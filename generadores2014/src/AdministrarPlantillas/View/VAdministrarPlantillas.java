package AdministrarPlantillas.View;

import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import AdministrarPlantillas.Controller.CAspectosPlantillas;
import AdministrarPlantillas.Controller.CPlantilla;
import ImportarExportar.View.TableModelDesgloseAspectoSelection;
import ImportarExportar.View.TableModelConceptoSelection;
import ImportarExportar.View.TableModelPartidaSelection;
import Model.Entity.AspectoPlantilla;
import Model.Entity.AspectoSelection;
import Model.Entity.ConceptoSelection;
import Model.Entity.PartidaSelection;
import Model.EntityCRUD.Exceptions.AttributeIsEmpty;
import Model.EntityCRUD.Exceptions.DuplicateKey;
import Model.EntityCRUD.Exceptions.ErrorConnectionDB;
import Model.EntityCRUD.Exceptions.ForeingKeyReferenceDelete;
import Model.EntityCRUD.Exceptions.MaxLength;
import ObjetosSerializables.Plantilla;
import Options.ComponentsUser;

import javax.swing.JButton;

import org.apache.log4j.Logger;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * vAdministrarPlantillas - ventana para administrar las plantillas existentes
 * en el sistema
 * 
 * @author Luis Angel Hernández Lázaro
 *
 */
