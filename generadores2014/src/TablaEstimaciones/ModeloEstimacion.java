package TablaEstimaciones;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import java.util.LinkedList;
import ObjetosSerializables.Estimacion;

/**
 * Clase ModeloTipo Funciona para declarar el modelo de la tabla de tipos de las
 * estimaciones
 */
public class ModeloEstimacion implements TableModel {

	// Lista de datos contenidos en el modelo
	private LinkedList<Estimacion> datos = new LinkedList<Estimacion>();
	private LinkedList<TableModelListener> listeners = new LinkedList<TableModelListener>();

	/**
	 * Número de columnas existentes en el modelo de datos
	 */
	public int getColumnCount() {
		return 1;
	}

	/**
	 * Número de elementos en la lista de datos
	 */
	public int getRowCount() {
		return datos.size();
	}

	/**
	 * Obtiene el valor de una celda de la tabla
	 * 
	 * @param rowIndex
	 *            int --- índice de la fila
	 * @param columnIndex
	 *            int --- índice de la columna
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		Estimacion aux;
		aux = (Estimacion) (datos.get(rowIndex));
		switch (columnIndex) {
		case 0:
			return aux.getFecha();
		default:
			return null;
		}
	}

	/**
	 * Elimina una fila del modelo de la tabla
	 * 
	 * @param fila
	 *            int --- Índice de la fila a eliminar
	 */
	public void borraEstimacion(int fila) {
		datos.remove(fila);

		TableModelEvent evento = new TableModelEvent(this, fila, fila, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
		avisaSuscriptores(evento);
	}

	/**
	 * Agregar una fila al modelo de la tabla
	 * 
	 * @param tipo
	 *            String --- Nombre del nuevo Tipo de Proyecto
	 */
	public void anhadeEstimacion(Estimacion es) {
		datos.add(es);
		TableModelEvent evento;
		evento = new TableModelEvent(this, this.getRowCount() - 1, this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
		avisaSuscriptores(evento);
	}

	/**
	 * Agregar Listener al modelo
	 */
	public void addTableModelListener(TableModelListener l) {
		listeners.add(l);
	}

	/**
	 * Obtener el tipo de clase de la coluna elegida
	 */
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;
		default:
			return Object.class;
		}
	}

	/**
	 * Obtener Nombres de las Columnas del Modelo
	 */
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "Fecha";
		default:
			return null;
		}
	}

	/**
	 * Permitir Edición
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	/**
	 * Eliminar un elemento de la lista de Listeners del modelo
	 */
	public void removeTableModelListener(TableModelListener l) {
		listeners.remove(l);
	}

	/**
	 * Fijar un valor a una celda de la tabla en el modelo
	 */
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Estimacion aux;
		aux = (Estimacion) (datos.get(rowIndex));
		aux.setFecha(((String) aValue));
		TableModelEvent evento = new TableModelEvent(this, rowIndex, rowIndex, columnIndex);
		avisaSuscriptores(evento);
	}

	/**
	 * Avisa cual es la fila y columna que cambió
	 * 
	 * @param evento
	 */
	private void avisaSuscriptores(TableModelEvent evento) {
		int i;
		for (i = 0; i < listeners.size(); i++)
			((TableModelListener) listeners.get(i)).tableChanged(evento);
	}

	/**
	 * Obtener los Datos del modelo de la tabla
	 * 
	 * @return lista de elementos
	 */
	public LinkedList<Estimacion> getListaDatos() {
		return this.datos;
	}

	/**
	 * Limpiar la lista de los elementos del modelo de la tabla
	 */
	public void Limpiar() {
		this.datos = new LinkedList<Estimacion>();
	}
}