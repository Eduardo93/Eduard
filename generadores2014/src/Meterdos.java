import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
// import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;



/*
 * import org.apache.poi.hssf.record.formula.functions.Input;
 * import Execel.Generarexcel;
 */
import com.toedter.calendar.JDateChooser;

import Excel.Genrargeneral;
import Excel.Reporteconcepto;
import Excel.Reportefrente;
import Excel.Reportepartida;
import Manejodetablas2.ControlTableGenerador;
import Manejodetablas2.ModeloTablaGenerador;
import Manejotablas.MyTableModel;
import MetodosRemotos.Metodos;
import ObjetosSerializables.Aspecto;
import ObjetosSerializables.Concepto;
import ObjetosSerializables.Consultor;
import ObjetosSerializables.Estimacion;
import ObjetosSerializables.Frente;
import ObjetosSerializables.Partida;
import ObjetosSerializables.Proyecto;
import ObjetosSerializables.Rgenerador;
import ObjetosSerializables.Rutas;
import Options.ComponentsUser;
import Tablaconceptos.ModeloConceptoSeleccion;
import TablasPartidas.ControPartida;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Clase para crear La estimación
 * @author Pablo Rivera 
 *
 */
public class Meterdos extends JInternalFrame {

	/**
	 * elementos para la creacion de la ventana
	 */
	private static final long serialVersionUID = 1L;
	private JDesktopPane Pprincipal;
	private JScrollPane Sconceptos;
	private JComboBox < String > Cconsultor;
	private JLabel jconsultor;
	private JPanel Pconcepto;
	private JTable Tpartidas;
	private JScrollPane Spartidas;
	private JPanel Ppartidas;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JComboBox < String > gastosimpre;
	private JTable Taspectos;
	private JScrollPane Saspectos;
	private JLabel efecha;
	private JRadioButton eliminar;
	private JRadioButton modificar;
	private JComboBox < String > exportar;
	private JLabel corroborar;
	private JTable Tsegir;
	private JPanel Psegir;
	private JLabel jfrentes;
	private JLabel Jproyecto;
	private JRadioButton agregar;
	private JComboBox < String > Cfrentes;
	private JComboBox < String > Cproyectos;
	private JPanel Pproyecto;
	private JPopupMenu pMenu;
	private JMenuItem iagregar, ieliminar, agregarimagen, verestimacion;
	private Vector < Consultor > ListaConsultores = new Vector < Consultor >( );
	private Consultor consul = new Consultor( );
	private Metodos conexion;
	private LinkedList < Proyecto > ListaProyectos = new LinkedList < Proyecto >( );
	private Proyecto pro = new Proyecto( );
	private LinkedList < Frente > Listafrentes = new LinkedList < Frente >( );
	private Frente fre = new Frente( );
	private LinkedList < Estimacion > Lestimacion = new LinkedList < Estimacion >( );
	private Estimaciones esti;
	private ControPartida controlp = new ControPartida();
	private LinkedList < Concepto > Lisconceptos = new LinkedList < Concepto >( );
	private LinkedList < Partida > Lpartidas = new LinkedList < Partida >( );
	private ModeloTablaGenerador modelo2 = new ModeloTablaGenerador( );
	private ControlTableGenerador control = new ControlTableGenerador( modelo2 );
	private ModeloTablaGenerador modelo3 = new ModeloTablaGenerador( );
	private ControlTableGenerador control3 = new ControlTableGenerador( modelo3 );
	private boolean bandera = false, siinicial = false;
	private int idestimacion, indice = 0;
	private int idconsultor;
	private int idpartida = 0;
	private JDateChooser finicial;
	private LinkedList < Aspecto > Laspectos = new LinkedList < Aspecto >( );
	private Metodos cone;
	private String des;
	private int repeticion = 0;
	private Cargarimagen imagen;
	private Rgenerador ob, ob2;
	private String partida = "";
	private Proinicial pinicial;
	private AbstractTableModel dos;
	private LinkedList < Rgenerador > LSeleccion;
	private String fechaestimacion = "";
	private Periodos peri;
	// **********************************************************************************************************************************
	private Object [ ][ ] elementos;
	private JTable table_1;

	// **********************************************************************************************************************************************************

	/**
	 * constructor de la clase 
	 * @param cone -- conexion para la base de datos 
	 * @param escritorio2 -- JDesktopPane principal de la aplicación 
	 */
	public Meterdos ( Metodos cone , JDesktopPane escritorio2 ) {
		super( "Seguimiento de obra", false, true, false, true );
		initGUI( cone, escritorio2 );
	}

