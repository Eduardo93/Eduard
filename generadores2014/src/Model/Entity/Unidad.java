package Model.Entity;

/**
 * Clase para representar la unidad de medida
 * 
 * @author luiiis
 *
 */
public class Unidad implements java.io.Serializable {

	/**
	 * elementos de la undiad
	 */
	private static final long serialVersionUID = 1L;
	private Integer idUnidad;
	private String nombre;
	private Boolean largo;
	private Boolean ancho;
	private Boolean alto;

	// constructor
	public Unidad() {
	}

	// constructor
	public Unidad(String nombre, Boolean largo) {
		this.nombre = nombre.replace("\\", "\\\\").replace("\"", "\\\"").replace("'", "\\'");
		this.largo = largo;
	}

	// constructor
	public Unidad(String nombre, Boolean largo, Boolean ancho, Boolean alto) {
		this.nombre = nombre.replace("\\", "\\\\").replace("\"", "\\\"").replace("'", "\\'");
		this.largo = largo;
		this.ancho = ancho;
		this.alto = alto;
	}

	// recuperacion del id de la unidad
	public Integer getIdUnidad() {
		return this.idUnidad;
	}

	// asignación del id de la unidad
	public void setIdUnidad(Integer idUnidad) {
		this.idUnidad = idUnidad;
	}

	// recuperación del nombre de la unidad
	public String getNombre() {
		return this.nombre;
	}

	// asignación del nombre de la unidad
	public void setNombre(String nombre) {
		this.nombre = nombre.replace("\\", "\\\\").replace("\"", "\\\"").replace("'", "\\'") ;
	}

	// recuperación de la propiedad largo de la unidad
	public Boolean isLargo() {
		return this.largo;
	}

	// asignación del atributo largo de la unidad
	public void setLargo(Boolean largo) {
		this.largo = largo;
	}

	// recuperación del atributo ancho de la unidad
	public Boolean isAncho() {
		return this.ancho;
	}

	// asignación del atributo ancho de la unidad
	public void setAncho(Boolean ancho) {
		this.ancho = ancho;
	}

	// recuperación del atributo alto de la unidad
	public Boolean isAlto() {
		return this.alto;
	}

	// asignación del alto de la unidad
	public void setAlto(Boolean alto) {
		this.alto = alto;
	}

}
