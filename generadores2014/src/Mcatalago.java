import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import AdministrarUnidadesMedida.Controller.CUnidad;
import AdministrarUnidadesMedida.View.VUnidadesMedida;
import Excel.Importar;
import Manejotablas.ControlTabla;
import MetodosRemotos.Metodos;
import Model.Entity.Unidad;
import ObjetosSerializables.Aspecto;
import ObjetosSerializables.Concepto;
import ObjetosSerializables.Objetoexecel;
import ObjetosSerializables.Partida;
import Options.ComponentsUser;
import Options.LenString;
import Options.VDescripcionCompletaDesgloseConcepto;
import Tablaconceptos.ConceptoL;
import Tablaconceptos.ControlConcepto;
import TablasPartidas.ControPartida;

import java.awt.event.MouseAdapter;

/**
 * Clase para las actualizaciones de los catálogos
 * (partidas, conceptos y aspectos)
 * @author Rivera Pablo 
 * @colaboración luiiis lazaro
 */
public class Mcatalago extends JInternalFrame {

	/**
	 * escritorio --- panel del Interfnal Frame
	 * Ppartias --- panel para la información de las partidas
	 * Tpartidas --- tabla para la información de las partidas
	 * Spartidas --- scroll para la tabla de partidas
	 * epartidas --- botón para eliminar una partida
	 * pmodificar --- botón para modificar partida 
	 * aaspecto --- botón para agregar un aspecto
	 * Taspecto --- tabla para la información de aspectos
	 * lbldesglose --- etiqueta para el titulo de deslgose 
	 * Saspecto --- scroll para la tabla de aspectos
	 * paspectos --- panel para la información de los aspectos
	 * econceptos --- botón para eliminar un concepto
	 * aconceptos --- botón para agregar un concepto 
	 * tconceptos --- tabla para la información de conceptos
	 * apartida --- botón para agregar una partida
	 * tab_partidas --- tabla para la información de partidas
	 * cone --- métodos para la manipulación de la base de datos
	 * lblconceptos --- etiqueta para la titulo de conceptos
	 * Sconcpetos --- scroll para la tabla de conceptos
	 * pconceptos --- panel para la información de conceptos
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDesktopPane escritorio;
	private JPanel Pparitdas;
	private JLabel Tpartidas;
	private JScrollPane Spatidas;
	private JButton Epartida;
	private JButton Pmodficar;
	private JButton Easpecto;
	private JButton Aaspecto;
	private JLabel lblDesglose;
	private JTable Taspectos;
	private JScrollPane Saspecto;
	private JPanel Paspectos;
	private JButton Econcepto;
	private JButton Aconcepto;
	private JTable Tconceptos;
	private JButton Apartida;
	private JTable tab_partidas;
	private Metodos cone;
	private JLabel lblConceptos;
	private JScrollPane Sconceptos;
	private JPanel Pconceptos;

	/**
	 * 
	 * modelop --- modelo para la tabla de partidas
	 * controlp --- control para la manipulación de la tabla de partidas
	 * lpartidas --- lista de partidas existentes en la base de datos
	 * par --- auxiliar para manipular las partidas en la lista de partidas
	 * indice --- auxiliar para obener el ID de la partida seleccionada
	 * i --- indice del concepto seleccionado
	 * npartida --- auxiliar par aobtener el ID de la partida al agregar un nuevo concepto
	 * 
	 */
	private ControPartida controlp = new ControPartida();
	private LinkedList < Partida > Lpartidas = new LinkedList < Partida >( );
	private Partida par = new Partida( );
	private int indice;
	private int idpartida;
	private int i = -1;
	private String npartida;

	/**
	 * lconceptos --- lista de conceptos de la base de datos
	 * modeloc --- modelo para la tabla de conceptos
	 * controlc --- control para manipular la informacion de la tabla de conceptos
	 * importar --- botón para importar conceptos /// no se encuentra documentado
	 * concep --- lista de los conceptos de la base de datos
	 * c --- auxiliar para manejar los conceptos de la lista de conceptos 
	 * cunidad --- combo box para seleccionar la unidad de medición de los aspectos
	 */
	private LinkedList < Concepto > Lconceptos = new LinkedList < Concepto >( );
	private ControlConcepto controlc = new ControlConcepto( );
	private JButton importar;
	private ConceptoL concep = new ConceptoL( );
	private Concepto c = new Concepto( );

