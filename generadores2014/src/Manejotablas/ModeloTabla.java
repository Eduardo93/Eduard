package Manejotablas;

import javax.swing.table.*;
import javax.swing.event.*;

import MetodosRemotos.Metodos;
import ObjetosSerializables.Aspecto;
import java.util.LinkedList;

/**
 * Clase ModeloTabla Funciona para declarar el modelo de la tabla del catálogo
 * de aspectos
 */
public class ModeloTabla implements TableModel {

	// Lista de datos contenidos en el modelo
	private LinkedList<Aspecto> datos = new LinkedList<Aspecto>();
	private LinkedList<TableModelListener> listeners = new LinkedList<TableModelListener>();
	private Metodos con;
	private int indice;

	/**
	 * Conexión --- Obtiene los métodos para la conexón a la base de datos
	 * 
	 * @param c
	 *            Metodos --- Para la manipulación de la base de datos.
	 */
	public void conexion(Metodos c) {
		this.con = c;
	}

	/**
	 * meterindice Indice el número del indice que ha sido añadido a la lista
	 * 
	 * @param indice
	 */
	public void meterindice(int indice) {
		this.indice = indice;
	}

	/**
	 * Regresa el número de columnas
	 */
	public int getColumnCount() {
		return 5;
	}

	/**
	 * Regresar el número de filas
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
	 *            int--- índice de la columna
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		Aspecto aux;
		aux = (Aspecto) (datos.get(rowIndex));
		switch (columnIndex) {
		case 0:
			return aux.getClave_privada();
		case 1:
			return aux.getClave();
		case 2:
			return aux.getDescripcion();
		case 3:
			return aux.getUnidad();
		case 4:
			return aux.getCosto();
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
	public void borraAspecto(int fila) {
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
	public void anhadeAspecto(Aspecto asp) {
		datos.add(asp);
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
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
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
			return "Clave Pública";
		case 1:
			return "Clave Privada";
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

		Aspecto aux;
		aux = (Aspecto) (datos.get(rowIndex));
		switch (columnIndex) {
		/**
		 * 0 cambio por clave privada 1 cambio por clave pública 2 cambio por
		 * descripción 3 cambio por unidad 4 cambio por costo
		 */
		case 0:
			aux.setClave_privada((String) aValue);
			if (aux.getClave().equals("") == false && aux.getDescripcion().equals("") == false && aux.getUnidad().equals("") == false && aux.getCosto().equals("") == false) {
				con.modificaraspecto(aux.getClave(), aux.getUnidad(), aux.getCosto(), aux.getDescripcion(), String.valueOf(indice), aux.getClave_privada());
			}
			break;
		case 1:
			aux.setClave(((String) aValue));
			if (aux.getClave().equals("") == false && aux.getDescripcion().equals("") == false && aux.getUnidad().equals("") == false && aux.getCosto().equals("") == false) {
				con.modificaraspecto(aux.getClave(), aux.getUnidad(), aux.getCosto(), aux.getDescripcion(), String.valueOf(indice), aux.getClave_privada());
			}
			break;
		case 2:
			aux.setDescripcion((String) aValue);
			if (aux.getClave().equals("") == false && aux.getDescripcion().equals("") == false && aux.getUnidad().equals("") == false && aux.getCosto().equals("") == false) {
				con.modificaraspecto(aux.getClave(), aux.getUnidad(), aux.getCosto(), aux.getDescripcion(), String.valueOf(indice), aux.getClave_privada());
			}
			break;
		case 3:
			aux.setUnidad((String) aValue);
			if (aux.getClave().equals("") == false && aux.getDescripcion().equals("") == false && aux.getUnidad().equals("") == false && aux.getCosto().equals("") == false) {
				con.modificaraspecto(aux.getClave(), aux.getUnidad(), aux.getCosto(), aux.getDescripcion(), String.valueOf(aux.getIdaspecto()), aux.getClave_privada());
			}
			break;
		case 4:
			aux.setCosto((String) aValue);
			if (aux.getClave().equals("") == false && aux.getDescripcion().equals("") == false && aux.getUnidad().equals("") == false && aux.getCosto().equals("") == false) {
				con.modificaraspecto(aux.getClave(), aux.getUnidad(), aux.getCosto(), aux.getDescripcion(), String.valueOf(indice), aux.getClave_privada());
			}
			break;

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
	public LinkedList<Aspecto> getListaDatos() {
		return this.datos;
	}

	/**
	 * Limpiar la lista de los elementos del modelo de la tabla
	 */
	public void Limpiar() {
		this.datos = new LinkedList<Aspecto>();

	}

}