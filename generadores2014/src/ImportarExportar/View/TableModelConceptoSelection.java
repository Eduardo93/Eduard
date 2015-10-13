package ImportarExportar.View;

import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import Model.Entity.ConceptoSelection;

/**
 * clase para manipular los conceptos del modelo de la tabla de conceptos 
 * @author Luis Angel Hernández Lázaro
 *
 */
public class TableModelConceptoSelection implements TableModel {

	/**
	 * elementos del modelo
	 */
	private LinkedList<ConceptoSelection> data;
	private LinkedList<TableModelListener> listeners;
	
	/**
	 * constructor del modelo
	 */
	public TableModelConceptoSelection() {
		this.data=new LinkedList<ConceptoSelection>();
		this.listeners= new LinkedList<TableModelListener>();
	}
	
	@Override
	public void addTableModelListener(TableModelListener l) {
		listeners.add(l);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return Boolean.class;
		case 1:
			return String.class;
		default:
			return Object.class;
		}
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "Seleccionar";
		case 1:
			return "Concepto";
		default:
			return null;
		}
	}

	@Override
	public int getRowCount() {
		return this.data.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return data.get(rowIndex).isSelect();
		case 1:
			return data.get(rowIndex).getNombre();
		default:
			return null;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return true;
		default:
				return false;
		}
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		listeners.remove(l);
	}

	@Override
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
	 * agregar concepto al modelo de la tabla
	 * @param concepto -- concepto a agregar 
	 */
	public void addConcepto(ConceptoSelection concepto) {
		data.add(concepto);
		notifySubs(new TableModelEvent(this, this.getRowCount() - 1, this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
	}
	
	/**
	 * notificación a los subscritores del modelo
	 * @param event -- evento a notificar
	 */
	public void notifySubs(TableModelEvent event) {
		for (int i = 0; i < listeners.size(); i++) {
			((TableModelListener) listeners.get(i)).tableChanged(event);
		}
	}
	
	/**
	 * método para limpiar las filas en el modelo de la tabla
	 */
	public void clear() {
		this.data = new LinkedList<ConceptoSelection>();
	}	

}
