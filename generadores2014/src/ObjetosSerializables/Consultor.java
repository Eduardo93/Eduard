package ObjetosSerializables;
/**
 * 
 * @author luiiis
 *
 */
public class Consultor {

	private int idConsultor;
	private String paterno;
	private String materno;
	private String nombre;
	private String login;
	private String pass;
	private String tipousu;


	/**
	 * 
	 * @param idConsultor
	 */
	public void setIdconsultor(int idConsultor) {
		this.idConsultor = idConsultor;
	}

	/**
	 * 
	 * @return
	 */
	public int getIdconsultor() {
		return this.idConsultor;
	}

	/**
	 * 
	 * @param paterno
	 */
	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	/**
	 * 
	 * @return
	 */
	public String getPaterno() {
		return this.paterno;
	}

	/**
	 * 
	 * @param materno
	 */
	public void setMaterno(String materno) {
		this.materno = materno;
	}

	/**
	 * 
	 * @return
	 */
	public String getMaterno() {
		return this.materno;
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

	/**
	 * 
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * 
	 * @return
	 */
	public String getlogin() {
		return this.login;
	}

	/**
	 * 
	 * @param pass
	 */
	public void setPassword(String pass) {
		this.pass = pass;
	}

	/**
	 * 
	 * @return
	 */
	public String getPassword() {
		return this.pass;
	}

	/**
	 * 
	 * @param tipousu
	 */
	public void setTipousu(String tipousu) {
		this.tipousu = tipousu;
	}

	/**
	 * 
	 * @return
	 */
	public String getTipousu() {
		return this.tipousu;
	}
	
	public String toString() {
		return this.nombre+" "+this.paterno+" "+this.materno;
	}
}
