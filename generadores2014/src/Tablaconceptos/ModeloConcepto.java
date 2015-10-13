package Tablaconceptos;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import MetodosRemotos.Metodos;

import java.util.LinkedList;

/**
 * Clase ModeloTipo Funciona para declarar el modelo de la tabla de Conceptos
 */
public class ModeloConcepto implements TableModel {

	// Lista de datos contenidos en el modelo
	private LinkedList<ConceptoL> datos = new LinkedList<ConceptoL>();
	private LinkedList<TableModelListener> listeners = new LinkedList<TableModelListener>();
	private int indice;
	private Metodos cone;

	/**
	 * Asignación de los metodos
	 * 
	 * @param con
	 *            Metodos --- conexión para la manipulación de la base de datos
	 */
	public void conexion(Metodos con) {
		this.cone = con;
	}

	/**
	 * Número de columnas existentes en el modelo de datos
	 */
	public int getColumnCount() {
		return 2;
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
		ConceptoL aux = (ConceptoL) datos.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return aux.getIdpartida();
		case 1:
			return aux.getNombre();

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
	public void borrarconcepto(int indice) {
		datos.remove(indice);
		TableModelEvent evento = new TableModelEvent(this, indice, indice, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
		avisaSuscriptores(evento);
	}

	/**
	 * Agregar una fila al modelo de la tabla
	 * 
	 * @param con
	 *            ConceptoL --- Nuevo concepto a agregar
	 */
	public void anhadeconcepto(ConceptoL con) {
		datos.add(con);
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
		case 1:
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
			return "Partida";
		case 1:
			return "Concepto";
		default:
			return null;
		}
	}

	/**
	 * Permitir Edición
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return false;
		case 1:
			return true;
		default:
			return true;
		}
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
		ConceptoL aux;
		String tempNombreConcepto;
		aux = (ConceptoL) datos.get(rowIndex);
		switch (columnIndex) {
		case 0:
			tempNombreConcepto = aux.getIdpartida();
			aux.setIdpartida((String) aValue);
			if (!aux.getIdpartida().equals("") && !aux.getNombre().equals("")) {
				cone.modificarconceptos(aux.getIdconcepto(), aux.getNombre(), String.valueOf(indice));
			} else {
				JOptionPane.showMessageDialog(null, "No se permite este campo vacío...");
				setValueAt(tempNombreConcepto, rowIndex, columnIndex);
			}
			break;
		case 1:
			tempNombreConcepto = aux.getNombre();
			aux.setNombre((String) aValue);
			if (aux.getIdpartida().equals("") == false && aux.getNombre().equals("") == false) {
				cone.modificarconceptos(aux.getIdconcepto(), aux.getNombre(), String.valueOf(indice));
			} else {
				JOptionPane.showMessageDialog(null, "No se permite este campo vacío...");
			}
		default:
			break;
		}
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
	public LinkedList<ConceptoL> getListaDatos() {
		return this.datos;
	}

	/**
	 * Limpiar la lista de los elementos del modelo de la tabla
	 */
	public void Limpiar() {
		this.datos = new LinkedList<ConceptoL>();
	}

	/**
	 * asignación del índice
	 * 
	 * @param indice
	 *            int --- índice
	 */
	public void meterIndice(int indice) {
		this.indice = indice;
	}

}