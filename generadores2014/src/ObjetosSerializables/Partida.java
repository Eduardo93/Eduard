package ObjetosSerializables;
/**
 * 
 * @author luiiis
 *
 */
public class Partida {

	public Partida(int idpartida, String nombre) {
		super();
		this.idpartida = idpartida;
		this.nombre = nombre;
	}

	public Partida() {
		super();
		// TODO Auto-generated constructor stub
	}

	private int idpartida;
	private String nombre;

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
