package AdministrarUnidadesMedida.View;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import org.apache.log4j.Logger;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import AdministrarUnidadesMedida.Controller.CUnidad;
import Model.Entity.Unidad;
import Model.EntityCRUD.Exceptions.AttributeIsEmpty;
import Model.EntityCRUD.Exceptions.DuplicateKey;
import Model.EntityCRUD.Exceptions.ErrorConnectionDB;
import Model.EntityCRUD.Exceptions.ForeingKeyReferenceDelete;
import Model.EntityCRUD.Exceptions.MaxLength;
import Model.EntityCRUD.Exceptions.Unidad.NoSelectAttributeUnidad;
import Options.ComponentsUser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Clase para la administración de unidades de medida
 * 
 * @author Luis Angel Hernández Lázaro
 *
 */
public class VUnidadesMedida extends javax.swing.JInternalFrame {
	/**
	 * elementos necesarios para la creacion de la ventana
	 */

	private static Logger log = Logger.getLogger(VUnidadesMedida.class);

	private static final long serialVersionUID = 1L;
	private JButton btnSave;
	private JButton btnDelete;

	private JScrollPane scrollUnidad;
	private JTable tabUnidad;
	private TableModelUnidad modelUnidad;

	private LinkedList<Unidad> listUnidad;

	private JLabel lblCatlogoDeUnidades;

