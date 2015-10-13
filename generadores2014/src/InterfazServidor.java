import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import MetodosRemotos.Metodos;

/**
 * Interfaz para la conexion al servidor de la base de datos
 * 
 * @author Rivera Pablo
 * @colaboración luiiis lazaro
 * @version 1.0
 * @descripcion proyecto de vinculación Arquitectos
 * @access {@docRoot}
 */
public class InterfazServidor extends javax.swing.JFrame {

	private final static Logger log = Logger.getLogger(InterfazServidor.class);
	
	private static final long serialVersionUID = 1L;
	private JDesktopPane escritorio;
	private JPanel Panelimagen;
	private JLabel icono;
	private JButton iniciar;
	private JTextField usuario;
	private JPasswordField contra;
	private JButton teminar;
	private JLabel pass;
	private JLabel login;
	private JLabel titulo;
	private JLabel ldireccion;
	private Metodos metodos;
	private JTextField direccion;

	/**
	 * Constructor de la clase llamada a la inicialización de los componentes
	 * gráficos
	 */
	public InterfazServidor ( ) {
		setResizable(false);// Constructor
		//configuración para el archivo log
		PropertyConfigurator.configure("log4j.properties");
		initGUI( );
		
		log.info("Run...");
	}

	/**
	 * inicializa los componentes gï¿½ficos
	 */
	private void initGUI ( ) {// inicio de la ventana
		try {
			javax.swing.UIManager.setLookAndFeel( "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel" );
		} catch ( Exception e ) {
			e.printStackTrace( );
		}

		try {
			this.addWindowListener( new WindowAdapter( ) {// cerrando la ventana

				public void windowClosing ( WindowEvent e ) {
					System.exit( 0 );
					metodos.cerrar( );
				}
			} );

			this.setTitle( "Control de Proyectos" );
			setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
			metodos = new Metodos( );// conexiones a la base de datos
			escritorio = new JDesktopPane( );
			getContentPane( ).add( escritorio, BorderLayout.SOUTH );
			escritorio.setPreferredSize( new java.awt.Dimension( 422, 250 ) );

			titulo = new JLabel( );
			escritorio.add( titulo, JLayeredPane.DEFAULT_LAYER );
			titulo.setText( "BD Sys Gerencial" );
			titulo.setBounds( 53, 7, 236, 45 );
			titulo.setFont( new java.awt.Font( "SansSerif", 1, 20 ) );
			titulo.setForeground( new java.awt.Color( 0, 255, 0 ) );

			Panelimagen = new JPanel( );
			escritorio.add( Panelimagen, JLayeredPane.DEFAULT_LAYER );
			Panelimagen.setBounds( 273, 7, 130, 111 );
			Panelimagen.setLayout( null );
			Panelimagen.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );

			icono = new JLabel( );
			Panelimagen.add( icono );
			icono.setIcon( new ImageIcon( getClass( ).getResource( "iconos/logo-sys.jpg" ) ) );
			icono.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );
			icono.setBounds( 13, 10, 105, 92 );

			iniciar = new JButton( );
			escritorio.add( iniciar, JLayeredPane.DEFAULT_LAYER );
			iniciar.setText( "Conectar" );
			iniciar.setBounds( 59, 200, 138, 28 );
			iniciar.addActionListener( new ActionListener( ) {
				/**
				 * Conectar con el servidor de la base de datos
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					iniciarConexion();
				}
			} );

			login = new JLabel( );
			escritorio.add( login, JLayeredPane.DEFAULT_LAYER );
			login.setText( "Usuario BD:" );
			login.setBounds( 31, 63, 102, 29 );
			login.setFont( new java.awt.Font( "SansSerif", 1, 16 ) );
			login.setForeground( new java.awt.Color( 0, 0, 255 ) );

			pass = new JLabel( );
			escritorio.add( pass, JLayeredPane.DEFAULT_LAYER );
			pass.setText( "Password BD:" );
			pass.setBounds( 15, 104, 120, 16 );
			pass.setFont( new java.awt.Font( "SansSerif", 1, 16 ) );
			pass.setForeground( new java.awt.Color( 0, 0, 255 ) );

			usuario = new JTextField( );
			usuario.setText( "root" );
			escritorio.add( usuario, JLayeredPane.DEFAULT_LAYER );
			usuario.setBounds( 135, 58, 105, 31 );

			contra = new JPasswordField( );
			contra.setText("123456");
			escritorio.add( contra, JLayeredPane.DEFAULT_LAYER );
			contra.setBounds( 135, 100, 105, 31 );

			teminar = new JButton( );
			escritorio.add( teminar, JLayeredPane.DEFAULT_LAYER );
			teminar.setText( "Cancelar" );
			teminar.setBounds( 244, 200, 148, 28 );

			ldireccion = new JLabel( );
			ldireccion.setText( "Dirección:" );
			ldireccion.setForeground( Color.BLUE );
			ldireccion.setFont( new Font( "SansSerif", Font.BOLD, 16 ) );
			ldireccion.setBounds( 53, 143, 120, 16 );
			escritorio.add( ldireccion );

			direccion = new JTextField( );
			direccion.setText( "localhost:3307" );
			direccion.setBounds( 135, 139, 116, 31 );
			escritorio.add( direccion );
			direccion.setColumns( 10 );

			teminar.addActionListener( new ActionListener( ) {
				/**
				 * cerrar o salir del sistema 
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					try {
						metodos.cerrar( );
						dispose( );
					} catch ( Exception h ) {
						dispose( );
					}
				}
			} );

			pack( );
			this.setSize( 440, 295 );

		} catch ( Exception e ) {
			e.printStackTrace( );
		}
		usuario.requestFocusInWindow( );
	}

	/**
	 * Inicia conexión con la base de datos 
	 * @param e evento clic 
	 */
	public void iniciarConexion () {
		// Verficación de los parámetros para realizar la conexión a la base de
		// datos
		if ( usuario.getText( ).isEmpty( ) == false && contra.getPassword( ).toString( ).isEmpty( ) == false && direccion.getText( ).isEmpty( ) == false ) {
			metodos.setDirecccion( direccion.getText( ) );
			if ( metodos.Conectar( usuario.getText( ), contra.getPassword( ) ) == true ) {
				this.setVisible( false );
				Principal pricipal = new Principal( metodos );
				pricipal.setVisible( true );
				pricipal.setExtendedState( MAXIMIZED_BOTH );
			}
		} else {
			JOptionPane.showConfirmDialog( null, "Faltan Parámetros de Conexión", "Atención", JOptionPane.WARNING_MESSAGE );
			this.setVisible( true );
		}
	}
}