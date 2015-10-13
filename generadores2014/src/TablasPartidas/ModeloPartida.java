
package TablasPartidas;

import javax.swing.table.*;
import javax.swing.event.*;

import java.util.LinkedList;

/**
 * Clase ModeloPartida
 * Funciona para declarar el modelo
 * de la tabla de partidas
 */
public class ModeloPartida implements TableModel {

	// lista de datos contenidos en el modelo
	private LinkedList < String > datos = new LinkedList < String >( );
	private LinkedList < TableModelListener > listeners = new LinkedList < TableModelListener >( );

	/**
	 * Número de columnas existentes en el modelo de datos
	 */
	public int getColumnCount ( ) {
		return 1;
	}

	/**
	 * Número de elementos en la lista de datos
	 */
	public int getRowCount ( ) {
		return datos.size( );
	}

	/**
	 * Obtiene el valor de una celda de la tabla
	 * 
	 * @param rowIndex
	 *            int --- índice de la fila
	 * @param columnIndex
	 *            int --- índice de la columna
	 */
	public Object getValueAt ( int rowIndex , int columnIndex ) {
		switch ( columnIndex ) {
			case 0 :
				return datos.get( rowIndex );

			default :
				return null;
		}
	}

	/**
	 * Elimina una fila del modelo de la tabla
	 * 
	 * @param fila
	 *            int --- Índice de la fila a eliminar
	 */
	public void borraPartida ( int fila ) {
		datos.remove( fila );
		//fireTableDataChanged( );
		TableModelEvent evento = new TableModelEvent( this, fila, fila, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE );
		avisaSuscriptores(evento);
	}

	/**
	 * Agregar una fila al modelo de la tabla
	 * 
	 * @param tipo
	 *            String --- Nombre del nuevo Tipo de Proyecto
	 */
	public void anhadePartida ( String par ) {
		datos.add( par );
		TableModelEvent evento;
		evento = new TableModelEvent( this, this.getRowCount( ) - 1, this.getRowCount( ) - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT );
		avisaSuscriptores( evento );
	}

	/**
	 * Agregar Listener al modelo
	 */
	public void addTableModelListener ( TableModelListener l ) {
		listeners.add( l );
	}

	/**
	 * Obtener el tipo de clase de la coluna elegida
	 */
	public Class < ? > getColumnClass ( int columnIndex ) {
		switch ( columnIndex ) {
			case 0 :
				return String.class;

			default :
				return Object.class;
		}
	}

	/**
	 * Obtener Nombres de las Columnas del Modelo
	 */
	public String getColumnName ( int columnIndex ) {
		switch ( columnIndex ) {
			case 0 :
				return "Partidas Prepuestales";
			default :
				return null;
		}
	}

	/**
	 * Permitir Edición
	 */
	public boolean isCellEditable ( int rowIndex , int columnIndex ) {
		return false;
	}

	/**
	 * Eliminar un elemento de la lista de Listeners del modelo
	 */
	public void removeTableModelListener ( TableModelListener l ) {
		listeners.remove( l );
	}

	/**
	 * Fijar un valor a una celda de la tabla en el modelo
	 */
	public void setValueAt ( Object aValue , int rowIndex , int columnIndex ) {
		@ SuppressWarnings ( "unused" )
		String aux;
		aux = ( String ) datos.get( rowIndex );
		switch ( columnIndex ) {
			case 0 :
				aux = ( String ) aValue;
				break;
			default :
				break;
		}
		TableModelEvent evento = new TableModelEvent( this, rowIndex, rowIndex, columnIndex );
		avisaSuscriptores( evento );
	}

	/**
	 * Avisa cual es la fila y columna que cambió
	 * 
	 * @param evento
	 */
	private void avisaSuscriptores ( TableModelEvent evento ) {
		int i;
		for ( i = 0 ; i < listeners.size( ) ; i++ )
			( ( TableModelListener ) listeners.get( i ) ).tableChanged( evento );
	}

	/**
	 * Obtener los Datos del modelo de la tabla
	 * 
	 * @return lista de cadenas de nombre de partidas
	 */
	public LinkedList < String > getListaDatos ( ) {
		return this.datos;
	}

	/**
	 * Limpiar la lista de elementos
	 */
	public void Limpiar ( ) {
		this.datos = new LinkedList < String >( );
	}
}
