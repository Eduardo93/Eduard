package Model.EntityCRUD.Exceptions;

public class AttributeIsEmpty extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AttributeIsEmpty(String attribute) {
		super("El "+attribute+" no puede estar vacío");
	}
}
