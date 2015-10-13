package Model.EntityCRUD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Model.Config.SQL;
import Model.Entity.ConceptoSelection;
import ObjetosSerializables.Concepto;

/**
 * clase para las operaciones de los conceptos en la base de datos
 * @author <Luis Angel Hernández Lázaro
 *
 */
public class ConceptoCRUD {
	
	/**
	 * método para verificar si el concepto recibido como parametro existe en la base de datos...
	 * si el concepto existe se recupera el id del concepto, 
	 * si no existe se realiza el insert en la base de datos
	 * @param concepto -- objeto a buscar en la base de datos
	 * @return -- id_concepto--- identificador del concepto 
	 * @throws SQLException
	 */
	public static int  checkConcepto(ConceptoSelection concepto)throws SQLException{
		String query="SELECT check_concepto("+concepto.getIdpartida()+",'"+concepto.getNombre()+"');";
		ResultSet result = SQL.query(query);
		int idConcepto=0;
		while (result!=null && result.next()) {
			idConcepto=result.getInt(1);
		}
		SQL.close();
		return idConcepto;
	}
	
	/**
	 * método para recuperar todos los conceptos de la base de datos
	 * @return -- lista de conceptos en la base de datos
	 * @throws SQLException
	 */
	public static LinkedList<Concepto> findConceptos() throws SQLException{
		LinkedList<Concepto> listConceptos = new LinkedList<Concepto>();
		String query="SELECT idconcepto, idpartida, nombre FROM `concepto`  order by nombre asc;";
		ResultSet result = SQL.query(query);
		while (result!=null && result.next()) {
			listConceptos.add(new Concepto(result.getInt("idconcepto"), result.getInt("idpartida"), result.getString("nombre")));
		}
		SQL.close();
		return listConceptos;
	}

}
