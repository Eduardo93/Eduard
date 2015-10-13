import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.beans.PropertyVetoException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import MetodosRemotos.Metodos;
import ObjetosSerializables.Frente;
import ObjetosSerializables.Proyecto;
import ObjetosSerializables.Tipo;
import Options.ComponentsUser;
import Options.OptionsText;
import Options.VComentarioProyecto;
import TablaFrentes.Controlfrente;
import TablaFrentes.Modelofrente;

import com.toedter.calendar.JDateChooser;

import javax.swing.table.TableColumn;

/**
 * Clase para administrar la información de proyectos
 * @author Pablo Rivera
 * @colaboración luiiis lazaro
 *
 */
public class Proyectos extends javax.swing.JInternalFrame {

	/**
	 * escritorio 
	 */
	private static final long serialVersionUID = 1L;
	private JDesktopPane escritorio;
	private JPanel Pproyectos;
	private JPanel Popcione;
	private JRadioButton agregar;
	private JPanel Pinformacion;
	private JTextField nombre;
	private JTextArea descripcion;
	private JButton bproyecto;
	private JButton feliminar;
	private JLabel efrentes;
	private JComboBox<String> Cproyectos;
	private JLabel Eproyecto;
	private JButton aceptar;
	private JLabel etifechafin;
	private JLabel efechaini;
	private JButton fagregar;
	private JTable Tfrentes;
	private JScrollPane Spanel;
	private JPanel Pfrentes;
	private JDateChooser Ffin;
	private JDateChooser finicial;
	private JLabel Edescripcion;
	private JLabel Enombre;
	private JComboBox<String> tipo;
	private JLabel Ltipo;
	private JRadioButton eliminar;
	private JRadioButton modificar;
	private Tipoproyecto tp;
	private LinkedList<Tipo> Ltipos = new LinkedList<Tipo>();
	private Tipo ti;
	private int idproyecto = -1;
	private int idfrente = -1, posicion = -1;
	private LinkedList<Proyecto> Lproyectos = new LinkedList<Proyecto>();
	private LinkedList<Frente> Lfrentes = new LinkedList<Frente>();
	private Metodos cone;
	private Modelofrente modelo = new Modelofrente();
	private Controlfrente control = new Controlfrente(modelo);
	private JScrollPane scrollPane;
	private JButton btnComments;
	private String commentsProyecto;

	/**
	 * Obtenern comentarios acerca del proyecto
	 * 
	 * @return the commentsProyecto
	 */
	public String getCommentsProyecto() {
		return commentsProyecto;
	}

	/**
	 * Asignar comentarios del proyecto
	 * 
	 * @param commentsProyecto
	 *            the commentsProyecto to set
	 */
	public void setCommentsProyecto(String commentsProyecto) {
		this.commentsProyecto = commentsProyecto;
	}

	/**
	 * construtor de la clase 
	 * @param escritorio2 --- panel de la aplacación principal 
	 * @param cone --- métodos para la manipulación de la base de datos
	 */
	public Proyectos(JDesktopPane escritorio2, Metodos cone) {
		super("Registro de Proyectos", false, true, false, true);
		initGUI(escritorio2, cone);
	}

