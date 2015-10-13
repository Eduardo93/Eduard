package AdministrarPlantillas.Controller;

import java.sql.SQLException;
import java.util.LinkedList;

import Model.Entity.AspectoSelection;
import Model.Entity.ConceptoSelection;
import Model.Entity.PartidaSelection;
import Model.EntityCRUD.AspectoCRUD;
import Model.EntityCRUD.ConceptoCRUD;
import Model.EntityCRUD.PartidaCRUD;
import Model.EntityCRUD.PlantillaCRUD;
import Model.EntityCRUD.Exceptions.AttributeIsEmpty;
import Model.EntityCRUD.Exceptions.MaxLength;
import ObjetosSerializables.Plantilla;
import ObjetosSerializables.Aspecto;
import ObjetosSerializables.Concepto;
import ObjetosSerializables.Partida;

/**
 * clase para el acceso a la base de datos de la plantilla
 * @author Luis Angel Hernández Lázaro
 *
 */
public class CPlantilla {

	/**
	 * Guardar una nueva plantilla en la base de datos
	 * @param plantilla - objeto a guardar
	 * @return id_plantilla 
	 * @throws SQLException
	 * @throws MaxLength 
	 * @throws AttributeIsEmpty 
	 */
	public static int save(Plantilla plantilla) throws SQLException, AttributeIsEmpty, MaxLength {
		return PlantillaCRUD.save(plantilla);
	}

	/**
	 * Actualizar una plantilla en la base de datos
	 * @param plantilla - objeto a actualizar
	 * @return verificación de la actualización de la plantilla
	 * @throws SQLException
	 * @throws MaxLength 
	 * @throws AttributeIsEmpty 
	 */
	public static int update(Plantilla plantilla) throws SQLException, AttributeIsEmpty, MaxLength {
		return PlantillaCRUD.update(plantilla);
	}

	/**
	 * elimianr una plantilla de la base de datos
	 * @param plantilla - objeto a eliminar de la base de datos
	 * @return verificación de la eliminación de la plantilla
	 * @throws SQLException
	 */
	public static int delete(Plantilla plantilla) throws SQLException {
		return PlantillaCRUD.delete(plantilla);
	}

	/**
	 * Recuperación de la información de las plantillas en la base de datos
	 * @return lista de plantillas 
	 * @throws SQLException
	 */
	public static LinkedList<Plantilla> findPlantillas() throws SQLException {
		return PlantillaCRUD.findPlantillas();
	}

	/**
	 * Recuperación de la información de las partidas en la base de datos 
	 * @return lista de partidas en la base de datos
	 * @throws SQLException
	 */
	public static LinkedList<Partida> findPartidas() throws SQLException {
		return PartidaCRUD.findPartidas();
	}

	/**
	 * recuperación de la información de los conceptos en la base de datos
	 * @return lista de conceptos en la base de datos
	 * @throws SQLException
	 */
	public static LinkedList<Concepto> findConceptos() throws SQLException {
		return ConceptoCRUD.findConceptos();
	}

	/**
	 * Recuperación de la información de los aspectos (desglose de concepto) en la base de datos
	 * @return lista de aspectos en la base de datos
	 * @throws SQLException
	 */
	public static LinkedList<Aspecto> findAspectos() throws SQLException {
		return AspectoCRUD.findAspectos();
	}
	
	/**
	 * método para vincular cada uno de los desgloses de concepto con su
	 * concepto correspondiente y cada uno de los conceptos con su partida
	 * correspondiente
	 * 
	 * @return lista de partidas con sus conceptos y sus desgloses vinculados
	 *         correctamente
	 * @throws SQLException
	 */
	public static LinkedList<PartidaSelection> findPartidaSelection() throws SQLException {
		LinkedList<Partida> list = new LinkedList<Partida>();
		LinkedList<PartidaSelection> listSelect = new LinkedList<PartidaSelection>();

		// recuperación de las partidas en la base de datos
		list = PartidaCRUD.findPartidas();
		// para cada partida se agrega a la lista de partidas de seleccion
		// las partidas de seleccion heredan de partida , y se agrega un
		// atributo que es select (para saber si esta marcada o no en pantalla)
		for (Partida partida : list) {
			listSelect.add(new PartidaSelection(true, partida.getIdpartida(), partida.getNombre()));
		}

		// obtener la lista de aspectos y conceptos de la base de datos ya en
		// formato aspecto de seleccion
		// concepto de seleccion hereda de concepto y se agrega un atributo
		// select
		LinkedList<ConceptoSelection> listconcepto = findConceptoSelection();
		// aspecto de seleccion hereda de aspecto y se agrega un atributo select
		LinkedList<AspectoSelection> listAspecto = findAspectoSelection();
		// llenar relacion entre conceptos y aspectos
		for (ConceptoSelection conceptoSelection : listconcepto) {
			for (AspectoSelection aspectoSelection : listAspecto) {
				// si los identificadores de concepto coinciden entre aspecto y
				// concepto, se agrega el aspecto a la lista de aspectos
				// vinculados al concepto
				if (conceptoSelection.getIdconcepto() == aspectoSelection.getIdconcepto()) {
					conceptoSelection.addSubsAspecto(aspectoSelection);
				}
			}
		}

		// lenar relacion entre partidas y conceptos
		for (PartidaSelection partida : listSelect) {
			for (ConceptoSelection concepto : listconcepto) {
				// si los identificadores de partidas coinciden entre partida y
				// concepto, se agrega el concepto a la lista de conceptos
				// vinculados a la partida
				if (concepto.getIdpartida() == partida.getIdpartida()) {
					partida.addSubsConceptos(concepto);
				}
			}
		}
		return listSelect;
	}
	
	/**
	 * método para obtener los conceptos en objetos de tipo concepto de
	 * seleccion
	 * 
	 * @return lista de conceptos de seleccion
	 * @throws SQLException
	 */
	public static LinkedList<ConceptoSelection> findConceptoSelection() throws SQLException {
		LinkedList<Concepto> list = new LinkedList<Concepto>();
		LinkedList<ConceptoSelection> listSelect = new LinkedList<ConceptoSelection>();
		// recuperación de los conceptos
		list = ConceptoCRUD.findConceptos();
		for (Concepto concepto : list) {
			// creacion del objeto concepto de seleccion
			listSelect.add(new ConceptoSelection(true, concepto.getIdconcepto(), concepto.getIdpartida(), concepto.getNombre()));
		}
		return listSelect;
	}

	/**
	 * método para obtener los desgloses de concepto en objetos de tipo
	 * seleccion
	 * 
	 * @return lista de desglose de concepto de seleccion
	 * @throws SQLException
	 */
	public static LinkedList<AspectoSelection> findAspectoSelection() throws SQLException {
		LinkedList<Aspecto> list = new LinkedList<Aspecto>();
		LinkedList<AspectoSelection> listSelect = new LinkedList<AspectoSelection>();
		// recuperación de los desgloses de concepto
		list = AspectoCRUD.findAspectos();
		for (Aspecto aspecto : list) {
			// creacion del objeto de seleccion
			listSelect.add(new AspectoSelection(true, aspecto.getIdaspecto(), aspecto.getIdconcepto(), aspecto.getClave(), aspecto.getUnidad(), aspecto.getTipo(), aspecto.getCosto(), aspecto.getDescripcion(), aspecto.getClave_privada(), aspecto.getDescripcion_Completa()));
		}
		return listSelect;
	}

	
}
