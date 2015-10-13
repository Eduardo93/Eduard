package Options;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

import MetodosRemotos.Metodos;
import ObjetosSerializables.Aspecto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

public class VDescripcionCompletaDesgloseConcepto extends JInternalFrame {

	/**
	 * variables para el manejo de la ventana
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblClavePrivadaDel = new JLabel("Clave Privada:");
	private JLabel lblClavePblicaDel = new JLabel("Clave Pública:");
	private JLabel lblClvpri = new JLabel("");
	private JLabel lblClvpub = new JLabel("");
	private final JButton btnUpdate = new JButton("Actualizar");
	private JButton btnSave = new JButton("Guardar y Cerrar");
	private final JTextArea txtrMoredescription = new JTextArea();
	private Metodos conn;
	private int idAspecto;

	/**
	 * getConn Regresa la conexión activa para la base de datos
	 * 
	 * @return conn --- Métodos para la manipulación de la base de datos
	 */
	public Metodos getConn() {
		return conn;
	}

	/**
	 * setConn Asigna la Conexión activa para la manipulación de la base de
	 * datos
	 * 
	 * @param conn
	 *            --- Métodos
	 */
	public void setConn(Metodos conn) {
		this.conn = conn;
	}

	/**
	 * Get IdAspecto Retorna el Identificador del aspecto seleccionado
	 * 
	 * @return idAspecto int
	 */
	public int getIdAspecto() {
		return idAspecto;
	}

	/**
	 * SetIdAspecto Asigna el identificador del aspecto seleccionado de la lista
	 * de aspectos
	 * 
	 * @param idAspecto
	 *            int --- identificador del aspecto seleccionado
	 */
	public void setIdAspecto(int idAspecto) {
		this.idAspecto = idAspecto;
	}

	/**
	 * Create the frame.
	 */
	public VDescripcionCompletaDesgloseConcepto(final Metodos conn, int idAspecto) {
		setTitle("Comentarios Acerca del Aspecto");
		setResizable(true);
		// asignaciones de aspecto y conexciones
		this.setIdAspecto(idAspecto);
		this.setConn(conn);

		setClosable(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 414, 168);
		getContentPane().add(scrollPane);

		txtrMoredescription.setEditable(false);
		scrollPane.setViewportView(txtrMoredescription);

		btnSave.addActionListener(new ActionListener() {

			/**
			 * Sección para la parte de guardar la nueva descripción más
			 * completa del aspecto (non-Javadoc)
			 * 
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				getConn().updateDescriptionAspecto(getIdAspecto(), OptionsText.replaceString(txtrMoredescription.getText()));
				txtrMoredescription.setEditable(false);
				dispose();
			}
		});
		btnSave.setBounds(192, 232, 232, 31);
		getContentPane().add(btnSave);

		btnUpdate.addActionListener(new ActionListener() {
			/**
			 * Sección para la parte de acturalizar la nueva descripcíon del
			 * aspecto (non-Javadoc)
			 * 
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				txtrMoredescription.setEditable(true);
				txtrMoredescription.setFocusable(true);
				txtrMoredescription.requestFocusInWindow();
			}
		});
		btnUpdate.setBounds(10, 232, 120, 31);
		getContentPane().add(btnUpdate);

		lblClavePrivadaDel.setBounds(10, 11, 89, 14);
		getContentPane().add(lblClavePrivadaDel);

		lblClavePblicaDel.setBounds(10, 32, 89, 14);
		getContentPane().add(lblClavePblicaDel);

		lblClvpri.setBounds(94, 11, 266, 14);
		getContentPane().add(lblClvpri);

		lblClvpub.setBounds(94, 32, 266, 14);
		getContentPane().add(lblClvpub);

		FillDescription(getIdAspecto());
	}

	/**
	 * FillDescription --- muestra la descripción completa del aspecto
	 * selecionado en la lista de aspectos recibiendo el id como parametro para
	 * realizar la búsqueda en la base de datos
	 * 
	 * @param clave
	 *            String --- Clave del aspecto para ver la descripción más
	 *            completa que se tenga en el sistema
	 * @return description String --- descripción del aspecto
	 */
	public void FillDescription(int idAspecto) {
		Aspecto aspectoQuery = new Aspecto();
		lblClvpri.setText("");
		lblClvpub.setText("");
		txtrMoredescription.setText("");
		// conexión a la base de datos para recuperar la descripción del aspecto
		aspectoQuery = conn.getSeeMoreDescriptionAspecto(idAspecto);
		lblClvpri.setText(aspectoQuery.getClave());
		lblClvpub.setText(aspectoQuery.getClave_privada());
		txtrMoredescription.setText(aspectoQuery.getDescripcion_Completa());
	}
}