	/**
	 * inicializar la GUI
	 * @param cone -- conexion para la base de datos 
	 * @param escritorio2 -- JDesktopPane principal de la aplicación 
	 */
	private void initGUI ( final Metodos cone , final JDesktopPane escritorio2 ) {
		try {
			this.cone = cone;
			this.setPreferredSize( new java.awt.Dimension( 1064, 670 ) );
			this.setBounds( 0, 0, 1064, 670 );
			setVisible( true );

			this.conexion = cone;
			llenarLAcpestosdos( );
			Pprincipal = new JDesktopPane( );
			getContentPane( ).add( Pprincipal, BorderLayout.CENTER );
			Pprincipal.setPreferredSize( new java.awt.Dimension( 1048, 550 ) );

			Pproyecto = new JPanel( );
			Pprincipal.add( Pproyecto, JLayeredPane.DEFAULT_LAYER );
			Pproyecto.setBounds( 7, 2, 1049, 52 );
			Pproyecto.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );
			Pproyecto.setLayout( null );

			Cproyectos = new JComboBox < String >( );
			Pproyecto.add( Cproyectos );
			Cproyectos.setBounds( 616, 22, 220, 23 );
			Cproyectos.addActionListener( new ActionListener( ) {
				/**
				 * seleccion para un proyecto existente en el sistema
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					if ( Cproyectos.getSelectedIndex( ) > -1 ) {
						//si el indice seleccionado es mayor a -1 se muestran los frentes del proyecto seleccionado
						pro = ( Proyecto ) ListaProyectos.get( Cproyectos.getSelectedIndex( ) );
						llenarfrentes( pro.getIdproyecto( ) );
						Cfrentes.setEnabled( true );
					}
				}
			} );

			Cfrentes = new JComboBox < String >( );
			Pproyecto.add( Cfrentes );
			Cfrentes.setBounds( 846, 22, 193, 23 );
			Cfrentes.addActionListener( new ActionListener( ) {
				/**
				 * seleccion para un frente existente en el proyecto seleccionado
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					int c = Cfrentes.getSelectedIndex( );
					int anio, mes, dia;// existe=0;
					String ca = "", cm = "", cd = "";
					if ( finicial.getDate( ) != null ) {
						// se crea una fecha estimación del frente seleccionado 
						anio = finicial.getCalendar( ).get( Calendar.YEAR );
						mes = finicial.getCalendar( ).get( Calendar.MONTH ) + 1;
						dia = finicial.getCalendar( ).get( Calendar.DAY_OF_MONTH );
						ca = String.valueOf( anio );
						if ( mes < 10 ) {
							cm = "0" + String.valueOf( mes );
						} else {
							cm = String.valueOf( mes );
						}
						if ( dia < 10 ) {
							cd = "0" + String.valueOf( dia );
						} else {
							cd = String.valueOf( dia );
						}
						fechaestimacion = ca + "-" + cm + "-" + cd;
					}
					if ( c > 0 ) {
						// se obtiene el identificador del frente seleccionado
						Frente fre = new Frente( );
						;
						for ( int i = 0 ; i < Listafrentes.size( ) ; i++ ) {
							fre = ( Frente ) Listafrentes.get( i );
							if ( fre.getIndentificador( ).equals( String.valueOf( Cfrentes.getSelectedItem( ) ) ) == true ) {
								c = fre.getIdfrente( );
							}
						}
					}
					// ******************************************************************************************************************************************************************************************************************************************************
					if ( c > 0 && agregar.isSelected( ) == true ) {
						// si la opcion agregar esta seleccionada 
						// se obtienen las estimaciones iniciales creadas en el frente
						Lestimacion = conexion.sacarPreinicial( String.valueOf( c ), "I" );
						if ( Lestimacion.size( ) == 0 ) {
							//si no tiene una estimacion inicial no se le puede adr continuacion a la estimacion.
							JOptionPane.showMessageDialog( null, "Esta frente no tiene ninguna estimacion inicial", "verifica", JOptionPane.WARNING_MESSAGE );
						} else {
							try {
								// si tiene una estimacion incial se crean la estimacion recuperada de la base de datos 
								esti = new Estimaciones( Lestimacion, conexion, "agregar", controlp, control3 );
								escritorio2.add( esti );
								esti.setSelected( true );
							} catch ( Exception a ) {
								System.out.println( a.toString( ) );
								a.printStackTrace( );
							}
							bandera = true;
							Cproyectos.setEnabled( false );
							Cfrentes.setEnabled( false );
						}
					}
					// ***********************************************************************************************************************************************************************************************************************************************************
					if ( c > 0 && modificar.isSelected( ) == true ) {
						//si la opcion modificar esta seleccionada
						// se recupera el egguimiento de las estimaciones creadas 
						Lestimacion = conexion.sacarPreinicial( String.valueOf( c ), "S" );
						if ( Lestimacion.size( ) == 0 ) {
							//si no tiene ningun seguimeinto no se puede continuar la modificación
							JOptionPane.showMessageDialog( null, "Esta frente no tiene ninguna estimacion de seguimiento", "verifica", JOptionPane.WARNING_MESSAGE );
						} else {
							try {
								// si el frente seleccionado tiene un seguimiento, se carga la informacion de la estimacion para modificarla
								esti = new Estimaciones( Lestimacion, conexion, "modificar", controlp, control3 );
								escritorio2.add( esti );
								esti.setSelected( true );
							} catch ( Exception a ) {
								System.out.println( a.toString( ) );
								a.printStackTrace( );
							}
							Cproyectos.setEnabled( false );
							Cfrentes.setEnabled( false );
							bandera = false;
						}
					}
					// ******************************************************************************************************************************************************************************************************
					if ( c > 0 && eliminar.isSelected( ) == true ) {
						// si la opcion eliminar esta seleccionada
						//se recupera la informacion de la estimacion en la base de datos
						Lestimacion = conexion.sacarPreinicial( String.valueOf( c ), "S" );
						if ( Lestimacion.size( ) == 0 ) {
							//si no tiene seguimiento de estimaciones no se puede continuar.
							JOptionPane.showMessageDialog( null, "Esta frente no tiene ninguna estimacion de seguimiento" );
						} else {
							// se obtiene la estimacion creada
							try {
								esti = new Estimaciones( Lestimacion, conexion, "eliminar" );
								escritorio2.add( esti );
								esti.setSelected( true );
							} catch ( Exception a ) {
								System.out.println( a.toString( ) );
								a.printStackTrace( );
							}
							Cproyectos.setEnabled( false );
							Cfrentes.setEnabled( false );
							bandera = false;
						}
					}
				}
			} );

			//grupo de radios para evitar doble seleccion en las opciones "agregar", "modificar", "eliminar".
			ButtonGroup grupoRadios = new ButtonGroup( );

			agregar = new JRadioButton( );
			grupoRadios.add( agregar );
			Pproyecto.add( agregar );
			agregar.setText( "Agregar" );
			agregar.setBounds( 10, 17, 66, 20 );
			agregar.addActionListener( new ActionListener( ) {
				/**
				 * seleccion de agregar, habilita el combo box de consultor, limpia los generadores si existen y llena los aspectos
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					Cconsultor.setEnabled( true );
					modificar.setSelected( false );
					eliminar.setSelected( false );
					//llenarconceptos( 0 );
					llenarAspectos( 0 );
					Limpiar( );
					Limpiar2( );
					Taspectos.setVisible( false );
				}
			} );

			Jproyecto = new JLabel( );
			Pproyecto.add( Jproyecto );
			Jproyecto.setText( "Proyectos:" );
			Jproyecto.setBounds( 618, 5, 69, 16 );

			jfrentes = new JLabel( );
			Pproyecto.add( jfrentes );
			jfrentes.setText( "Frentes:" );
			jfrentes.setBounds( 848, 5, 57, 16 );

			jconsultor = new JLabel( );
			Pproyecto.add( jconsultor );
			jconsultor.setText( "Consultor:" );
			jconsultor.setBounds( 405, 5, 66, 16 );

			Cconsultor = new JComboBox < String >( );
			Pproyecto.add( Cconsultor );
			Cconsultor.setBounds( 401, 22, 199, 23 );

			modificar = new JRadioButton( );
			grupoRadios.add( modificar );
			Pproyecto.add( modificar );
			modificar.setText( "Modificar" );
			modificar.setBounds( 81, 18, 76, 20 );
			modificar.addActionListener( new ActionListener( ) {
				/**
				 * seleccion de agregar, habilita el combo box de proyecto, limpia los generadores si existen y llena los aspectos
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					eliminar.setSelected( false );
					agregar.setSelected( false );
					//llenarconceptos( 0 );
					llenarAspectos( 0 );
					Limpiar( );
					Limpiar2( );
					Cconsultor.setEnabled( false );
					Cproyectos.setEnabled( true );
					Cfrentes.setEnabled( false );
					Taspectos.setVisible( false );
				}
			} );

			eliminar = new JRadioButton( );
			grupoRadios.add( eliminar );
			Pproyecto.add( eliminar );
			eliminar.setText( "Eliminar" );
			eliminar.setBounds( 158, 19, 71, 20 );
			eliminar.addActionListener( new ActionListener( ) {
				/**
				 * seleccion de agregar, habilita el combo box de proyecto, limpia los generadores si existen y llena los aspectos
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					agregar.setSelected( false );
					modificar.setSelected( false );
					//llenarconceptos( 0 );
					llenarAspectos( 0 );
					Limpiar( );
					Limpiar2( );
					Taspectos.setVisible( false );
					Cproyectos.setEnabled( true );
					Cfrentes.setEnabled( false );
				}
			} );

			efecha = new JLabel( );
			Pproyecto.add( efecha );
			efecha.setText( "Fecha" );
			efecha.setBounds( 232, 3, 54, 16 );

			ImageIcon Imagenfecha = new ImageIcon( getClass( ).getResource( "iconos/reloj.png" ) );
			finicial = new JDateChooser( );
			finicial.setIcon( Imagenfecha );
			Pproyecto.add( finicial );
			finicial.setBounds( 229, 20, 160, 30 );
			Calendar c1 = new GregorianCalendar( );
			String cm = "";
			int dia = c1.get( Calendar.DATE );
			int mes = c1.get( Calendar.MONTH );
			if ( mes < 10 ) {
				cm = "0" + String.valueOf( mes );
			} else {
				cm = String.valueOf( mes );
			}
			int a = c1.get( Calendar.YEAR );
			String fe = String.valueOf( dia ) + "-" + cm + "-" + String.valueOf( a );

			SimpleDateFormat sdf = new SimpleDateFormat( "dd-MM-yyyy" );
			Date uno = sdf.parse( fe );
			finicial.setDate( uno );

			Cconsultor.setEnabled( false );
			Cconsultor.addActionListener( new ActionListener( ) {
				/**
				 * si el usuario da clic al nombre de un consultor, habilita el combo box de proyectos. 
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					Cproyectos.setEnabled( true );
				}
			} );

			//llamadas para mostrar los consultores, proyectos, frentes, conceptos y partidas
			llenarconsultor( );
			llenarproyectos( );
			llenarLFrentes( );
			llenarLconceptos( );
			llenarpartidas( );

			Ppartidas = new JPanel( );
			Pprincipal.add( Ppartidas, JLayeredPane.DEFAULT_LAYER );
			Ppartidas.setBounds( 9, 57, 219, 153 );

			Spartidas = new JScrollPane( );
			Ppartidas.add( Spartidas );
			Spartidas.setPreferredSize( new java.awt.Dimension( 212, 138 ) );

			Tpartidas = new JTable( controlp.getModel() );
			Tpartidas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					//si le damos clic a la tabla de partidas se crea una nueva partida para recuperar la información de la partida seleccionada
					Partida p = new Partida( );
						int c = -1;
						p = new Partida( );
						p = ( Partida ) Lpartidas.get( Tpartidas.getSelectionModel( ).getLeadSelectionIndex( ) );
						llenarconceptos( p.getIdpartida( ) );
						partida = p.getNombre( );
						Taspectos.setVisible( false );
						// ************************************************************************************************************************************************************************************
						if ( agregar.isSelected( ) == true ) {
							//si la opcion "agregar" esta seleccionada se obtiene el frente selecciondao para ingresar su estimacion 
							// *********************************************************************************************************************************************************************************
							if ( bandera == true && esti.Rtodos( ).size( ) > 0 ) {
								if ( Cfrentes.getSelectedIndex( ) > 0 ) {
									Frente fre = new Frente( );
									for ( int i = 0 ; i < Listafrentes.size( ) ; i++ ) {
										fre = ( Frente ) Listafrentes.get( i );
										if ( fre.getIndentificador( ).equals( String.valueOf( Cfrentes.getSelectedItem( ) ) ) == true ) {
											c = fre.getIdfrente( );
										}
									}
									if ( Cconsultor.getSelectedIndex( ) > -1 ) {
										Consultor con = ( Consultor ) ListaConsultores.get( Cconsultor.getSelectedIndex( ) );
										idconsultor = con.getIdconsultor( );
										if ( finicial.getDate( ) != null ) {

										} else {
											JOptionPane.showMessageDialog( null, "No has seleccionado fecha inicial" );
										}
										idestimacion = conexion.insertarestimacion( String.valueOf( c ), String.valueOf( idconsultor ), "0.0", fechaestimacion, "S", String.valueOf( esti.Indiceselecionado( ) ) );
										if ( idestimacion > 0 ) {
											bandera = false;
											Limpiar2( );
										}
									}
								}
							}// **************************************************************************************************************************************************************
							//si no tiene estimaciones el frente seleccionado se crea una nueva estimacion en la base de datos
							else {
								if ( control3.getLista( ).size( ) > 0 && idpartida > 0 ) {
									Rgenerador asp = new Rgenerador( );
									for ( int i = 0 ; i < control3.getLista( ).size( ) ; i++ ) {
										asp = ( Rgenerador ) control3.getLista( ).get( i );
										if ( asp.getX( ).equals( " " ) == true ) {
											asp.setX( "0" );
										}
										if ( asp.getY( ).equals( " " ) == true ) {
											asp.setY( "0" );
										}
										if ( asp.getZ( ).equals( " " ) == true ) {
											asp.setZ( "0" );
										}
										conexion.estimacionaspecto( asp.getIdaspecto( ), String.valueOf( idestimacion ), asp.getPiezas( ), asp.getImporte( ), asp.getX( ), asp.getY( ), asp.getZ( ), asp.getAlto( ), asp.getLargo( ), asp.getAncho( ), asp.getCosto( ), String.valueOf( idpartida ), String.valueOf( asp.getRepeticion( ) ), fechaestimacion );
									}
								}
							}
							idpartida = p.getIdpartida( );
							Limpiar2( );
							llenartodos( p.getNombre( ) );
						}
						// ******************************************************************************************************************************************************************************
						if ( modificar.isSelected( ) == true ) {
							//si la opcion modificar esta seleccionada
							if ( control3.getLista( ).size( ) > 0 ) {
								// se potinene el idedentificador de la estimacion del frente y se guarda en la base de datos 
								idestimacion = esti.Indiceselecionado( );
								Rgenerador asp = new Rgenerador( );
								for ( int i = 0 ; i < control3.getLista( ).size( ) ; i++ ) {
									asp = ( Rgenerador ) control3.getLista( ).get( i );
									if ( asp.getX( ).equals( " " ) == true ) {
										asp.setX( "0" );
									}
									if ( asp.getY( ).equals( " " ) == true ) {
										asp.setY( "0" );
									}
									if ( asp.getZ( ).equals( " " ) == true ) {
										asp.setZ( "0" );
									}
									conexion.estimacionaspecto( asp.getIdaspecto( ), String.valueOf( esti.Indiceselecionado( ) ), asp.getPiezas( ), asp.getImporte( ), asp.getX( ), asp.getY( ), asp.getZ( ), asp.getAlto( ), asp.getLargo( ), asp.getAncho( ), asp.getCosto( ), String.valueOf( idpartida ), String.valueOf( asp.getRepeticion( ) ), asp.getFecha( ) );
								}
							}
							idpartida = p.getIdpartida( );
							Limpiar2( );
							llenartodos( p.getNombre( ) );
							// Lseleccion=conexion.general(String.valueOf(esti.Indiceselecionado()),String.valueOf(idpartida));
							// Limpiar2();
							// Rgenerador asp;
							// Rgenerador a= new Rgenerador();
							// ****************************************************************************************************************************************************
							/*
							 * for (int i=0; i<Lseleccion.size(); i++){
							 * asp= new Rgenerador();
							 * a=(Rgenerador)Lseleccion.get(i);
							 * asp.setIdaspecto(a.getIdaspecto());
							 * asp.setClave(a.getClave());
							 * asp.setDescripcion(a.getDescripcion());
							 * asp.setUnidad(a.getUnidad());
							 * asp.setX(a.getX());
							 * asp.setY(a.getY());
							 * asp.setZ(a.getZ());
							 * asp.setAlto(a.getAlto());
							 * asp.setLargo(a.getLargo());
							 * asp.setAncho(a.getAncho());
							 * int al,l,an,re;
							 * if(a.getUnidad().equals("M2")==true){
							 * an=Integer.parseInt(a.getAncho());
							 * l=Integer.parseInt(a.getLargo());
							 * re=an*l;
							 * asp.setCantidad(String.valueOf(re));
							 * }
							 * if(a.getUnidad().equals("M3")==true){
							 * an=Integer.parseInt(a.getAncho());
							 * l=Integer.parseInt(a.getLargo());
							 * al=Integer.parseInt(a.getAlto());
							 * re=(an*l)*al;
							 * asp.setCantidad(String.valueOf(re));
							 * }
							 * if(a.getUnidad().equals("Pza")==true){
							 * asp.setCantidad(a.getPiezas());
							 * }
							 * if(a.getUnidad().equals("l")==true){
							 * asp.setCantidad(a.getPiezas());
							 * }
							 * if(a.getUnidad().equals("M")==true){
							 * asp.setCantidad(a.getLargo());
							 * }
							 * asp.setPiezas(a.getPiezas());
							 * asp.setCosto(a.getCosto());
							 * asp.setImporte(a.getImporte());
							 * asp.setRepeticion(a.getRepeticion());
							 * control3.anhadeFila(asp);
							 * }
							 */
						}
					}
			});
			Spartidas.setViewportView( Tpartidas );
			
			TableColumn col = Tpartidas.getColumn( "Partidas Prepuestales" );
			col.setPreferredWidth( 265 );
			Tpartidas.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );

			Pconcepto = new JPanel( );
			Pprincipal.add( Pconcepto, JLayeredPane.DEFAULT_LAYER );
			Pconcepto.setBounds( 237, 59, 819, 152 );
			Pconcepto.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );
			Pconcepto.setLayout( null );

			Sconceptos = new JScrollPane( );
			Pconcepto.add( Sconceptos );
			Sconceptos.setBounds( 13, 13, 258, 130 );
			
			table_1 = new JTable();
			table_1.addMouseListener(new MouseAdapter() {
				@Override
				/**
				 * si damos clic sobre un concepto desplegamos los aspectos dependiendo el concepto seleccionado
				 */
				public void mouseClicked(MouseEvent arg0) {
					Concepto con = new Concepto();
					int indi = 0;
					for (int i = 0; i < Lisconceptos.size(); i++) {
						con = (Concepto) Lisconceptos.get(i);
						if (con.getNombre() == table_1.getValueAt(table_1.getSelectedRow(),0)) {
							indi = con.getIdconcepto();
							i=Lisconceptos.size();
						}
					}
					
					llenarAspectos(indi);
					
					TableColumn co = Taspectos.getColumn("Selección");
					co.setPreferredWidth(80);
					TableColumn co2 = Taspectos.getColumn("Desglose de Conceptos");
					co2.setPreferredWidth(850);
				}
			});
			Sconceptos.setViewportView(table_1);

			Saspectos = new JScrollPane( );
			Pconcepto.add( Saspectos );
			Saspectos.setBounds( 283, 13, 518, 127 );

			Taspectos = new JTable( );
			Saspectos.setViewportView( Taspectos );
			Taspectos.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
			Taspectos.addMouseListener( new java.awt.event.MouseAdapter( ) {
				/**
				 * si damos clic a un concepto entonces agregamos una fila a la tabla para crear los número generadores
				 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
				 */
				public void mouseClicked ( java.awt.event.MouseEvent evt ) {
					@ SuppressWarnings ( "unused" )
					/**
					 * grupo de variables para el generador
					 */
					int uno, dos, i = 0, tm = 0, veces = 0, id = -1, indi = 0;
					Aspecto a = new Aspecto( );
					String ban = "", des = "", clave = "", ida = "0";
					uno = Taspectos.getSelectionModel( ).getLeadSelectionIndex( );
					dos = Taspectos.getColumnModel( ).getSelectionModel( ).getLeadSelectionIndex( );
					Object cellValue = Taspectos.getModel( ).getValueAt( uno, 0 );
					ban = cellValue.toString( );
					String ca, cm, cd;
					//si no se ha seleccionado la fecha se obtiene de la base de datos
					if ( finicial.getDate( ) != null ) {
						int anio = finicial.getCalendar( ).get( Calendar.YEAR );
						int mes = finicial.getCalendar( ).get( Calendar.MONTH ) + 1;
						int dia = finicial.getCalendar( ).get( Calendar.DAY_OF_MONTH );
						ca = String.valueOf( anio );
						if ( mes < 10 ) {
							cm = "0" + String.valueOf( mes );
						} else {
							cm = String.valueOf( mes );
						}
						if ( dia < 10 ) {
							cd = "0" + String.valueOf( dia );
						} else {
							cd = String.valueOf( dia );
						}
						fechaestimacion = ca + "-" + cm + "-" + cd;
					}
					if ( evt.isMetaDown( ) == true ) {

					} else {
						if ( evt.isAltDown( ) == false ) {
							// *****************************************************************************************************************************************************
							//se obtiene el aspecto seleccionado en la tabla de aspectos
							if ( uno > -1 ) {
								Object cellValuedos = Taspectos.getModel( ).getValueAt( uno, 1 );
								des = cellValuedos.toString( );
								if ( dos == 0 ) {
									//para cada aspecto se verifica la descripcion con el aspecto seleccionado para obtener su identificador y clave
									for ( i = 0 ; i < Laspectos.size( ) ; i++ ) {
										a = ( Aspecto ) Laspectos.get( i );
										if ( a.getDescripcion( ) == des ) {
											indi = a.getIdaspecto( );
											clave = a.getClave( );
											i = Laspectos.size( ) + 5;
										}
									}
									if ( ban.equals( "true" ) == true && des.equals( "" ) == false ) {
										// *******************************************************************************************************************************************************************************************
										if ( JOptionPane.showConfirmDialog( null, "Deseas agregar este aspecto al seguimiento de obra", "Confirmacinin", JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION ) {
										//	si se confirma agregar el aspecto al seguimiento, se cre una nueva fila para el formato de números generadores
											Rgenerador asp = new Rgenerador( );
											asp.setIdaspecto( String.valueOf( a.getIdaspecto( ) ) );
											asp.setClave( a.getClave( ) );
											asp.setDescripcion( a.getDescripcion( ) );
											asp.setUnidad( a.getUnidad( ) );
											asp.setX( "0" );
											asp.setY( "0" );
											asp.setZ( "0" );
											asp.setAlto( "0" );
											asp.setLargo( "0" );
											asp.setAncho( "0" );
											asp.setCantidad( "0" );
											asp.setPiezas( "0" );
											asp.setCosto( String.valueOf( a.getCosto( ) ) );
											asp.setImporte( "0" );
											asp.setRepeticion( 1 );
											asp.setPartida( String.valueOf( idpartida ) );
											asp.setFecha( fechaestimacion );
											control3.anhadeFila( asp );
											Tsegir.changeSelection( control3.Tama( ) - 1, 2, false, false );
										} else {
											// *********************************************************************************************************************************************************************************************
											Taspectos.getModel( ).setValueAt( new Boolean( false ), uno, 0 );
										}
									} else {
										//en caso contrario, si se elimina el aspecto seleccionado se elimina la fila de la tabla a de numeros generadores.
										// ******************************************************************************************************************************************************************************************************************************************
										if ( JOptionPane.showConfirmDialog( null, "Deseas eliminar este aspecto al seguimiento de obra", "Confirmacinin", JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION ) {
											tm = control3.Tama( );
											if ( ban.equals( "false" ) == true && des.equals( "" ) == false ) {
												Rgenerador asp = new Rgenerador( );
												for ( i = 0 ; i < tm ; i++ ) {
													asp = ( Rgenerador ) control3.getLista( ).get( i );
													if ( asp.getClave( ).equals( clave ) ) {
														if ( id == -1 ) {
															id = i;
															ida = asp.getIdaspecto( );
														}
														veces++;
													}
												}
												if ( cone.Eliminaresaspecto( ida, String.valueOf( idestimacion ) ) ) {
													if ( veces > 1 ) {
														control3.borraFila( id, veces );
													} else {
														control3.borraFila( id, 1 );
													}
												} else {
													JOptionPane.showMessageDialog( null, "Error al eliminar aspecto", "Error", JOptionPane.WARNING_MESSAGE );
												}
											}
										} else {
											// *************************************************************************************************************************************************************************************************************************************
											Taspectos.getModel( ).setValueAt( new Boolean( true ), uno, 0 );
										}
									}
								}
							}
						}
					}
				}
			} );

			Psegir = new JPanel( );
			Pprincipal.add( Psegir, JLayeredPane.DEFAULT_LAYER );
			Psegir.setBounds( 12, 219, 1038, 414 );
			Psegir.setLayout( null );

			JScrollPane Ssegir = new JScrollPane( );
			Psegir.add( Ssegir );
			Ssegir.setBounds( 9, 23, 1013, 344 );

			Tsegir = new JTable( modelo3 );
			Tsegir.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
			Tsegir.setRowHeight( 60 );
			
			TableColumn tabColClave = Tsegir.getColumn( "Clave" );
			tabColClave.setCellEditor( new DefaultCellEditor( ComponentsUser.getDataTxt( 15, 2 ) ) );
			
			TableColumn c3 = Tsegir.getColumn( "Descripción" );
			c3.setCellRenderer( new CustomRenderer( ) );
			c3.setCellEditor( new CustomEditor( ) );
			c3.setPreferredWidth( 400 );
			
			TableColumn tc4 = Tsegir.getColumn( "Unidad" );
			tc4.setPreferredWidth( 50 );
			tc4.setCellEditor( new DefaultCellEditor( ComponentsUser.getDataTxt( 10, 2 ) ) );

			/**
			 * X
			 * Y
			 * Z
			 */
			TableColumn tabColX = Tsegir.getColumn( "       X" );
			tabColX.setCellEditor( new DefaultCellEditor( ComponentsUser.getDataTxt( 6, 4 ) ) );

			TableColumn tabColY = Tsegir.getColumn( "       Y" );
			tabColY.setCellEditor( new DefaultCellEditor( ComponentsUser.getDataTxt( 6, 4 ) ) );

			TableColumn tabColZ = Tsegir.getColumn( "       Z" );
			tabColZ.setCellEditor( new DefaultCellEditor( ComponentsUser.getDataTxt( 6, 4 ) ) );

			/**
			 * Alto
			 * Largo
			 * Ancho
			 */
			TableColumn tabColAlto = Tsegir.getColumn( "    Alto" );
			tabColAlto.setCellEditor( new DefaultCellEditor( ComponentsUser.getDataTxt( 9, 3 ) ) );

			TableColumn tabColLargo = Tsegir.getColumn( "    Largo" );
			tabColLargo.setCellEditor( new DefaultCellEditor( ComponentsUser.getDataTxt( 9, 3 ) ) );

			TableColumn tabColAncho = Tsegir.getColumn( "    Ancho" );
			tabColAncho.setCellEditor( new DefaultCellEditor( ComponentsUser.getDataTxt( 9, 3 ) ) );

			/**
			 * Cantidad
			 * Piezas
			 * Costo
			 * Importe
			 */

			TableColumn tabColCantidad = Tsegir.getColumn( "Cantidad" );
			tabColCantidad.setCellEditor( new DefaultCellEditor( ComponentsUser.getDataTxt( 9, 3 ) ) );

			TableColumn tabColPiezas = Tsegir.getColumn( "Piezas" );
			tabColPiezas.setCellEditor( new DefaultCellEditor( ComponentsUser.getDataTxt( 9, 3 ) ) );

			TableColumn tabColCosto = Tsegir.getColumn( "Costo" );
			tabColCosto.setCellEditor( new DefaultCellEditor( ComponentsUser.getDataTxt( 9, 3 ) ) );
			tabColCosto.setPreferredWidth( 70 );

			TableColumn tabColImporte = Tsegir.getColumn( "Importe" );
			tabColImporte.setCellEditor( new DefaultCellEditor( ComponentsUser.getDataTxt( 9, 3 ) ) );
			tabColImporte.setPreferredWidth( 100 );
			
			Tsegir.addMouseListener( new java.awt.event.MouseAdapter( ) {
				/**
				 * dar clic a la tabla de los números generadores
				 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
				 */
				public void mouseClicked ( java.awt.event.MouseEvent a ) {
					indice = Tsegir.getSelectionModel( ).getLeadSelectionIndex( );
					int i = 0;// , posi=0;
					if ( a.isMetaDown( ) ) {
						pMenu.show( a.getComponent( ), a.getX( ), a.getY( ) );
					} else {
						if ( a.isAltDown( ) == false ) {
							// se obtiene  la informacion de la fila seleccionada
							if ( indice > -1 ) {
								ob = new Rgenerador( );
								ob = ( Rgenerador ) control3.getLista( ).get( indice );
								if ( ob.getDescripcion( ).equals( "" ) == false ) {
									des = ob.getDescripcion( );
								}
								repeticion = ob.getRepeticion( );
								// ********************************************************************************************************************************************
								// si la opcion agregar esta seleccionada se verifica la seleccion del aspecto en la tabla para habilitar o deshabilitar los componentes ( verestimacion)
								if ( agregar.isSelected( ) ) {
									if ( esti.Rtodos( ).size( ) > 0 ) {
										ob2 = new Rgenerador( );
										for ( i = 0 ; i < esti.Rtodos( ).size( ) ; i++ ) {
											ob2 = ( Rgenerador ) esti.Rtodos( ).get( i );
											if ( ob.getClave( ).equals( ob2.getClave( ) ) == true ) {
												i = esti.Rtodos( ).size( ) + 2;
												verestimacion.setEnabled( true );
												verestimacion.setVisible( true );
												siinicial = true;
											} else {
												verestimacion.setEnabled( false );
												verestimacion.setVisible( false );
												siinicial = false;
											}
										}
									}
								}
								if ( modificar.isSelected( ) ) {
									// si la opcion agregar esta seleccionada se verifica la seleccion del aspecto en la tabla para habilitar o deshabilitar los componentes ( verestimacion)
									if ( esti.Rtodos( ).size( ) > 0 ) {
										ob2 = new Rgenerador( );
										for ( i = 0 ; i < esti.estimacioninicial( ).size( ) ; i++ ) {
											ob2 = ( Rgenerador ) esti.estimacioninicial( ).get( i );
											if ( ob.getClave( ).equals( ob2.getClave( ) ) == true ) {
												i = esti.Rtodos( ).size( ) + 2;
												verestimacion.setEnabled( true );
												verestimacion.setVisible( true );
												siinicial = true;
											} else {
												verestimacion.setEnabled( false );
												verestimacion.setVisible( false );
												siinicial = false;
											}
										}
									}
								}
								// ********************************************************************************************************************************************
								for ( i = 0 ; i < elementos.length ; i++ ) {
									Object cellValuedos = Taspectos.getModel( ).getValueAt( i, 1 );
									if ( ob.getDescripcion( ).equals( cellValuedos.toString( ) ) == true ) {
										Taspectos.changeSelection( i, 2, false, false );
										i = elementos.length + 2;
									}
								}
							}
						}
					}
				}
			} );

			Ssegir.setViewportView( Tsegir );

			pMenu = new JPopupMenu( );
			agregarimagen = new JMenuItem( "Agregar Imagenes" );
			agregarimagen.addActionListener( new ActionListener( ) {
				/**
				 * clic al boton agregar imagen 
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					//se visualiza las imagenes que ahn sido seleccionadas para representar el seguimiento de la estimacion
					Rgenerador ob = new Rgenerador( );
					ob = ( Rgenerador ) control3.getLista( ).get( indice );
					LinkedList < Rutas > rutas = new LinkedList < Rutas >( );
					Rutas ru = new Rutas( );
					rutas = cone.Rutasfotos( String.valueOf( idpartida ), ob.getIdaspecto( ), String.valueOf( idestimacion ), String.valueOf( ob.getRepeticion( ) ) );
					for ( int i = 0 ; i < rutas.size( ) ; i++ ) {
						ru = ( Rutas ) rutas.get( i );
					}
					try {
						imagen = new Cargarimagen( cone, ob, idestimacion, idpartida, ru, fechaestimacion );
						escritorio2.add( imagen );
						imagen.setSelected( true );
					} catch ( Exception ex ) {
						System.out.println( ex );
					}
				}
			} );

			iagregar = new JMenuItem( "Agregar" );
			iagregar.addActionListener( new ActionListener( ) {
				/**
				 * clic en agregar un aspecto al generador
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					int i = 0;
					if ( indice > -1 ) {
						//se agrega una fila a la tabla del generador con la informacion del aspecto seleccionado
						// *****************************************************************************************
						Rgenerador asp = new Rgenerador( );
						Aspecto asp2 = new Aspecto( );
						Rgenerador a = new Rgenerador( );
						for ( i = 0 ; i < Laspectos.size( ) ; i++ ) {
							asp2 = ( Aspecto ) Laspectos.get( i );
							if ( asp2.getDescripcion( ).equals( des ) == true ) {
								asp.setIdaspecto( String.valueOf( asp2.getIdaspecto( ) ) );
								asp.setClave( asp2.getClave( ) );
								asp.setDescripcion( "" );
								asp.setUnidad( asp2.getUnidad( ) );
								asp.setX( " " );
								asp.setY( " " );
								asp.setZ( " " );
								asp.setAlto( "0" );
								asp.setLargo( "0" );
								asp.setAncho( "0" );
								asp.setCantidad( "0" );
								asp.setPiezas( "0" );
								asp.setCosto( asp2.getCosto( ) );
								asp.setImporte( "0" );
								asp.setFecha( fechaestimacion );
								for ( int j = 0 ; j < control3.getLista( ).size( ) ; j++ ) {
									a = ( Rgenerador ) control3.getLista( ).get( j );
									if ( a.getClave( ).equals( asp2.getClave( ) ) ) {
										repeticion++;
									}
								}
								asp.setRepeticion( repeticion );
								control3.Insertaabajo( asp, indice );
								i = Laspectos.size( ) + 5;
								Tsegir.changeSelection( indice + 1, 2, false, false );
							}
						}
					}
				}
			} );

			ieliminar = new JMenuItem( "Eliminar" );
			ieliminar.addActionListener( new ActionListener( ) {
				/**
				 * clic en eliminar aspecto... se elimina el aspecto seleccionado de la estimacion creada
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					if ( indice > -1 ) {
						Rgenerador asp = new Rgenerador( );
						asp = ( Rgenerador ) control3.getLista( ).get( indice );
						if ( cone.eleminarunaspecto( asp.getIdaspecto( ), String.valueOf( idestimacion ), String.valueOf( asp.getRepeticion( ) ) ) == true && asp != null ) {
							control3.borraFila( indice, 1 );
						}
					}
				}
			} );

			verestimacion = new JMenuItem( "Ver datos" );
			verestimacion.setEnabled( false );
			verestimacion.addActionListener( new ActionListener( ) {
				/**
				 * clic para ver todas las estimaciones del frente seleccionado
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					if ( ob2 != null ) {
						try {
							pinicial = new Proinicial( ob2 );
							escritorio2.add( pinicial );
							pinicial.setSelected( true );
						} catch ( PropertyVetoException e1 ) {
							e1.printStackTrace( );
						}
					}
				}
			} );

			pMenu.add( iagregar );
			pMenu.add( ieliminar );
			pMenu.add( agregarimagen );
			pMenu.add( verestimacion );

			corroborar = new JLabel( );
			Psegir.add( corroborar );
			corroborar.setText( "Corroborar informaci\u00F3n" );
			corroborar.setBounds( 12, 3, 177, 16 );
			corroborar.setFont( new java.awt.Font( "Segoe UI", 1, 14 ) );

			exportar = new JComboBox < String >( );
			Psegir.add( exportar );
			exportar.setBounds( 657, 379, 170, 23 );
			exportar.addItem( "Exportar todo" );
			exportar.addItem( "Exportar por concepto" );
			exportar.addActionListener( new ActionListener( ) {
				/**
				 * clic en exportar estimacion 
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					boolean ban2 = false;
					String path = "";
					//si existe por lo menos un aspecto incluido en la estimacion
					if ( control3.getLista( ).size( ) > 0 ) {
						if ( modificar.isSelected( ) ) {
							idestimacion = esti.Indiceselecionado( );
						}
						// se verifica la informacion de la ubicacion para cada uno de los aspectos incluidos en la estimacion
						Rgenerador asp = new Rgenerador( );
						for ( int i = 0 ; i < control3.getLista( ).size( ) ; i++ ) {
							asp = ( Rgenerador ) control3.getLista( ).get( i );
							if ( asp.getX( ).equals( " " ) == true ) {
								asp.setX( "0" );
							}
							if ( asp.getY( ).equals( " " ) == true ) {
								asp.setY( "0" );
							}
							if ( asp.getZ( ).equals( " " ) == true ) {
								asp.setZ( "0" );
							}
							ban2 = conexion.estimacionaspecto( asp.getIdaspecto( ), String.valueOf( idestimacion ), asp.getPiezas( ), asp.getImporte( ), asp.getX( ), asp.getY( ), asp.getZ( ), asp.getAlto( ), asp.getLargo( ), asp.getAncho( ), asp.getCosto( ), String.valueOf( idpartida ), String.valueOf( asp.getRepeticion( ) ), fechaestimacion );
						}
					}
					//se muestra un cuadro de dialogo para guardar el archivo del formato de números generadores en formato .xls
					JFileChooser navega = new JFileChooser( );
					int estado = navega.showSaveDialog( Tsegir );
					navega.setDialogTitle( "Salvar reporte" );
					if ( estado == JFileChooser.APPROVE_OPTION ) {
						path = navega.getSelectedFile( ).getAbsolutePath( );
						path += ".xls";
					}
					
					//se crea el archivo con la información capturada en el sistema
					if ( path != null && path.equals( "" ) == false ) {
						if ( exportar.getSelectedIndex( ) == 1 ) {
							// si esta seleccionda la opcion exportar todo se agregan todos los aspectos asignados al frente a la exportacion 
							// *************************************************************************************************************************************
							if ( control3.Tama( ) > 0 ) {
								/*
								 * if(verificarDatos()==false){
								 * Rgenerador asp= new Rgenerador();
								 * for(int i=0;
								 * i<control3.getLista().size();i++){
								 * asp=(Rgenerador)control3.getLista().get(i);
								 * ban2=conexion.estimacionaspecto(asp.getIdaspecto
								 * (
								 * ),String.valueOf(idestimacion),asp.getPiezas(
								 * )
								 * ,asp.getImporte(),asp.getX(),asp.getY(),asp.getZ
								 * (
								 * ),asp.getAlto(),asp.getLargo(),asp.getAncho()
								 * ,
								 * asp.getCosto(),String.valueOf(idpartida),String
								 * .valueOf(asp.getRepeticion()));
								 * } else{ban2=false;}
								 */
								if ( ban2 == true ) {
									LinkedList < Rutas > rutas = new LinkedList < Rutas >( );
									Rutas ru = new Rutas( );
									rutas = cone.Rutasfotos( String.valueOf( idpartida ), ob.getIdaspecto( ), String.valueOf( idestimacion ), String.valueOf( ob.getRepeticion( ) ) );
									for ( int i = 0 ; i < rutas.size( ) ; i++ ) {
										ru = ( Rutas ) rutas.get( i );
									}
									try {
										if ( ob != null && ob2 != null ) {
											@ SuppressWarnings ( "unused" )
											Reporteconcepto exe = new Reporteconcepto( ob, ob2, siinicial, partida, ru.getUno( ), ru.getDos( ), path );
										}
									} catch ( IOException e2 ) {
										e2.printStackTrace( );
									}
									try {
										File file = new File( path );
										Desktop.getDesktop( ).open( file );
									} catch ( IOException e1 ) {
										JOptionPane.showMessageDialog( null, "Error al lanzar archivo Execel" );
									}
								}
							} else {
								JOptionPane.showMessageDialog( null, "No hay datos selecionados" );
							}
						}
						// **************************************************************************************************************************************************
						//si esta seleccionada la opcion exportar por concepto, solo se exportan los aspectos que estan actualmente en el formato del sistema
						if ( exportar.getSelectedIndex( ) == 0 ) {
							if ( idestimacion > 0 ) {
								LinkedList < Rgenerador > desplegar = new LinkedList < Rgenerador >( );
								desplegar = conexion.reportegeneral( String.valueOf( idestimacion ) );
								if ( desplegar.size( ) > 0 ) {
									if ( verificarDatos( ) == false ) {
										new Genrargeneral( desplegar, path );
										try {
											File file = new File( path );
											Desktop.getDesktop( ).open( file );
										} catch ( IOException e1 ) {
											JOptionPane.showMessageDialog( null, "Error al lanzar Execel" );
										}
									}
								} else {
									JOptionPane.showMessageDialog( null, "La estimacion no contiene datos" );
								}
							} else {
								JOptionPane.showMessageDialog( null, "Debes seleccionar una estimacinin" );
							}
						}
						// **************************************************************************************************************************************************************************
					}
				}
			} );

			ComboBoxModel < String > gastosimpreModel = new DefaultComboBoxModel < String >( new String [ ] {
			"Partida", "Frente", "Periodos"
			} );
			gastosimpre = new JComboBox < String >( );
			Psegir.add( gastosimpre );
			gastosimpre.setModel( gastosimpreModel );
			gastosimpre.setBounds( 354, 379, 137, 23 );
			gastosimpre.addActionListener( new ActionListener( ) {
				/**
				 * clic en visualizar los gastos de la estimacion seleccionada
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 * 
				 */
				@ SuppressWarnings ( "unused" )
				public void actionPerformed ( ActionEvent e ) {
					if ( gastosimpre.getSelectedIndex( ) != 2 ) {
						JFileChooser navega = new JFileChooser( );
						String path = "";
						int estado = navega.showSaveDialog( Tsegir );
						navega.setDialogTitle( "Salvar reporte" );
						if ( estado == JFileChooser.APPROVE_OPTION ) {
							path = navega.getSelectedFile( ).getAbsolutePath( );
							path += ".xls";
						}
						
						//se genera el formato de gastos en la estimacion seleccionada
						if ( path != null && path.equals( "" ) == false ) {
							if ( gastosimpre.getSelectedIndex( ) == 0 ) {
								if ( modificar.isSelected( ) ) {
									Reportepartida repor = new Reportepartida( esti.estimacioninicial( ), partida, control3.getLista( ), path );
								}
								if ( agregar.isSelected( ) ) {
									Reportepartida repor = new Reportepartida( esti.Rtodos( ), partida, control3.getLista( ), path );
								}
							}
							if ( gastosimpre.getSelectedIndex( ) == 1 && LSeleccion != null ) {
								if ( agregar.isSelected( ) ) {
									Reportefrente repor = new Reportefrente( esti.Rtodos( ), LSeleccion, path );
								}
								if ( modificar.isSelected( ) ) {
									Reportefrente repor = new Reportefrente( esti.estimacioninicial( ), LSeleccion, path );
								}
							}
							try {
								//se abre el archivo creado
								File file = new File( path );
								Desktop.getDesktop( ).open( file );
							} catch ( IOException e1 ) {
								JOptionPane.showMessageDialog( null, "Error al lanzar archivo Execel" );
							}
						}
					} else {
						peri = new Periodos( cone, esti.fechainicial( ), esti.estimacioninicial( ), String.valueOf( idestimacion ) );
						escritorio2.add( peri );
						try {
							peri.setSelected( true );
						} catch ( PropertyVetoException e1 ) {
							e1.printStackTrace( );
						}
					}

				}
			} );

			jLabel1 = new JLabel( );
			Psegir.add( jLabel1 );
			jLabel1.setText( "Calculo de gastos:" );
			jLabel1.setBounds( 236, 382, 118, 16 );

			jLabel2 = new JLabel( );
			Psegir.add( jLabel2 );
			jLabel2.setText( "Realizar Reportes:" );
			jLabel2.setBounds( 536, 381, 115, 18 );
		} catch ( Exception e ) {
			e.printStackTrace( );
		}
	}

	/**
	 * método para  recuperar los consultores de la base de datos y mostrarlos en pantalla
	 */
	private void llenarconsultor ( ) {
		Cconsultor.removeAllItems( );
		ListaConsultores = conexion.GetConsultores( );
		for ( int i = 0 ; i < ListaConsultores.size( ) ; i++ ) {
			consul = ( Consultor ) ListaConsultores.get( i );
			Cconsultor.addItem( consul.getNombre( ) + "   " + consul.getPaterno( ) + "  " + consul.getMaterno( ) );
		}
		if ( Cconsultor.getItemCount( ) > 0 ) {
			Cconsultor.setSelectedIndex( 0 );
		}
	}

	/**
	 * método para  recuperar los proyectos de la base de datos y mostrarlos en pantalla
	 */
	private void llenarproyectos ( ) {
		Cproyectos.removeAllItems( );
		ListaProyectos = conexion.GetProyectos( );
		for ( int i = 0 ; i < ListaProyectos.size( ) ; i++ ) {
			pro = ( Proyecto ) ListaProyectos.get( i );
			Cproyectos.addItem( pro.getProyecto( ) );
		}
		if ( Cproyectos.getItemCount( ) > 0 ) {
			Cproyectos.setSelectedIndex( 0 );
		}
	}

	/**
	 * método para llenar recuperar los frentes de la base de datos 
	 */
	private void llenarLFrentes ( ) {
		Listafrentes = conexion.Frentes( );
	}

	/**
	 * método para  recuperar los frentes  de la base de datos de un proyecto seleccionado
	 * @param indice
	 */
	private void llenarfrentes ( int indice ) {
		Cfrentes.removeAllItems( );
		Cfrentes.addItem( "  " );
		for ( int i = 0 ; i < Listafrentes.size( ) ; i++ ) {
			fre = ( Frente ) Listafrentes.get( i );
			if ( fre.getIdproyecto( ) == indice ) {
				Cfrentes.addItem( fre.getIndentificador( ) );
			}
			Cfrentes.setSelectedIndex( -1 );
		}
		if ( Cfrentes.getItemCount( ) > 0 ) {
			Cfrentes.setSelectedIndex( 0 );
		}
	}

	/**
	 * método para limpiar la tabla de números generadores del sistema 
	 */
	public void Limpiar2 ( ) {
		int tama = control3.Tama( ) - 1;
		if ( tama > -1 ) {
			while ( tama >= 0 ) {
				control3.borraFila( tama, 1 );
				tama--;
			}
		}
	}

	/**
	 * método para limpiar la tabla de números generadores del sistema
	 */
	public void Limpiar ( ) {
		int tama = control.Tama( ) - 1;
		if ( tama > -1 ) {
			while ( tama >= 0 ) {
				control.borraFila( tama, 1 );
				tama--;
			}
		}
	}

	/**
	 * método para recuperar los conceptos de la base de datos
	 */
	private void llenarLconceptos ( ) {
		Lisconceptos = conexion.GetConceptos( );
	}

	/**
	 * método para llenar la tabla de conceptos en pantalla
	 * @param indice --  identificador de partida seleccionada
	 */
	private void llenarconceptos ( int indice ) {
		Concepto c = new Concepto();
		table_1.removeAll();
		LinkedList<String> nameConcept= new LinkedList<String>();
		for (int i = 0; i < Lisconceptos.size(); i++) {
			c = (Concepto) Lisconceptos.get(i);
			if (c.getIdpartida() == indice) {
				nameConcept.add(c.getNombre());
			}
		}
		ModeloConceptoSeleccion conceptosATM= new ModeloConceptoSeleccion();
		conceptosATM.setData(nameConcept);
		table_1.setModel(conceptosATM);
		
		/*
		 * String co = "";
		 * Rgenerador concep = new Rgenerador();
		 * LinkedList l= new LinkedList();
		 * l=esti.Rtodos();
		 * for(int i=0; i<l.size(); i++){
		 * concep=(Rgenerador)l.get(i);
		 * if (concep.getPartida().equals(indice)==true){
		 * for(int j=0; j<Lisconceptos.size(); j++){
		 * c=(Concepto)Lisconceptos.get(j);
		 * if(concep.getIdconcepto().equals(String.valueOf(c.getIdconcepto()))){
		 * if(concep.getIdconcepto().equals(co)==false){
		 * modelo.addElement(c.getNombre());
		 * co=concep.getIdconcepto();
		 * }
		 * }
		 * }
		 * }
		 * }
		 */
	}

	// **************************************************************************************************************************************
	/**
	 * método para recuperar las partidas de la base de datos
	 */
	public void llenarpartidas ( ) {
		Lpartidas = conexion.getPartidas( );
	}

	// **********************************************************************************************************************************

	// *****************************************************************************************************************************
	/**
	 * Clase para asiginar un componente JTextArea en la columna de descripcion del aspecto (visualmente)
	 * @author Pablo Rivera
	 *
	 */
	class CustomRenderer implements TableCellRenderer {

		private JTextArea textArea;

		/**
		 * constructor con un JTextArea
		 */
		public CustomRenderer ( ) {
			textArea = new JTextArea( );
			textArea.setLineWrap( true );
		}

		public Component getTableCellRendererComponent ( JTable table , Object value , boolean isSelected , boolean hasFocus , int row , int column ) {
			textArea.setText( ( String ) value );
			return textArea;
		}
	}

	/**
	 * Clase para asignar el editor de un componente JTextField en la columna de descripcion del aspecto
	 * @author Pablo Rivera
	 *
	 */
	class CustomEditor implements TableCellEditor {

		private JTextArea textArea;

		/**
		 * constructor con un JtextArea
		 */
		public CustomEditor ( ) {
			textArea = new JTextArea( );
			textArea.setLineWrap( true );
			textArea.setWrapStyleWord( true );
			textArea.setOpaque( true );
		}

		public Component getTableCellEditorComponent ( JTable table , Object value , boolean isSelected , int row , int column ) {
			textArea.setText( ( String ) value );
			return textArea;
		}

		public void addCellEditorListener ( CellEditorListener l ) {
		}

		public void cancelCellEditing ( ) {
		}
		public Object getCellEditorValue ( ) {
			return textArea.getText( );
		}

		public boolean isCellEditable ( EventObject anEvent ) {
			return true;
		}

		public void removeCellEditorListener ( CellEditorListener l ) {
		}

		public boolean shouldSelectCell ( EventObject anEvent ) {
			return true;
		}
		public boolean stopCellEditing ( ) {
			return true;
		}
	}

	// **********************************************************************************************************************************

	/**
	 * método para verificar la informacion de los aspectos seleccionados en la estimacion
	 * @return si existe algun problema con la informacion del aspecto ? false : todo ok true
	 */
	private boolean verificarDatos ( ) {
		boolean ban = false;
		int i;
		Rgenerador ge = new Rgenerador( );
		for ( i = 0 ; i < control3.getLista( ).size( ) ; i++ ) {
			ge = ( Rgenerador ) control3.getLista( ).get( i );
			if ( ge.getX( ).equals( " " ) == true || ge.getY( ).equals( " " ) == true || ge.getZ( ).equals( " " ) == true ) {
				ban = true;
				JOptionPane.showMessageDialog( null, "El aspecto: " + ge.getClave( ) + "  contiene valores no validos " );
				i = control.getLista( ).size( ) + 4;
			} else {
				if ( ge.getUnidad( ).equals( "M2" ) == true ) {
					if ( ge.getLargo( ).equals( "0" ) == true || ge.getAncho( ).equals( "0" ) == true || ge.getPiezas( ).equals( "0" ) == true ) {
						ban = true;
						JOptionPane.showMessageDialog( null, "El aspecto: " + ge.getClave( ) + "  contiene valores no validos " );
						i = control.getLista( ).size( ) + 4;
					}
				}
				if ( ge.getUnidad( ).equals( "M" ) == true ) {
					if ( ge.getLargo( ).equals( "0" ) == true || ge.getPiezas( ).equals( "0" ) == true ) {
						ban = true;
						JOptionPane.showMessageDialog( null, "El aspecto: " + ge.getClave( ) + "  contiene valores no validos " );
						i = control.getLista( ).size( ) + 4;
					}
				}
				if ( ge.getUnidad( ).equals( "M3" ) == true ) {
					if ( ge.getAlto( ).equals( "0" ) == true || ge.getLargo( ).equals( "0" ) == true || ge.getAncho( ).equals( "0" ) == true || ge.getPiezas( ).equals( "0" ) == true ) {
						ban = true;
						JOptionPane.showMessageDialog( null, "El aspecto: " + ge.getClave( ) + "  contiene valores no validos " );
						i = control.getLista( ).size( ) + 4;
					}
				}
				if ( ge.getUnidad( ).equals( "Pza" ) == true ) {
					if ( ge.getPiezas( ).equals( "0" ) == true ) {
						ban = true;
						JOptionPane.showMessageDialog( null, "El aspecto: " + ge.getClave( ) + "  contiene valores no validos " );
						i = control.getLista( ).size( ) + 4;
					}
				}
				if ( ge.getUnidad( ).equals( "L" ) == true ) {
					if ( ge.getPiezas( ).equals( "0" ) == true ) {
						ban = true;
						JOptionPane.showMessageDialog( null, "El aspecto: " + ge.getClave( ) + "  contiene valores no validos " );
						i = control.getLista( ).size( ) + 4;
					}
				}
				if ( ge.getUnidad( ).equals( "t" ) == true ) {
					if ( ge.getPiezas( ).equals( "0" ) == true ) {
						ban = true;
						JOptionPane.showMessageDialog( null, "El aspecto: " + ge.getClave( ) + "  contiene valores no validos " );
						i = control.getLista( ).size( ) + 4;
					}
				}
			}
		}
		return ban;
	}

	// ***********************************************************************************************************************************************************+++

	/**
	 * método para desplegar los aspectos en pantalla
	 * @param indice -- indice del concepto seleccionado
	 */
	private void llenarAspectos ( int indice ) {
		Rgenerador seleccionado = new Rgenerador( );
		int posi = 0;
		Aspecto asp2;
		int filas = 0, columnas = 2;

		//se obtiene el total de aspectos relacionados con el concepto
		for ( int i = 0 ; i < Laspectos.size( ) ; i++ ) {
			asp2 = ( Aspecto ) Laspectos.get( i );
			if ( asp2.getIdconcepto( ) == indice )
				filas++;
		}
		
		//se crea una matriz de N filas por N columanas para desplegar la informacion en la tabla de aspectos
		elementos = new Object [ filas ] [ columnas ];

		for ( int i = 0 ; i < elementos.length ; i++ ) {
			elementos[i][0] = new Boolean( false );
		}

		// para cada aspecto se verifica que coincida con el concepto seleccionado en pantalla
		for ( int i = 0 ; i < Laspectos.size( ) ; i++ ) {
			asp2 = ( Aspecto ) Laspectos.get( i );
			if ( asp2.getIdconcepto( ) == indice ) {
				elementos[posi][1] = asp2.getDescripcion( );
				if ( control3.getLista( ).size( ) > 0 ) {
					for ( int j = 0 ; j < control3.getLista( ).size( ) ; j++ ) {
						seleccionado = ( Rgenerador ) control3.getLista( ).get( j );
						//siesta agregado un aspecto a la estimacion se marca en True la primera columna en la tabla de aspectos
						if ( seleccionado.getClave( ).equals( asp2.getClave( ) ) ) {
							elementos[posi][0] = new Boolean( true );
						}
					}
				}
				posi++;
			}
		}
		dos = new MyTableModel( elementos );
		Taspectos.removeAll( );
		Saspectos.setViewportView( Taspectos );
		Taspectos.setModel( dos );
		TableColumn tc3 = Taspectos.getColumn( "Desglose de Conceptos" );
		tc3.setPreferredWidth( 800 );
		tc3.setCellRenderer( new ColoredTableCellRenderer( ) );
		Taspectos.setVisible( true );
		TableColumn co1 = Taspectos.getColumn( "Selección" );
		co1.setPreferredWidth( 70 );

	}

	/**
	 * método para obtener los aspectos de la base de datos
	 */
	private void llenarLAcpestosdos ( ) {
		Laspectos = cone.GetAspectos( );
	}

	/**
	 * método apra llenar todos los aspectos incluidos en una estimacion 
	 * @param nombre nombre de la partida para ver si estan agregados aspectos 
	 */
	private void llenartodos ( String nombre ) {
		Rgenerador ob = new Rgenerador( );
		LSeleccion = new LinkedList < Rgenerador >( );
		if ( modificar.isSelected( ) ) {
			LSeleccion = cone.reportegeneral( String.valueOf( esti.Nestimacion( ) ) );
		}
		if ( agregar.isSelected( ) ) {
			LSeleccion = cone.reportegeneral( String.valueOf( idestimacion ) );
		}
		for ( int i = 0 ; i < LSeleccion.size( ) ; i++ ) {
			ob = ( Rgenerador ) LSeleccion.get( i );
			if ( ob.getPartida( ).equals( nombre ) == true ) {
				Rgenerador meter = new Rgenerador( );
				meter.setIdaspecto( String.valueOf( ob.getIdaspecto( ) ) );
				meter.setClave( ob.getClave( ) );
				meter.setDescripcion( ob.getDescripcion( ) );
				meter.setUnidad( ob.getUnidad( ) );
				meter.setX( ob.getX( ) );
				meter.setY( ob.getY( ) );
				meter.setZ( ob.getZ( ) );
				meter.setAlto( ob.getAlto( ) );
				meter.setLargo( ob.getLargo( ) );
				meter.setAncho( ob.getAncho( ) );
				meter.setCantidad( "0" );
				meter.setPiezas( ob.getCantidad( ) );
				meter.setCosto( String.valueOf( ob.getCosto( ) ) );
				meter.setImporte( ob.getImporte( ) );
				meter.setRepeticion( ob.getRepeticion( ) );
				meter.setFecha( ob.getFecha( ) );
				control3.anhadeFila( meter );
			}
		}
	}

	// *******************************************************************************************************************************************************

	/**
	 * Clase para marcar en color verde los aspectos que estan incluidos en la estimacion inicial o estimaciones de seguimiento creados con anterioridad
	 * @author Pablo Rivera
	 *
	 */
	class ColoredTableCellRenderer extends DefaultTableCellRenderer {

		/**
		 * elementos de la clase para colorear la fila 
		 */
		private static final long serialVersionUID = 1L;
		Rgenerador seleccionado = new Rgenerador( );
		int j, uno;
		String valor = "";
		
		/**
		 * método para verificar si el aspecto esta incluido en la estimacion y destacarlo con color verde
		 * @see javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
		 */
		public Component getTableCellRendererComponent ( JTable table , Object value , boolean selected , boolean focused , int row , int column ) {
			Object cellValue = Taspectos.getModel( ).getValueAt( row, column );
			valor = cellValue.toString( );
			setEnabled( table == null || table.isEnabled( ) );
			if ( agregar.isSelected( ) ) {
				if ( esti.Rtodos( ).size( ) > 0 ) {
					for ( j = 0 ; j < esti.Rtodos( ).size( ) ; j++ ) {
						seleccionado = ( Rgenerador ) esti.Rtodos( ).get( j );
						if ( seleccionado.getDescripcion( ).equals( valor ) ) {
							setBackground( Color.green );
							j = esti.Rtodos( ).size( ) + 5;
						} else {
							setBackground( null );
						}
					}
				}
			}
			if ( modificar.isSelected( ) ) {
				if ( esti.estimacioninicial( ).size( ) > 0 ) {
					for ( j = 0 ; j < esti.estimacioninicial( ).size( ) ; j++ ) {
						seleccionado = ( Rgenerador ) esti.estimacioninicial( ).get( j );
						if ( seleccionado.getDescripcion( ).equals( valor ) ) {
							setBackground( Color.green );
							j = esti.Rtodos( ).size( ) + 5;
						} else {
							setBackground( null );
						}
					}
				}
			}
			super.getTableCellRendererComponent( table, value, selected, focused, row, column );
			return this;
		}
	}
}