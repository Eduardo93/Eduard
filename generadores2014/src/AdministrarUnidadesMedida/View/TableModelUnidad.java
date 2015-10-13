package AdministrarUnidadesMedida.View;

import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import AdministrarUnidadesMedida.Controller.CUnidad;
import Model.Entity.Unidad;
import Model.EntityCRUD.Exceptions.AttributeIsEmpty;
import Model.EntityCRUD.Exceptions.MaxLength;
import Model.EntityCRUD.Exceptions.Unidad.NoSelectAttributeUnidad;

/**
 * Modelo para la Tabla de Unidades
 * 
 * @author Luis Angel Hernández Lázaro
 *
 */
public class TableModelUnidad implements TableModel {

	/**
	 * Elementos para la creacón del modelo, datos mostrados en la tabla y
	 * listeners
	 */
	private LinkedList<Unidad> data;
	private LinkedList<TableModelListener> listeners;
	private static final Logger log = Logger.getLogger(TableModelUnidad.class);

	/**
	 * Constructor del modelo de la tabla
	 */
	public TableModelUnidad() {
		// declaración de nuevos objetos para los elementos del modelo
		this.data = new LinkedList<Unidad>();
		this.listeners = new LinkedList<TableModelListener>();
	}

	/**
	 * agregar listener al modelo
	 * 
	 * @see javax.swing.table.TableModel#addTableModelListener(javax.swing.event.TableModelListener)
	 */
	public void addTableModelListener(TableModelListener l) {
		listeners.add(l);
	}

