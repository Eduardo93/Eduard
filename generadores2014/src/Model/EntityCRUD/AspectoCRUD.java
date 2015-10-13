package Model.EntityCRUD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Model.Config.SQL;
import ObjetosSerializables.Aspecto;

/**
 * 
 * Clase para hacer las operaciones a la base de datos para los (desgloses de concepto) aspectos
 * @author Luis Angel Hernández Lázaro
 *
 */
public class AspectoCRUD {
	
	/**
	 * Agregar un nuevo aspecto a la base de datos
	 * @param aspecto - objeto a agregar
	 * @return - verficación del insert en la base de datos
	 * @throws SQLException
	 */
	public static int  save(Aspecto aspecto) throws SQLException{
		//llamada a la funcion agregar_aspecto para guardar el aspecto en la base de datos
		String query="SELECT agregar_aspecto("+aspecto.getIdconcepto()+",'"+aspecto.getClave()+"','"+aspecto.getUnidad()+"','"+aspecto.getCosto()+"','"+aspecto.getDescripcion()+"');";
		ResultSet result = SQL.query(query);
		int idAspecto=0;
		while (result!=null && result.next()) {
			idAspecto= result.getInt(1);
		}
		SQL.close();
		return idAspecto;
	}
	
	/**
	 * método para recuperar todos los desgloses de concepto de la base de datos 
	 * @return
	 * @throws SQLException
	 */
	public static LinkedList<Aspecto> findAspectos()throws SQLException{
		//se crea una lista para lamcaenar los elementos de los desgloses de concepto temporalemente 
		LinkedList<Aspecto> listAspectos = new LinkedList<Aspecto>();
		String query="SELECT `idaspecto`, `idConcepto`, `clave`, `unidad`, `tipo`, `costo`, `descripcion`, `clave_publica`, `descripcion_completa` FROM `aspecto`  order by clave asc;";
		ResultSet result = SQL.query(query);
		while (result!=null && result.next()) {
			listAspectos.add(new Aspecto(result.getInt("idaspecto"), result.getInt("idConcepto"), result.getString("clave"), result.getString("unidad"), result.getString("tipo"), result.getString("costo"), result.getString("descripcion"), result.getString("clave_publica"), result.getString("descripcion_Completa")));
		}
		SQL.close();
		return listAspectos;
	}

}
