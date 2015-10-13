package Model.Config;

import java.sql.*;

import MetodosRemotos.Metodos;

public class Connect {

	private Connection connection;
	public Connect(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//
			connection = DriverManager.getConnection(Metodos.getConnectionStrinsg());
		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(null, "Ha ocurrido un error al Intentar al Conectar a la Base de Datos");
			System.err.println("Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Se produjo un error inesperado: " + e.getMessage());
		}
	}

	public Connection connection() {
		return connection;
	}

	public void close() {
		try {
			connection.close();
			System.out.println("La base de datos se cerro correctamente");
		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage() + "\n" + e.getErrorCode());
		}
	}
}