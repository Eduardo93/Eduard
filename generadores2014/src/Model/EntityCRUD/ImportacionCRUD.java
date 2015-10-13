package Model.EntityCRUD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.LinkedList;

import org.apache.log4j.Logger;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import Model.Config.SQL;
import Model.Entity.AspectoSelection;
import Model.Entity.ConceptoSelection;
import Model.Entity.PartidaSelection;
import Model.EntityCRUD.Exceptions.DuplicateKey;
import Model.EntityCRUD.Exceptions.ForeingKeyReference;
import Model.EntityCRUD.Exceptions.ImportarExportar.ErrorCreateSavePoint;
import Model.EntityCRUD.Exceptions.ImportarExportar.ErrorRollBack;

public class ImportacionCRUD {

	private static Logger log = Logger.getLogger(ImportacionCRUD.class);
	private static Connection conn;

	public static boolean SavePartidas(LinkedList<PartidaSelection> data) throws SQLException, ForeingKeyReference {
		// para cada partida en la lista de partida
		conn = SQL.getConnection();
		Savepoint sp = null;
		try {
			sp = conn.setSavepoint();
		} catch (SQLException e) {
			conn.close();
			ErrorCreateSavePoint eSavePoint = new ErrorCreateSavePoint(e);
			throw eSavePoint;
		}
		for (PartidaSelection partidaSelection : data) {
			// se asigna el id en la partidas por medio del modelo la
			// partida
			try {
				partidaSelection.setIdpartida(checkPartida(partidaSelection));
			} catch (SQLException e) {
				try {
					conn.rollback(sp);
				} finally {
					conn.close();
				}
			}
			log.info(partidaSelection.getIdpartida() + " - " + partidaSelection.getNombre());
			// para cada concepto en la lista de conceptos de la partida
			// actual
			for (ConceptoSelection conceptoSelection : partidaSelection.getSubsConceptos()) {
				// se asigna el idenficador de la partida actual al concepto
				conceptoSelection.setIdpartida(partidaSelection.getIdpartida());
				// se asigna el id en el concepto por medio del modelo del
				// concepto
				try {
					conceptoSelection.setIdconcepto(checkConcepto(conceptoSelection));
				} catch (MySQLIntegrityConstraintViolationException eIntegrityConstraintViolationException) {
					if (eIntegrityConstraintViolationException.toString().contains("Cannot add or update a child row")) {
						ForeingKeyReference eForeingKeyReference = new ForeingKeyReference("Nombre Partida: " + partidaSelection.getNombre(), String.valueOf(conceptoSelection.getIdpartida() + " (como Identificador) "));
						log.error(eForeingKeyReference, eIntegrityConstraintViolationException);
						throw eForeingKeyReference;
					}
				} catch (SQLException e) {
					log.error(e);
					try {
						conn.rollback(sp);
					} finally {
						conn.close();
					}
				}
				log.info(conceptoSelection.getIdpartida() + "-\t" + conceptoSelection.getIdconcepto() + " - " + conceptoSelection.getNombre());
				// para cada desglose de concepto encontrado en la lista de
				// desgloses de concepto del concepto actual
				for (AspectoSelection aspectoSelection : conceptoSelection.getSubsAspectos()) {
					// se asigna el identificador del concepto actual
					aspectoSelection.setIdconcepto(conceptoSelection.getIdconcepto());
					// se asigna el identificador del desglose de concepto
					// por
					// mdeio del modelo del aspecto
					try {
						aspectoSelection.setIdaspecto(AspectoCRUD.save(aspectoSelection));
					} catch (MySQLIntegrityConstraintViolationException eIntegridad) {
						if (eIntegridad.toString().contains("Duplicate")) {
							DuplicateKey eDuplicateKey = new DuplicateKey("Clave", aspectoSelection.getClave());
							log.error(eDuplicateKey, eIntegridad);

							//	conn.rollback(sp);
							
							throw eDuplicateKey;
						} else if (eIntegridad.toString().contains("Cannot add or update a child row")) {
							ForeingKeyReference eForeingKeyReference = new ForeingKeyReference("Nombre Concepto: " + conceptoSelection.getNombre(), String.valueOf(aspectoSelection.getIdconcepto() + " (como Indetificador) "));
							log.error(eForeingKeyReference, eIntegridad);
							throw eForeingKeyReference;
						}
					} catch (SQLException e) {
						try {
							conn.rollback(sp);
						} finally {
							conn.close();
						}
					}
					log.info(conceptoSelection.getIdpartida() + "-\t" + aspectoSelection.getIdconcepto() + " -\t" + aspectoSelection.getIdaspecto() + " - " + aspectoSelection.getClave());
				}
			}
		}
		
		return true;
	}

	private static int checkPartida(PartidaSelection partida) throws SQLException {
		String query = "SELECT check_partida('" + partida.getNombre() + "');";
		ResultSet result = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(query);
		while (result != null && result.next()) {
			return result.getInt(1);
		}
		return 0;
	}

	private static int checkConcepto(ConceptoSelection concepto) throws SQLException {
		String query = "SELECT check_concepto(" + concepto.getIdpartida() + ",'" + concepto.getNombre() + "');";
		ResultSet result = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(query);
		while (result != null && result.next()) {
			return result.getInt(1);
		}
		return 0;
	}

	public static int saveDesglose(AspectoSelection aspecto) throws SQLException {
		String query = "SELECT agregar_aspecto(" + aspecto.getIdconcepto() + ",'" + aspecto.getClave() + "','" + aspecto.getUnidad() + "','" + aspecto.getCosto() + "','" + aspecto.getDescripcion() + "');";
		ResultSet result = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(query);
		while (result != null && result.next()) {
			return result.getInt(1);
		}
		return 0;
	}

}
