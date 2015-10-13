package ImportarExportar.View;

import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import Model.Entity.AspectoSelection;

/**
 * Modelo para la tabla de desgloses de concepto de seleccion 
 * @author Luis Angel Hernández Lázaro
 *
 */
public class TableModelDesgloseAspectoSelection implements TableModel {

	/**
	 * Elementos para la creación del modelo
	 */
	private LinkedList<AspectoSelection> data;
	private LinkedList<TableModelListener> listeners;

	/**
	 * constructor del modelo 
	 */
	public TableModelDesgloseAspectoSelection() {
		data = new LinkedList<AspectoSelection>();
		listeners = new LinkedList<TableModelListener>();
	}

	/**
	 * agregar un nuevo listener a al modelo
	 */
	public void addTableModelListener(TableModelListener l) {
		listeners.add(l);
	}

	/**
	 * asignar el tipo de datos de las columnas
	 * sólo la primera es boolean para obtener el check box en la tabla 
	 * las demás son Objectos, sólo son para representar la información del desglose
	 */
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return Boolean.class;
		default:
			return Object.class;
		}
	}

	/**
	 * número de columnas en el modelo
	 */
	public int getColumnCount() {
		return 5;
	}

	/**
	 * titulos de las columnas del modelo	
	 */
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "Seleccionar";
		case 1:
			return "Clave Pública";
		case 2:
			return "Descripción";
		case 3:
			return "Unidad";
		case 4:
			return "Costo";
		default:
			return null;
		}
	}

	/**
	 * número de elementos (filas) en el modelo 
	 */
	public int getRowCount() {
		return data.size();
	}

	/**
	 * obtener información de los elementos para visualizar en la tabla
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return data.get(rowIndex).isSelect();
		case 1:
			return data.get(rowIndex).getClave();
		case 2:
			return data.get(rowIndex).getDescripcion();
		case 3:
			return data.get(rowIndex).getUnidad();
		case 4:
			return data.get(rowIndex).getCosto();
		default:
			return null;
		}
	}

	/**
	 * atributo para permitir la edición de columnas 
	 * sólo la primera es editable para asignar el valor true o false 
	 * las demas no permiten la edición para no alterar la información
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return true;
		default:
			return false;
		}
	}

	/**
	 * remover un listener del modelo
	 */
	public void removeTableModelListener(TableModelListener l) {
		listeners.remove(l);
	}

	/**
	 * establecer un valor para el elemento del modelo , sólo se asigna al atributo de seleccion
	 */
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			data.get(rowIndex).setSelect((boolean) aValue);
			break;
		default:
			break;
		}
		notifySubs(new TableModelEvent(this, rowIndex, rowIndex, columnIndex));
	}
	
	/**
	 * agregar un desglose al modelo
	 * @param aspecto -- desglose de concepto para agregar al modelo de la tabla
	 */
	public void addAspecto(AspectoSelection aspecto) {
		data.add(aspecto);
		notifySubs(new TableModelEvent(this, this.getRowCount() - 1, this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
	}

	/**
	 * notificacion para los subscritores
	 * @param event -- evento que debe avisar al modelo
	 */
	public void notifySubs(TableModelEvent event) {
		for (int i = 0; i < listeners.size(); i++) {
			((TableModelListener) listeners.get(i)).tableChanged(event);
		}
	}

	/**
	 * método para limpiar los elementos del modelo
	 */
	public void clear() {
		this.data = new LinkedList<AspectoSelection>();
	}

}
