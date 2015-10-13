package TablaFrentes;

import java.util.LinkedList;
import MetodosRemotos.Metodos;
import ObjetosSerializables.Frente;

/**
 * Clase ControlFrente Funciona para manipilar el modelo de la tabla de Frentes
 */
public class Controlfrente {

	// modelo de la tabla de Frentes de la clase ModeloTipo
	private Modelofrente modelo = new Modelofrente();

	/**
	 * Constructor para asignar el modelo de la tabla
	 * 
	 * @param modelo
	 *            ModeloFrente --- modelo de la tabla de frentes
	 */
	public Controlfrente(Modelofrente modelo) {
		this.modelo = modelo;
	}

	/**
	 * Agregar una Fila al modelo
	 * 
	 * @param fre
	 *            Frente--- Frente para agregar
	 */
	public void anhadeFila(Frente fre) {
		modelo.anhadeFrente(fre);
	}

	/**
	 * Elimina una fila del modelo de datos
	 * 
	 * @param indice
	 *            int --- Índice de la fila a eliminar
	 */
	public void borraFila(int indice) {
		if (modelo.getRowCount() > 0)
			modelo.borrarfrente(indice);
	}

	/**
	 * Obtener los elementos del modelo de datos
	 * 
	 * @return lista de elementos en el modelo de datos
	 */
	public LinkedList<Frente> getListaDatos() {
		return modelo.getListaDatos();
	}

	/**
	 * Asingación de la conexión y métodos para la base de datos
	 * 
	 * @param con
	 *            Metodos --- acceso a la base de datos
	 */
	public void meterconexion(Metodos con) {
		modelo.conexion(con);
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