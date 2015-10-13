package Personalizacion;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoadPictures extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPicServer = new JScrollPane();
	private JLabel lblPicservidor = new JLabel("");
	private JScrollPane scrollPicSesion = new JScrollPane();
	private JLabel lblPiciniciosesion = new JLabel("");
	private JLabel lblServidor = new JLabel("Imagen de Inicio del Servidor");
	private JLabel lblIniciosesion = new JLabel("Imagen de Inicio de Sesi\u00F3n");
	private JButton btnSerPic = new JButton("Cargar Nueva Imagen");
	private JButton btnSesPic = new JButton("CargarNuevaImagen");
	private JButton btnSerSave = new JButton("GuardarCambios");
	private JButton btnSesSave = new JButton("GuardarCambios");
	private JFileChooser choosePictures = new JFileChooser();

	/**
	 * Create the frame.
	 */
	public LoadPictures() {
		setTitle("Configurar Imagenes");
		setBounds(100, 100, 743, 468);
		getContentPane().setLayout(null);

		scrollPicServer.setBounds(10, 39, 350, 350);
		getContentPane().add(scrollPicServer);

		scrollPicServer.setViewportView(lblPicservidor);

		scrollPicSesion.setBounds(370, 39, 350, 350);
		getContentPane().add(scrollPicSesion);

		scrollPicSesion.setViewportView(lblPiciniciosesion);

		lblServidor.setBounds(10, 14, 214, 14);
		getContentPane().add(lblServidor);

		lblIniciosesion.setBounds(370, 14, 171, 14);
		getContentPane().add(lblIniciosesion);

		btnSerPic.addActionListener(new ActionListener() {
			/**
			 * Opción para cargar una nueva imagen en el inicio de sesión de
			 * servidor (non-Javadoc)
			 * 
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				choosePictures.showOpenDialog(scrollPicServer);
				String rute = choosePictures.getSelectedFile().getAbsolutePath();
				lblPicservidor.setIcon(new ImageIcon(rute));
			}
		});
		btnSerPic.setBounds(10, 402, 179, 28);
		getContentPane().add(btnSerPic);

		btnSesPic.setBounds(370, 402, 179, 28);
		getContentPane().add(btnSesPic);

		btnSerSave.setBounds(199, 402, 161, 28);
		getContentPane().add(btnSerSave);

		btnSesSave.setBounds(559, 400, 161, 28);
		getContentPane().add(btnSesSave);

	}
}
