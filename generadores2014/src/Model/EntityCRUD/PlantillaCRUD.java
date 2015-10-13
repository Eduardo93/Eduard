package Model.EntityCRUD;

import java.sql.SQLException;
import java.util.LinkedList;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import Model.Config.SQL;
import Model.EntityCRUD.Exceptions.AttributeIsEmpty;
import Model.EntityCRUD.Exceptions.MaxLength;
import ObjetosSerializables.Plantilla;

/**
 * clase para las operaciones de la base de datos de las plantillas
 * @author Luis Angel Hernández Lázaro 
 *
 */
public class PlantillaCRUD {
	private static final Logger log = Logger.getLogger(PlantillaCRUD.class);
	private static final int maxLengthPlantilla=45;
	
	/**
	 * método para guardar una plantilla
	 * @param plantilla - objeto a guardar 
	 * @return verificación del insert, si se registra en la base de datos se regresa el id de la plantilla 
	 * @throws SQLException
	 * @throws MaxLength 
	 * @throws AttributeIsEmpty 
	 */
	public static int save(Plantilla plantilla) throws SQLException, AttributeIsEmpty, MaxLength {
		validatePlantilla(plantilla);
		//llamada al método agregar plantilla, el método hace el insert 
		String query = "SELECT `AgregarPlantilla`('"+plantilla.getNombre()+"', now());";
		ResultSet result = SQL.query(query);
		int id_plantilla=0;
		while(result!=null && result.next()) {
			id_plantilla= result.getInt(1);
		}
		SQL.close();
		return id_plantilla;
	}
	
	/**
	 * método para actualizar la información de una plantilla
	 * @param plantilla - objeto a actualizar 
	 * @return verificación del update
	 * @throws SQLException
	 * @throws MaxLength 
	 * @throws AttributeIsEmpty 
	 */
	public static int update(Plantilla plantilla ) throws SQLException, AttributeIsEmpty, MaxLength{
		
		validatePlantilla(plantilla);
		
		String query="UPDATE `plantillas` SET `nombre`='"+plantilla.getNombre()+"' WHERE  `id_plantilla`="+plantilla.getIdplantilla()+";";
		int result = SQL.update(query);
		SQL.close();
		return result;
	}
	
	/**
	 * método para eliminar una plantilla
	 * @param plantilla - objeto a eliminar
	 * @return verificacion de delete 
	 * @throws SQLException
	 */
	public static int delete (Plantilla plantilla ) throws SQLException{
		String query="DELETE FROM `plantillas` WHERE  `id_plantilla`="+plantilla.getIdplantilla()+";";
		int result =SQL.update(query);
		SQL.close();
		return result;
	}
	
	/**
	 * método para recuperar la información de las plantillas en la base de datos 
	 * @return lista de plantillas en la base de datos
	 * @throws SQLException
	 */
	public static LinkedList<Plantilla> findPlantillas() throws SQLException{
		LinkedList<Plantilla> listPlantilla = new LinkedList<Plantilla>();
		String query="SELECT id_plantilla, nombre FROM `plantillas` order by nombre asc;";
		ResultSet result = (ResultSet) SQL.query(query);
		while (result != null && result.next()) {
			Plantilla plantilla = new Plantilla();
			plantilla.setIdplantilla(result.getString("id_plantilla"));
			plantilla.setNombre(result.getString("nombre"));
			listPlantilla.add(plantilla);
		}
		SQL.close();
		return listPlantilla;
	}
	
	/**
	 * 
	 * @param plantilla
	 * @throws AttributeIsEmpty
	 * @throws MaxLength
	 */
	public static void validatePlantilla(Plantilla plantilla) throws AttributeIsEmpty, MaxLength{
		if (plantilla.getNombre().length()>maxLengthPlantilla) {
			MaxLength e = new MaxLength("Nombre de la Plantilla");
			log.warn(e);
			throw e;
		}
		if (plantilla.getNombre().trim().isEmpty()) {
			AttributeIsEmpty e = new AttributeIsEmpty("Nombre de la Plantilla");
			log.warn(e);
			throw e;
		}
	}

}
