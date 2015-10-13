import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;

import Manejotablas.MyTableModel;
import Excel.Generarexcel;
import Excel.Genrargeneral;
import ObjetosSerializables.Aspecto;
import ObjetosSerializables.Concepto;
import ObjetosSerializables.Consultor;
import ObjetosSerializables.Estimacion;
import ObjetosSerializables.Frente;
import ObjetosSerializables.Partida;
import ObjetosSerializables.Plantilla;
import ObjetosSerializables.Proyecto;
import ObjetosSerializables.Rgenerador;
import Options.ComponentsUser;
import PDF.Generarpdf;
import Tablaconceptos.ModeloConceptoSeleccion;
import TablasPartidas.ControPartida;
import Manejodetablas2.ControlTableGenerador;
import Manejodetablas2.ModeloTablaGenerador;
import MetodosRemotos.Metodos;

import java.awt.event.MouseAdapter;

/**
 * Clase para realizar la estimacion inicial a un frente de un proyecto seleccionado
 * @author Pablo Rivera
 *
 */
public class Npresupuesto extends JInternalFrame {

	/**
     * elementos para la creacion de la ventana 
     */
	private static final long serialVersionUID = 1L;

	private JDesktopPane escritorio;
	private JPanel Pparitdas;
	private JPanel Pconcepto;
	private JLabel etiqueta;
	private JScrollPane Ddescripcion;
	@SuppressWarnings("unused")
	private int indi;
	private JTable generadores;
	private JTable TDescripcion;
	private JLabel efecha;
	private JLabel eexportar;
	private JComboBox<String> Copciones;
	private JButton plantilla;
	private JLabel ope;
	private JComboBox<String> Coperaciones;
	private JPanel Poperaciones;
	private JScrollPane SPartidas;
	private JButton verplano;
	private JComboBox<String> Cfrente;
	private JLabel Tfrente;
	private JComboBox<String> consultor;
	private JLabel jLabel1;
	private JComboBox<String> Cproyecto;
	private JLabel Econsultor;
	private JPanel Pcosultor;
	private JScrollPane Pgenerador;
	private JLabel fecha;
	private JLabel presupuesto;
	private JPanel Ptabla;
	private JTable tablap;
	private JDateChooser finicial;

	private LinkedList<Partida> Lpartidas = new LinkedList<Partida>();
	private LinkedList<Proyecto> ListaProyectos = new LinkedList<Proyecto>();
	private Vector<Consultor> ListaConsultores = new Vector<Consultor>();
	private LinkedList<Frente> Listafrentes = new LinkedList<Frente>();
	private LinkedList<Concepto> Lconceptos = new LinkedList<Concepto>();
	private LinkedList<Aspecto> Laspectos = new LinkedList<Aspecto>();
	private LinkedList<Estimacion> Lestimacion = new LinkedList<Estimacion>();

	private Metodos cone;
	private ModeloTablaGenerador modelo2 = new ModeloTablaGenerador();
	private ControlTableGenerador control = new ControlTableGenerador(modelo2);
	private Proyecto pro = new Proyecto();
	private Consultor consul = new Consultor();
	private Frente fre = new Frente();
	private Concepto concep = new Concepto();
	private Aspecto asp2 = new Aspecto();
	private Partida par = new Partida();
	@SuppressWarnings("unused")
	private boolean ban = false, ban2 = false, entrouno = false;
	private int idpartida = 0;
	private int idestimacion = 0;
	private int idfrente = -1;
	private int idconsultor = -1;
	private int idproyecto = -1;
	private int idconcepto;
	private int repeticion = -1;
	private JPopupMenu pMenu;
	private JMenuItem agregar, eliminar;
	private int indice;
	private String des;
	private Lplano pla;
	@SuppressWarnings("unused")
	private Genrargeneral general;
	private Estimaciones esti;
	private GuardarEstimacion gestimacion;
	private Elegirplantilla vpla;
	//private ModeloPartida modelop = new ModeloPartida();
	private ControPartida controlp = new ControPartida();
	private String fechaestimacion = "";
	private JTable tab_conceptos;

	/**
	 * constructor de la clase  
	 * 
	 * @param escritorio2 -- JDesktopPane de la aplicación principal
	 * @param conexion -- conexión a la base de datos 
	 */
	public Npresupuesto(JDesktopPane escritorio2, Metodos conexion) {
		super("Estimaciones Iniciales", false, true, false, true);
		// Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		initGUI(escritorio2, conexion);
	}

