package Model.EntityCRUD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Model.Config.SQL;
import ObjetosSerializables.Proyecto;

/**
 * clase para las operaciones a la base de datos de los proyectos
 * @author Luis Angel Hernández Lázaro
 *
 */
public class ProyectoCRUD {
	
	/**
	 * método para la recuperacion de la información de los proyectos
	 * @return lista de proyectos 
	 * @throws SQLException
	 */
	public static LinkedList<Proyecto> findProyectos () throws SQLException{
		LinkedList<Proyecto> listProyectos= new LinkedList<Proyecto>();
		String query="SELECT p.idproyecto, p.proyecto FROM proyecto AS p;";
		ResultSet result = SQL.query(query);
		while (result!=null && result.next()) {
			Proyecto proyecto = new Proyecto();
			proyecto.setIdproyecto(result.getInt(1));
			proyecto.setProyecto(result.getString(2));
			listProyectos.add(proyecto);
		}
		SQL.close();
		return listProyectos;
	}
	
}