public class VAdministrarPlantillas extends JInternalFrame {
	/**
	 * elementos para la creacion de la ventana
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(VAdministrarPlantillas.class);

	private LinkedList<Plantilla> listPlantillas;
	private LinkedList<AspectoPlantilla> listRelationship;
	private LinkedList<PartidaSelection> listPartidas;

	private JScrollPane scrollPartidas;
	private TableModelPartidaSelection modelPartida;
	private JTable tabPartidas;

	private JScrollPane scrollConceptos;
	private TableModelConceptoSelection modelConcepto;
	private JTable tabConceptos;

	private JScrollPane scrollAspectos;
	private TableModelDesgloseAspectoSelection modelAspecto;
	private JTable tabAspectos;

	private JLabel lblNombreDePlantilla;
	private JLabel lblPartidaspresupuestale;
	private JLabel lblConceptos;
	private JLabel lblDesgloseDeConceptos;

	private JButton btnSavePlantilla;
	private JButton btnUpdatePlantilla;
	private JButton btnDeletePlantilla;

	private JComboBox<String> cbNombrePlantilla;
	private int id_plantilla_select;

	/**
	 * Create the frame.
	 */
	public VAdministrarPlantillas() {
		super("Administración de Plantillas", false, true, false, true);
		setVisible(true);

		setBounds(100, 100, 805, 700);
		getContentPane().setLayout(null);

		listPlantillas = new LinkedList<Plantilla>();
		listRelationship = new LinkedList<AspectoPlantilla>();
		listPartidas = new LinkedList<PartidaSelection>();
		id_plantilla_select = -1;

		lblNombreDePlantilla = new JLabel("Nombre de Plantilla:");
		lblNombreDePlantilla.setBounds(6, 23, 112, 16);
		getContentPane().add(lblNombreDePlantilla);

		scrollPartidas = new JScrollPane();
		scrollPartidas.setBounds(6, 68, 370, 241);
		getContentPane().add(scrollPartidas);
		modelPartida = new TableModelPartidaSelection();
		tabPartidas = new JTable(modelPartida);
		tabPartidas.addMouseListener(new MouseAdapter() {
			@Override
			/**
			 * llamada al método para llenar los conceptos relacionados a la partida seleccionda
			 */
			public void mouseClicked(MouseEvent arg0) {
				fillTableModelConcepto(tabPartidas.getSelectionModel().getLeadSelectionIndex());
			}
		});
		// se elimina la primera columna en la tabla porque pertenece a la
		// casilla de checkbox y no es necesaria para esta ventana
		tabPartidas.removeColumn(tabPartidas.getColumnModel().getColumn(0));
		scrollPartidas.setViewportView(tabPartidas);

		scrollConceptos = new JScrollPane();
		scrollConceptos.setBounds(415, 68, 370, 241);
		getContentPane().add(scrollConceptos);
		modelConcepto = new TableModelConceptoSelection();
		tabConceptos = new JTable(modelConcepto);
		tabConceptos.addMouseListener(new MouseAdapter() {
			@Override
			/**
			 * llamada al método para llenar los aspectos (desglose de conceptos) relacionados al concepto
			 */
			public void mouseClicked(MouseEvent e) {
				fillTableModelAspectos(tabPartidas.getSelectionModel().getLeadSelectionIndex(), tabConceptos.getSelectionModel().getLeadSelectionIndex());
			}
		});
		tabConceptos.removeColumn(tabConceptos.getColumnModel().getColumn(0));
		scrollConceptos.setViewportView(tabConceptos);

		lblPartidaspresupuestale = new JLabel("PartidasPresupuestales");
		lblPartidaspresupuestale.setBounds(16, 51, 134, 16);
		getContentPane().add(lblPartidaspresupuestale);

		lblConceptos = new JLabel("Conceptos");
		lblConceptos.setBounds(431, 51, 60, 16);
		getContentPane().add(lblConceptos);

		lblDesgloseDeConceptos = new JLabel("Desglose de Conceptos");
		lblDesgloseDeConceptos.setBounds(6, 322, 134, 16);
		getContentPane().add(lblDesgloseDeConceptos);

		scrollAspectos = new JScrollPane();
		scrollAspectos.setBounds(6, 350, 778, 313);
		getContentPane().add(scrollAspectos);
		modelAspecto = new TableModelDesgloseAspectoSelection();
		tabAspectos = new JTable(modelAspecto);
		tabAspectos.getTableHeader().setReorderingAllowed(false);
		
		tabAspectos.addMouseListener(new MouseAdapter() {
			@Override
			/**
			 * llamada al método para determinar si se agrega o se elimina una relación entre aspectos y plantillas
			 */
			public void mouseClicked(MouseEvent e) {
				if (tabPartidas.getColumnModel().getSelectionModel().getLeadSelectionIndex() == 0) {
					if (listPlantillas.size()>0) {
						saveOrDeleteRelation();
					}
					
				}
			}
			
		});
		scrollAspectos.setViewportView(tabAspectos);

		btnSavePlantilla = new JButton("Nueva Plantilla");
		btnSavePlantilla.addActionListener(new ActionListener() {
			/**
			 * llamada al método para guardar una plantilla
			 */
			public void actionPerformed(ActionEvent arg0) {
				savePlantilla();
			}
		});
		btnSavePlantilla.setBounds(328, 17, 120, 28);
		getContentPane().add(btnSavePlantilla);

		btnUpdatePlantilla = new JButton("Cambiar Nombre de Plantilla");
		btnUpdatePlantilla.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				if (!(listPlantillas.size() > 0)) {
					btnUpdatePlantilla.setEnabled(false);
				} else {
					btnUpdatePlantilla.setEnabled(true);
				}
			}

		});
		btnUpdatePlantilla.addActionListener(new ActionListener() {
			/**
			 * llamada al método para actualizar una plantilla
			 */
			public void actionPerformed(ActionEvent arg0) {
				updatePlantilla();
			}
		});
		btnUpdatePlantilla.setBounds(597, 17, 188, 28);
		getContentPane().add(btnUpdatePlantilla);

		btnDeletePlantilla = new JButton("Eliminar Plantilla");
		
		btnDeletePlantilla.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				if (!(listPlantillas.size() > 0)) {
					btnDeletePlantilla.setEnabled(false);
				} else {
					btnDeletePlantilla.setEnabled(true);
				}
			}
		});
		
		btnDeletePlantilla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/**
				 * llamada al método para eliminar una plantilla
				 */
				deletePlantilla();
			}
		});
		btnDeletePlantilla.setBounds(460, 17, 134, 28);
		getContentPane().add(btnDeletePlantilla);

		cbNombrePlantilla = new JComboBox<String>();
		cbNombrePlantilla.addActionListener(new ActionListener() {
			/**
			 * actualización del ID_plantilla cuando se selecciona una plantilla
			 * del combobox
			 */
			public void actionPerformed(ActionEvent arg0) {
				if (cbNombrePlantilla.getItemCount() > 1) {
					id_plantilla_select = Integer.parseInt(listPlantillas.get(cbNombrePlantilla.getSelectedIndex()).getIdplantilla());
				}
			}
		});
		cbNombrePlantilla.setBounds(130, 18, 193, 28);
		getContentPane().add(cbNombrePlantilla);

		displayPlantillas();

	}

	/**
	 * mostrar las plantillas al usuario
	 */
	public void displayPlantillas() {
		try {
			// recuperación de las plantillas en la base de datos
			listPlantillas = CPlantilla.findPlantillas();
			if (listPlantillas.size() > 0) {
				for (Plantilla plantilla : listPlantillas) {
					cbNombrePlantilla.addItem(plantilla.getNombre());
				}
				// asignacion del id para la primera plantilla recuperada
				id_plantilla_select = Integer.parseInt(listPlantillas.getFirst().getIdplantilla());
			}
		} catch (SQLException e) {
			// llamada a logs4j
			showMessageErrorConnectDB();
			log.error(e);
		}

		try {
			// recuperación de las partidas, conceptos y desgloses existentes en
			// la base de datos
			listPartidas = CPlantilla.findPartidaSelection();
		} catch (SQLException e) {
			// si no se puede recuperar la información , se registra el error
			log.error(e);
			showMessageErrorConnectDB();
		}
		// se llena el modelo de la partida
		fillTableModelPartida();

	}

	/**
	 * Método para llenar el modelo de la tabla partidas
	 */
	public void fillTableModelPartida() {
		// se limpia el modelo
		modelPartida.clear();
		// para cada partida en la lista de partidas se agrega al modelo
		for (PartidaSelection partidaSelection : listPartidas) {
			modelPartida.addPartida(partidaSelection);
		}
		tabPartidas.changeSelection(0, 1, false, false);
		// llamada al método para llenar el modelo de conceptos
		fillTableModelConcepto(tabPartidas.getSelectionModel().getLeadSelectionIndex());
	}

	/**
	 * Método para llenar el modelo de la tabla de conceptos
	 * 
	 * @param index
	 *            = indice de la partida seleccionada
	 */
	public void fillTableModelConcepto(int index) {
		// se limpia el modelo
		modelConcepto.clear();
		tabConceptos.repaint();
		// para cada concepto en la lista de conceptos obtenida apartir del
		// indice de partida seleccionada , agregar al modelo
		for (ConceptoSelection conceptoSelection : listPartidas.get(index).getSubsConceptos()) {
			modelConcepto.addConcepto(conceptoSelection);
		}
		tabConceptos.changeSelection(0, 1, false, false);
		// llamada al método para llenar el modelo de aspectos
		fillTableModelAspectos(index, tabConceptos.getSelectionModel().getLeadSelectionIndex());
	}

	/**
	 * Método para llenar el modelo de la tabla de aspectos
	 * 
	 * @param indexPartida
	 *            = indice de la partida seleccionada
	 * @param indexConcepto
	 *            = indice del concepto seleccionado
	 */
	public void fillTableModelAspectos(int indexPartida, int indexConcepto) {
		// se limpia el modelo de aspectos
		modelAspecto.clear();
		tabAspectos.repaint();
		// se obtiene la lista de relaciones entre aspectos y plantillas por
		// medio del id_plantilla
		try {
			listRelationship = CAspectosPlantillas.findRelationship(id_plantilla_select);
		} catch (SQLException e1) {
			// llamada a logs4j
			showMessageErrorConnectDB();
			log.error(e1);
		}

		// para cada uno de los aspectos en la lista de partidas de su lista de
		// conceptos agregar al modelo de aspectos
		for (AspectoSelection aspectoSelection : listPartidas.get(indexPartida).getSubConcepto(indexConcepto).getSubsAspectos()) {
			// llamada al método checkBoxAspectoPlantilla para determinar si el
			// aspecto se encuentra incluido en la plantilla seleccionada
			aspectoSelection.setSelect(checkBoxAspectoPlantilla(aspectoSelection.getIdaspecto()));
			modelAspecto.addAspecto(aspectoSelection);
		}
		tabAspectos.changeSelection(0, 1, false, false);
	}

	/**
	 * Método para determinar si el aspecto se encuentra incluido en la
	 * plantilla
	 * 
	 * @param id_aspecto
	 *            = ID_Aspecto a verificar si se encuentra en la plantilla
	 *            actual
	 * @return ? true si esta incluido : false si no esta incluido
	 */
	public boolean checkBoxAspectoPlantilla(int id_aspecto) {
		for (AspectoPlantilla relacion : listRelationship) {
			if (relacion.getIdAspecto() == id_aspecto) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Método para guardar una nueva plantilla
	 */
	public void savePlantilla() {
		// creación del componente para ingresar el nombre de la plantilla
		JTextField nombrePlantilla = ComponentsUser.getDataTxt(45, 2);
		// confirmación del nombre de la plantilla
		int result = JOptionPane.showConfirmDialog(null, nombrePlantilla, "Ingrese Nombre de la Plantilla: ", JOptionPane.OK_CANCEL_OPTION);

		// creacion de un nuevo objeto de tipo plantilla
		Plantilla plantilla = new Plantilla(nombrePlantilla.getText());
		switch (result) {
		case 0:
			// si el usuario acepto guardar la plantilla
			try {
				// llamaada al controlador para guardar la plantilla
				int idPlantilla = CPlantilla.save(plantilla);
				if (idPlantilla > 0) {
					// si la plantilla se guardo, recuperar si ID_Plantilla, se
					// agrega al combobox y se agrega a la lista de plantillas
					plantilla.setIdplantilla(String.valueOf(idPlantilla));
					listPlantillas.add(plantilla);
					cbNombrePlantilla.addItem(plantilla.getNombre());
				}
			} catch (MaxLength eLength) {
				JOptionPane.showMessageDialog(null, eLength.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
				log.warn(eLength);
			} catch (AttributeIsEmpty eAttributeEmpty) {
				JOptionPane.showMessageDialog(null, eAttributeEmpty.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
				log.warn(eAttributeEmpty);
			} catch (MySQLIntegrityConstraintViolationException eIntegridad) {
				if (eIntegridad.toString().contains("Duplicate")) {
					DuplicateKey eDuplicateKey = new DuplicateKey("Nombre Plantilla", plantilla.getNombre());
					JOptionPane.showMessageDialog(null, eDuplicateKey.getMessage());
					log.warn(eDuplicateKey, eIntegridad);
				}
			} catch (SQLException eSQL) {
				// mensaje = error de conexión a la base de datos
				showMessageErrorConnectDB();
				log.error(eSQL);
			}
			break;
		default:
			break;
		}
	}

	/**
	 * Método para Actualizar el nombre de una plantilla
	 */
	public void updatePlantilla() {
		// creación del componente para guardar el nuevo nombre
		JTextField nombrePlantilla = ComponentsUser.getDataTxt(25, 2);
		int option = JOptionPane.showConfirmDialog(null, nombrePlantilla, "Nuevo Nombre de la Plantilla: ", JOptionPane.OK_CANCEL_OPTION);

		// se obtiene la información de la plantilla seleccionada
		Plantilla plantilla;
		if (!(listPlantillas.size() > 1)) {
			plantilla = listPlantillas.getFirst();
		} else {
			plantilla = listPlantillas.get(cbNombrePlantilla.getSelectedIndex());
		}
		switch (option) {
		case 0:
			try {
				// se actualiza el nombre por medio de su controlador
				plantilla.setNombre(nombrePlantilla.getText());
				int result = CPlantilla.update(plantilla);
				if (result > 0) {
					// si se actualizo el nombre de la unidad, se elimina y se
					// agraga al combobox, al igual que de la lista
					cbNombrePlantilla.removeItemAt(cbNombrePlantilla.getSelectedIndex());
					cbNombrePlantilla.addItem(plantilla.getNombre());
					listPlantillas.remove(cbNombrePlantilla.getSelectedIndex());
					listPlantillas.add(plantilla);
				}
			} catch (MaxLength eLength) {
				JOptionPane.showMessageDialog(null, eLength.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
				log.warn(eLength);
			} catch (AttributeIsEmpty eAttributeEmpty) {
				JOptionPane.showMessageDialog(null, eAttributeEmpty.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
				log.warn(eAttributeEmpty);
			} catch (MySQLIntegrityConstraintViolationException eIntegridad) {
				if (eIntegridad.toString().contains("Duplicate")) {
					DuplicateKey eDuplicateKey = new DuplicateKey("Nombre Plantilla", plantilla.getNombre());
					JOptionPane.showMessageDialog(null, eDuplicateKey.getMessage());
					log.warn(eDuplicateKey, eIntegridad);
				}
			} catch (SQLException eSQL) {
				showMessageErrorConnectDB();
				log.error(eSQL);
			}

			break;
		default:
			break;
		}
	}

	/**
	 * Método para eliminar una plantilla de la base de datos
	 */
	public void deletePlantilla() {
		// se obtiene el indice de la plantilla seleccionada
		int selectItem = cbNombrePlantilla.getSelectedIndex();
		if (selectItem > -1) {
			// se confirma la eliminación de la plantilla
			int confirmDelete = JOptionPane.showConfirmDialog(null, "¿Deseas Eliminar esta Plantilla?", "Confirmar", JOptionPane.YES_NO_OPTION);
			if (confirmDelete == JOptionPane.YES_OPTION) {
				Plantilla plantilla = listPlantillas.get(selectItem);
				// se obtiene la infomación de la plantilla y se llama al
				// controlador para eliminarla
				try {
					if (CPlantilla.delete(plantilla) > 0) {
						// si se lleva a cabo la eliminación
						listPlantillas.remove(selectItem);
						cbNombrePlantilla.removeItemAt(selectItem);
					}
				} catch (MySQLIntegrityConstraintViolationException eIntegridad) {
					if (eIntegridad.toString().contains("Cannot delete or update")) {
						ForeingKeyReferenceDelete eForeingKey = new ForeingKeyReferenceDelete("Nombre Plantilla", plantilla.getNombre());
						JOptionPane.showMessageDialog(null, eForeingKey.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
						log.warn(eForeingKey, eIntegridad);
					}
				} catch (SQLException eSQL) {
					// mensaje = no se puede eliminar por que esta en uso la
					// unidad de medida por parte de algun desglose de concepto
					// se notifica al usuario
					// mensaje = error de conexión a la base de datos
					// llamada a log4j
					showMessageErrorConnectDB();
					log.error(eSQL);
				}
			}
		}
	}

	/**
	 * método para mostrar el mensaje de error al eliminar una plantilla
	 */
	public void showMessageErrorDelete() {
		JOptionPane.showMessageDialog(null, "La Plantilla no pudo elimarse", "Atención", JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Método para determinar si se agrega o se elimina una relación entre
	 * aspectos y plantillas
	 */
	public void saveOrDeleteRelation() {
		// se verifica la seleccion de la primera columna del modelo de la tabla
		// de aspectos
		if (tabAspectos.getColumnModel().getSelectionModel().getLeadSelectionIndex() == 0) {
			// se crea una relacion entre la plantilla y el aspecto por medio de
			// sus identificadores.
			AspectoPlantilla relation = new AspectoPlantilla(id_plantilla_select, listPartidas.get(tabPartidas.getSelectedRow()).getSubConcepto(tabConceptos.getSelectedRow()).getSubAspecto(tabAspectos.getSelectedRow()).getIdaspecto());
			if ((boolean) tabAspectos.getValueAt(tabAspectos.getSelectedRow(), 0)) {
				// si el valor del modelo es true = se agrega la relacion en la
				// base de datos
				try {
					CAspectosPlantillas.saveRelationship(relation);
				} catch (SQLException e1) {
					// llamada a logs 4j
					showMessageErrorConnectDB();
					log.error(e1);
				}
			} else {
				// si el valor del modelo es false = se elimina la relacion en
				// la base de datos
				try {
					CAspectosPlantillas.deleteRelationship(relation);
				} catch (SQLException e1) {
					// llamada a log4j
					showMessageErrorConnectDB();
					log.error(e1);
				}
			}
		}
	}

	/**
	 * Método para mostrar el mensaje error de conexión a la base de datos
	 */
	public void showMessageErrorConnectDB() {
		ErrorConnectionDB eConnectionDB = new ErrorConnectionDB();
		log.error(eConnectionDB);
		JOptionPane.showMessageDialog(null, eConnectionDB.getMessage());
	}
}
