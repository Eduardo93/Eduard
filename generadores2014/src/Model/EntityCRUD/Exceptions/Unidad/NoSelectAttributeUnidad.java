package Model.EntityCRUD.Exceptions.Unidad;

public class NoSelectAttributeUnidad extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSelectAttributeUnidad() {
		super("Ningún atributo (largo, ancho, alto) seleccionado");
	}
}
