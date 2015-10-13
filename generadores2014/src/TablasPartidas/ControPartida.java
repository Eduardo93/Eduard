package TablasPartidas;

import java.util.LinkedList;

/**
 * Clase ControlTipo Funciona para manipilar el modelo de la tabla de Partidas
 */
public class ControPartida {

	// modelo de la tabla de Partidas de la clase ModeloPartida
	private ModeloPartida modelo = new ModeloPartida();

	/**
	 * Constructor para asignar el modelo de la tabla
	 * 
	 * @param modelo
	 *            ModeloTipo --- modelo de la tabla partidas
	 */
	public ControPartida() {
		this.modelo = new ModeloPartida( );;
	}

	/**
	 * Agregar una Fila al modelo
	 * 
	 * @param tipo
	 *            String --- Nombre de la partida para agregar
	 */
	public void anhadeFila(String par) {
		modelo.anhadePartida(par);
	}

	/**
	 * Número de elementos en el modelo de datos
	 * 
	 * @return total de elementos int
	 */
	public int numeropartidas() {
		return modelo.getRowCount();
	}

	/**
	 * Elimina una fila del modelo de datos
	 * 
	 * @param indice
	 *            int --- Índice de la fila a eliminar
	 */
	public void borraFila(int indice) {
		if (modelo.getRowCount() >= 0) {
			modelo.borraPartida(indice);
			//((AbstractTableModel) modelo).fireTableDataChanged();
		}
	}

	/**
	 * Obtener los elementos del modelo de datos
	 * 
	 * @return lista de elementos en el modelo de datos
	 */
	public LinkedList<String> getListaDatos() {
		return modelo.getListaDatos();
	}

	/**
	 * Número de elementos en el modelo de datos
	 * 
	 * @return total de elementos int
	 */
	public int Tama() {
		return modelo.getRowCount();
	}

	/**
	 * Limpiar la lista de los elementos del modelo de la tabla
	 */
	public void Limpiar() {
		modelo.Limpiar();
	}
	
	public ModeloPartida getModel() {
		return modelo;
	}
}
