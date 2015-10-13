import java.awt.BorderLayout;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import MetodosRemotos.Metodos;
import ObjetosSerializables.Rgenerador;
import ObjetosSerializables.Rutas;

/**
 * Clase para cargar y guardar las direcciones de fotografías de los conceptos
 * realizados en la estimación
 * 
 * @author Pablo Rivera
 * @colaboración luiiis Lazaro
 * @access {@docRoot}
 */
public class Cargarimagen extends javax.swing.JInternalFrame {

	/**
	 * variables: escritorio --- panel de la aplicación (menú principal) panel
	 * --- panel del JInternalFrame Pfotodos --- scroll para el área de la
	 * fotografía dos Pfotouno --- scrol para el área de la fotografía uno
	 * fotodos --- botón para seleccionar la foto dos fotouno --- botón para
	 * seleccionar la foto uno lblUno --- etiqueta texto foto uno lblDos ---
	 * etiqueta texto foto dos rutauno,rutados, rutaunoi, rutadosi --- cadenas
	 * auxiliares para las direcciones de las fotografías
	 */
	private static final long serialVersionUID = 1L;
	private JDesktopPane escritorio;
	private JPanel panel;
	private JScrollPane Pfotodos;
	private JButton guardar;
	private JButton fotodos;
	private JButton fotouno;
	private JLabel lblDos;
	private JLabel lbUno;
	private JScrollPane Pfotouno;
	private String rutauno = "", rutados = "", rutaunoi = "", rutadosi = "";

	/**
	 * Constructor de la Clase para iniciar compoentes gráficos
	 * 
	 * @param cone
	 *            --- Metodos para relizar la manipulación de la base de datos
	 * @param ob
	 *            ---
	 * @param idestimacion
	 *            --- Identificadore de la estimación para recuperar imágenes si
	 *            tiene
	 * @param idpartida
	 *            --- Identificador de la partida *** no se utiliza en esta
	 *            clase
	 * @param ru
	 *            --- Objeto para guardar las direcciones de la imágenes
	 * @param fechaestimacion
	 *            --- Fecha de la realización de la estimación
	 */
	public Cargarimagen(Metodos cone, Rgenerador ob, int idestimacion, int idpartida, Rutas ru, String fechaestimacion) {
		super("Cargar Fotograf\u00EDas", false, true, false, true);
		initGUI(cone, ob, idestimacion, idpartida, ru, fechaestimacion);
	}

