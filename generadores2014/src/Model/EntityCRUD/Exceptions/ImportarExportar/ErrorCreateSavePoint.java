package Model.EntityCRUD.Exceptions.ImportarExportar;

import java.sql.SQLException;

public class ErrorCreateSavePoint extends SQLException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErrorCreateSavePoint(Exception e) {
		super("Error al obtener un Punto de restauración de la base de datos");
	}

}
