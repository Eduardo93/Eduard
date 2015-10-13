import java.awt.BorderLayout;
import java.awt.HeadlessException;
// import java.rmi.RemoteException;
import java.util.LinkedList;

import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Manejodetablas2.ControlTableGenerador;
import MetodosRemotos.Metodos;
import ObjetosSerializables.Estimacion;
import ObjetosSerializables.Partida;
import ObjetosSerializables.Rgenerador;
import TablaEstimaciones.ControlEstimacion;
import TablaEstimaciones.ModeloEstimacion;
import TablasPartidas.ControPartida;

/**
 * clase para seleccionar la fecha de la estimación de un proyecto creado
 * @author Pablo Rivera
 * @colaboración luiiis Lazaro
 * @access {@docRoot}
 *
 */
public class Estimaciones extends javax.swing.JInternalFrame {

	/**
	 * escritorio --- panel de la aplicación (menú principal)
	 * paneluno --- panel del JInternalFrame
	 * Testimacion --- tabla para visualizar la fecha de creacion de la estimacion.
	 * sestimaciones --- scroll para la tabla de fechas de estimaciones
	 * modelo --- modelo para manipular la información de la tabla de fechas de estimaciones
	 * control --- control para manipular el modelo de la tabala de fechas de estimaciones
	 * indice --- auxiliar para obtener el ID de la Fecha seleccionada
	 * id --- auxiliar para obtener el ID de la fecha selecciona (dentro de la lista de estimaciones ) 
	 * es --- auxiliar para manipular las estimaciones de la lista obtenida de la base de datos
	 * lista --- lista de estimaciones creadas
	 * lesti --- lista de los conceptos relacionados con la fecha de estimación.
	 * linicial --- lista de estimaciones iniciales
	 * nestiamcion --- número de indentificador de la estimación
	 * fechaini --- auxiliar para obtener la fecha de la estimación
	 */
	private static final long serialVersionUID = 1L;
	private JDesktopPane Escritorio;
	private JTable Testimaciones;
	private JScrollPane Sestimaciones;
	private ModeloEstimacion modelo = new ModeloEstimacion( );
	private ControlEstimacion control = new ControlEstimacion( modelo );
	private int indice = -1;
	private int id = -1;
	private Estimacion es;
	private LinkedList < Estimacion > lista = new LinkedList < Estimacion >( );
	private LinkedList < Rgenerador > Lesti = new LinkedList < Rgenerador >( );
	private LinkedList < Rgenerador > Linicial = new LinkedList < Rgenerador >( );
	private int nestimacion = 0;
	private String fechaini = "";

	/**
	 * constructor de la clase
	 * @param esti --- lista de estimaciones, creadas en el proyecto
	 * @param con --- metodos para la manipulación de la base de datos 
	 * @param accion --- opcion para seleccionar "modificar o eliminar"
	 * @wbp.parser.constructor
	 */
	public Estimaciones ( LinkedList < Estimacion > esti , Metodos con , String accion ) {
		super( "Selecionar Estimaci\u00F3n", false, true, false, true );
		initGUI( esti, con, accion );
	}