	/**
	 * inicialización de los componentes gráficos
	 * @param escritorio2 --- panel de la aplacación principal 
	 * @param cone --- métodos para la manipulación de la base de datos
	 */
	private void initGUI(final JDesktopPane escritorio2, final Metodos cone) {
		try {
			this.setPreferredSize(new java.awt.Dimension(528, 561));
			this.setBounds(0, 0, 528, 561);
			setVisible(true);
			this.cone = cone;

			escritorio = new JDesktopPane();
			getContentPane().add(escritorio, BorderLayout.CENTER);
			control.meterconexion(cone);
			Ltipos = cone.Tiposproyectos();

			Pproyectos = new JPanel();
			escritorio.add(Pproyectos, JLayeredPane.DEFAULT_LAYER);
			Pproyectos.setBounds(0, 0, 526, 531);
			Pproyectos.setLayout(null);

			Popcione = new JPanel();
			Pproyectos.add(Popcione);
			Popcione.setBounds(12, 2, 495, 70);
			Popcione.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
			Popcione.setLayout(null);

			agregar = new JRadioButton();
			Popcione.add(agregar);
			agregar.setText("Agregar");
			agregar.setBounds(17, 6, 74, 20);
			agregar.addActionListener(new ActionListener() {
				/**
				 * opción para agregar un nuevo proyecto
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					modificar.setSelected(false);
					eliminar.setSelected(false);
					Cproyectos.setEnabled(false);
					aceptar.setEnabled(true);
					limpiar();
					Limpiarfrente();
				}
			});

			modificar = new JRadioButton();
			Popcione.add(modificar);
			modificar.setText("Modificar");
			modificar.setBounds(108, 6, 88, 20);
			modificar.addActionListener(new ActionListener() {
				/**
				 * Opción para actualizar la información de un proyecto
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					agregar.setSelected(false);
					eliminar.setSelected(false);
					Cproyectos.setEnabled(true);
					aceptar.setEnabled(true);
					llenarproyectos();
					limpiar();
					Limpiarfrente();
					Tfrentes.setEnabled(false);
					fagregar.setEnabled(false);
					feliminar.setEnabled(false);
				}
			});

			// BotÃ³n para eliminar un proyecto
			eliminar = new JRadioButton();
			Popcione.add(eliminar);
			eliminar.setText("Eliminar");
			eliminar.setBounds(201, 6, 82, 20);
			eliminar.addActionListener(new ActionListener() {
				/**
				 * opción para eliminar un proyecto
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					agregar.setSelected(false);
					modificar.setSelected(false);
					Cproyectos.setEnabled(true);
					aceptar.setEnabled(true);
					llenarproyectos();
					Limpiarfrente();
					Tfrentes.setEnabled(false);
					fagregar.setEnabled(false);
					feliminar.setEnabled(false);
				}
			});

			// Agrupación de los botones radios
			ButtonGroup groupRadioBTN = new ButtonGroup();
			groupRadioBTN.add(agregar);
			groupRadioBTN.add(modificar);
			groupRadioBTN.add(eliminar);

			// Combo para los nombre de los proyectos
			Cproyectos = new JComboBox<String>();
			Popcione.add(Cproyectos);
			Cproyectos.setBounds(94, 35, 388, 23);
			Cproyectos.setEnabled(false);
			Cproyectos.addActionListener(new ActionListener() {
				/**
				 * Listener para desplegar la información del proyecto seleccionado
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					int i = 0;
					Date uno;
					Date dos;
					if (Cproyectos.getSelectedIndex() > -1) {
						Proyecto pro = new Proyecto();
						pro = (Proyecto) Lproyectos.get(Cproyectos.getSelectedIndex());
						idproyecto = pro.getIdproyecto();
						Tipo ti = new Tipo();
						for (i = 0; i < Ltipos.size(); i++) {
							ti = (Tipo) Ltipos.get(i);
							if (Integer.parseInt(ti.getIdtipo()) == pro.getIdtipo()) {
								tipo.setSelectedIndex(i);
								descripcion.setText(pro.getDescripcion());
								nombre.setText(pro.getProyecto());
								uno = pro.getInicio();
								finicial.setDate(uno);
								dos = pro.getFin();
								Ffin.setDate(dos);
								break;
							}
						}
					}
				}
			});

			Eproyecto = new JLabel();
			Popcione.add(Eproyecto);
			Eproyecto.setText("Proyectos");
			Eproyecto.setBounds(17, 38, 65, 16);

			Pinformacion = new JPanel();
			Pproyectos.add(Pinformacion);
			Pinformacion.setBounds(12, 75, 495, 283);
			Pinformacion.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
			Pinformacion.setLayout(null);

			Ltipo = new JLabel();
			Pinformacion.add(Ltipo);
			Ltipo.setText("Tipo de Proyecto");
			Ltipo.setBounds(9, 13, 116, 16);

			tipo = new JComboBox<String>();
			Pinformacion.add(tipo);
			tipo.setBounds(125, 10, 214, 23);
			llenarTipos();

			Enombre = new JLabel();
			Pinformacion.add(Enombre);
			Enombre.setText("Nombre del Proyecto");
			Enombre.setBounds(13, 52, 135, 16);

			nombre = new JTextField();
			nombre = ComponentsUser.getDataTxt(40, 1);// se agrega el typed y
														// la longitud de la
														// cadena
			Pinformacion.add(nombre);
			nombre.setBounds(138, 49, 335, 27);

			Edescripcion = new JLabel();
			Pinformacion.add(Edescripcion);
			Edescripcion.setText("Descripci\u00F3n del Proyecto");
			Edescripcion.setBounds(9, 80, 182, 16);

			descripcion = ComponentsUser.getDataJTextArea(255);
			Pinformacion.add(descripcion);
			descripcion.addKeyListener(new KeyAdapter() {
				/**
				 * Validación del texto de entrada
				 * (non-Javadoc) 
				 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
				 */
				public void keyTyped(KeyEvent e) {
					OptionsText.onlyCharsNumbers(e.getKeyChar(), e);
				}
			});

			scrollPane = ComponentsUser.getDataJScrollPane();
			scrollPane.setBounds(19, 97, 459, 86);
			scrollPane.setViewportView(descripcion);
			Pinformacion.add(scrollPane);

			bproyecto = new JButton();
			Pinformacion.add(bproyecto);
			bproyecto.setText(".....");
			bproyecto.setBounds(351, 10, 29, 23);
			bproyecto.addActionListener(new ActionListener() {
				/**
				 * opción para crear nuevos tipos de proyecto
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					if (tp == null || tp.isClosed()) {
						// **************************************************
						try {
							tp = new Tipoproyecto(cone, tipo);
							escritorio2.add(tp);
							tp.setSelected(true);
						} catch (Exception a) {
							System.out.println(a);
						}
						// *************************************************
					} else {
						try {
							tp.setSelected(true);
						} catch (PropertyVetoException e1) {
							System.out.println(e1.toString());
						}
					}
				}
			});

			ImageIcon Imagenfecha = new ImageIcon("C:/Users/pabluchis/workspace/Generadores/src/iconos/reloj.png");
			finicial = new JDateChooser();
			finicial.setIcon(Imagenfecha);
			Pinformacion.add(finicial);
			finicial.setBounds(35, 203, 200, 30);

			Ffin = new JDateChooser();
			Ffin.setIcon(Imagenfecha);
			Pinformacion.add(Ffin);
			Ffin.setBounds(278, 203, 200, 30);
			// ****************************************************************

			efechaini = new JLabel();
			Pinformacion.add(efechaini);
			efechaini.setText("Fecha Inicial");
			efechaini.setBounds(35, 186, 90, 16);

			etifechafin = new JLabel();
			Pinformacion.add(etifechafin);
			etifechafin.setText("Fecha Final");
			etifechafin.setBounds(278, 187, 96, 16);

			// BotÃ³n para agregar, modificar o eliminar un proyecto
			ImageIcon Imagenproceso = new ImageIcon(getClass().getResource("iconos/proceso.png"));
			aceptar = new JButton();
			aceptar.setEnabled(false);
			Pinformacion.add(aceptar);
			aceptar.setIcon(Imagenproceso);
			aceptar.setText("Procesar");
			aceptar.setBounds(56, 243, 135, 30);

			btnComments = new JButton("Ver Comentarios");
			btnComments.setEnabled(false);
			btnComments.addActionListener(new ActionListener() {
				/**
				 * opción para agregar o actualizar la información de los comentarios del proyecto
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent arg0) {
					VComentarioProyecto comentariosProyecto = new VComentarioProyecto(cone, idproyecto);
					escritorio.add(comentariosProyecto);
					comentariosProyecto.setEnabled(true);
					comentariosProyecto.setVisible(true);
				}
			});
			btnComments.setBounds(278, 244, 200, 28);
			Pinformacion.add(btnComments);

			aceptar.addActionListener(new ActionListener() {
				/**
				 * opción para guardar / actualizar o eliminar un proyecto 
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					int ano, mes, dia, existe = 0;
					String ca = "", cm = "", cd = "", fecha1 = "0", fecha2 = "0";
					Ltipos = cone.Tiposproyectos();
					// ****************************************************************************************************************************************
					if (finicial.getDate() != null) {
						ano = finicial.getCalendar().get(Calendar.YEAR);
						mes = finicial.getCalendar().get(Calendar.MONTH) + 1;
						dia = finicial.getCalendar().get(Calendar.DAY_OF_MONTH);
						ca = String.valueOf(ano);
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
						fecha1 = ca + "-" + cm + "-" + cd;
					} else {
						JOptionPane.showMessageDialog(null, "No has seleccionado fecha inicial");
					}
					// ***************************************************************************************************************************************
					if (Ffin.getDate() != null) {
						ano = Ffin.getCalendar().get(Calendar.YEAR);
						mes = Ffin.getCalendar().get(Calendar.MONTH) + 1;
						dia = Ffin.getCalendar().get(Calendar.DAY_OF_MONTH);
						ca = String.valueOf(ano);
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
						fecha2 = ca + "-" + cm + "-" + cd;
					} else {
						fecha2 = "";
					}
					
					/**
					 * validación para las fechas, la fecha final no debe ser menor a la fecha de inicio
					 */
					try {
						if (Ffin.getCalendar().before(finicial.getCalendar())){
							JOptionPane.showMessageDialog(null, "Atención con la fecha final del proyecto. No puede ser menor a la de inicio.");
							return;
						}	
					} catch (Exception e2) {
						// TODO: handle exception
					}
					

					if (agregar.isSelected()) {
						if (vacio() == false && fecha1.equals("0") == false && fecha2.equals("0") == false) {
							try {
								if (cone.existeproyecto(nombre.getText()) == false) {
									// ***************************************************************************************************************************
									Tipo p = (Tipo) Ltipos.get(tipo.getSelectedIndex());
									idproyecto = cone.Insertarproyecto(p.getIdtipo(), fecha1, fecha2, descripcion.getText(), nombre.getText(), getCommentsProyecto());
									if (idproyecto > 0) {
										JOptionPane.showMessageDialog(null, "Éxito al crear proyecto, ingresa frentes");
										Tfrentes.setEnabled(true);
										fagregar.setEnabled(true);
										feliminar.setEnabled(true);
										aceptar.setEnabled(false);
										btnComments.setEnabled(true);
									}
									// *****************************************************************************************************************************
								} else {
									JOptionPane.showMessageDialog(null, "El proyecto ya existe", "Error", JOptionPane.WARNING_MESSAGE);
								}
							} catch (HeadlessException e1) {
								JOptionPane.showMessageDialog(null, "Servidor fuera de servicio (Error al insertar proyecto) ", "Error", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Verifica que todos los campos esten llenos", "verifica", JOptionPane.WARNING_MESSAGE);
						}
					}

					if (modificar.isSelected()) {
						int res = JOptionPane.showConfirmDialog(null, "Confirmar para modificar", "Confirmación", JOptionPane.YES_NO_OPTION);
						if (res == JOptionPane.YES_OPTION) {
							btnComments.setEnabled(true);
							Tipo p = (Tipo) Ltipos.get(tipo.getSelectedIndex());
							if (cone.modificarProyecto(p.getIdtipo(), fecha1, fecha2, descripcion.getText(), nombre.getText(), String.valueOf(idproyecto))) {
								Lfrentes = cone.Frentes();
								Limpiarfrente();
								Frente fre;
								if (Lfrentes.size() > 1) {
									for (int i = 0; i < Lfrentes.size(); i++) {
										fre = new Frente();
										fre = (Frente) Lfrentes.get(i);
										if (fre.getIdproyecto() == idproyecto) {
											control.anhadeFila(fre);
										}
									}
									Tfrentes.setEnabled(true);
									fagregar.setEnabled(true);
									feliminar.setEnabled(true);
									aceptar.setEnabled(false);
								} else {
									Tfrentes.setEnabled(false);
									fagregar.setEnabled(false);
									feliminar.setEnabled(false);
									aceptar.setEnabled(false);
								}
							}
						}
					}

					if (eliminar.isSelected()) {
						int res = JOptionPane.showConfirmDialog(null, "Confirmar para eliminar", "Confirmación", JOptionPane.YES_NO_OPTION);
						if (res == JOptionPane.YES_OPTION) {
							Lfrentes = cone.Frentes();
							Limpiarfrente();
							Frente fre;
							if (Lfrentes.size() >= 1) {
								for (int i = 0; i < Lfrentes.size(); i++) {
									fre = new Frente();
									fre = (Frente) Lfrentes.get(i);
									if (fre.getIdproyecto() == idproyecto) {
										control.anhadeFila(fre);
										existe++;
									}
								}
							}
							if (existe == 0) {
								Tfrentes.setEnabled(false);
								fagregar.setEnabled(false);
								feliminar.setEnabled(false);
								aceptar.setEnabled(true);
								try {
									if (cone.eliminarProyecto(String.valueOf(idproyecto))) {
										JOptionPane.showMessageDialog(null, "El proyecto ha sido eliminado");
										llenarproyectos();
										limpiar();
										aceptar.setEnabled(false);
									} else {
										JOptionPane.showMessageDialog(null, "Error al eliminar proyecto", "Error", JOptionPane.WARNING_MESSAGE);
									}
								} catch (HeadlessException e1) {
									JOptionPane.showMessageDialog(null, "Servidor fuera de servicio (Error al eliminar proyecto) ", "Error", JOptionPane.ERROR_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null, "No puede eliminar éste proyecto tiene: " + existe + " frentes", "Error", JOptionPane.WARNING_MESSAGE);
								Tfrentes.setEnabled(true);
								fagregar.setEnabled(true);
								feliminar.setEnabled(true);
								aceptar.setEnabled(true);
							}
						}
					}
				}
			});

			Pfrentes = new JPanel();
			Pproyectos.add(Pfrentes);
			Pfrentes.setBounds(12, 364, 495, 161);
			Pfrentes.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
			Pfrentes.setLayout(null);
			Pfrentes.setEnabled(false);

			// scroll y tabla de frentes del proyecto 45 caracteresde longitud
			// para las celdas de las tablas
			Spanel = new JScrollPane();
			Pfrentes.add(Spanel);
			Spanel.setBounds(5, 27, 486, 89);
			Tfrentes = new JTable(modelo);
			Tfrentes.setRowHeight(25);
			Spanel.setViewportView(Tfrentes);
			Tfrentes.setEnabled(false);

			TableColumn c1IdentificadorColumn = Tfrentes.getColumn("Indentificador");
			c1IdentificadorColumn.setCellEditor(new DefaultCellEditor(ComponentsUser.getDataTxt(45, 2)));

			TableColumn c2UbicacionColumn = Tfrentes.getColumn("Ubicación");
			c2UbicacionColumn.setCellEditor(new DefaultCellEditor(ComponentsUser.getDataTxt(45, 2)));

			Tfrentes.addMouseListener(new MouseAdapter() {
				/**
				 * listener para cambiar el ID del frente seleccionado en la tabla
				 * (non-Javadoc)
				 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
				 */
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if (e.isMetaDown()) {

					} else {
						if (e.isAltDown() == false) {
							Frente fre = new Frente();
							posicion = Tfrentes.getSelectionModel().getLeadSelectionIndex();
							fre = (Frente) control.getListaDatos().get(posicion);
							idfrente = fre.getIdfrente();
						}
					}
				}
			});

			ImageIcon Imagenagregar = new ImageIcon(getClass().getResource("iconos/agregar.png"));
			fagregar = new JButton();
			fagregar.addActionListener(new ActionListener() {
				/**
				 * opción para agregar un nuevo frente al proyecto
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					if (idproyecto > 0) {
						idfrente = cone.Insetarfrente(String.valueOf(idproyecto), "nueva descripcion", "nueva ubicacion");
						if (idfrente > -1) {
							Frente fre = new Frente();
							fre.setIdproyecto(idproyecto);
							fre.setIdentificador("nueva descripcion");
							fre.setUbicacion("nueva ubicacion");
							fre.setIdfrente(idfrente);
							control.anhadeFila(fre);
							Tfrentes.changeSelection(control.Tama() - 1, 1, false, false);
						}
					}
				}
			});
			Pfrentes.add(fagregar);
			fagregar.setIcon(Imagenagregar);
			fagregar.setText("Agregar");
			fagregar.setBounds(260, 122, 103, 30);
			fagregar.setEnabled(false);

			// boton para eliminar frente
			ImageIcon Imageneliminar = new ImageIcon(getClass().getResource("iconos/eliminar.png"));
			feliminar = new JButton();
			Pfrentes.add(feliminar);
			feliminar.setIcon(Imageneliminar);
			feliminar.setText("Eliminar");
			feliminar.setBounds(374, 122, 103, 30);
			feliminar.setEnabled(false);
			feliminar.addActionListener(new ActionListener() {
				/**
				 * opción para eliminar un frente del proyecto
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed(ActionEvent e) {
					if (idfrente > -1) {
						int res = JOptionPane.showConfirmDialog(null, "Deseas eliminar Ã©ste frente", "ConfirmaciÃ³n", JOptionPane.YES_NO_OPTION);
						if (res == JOptionPane.YES_OPTION) {
							if (cone.eliminafrente(String.valueOf(idfrente))) {
								control.borraFila(posicion);
								Tfrentes.changeSelection(posicion - 1, 1, false, false);
							} else {
								JOptionPane.showMessageDialog(null, "Error al eliminar frente", "Error", JOptionPane.WARNING_MESSAGE);
							}
						}
					}
				}
			});

			efrentes = new JLabel();
			Pfrentes.add(efrentes);
			efrentes.setText("Agregar Frentes");
			efrentes.setBounds(13, 6, 158, 16);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Limpia la tabla de los frentes del proyecto
	 */
	public void Limpiarfrente() {
		int tama = control.Tama() - 1;
		if (tama > -1) {
			while (tama >= 0) {
				control.borraFila(tama);
				tama--;
			}
		}
	}

	/**
	 * llenar los tipos de proyectos existentes
	 */
	public void llenarTipos() {
		tipo.removeAllItems();
		for (int i = 0; i < Ltipos.size(); i++) {
			ti = new Tipo();
			ti = (Tipo) Ltipos.get(i);
			tipo.addItem(ti.getTipo());
		}
		if (tipo.getItemCount() > 0) {
			tipo.setSelectedIndex(-1);
		}
	}

	/**
	 * Si algun campo esta en blanco
	 * 
	 * @return campos vacios ? true : false
	 */
	public boolean vacio() {
		String strDescription = descripcion.getText().trim();
		String strName = nombre.getText().trim();
		if (strDescription.isEmpty() || strName.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * consultar los proyectos en la base de datos
	 */
	public void llenarproyectos() {
		Lproyectos = cone.GetProyectos();
		Cproyectos.removeAllItems();
		Proyecto pro = new Proyecto();
		for (int i = 0; i < Lproyectos.size(); i++) {
			pro = (Proyecto) Lproyectos.get(i);
			Cproyectos.addItem(pro.getProyecto());
		}
		if (Cproyectos.getItemCount() > 0) {
			Cproyectos.setSelectedItem(-1);
		}
	}

	/**
	 * Limipar datos de entrada de la GUI
	 */
	public void limpiar() {
		nombre.setText("");
		descripcion.setText("");
		Ffin.setDate(null);
		finicial.setDate(null);
	}

}