	/**
	 * obtener el tipo de clase de las columnas del modelo de la tabla la
	 * primera columna es cadena, las demás son de tipo boolean ya que se deben
	 * visualizar como un check box
	 * 
	 * @see javax.swing.table.TableModel#getColumnClass(int)
	 */
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
			return Boolean.class;
		case 2:
			return Boolean.class;
		case 3:
			return Boolean.class;
		default:
			return Object.class;
		}
	}

	/**
	 * obtener el total de columnas del modelo
	 * 
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return 4;
	}

	/**
	 * Obtener el titulo de la columna
	 * 
	 * @see javax.swing.table.TableModel#getColumnName(int)
	 */
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "Nombre";
		case 1:
			return "Largo";
		case 2:
			return "Ancho";
		case 3:
			return "Alto";
		default:
			return null;
		}
	}

	/**
	 * obtener el total de elementos en el modelo.
	 * 
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return data.size();
	}

	/**
	 * Obtener el valor almacenado para cada una de las columnas del modelo,
	 * dependiendo del contenido en la lista de datos
	 * 
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return data.get(rowIndex).getNombre();
		case 1:
			return data.get(rowIndex).isLargo();
		case 2:
			return data.get(rowIndex).isAncho();
		case 3:
			return data.get(rowIndex).isAlto();
		default:
			return null;
		}
	}

	/**
	 * obtener si la celda es editable, se regresa true en todas, ya que pueden
	 * ser modificadas por el usuario a su necesidad
	 * 
	 * @see javax.swing.table.TableModel#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	/**
	 * remover listener del modelo
	 * 
	 * @see javax.swing.table.TableModel#removeTableModelListener(javax.swing.event.TableModelListener)
	 */
	public void removeTableModelListener(TableModelListener l) {
		listeners.remove(l);
	}

	/**
	 * Asignar un valor a una celda de la tabla
	 * 
	 * @see javax.swing.table.TableModel#setValueAt(java.lang.Object, int, int)
	 */
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// se crea auxiliar para saber que elemento esta seleccionado desde de
		// la tabla
		Unidad auxUnidad = data.get(rowIndex);
		Unidad nameOld = new Unidad();
		nameOld.setNombre(auxUnidad.getNombre());
		// verificación para saber que columna es la que va a ser actualizada en
		// la undad de medida
		switch (columnIndex) {
		case 0:
			// si el nombre es actualizado se asigna el nuevo valor y se
			// actualiza por medio del controlador de la unidad
			auxUnidad.setNombre((String) aValue);
			break;

		case 1:
			// si el atributo largo es actualizado se asigna su nuevo valor
			// y se actualiza por medio del controlador de la unidad
			auxUnidad.setLargo((Boolean) aValue);
			break;
		case 2:
			// si el atributo ancho es actualizado se asigna su nuevo valor
			// y se actualiza por medio del controlador de la unidad
			auxUnidad.setAncho((Boolean) aValue);
			break;
		case 3:
			// si el atributo alto es actualizado se asigna su nuevo valor y
			// se actualiza por medio del controlador de la unidad
			auxUnidad.setAlto((Boolean) aValue);
			break;
		default:
			break;
		}
		
		try {
			CUnidad.update(auxUnidad);
		} catch (NoSelectAttributeUnidad eNoSelect) {
			JOptionPane.showMessageDialog(null, eNoSelect.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
			log.warn(eNoSelect);
			setValueAt(true, rowIndex, columnIndex);
		} catch (MaxLength eLength) {
			JOptionPane.showMessageDialog(null, eLength.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
			log.warn(eLength);
			setValueAt(nameOld.getNombre(), rowIndex, columnIndex);
		} catch (AttributeIsEmpty eAttributeEmpty) {
			JOptionPane.showMessageDialog(null, eAttributeEmpty.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
			log.warn(eAttributeEmpty);
			setValueAt(nameOld.getNombre(), rowIndex, columnIndex);
		} catch (MySQLIntegrityConstraintViolationException eIntegridad) {
			if (eIntegridad.toString().contains("Duplicate")) {
				JOptionPane.showMessageDialog(null, "El nombre de Unidad ya existe en la Base de Datos", "Atención", JOptionPane.WARNING_MESSAGE);
				log.warn(eIntegridad);
				setValueAt(nameOld, rowIndex, columnIndex);
			}
		} catch (SQLException eSQL) {
			// mensaje = error de conexión a la base de datos
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al Intentar al Conectar a la Base de Datos", "Atención", JOptionPane.ERROR_MESSAGE);
			log.error(eSQL);
		}	
		
		// notificación para los elementos de la tabla
		notifySubs(new TableModelEvent(this, rowIndex, rowIndex, columnIndex));
	}

	/**
	 * Método para actualizar los atributos de la unidad, aquí se realiza la
	 * llam
	 * 
	 * @param unidad
	 * @throws AttributeIsEmpty
	 * @throws MaxLength
	 * @throws NoSelectAttributeUnidad
	 * @throws Exception
	 */
	public void updateUnidad(Unidad unidad) {
		
	}

	/**
	 * si se desea desmarcar todos los atributos de la unidad, nofiticar al
	 * usuario que debe por lo menos seleccionar 1
	 * 
	 * @param aValue
	 *            - valor actual de la celda
	 * @param rowIndex
	 *            - indice de la fila seleccionada
	 * @param columnIndex
	 *            - indice de la columna seleccionada
	 */
	public void ShowMessageCheckAtrib(Object aValue, int rowIndex, int columnIndex) {
		JOptionPane.showMessageDialog(null, "Seleccione por lo menos un Atributo para esta Unidad de Medida");
		setValueAt(aValue, rowIndex, columnIndex);
	}

	/**
	 * Agregar una unidad al modelo
	 * 
	 * @param unidad
	 */
	public void addUnidad(Unidad unidad) {
		data.add(unidad);
		notifySubs(new TableModelEvent(this, this.getRowCount() - 1, this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
	}

	/**
	 * Eliminar una unidad del modelo
	 * 
	 * @param row
	 */
	public void deleteUnidad(int index) {
		data.remove(index);
		notifySubs(new TableModelEvent(this, index, index, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE));
	}

	/**
	 * obtener una unidad del modelo por medio del indice
	 * 
	 * @param index
	 * @return
	 */
	public Unidad getUnidad(int index) {
		return this.data.get(index);
	}

	/**
	 * Método para notificar a los subcriptores un evento del modelo
	 * 
	 * @param event
	 */
	public void notifySubs(TableModelEvent event) {
		for (TableModelListener listener : listeners) {
			listener.tableChanged(event);
		}
	}

}