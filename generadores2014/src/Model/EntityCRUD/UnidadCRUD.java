package Model.EntityCRUD;

import java.net.ConnectException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import org.apache.log4j.Logger;

import Model.Config.SQL;
import Model.Entity.Unidad;
import Model.EntityCRUD.Exceptions.AttributeIsEmpty;
import Model.EntityCRUD.Exceptions.ErrorConnectionDB;
import Model.EntityCRUD.Exceptions.MaxLength;
import Model.EntityCRUD.Exceptions.Unidad.NoSelectAttributeUnidad;

/**
 * 
 * @author usuariofei
 *
 */
public class UnidadCRUD {
	// variable estatica para el límite de los caracteres en el nombre de la
	// unidad
	private static final int maxLengthUnidad = 25;
	private static final Logger log = Logger.getLogger(UnidadCRUD.class);

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * APARTADO DE METODOS PARA UNIDADES DE MEDIDA ADD,GET,UPDATE,DELETE
	 * 
	 */
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Método para agregar las unidades de medición
	 * 
	 * @param nameUnit
	 *            --- nombre de la nueva unidad de medida
	 * @return insert con éxito ? true : false
	 * @throws AttributeIsEmpty
	 * @throws MaxLength
	 * @throws NoSelectAttributeUnidad
	 * @throws ConnectException 
	 * @throws Exception
	 */
	public static int save(Unidad unidad) throws SQLException, NoSelectAttributeUnidad, MaxLength, AttributeIsEmpty {
		validateUnidad(unidad);
		int idUnidad = 0;
			// escape de caracteres en el nombre de la unidad
			String query = "SELECT `agregar_unidad`('" + unidad.getNombre().replace("\\", "\\\\").replace("\"", "\\\"").replace("'", "\\'") + "', " + unidad.isLargo() + ", " + unidad.isAncho() + ", " + unidad.isAlto() + ");";
			ResultSet result = SQL.query(query);
			while (result != null && result.next()) {
				idUnidad = result.getInt(1);
			}
			SQL.close();
		return idUnidad;

	}

	/**
	 * Método para actualizar el nombre de la unidad
	 * 
	 * @param nameUnitNew
	 *            --- nuevo nombre de la unidad
	 * @param nameUnitOld
	 *            --- nombre actual en la base de datos
	 * @return update con éxito ? true : false
	 * @throws AttributeIsEmpty
	 * @throws MaxLength
	 * @throws NoSelectAttributeUnidad
	 * @throws ConnectException 
	 */
	public static int update(Unidad unidad) throws SQLException, NoSelectAttributeUnidad, MaxLength, AttributeIsEmpty {

		validateUnidad(unidad);
		int result = 0;
		try {
			String query = "UPDATE `unidad` SET `nombre`='" + unidad.getNombre() + "', `largo`=" + unidad.isLargo() + ", `ancho`=" + unidad.isAncho() + ", `alto`=" + unidad.isAlto() + " WHERE  `id_unidad`=" + unidad.getIdUnidad() + ";";
			result = SQL.update(query);
			SQL.close();
		} catch (SQLException eSQL) {
			ErrorConnectionDB eConnectionDB = new ErrorConnectionDB();
			log.error(eConnectionDB+"crud", eSQL);
			throw eConnectionDB;
		}
		return result;
	}

	/**
	 * 
	 * @param nameUnit
	 * @return
	 * @throws ConnectException 
	 */
	public static int delete(Unidad unidad) throws SQLException {
		String query = "DELETE FROM `unidad` WHERE  `id_unidad`=" + unidad.getIdUnidad() + ";";
		int resutl = SQL.update(query);
		SQL.close();
		return resutl;
	}

	/**
	 * Método para obtener la lista de unidades de medida de la base de datos
	 * 
	 * @return listaUnidades --- unidades actuales en la base de datos
	 * @throws ConnectException 
	 */
	public static LinkedList<Unidad> findUnidades() throws SQLException {
		LinkedList<Unidad> listUnidad = new LinkedList<Unidad>();
		String query = "SELECT id_unidad, nombre, largo, ancho, alto from unidad;";
		ResultSet result = SQL.query(query);

		while (result != null && result.next()) {
			Unidad unidad = new Unidad();
			unidad.setIdUnidad(result.getInt("id_unidad"));
			unidad.setNombre(result.getString("nombre"));
			unidad.setLargo(result.getBoolean("largo"));
			unidad.setAncho(result.getBoolean("ancho"));
			unidad.setAlto(result.getBoolean("alto"));
			listUnidad.add(unidad);
		}
		SQL.close();
		return listUnidad;
	}

	/**
	 * 
	 * @param unidad
	 * @throws Exception
	 */
	public static void validateUnidad(Unidad unidad) throws NoSelectAttributeUnidad, MaxLength, AttributeIsEmpty {
		// verficación de los atributos para la unidad
		if (!unidad.isLargo() && !unidad.isAncho() && !unidad.isAlto()) {
			NoSelectAttributeUnidad e = new NoSelectAttributeUnidad();
			log.warn(e);
			throw e;
		}
		// verificación del número de caracteres ingresados en el nombre de la
		// unidad
		if (unidad.getNombre().length() > maxLengthUnidad) {//
			MaxLength e = new MaxLength("Nombre de Unidad");
			log.warn(e);
			throw e;
		}
		//
		if (unidad.getNombre().trim().isEmpty()) {
			AttributeIsEmpty e = new AttributeIsEmpty("Nombre de Unidad");
			log.warn(e);
			throw e;
		}
	}
}
