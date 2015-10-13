package ObjetosSerializables;

/**
 * 
 * @author luiiis
 *
 */
public class Frente {

	private int idfrente;
	private int idproyecto;
	private String identificador;
	private String ubicacion;

	/**
	 * 
	 * @param idfrente
	 */
	public void setIdfrente(int idfrente) {
		this.idfrente = idfrente;
	}

	/**
	 * 
	 * @return
	 */
	public int getIdfrente() {
		return this.idfrente;
	}

	/**
	 * 
	 * @param idproyecto
	 */
	public void setIdproyecto(int idproyecto) {
		this.idproyecto = idproyecto;
	}

	/**
	 * 
	 * @return
	 */
	public int getIdproyecto() {
		return this.idproyecto;
	}

	/**
	 * 
	 * @param identificador
	 */
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	/**
	 * 
	 * @return
	 */
	public String getIndentificador() {
		return this.identificador;
	}

	/**
	 * 
	 * @param ubicacion
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * 
	 * @return
	 */
	public String getUbicacion() {
		return this.ubicacion;
	}
}