// CLASE para la ventana de inicio de sesión...
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


// import java.rmi.RemoteException;
import javax.swing.*;

import MetodosRemotos.Metodos;
import Options.OptionsText;

import java.awt.Color;

/**
 * Clase para el inicio de sesiï¿½n en el sistema
 * 
 * @author Rivera Pablo
 * @colaboración luiiis lazaro
 * @version 1.0
 * @descripcion proyecto de vinculaciï¿½n Arquitectos
 * @access {@docRoot}
 */
public class Logeo extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JButton cancelar;// cancelar
	private JButton aceptar;// aceptar
	private JTextField Tclave;// usuario
	private JPasswordField Tpass;// contraseï¿½a
	private JLabel pass;// etiqueta
	private JLabel login;// etiqueta
	private JPanel Pimagen;// logo
	private JDesktopPane jDesktopPane1;// panel
	private String verificar;// tipo de usuario en la base de datos
	private JPanel panel;

	/**
	 * Contructor de la clase para incio de sesiï¿½n al sistema
	 * 
	 * @param conexion para las operaciones a la base de datos
	 * @param jMenu2 bloqueo o desbloqueo de menï¿½ para opciï¿½n
	 * @param usuarios bloqueo o desbloqueo de menï¿½ para opciï¿½n
	 * @param proyectos bloqueo o desbloqueo de menï¿½ para opciï¿½n
	 * @param jMenu1 bloqueo o desbloqueo de menï¿½ para opciï¿½n
	 * @param avaluar bloqueo o desbloqueo de menï¿½ para opciï¿½n
	 * @see Principal.java
	 */
	Logeo ( final Metodos conexion , final JMenu jMenu2 , final JMenu usuarios , final JMenu proyectos , final JMenu jMenu1 , final JMenu avaluar ) {
		super( "Inicio de Sesión", false, true, false, true );
		this.setBounds( 260, 59, 577, 333 );
		this.setVisible( true );
		getContentPane().setLayout(null);

		jDesktopPane1 = new JDesktopPane( );
		jDesktopPane1.setBounds(0, 1, 561, 302);
		jDesktopPane1.setBackground( Color.WHITE );
		getContentPane( ).add( jDesktopPane1 );
		jDesktopPane1.setPreferredSize( new java.awt.Dimension( 417, 302 ) );
		jDesktopPane1.setLayout(null);

		Pimagen = new JPanel( );
		Pimagen.setBounds(348, 21, 101, 90);
		JLabel icono = new JLabel( );
		Pimagen.add( icono, BorderLayout.CENTER );
		icono.setIcon( new ImageIcon( getClass( ).getResource( "iconos/logo-sys.jpg" ) ) );

		jDesktopPane1.add( Pimagen );

		login = new JLabel( );
		login.setBounds(315, 136, 70, 16);
		jDesktopPane1.add( login );
		login.setText( " Usuario" );
		login.setFont( new java.awt.Font( "SansSerif", 0, 16 ) );

		pass = new JLabel( );
		pass.setBounds(291, 179, 101, 16);
		jDesktopPane1.add( pass );
		pass.setText( "Contrase\u00F1a" );
		pass.setFont( new java.awt.Font( "SansSerif", 0, 16 ) );

		Tpass = new JPasswordField( );
		Tpass.setText("12345");
		Tpass.setBounds(392, 173, 152, 28);
		jDesktopPane1.add( Tpass );

		Tpass.addKeyListener( new KeyListener( ) {

			/**
			 * @see
			 * java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
			 * @Override(non-Javadoc)
			 */
			public void keyTyped ( KeyEvent e ) {
				OptionsText.onlyCharsNumbers( e.getKeyChar( ), e );
			}

			public void keyReleased ( KeyEvent e ) {
			}

			public void keyPressed ( KeyEvent e ) {
			}
		} );

		Tclave = new JTextField( );
		Tclave.setText("Arturo");
		Tclave.setBounds(392, 133, 152, 28);
		jDesktopPane1.add( Tclave );

		Tclave.addKeyListener( new KeyListener( ) {// listener para validar caracteres presionados no permite cosas raras '#
			/**
			 * @see
			 * java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
			 * @Override(non-Javadoc)
			 */
			public void keyTyped ( KeyEvent e ) {
				/**
				 * validacion de caracteres no validos en el campos de nombre de
				 * usuario
				 */
				OptionsText.onlyChars( e.getKeyChar( ), e );
			}

			public void keyPressed ( KeyEvent e ) {
			}

			public void keyReleased ( KeyEvent e ) {
			}
		} );// fin de listener

		OptionsText.disablePasteAndCopyAction( ( JComponent ) Tpass );
		OptionsText.disablePasteAndCopyAction( ( JComponent ) Tclave );

		/*
		 * para fines prï¿½cticos de pruebas e inicios de sesiï¿½n
		 */

		aceptar = new JButton( );
		aceptar.setBounds(291, 233, 95, 28);
		jDesktopPane1.add( aceptar );
		aceptar.setText( "Aceptar" );
		aceptar.addActionListener( new ActionListener( ) {
			/**listener para el boton de ingresar
			 * (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed ( ActionEvent e ) {
				if ( Tclave.getText( ).isEmpty( ) || new String( Tpass.getPassword( ) ).equals( new String( "" ) ) == true ) {
					JOptionPane.showMessageDialog( null, "uno de los campos esta vacio", "verifica", JOptionPane.WARNING_MESSAGE );
				} else {
					verificar = conexion.verificarusuario( Tclave.getText( ), Tpass.getPassword( ) );// si el usuario existe
					System.out.println(verificar);
					if ( verificar.equals( "nada" ) == false ) {// confirma la respuesta
						switch (verificar) {
						case "U":
							jMenu2.setEnabled( false );
							usuarios.setEnabled( false );
							proyectos.setEnabled( true );
							jMenu1.setEnabled( true );
							avaluar.setEnabled( true );
							break;
						case "A":
							jMenu2.setEnabled( true );
							usuarios.setEnabled( true );
							proyectos.setEnabled( true );
							jMenu1.setEnabled( true );
							avaluar.setEnabled( true );
						default:
							break;
						}// fin option --- yes
						dispose( ); /* conexion.cerrar(); */
					} else {
						JOptionPane.showMessageDialog( null, "Usuario no valido" );
						jMenu2.setEnabled( false );
						usuarios.setEnabled( false );
						proyectos.setEnabled( false );
						jMenu1.setEnabled( false );
						avaluar.setEnabled( false );
					}// fin de verificar nada == false
				}// fin de campos vacios en la ventana
			}// fin de action performed
		} );// fin de action Listener

		ImageIcon Imagencancelar = new ImageIcon( "C:/Users/pabluchis/workspace/Generadores/src/iconos/cancelar.png" );
		cancelar = new JButton( );
		cancelar.setBounds(441, 233, 110, 28);
		jDesktopPane1.add( cancelar );
		cancelar.setIcon( Imagencancelar );
		cancelar.setText( "Cancelar" );
		
		panel = new JPanel();
		panel.setBounds(10, 11, 271, 280);
		jDesktopPane1.add(panel);
		panel.setLayout(null);
		
		JScrollPane SPPub = new JScrollPane();
		SPPub.setBounds(0, 0, 271, 280);
		panel.add(SPPub);
		
		JLabel lblPub = new JLabel("");
		lblPub.setHorizontalAlignment(SwingConstants.CENTER);
		lblPub.setToolTipText("Espacio para Publicidad aqu\u00ED");
		lblPub.setLabelFor(login);
		lblPub.setIcon(new ImageIcon(Logeo.class.getResource("/iconos/icon.png")));
		SPPub.setViewportView(lblPub);
		
		cancelar.addActionListener( new ActionListener( ) {
			/**
			 * (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed ( ActionEvent e ) {
				dispose( );
				// conexion.cerrar();
			}
		} );
		Tclave.requestFocusInWindow( );
	}// fin de constructor
}