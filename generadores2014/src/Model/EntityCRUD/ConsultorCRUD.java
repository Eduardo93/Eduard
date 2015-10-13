package Model.EntityCRUD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Model.Config.SQL;
import ObjetosSerializables.Consultor;

/**
 * clase para las operaciones a la base de datos de los consultores
 * @author Luis Angel Hernández Lázaro
 *
 */
public class ConsultorCRUD {
	
	/**
	 * método para recuperar la informaciónd e todos los consultores en la base de datos....
	 * sólo se recuperan id, nombre, ap paterno, ap materno ... por que sólo son necesarios en las nuevas actualizaciones, si más adelante se agregan los demás atributos del consultor no existe problema.
	 * @return
	 * @throws SQLException
	 */
	public static LinkedList<Consultor> findConsultors () throws SQLException{
		LinkedList<Consultor> listconConsultors = new LinkedList<Consultor>();
		String query="select idConsultor, paterno, materno, nombre from consultor;";
		ResultSet result = SQL.query(query);
		while(result!= null && result.next()) {
			Consultor consultor = new Consultor();
			consultor.setIdconsultor(result.getInt("idConsultor"));
			consultor.setNombre(result.getString("nombre"));
			consultor.setPaterno(result.getString("paterno"));
			consultor.setMaterno(result.getString("materno"));
			listconConsultors.add(consultor);
		}
		SQL.close();
		return listconConsultors;
	}

}
