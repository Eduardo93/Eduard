import java.awt.BorderLayout;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import MetodosRemotos.Metodos;
import ObjetosSerializables.Partida;
import ObjetosSerializables.Plantilla;
import ObjetosSerializables.Rgenerador;
import Tablaplantillas.ControlPlantilla;
import Tablaplantillas.ModeloPlantilla;

/**
 * clase para elegir la plantilla y crear un proyecto con sus conceptos
 * @author Pablo Rivera
 * @colaboración luiiis Lazaro
 * @access{@docRoot}
 */
public class Elegirplantilla extends javax.swing.JInternalFrame {

	/**
     * escritorio --- panel de la aplicación (menú principal)
     * pplantilla --- panel del JInternalFrame
     * Splantillas --- scroll para mostrar las platillas almacenadas en el sistema
     * modelo --- modelo para manipular la información de la tabla plantillas (desde la base de datos)
     * control --- control para la manipulación del modelo de las plantillas
     * eliminar --- botón para eliminar la plantilla seleccionada
     * etiqueta --- etiqueta para mostrar detalle de los conceptos
     * Scosas --- scroll para mostrar los conceptos relacionados con el nombre de la plantilla
     * tinformacion --- área donde se muestran los conceptos de la plantilla
     * indice --- auxiliar para obtener el ID de la plantilla seleccionada
     * Lpartidas --- lista de partidas contenidas en la plantilla
     * Lps --- lista de los conceptos contenidos en la partida de la plantilla
     * 
     */
	private static final long serialVersionUID = 1L;
	private JDesktopPane escritorio;
	private JPanel Pplantilla;
	private JScrollPane Splantillas;
	private JTable Tplantillas;
	private ModeloPlantilla modelo = new ModeloPlantilla( );
	private ControlPlantilla control = new ControlPlantilla( modelo );
	private JButton eliminar;
	private JLabel etiqueta;
	private JButton aceptar;
	private JScrollPane Scosas;
	private JTextPane Tinformacion;
	private String indice = "0";
	private LinkedList<Partida> Lpartidas = new LinkedList<Partida>( );
	private LinkedList<Rgenerador> Lps = new LinkedList<Rgenerador>( );

	/**
	 * Constructor de la clase
	 * @param lista --- lista de plantillas 
	 * @param con --- metodos para la manipulación de la base de datos
	 * @param idestimacion --- identificador de la estimación 
	 * @param fecha --- fecha de creación de la estimación 
	 */
	public Elegirplantilla ( LinkedList<Plantilla> lista , Metodos con , int idestimacion , String fecha ) {
		super( "Seleccionar una Plantilla", false, true, false, true );
		initGUI( lista, con, idestimacion, fecha );
	}