	/**
	 * Inicio de los componentes gráficos de la ventana para cargar las imagenes
	 * (fotografías)
	 * 
	 * @param cone
	 *            --- Metodos para relizar la manipulación de la base de datos
	 * @param ob
	 *            ---
	 * @param idestimacion
	 *            --- Identificadore de la estimación para recuperar imágenes si
	 *            tiene
	 * @param idpartida
	 *            --- Identificador de la partida *** no se utiliza en esta
	 *            clase
	 * @param ru
	 *            --- Objeto para guardar las direcciones de la imágenes
	 * @param fechaestimacion
	 *            --- Fecha de la realización de la estimación
	 */
	private void initGUI(final Metodos cone, final Rgenerador ob, final int idestimacion, final int idpartida, Rutas ru, final String fechaestimacion) {
		try {
			this.setPreferredSize(new java.awt.Dimension(746, 405));
			this.setBounds(0, 0, 746, 405);
			setVisible(true);

			escritorio = new JDesktopPane();
			getContentPane().add(escritorio, BorderLayout.CENTER);

			panel = new JPanel();
			escritorio.add(panel, JLayeredPane.DEFAULT_LAYER);
			panel.setBounds(0, 0, 744, 380);
			panel.setLayout(null);

			Pfotouno = new JScrollPane();
			panel.add(Pfotouno);
			Pfotouno.setBounds(34, 37, 323, 247);
			// si existen rutas en el objeto ru, se cargar las imágenes con su
			// respectiva ruta
			if (ru.getUno() != null) {
				JLabel etiqueta = new JLabel();
				Icon imagen = new ImageIcon(ru.getUno());
				etiqueta.setIcon(imagen);
				Pfotouno.setViewportView(etiqueta);
				rutauno = ru.getUno();
			}

			Pfotodos = new JScrollPane();
			panel.add(Pfotodos);
			Pfotodos.setBounds(390, 37, 324, 247);
			// si existen rutas en el objeto ru, se cargar las imágenes con su
			// respectiva ruta
			if (ru.getDos() != null) {
				JLabel etiqueta = new JLabel();
				Icon imagen = new ImageIcon(ru.getDos());
				etiqueta.setIcon(imagen);
				Pfotodos.setViewportView(etiqueta);
				rutados = ru.getDos();
			}

			lbUno = new JLabel();
			panel.add(lbUno);
			lbUno.setText("Fotograf\u00EDa Uno");
			lbUno.setBounds(34, 12, 110, 16);

			lblDos = new JLabel();
			panel.add(lblDos);
			lblDos.setText("Fotograf\u00EDa Dos");
			lblDos.setBounds(397, 12, 114, 16);

			ImageIcon Imagenabrir = new ImageIcon("C:/Users/pabluchis/workspace/Generadores/src/iconos/abrir.png");
			fotouno = new JButton();
			panel.add(fotouno);
			fotouno.setIcon(Imagenabrir);
			fotouno.setText("Cargar Foto 1");
			fotouno.setBounds(122, 297, 133, 30);
			fotouno.addActionListener(new ActionListener() {
				/**
				 * Cargar la Primera imágene en la ventana (non-Javadoc)
				 * 
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					JFileChooser navega = new JFileChooser();
					navega.showOpenDialog(Pfotouno);
					rutauno = navega.getSelectedFile().getAbsolutePath();
					JLabel etiqueta = new JLabel();
					etiqueta.setIcon(new ImageIcon(rutauno));
					Pfotouno.setViewportView(etiqueta);
					guardar.setEnabled(true);
				}
			});

			// ImageIcon Imagenabrir = new
			// ImageIcon("C:/Users/pabluchis/workspace/Generadores/src/iconos/abrir.png");
			fotodos = new JButton();
			panel.add(fotodos);
			fotodos.setIcon(Imagenabrir);
			fotodos.setText("Cargar Foto 2");
			fotodos.setBounds(518, 295, 141, 30);
			fotodos.addActionListener(new ActionListener() {
				/**
				 * Cargar la segunda imágen en la ventana (non-Javadoc)
				 * 
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					JFileChooser navega = new JFileChooser();
					navega.showOpenDialog(Pfotodos);
					rutados = navega.getSelectedFile().getAbsolutePath();
					JLabel etiqueta = new JLabel();
					Icon imagen = new ImageIcon(rutados);
					etiqueta.setIcon(imagen);
					Pfotodos.setViewportView(etiqueta);
					guardar.setEnabled(true);
				}
			});

			ImageIcon Imagendisco = new ImageIcon("C:/Users/pabluchis/workspace/Generadores/src/iconos/disco.png");
			guardar = new JButton();
			guardar.setEnabled(false);
			panel.add(guardar);
			guardar.setIcon(Imagendisco);
			guardar.setText("Guardar Fotograf\u00EDas");
			guardar.setBounds(282, 331, 181, 30);
			guardar.addActionListener(new ActionListener() {
				/**
				 * Guardar las Imágenes Seleccionadas (non-Javadoc)
				 * 
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					if (concatenarEspecial(rutados, rutadosi).equals("") == false && concatenarEspecial(rutauno, rutaunoi).equals("") == false) {
						try {
							// se guardan en la base de datos las direcciones de
							// las imágenes
							if (cone.aspectofoto(ob.getIdaspecto(), String.valueOf(idestimacion), ob.getCantidad(), ob.getImporte(), ob.getX(), ob.getY(), ob.getZ(), ob.getAlto(), ob.getLargo(), ob.getAncho(), ob.getCosto(), String.valueOf(idpartida), String.valueOf(ob.getRepeticion()), rutaunoi, rutadosi, fechaestimacion) == true) {
								JOptionPane.showMessageDialog(null, "Éxito al cargar las imagenes");
								dispose();
							}
						} catch (HeadlessException e1) {
							JOptionPane.showMessageDialog(null, "Servidor fuera de servicio (Error al obtener consultar fotos ) ", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Debes agregar las dos imagenes");
					}
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para escapar las barras especiales de la dirección de las imágenes
	 */
	public String concatenarEspecial(String ruta, String rutaBD) {
		for (int i = 0; i < ruta.length(); i++) {
			char letra = ruta.charAt(i);
			if (letra == '\\') {
				rutaBD += letra;
				rutaBD += '\\';
			}
			rutaBD += letra;
		}
		return rutaBD;
	}
}