	/**
	 * inicialización de componentes gráficos
	 * @param esti --- lista de estimaciones, creadas en el proyecto
	 * @param con --- metodos para la manipulación de la base de datos 
	 * @param accion --- opcion para seleccionar "modificar o eliminar"
	 */
	private void initGUI ( LinkedList < Estimacion > esti , final Metodos con , final String accion ) {
		try {
			this.setPreferredSize( new java.awt.Dimension( 234, 300 ) );
			this.setBounds( 200, 60, 234, 300 );
			setVisible( true );

			this.lista = esti;
			Escritorio = new JDesktopPane( );
			getContentPane( ).add( Escritorio, BorderLayout.CENTER );

			Sestimaciones = new JScrollPane( );
			Escritorio.add( Sestimaciones, JLayeredPane.DEFAULT_LAYER );
			Sestimaciones.setBounds( 0, 0, 232, 275 );

			Testimaciones = new JTable( modelo );
			Sestimaciones.setViewportView( Testimaciones );

			//agregar conceptos a la tabla 
			for ( int i = 0 ; i < esti.size( ) ; i++ ) {
				es = new Estimacion( );
				es = ( Estimacion ) lista.get( i );
				control.anhadeFila( es );
			}

			Testimaciones.addMouseListener( new java.awt.event.MouseAdapter( ) {
				/**
				 * cuando se da clic sobre una fecha de estimación.
				 * (non-Javadoc)
				 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
				 */
				public void mouseClicked ( java.awt.event.MouseEvent a ) {
					if ( a.isMetaDown( ) ) {

					} else {
						if ( a.isAltDown( ) == false ) {
							indice = Testimaciones.getSelectionModel( ).getLeadSelectionIndex( );
							if ( accion.equals( "modificar" ) == true ) {
								if ( indice > -1 ) {
									es = new Estimacion( );
									es = ( Estimacion ) lista.get( indice );
									id = Integer.parseInt( es.getIdestimacion( ) );
									dispose( );
								}
							} else {
								/**
								 * opción para eliminar una estimacion 
								 */
								if ( accion.equals( "eliminar" ) == true ) {
									int res = JOptionPane.showConfirmDialog( null, "Deseas eliminar estimación(se perderán las estimaciones de seguimiento)", "Confirmación", JOptionPane.YES_NO_OPTION );
									if ( res == JOptionPane.YES_OPTION ) {
										es = new Estimacion( );
										es = ( Estimacion ) lista.get( indice );
										try {
											if ( con.EliminarEstimacion( es.getIdestimacion( ) ) ) {
												JOptionPane.showMessageDialog( null, "La estimación con fecha:  " + es.getFecha( ) + "  ha sido eliminada" );
											} else {
												JOptionPane.showMessageDialog( null, "Error al eliminar estimación inicial", "Error", JOptionPane.WARNING_MESSAGE );
											}
										} catch ( HeadlessException e ) {
											JOptionPane.showMessageDialog( null, "Servidor fuera de servicio (Error al eliminar estimación inicial ) ", "Error", JOptionPane.ERROR_MESSAGE );
										}
										dispose( );
									} else {
										dispose( );
									}
								}
							}
						}
					}
				}
			} );
		} catch ( Exception e ) {
			e.printStackTrace( );
		}

	}

	// *************************************************************************************************************************************************************************************************************************
	/**
	 * Otro constructor para la clase
	 * @param esti --- lista de estimaciones, creadas en el proyecto
	 * @param con --- metodos para la manipulación de la base de datos 
	 * @param accion --- opcion para seleccionar "modificar o eliminar"
	 * @param controlp --- control para manipular la tabla de partidas 
	 * @param control3 --- control para manipular la tabla de generadores
	 */
	public Estimaciones ( LinkedList < Estimacion > esti , Metodos con , String accion , ControPartida controlp , ControlTableGenerador control3 ) {
		super( "Selecionar estimación", false, true, false, true );
		initGUI( esti, con, accion, controlp, control3 );
	}

