package TablaEstimaciones;

import java.util.LinkedList;
import ObjetosSerializables.Estimacion;

/**
 * Clase ControlTipo Funciona para manipilar el modelo de la tabla de
 * Estimaciones(fechas)
 */
public class ControlEstimacion {

	// modelo de la tabla de tipo proyecto de la clase ModeloTipo
	private ModeloEstimacion modelo = new ModeloEstimacion();

	/**
	 * Constructor para asignar el modelo de la tabla
	 * 
	 * @param modelo
	 *            ModeloTipo --- modelo de la tabla tipo de proyectos
	 */
	public ControlEstimacion(ModeloEstimacion modelo) {
		this.modelo = modelo;
	}

	/**
	 * Agregar una Fila al modelo
	 * 
	 * @param tipo
	 *            String --- Nombre del Tipo de Proyecto para agregar
	 */
	public void anhadeFila(Estimacion es) {
		modelo.anhadeEstimacion(es);
	}

	/**
	 * Elimina una fila del modelo de datos
	 * 
	 * @param indice
	 *            int --- Índice de la fila a eliminar
	 */
	public void borraFila(int indice) {
		if (modelo.getRowCount() > 0)
			modelo.borraEstimacion(indice);
	}

	/**
	 * Obtener los elementos del modelo de datos
	 * 
	 * @return lista de elementos en el modelo de datos
	 */
	public LinkedList<Estimacion> getListaDatos() {
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
}