	/**
	 * Create the frame.
	 */
	public VUnidadesMedida() {
		setTitle("Administración de Unidades");
		setClosable(true);
		setBounds(10, 10, 346, 428);
		getContentPane().setLayout(null);

		displayUnidad();

		btnSave = new JButton("Agregar Unidad");
		btnSave.addActionListener(new ActionListener() {
			/**
			 * llamada al método para guardar la unidad
			 * 
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				saveUnidad();
			}
		});
		btnSave.setBounds(10, 357, 150, 34);
		getContentPane().add(btnSave);

		btnDelete = new JButton("Eliminar Unidad");
		btnDelete.addActionListener(new ActionListener() {
			/**
			 * llamada al método para eliminar la unidad
			 * 
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent arg0) {
				deleteUnidad();
			}
		});
		btnDelete.setBounds(172, 357, 150, 34);
		getContentPane().add(btnDelete);

		lblCatlogoDeUnidades = new JLabel("Cat\u00E1logo de Unidades de Medida");
		lblCatlogoDeUnidades.setBounds(66, 6, 204, 14);
		getContentPane().add(lblCatlogoDeUnidades);

		scrollUnidad = new JScrollPane();
		scrollUnidad.setBounds(10, 28, 312, 299);
		getContentPane().add(scrollUnidad);
		scrollUnidad.setViewportView(tabUnidad);

		/*
		 * Diseño para la tabla y su columna
		 */
		tabUnidad.setRowHeight(30);
		TableColumn nameUnit = tabUnidad.getColumn("Nombre");
		nameUnit.setPreferredWidth(100);
		nameUnit.setCellEditor(new DefaultCellEditor(ComponentsUser.getDataTxt(25, 2)));
	}

	/**
	 * Método mostrar las unidades de la base de datos
	 */
	public void displayUnidad() {
		// Creación del modelo y asignación a la tabla de unidades
		modelUnidad = new TableModelUnidad();
		tabUnidad = new JTable(modelUnidad);

		// Recuperación de las Unidades de medida de la base de datos
		listUnidad = new LinkedList<Unidad>();
		try {
			listUnidad = CUnidad.findUnidades();
		} catch (ErrorConnectionDB eConnectionDB) {
			JOptionPane.showMessageDialog(null, eConnectionDB.getMessage());
			log.error(eConnectionDB);
		} catch (SQLException eSQL) {
			ErrorConnectionDB eConnectionDB = new ErrorConnectionDB();
			log.error(eConnectionDB, eSQL);
			JOptionPane.showMessageDialog(null, eConnectionDB.getMessage());
		}

		// Agregar unidades encontradas a la tabla
		for (Unidad unit : listUnidad) {
			modelUnidad.addUnidad(unit);
		}
	}

	/**
	 * Método para guardar una unidad en la base de datos
	 */
	public void saveUnidad() {
		// creación del componente para escribir el nombre de la unidad
		JTextField nombreUnidad = ComponentsUser.getDataTxt(15, 2);
		// componentes para marcar cuales dimesiones son asignadas a la unidad
		// de medida
		JCheckBox checkLargo = new JCheckBox("Largo");
		JCheckBox checkAncho = new JCheckBox("Ancho");
		JCheckBox checkAlto = new JCheckBox("Alto");

		// Objeto para solicitar la información de las unidades al usuario
		Object[] message = { "Nombre:", nombreUnidad, "Largo:", checkLargo, "Ancho:", checkAncho, "Alto:", checkAlto };

		int result = JOptionPane.showConfirmDialog(null, message, "Ingrese Nombre de Unidad: ", JOptionPane.OK_CANCEL_OPTION);
		// verificación de la opción del usuario
		switch (result) {
		case 0:
			// aceptar = guardar la unidad
			// creación de un objeto para la nueva unidad
			Unidad unidad = new Unidad(nombreUnidad.getText(), checkLargo.isSelected(), checkAncho.isSelected(), checkAlto.isSelected());
			try {
				// llamada al controlador para guardar la unidad
				int id_unidad = CUnidad.save(unidad);
				if (id_unidad > 0) {
					// si el resultado de guardar la unidad es mayor a 0 ,
					// significa que se guardo y recuperamos el ID_Unidad para
					// ser asignado, se agrega al modelo y se selecciona en la
					// tabla
					unidad.setIdUnidad(id_unidad);
					modelUnidad.addUnidad(unidad);
					tabUnidad.changeSelection(modelUnidad.getRowCount() - 1, 1, false, false);
				}
			} catch (NoSelectAttributeUnidad eNoSelect) {
				JOptionPane.showMessageDialog(null, eNoSelect.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
				log.warn(eNoSelect);
			} catch (MaxLength eLength) {
				JOptionPane.showMessageDialog(null, eLength.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
				log.warn(eLength);
			} catch (AttributeIsEmpty eAttributeEmpty) {
				JOptionPane.showMessageDialog(null, eAttributeEmpty.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
				log.warn(eAttributeEmpty);
			} catch (MySQLIntegrityConstraintViolationException eIntegridad) {
				if (eIntegridad.toString().contains("Duplicate")) {
					DuplicateKey eDuplicateKey = new DuplicateKey("Nombre Unidad", unidad.getNombre());
					JOptionPane.showMessageDialog(null, eDuplicateKey.getMessage());
					log.warn(eDuplicateKey, eIntegridad);
				}
			} catch (SQLException eSQL) {
				// mensaje = error de conexión a la base de datos
				ErrorConnectionDB eConnectionDB = new ErrorConnectionDB();
				log.error(eConnectionDB, eSQL);
				JOptionPane.showMessageDialog(null, eConnectionDB.getMessage());
			}
			break;
		default:
			break;
		}
	}

	/**
	 * Método para eliminar una unidad de la base de datos
	 */
	public void deleteUnidad() {
		// se obteiene el indice de la tabla para obtener la unidad seleccionada
		int selectItem = tabUnidad.getSelectionModel().getLeadSelectionIndex();
		if (selectItem > -1) {
			// se confirma la eliminación de la unidada de la base de datos
			int confirmDelete = JOptionPane.showConfirmDialog(null, "¿Deseas Eliminar Esta Unidad?", "Confirmar", JOptionPane.YES_NO_OPTION);
			if (confirmDelete == JOptionPane.YES_OPTION) {
				// si se confirma la eliminación se obtiene la unidad
				// seleccionada
				Unidad unidad = modelUnidad.getUnidad(selectItem);
				try {
					// llamada al controlador para eliminar la unidad
					if (CUnidad.delete(unidad) > 0) {
						// si el resultado es mayor a 0 se quita la unidad de la
						// tabla para su visualización
						modelUnidad.deleteUnidad(selectItem);
					}
				} catch (MySQLIntegrityConstraintViolationException eIntegridad) {
					if (eIntegridad.toString().contains("Cannot delete or update")) {
						ForeingKeyReferenceDelete eForeingKey = new ForeingKeyReferenceDelete("Nombre Unidad", unidad.getNombre());
						JOptionPane.showMessageDialog(null, eForeingKey.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
						log.warn(eForeingKey, eIntegridad);
					}
				} catch (SQLException eSQL) {
					// mensaje = no se puede eliminar por que esta en uso la
					// unidad de medida por parte de algun desglose de concepto
					// se notifica al usuario
					// mensaje = error de conexión a la base de datos
					ErrorConnectionDB eConnectionDB = new ErrorConnectionDB();
					log.error(eConnectionDB, eSQL);
					JOptionPane.showMessageDialog(null, eConnectionDB.getMessage());
				}
			}
		}
	}
}