	/**
	 * inicio de la GUI
	 * 
	 * @param escritorio2 -- JDesktopPane de la aplicación principal
	 * @param conexion -- conexión a la base de datos
	 */
	private void initGUI(final JDesktopPane escritorio2, Metodos conexion) {
		try {
			this.setPreferredSize(new java.awt.Dimension(227, 190));
			this.setBounds(0, 0, 227, 190);
			this.setPreferredSize(new java.awt.Dimension(1275, 663));
			this.setBounds(0, 0, 1275, 663);
			setVisible(true);

			this.cone = conexion;
			escritorio = new JDesktopPane();
			llenarParitdas();
			getContentPane().add(escritorio, BorderLayout.CENTER);

			Pparitdas = new JPanel();
			escritorio.add(Pparitdas, JLayeredPane.DEFAULT_LAYER);
			Pparitdas.setBounds(17, 64, 260, 275);
			Pparitdas.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
			Pparitdas.setLayout(null);

			SPartidas = new JScrollPane();
			Pparitdas.add(SPartidas);
			SPartidas.setPreferredSize(new java.awt.Dimension(244, 222));
			SPartidas.setBounds(8, 6, 244, 222);

			tablap = new JTable(controlp.getModel());
			SPartidas.setViewportView(tablap);
			TableColumn col = tablap.getColumn("Partidas Prepuestales");
			col.setPreferredWidth(265);
			tablap.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tablap.setEnabled(false);

			tablap.addMouseListener(new MouseAdapter() {
				private LinkedList<Rgenerador> Lseleccion = new LinkedList<Rgenerador>();
				/**
				 * clic en la tabla de partidas 
				 */
				@Override
				public void mouseClicked(MouseEvent e) {
					//verificacion de las opciones "agregar", "eliminar", "modificar" disponibles para el usuario
					if (Coperaciones.getSelectedIndex() == 1) {
						if (esti.Indiceselecionado() == -1) {
							JOptionPane.showMessageDialog(null, "No selecionaste ninguna estimacion");
						} else {
							idestimacion = esti.Indiceselecionado();
						}
					}
					// *************************************************************************************************************************************
					if (control.Tama() > 0) {
						if (idpartida > 0) {
							if (idfrente > 0) {
								// *******************************************************************************************
								//verificacion de la informacion del aspecto seleccionado 
								Rgenerador asp = new Rgenerador();
								for (int i = 0; i < control.getLista().size(); i++) {
									asp = (Rgenerador) control.getLista().get(i);
									if (asp.getX().equals(" ") == true) {
										asp.setX("0");
									}
									if (asp.getY().equals(" ") == true) {
										asp.setY("0");
									}
									if (asp.getZ().equals(" ") == true) {
										asp.setZ("0");
									}
									ban2 = cone.estimacionaspecto(asp.getIdaspecto(), String.valueOf(idestimacion), asp.getPiezas(), asp.getImporte(), asp.getX(), asp.getY(), asp.getZ(), asp.getAlto(), asp.getLargo(), asp.getAncho(), asp.getCosto(), String.valueOf(idpartida), String.valueOf(asp.getRepeticion()), fechaestimacion);
								}
								// ***********************************************************************************************
							} else {
								JOptionPane.showMessageDialog(null, "No tienes selecionado ningun frente");
							}
						}
					} else {
						ban2 = true;
					}
					// *************************************************************************************************************************************
					if (ban2 == true) {
						//si no existen aspectos seleccionados en el frente  se llenan los conceptos para desplegarlos en pantalla al usuario
						indice = tablap.getSelectionModel().getLeadSelectionIndex();
						par = (Partida) Lpartidas.get(indice);
						idpartida = par.getIdpartida();
						llenarconceptos(idpartida);
						if (idestimacion > 0) {
							//se obtienen los aspectos de la base de datos que estan incluidos en la seleccion
							Lseleccion = cone.general(String.valueOf(idestimacion), String.valueOf(idpartida));
							llenarconceptos(idpartida);
							Limpiar();
							Rgenerador asp;
							Rgenerador a = new Rgenerador();
							;
							// ****************************************************************************************************************************************************
							for (int i = 0; i < Lseleccion.size(); i++) {
								asp = new Rgenerador();
								a = (Rgenerador) Lseleccion.get(i);
								asp.setIdaspecto(a.getIdaspecto());
								asp.setClave(a.getClave());
								asp.setDescripcion(a.getDescripcion());
								asp.setUnidad(a.getUnidad());
								asp.setX(a.getX());
								asp.setY(a.getY());
								asp.setZ(a.getZ());
								asp.setAlto(a.getAlto());
								asp.setLargo(a.getLargo());
								asp.setAncho(a.getAncho());
								float al, l, an, re;
								if (a.getUnidad().equals("m2") == true || a.getUnidad().equals("M2") == true) {
									an = Float.parseFloat(a.getAncho());
									l = Float.parseFloat(a.getLargo());
									re = an * l;
									asp.setCantidad(String.valueOf(re));
								}
								if (a.getUnidad().equals("m3") == true) {
									an = Float.parseFloat(a.getAncho());
									l = Float.parseFloat(a.getLargo());
									al = Float.parseFloat(a.getAlto());
									re = (an * l) * al;
									asp.setCantidad(String.valueOf(re));
								}

								if (a.getUnidad().equals("pieza") == true || a.getUnidad().equals("PZA") == true) {
									asp.setCantidad(a.getPiezas());
								}

								if (a.getUnidad().equals("kg") == true || a.getUnidad().equals("ton") == true || a.getUnidad().equals("LOTE") == true || a.getUnidad().equals("SALIDA") == true) {
									asp.setCantidad(a.getPiezas());
								}

								if (a.getUnidad().equals("ML") == true || a.getUnidad().equals("ml") == true) {
									asp.setCantidad(a.getLargo());
								}

								asp.setPiezas(a.getPiezas());
								asp.setCosto(a.getCosto());
								asp.setImporte(a.getImporte());
								asp.setRepeticion(a.getRepeticion());
								asp.setFecha(a.getFecha());
								control.anhadeFila(asp);
							}
							// ****************************************************************************************************************************************************
						}
					}
					indice = tablap.getSelectionModel().getLeadSelectionIndex();
					par = (Partida) Lpartidas.get(indice);
					llenarconceptos(par.getIdpartida());
				}
			});

			plantilla = new JButton();
			Pparitdas.add(plantilla);
			plantilla.setText("Crear plantilla");
			plantilla.setBounds(73, 234, 105, 28);
			plantilla.addActionListener(new ActionListener() {
				/**
				 * clic en el boton para crear una plantilla con los aspectos seleccionados en el frente 
				 * 
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					if (idestimacion > 0) {
						if (gestimacion == null || gestimacion.isClosed()) {
							// ****************************************************************
							try {
								//se optiene la lista de aspectos seleccionados en la estimacion 
								LinkedList<Rgenerador> desplegar = new LinkedList<Rgenerador>();
								desplegar = cone.reportegeneral(String.valueOf(idestimacion));
								if (desplegar.size() > 0) {
									// se guarda la estimacion como plantilla para ser usada en otro momento
									gestimacion = new GuardarEstimacion(desplegar, cone);
									escritorio2.add(gestimacion);
									gestimacion.setSelected(true);
								} else {
									JOptionPane.showMessageDialog(null, "Tu estimacinin no contiene datos");
								}
							} catch (Exception a) {
							}
							// ****************************************************************
						} else {
							try {
								gestimacion.setSelected(true);
							} catch (PropertyVetoException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			});

			Pconcepto = new JPanel();
			escritorio.add(Pconcepto, JLayeredPane.DEFAULT_LAYER);
			Pconcepto.setBounds(282, 66, 972, 275);
			Pconcepto.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));

			Ptabla = new JPanel();
			escritorio.add(Ptabla, JLayeredPane.DEFAULT_LAYER);
			Ptabla.setBounds(17, 341, 1240, 285);
			Ptabla.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
			Ptabla.setLayout(null);

			Pcosultor = new JPanel();
			escritorio.add(Pcosultor, JLayeredPane.DEFAULT_LAYER);
			Pcosultor.setBounds(284, 5, 970, 59);
			Pcosultor.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
			Pcosultor.setLayout(null);

			jLabel1 = new JLabel();
			Pcosultor.add(jLabel1);
			jLabel1.setText("Consultor:");
			jLabel1.setBounds(220, 7, 90, 16);

			consultor = new JComboBox<String>();
			Pcosultor.add(consultor);
			llenarconsultor();
			llenarLFrentes();
			llenarLconceptos();
			llenarLAcpestosdos();
			consultor.setBounds(214, 27, 248, 25);
			consultor.addActionListener(new ActionListener() {
				/**
				 * clic en el nombre de un consultor,
				 * sólo habilita el combo box de proyectos
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					if (consultor.getSelectedIndex() > -1) {
						Consultor con = (Consultor) ListaConsultores.get(consultor.getSelectedIndex());
						idconsultor = con.getIdconsultor();
						Cproyecto.setEnabled(true);
					}
				}
			});

			consultor.setEnabled(false);

			Econsultor = new JLabel();
			Pcosultor.add(Econsultor);
			Econsultor.setText("Proyecto:");
			Econsultor.setName("Eproyecto");
			Econsultor.setBounds(484, 7, 80, 16);

			Cproyecto = new JComboBox<String>();
			Pcosultor.add(Cproyecto);
			llenarproyectos();
			Cproyecto.setBounds(478, 27, 271, 25);
			Cproyecto.addActionListener(new ActionListener() {
				/**
				 * clic en el nombre de un proyecto, 
				 * solo habilita el combo box de frentes
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					if (Cproyecto.getSelectedIndex() > -1) {
						Proyecto pro = new Proyecto();
						pro = (Proyecto) ListaProyectos.get(Cproyecto.getSelectedIndex());
						idproyecto = pro.getIdproyecto();
						llenarfrentes(idproyecto);
						Cfrente.setEnabled(true);
					}
				}
			});
			Cproyecto.setEnabled(false);

			Tfrente = new JLabel();
			Pcosultor.add(Tfrente);
			Tfrente.setText("Frentes:");
			Tfrente.setBounds(766, 7, 45, 16);

			Cfrente = new JComboBox<String>();
			Pcosultor.add(Cfrente);
			Cfrente.setEnabled(false);
			Cfrente.setBounds(766, 26, 190, 26);

			efecha = new JLabel();
			Pcosultor.add(efecha);
			efecha.setText("Fecha: ");
			efecha.setBounds(19, 7, 40, 16);

			ImageIcon Imagenfecha = new ImageIcon("C:/Users/pabluchis/workspace/Generadores/src/iconos/reloj.png");
			finicial = new JDateChooser();
			finicial.setIcon(Imagenfecha);
			Pcosultor.add(finicial);
			finicial.setBounds(16, 24, 180, 30);
			Calendar c1 = new GregorianCalendar();
			String cm = "";
			int dia = c1.get(Calendar.DATE);
			int mes = c1.get(Calendar.MONTH) + 1;
			if (mes < 10) {
				cm = "0" + String.valueOf(mes);
			} else {
				cm = String.valueOf(mes);
			}
			int a = c1.get(Calendar.YEAR);
			String fe = String.valueOf(dia) + "-" + cm + "-" + String.valueOf(a);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date uno = sdf.parse(fe);
			finicial.setDate(uno);

			Cfrente.addActionListener(new ActionListener() {
				/**
				 * clic en el nombre de un frente,
				 * crea una estimacion con la informacions seleccionada con anterioridad (consultor,proyecto)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent evt) {
					int anio, mes, dia;// , existe = 0;
					String ca = "", cm = "", cd = "";
					int c = -1;
					String fe = "";
					if (Cfrente.getSelectedIndex() > 0) {
						Frente fre = new Frente();
						;
						for (int i = 0; i < Listafrentes.size(); i++) {
							fre = (Frente) Listafrentes.get(i);
							if (fre.getIndentificador().equals(String.valueOf(Cfrente.getSelectedItem())) == true) {
								c = fre.getIdfrente();
							}
						}
						// ****************************************************************************************************************************************
						if (finicial.getDate() != null) {
							anio = finicial.getCalendar().get(Calendar.YEAR);
							mes = finicial.getCalendar().get(Calendar.MONTH) + 1;
							dia = finicial.getCalendar().get(Calendar.DAY_OF_MONTH);
							ca = String.valueOf(anio);
							if (mes < 10) {
								cm = "0" + String.valueOf(mes);
							} else {
								cm = String.valueOf(mes);
							}
							if (dia < 10) {
								cd = "0" + String.valueOf(dia);
							} else {
								cd = String.valueOf(dia);
							}
							fe = ca + "-" + cm + "-" + cd;
							fechaestimacion = fe;
						} else {
							JOptionPane.showMessageDialog(null, "No has seleccionado fecha inicial");
						}
						// ***************************************************************************************************************************************
						idfrente = c;
						if (c > 0) {
							tablap.setEnabled(true);
						}
						// *************************************************************************************************************************************************************************************************************************
						if (c > 0 && Coperaciones.getSelectedIndex() == 0) {
							Lestimacion = cone.sacarPreinicial(String.valueOf(c), "I");
							if (Lestimacion.size() == 0) {
								int res = JOptionPane.showConfirmDialog(null, "Deseas utilizar una plantilla", "Confirmacion", JOptionPane.YES_NO_OPTION);
								if (res == JOptionPane.NO_OPTION) {
									idestimacion = cone.insertarestimacion(String.valueOf(idfrente), String.valueOf(idconsultor), "0.0", fe, "I", "0");
									if (idestimacion > 0) {
										JOptionPane.showMessageDialog(null, "Estimacion creada");
									}
								} else {
									if (res == JOptionPane.YES_OPTION) {
										idestimacion = -1;
										LinkedList<Plantilla> pla = new LinkedList<Plantilla>();
										pla = cone.consultarPlantillas();
										if (pla.size() > 0) {
											try {
												idestimacion = cone.insertarestimacion(String.valueOf(idfrente), String.valueOf(idconsultor), "0.0", fe, "I", "0");
												vpla = new Elegirplantilla(pla, cone, idestimacion, fechaestimacion);
												escritorio2.add(vpla);
												vpla.setSelected(true);
											} catch (Exception e) {
												e.printStackTrace();
												idestimacion = -1;
											}
										} else {
											idestimacion = cone.insertarestimacion(String.valueOf(idfrente), String.valueOf(idconsultor), "0.0", fe, "I", "0");
										}
										// if(idestimacion>0){JOptionPane.showMessageDialog(null,"No hay plantillas desponibles,estimacion creada");
										// }
									} else {
										idestimacion = -1;
									}
								}
								if (idestimacion > -1) {
									consultor.setEnabled(false);
									Cproyecto.setEnabled(false);
									Cfrente.setEnabled(false);
								}
							} else {
								int res = JOptionPane.showConfirmDialog(null, "Este frente tiene: " + Lestimacion.size() + " estimaciones iniciales deseas crear una nueva", "Confirmar", JOptionPane.YES_NO_OPTION);
								if (res == JOptionPane.YES_OPTION) {
									int r = JOptionPane.showConfirmDialog(null, "Deseas utilizar una plantilla", "Confirmacion", JOptionPane.YES_NO_OPTION);
									if (r == JOptionPane.NO_OPTION) {
										idestimacion = cone.insertarestimacion(String.valueOf(idfrente), String.valueOf(idconsultor), "0.0", fe, "I", "0");
									} else {
										if (r == JOptionPane.YES_OPTION) {
											idestimacion = -1;
											LinkedList<Plantilla> pla = new LinkedList<Plantilla>();
											pla = cone.consultarPlantillas();
											if (pla.size() > 0) {
												try {
													idestimacion = cone.insertarestimacion(String.valueOf(idfrente), String.valueOf(idconsultor), "0.0", fe, "I", "0");
													vpla = new Elegirplantilla(pla, cone, idestimacion, fechaestimacion);
													escritorio2.add(vpla);
													vpla.setSelected(true);
												} catch (Exception e) {
													e.printStackTrace();
													idestimacion = -1;
												}
											} else {
												idestimacion = cone.insertarestimacion(String.valueOf(idfrente), String.valueOf(idconsultor), "0.0", fe, "I", "0");
											}
											// if(idestimacion>0){JOptionPane.showMessageDialog(null,"No hay plantillas desponibles,estimacion creada");}
										} else {
											idestimacion = -1;
										}
									}
									if (idestimacion > -1) {
										consultor.setEnabled(false);
										Cproyecto.setEnabled(false);
										Cfrente.setEnabled(false);
									}

								}

							}

						}
						// ******************************************************************************************************************************************************************************************************************************************************
						if (c > 0 && Coperaciones.getSelectedIndex() == 1) {
							Lestimacion = cone.sacarPreinicial(String.valueOf(c), "I");
							if (Lestimacion.size() == 0) {
								JOptionPane.showMessageDialog(null, "Este frente no tiene ninguna estimacinin inicial", "verifica", JOptionPane.WARNING_MESSAGE);
							} else {
								try {
									esti = new Estimaciones(Lestimacion, cone, "modificar");
									escritorio2.add(esti);
									esti.setSelected(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
								Cproyecto.setEnabled(false);
								Cfrente.setEnabled(false);
							}
						}
						// ***********************************************************************************************************************************************************************************************************************************************************
						if (c > 0 && Coperaciones.getSelectedIndex() == 2) {
							tablap.setEnabled(false);
							Lestimacion = cone.sacarPreinicial(String.valueOf(c), "I");

							if (Lestimacion.size() == 0) {
								JOptionPane.showMessageDialog(null, "Este frente no tiene ninguna estimacinin inicial", "verifica", JOptionPane.WARNING_MESSAGE);
							} else {
								try {
									esti = new Estimaciones(Lestimacion, cone, "eliminar");
									escritorio2.add(esti);
									esti.setSelected(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
								Cproyecto.setEnabled(false);
								Cfrente.setEnabled(false);
							}
						}

						// ***********************************************************************************************************************************************************************************************************************************************************

					}
				}
			});

			Poperaciones = new JPanel();
			escritorio.add(Poperaciones, JLayeredPane.DEFAULT_LAYER);
			Poperaciones.setBounds(17, 4, 260, 58);
			Poperaciones.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
			Poperaciones.setLayout(null);

			Coperaciones = new JComboBox<String>();
			Coperaciones.addItem("Agregar");
			Coperaciones.addItem("Modificar");
			Coperaciones.addItem("Eliminar");
			Coperaciones.setSelectedIndex(-1);
			Coperaciones.addActionListener(new ActionListener() {
				/**
				 * validación para las opciones permitidas al usuario
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					if (Coperaciones.getSelectedIndex() == 0) {
						consultor.setEnabled(true);
						Cproyecto.setEnabled(false);
						Cfrente.setEnabled(false);
						tablap.setEnabled(false);

						Limpiar();
					}
					if (Coperaciones.getSelectedIndex() == 1) {
						consultor.setEnabled(false);
						Cproyecto.setEnabled(true);
						Cfrente.setEnabled(false);
						tablap.setEnabled(false);
						Limpiar();
						plantilla.setEnabled(true);

					}
					if (Coperaciones.getSelectedIndex() == 2) {
						consultor.setEnabled(false);
						Cproyecto.setEnabled(true);
						Cfrente.setEnabled(false);
						tablap.setEnabled(false);
						Limpiar();
					}
				}
			});
			Poperaciones.add(Coperaciones);
			Coperaciones.setBounds(9, 23, 239, 26);

			ope = new JLabel();
			Poperaciones.add(ope);
			ope.setText("Acción:");
			ope.setBounds(16, 4, 53, 16);

			Pgenerador = new JScrollPane();
			Ptabla.add(Pgenerador);
			Pgenerador.setPreferredSize(new java.awt.Dimension(1216, 239));
			Pgenerador.setBounds(12, 6, 1216, 239);

			pMenu = new JPopupMenu();
			agregar = new JMenuItem("Agregar");
			agregar.addActionListener(new ActionListener() {
				/**
				 * clic en agregar un aspecto a la estimacion inicial 
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					int i = 0;
					if (indice > -1) {
						// *****************************************************************************************
						//se agrega una fila con la informacion del aspecto seleccionado 
						Rgenerador asp = new Rgenerador();
						Rgenerador a = new Rgenerador();
						for (i = 0; i < Laspectos.size(); i++) {
							asp2 = (Aspecto) Laspectos.get(i);
							if (asp2.getDescripcion().equals(des) == true) {
								asp.setIdaspecto(String.valueOf(asp2.getIdaspecto()));
								asp.setClave(asp2.getClave());
								asp.setDescripcion("");
								asp.setUnidad(asp2.getUnidad());
								asp.setX(" ");
								asp.setY(" ");
								asp.setZ(" ");
								asp.setAlto("0");
								asp.setLargo("0");
								asp.setAncho("0");
								asp.setCantidad("0");
								asp.setPiezas("0");
								asp.setCosto(asp2.getCosto());
								asp.setImporte("0");
								asp.setFecha(fechaestimacion);
								for (int j = 0; j < control.getLista().size(); j++) {
									a = (Rgenerador) control.getLista().get(j);
									if (a.getClave().equals(asp2.getClave())) {
										repeticion++;
									}
								}
								asp.setRepeticion(repeticion);
								control.Insertaabajo(asp, indice);
								i = Laspectos.size() + 5;
								generadores.changeSelection(indice + 1, 2, false, false);
							}
						}
					}
				}
			});

			eliminar = new JMenuItem("Eliminar");
			eliminar.addActionListener(new ActionListener() {
				/**
				 * clic en eliminar el aspecto incluido en la estimación
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					if (indice > -1) {
						// se elimina el aspecto del formato de números generadores del sistema
						Rgenerador asp = new Rgenerador();
						asp = (Rgenerador) control.getLista().get(indice);
						if (cone.eleminarunaspecto(asp.getIdaspecto(), String.valueOf(idestimacion), String.valueOf(asp.getRepeticion())) == true && asp != null) {
							control.borraFila(indice, 1);
						} else {
							JOptionPane.showMessageDialog(null, "Error al eliminar aspecto", "Error", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			});

			pMenu.add(agregar);
			pMenu.add(eliminar);

			generadores = new JTable(modelo2);
			Pgenerador.setViewportView(generadores);
			generadores.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

			// Restricción de la información de entrada
			TableColumn tabColClave = generadores.getColumn("Clave");
			tabColClave.setCellEditor(new DefaultCellEditor(ComponentsUser.getDataTxt(15, 2)));

			TableColumn tc3 = generadores.getColumn("Descripción");
			tc3.setPreferredWidth(400);
			tc3.setCellRenderer(new CustomRenderer());
			tc3.setCellEditor(new CustomEditor());

			TableColumn tc4 = generadores.getColumn("Unidad");
			tc4.setPreferredWidth(50);
			tc4.setCellEditor(new DefaultCellEditor(ComponentsUser.getDataTxt(10, 2)));

			/**
			 * X Y Z
			 */
			TableColumn tabColX = generadores.getColumn("       X");
			tabColX.setCellEditor(new DefaultCellEditor(ComponentsUser.getDataTxt(6, 4)));

			TableColumn tabColY = generadores.getColumn("       Y");
			tabColY.setCellEditor(new DefaultCellEditor(ComponentsUser.getDataTxt(6, 4)));

			TableColumn tabColZ = generadores.getColumn("       Z");
			tabColZ.setCellEditor(new DefaultCellEditor(ComponentsUser.getDataTxt(6, 4)));

			/**
			 * Alto Largo Ancho
			 */
			TableColumn tabColAlto = generadores.getColumn("    Alto");
			tabColAlto.setCellEditor(new DefaultCellEditor(ComponentsUser.getDataTxt(9, 3)));

			TableColumn tabColLargo = generadores.getColumn("    Largo");
			tabColLargo.setCellEditor(new DefaultCellEditor(ComponentsUser.getDataTxt(9, 3)));

			TableColumn tabColAncho = generadores.getColumn("    Ancho");
			tabColAncho.setCellEditor(new DefaultCellEditor(ComponentsUser.getDataTxt(9, 3)));

			/**
			 * Cantidad Piezas Costo Importe
			 */

			TableColumn tabColCantidad = generadores.getColumn("Cantidad");
			tabColCantidad.setCellEditor(new DefaultCellEditor(ComponentsUser.getDataTxt(9, 3)));

			TableColumn tabColPiezas = generadores.getColumn("Piezas");
			tabColPiezas.setCellEditor(new DefaultCellEditor(ComponentsUser.getDataTxt(9, 3)));

			TableColumn tabColCosto = generadores.getColumn("Costo");
			tabColCosto.setCellEditor(new DefaultCellEditor(ComponentsUser.getDataTxt(9, 3)));
			tabColCosto.setPreferredWidth(70);

			TableColumn tabColImporte = generadores.getColumn("Importe");
			tabColImporte.setCellEditor(new DefaultCellEditor(ComponentsUser.getDataTxt(9, 3)));
			tabColImporte.setPreferredWidth(100);

			generadores.setRowHeight(60);
			generadores.addMouseListener(new java.awt.event.MouseAdapter() {
				/**
				 * clic en la tabla de números generadores del sistema, obtiene la descripción del aspecto seleccionado para su futuro uso en el sistema
				 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
				 */
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					if (evt.isMetaDown()) {
						pMenu.show(evt.getComponent(), evt.getX(), evt.getY());
					} else {
						if (evt.isAltDown() == false) {
							indice = generadores.getSelectionModel().getLeadSelectionIndex();
							if (indice > -1) {
								Rgenerador asp = new Rgenerador();
								asp = (Rgenerador) control.getLista().get(indice);
								if (asp.getDescripcion().equals("") == false) {
									des = asp.getDescripcion();
								}
								repeticion = asp.getRepeticion();
							}
						}
					}
				}
			});

			generadores.addKeyListener(new KeyListener() {
				public void keyPressed(KeyEvent e) {
				}

				public void keyReleased(KeyEvent e) {
				}

				/**
				 * validación del texto de entrada por parte del usuario en la tabla de números generadores
				 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
				 */
				public void keyTyped(KeyEvent e) {
					char letra = e.getKeyChar();
					if ((letra < '0' || letra > '9') && (letra != '.') && (letra != KeyEvent.VK_BACK_SPACE)) {
						e.consume();
					}
				}
			});

			presupuesto = new JLabel();
			Ptabla.add(presupuesto);
			presupuesto.setPreferredSize(new java.awt.Dimension(32, 16));
			presupuesto.setBounds(333, 256, 32, 16);

			fecha = new JLabel();
			Ptabla.add(fecha);
			fecha.setPreferredSize(new java.awt.Dimension(93, 15));
			fecha.setBounds(370, 256, 93, 15);

			ImageIcon Imagenplano = new ImageIcon(getClass().getResource("iconos/abrir.png"));
			verplano = new JButton();
			Ptabla.add(verplano);
			verplano.setIcon(Imagenplano);
			verplano.setText("Abrir Plano");
			verplano.setBounds(753, 250, 116, 28);

			ComboBoxModel<String> CopcionesModel = new DefaultComboBoxModel<String>(new String[] { "Exporta por patida", "Exportar todo el frente", "Exportar partida a PDF" });
			Copciones = new JComboBox<String>();
			Ptabla.add(Copciones);
			Copciones.setModel(CopcionesModel);
			Copciones.setBounds(549, 253, 198, 26);
			Copciones.addActionListener(new ActionListener() {
				/**
				 * clic en el combo box para obtener el formato de los números generadores
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				@SuppressWarnings("unused")
				public void actionPerformed(ActionEvent e) {
					//recuperación del directorio seleccionado para guardar el archivo
					String path = "";
					JFileChooser navega = new JFileChooser();
					int estado = navega.showSaveDialog(generadores);
					navega.setDialogTitle("Guardar reporte");
					if (estado == JFileChooser.APPROVE_OPTION) {
						path = navega.getSelectedFile().getAbsolutePath();
					}
					if (path != null && path.equals("") == false) {
						// ********************************************************************************************************************************************************************
						if (Copciones.getSelectedIndex() == 1) {
							//realizar la exportacion por frente
							if (idestimacion > 0) {
								// *******************************************************************************************
								Rgenerador asp = new Rgenerador();
								for (int i = 0; i < control.getLista().size(); i++) {
									asp = (Rgenerador) control.getLista().get(i);
									if (asp.getX().equals(" ") == true) {
										asp.setX("0");
									}
									if (asp.getY().equals(" ") == true) {
										asp.setY("0");
									}
									if (asp.getZ().equals(" ") == true) {
										asp.setZ("0");
									}
									ban2 = cone.estimacionaspecto(asp.getIdaspecto(), String.valueOf(idestimacion), asp.getPiezas(), asp.getImporte(), asp.getX(), asp.getY(), asp.getZ(), asp.getAlto(), asp.getLargo(), asp.getAncho(), asp.getCosto(), String.valueOf(idpartida), String.valueOf(asp.getRepeticion()), fechaestimacion);
								}

								/**
								 * Parte para la creación de un archivo en EXCEL
								 */

								LinkedList<Rgenerador> desplegar = new LinkedList<Rgenerador>();
								desplegar = cone.reportegeneral(String.valueOf(idestimacion));
								if (desplegar.size() > 0) {
									if (verificarDatos() == false) {
										path += ".xls";
										general = new Genrargeneral(desplegar, path);
										try {
											File file = new File(path);
											Desktop.getDesktop().open(file);
										} catch (IOException e1) {
											JOptionPane.showMessageDialog(null, "Error al lanzar Execel");
											;
										}
									}
								} else {
									JOptionPane.showMessageDialog(null, "La estimacinin no contiene datos", "verificar", JOptionPane.WARNING_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null, "Debes seleccionar una estimacinin", "verificar", JOptionPane.WARNING_MESSAGE);
							}
						}

						if (Copciones.getSelectedIndex() == 0) {
							//realizar la exportacion por partida 
							if (control.Tama() > 0) {
								if (idpartida > 0) {
									if (idfrente > 0) {
										// *******************************************************************************************

										if (verificarDatos() == false) {
											Rgenerador asp = new Rgenerador();
											for (int i = 0; i < control.getLista().size(); i++) {
												asp = (Rgenerador) control.getLista().get(i);
												ban2 = cone.estimacionaspecto(asp.getIdaspecto(), String.valueOf(idestimacion), asp.getPiezas(), asp.getImporte(), asp.getX(), asp.getY(), asp.getZ(), asp.getAlto(), asp.getLargo(), asp.getAncho(), asp.getCosto(), String.valueOf(idpartida), String.valueOf(asp.getRepeticion()), fechaestimacion);
											}
										} else {
											ban = false;
										}

										if (ban2 == true) {
											path += ".xls";
											Generarexcel exe = new Generarexcel(control.getLista(), path);
											try {
												File file = new File(path);
												Desktop.getDesktop().open(file);
											} catch (IOException e1) {
												JOptionPane.showMessageDialog(null, "Error al lanzar archivo Execel");
												;
											}
										}

										// ***********************************************************************************************
									} else {
										JOptionPane.showMessageDialog(null, "No tienes selecionado ningnin frente", "verifica", JOptionPane.WARNING_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null, "No tienes seleccionada ninguna partida", "verifica", JOptionPane.WARNING_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null, "No hay datos selecionados", "verifica", JOptionPane.WARNING_MESSAGE);
							}
						}
						if (Copciones.getSelectedIndex() == 2) {
							//realizar la exportacion por frente en formato pdf 
							if (control.Tama() > 0) {
								if (idpartida > 0) {
									if (idfrente > 0) {
										// *******************************************************************************************
										if (verificarDatos() == false) {
											Rgenerador asp = new Rgenerador();
											for (int i = 0; i < control.getLista().size(); i++) {
												asp = (Rgenerador) control.getLista().get(i);
												ban2 = cone.estimacionaspecto(asp.getIdaspecto(), String.valueOf(idestimacion), asp.getPiezas(), asp.getImporte(), asp.getX(), asp.getY(), asp.getZ(), asp.getAlto(), asp.getLargo(), asp.getAncho(), asp.getCosto(), String.valueOf(idpartida), String.valueOf(asp.getRepeticion()), fechaestimacion);
											}
										} else {
											ban2 = false;
										}
										if (ban2 == true) {
											path += ".pdf";
											Generarpdf pdf = new Generarpdf(control.getLista(), consultor.getSelectedItem().toString(), path);
											try {
												File file = new File(path);
												Desktop.getDesktop().open(file);
											} catch (IOException e1) {
												JOptionPane.showMessageDialog(null, "Error al lanzar PDF");
											}
										}
										// ***********************************************************************************************
									} else {
										JOptionPane.showMessageDialog(null, "No tienes selecionado ningnin frente", "verifica", JOptionPane.WARNING_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(null, "No tienes seleccionada ninguna partida", "verifica", JOptionPane.WARNING_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null, "No hay datos selecionados");
							}
						}
						// **************************************************************************************************************************************************************************
					}
				}
			});

			eexportar = new JLabel();
			Ptabla.add(eexportar);
			eexportar.setText("Exportar :");
			eexportar.setBounds(489, 256, 60, 16);

			verplano.addActionListener(new ActionListener() {
				/**
				 * clic en ver plano de la obra, sólo muestra la imagen seleccionada del plano de obra
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					JFileChooser navega;
					navega = new JFileChooser();
					int estado = navega.showOpenDialog(verplano);
					;
					if (estado == JFileChooser.APPROVE_OPTION) {
						String ruta = navega.getSelectedFile().getAbsolutePath();
						pla = new Lplano(ruta);
						escritorio2.add(pla);
					}
					try {
						pla.setSelected(true);
					} catch (Exception a) {
						a.printStackTrace();
					}
				}
			});
			Pconcepto.setLayout(null);
			etiqueta = new JLabel();
			etiqueta.setBounds(387, 124, 170, 16);
			Pconcepto.add(etiqueta);
			etiqueta.setText("Desglose de Conceptos");
			etiqueta.setPreferredSize(new java.awt.Dimension(105, 16));
			Ddescripcion = new JScrollPane();
			Ddescripcion.setBounds(6, 139, 960, 130);
			Pconcepto.add(Ddescripcion);
			Ddescripcion.setPreferredSize(new java.awt.Dimension(898, 124));
			
						TDescripcion = new JTable();
						TDescripcion.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						Ddescripcion.setViewportView(TDescripcion);
						TDescripcion.addMouseListener(new java.awt.event.MouseAdapter() {
							/**
							 * clic en la tabla de 
							 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
							 */
							public void mouseClicked(java.awt.event.MouseEvent evt) {
								int uno, dos, i = 0, tm = 0, veces = 0, id = -1;
								Aspecto a = new Aspecto();
								String ban = "", des = "", clave = "", ida = "0";
								uno = TDescripcion.getSelectionModel().getLeadSelectionIndex(); //
								dos = TDescripcion.getColumnModel().getSelectionModel().getLeadSelectionIndex(); //
								Object cellValue = TDescripcion.getModel().getValueAt(uno, 0);// valor
								// true
								// :
								// false
								ban = cellValue.toString();

								if (evt.isMetaDown()) {
								} else {
									if (evt.isAltDown() == false) {
										// ******************************************************************************************************************************
										if (uno > -1) {
											Object cellValuedos = TDescripcion.getModel().getValueAt(uno, 1);
											des = cellValuedos.toString();
											if (dos == 0) {
												for (i = 0; i < Laspectos.size(); i++) {
													a = (Aspecto) Laspectos.get(i);
													if (a.getDescripcion() == des) {
														indi = a.getIdaspecto();
														clave = a.getClave();
														i = Laspectos.size() + 5;
													}
												}
												if (ban.equals("true") == true && des.equals("") == false) {
													Rgenerador asp = new Rgenerador();
													asp.setClavePublica(a.getClave_privada());
													asp.setIdaspecto(String.valueOf(a.getIdaspecto()));
													asp.setClave(a.getClave());
													asp.setDescripcion(a.getDescripcion());
													asp.setUnidad(a.getUnidad());
													asp.setX("0");
													asp.setY("0");
													asp.setZ("0");
													asp.setAlto("0");
													asp.setLargo("0");
													asp.setAncho("0");
													asp.setCantidad("0");
													asp.setPiezas("0");
													asp.setCosto(String.valueOf(a.getCosto()));
													asp.setImporte("0");
													asp.setRepeticion(1);
													asp.setFecha(fechaestimacion);
													control.anhadeFila(asp);
													generadores.changeSelection(control.Tama() - 1, 2, false, false);
												} else {
													tm = control.Tama();
													if (ban.equals("false") == true && des.equals("") == false) {
														Rgenerador asp = new Rgenerador();
														for (i = 0; i < tm; i++) {
															asp = (Rgenerador) control.getLista().get(i);
															if (asp.getClave().equals(clave)) {
																if (id == -1) {
																	id = i;
																	ida = asp.getIdaspecto();
																}
																veces++;
															}
														}
														if (cone.Eliminaresaspecto(ida, String.valueOf(idestimacion))) {
															if (veces > 1) {
																control.borraFila(id, veces);
															} else {
																control.borraFila(id, 1);
															}
														} else {
															JOptionPane.showMessageDialog(null, "Error al eliminar aspecto", "Error", JOptionPane.WARNING_MESSAGE);
														}
													}
												}
											}
										}
										// ******************************************************************************************************************************
									}
								}
							}
						});

			JScrollPane ScrollConcept = new JScrollPane();
			ScrollConcept.setBounds(6, 6, 960, 113);
			Pconcepto.add(ScrollConcept);

			tab_conceptos = new JTable();
			tab_conceptos.addMouseListener(new MouseAdapter() {
				//clic en la tabla de conceptos, llena los aspectos relacionados con el concepto seleccionado
				public void mouseClicked(MouseEvent arg0) {

					Concepto con = new Concepto();
					int indi = 0;
					for (int i = 0; i < Lconceptos.size(); i++) {
						con = (Concepto) Lconceptos.get(i);
						if (con.getNombre() == tab_conceptos.getValueAt(tab_conceptos.getSelectedRow(), 0)) {
							indi = con.getIdconcepto();
							idconcepto = indi;
							i = Lconceptos.size();
						}
					}
					llenarAspectos(idconcepto);

					TableColumn co = TDescripcion.getColumn("Selección");
					co.setPreferredWidth(80);
					TableColumn co2 = TDescripcion.getColumn("Desglose de Conceptos");
					co2.setPreferredWidth(850);
				}
			});
			ScrollConcept.setViewportView(tab_conceptos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * método para recuperar los proyectos de la base de datos y mostrarlos en pantalla
	 */
	private void llenarproyectos() {
		Cproyecto.removeAllItems();
		ListaProyectos = cone.GetProyectos();
		for (int i = 0; i < ListaProyectos.size(); i++) {
			pro = (Proyecto) ListaProyectos.get(i);
			Cproyecto.addItem(pro.getProyecto());
		}
		if (Cproyecto.getItemCount() > 0) {
			Cproyecto.setSelectedIndex(0);
		}

	}

	/**
	 * método para recuperar los consultores de la base de datos y mostrarlos en pantalla
	 */
	private void llenarconsultor() {
		consultor.removeAllItems();
		ListaConsultores = cone.GetConsultores();
		for (int i = 0; i < ListaConsultores.size(); i++) {
			consul = (Consultor) ListaConsultores.get(i);
			consultor.addItem(consul.getNombre() + "   " + consul.getPaterno() + "  " + consul.getMaterno());
		}
		if (consultor.getItemCount() > 0) {
			consultor.setSelectedIndex(0);
		}
	}

	/**
	 * método para recuperar los frentes de la base de datos 
	 */
	private void llenarLFrentes() {
		Listafrentes = cone.Frentes();
	}

	/**
	 * método para mostrar los frentes en pantalla dependiendo del proyecto seleccionado
	 * @param indice -- identificador del proyecto seleccionado
	 */
	private void llenarfrentes(int indice) {
		Cfrente.removeAllItems();
		Cfrente.addItem("        ");
		for (int i = 0; i < Listafrentes.size(); i++) {
			fre = (Frente) Listafrentes.get(i);
			if (fre.getIdproyecto() == indice) {
				Cfrente.addItem(fre.getIndentificador());
			}
			Cfrente.setSelectedIndex(-1);

		}
		if (Cfrente.getItemCount() > 0) {
			Cfrente.setSelectedIndex(0);
		}

	}

	/**
	 * método para recuperar los conceptos de la base de datos
	 */
	private void llenarLconceptos() {
		Lconceptos = cone.GetConceptos();
	}

	/**
	 * método para mostrar los conceptos en pantalla dependiendo de la partida seleccionada
	 * @param indice -- indentificador de partida seleccionada
	 */
	private void llenarconceptos(int indice) {
		tab_conceptos.removeAll();
		LinkedList<String> nameConcept = new LinkedList<String>();
		for (int i = 0; i < Lconceptos.size(); i++) {
			concep = (Concepto) Lconceptos.get(i);
			if (concep.getIdpartida() == indice) {
				nameConcept.add(concep.getNombre());
			}
		}
		ModeloConceptoSeleccion conceptosATM = new ModeloConceptoSeleccion();
		conceptosATM.setData(nameConcept);
		tab_conceptos.setModel(conceptosATM);
	}

	/**
	 * método para recuperar los Aspectos de la base de datos 
	 */
	private void llenarLAcpestosdos() {
		Laspectos = cone.GetAspectos();
	}

	/**
	 * método para verificar la informacion de los aspectos seleccionados en la estimacion
	 * @return si existe algun problema con la informacion del aspecto ? false : todo ok true
	 */
	private boolean verificarDatos() {
		boolean ban = false;
		int i;
		Rgenerador ge = new Rgenerador();
		for (i = 0; i < control.getLista().size(); i++) {
			ge = (Rgenerador) control.getLista().get(i);
			if (ge.getX().equals(" ") == true || ge.getY().equals(" ") == true || ge.getZ().equals(" ") == true) {
				ban = true;
				JOptionPane.showMessageDialog(null, "El aspecto: " + ge.getClave() + "  contiene valores no validos ", "verifica", JOptionPane.WARNING_MESSAGE);
				i = control.getLista().size() + 4;
			} else {
				if (ge.getUnidad().equals("m2") == true) {
					if (ge.getLargo().equals("0") == true || ge.getAncho().equals("0") == true || ge.getPiezas().equals("0") == true) {
						ban = true;
						JOptionPane.showMessageDialog(null, "El aspecto: " + ge.getClave() + "  contiene valores no validos ", "verifica", JOptionPane.WARNING_MESSAGE);
						i = control.getLista().size() + 4;
					}
				}
				if (ge.getUnidad().equals("ml") == true) {
					if (ge.getLargo().equals("0") == true || ge.getPiezas().equals("0") == true) {
						ban = true;
						JOptionPane.showMessageDialog(null, "El aspecto: " + ge.getClave() + "  contiene valores no validos ", "verifica", JOptionPane.WARNING_MESSAGE);
						i = control.getLista().size() + 4;
					}
				}
				if (ge.getUnidad().equals("m3") == true) {
					if (ge.getAlto().equals("0") == true || ge.getLargo().equals("0") == true || ge.getAncho().equals("0") == true || ge.getPiezas().equals("0") == true) {
						ban = true;
						JOptionPane.showMessageDialog(null, "El aspecto: " + ge.getClave() + "  contiene valores no validos ", "verifica", JOptionPane.WARNING_MESSAGE);
						i = control.getLista().size() + 4;
					}
				}
				if (ge.getUnidad().equals("pieza") == true) {
					if (ge.getPiezas().equals("0") == true) {
						ban = true;
						JOptionPane.showMessageDialog(null, "El aspecto: " + ge.getClave() + "  contiene valores no validos ", "verifica", JOptionPane.WARNING_MESSAGE);
						i = control.getLista().size() + 4;
					}
				}
				if (ge.getUnidad().equals("L") == true) {
					if (ge.getPiezas().equals("0") == true) {
						ban = true;
						JOptionPane.showMessageDialog(null, "El aspecto: " + ge.getClave() + "  contiene valores no validos ", "verifica", JOptionPane.WARNING_MESSAGE);
						i = control.getLista().size() + 4;
					}
				}
				if (ge.getUnidad().equals("kg") == true) {
					if (ge.getPiezas().equals("0") == true) {
						ban = true;
						JOptionPane.showMessageDialog(null, "El aspecto: " + ge.getClave() + "  contiene valores no validos ", "verifica", JOptionPane.WARNING_MESSAGE);
						i = control.getLista().size() + 4;
					}
				}
			}
		}
		return ban;
	}

	/**
	 * método para desplegar los aspectos en pantalla
	 * @param indice -- indice del concepto seleccionado
	 */
	private void llenarAspectos(int indice) {
		Rgenerador seleccionado = new Rgenerador();
		int posi = 0;
		Object[][] elementos;
		int filas = 0, columnas = 2;

		for (int i = 0; i < Laspectos.size(); i++) {
			asp2 = (Aspecto) Laspectos.get(i);
			if (asp2.getIdconcepto() == indice)
				filas++;
		}
		elementos = new Object[filas][columnas];
		for (int i = 0; i < elementos.length; i++) {
			elementos[i][0] = new Boolean(false);
		}
		TDescripcion.removeAll();

		for (int i = 0; i < Laspectos.size(); i++) {
			asp2 = (Aspecto) Laspectos.get(i);
			if (asp2.getIdconcepto() == indice) {
				elementos[posi][1] = asp2.getDescripcion();
				if (control.Tama() > 0) {
					for (int j = 0; j < control.getLista().size(); j++) {
						seleccionado = (Rgenerador) control.getLista().get(j);
						if (seleccionado.getClave().equals(asp2.getClave())) {
							elementos[posi][0] = new Boolean(true);
						}
					}
				}
				posi++;
			}
		}
		AbstractTableModel dos = new MyTableModel(elementos);
		TDescripcion.removeAll();
		Ddescripcion.setViewportView(TDescripcion);
		TDescripcion.setModel(dos);
	}

	/**
	 * método para limpiar el modelo de la tabla del generador en pantalla
	 */
	public void Limpiar() {
		int tama = control.Tama() - 1;
		if (entrouno = true) {
			if (tama > -1) {
				while (tama >= 0) {
					control.borraFila(tama, 1);
					tama--;
				}
			}
		} else {
			entrouno = true;
		}

	}

	/**
	 * método para mostrar las partidas en pantalla
	 */
	public void llenarParitdas() {
		Lpartidas = cone.getPartidas();
		for (int i = 0; i < Lpartidas.size(); i++) {
			par = (Partida) Lpartidas.get(i);
			controlp.anhadeFila(par.getNombre());
		}
	}

	// *****************************************************************************************************************************
	/**
	 *Clase para asiginar un componente JTextArea en la columna de descripcion del aspecto (visualmente)
	 * @author Pablo Rivera
	 */
	class CustomRenderer implements TableCellRenderer {

		JTextArea textArea;

		/**
		 * constructor con un JTextArea
		 */
		public CustomRenderer() {
			textArea = new JTextArea();
			textArea.setLineWrap(true);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			textArea.setText((String) value);
			return textArea;
		}
	}

	/**
	 *Clase para asignar el editor de un componente JTextField en la columna de descripcion del aspecto
	 * @author Pablo Rivera
	 */
	class CustomEditor implements TableCellEditor {

		JTextArea textArea;

		/**
		 * constructor con un JtextArea
		 */
		public CustomEditor() {
			textArea = new JTextArea();
			textArea.setLineWrap(true);
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			textArea.setText((String) value);
			return textArea;
		}

		public void addCellEditorListener(CellEditorListener l) {
		}

		public void cancelCellEditing() {
		}

		public Object getCellEditorValue() {
			return textArea.getText();
		}
		public boolean isCellEditable(EventObject anEvent) {
			return true;
		}

		public void removeCellEditorListener(CellEditorListener l) {
		}

		public boolean shouldSelectCell(EventObject anEvent) {
			return true;
		}
		public boolean stopCellEditing() {
			return true;
		}
	}
}