package TablaFrentes;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import MetodosRemotos.Metodos;
import ObjetosSerializables.Frente;

// import java.rmi.RemoteException;


import java.util.LinkedList;

/**
 * Clase ModeloFrente Funciona para declarar el modelo de la tabla de frentes
 */
public class Modelofrente implements TableModel {

	// Lista de datos contenidos en el modelo de frentes
	private LinkedList<Frente> datos = new LinkedList<Frente>();
	private LinkedList<TableModelListener> listeners = new LinkedList<TableModelListener>();
	// Métodos para la manipulación a la base de datos
	private Metodos cone;

	/**
	 * Asignación de la conexión
	 * 
	 * @param con
	 *            Metodos --- para la manipulación de la base de datos
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
		Frente aux = (Frente) datos.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return aux.getIndentificador();
		case 1:
			return aux.getUbicacion();

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
	public void borrarfrente(int indice) {
		datos.remove(indice);

		TableModelEvent evento = new TableModelEvent(this, indice, indice, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
		avisaSuscriptores(evento);
	}

	/**
	 * Agregar una fila al modelo de la tabla
	 * 
	 * @param tipo
	 *            String --- Nombre del nuevo Tipo de Proyecto
	 */
	public void anhadeFrente(Frente fre) {
		datos.add(fre);
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
			return "Indentificador";
		case 1:
			return "Ubicación";
		default:
			return null;
		}
	}

	/**
	 * Permitir Edición
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
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
		Frente aux;
		String tempIdentificadorUbicacion;
		aux = (Frente) datos.get(rowIndex);
		switch (columnIndex) {
			case 0:
				tempIdentificadorUbicacion= aux.getIndentificador();
				aux.setIdentificador((String) aValue);
				if (!aux.getIndentificador().equals("")) {
					cone.ModificarFrente(aux.getIndentificador(), aux.getUbicacion(), String.valueOf(aux.getIdfrente()));
				}else{
					JOptionPane.showMessageDialog(null, "No se permite este campo vacío...");
					setValueAt(tempIdentificadorUbicacion, rowIndex, columnIndex);
				}
				break;
			case 1:
				tempIdentificadorUbicacion=aux.getUbicacion();
				aux.setUbicacion((String) aValue);
				if (! aux.getUbicacion().equals("")) {
					cone.ModificarFrente(aux.getIndentificador(), aux.getUbicacion(), String.valueOf(aux.getIdfrente()));
				}else{
					JOptionPane.showMessageDialog(null, "No se permite este campo vacío...");
					setValueAt(tempIdentificadorUbicacion, rowIndex, columnIndex);
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
	public LinkedList<Frente> getListaDatos() {
		return this.datos;
	}

	/**
	 * Limpiar la lista de los elementos del modelo de la tabla
	 */
	public void Limpiar() {
		this.datos = new LinkedList<Frente>();
	}

}