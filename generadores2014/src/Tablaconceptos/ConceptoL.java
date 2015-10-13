package Tablaconceptos;

/**
 * Clase para la manipulación de los conceptos
 */
public class ConceptoL {

	private String idconcepto;
	private String idpartida;
	private String nombre;

	/**
	 * 
	 * @param idconcepto
	 */
	public void setIdconcepto(String idconcepto) {
		this.idconcepto = idconcepto;
	}

	/**
	 * 
	 * @return
	 */
	public String getIdconcepto() {
		return this.idconcepto;
	}

	/**
	 * 
	 * @param idpartida
	 */
	public void setIdpartida(String idpartida) {
		this.idpartida = idpartida;
	}

	/**
	 * 
	 * @return
	 */
	public String getIdpartida() {
		return this.idpartida;
	}

	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * 
	 * @return
	 */
	public String getNombre() {
		return this.nombre;
	}
}
