package Model.EntityCRUD.Exceptions;

import java.sql.SQLException;

public class DuplicateKey extends SQLException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DuplicateKey(String attribute, String value) {
		super ("El campo: "+ attribute+ " con valor: "+value+" ya se ecuentra registrado en la base de datos");
	}

}
