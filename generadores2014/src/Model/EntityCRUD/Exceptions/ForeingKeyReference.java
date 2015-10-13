package Model.EntityCRUD.Exceptions;

public class ForeingKeyReference extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ForeingKeyReference(String attribute, String value) {
		super("El campo: "+attribute+" con valor: "+value+" no se encuentra disponible en la base de datos.");
	}
}