	/**
	 * laspectos --- lista para la infomracion de los aspectos de la base de datos
	 * modeloa --- modelo para la tabla de aspectos
	 * controla --- control para la manipulacion de la información de la tabla de aspectos 	 a
	 * a --- auxiliar para manejar la información del aspecto en la lista de aspectos
	 * asp --- "
	 * btnseemore --- botón para ver la descripción completa del aspecto
	 * 
	 */
	private LinkedList < Aspecto > Laspectos = new LinkedList < Aspecto >( );
	private ControlTabla controla = new ControlTabla();
	private Aspecto a = new Aspecto( );
	@ SuppressWarnings ( "unused" )
	private Aspecto asp = new Aspecto( );
	private JButton btnSeeMore;

	/**
	 * constructor de la clase iniciar componentes gráficos
	 * @param conexion
	 * 			metodos	--- conexión para la manipulación de la base de datos 
	 */
	public Mcatalago ( Metodos conexion ) {
		super( "Editar Catálogos", true, true, false, true );
		initGUI( conexion );
	}

	/**
	 * Inicialización de componentes gráficos para la ventana de cátalogo de: partidas, conceptos y aspectos
	 * @param conexion
	 * 			Metodos --- conexión para la manipulación de la base de datos
	 */
	private void initGUI ( Metodos conexion ) {
		try {
			this.setPreferredSize( new java.awt.Dimension( 1133, 481 ) );
			this.setBounds( 0, 0, 1200, 481 );
			setVisible( true );

			this.cone = conexion;
			controlc.meterconexion( conexion );
			controla.conexion( conexion );
			llenarPartidas( );
			escritorio = new JDesktopPane( );
			getContentPane( ).add( escritorio, BorderLayout.CENTER );
			escritorio.setPreferredSize( new java.awt.Dimension( 991, 643 ) );

			Pparitdas = new JPanel( );
			escritorio.add( Pparitdas, JLayeredPane.DEFAULT_LAYER );
			Pparitdas.setBounds( 6, 19, 342, 425 );
			Pparitdas.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );
			Pparitdas.setLayout( null );

			Pconceptos = new JPanel( );
			escritorio.add( Pconceptos, JLayeredPane.DEFAULT_LAYER );
			Pconceptos.setBounds( 355, 21, 819, 196 );
			Pconceptos.setLayout( null );

			Sconceptos = new JScrollPane( );
			Pconceptos.add( Sconceptos );
			Sconceptos.setBounds( 16, 29, 793, 126 );

			Tconceptos = new JTable( controlc.getModel() );
			Sconceptos.setViewportView( Tconceptos );
			TableColumn c3 = Tconceptos.getColumn( "Partida" );
			c3.setPreferredWidth( 200 );

			TableColumn c4 = Tconceptos.getColumn( "Concepto" );
			c4.setCellEditor( new DefaultCellEditor( ComponentsUser.getDataTxt( 60, 1 ) ) );
			c4.setPreferredWidth( 400 );

			Tconceptos.setRowHeight( 25 );
			llenarLconceptos( );
			llenarLAcpestosdos( );
			/**
			 * listener para llenar la tabla de aspectos dependiendo que concepto se selecciona en la tabla
			 */
			Tconceptos.addMouseListener( new java.awt.event.MouseAdapter( ) {
				/**
				 * (non-Javadoc)
				 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
				 */
				public void mouseClicked ( java.awt.event.MouseEvent evt ) {
					i = -1;
					if ( evt.isMetaDown( ) ) {

					} else {
						if ( evt.isAltDown( ) == false ) {
							i = Tconceptos.getSelectionModel( ).getLeadSelectionIndex( );
							if ( i > -1 ) {
								concep = ( ConceptoL ) controlc.getListaDatos( ).get( i );
								llenaraspectos( Integer.parseInt( concep.getIdconcepto( ) ) );
							}
						}
					}
					System.out.println(Tconceptos.getValueAt(i, 1));
				}
			} );

			lblConceptos = new JLabel( );
			Pconceptos.add( lblConceptos );
			lblConceptos.setText( "Lista de conceptos" );
			lblConceptos.setBounds( 329, 7, 146, 16 );

			ImageIcon Imagenagregar = new ImageIcon( getClass( ).getResource( "iconos/agregar.png" ) );
			Aconcepto = new JButton( );
			Pconceptos.add( Aconcepto );
			Aconcepto.setIcon( Imagenagregar );
			Aconcepto.setText( "Agregar" );
			Aconcepto.setBounds( 593, 160, 103, 30 );
			Aconcepto.addActionListener( new ActionListener( ) {
				/** 
				 * Sección para agregar un nuevo concepto
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					concep = new ConceptoL( );
					concep.setIdpartida( npartida );
					concep.setNombre( "Nuevo concepto" );
					if ( cone.insertarconcepto( concep.getNombre( ), String.valueOf( idpartida ) ) == true ) {
						llenarLconceptos( );
						llenarconceptos( idpartida, npartida );
						Tconceptos.changeSelection( controlc.Tama( ) - 1, 1, false, false );
					}
				}
			} );

			ImageIcon Imageneliminar = new ImageIcon( getClass( ).getResource( "iconos/eliminar.png" ) );
			Econcepto = new JButton( );
			Pconceptos.add( Econcepto );
			Econcepto.setIcon( Imageneliminar );
			Econcepto.setText( "Eliminar" );
			Econcepto.setBounds( 706, 160, 103, 30 );
			Econcepto.addActionListener( new ActionListener( ) {
				/**
				 * Sección para eliminar un concepto
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					int uno = Tconceptos.getSelectionModel( ).getLeadSelectionIndex( );
					if ( uno > -1 ) {
						int res = JOptionPane.showConfirmDialog( null, "Eliminar concepto", "Confirmación", JOptionPane.YES_NO_OPTION );
						if ( res == JOptionPane.YES_OPTION ) {
							concep = ( ConceptoL ) controlc.getListaDatos( ).get( uno );
							if ( cone.eliminarConcepto( concep.getIdconcepto( ) ) == true ) {
								llenarLconceptos( );
								llenarconceptos( idpartida, npartida );
							} else {
								JOptionPane.showMessageDialog( null, "Error al eliminar concepto", "Error", JOptionPane.WARNING_MESSAGE );
							}
						}
					}
				}
			} );

			Paspectos = new JPanel( );
			escritorio.add( Paspectos, JLayeredPane.DEFAULT_LAYER );
			Paspectos.setBounds( 355, 228, 819, 221 );
			Paspectos.setLayout( null );

			Saspecto = new JScrollPane( );
			Paspectos.add( Saspecto );
			Saspecto.setBounds( 16, 34, 793, 148 );

			Taspectos = new JTable( controla.getModel());
			Saspecto.setViewportView( Taspectos );
			Taspectos.setRowHeight( 60 );

			//Elementos de la tabla de aspectos
			TableColumn c41 = Taspectos.getColumn( "Clave Privada" );
			c41.setPreferredWidth( 100 );
			c41.setCellEditor( new DefaultCellEditor( ComponentsUser.getDataTxt( 15, 2 ) ) );
			
			/**
			 * Espacio para la clave pública del aspecto
			 */
			TableColumn cclavepublica = Taspectos.getColumn( "Clave Pública" );
			cclavepublica.setPreferredWidth( 100 );
			cclavepublica.setCellEditor( new DefaultCellEditor( ComponentsUser.getDataTxt( 15, 2 ) ) );

			TableColumn c5 = Taspectos.getColumn( "Descripción" );
			c5.setPreferredWidth( 400 );
			Taspectos.getColumnModel( ).getColumn( 2 ).setCellRenderer( new TextAreaRenderer( ) );

			TableColumn c6 = Taspectos.getColumn( "Unidad" );
			
			//cargar desde la base de datos 
			c6.setCellEditor( new DefaultCellEditor( fillUnits() ) );
			c6.setPreferredWidth( 100 );
			
			TableColumn c7 = Taspectos.getColumn( "Costo" );
			c7.setPreferredWidth( 100 );
			c7.setCellEditor( new DefaultCellEditor( ComponentsUser.getDataTxt( 7, 3 ) ) );

			Taspectos.setAutoResizeMode( JTable.AUTO_RESIZE_ALL_COLUMNS );
			Taspectos.addMouseListener( new java.awt.event.MouseAdapter( ) {
				/**
				 * listener para obtener el ID del aspecto seleccionado en la tabla de aspectos
				 * (non-Javadoc)
				 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
				 */
				public void mouseClicked ( java.awt.event.MouseEvent evt ) {
					if ( evt.isMetaDown( ) ) {
					} else {
						if ( evt.isAltDown( ) == false ) {
							int indi = Taspectos.getSelectionModel( ).getLeadSelectionIndex( );
							if ( indi > -1 ) {
								a = ( Aspecto ) controla.getListaDatos( ).get( indi );
								controla.meterindice( a.getIdaspecto( ) );
							}
						}
					}
				}
			} );

			lblDesglose = new JLabel( );
			Paspectos.add( lblDesglose );
			lblDesglose.setText( "Desglose de Conceptos" );
			lblDesglose.setBounds( 314, 12, 162, 16 );

			ImageIcon Imagenagregar1 = new ImageIcon( getClass( ).getResource( "iconos/agregar.png" ) );
			Aaspecto = new JButton( );
			Paspectos.add( Aaspecto );
			Aaspecto.setIcon( Imagenagregar1 );
			Aaspecto.setText( "Agregar" );
			Aaspecto.setBounds( 593, 187, 103, 30 );
			Aaspecto.addActionListener( new ActionListener( ) {
				/** 
				 * sección para agregar aspectos a los conceptos
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					if ( i > -1 ) {
						concep = ( ConceptoL ) controlc.getListaDatos( ).get( i );
						a = new Aspecto( );
						a.setIdconcepto( Integer.valueOf( concep.getIdconcepto( ) ) );
						a.setClave( "" );
						a.setUnidad( "" );
						a.setCosto( "0.0" );
						a.setDescripcion( "" );
						a.setClave_privada( "" );
						try {
							if ( cone.agregaraspecto( String.valueOf( a.getIdconcepto( ) ), a.getClave( ), a.getUnidad( ), a.getTipo( ), a.getCosto( ), a.getDescripcion( ), a.getClave_privada( ) ) ) {
								controla.anhadeFila( a );
								llenarLAcpestosdos( );
								llenaraspectos( Integer.parseInt( concep.getIdconcepto( ) ) );
								Taspectos.changeSelection( controla.Tama( ) - 1, 1, false, false );
							}
						} catch ( NumberFormatException e1 ) {
							JOptionPane.showMessageDialog( null, "Servidor fuera de servicio (Error al agregar aspecto) ", "Error", JOptionPane.ERROR_MESSAGE );
						}
					}
				}
			} );
			ImageIcon Imageneliminar1 = new ImageIcon( getClass( ).getResource( "iconos/eliminar.png" ) );
			Easpecto = new JButton( );
			Easpecto.setIcon( Imageneliminar1 );
			Paspectos.add( Easpecto );
			Easpecto.setText( "Eliminar" );
			Easpecto.setBounds( 706, 187, 103, 30 );
			
			btnSeeMore = new JButton("Ver Descripción Completa");
			btnSeeMore.setBounds(16, 187, 255, 30);
			Paspectos.add(btnSeeMore);
			
			JButton btnUnidades = new JButton("Ver Unidades de Medida");
			btnUnidades.addActionListener(new ActionListener() {
				/**
				 * TEST para catálogo de unidades de medición .........!!!!!!!!!!
				 * pendiente de comentar 
				 */
				public void actionPerformed(ActionEvent arg0) {
					VUnidadesMedida vUnit= new VUnidadesMedida();
					escritorio.add(vUnit);
					vUnit.setVisible(true);
					vUnit.setEnabled(true);
				}
			});
			btnUnidades.setBounds(341, 188, 178, 28);
			
			Paspectos.add(btnUnidades);
			btnSeeMore.addActionListener(new ActionListener() {
				/**
				 * opción para ver la descripción más completa del aspecto seleccionado
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent arg0) {
					VDescripcionCompletaDesgloseConcepto seeMoreDescription = new VDescripcionCompletaDesgloseConcepto( cone, a.getIdaspecto( ) );
					escritorio.add( seeMoreDescription );
					seeMoreDescription.setEnabled( true );
					seeMoreDescription.setVisible( true );
				}
			});
			
			Easpecto.addActionListener( new ActionListener( ) {
				/**
				 * opción para eliminar aspecto de la lista 
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					int ia = Taspectos.getSelectionModel( ).getLeadSelectionIndex( );
					if ( ia > -1 ) {
						int res = JOptionPane.showConfirmDialog( null, "Eliminar aspecto", "Pregunta", JOptionPane.YES_NO_OPTION );
						if ( res == JOptionPane.YES_OPTION ) {
							a = ( Aspecto ) controla.getListaDatos( ).get( ia );
							try {
								if ( cone.eliminaraspecto( String.valueOf( a.getIdaspecto( ) ) ) ) {
									controla.borraFila( ia );
									llenarLAcpestosdos( );
									llenaraspectos( Integer.parseInt( concep.getIdconcepto( ) ) );
								} else {
									JOptionPane.showMessageDialog( null, "Error al eliminar aspecto", "Error", JOptionPane.WARNING_MESSAGE );
								}
							} catch ( NumberFormatException e1 ) {
								JOptionPane.showMessageDialog( null, "Servidor fuera de servicio (Error al eliminar aspecto) ", "Error", JOptionPane.ERROR_MESSAGE );
							}
						}
					}
				}
			} );

			Tpartidas = new JLabel( );
			Pparitdas.add( Tpartidas );
			Tpartidas.setText( "Partidas Persupuestales" );
			Tpartidas.setBounds( 34, 6, 207, 16 );

			Spatidas = new JScrollPane( );
			Pparitdas.add( Spatidas );
			Spatidas.setBounds( 7, 27, 322, 320 );

			tab_partidas = new JTable( controlp.getModel() );
			tab_partidas.addMouseListener(new MouseAdapter() {
				/**
				 * evento clic sobre una partia para llenar los conceptos relacionados con ese nombre
				 * (non-Javadoc)
				 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
				 */
				public void mouseClicked(MouseEvent arg0) {
					indice = tab_partidas.getSelectionModel( ).getLeadSelectionIndex( );
					par = Lpartidas.get( indice );
					npartida = par.getNombre( );
					idpartida = par.getIdpartida( );
					llenarconceptos( idpartida, npartida );

				}
			});
			Spatidas.setViewportView( tab_partidas );
			
			TableColumn c2 = tab_partidas.getColumn( "Partidas Prepuestales" );
			c2.setPreferredWidth( 320 );
			tab_partidas.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
			
			

			ImageIcon Imagenagregar11 = new ImageIcon( getClass( ).getResource( "iconos/agregar.png" ) );
			Apartida = new JButton( );
			Pparitdas.add( Apartida );
			Apartida.setIcon( Imagenagregar11 );
			Apartida.setText( "Agregar" );
			Apartida.setBounds( 7, 352, 103, 30 );
			Apartida.addActionListener( new ActionListener( ) {
				/**
				 * opción para agregar una partida
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					JTextField txtField = ComponentsUser.getDataTxt( 45, 1 );
					int result = JOptionPane.showConfirmDialog( null, txtField, "Ingrese Nuevo Tipo de Partida", JOptionPane.OK_CANCEL_OPTION );
					switch ( result ) {
						case 0 :
							try {
								String newtype = txtField.getText( ).trim( );
								if ( Boolean.valueOf( cone.existepartida( newtype ) ).equals( false ) ) {
									if ( Boolean.valueOf( newtype.equals( "" ) ).equals( true ) ) {
										JOptionPane.showMessageDialog( null, "No se ha Ingresado Ningún Dato" );
									} else {
										if ( cone.insertarPartida( newtype ) ) {
											Limpiar( );
											llenarPartidas( );
										}
									}
								} else {
									JOptionPane.showMessageDialog( null, "La partida ya existe", "Error", JOptionPane.WARNING_MESSAGE );
								}
							} catch ( Exception e2 ) {
								System.out.println( e2.toString( ) );
							}
							break;
						default :
							JOptionPane.showMessageDialog( null, "Operación Cancelada por el Usuario" );
							break;
					}
				}// fin de action performed
			} );// fin de action listener

			ImageIcon Imageneliminar11 = new ImageIcon( getClass( ).getResource( "iconos/eliminar.png" ) );
			Epartida = new JButton( );
			Pparitdas.add( Epartida );
			Epartida.setIcon( Imageneliminar11 );
			Epartida.setText( "Eliminar" );
			Epartida.setBounds( 117, 353, 103, 30 );
			Epartida.addActionListener( new ActionListener( ) {
				/**
				 * opción para eliminar una partida
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					if ( indice > -1 ) {
						int opc = JOptionPane.showConfirmDialog( null, "Desea eliminar  partida", "Confirmar", JOptionPane.YES_NO_OPTION );
						if ( opc == JOptionPane.YES_OPTION ) {
							par = Lpartidas.get( indice );
							if ( cone.eliminarPartida( String.valueOf( par.getIdpartida( ) ) ) == true ) {
								llenarPartidas( );
							} else {
								JOptionPane.showMessageDialog( null, "Error al eliminar partida", "Error", JOptionPane.WARNING_MESSAGE );
							}
						}
					}
				}
			} );

			ImageIcon Imagenmodificar = new ImageIcon( getClass( ).getResource( "iconos/modificar.png" ) );
			Pmodficar = new JButton( );
			Pparitdas.add( Pmodficar );
			Pmodficar.setIcon( Imagenmodificar );
			Pmodficar.setText( "Modificar" );
			Pmodficar.setBounds( 224, 354, 112, 30 );
			Pmodficar.addActionListener( new ActionListener( ) {
				/**
				 * opcion para modificar el nombre de una partida
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					JTextField textField = ComponentsUser.getDataTxt( 45, 1 );
					int result = JOptionPane.showConfirmDialog( null, textField, "Ingresa el Nuevo Nombre: ", JOptionPane.OK_CANCEL_OPTION );
					switch ( result ) {
						case 0 :
							try {
								String newName = textField.getText( ).trim( );
								if ( Boolean.valueOf( cone.existepartida( newName ) ).equals( false ) ) {
									if ( Boolean.valueOf( newName.equals( "" ) ).equals( true ) ) {
										JOptionPane.showMessageDialog( null, "No se ha Ingresado Ningún Dato" );
									} else {
										if ( cone.modificarPartida( newName, String.valueOf( par.getIdpartida( ) ) ) ) {
											Limpiar( );
											llenarPartidas( );
											tab_partidas.repaint();
											
										}
									}
								} else {
									JOptionPane.showMessageDialog( null, "Este Nombre ya Existe", "Atención", JOptionPane.WARNING_MESSAGE );
								}
							} catch ( Exception e2 ) {
								System.out.println( e2.toString( ) );
							}
							break;
						default :
							break;
					}
				}
			} );

			importar = new JButton( );
			Pparitdas.add( importar );
			importar.setText( "Importar " );
			importar.setEnabled(false);
			importar.setVisible(false);
			importar.setBounds( 224, 395, 98, 23 );
			importar.addActionListener( new ActionListener( ) {
				/**
				 * opción para importar conceptos //no documentada
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					String idpar = "0", idcon = "0", idas = "0";
					int r = 0, nivel = 0;
					;
					int opc = JOptionPane.showConfirmDialog( null, "Continuar", "Confirmar", JOptionPane.YES_NO_OPTION );
					if ( opc == JOptionPane.YES_OPTION ) {
						Objetoexecel fila;
						JFileChooser navega = new JFileChooser( );
						navega.showOpenDialog( Spatidas );
						String path = navega.getSelectedFile( ).getAbsolutePath( );
						if ( path != null && path.equals( "" ) == false ) {
							Importar imp = new Importar( );
							LinkedList < Objetoexecel > lis = new LinkedList < Objetoexecel >( );
							lis = imp.leer_archivo_exel( path );
							if ( lis != null && lis.size( ) > 0 ) {
								while ( r < lis.size( ) ) {
									fila = lis.get( r );
									if ( fila.getTitulo( ).equals( "Capítulo" ) ) {
										idpar = cone.existepartidaid( fila.getDescripcion( ) );
										if ( idpar == "-1" ) {
											idpar = cone.insertarPartidaex( fila.getDescripcion( ) );
											if ( idpar.equals( "0" ) ) {
												r = lis.size( ) + 10;
											} else {
												nivel = 1;
											}
										}
										if ( idpar.equals( "0" ) == false ) {
											nivel = 1;
										}
									}
									if ( fila.getTitulo( ).equals( "Subcapítulo" ) ) {
										idcon = cone.existeconceptoex( fila.getDescripcion( ) );
										if ( idcon.equals( "0" ) ) {
											idcon = cone.insertarconceptoex( idpar, fila.getDescripcion( ) );
											if ( idcon.equals( "0" ) ) {
												r = lis.size( ) + 10;
											}
											nivel = 3;
										}
										if ( idcon.equals( "0" ) == false ) {
											nivel = 3;
										}
									}
									if ( fila.getTitulo( ).equals( "Concepto" ) ) {
										if ( nivel == 1 ) {
											fila = lis.get( r - 1 );
											idcon = cone.existeconceptoex( fila.getDescripcion( ) );
											if ( idcon.equals( "0" ) ) {
												idcon = cone.insertarconceptoex( idpar, fila.getDescripcion( ) );
												if ( idcon.equals( "0" ) ) {
													r = lis.size( ) + 10;
												}
												nivel = 3;
												r++;
											}
											if ( idcon.equals( "0" ) == false ) {
												nivel = 3;
											}
										}
										boolean inse;
										if ( nivel == 3 ) {
											fila = lis.get( r );
											idas = cone.existeaspectoex( fila.getClave( ) );
											if ( idas.equals( "0" ) ) {
												String cadena = fila.getDescripcion( );
												cadena = cadena.replaceAll( "'", "''" );
												String cadecos = fila.getCosto( );
												cadecos = cadecos.replaceAll( "'", "" );
												cadecos = cadecos.replaceAll( ",", "" );
												inse = cone.agregaraspecto( idcon, fila.getClave( ), fila.getUnidad( ), "MAT", cadecos, cadena,"");
												if ( inse == false ) {
													r = lis.size( ) + 10;
												}
											}
											if ( idas.equals( "0" ) == false ) {
												String cadena = fila.getDescripcion( );
												cadena = cadena.replaceAll( "'", "''" );
												String cadecos = fila.getCosto( );
												cadecos = cadecos.replaceAll( "'", "" );
												cadecos = cadecos.replaceAll( ",", "" );
												inse = cone.modificaraspecto( fila.getClave( ), fila.getUnidad( ), cadecos, cadena, idas, "");
												if ( inse == false ) {
													r = lis.size( ) + 10;
												}
												JOptionPane.showMessageDialog( null, "Servidor fuera de servicio (Error insertar aspecto) ", "Error", JOptionPane.ERROR_MESSAGE );
											}
										}
									}
									r++;
								}
								if ( r == lis.size( ) ) {
									JOptionPane.showMessageDialog( null, "Exito al exportar la base de datos" );
									Limpiar( );
									llenarPartidas( );
									llenarLconceptos( );
									llenarLAcpestosdos( );
								}
							}
						}
					}
				}
			} );
		} catch ( Exception e ) {
			e.printStackTrace( );
			System.out.println( e.toString( ) );
		}
	}

	
	/**
	 * Método para llenar la lista de Unidades de medida desde la base de datos
	 */
	public JComboBox< String > fillUnits() {
		JComboBox<String> unidadesBox = new JComboBox<String>();
		try {
			for (Unidad unidad : CUnidad.findUnidades()) {
				unidadesBox.addItem(unidad.getNombre());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			e.toString(); 
		}
		return unidadesBox;
	}
	
	/**
	 * Método para llenar la lista de partidas desde la base de datos
	 */
	public void llenarPartidas ( ) {
		Limpiar( );
		Lpartidas = cone.getPartidas( );
		for ( int i = 0 ; i < Lpartidas.size( ) ; i++ ) {
			par = Lpartidas.get( i );
			controlp.anhadeFila( par.getNombre( ) );
			System.out.println(par.getNombre( ));
		}
	}

	/**
	 * Método para limpiar el contenido de la tabla de partidas 
	 */
	public void Limpiar ( ) {
		int tama = controlp.Tama( ) - 1;
		if ( tama > -1 ) {
			while ( tama >= 0 ) {
				controlp.borraFila( tama );
				tama--;
			}
		}
	}

	/**
	 * Método para limpiar el contenido de tabla de conceptos 
	 */
	public void Limpiarconceptos ( ) {
		int tama = controlc.Tama( ) - 1;
		if ( tama > -1 ) {
			while ( tama >= 0 ) {
				controlc.borraFila( tama );
				tama--;
			}
		}
	}

	/**
	 * Método para llenar la lista de conceptos de la base de datos 
	 */
	private void llenarLconceptos ( ) {
		Lconceptos = cone.GetConceptos( );
	}

	/**
	 * Método para mostrar los conceptos relacionados con la partida seleccionada
	 * @param indip --- identificador de la partida
	 * @param nombre --- nombre de la partida
	 */
	private void llenarconceptos ( int indip , String nombre ) {
		Limpiarconceptos( );
		for ( int i = 0 ; i < Lconceptos.size( ) ; i++ ) {
			c = Lconceptos.get( i );
			if ( c.getIdpartida( ) == indip ) {
				concep = new ConceptoL( );
				concep.setIdconcepto( String.valueOf( c.getIdconcepto( ) ) );
				concep.setIdpartida( nombre );
				concep.setNombre( c.getNombre( ) );
				controlc.anhadeFila( concep );
				controlc.meterIndice( par.getIdpartida( ) );
			}
		}
	}

	/**
	 * Método para llenar la lista de aspectos desde la base de datos
	 */
	private void llenarLAcpestosdos ( ) {
		Laspectos = cone.GetAspectos( );
	}

	/**
	 * Método para llenar los aspectos dependiendo que concepto es seleccionado 
	 * @param indip
	 */
	private void llenaraspectos ( int indip ) {
		Limpiaraspecto( );
		for ( int i = 0 ; i < Laspectos.size( ) ; i++ ) {
			a = Laspectos.get( i );
			if ( a.getIdconcepto( ) == indip ) {
				asp = new Aspecto( );
				asp = a;
				controla.anhadeFila( a );
			}
		}
	}

	/**
	 * Método para limpiar los aspectos de la tabla
	 */
	public void Limpiaraspecto ( ) {
		int tama = controla.Tama( ) - 1;
		if ( tama > -1 ) {
			while ( tama >= 0 ) {
				controla.borraFila( tama );
				tama--;
			}
		}
	}

	/**
	 * clase para mostrar un JText Area en la descripcion del concepto  
	 */
	class TextAreaRenderer extends JTextArea implements TableCellRenderer {
		private static final long serialVersionUID = 1L;

		/**
		 * constructor que crea el JTextField
		 */
		public TextAreaRenderer ( ) {
			setLineWrap( true );
			setWrapStyleWord( true );
			setDocument( new LenString( 500 ) );
		}

		/**
		 * Método par obtener el componente con formato 
		 * (non-Javadoc)
		 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
		 */
		public Component getTableCellRendererComponent ( JTable jTable , Object obj , boolean isSelected , boolean hasFocus , int row , int column ) {
			setText( ( String ) obj );
			return this;
		}
	}
}