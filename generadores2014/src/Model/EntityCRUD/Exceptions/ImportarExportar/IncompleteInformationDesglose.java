/**
 * 
 */
package Model.EntityCRUD.Exceptions.ImportarExportar;

/**
 * @author usuariofei
 *
 */
public class IncompleteInformationDesglose extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public IncompleteInformationDesglose(String attribute, int valueRow) {
		super("Falta Información del Concepto en el campo: "+attribute + ", en la fila número "+ valueRow +" del archivo de importación");
	}
}
