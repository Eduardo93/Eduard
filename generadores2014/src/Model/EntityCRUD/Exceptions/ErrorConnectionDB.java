package Model.EntityCRUD.Exceptions;

import java.sql.SQLException;

public class ErrorConnectionDB extends SQLException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErrorConnectionDB() {
		super("Ha ocurrido un error al Intentar al Conectar a la Base de Datos");
	}

}
