package Model.EntityCRUD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Model.Config.SQL;
import Model.Entity.AspectoPlantilla;

/**
 * clase para las operaciones a la base de datos de las relaciones entre aspectos y plantillas
 * @author Luis Angel Hernández Lázaro
 *
 */
public class AspectosPlantillasCRUD {

	/**
	 * método para recuperar todas las realciones de aspectos por medio del identificador de la plantilla
	 * @param id_plantilla -- parametro para filtrar las relaciones 
	 * @return -- lista de relaciones de aspectos por medio del identificador de la plantilla
	 * @throws SQLException
	 */
	public static LinkedList< AspectoPlantilla> findRelationship(int id_plantilla) throws SQLException{
		LinkedList<AspectoPlantilla> listRelationship = new LinkedList<AspectoPlantilla>();
		String query = "SELECT * FROM `aspectos_plantillas` where plantillas_idplantilla="+id_plantilla;
		ResultSet result = SQL.query(query);
		while (result!=null && result.next()) {
			listRelationship .add(new AspectoPlantilla(result.getInt(2), result.getInt(1)));
		}
		SQL.close();
		return listRelationship;
	}
	
	/**
	 * método para guardar una relacion (entre aspectos y plantillas) en la base de datos 
	 * @param relation -  objeto relacion a guardar en la base de datos 
	 * @return -- verificación del insert en la base de datos
	 * @throws SQLException
	 */
	public static boolean saveRelationship(AspectoPlantilla relation) throws SQLException{
		String query="INSERT INTO `aspectos_plantillas` (`idaspecto`, `plantillas_idplantilla`) VALUES ("+relation.getIdAspecto()+", "+relation.getIdPlantilla()+");";
		boolean result = SQL.execute(query);
		SQL.close();
		return result;
	}
	
	/**
	 * método para liminar una relacion (entre aspectos y plantillas) en la base de datos
	 * @param relation -- objeto relacion a eliminar en la base de datos
	 * @return -- verificacion del delete en la base de datos
	 * @throws SQLException
	 */
	public static int deleteRelationship (AspectoPlantilla relation )throws SQLException{
		String query="DELETE FROM `aspectos_plantillas` WHERE  `idaspecto`="+relation.getIdAspecto()+" AND `plantillas_idplantilla`="+relation.getIdPlantilla()+";";
		int result = SQL.update(query);
		SQL.close();
		return result;
	}

}
