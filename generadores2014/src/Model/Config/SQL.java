package Model.Config;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Connection;

public class SQL {

	private static Statement statement;
	private static Connection connection;
	
	/**
	 * @return the connection
	 */
	public static Connection getConnection() {
		connection = new Connect().connection();
		return connection;
	}

	public static boolean execute(String query) throws SQLException {
		connection = new Connect().connection();
		statement = connection.createStatement();
		return statement.execute(query);
	}

	/**
	 * @param connection the connection to set
	 */
	public static void setConnection(Connection connection) {
		SQL.connection = connection;
	}

	public static ResultSet query(String query) throws SQLException {
		connection = new Connect().connection();
	
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); 
		return statement.executeQuery(query);
	}

	public static int update(String query) throws SQLException {
		connection = new Connect().connection();
		statement= connection.createStatement();
		return statement.executeUpdate(query);// -1 si hay error, 0 si OK
	}

	public static void close() {
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	/*public static void ExecuteTransaction(LinkedList<PartidaSelection> listPartidasImport) throws SQLException {
		connection= new Connect().connection();
		connection.setAutoCommit(false);
		
	}*/

}
