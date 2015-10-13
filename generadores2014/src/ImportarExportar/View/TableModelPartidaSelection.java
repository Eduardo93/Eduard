package ImportarExportar.View;

import java.util.LinkedList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import Model.Entity.PartidaSelection;

/**
 * modelo para la tabla de partidas de seleccion 
 * @author Luis Angel Hernández Lázaro
 *
 */
public class TableModelPartidaSelection implements TableModel{

	/**
	 * elementos para crear el modelo
	 */
	private LinkedList<PartidaSelection> data;
	private LinkedList<TableModelListener> listeners;
	
	/**
	 * constructor del modelo
	 */
	public TableModelPartidaSelection() {
		this.data = new LinkedList<PartidaSelection>();
		this.listeners = new LinkedList<TableModelListener>();
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
			return "Partida Presupuestal";
		default:
			return null;
		}
	}

	@Override
	public int getRowCount() {
		return data.size();
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
		case 1:
			return false;
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
			data.get(rowIndex).setSelect((boolean)aValue);
			break;
		default:
			break;
		}
		notifySubs(new TableModelEvent(this, rowIndex, rowIndex, columnIndex));
	}

	/**
	 * agregar una partida al modelo 
	 * @param partida -- partida a agregar al modelo
	 */
	public void addPartida(PartidaSelection partida) {
		data.add(partida);
		notifySubs(new TableModelEvent(this, this.getRowCount() - 1, this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
	}

	/**
	 * 
	 * @param event
	 */
	public void notifySubs(TableModelEvent event) {
		for (int i = 0; i < listeners.size(); i++) {
			((TableModelListener) listeners.get(i)).tableChanged(event);
		}
	}

	/**
	 * método para limpiar el modelo de la tabla 
	 */
	public void clear() {
		this.data= new LinkedList<PartidaSelection>();
	}
	
}
