package Model.EntityCRUD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Model.Config.SQL;
import Model.Entity.HistorialConsultor;
import ObjetosSerializables.Consultor;
import ObjetosSerializables.Proyecto;

/**
 * clase para las operaciones a la base de datos para los historiales de los consultores
 * @author Luis Angel Hernández Lázaro
 *
 */
public class HistorialConsultorCRUD {
	
	/**
	 * método para guardar un nuevo registro de historial en la base de datos
	 * @param historial -- objeto a guardar en la base de datos
	 * @return -- verificacion del insert en la base de datos
	 * @throws SQLException
	 */
	public static int save(HistorialConsultor historial) throws SQLException{
		int idHistorial=0;
		//llamada al método agregar_historial
		String query ="SELECT `agregar_historial`('"+historial.getProyecto().getProyecto()+"', '"+historial.getIdConsultorAnterior().getIdconsultor()+"', '"+historial.getIdConsultorNuevo().getIdconsultor()+"','"+historial.getProyecto().getIdproyecto()+"')";
		ResultSet result = SQL.query(query);
		while (result!=null && result.next()) {
			idHistorial = result.getInt(1);
		}
		SQL.close();
		return idHistorial;
	}
	
	/**
	 * método para recuperar el historial de proyectos de un consulor
	 * @param idConsultor - identificador del consultorr para recuperar sus proyectos reasignados
	 * @return lista de historial de consultor
	 * @throws SQLException
	 */
	public static LinkedList<HistorialConsultor> findHistorialConsultor(int idConsultor) throws SQLException{
		LinkedList<HistorialConsultor> listHistorialConsultor = new LinkedList<HistorialConsultor>();
		String query="SELECT DISTINCT h.id_historial, h.proyecto, h.id_consultor_anterior, h.id_consultor_nuevo, h.fecha_registro, p.idproyecto, ant.nombre, ant.materno, ant.paterno, nue.nombre, nue.paterno, nue.materno FROM historial_consultor AS h INNER JOIN proyecto AS p ON h.proyecto = p.proyecto INNER JOIN consultor AS ant ON h.id_consultor_anterior = ant.idConsultor INNER JOIN consultor AS nue ON h.id_consultor_nuevo = nue.idConsultor WHERE h.id_consultor_anterior="+idConsultor;
		ResultSet result = SQL.query(query);
		while (result!=null && result.next()) {
			//se crea un nuevo historial para ser agregado
			HistorialConsultor historial= new  HistorialConsultor();
			historial.setIdHistorial(result.getInt("id_historial"));
			//se crea el proyecto donde se realizo la reasignación 
			Proyecto proyecto = new Proyecto();
			proyecto.setIdproyecto(result.getInt("idproyecto"));
			proyecto.setProyecto(result.getString("proyecto"));
			historial.setProyecto(proyecto);
			//se crea el consultor (nuevo, anterior ) en el proyecto
			Consultor nuevo= new Consultor(), anterior= new Consultor();
			anterior.setIdconsultor(result.getInt("id_consultor_anterior"));
			anterior.setNombre(result.getString("ant.nombre"));
			anterior.setPaterno(result.getString("ant.paterno"));
			anterior.setMaterno(result.getString("ant.materno"));
			
			nuevo.setIdconsultor(result.getInt("id_consultor_nuevo"));
			nuevo.setNombre(result.getString("nue.nombre"));
			nuevo.setPaterno(result.getString("nue.paterno"));
			nuevo.setMaterno(result.getString("nue.materno"));
			
			
			historial.setIdConsultorAnterior(anterior);
			historial.setIdConsultorNuevo(nuevo);
			historial.setFechaRegistro(result.getTimestamp("fecha_registro"));
			//se agrega a la lista el registro del historial
			listHistorialConsultor.add(historial);
		}
		SQL.close();
		return listHistorialConsultor;
	}
	
	/**
	 * método para recuperar todos los proyectos de la base de datos por medio del identificador del consultor
	 * @param idConsultor -- identificador del consultor para aplicar el filtro en la búsqueda
	 * @return lista de proyectos 
	 * @throws SQLException
	 */
	public static LinkedList<Proyecto> findProyecto(int idConsultor) throws SQLException{
		LinkedList<Proyecto> listProyectos= new LinkedList<Proyecto>();
		String query="select distinct p.idproyecto, p.proyecto from proyecto as p inner join frente as f on p.idproyecto =f.idproyecto inner join control_estimacion as ce on f.idfrente = ce.idfrente inner join consultor as c on c.idConsultor = ce.idConsultor where c.idConsultor="+idConsultor+" order by p.idproyecto;";
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
	
	/**
	 * método para recuperara todos los consultores de la base de datos por medio de un identificador en la creacion de estimaciones
	 * @param idProyecto -- identificador del proyecto a buscar en la base de datos
	 * @return lista de consultores que colaboran en el proyecto seleccionado por id
	 * @throws SQLException
	 */
	public static LinkedList<Consultor> findConsultor(int idProyecto) throws SQLException{
		LinkedList<Consultor> listConsultors = new LinkedList<Consultor>();
		String query="SELECT DISTINCT c.paterno, c.materno, c.nombre, c.idConsultor FROM consultor AS c INNER JOIN control_estimacion AS ce ON ce.idConsultor = c.idConsultor INNER JOIN frente AS f ON ce.idfrente = f.idfrente WHERE f.idproyecto ="+idProyecto;
		ResultSet result = SQL.query(query);
		while (result!=null && result.next()) {
			Consultor consultor = new Consultor();
			consultor.setPaterno(result.getString(1));
			consultor.setMaterno(result.getString(2));
			consultor.setNombre(result.getString(3));
			consultor.setIdconsultor(result.getInt(4));
			listConsultors.add(consultor);
		}
		SQL.close();
		return listConsultors;
	}
	
}
