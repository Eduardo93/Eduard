package ObjetosSerializables;
/**
 * 
 * @author luiiis
 *
 */
public class Concepto {

	private int idconcepto;
	private int idpartida;
	private String nombre;

	/**
	 * 
	 * @param idconcepto
	 */
	public void setIdconcepto(int idconcepto) {
		this.idconcepto = idconcepto;
	}

	/**
	 * 
	 * @return
	 */
	public int getIdconcepto() {
		return this.idconcepto;
	}

	public Concepto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Concepto(int idconcepto, int idpartida, String nombre) {
		super();
		this.idconcepto = idconcepto;
		this.idpartida = idpartida;
		this.nombre = nombre;
	}

	/**
	 * 
	 * @param idpartida
	 */
	public void setIdpartida(int idpartida) {
		this.idpartida = idpartida;
	}

	/**
	 * 
	 * @return
	 */
	public int getIdpartida() {
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
