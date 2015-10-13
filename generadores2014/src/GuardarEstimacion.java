import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import MetodosRemotos.Metodos;
import ObjetosSerializables.Rgenerador;

/**
 * 
 * @author Rivera Pablo
 * @colaboración luiiis Lazaro
 *
 */
public class GuardarEstimacion extends javax.swing.JInternalFrame {

	/**
     * escritorio --- panel de la aplicación (menú principal)
     * partidas --- etiqueta para el número de partidas en la plantilla 
	 * guardar --- botón para guardar la plantilla 
	 * cancelar --- botón para cancelar la creación de la plantilla
	 * descripción --- área para la descripción de la plantilla
	 * Ldescripcion --- etiqueta para el campo de descripción
	 * aspectos --- etiqueta para el número de aspectos agregados en la plantilla
	 * conceptos --- etiqueta para el número de conceptos agregados en la plantilla
	 * Laspectos --- etiqueta para el titulo de número de aspectos
	 * Lconceptos --- etiqueta para el titulo de número de conceptos
	 * Lpartidas --- etiqueta para el titulo de número de partidas
	 * datos --- etiqueta para el titulo de información de la plantilla
	 * Pdatos --- panel del JInternalFrame
	 * lista --- lista de aspectos seleccionados en la plantilla 
	 *  
     */
	private static final long serialVersionUID = 1L;

	private JDesktopPane escritorio;
	private JLabel partidas;
	private JButton guardar;
	private JButton cancelar;
	private JTextArea descripcion;
	private JLabel Ldescripcion;
	private JLabel aspectos;
	private JLabel conceptos;
	private JLabel Laspectos;
	private JLabel Lconceptos;
	private JLabel Lpartidas;
	private JLabel datos;
	private JPanel Pdatos;
	private LinkedList < Rgenerador > lista;

	/**
	 * constructor de la clase para guardar una plantilla
	 * @param lista --- lista de aspectos seleccionados en la plantilla 
	 * @param con --- métodos para la manipulación de la base de datos
	 */
	public GuardarEstimacion ( LinkedList < Rgenerador > lista , Metodos con ) {
		super( "Plantillas ", false, true, false, true );
		try {
			javax.swing.UIManager.setLookAndFeel( "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel" );
		} catch ( Exception e ) {
			e.printStackTrace( );
		}
		initGUI( lista, con );
	}

