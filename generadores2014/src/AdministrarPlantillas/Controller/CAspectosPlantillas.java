package AdministrarPlantillas.Controller;

import java.sql.SQLException;
import java.util.LinkedList;

import Model.Entity.AspectoPlantilla;
import Model.EntityCRUD.AspectosPlantillasCRUD;

/**
 * Clase para el acceso a la base de datos para las relaciones entre aspectos y plantillas
 * @author Luis Angel Hernández Lázaro
 */
public class CAspectosPlantillas {
	
	/**
	 * Guardar una relación en la base de datos 
	 * @param relation - objeto a guardar
	 * @return verificación del INSERT
	 * @throws SQLException
	 */
	public static boolean saveRelationship(AspectoPlantilla relation)throws SQLException{
		return AspectosPlantillasCRUD.saveRelationship(relation);
	}
	
	/**
	 * Eliminar una relación en la base de datos 
	 * @param relation -objeto a eliminar
	 * @return verificación de la eliminación
	 * @throws SQLException
	 */
	public static int deleteRelationship(AspectoPlantilla relation) throws SQLException{
		return AspectosPlantillasCRUD.deleteRelationship(relation);
	}

	/**
	 * Recuperación de la lista de aspectos incluidos en una plantilla por medio de su identificador 
	 * @param id_plantilla - identificador de la plantilla para flitrar la consulta
	 * @return lista de aspectos incluidos en una plantilla
	 * @throws SQLException
	 */
	public static LinkedList<AspectoPlantilla> findRelationship(int id_plantilla) throws SQLException {
		return AspectosPlantillasCRUD.findRelationship(id_plantilla);
	}

}
