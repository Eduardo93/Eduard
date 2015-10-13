package Options;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Clase para validar las longitudes de los campos de los TextFields
 * 
 * @author luiiis lazaro
 * @category Complementos
 * @see PlainDocument
 */
public class LenString extends PlainDocument {

	private static final long serialVersionUID = 1L;
	// longitud máxima
	private final int limit;

	/**
	 * Constructor asignación del limite
	 * 
	 * @param limit
	 *            int --- longitud máxima del componente
	 */
	public LenString(int limit) {
		this.limit = limit;
	}

	/**
	 * Verifica si la cadena cumple con la longitud establecida
	 * 
	 * @Override(non-Javadoc)
	 * @see javax.swing.text.PlainDocument#insertString(int, java.lang.String,
	 *      javax.swing.text.AttributeSet)
	 */
	public void insertString(int offs, String str, javax.swing.text.AttributeSet a) throws BadLocationException {
		if (str == null)
			return;
		if (getLength() + str.length() <= limit) {
			super.insertString(offs, str, a);
		} else {
		}
	}
}