	/**
	 * inicialización de los componentes gráficos
	 * @param lista --- lista de aspectos seleccionados en la plantilla 
	 * @param con --- métodos para la manipulación de la base de datos
	 */
	private void initGUI ( final LinkedList < Rgenerador > lista , final Metodos con ) {
		try {
			this.setPreferredSize( new java.awt.Dimension( 330, 329 ) );
			this.setBounds( 120, 60, 330, 329 );
			setVisible( true );
			
			escritorio = new JDesktopPane( );
			getContentPane( ).add( escritorio, BorderLayout.CENTER );
			
			this.lista = lista;

			Pdatos = new JPanel( );
			escritorio.add( Pdatos, JLayeredPane.DEFAULT_LAYER );
			Pdatos.setBounds( 0, 0, 318, 310 );
			Pdatos.setLayout( null );

			datos = new JLabel( );
			Pdatos.add( datos );
			datos.setText( "Datos de la Nueva Plantilla" );
			datos.setBounds( 49, 10, 194, 16 );
			datos.setBackground( new java.awt.Color( 255, 0, 0 ) );
			datos.setFont( new java.awt.Font( "Segoe UI", 1, 14 ) );
			
			Lpartidas = new JLabel( );
			Pdatos.add( Lpartidas );
			Lpartidas.setText( "Cantidad de Partidas:" );
			Lpartidas.setBounds( 59, 47, 122, 16 );
			
			Lconceptos = new JLabel( );
			Pdatos.add( Lconceptos );
			Lconceptos.setText( "Cantidad de Conceptos:" );
			Lconceptos.setBounds( 49, 74, 133, 16 );
			
			Laspectos = new JLabel( );
			Pdatos.add( Laspectos );
			Laspectos.setText( "Cant. de Desglose de Conceptos:" );
			Laspectos.setBounds( 10, 101, 186, 16 );
			
			partidas = new JLabel( );
			Pdatos.add( partidas );
			partidas.setBounds( 197, 45, 93, 16 );
			
			conceptos = new JLabel( );
			Pdatos.add( conceptos );
			conceptos.setBounds( 197, 73, 93, 16 );
			
			aspectos = new JLabel( );
			Pdatos.add( aspectos );
			aspectos.setBounds( 197, 101, 94, 16 );
			
			Ldescripcion = new JLabel( );
			Pdatos.add( Ldescripcion );
			Ldescripcion.setText( "Nombre de la Plantilla:" );
			Ldescripcion.setBounds( 15, 129, 203, 16 );
			Ldescripcion.setFont( new java.awt.Font( "SansSerif", 1, 12 ) );
			
			descripcion = new JTextArea( );
			Pdatos.add( descripcion );
			descripcion.setBounds( 15, 151, 297, 103 );
			descripcion.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );
			descripcion.setLineWrap( true );
			
			ImageIcon Imagendisco = new ImageIcon( getClass( ).getResource( "iconos/disco.png" ) );
			guardar = new JButton( );
			Pdatos.add( guardar );
			guardar.setIcon( Imagendisco );
			guardar.setText( "Guardar" );
			guardar.setBounds( 73, 260, 103, 30 );
			guardar.addActionListener( new ActionListener( ) {
				/**
				 * opción para guardar la información de la plantilla
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					boolean ban = false;
					if ( lista.size( ) > 0 ) {
						//se obtiene la fecha del sistema
						Calendar calendario = Calendar.getInstance( );
						int day = calendario.get( Calendar.DAY_OF_MONTH );
						int month = calendario.get( Calendar.MONTH );
						int year = calendario.get( Calendar.YEAR );
						String fecha = String.valueOf( year ) + "-" + String.valueOf( month ) + "-" + String.valueOf( day );
						if ( descripcion.equals( "" ) == true ) {
							JOptionPane.showMessageDialog( null, "No has agregado la descripción de la plantilla" );
						} else {
							int id = 0;
							id = con.InsertarPlantilla( descripcion.getText( ), fecha );
							if ( id > 0 ) {
								String asp = "";
								Rgenerador pe = new Rgenerador( );
								for ( int i = 0 ; i < lista.size( ) ; i++ ) {
									pe = ( Rgenerador ) lista.get( i );
									if ( pe.getIdaspecto( ).equals( asp ) == false ) {
										ban = con.Insetaraspectospalntillas( String.valueOf( id ), pe.getIdaspecto( ) );
										asp = pe.getIdaspecto( );
									}
								}
							}
							if ( ban == true ) {
								JOptionPane.showMessageDialog( null, "La plantilla a sido creada con exito" );
							}
							dispose( );
						}
					}
				}
			} );
			
			ImageIcon Imagencancelar = new ImageIcon( getClass( ).getResource( "iconos/cancelar.png" ) );
			cancelar = new JButton( );
			Pdatos.add( cancelar );
			cancelar.setIcon( Imagencancelar );
			cancelar.setText( "Cancelar" );
			cancelar.setBounds( 200, 260, 103, 30 );
			cancelar.addActionListener( new ActionListener( ) {
				/**
				 * opción para cancelar la creación de la plantilla
				 * sólo cierra la ventana y no pasa nada
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					dispose( );
				}
			} );
			llenarDatos( );
		} catch ( Exception e ) {
			e.printStackTrace( );
		}
	}

	/**
	 * Método para llenar el número de partidas, conceptos y aspectos en la plantilla
	 */
	public void llenarDatos ( ) {
		Rgenerador da;
		int i = 0, p = 0, c = 0, a = 0;
		String partida = " ", ap = " ", con = " ";
		while ( i < lista.size( ) ) {
			da = ( Rgenerador ) lista.get( i );
			if ( da.getPartida( ).equals( partida ) == false ) {
				p++;
				partida = da.getPartida( );
			}

			if ( da.getIdconcepto( ).equals( con ) == false ) {
				c++;
				con = da.getIdconcepto( );
			}

			if ( da.getIdaspecto( ).equals( ap ) == false ) {
				a++;
				ap = da.getIdaspecto( );
			}

			i++;
		}
		partidas.setText( String.valueOf( p ) );
		aspectos.setText( String.valueOf( a ) );
		conceptos.setText( String.valueOf( c ) );
	}
}
