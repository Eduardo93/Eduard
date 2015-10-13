package Tablaplantillas;

import java.util.LinkedList;
import ObjetosSerializables.Plantilla;

/**
 * Clase ControlPlantilla Funciona para manipilar el modelo de la tabla de
 * Plantillas
 */
public class ControlPlantilla {

	// modelo de la tabla de tipo proyecto de la clase ModeloTipo
	private ModeloPlantilla modelo = new ModeloPlantilla();

	/**
	 * Constructor para asignar el modelo de la tabla
	 * 
	 * @param modelo
	 *            ModeloTipo --- modelo de la tabla tipo de proyectos
	 */
	public ControlPlantilla(ModeloPlantilla modelo) {
		this.modelo = modelo;
	}

	/**
	 * Agregar una Fila al modelo
	 * 
	 * @param tipo
	 *            String --- Nombre del Tipo de Proyecto para agregar
	 */
	public void anhadeFila(Plantilla pla) {
		modelo.anhadePlantilla(pla);
	}

	/**
	 * Elimina una fila del modelo de datos
	 * 
	 * @param indice
	 *            int --- Índice de la fila a eliminar
	 */
	public void borraFila(int indice) {
		if (modelo.getRowCount() > 0)
			modelo.borraPlantilla(indice);
	}

	/**
	 * Obtener los elementos del modelo de datos
	 * 
	 * @return lista de elementos en el modelo de datos
	 */
	public LinkedList<Plantilla> getListaDatos() {
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