	/**
	 * inicialización de componentes gráficos
	 * @param lista --- lista de plantillas 
	 * @param con --- metodos para la manipulación de la base de datos
	 * @param idestimacion --- identificador de la estimación 
	 * @param fecha --- fecha de creación de la estimación
	 */
	private void initGUI ( final LinkedList<Plantilla> lista , final Metodos con , final int estimacion , final String fecha ) {
		try {
			this.setPreferredSize( new java.awt.Dimension( 560, 459 ) );
			this.setBounds( 0, 0, 560, 459 );
			setVisible( true );
			
			escritorio = new JDesktopPane( );
			getContentPane( ).add( escritorio, BorderLayout.CENTER );
			Lpartidas = con.getPartidas( );
			Pplantilla = new JPanel( );
			escritorio.add( Pplantilla, JLayeredPane.DEFAULT_LAYER );
			Pplantilla.setBounds( 0, 0, 558, 434 );
			Pplantilla.setLayout( null );
			
			Splantillas = new JScrollPane( );
			Pplantilla.add( Splantillas );
			Splantillas.setBounds( 0, 1, 558, 160 );
			
			etiqueta = new JLabel( );
			Pplantilla.add( etiqueta );
			etiqueta.setText( "Contenido de la plantilla" );
			etiqueta.setBounds( 6, 164, 159, 16 );
			etiqueta.setFont( new java.awt.Font( "Segoe UI", 1, 12 ) );
		
			Scosas = new JScrollPane( );
			Pplantilla.add( Scosas );
			Scosas.setBounds( 12, 192, 534, 200 );
			
			Tinformacion = new JTextPane( );
			Scosas.setViewportView( Tinformacion );
			Tinformacion.setBounds( 4, 337, 375, 50 );
			Tinformacion.setPreferredSize( new java.awt.Dimension( 531, 187 ) );
			Tinformacion.setEditable( false );
			
			ImageIcon Imagenaceptar = new ImageIcon( getClass( ).getResource( "iconos/guardar.png" ) );
			aceptar = new JButton( );
			Pplantilla.add( aceptar );
			aceptar.setIcon( Imagenaceptar );
			aceptar.setText( "Aceptar" );
			aceptar.setBounds( 150, 398, 103, 30 );
			aceptar.addActionListener( new ActionListener( ) {
				/**
				 * Confirmar la seleccion de la plantilla para cargar sus conceptos.
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					boolean ban = false;
					if ( Lps.size( ) == 0 && Lps == null ) {
						JOptionPane.showMessageDialog( null, "No has seleccionado una plantilla" );
					} else {
						Rgenerador g = new Rgenerador( );
						Rgenerador asp;
						for ( int i = 0 ; i < Lps.size( ) ; i++ ) {
							g = ( Rgenerador ) Lps.get( i );
							asp = new Rgenerador( );
							asp.setPartida( g.getPartida( ) );
							asp.setIdaspecto( String.valueOf( g.getIdaspecto( ) ) );
							asp.setClave( g.getClave( ) );
							asp.setDescripcion( "" );
							asp.setUnidad( g.getUnidad( ) );
							asp.setX( "0" );
							asp.setY( "0" );
							asp.setZ( "0" );
							asp.setAlto( "0" );
							asp.setLargo( "0" );
							asp.setAncho( "0" );
							asp.setCantidad( "0" );
							asp.setPiezas( "0" );
							asp.setCosto( g.getCosto( ) );
							asp.setImporte( "0" );
							asp.setRepeticion( 1 );
							ban = con.estimacionaspecto( g.getIdaspecto( ), String.valueOf( estimacion ), asp.getCantidad( ), asp.getImporte( ), asp.getX( ), asp.getY( ), asp.getZ( ), asp.getAlto( ), asp.getLargo( ), asp.getAncho( ), asp.getCosto( ), asp.getPartida( ), String.valueOf( asp.getRepeticion( ) ), fecha );
						}	
					}
					if ( estimacion > -1 && ban == true ) {
						JOptionPane.showMessageDialog( null, "La plantilla  se a cargo con éxito" );
						dispose( );
					}
				}
			} );
		
			ImageIcon Imageneliminar = new ImageIcon( getClass( ).getResource( "iconos/eliminar.png" ) );
			eliminar = new JButton( );
			eliminar.setIcon( Imageneliminar );
			Pplantilla.add( eliminar );
			eliminar.setText( "Eliminar Plantilla" );
			eliminar.setBounds( 288, 395, 169, 33 );
			eliminar.addActionListener( new ActionListener( ) {
				/**
				 * opción para eliminar la plantilla seleccionada
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					if ( Lps.size( ) == 0 && Lps == null ) {
						JOptionPane.showMessageDialog( null, "No has seleccionado ninguna plantilla" );
					} else {
						int repu = JOptionPane.showConfirmDialog( null, "Deseas eliminar esta plantilla", "Confirmar", JOptionPane.YES_NO_OPTION );
						if ( repu == JOptionPane.YES_OPTION ) {
							try {
								//borra la plantilla de la DB
								if ( con.eliminarplantilla( indice ) ) {
									JOptionPane.showMessageDialog( null, "La plantilla se elimino con éxito" );
									//actualiza la información en el catálogo de plantillas
									Tinformacion.setText( "" );
									control.borraFila( Tplantillas.getSelectionModel( ).getLeadSelectionIndex( ) );
								} else {
									JOptionPane.showMessageDialog( null, "Error al eliminar plantilla", "Error", JOptionPane.WARNING_MESSAGE );
								}
							} catch ( HeadlessException e1 ) {
								JOptionPane.showMessageDialog( null, "Servidor fuera de servicio (Error insertar al eliminar plantilla) ", "Error", JOptionPane.ERROR_MESSAGE );
								
							}
						}
					}
					
				}
			} );
		
			Tplantillas = new JTable( modelo );
			Splantillas.setViewportView( Tplantillas );
			Tplantillas.addMouseListener( new java.awt.event.MouseAdapter( ) {
				/**
				 * listener para cargar la información de conceptos de la plantilla cuando se le da clic al nombre
				 * (non-Javadoc)
				 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
				 */
				public void mouseClicked ( java.awt.event.MouseEvent e ) {
					if ( e.isMetaDown( ) ) {
					} else {
						if ( e.isAltDown( ) == false ) {
							Plantilla p = new Plantilla( );
							Rgenerador g = new Rgenerador( );
							Rgenerador asp;
							int p1 = 0;
							p = ( Plantilla ) lista.get( Tplantillas.getSelectionModel( ).getLeadSelectionIndex( ) );
							indice = p.getIdplantilla( );
							Lps = con.Regresarplantilla( String.valueOf( indice ) );
							if ( Lps.size( ) == 0 ) {
								JOptionPane.showMessageDialog( null, "La plantilla no tiene ningun  aspecto" );
							} else {
								Tinformacion.setText( "" );
								String cpar = "";
								String ccon = "";
								String casp = "";
								for ( int i = 0 ; i < Lps.size( ) ; i++ ) {
									g = ( Rgenerador ) Lps.get( i );
									asp = new Rgenerador( );
									asp.setPartida( g.getPartida( ) );
									asp.setIdaspecto( String.valueOf( g.getIdaspecto( ) ) );
									asp.setClave( g.getClave( ) );
									asp.setDescripcion( "" );
									asp.setUnidad( g.getUnidad( ) );
									asp.setX( "0" );
									asp.setY( "0" );
									asp.setZ( "0" );
									asp.setAlto( "0" );
									asp.setLargo( "0" );
									asp.setAncho( "0" );
									asp.setCantidad( "0" );
									asp.setPiezas( "0" );
									asp.setCosto( g.getCosto( ) );
									asp.setImporte( "0" );
									asp.setRepeticion( 1 );
									if ( cpar.equals( g.getPartida( ) ) == false ) {
										for ( p1 = 0 ; p1 < Lpartidas.size( ) ; p1++ ) {
											Partida par = ( Partida ) Lpartidas.get( p1 );
											if ( par.getIdpartida( ) == Integer.parseInt( g.getPartida( ) ) ) {
												Tinformacion.setText( Tinformacion.getText( ) + par.getIdpartida( ) + ".-" + par.getNombre( ) + "\n" );												
											}
										}	
										cpar = g.getPartida( );
									}
									if ( ccon.equals( g.getNombre( ) ) == false ) {
										Tinformacion.setText( Tinformacion.getText( ) + "                    *" + g.getNombre( ) + "\n" );
										ccon = g.getNombre( );
									}
									if ( casp.equals( g.getDescripcion( ) ) == false ) {
										Tinformacion.setText( Tinformacion.getText( ) + "                         ---" + g.getDescripcion( ) + "\n" );
										casp = g.getDescripcion( );
									}
								}	
							}
						}
					}
				}
			} );

			Plantilla pla;
			for ( int i = 0 ; i < lista.size( ) ; i++ ) {
				pla = new Plantilla( );
				pla = ( Plantilla ) lista.get( i );
				control.anhadeFila( pla );
			}
		} catch ( Exception e ) {
		e.printStackTrace( );
		}
	}
}