package Manejotablas;

import java.util.LinkedList;

import MetodosRemotos.Metodos;
import ObjetosSerializables.Aspecto;

/**
 * 
 * @author Pablo Rivera
 *
 */
public class ControlTabla {

	private ModeloTabla modelo = new ModeloTabla();

	/**
	 * constructor de la clase, asignacion del modelo de datos.
	 * @param modelo
	 */
	public ControlTabla() {
		this.modelo = new ModeloTabla();
	}

	/**
	 * Agregar un elemento al modelo de datos. 
	 * @param asp
	 */
	public void anhadeFila(Aspecto asp) {
		modelo.anhadeAspecto(asp);
	}

	/**
	 * borrar un elemento del modelo de datos
	 * @param indice
	 */
	public void borraFila(int indice) {
		if (modelo.getRowCount() > 0)
			modelo.borraAspecto(indice);
	}

	/**
	 * obtener los elementos del modelo de datos
	 * @return linkedList<Aspectodos> //lista de elementos en el modelo
	 */
	public LinkedList<Aspecto> getListaDatos() {
		return modelo.getListaDatos();
	}

	/**
	 * Ingresar indice para la lista
	 * @param indice
	 */
	public void meterindice(int indice) {
		modelo.meterindice(indice);
	}

	/**
	 * Establecer conexión a la base de datos para el modelo de datos
	 * @param c
	 */
	public void conexion(Metodos c) {
		modelo.conexion(c);
	}

	/**
	 * Obtener el número de elementos incluidos en el modelo de datos 
	 * @return
	 */
	public int Tama() {
		return modelo.getRowCount();
	}

	/**
	 * Reset del modelo de datos
	 */
	public void Limpiar() {
		modelo.Limpiar();
	};
	
	public ModeloTabla getModel() {
		return modelo;
	}
}