package ObjetosSerializables;
/**
 * 
 * @author luiiis
 *
 */
public class Aspecto {

	public Aspecto() {
		super();
		// TODO Auto-generated constructor stub
	}

	private int idaspecto;
	private int idconcepto;
	private String clave;
	private String unidad;
	private String tipo;
	private String costo;
	private String descripcion;
	private String clave_privada;
	private String descripcion_Completa;

	/**
	 * 
	 * @return
	 */
	public String getDescripcion_Completa() {
		return descripcion_Completa;
	}

	public Aspecto(int idaspecto, int idconcepto, String clave, String unidad, String tipo, String costo, String descripcion, String clave_publica, String descripcion_Completa) {
		super();
		this.idaspecto = idaspecto;
		this.idconcepto = idconcepto;
		this.clave = clave;
		this.unidad = unidad;
		this.tipo = tipo;
		this.costo = costo;
		this.descripcion = descripcion;
		this.clave_privada = clave_publica;
		this.descripcion_Completa = descripcion_Completa;
	}

	/**
	 * 
	 * @param descripcion_Completa
	 */
	public void setDescripcion_Completa(String descripcion_Completa) {
		this.descripcion_Completa = descripcion_Completa;
	}

	/**
	 * 
	 * @return
	 */
	public String getClave_privada() {
		return clave_privada;
	}

	/**
	 * 
	 * @param clave_publica
	 */
	public void setClave_privada(String clave_publica) {
		this.clave_privada = clave_publica;
	}

	/**
	 * 
	 * @param idaspecto
	 */
	public void setIdaspecto(int idaspecto) {
		this.idaspecto = idaspecto;
	}

	/**
	 * 
	 * @return
	 */
	public int getIdaspecto() {
		return this.idaspecto;
	}

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

	/**
	 * 
	 * @param clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * 
	 * @return
	 */
	public String getClave() {
		return this.clave;
	}

	/**
	 * 
	 * @param unidad
	 */
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	/**
	 * 
	 * @return
	 */
	public String getUnidad() {
		return this.unidad;
	}

	/**
	 * 
	 * @param tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * 
	 * @return
	 */
	public String getTipo() {
		return this.tipo;
	}

	/**
	 * 
	 * @param costo
	 */
	public void setCosto(String costo) {
		this.costo = costo;
	}

	/**
	 * 
	 * @return
	 */
	public String getCosto() {
		return this.costo;
	}

	/**
	 * 
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * 
	 * @return
	 */
	public String getDescripcion() {
		return this.descripcion;
	}
}
