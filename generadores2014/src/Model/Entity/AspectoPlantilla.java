package Model.Entity;

/**
 * clase para representar la relación entre aspectos (desgloses) y plantillas
 * @author usuariofei
 *
 */
public class AspectoPlantilla {
	
	//elementos de la clase 
	private int idPlantilla;
	private int idAspecto;
	
	//constructor 
	public AspectoPlantilla () {
		
	}
	
	//constructor con parametros para crear la relacion
	public AspectoPlantilla(int idPlantilla, int idAspecto) {
		super();
		this.idPlantilla = idPlantilla;
		this.idAspecto = idAspecto;
	}
	
	/**
	 * recuperar el id de la plantilla
	 * @return idPlantilla
	 */
	public final int getIdPlantilla() {
		return idPlantilla;
	}
	/**
	 * asignar el id de la plantilla
	 * @param idPlantilla the idPlantilla to set
	 */
	public final void setIdPlantilla(int idPlantilla) {
		this.idPlantilla = idPlantilla;
	}
	/**
	 * recuperar el id del aspecto
	 * @return the idAspecto
	 */
	public final int getIdAspecto() {
		return idAspecto;
	}
	
	/**
	 * asignación del id del aspectos
	 * @param idAspecto the idAspecto to set
	 */
	public final void setIdAspecto(int idAspecto) {
		this.idAspecto = idAspecto;
	}
	
	

}
