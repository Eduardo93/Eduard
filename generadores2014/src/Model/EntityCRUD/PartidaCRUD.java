package Model.EntityCRUD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Model.Config.SQL;
import ObjetosSerializables.Partida;

/**
 * clase para las operaciones a la base de datos
 * 
 * @author Luis Angel Hernández Lázaro
 *
 */
public class PartidaCRUD {

	/**
	 * método para verificar la existencia de una partida en la base de datos,
	 * si existe se recupera el id de la partida , si no se realiza el insert en
	 * la base de datos
	 * 
	 * @param partida
	 *            -- objeto a verificar
	 * @return -- id partida
	 * @throws SQLException
	 */
	public static int checkPartida(Partida partida) throws SQLException {
		// llamada a la funcion en la base de datos para verificarr la partida
		String query = "SELECT check_partida('" + partida.getNombre() + "');";
		ResultSet result = SQL.query(query);
		int idPartida=0;
		while (result != null && result.next()) {
			idPartida= result.getInt(1);
		}
		SQL.close();
		return idPartida;
	}

	/**
	 * método para recuperar todas las partidas de la base de datos
	 * 
	 * @return lista de partidas
	 * @throws SQLException
	 */
	public static LinkedList<Partida> findPartidas() throws SQLException {
		LinkedList<Partida> listPartida = new LinkedList<Partida>();
		String query = "SELECT idpartida, nombre FROM partida order by nombre asc";
		ResultSet result = SQL.query(query);
		while (result != null && result.next()) {
			listPartida.add(new Partida(result.getInt("idpartida"), result.getString("nombre")));
		}
		SQL.close();
		return listPartida;
	}

}
