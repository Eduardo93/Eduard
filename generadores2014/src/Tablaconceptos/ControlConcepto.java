package Tablaconceptos;

import java.util.LinkedList;

import MetodosRemotos.Metodos;
import TablasPartidas.ModeloPartida;

/**
 * Clase ControlTipo Funciona para manipilar el modelo de la tabla de conceptos
 */
public class ControlConcepto {

	private ModeloConcepto modelo = new ModeloConcepto();

	/**
	 * Constructor para asignar el modelo de la tabla
	 * 
	 * @param modelo
	 *            ModeloTipo --- modelo de la tabla conceptos
	 * @return 
	 */
	public ControlConcepto() {
		this.modelo = new ModeloConcepto( );
	}

	/**
	 * Agregar una Fila al modelo
	 * 
	 * @param con
	 *            ConceptoL --- nuevo concepto para agregar
	 */
	public void anhadeFila(ConceptoL con) {
		modelo.anhadeconcepto(con);
	}

	/**
	 * Elimina una fila del modelo de datos
	 * 
	 * @param indice
	 *            int --- Índice de la fila a eliminar
	 */
	public void borraFila(int indice) {
		if (modelo.getRowCount() > 0)
			modelo.borrarconcepto(indice);
	}

	/**
	 * Obtener los elementos del modelo de datos
	 * 
	 * @return lista de elementos en el modelo de datos
	 */
	public LinkedList<ConceptoL> getListaDatos() {
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

	/**
	 * Asignación de métodos para la manipulación de la base de datos
	 * 
	 * @param con
	 *            Metodos --- acceso a la base de datos
	 */
	public void meterconexion(Metodos con) {
		modelo.conexion(con);
	}

	/**
	 * Asignación del Índice
	 * 
	 * @param indice
	 *            int --- índice
	 */
	public void meterIndice(int indice) {
		modelo.meterIndice(indice);
	}
	
	public ModeloConcepto getModel() {
		return modelo;
	}
}
