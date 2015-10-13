package Options;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

/**
 * Clase para Obtener componentes modificados
 * 
 * @author luiiis lazaro
 * @category Complementos
 */
public final class ComponentsUser {

	/**
	 * Creación de un JtextField Modificado
	 * 
	 * @param limit
	 *            int --- longitud del campo
	 * @param typetxt
	 *            int --- tipo de caracteres aceptados 0 = onlyCharABC 1 =
	 *            onlyCharABCSpace 2 = onlyCharSpaceNumbers 3 =
	 *            onlyNumbersDoubles 4 = onlyINT
	 * @return txt --- JTextField con Formato preliminar
	 */
	public static JTextField getDataTxt(int limit, final int typetxt) {
		JTextField txt = new JTextField();
		txt.addKeyListener(new KeyListener() {

			/*
			 * @Override(non-Javadoc)
			 * 
			 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
			 */
			public void keyTyped(KeyEvent e) {
				switch (typetxt) {
				case 0:
					OptionsText.onlyChars(e.getKeyChar(), e);
					break;
				case 1:
					OptionsText.onlyCharsSpace(e.getKeyChar(), e);
					break;
				case 2:
					OptionsText.onlyCharsNumbers(e.getKeyChar(), e);
					break;
				case 3:
					OptionsText.onlyNumbersDouble(e.getKeyChar(), e);
					break;
				case 4:
					OptionsText.onlyINT(e.getKeyChar(), e);
					break;
				default:
					break;
				}
			}

			public void keyReleased(KeyEvent arg0) {
			}

			public void keyPressed(KeyEvent arg0) {
			}
		});
		txt.setDocument(new LenString(limit));

		return txt;
	}

	/**
	 * Creación de un JTextArea Modificado
	 * 
	 * @param limit
	 *            int --- longitud del campo
	 * @return textArea --- JTextArea con Formato preliminar
	 */
	public static JTextArea getDataJTextArea(int limit) {
		JTextArea textArea = new JTextArea();
		textArea.setDocument(new LenString(limit));
		textArea.setLineWrap(true);
		textArea.setBorder(new LineBorder(new Color(0, 0, 0), 1, false));
		return textArea;
	}

	/**
	 * Creación de un JScrollPane Modificado
	 * 
	 * @return scrollPane --- JScrollPane con Formato
	 */
	public static JScrollPane getDataJScrollPane() {
		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		return scrollPanel;
	}
}