	// *****************************************************************************************************************************************************************************************************************************************
	/**
	 * inicialización de componentes gráficos
	 * @param esti --- lista de estimaciones, creadas en el proyecto
	 * @param con --- metodos para la manipulación de la base de datos 
	 * @param accion --- opcion para seleccionar "modificar o eliminar"
	 * @param controlp --- control para manipular la tabla de partidas 
	 * @param control3 --- control para manipular la tabla de generadores
	 */
	private void initGUI ( LinkedList < Estimacion > esti , final Metodos con , final String accion , final ControPartida controlp , final ControlTableGenerador control3 ) {
		try {
			this.setPreferredSize( new java.awt.Dimension( 234, 300 ) );
			this.setBounds( 200, 60, 234, 300 );
			setVisible( true );

			this.lista = esti;
			Escritorio = new JDesktopPane( );
			getContentPane( ).add( Escritorio, BorderLayout.CENTER );

			Sestimaciones = new JScrollPane( );
			Escritorio.add( Sestimaciones, JLayeredPane.DEFAULT_LAYER );
			Sestimaciones.setBounds( 0, 0, 232, 275 );

			Testimaciones = new JTable( modelo );
			Sestimaciones.setViewportView( Testimaciones );

			for ( int i = 0 ; i < esti.size( ) ; i++ ) {
				es = new Estimacion( );
				es = ( Estimacion ) lista.get( i );
				control.anhadeFila( es );
			}

			Testimaciones.addMouseListener( new java.awt.event.MouseAdapter( ) {
				/**
				 * parte que verifica si se agrega o se modifica la estimación seleccionada
				 * (non-Javadoc)
				 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
				 */
				public void mouseClicked ( java.awt.event.MouseEvent a ) {
					if ( a.isMetaDown( ) ) {

					} else {
						if ( a.isAltDown( ) == false ) {
							indice = Testimaciones.getSelectionModel( ).getLeadSelectionIndex( );
							es = new Estimacion( );
							es = ( Estimacion ) lista.get( indice );
							// ***********************************************************************************************************************************************************************
							/**
							 * 
							 */
							if ( accion.equals( "agregar" ) == true ) {
								id = Integer.parseInt( es.getIdestimacion( ) );
								Lesti = new LinkedList < Rgenerador >( );
								Lesti = con.reportegeneral( String.valueOf( id ) );
								fechaini = es.getFecha( );
								if ( Lesti.size( ) == 0 ) {
									JOptionPane.showMessageDialog( null, "Esta estimación inicial no contiene datos" );
									dispose( );
								} else {
									LinkedList < Partida > Lpartidas = new LinkedList < Partida >( );
									Partida par = new Partida( );
									Lpartidas = con.getPartidas( );
									for ( int i = 0 ; i < Lpartidas.size( ) ; i++ ) {
										par = ( Partida ) Lpartidas.get( i );
										controlp.anhadeFila( par.getNombre( ) );
									}
								}
								dispose( );
							}
						}
						// ********************************************************************************************************************************************************************
						/**
						 * 
						 */
						if ( accion.equals( "modificar" ) == true ) {
							id = Integer.parseInt( es.getIdestimacion( ) );
							Lesti = new LinkedList < Rgenerador >( );
							Lesti = con.reportegeneral( es.getNestimacion( ) );
							nestimacion = id;
							if ( Lesti.size( ) == 0 ) {
								JOptionPane.showMessageDialog( null, "Esta estimación de seguimiento no contiene datos" );
								dispose( );
							} else {
								LinkedList < Partida > Lpartidas = new LinkedList < Partida >( );
								Partida par = new Partida( );
								Lpartidas = con.getPartidas( );
								for ( int i = 0 ; i < Lpartidas.size( ) ; i++ ) {
									par = ( Partida ) Lpartidas.get( i );
									controlp.anhadeFila( par.getNombre( ) );
								}
							}
							Lesti = con.reportegeneral( String.valueOf( id ) );
							Rgenerador ob = new Rgenerador( );
							for ( int j = 0 ; j < Lesti.size( ) ; j++ ) {
								ob = ( Rgenerador ) Lesti.get( j );
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
							Linicial = Lesti;
							fechaini = con.sacarfecha( es.getNestimacion( ) );
							dispose( );
						}
					}
				}
			} );
		} catch ( Exception e ) {
			e.printStackTrace( );
		}
	}

	/**
	 * Obtiene el id auxiliar para el ID de la fecha de estimación
	 * @return ID int 
	 */
	public int Indiceselecionado ( ) {
		return this.id;
	}

	/**
	 * obtiene la lista de estimaciones relacionados con la estiamción  
	 * @return lista de conceptos generados 
	 */
	public LinkedList < Rgenerador > Rtodos ( ) {
		return this.Lesti;
	}

	/**
	 * Número de estimación creada auxiliar para obtener el ID de la estimación
	 * @return nestimacion int 
	 */
	public int Nestimacion ( ) {
		return this.nestimacion;
	}

	/**
	 * obtiene la lista de estimaciones iniciales del proyecto
	 * @return linicial lista de generadores iniciales 
	 */
	public LinkedList < Rgenerador > estimacioninicial ( ) {
		return this.Linicial;
	}

	/**
	 * Obtiene la fecha inicial de la estimación del proyecto
	 * @return fechaini string 
	 */
	public String fechainicial ( ) {
		return this.fechaini;
	}
}
