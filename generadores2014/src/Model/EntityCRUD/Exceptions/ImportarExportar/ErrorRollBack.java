package Model.EntityCRUD.Exceptions.ImportarExportar;

import java.sql.SQLException;

public class ErrorRollBack extends SQLException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public	ErrorRollBack() {
		super("Error al aplicar el punto de restauración de la base de datos");
	}

}
