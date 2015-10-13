package Model.EntityCRUD.Exceptions;

public class ForeingKeyReferenceDelete extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ForeingKeyReferenceDelete(String attribute, String value) {
		super("El campo: "+attribute+" con valor: "+value+", no se puede eliminar. Actualmente Se encuentra en uso por la base de datos");
	}
}
