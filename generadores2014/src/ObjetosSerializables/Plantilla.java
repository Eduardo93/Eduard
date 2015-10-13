package ObjetosSerializables;

public class Plantilla {

	String idplantilla;
	String nombre;
	String fecha;
	
	public Plantilla () {
		
	}
	public Plantilla (String nombre) {
		this.nombre= nombre;
	}
	
	public Plantilla(String idplantilla, String nombre, String fecha) {
		super();
		this.idplantilla = idplantilla;
		this.nombre = nombre;
		this.fecha = fecha;
	}

	/**
	 * 
	 * @param idplantilla
	 */
	public void setIdplantilla(String idplantilla) {
		this.idplantilla = idplantilla;
	}

	/**
	 * 
	 * @return
	 */
	public String getIdplantilla() {
		return this.idplantilla;
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
	 * @param fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * 
	 * @return
	 */
	public String getFecha() {
		return this.fecha;
	}
}
