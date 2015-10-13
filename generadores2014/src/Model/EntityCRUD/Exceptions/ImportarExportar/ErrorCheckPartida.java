package Model.EntityCRUD.Exceptions.ImportarExportar;

import java.sql.SQLException;

public class ErrorCheckPartida extends SQLException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ErrorCheckPartida(String attribute) {
		super("Error al verificar el nombre de la Partida: "+attribute);
	}

}
