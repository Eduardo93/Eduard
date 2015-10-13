package ObjetosSerializables;

import java.sql.Date;

/**
 * 
 * @author luiiis
 *
 */
public class Proyecto {

	private int idproyecto;
	private int idtipo;
	private String proyecto;
	private String descripcion;
	private Date inicio;
	private Date fin;
	private String comentarios;

	/**
	 * @return the comentarios
	 */
	public String getComentarios() {
		return comentarios;
	}

	/**
	 * @param comentarios
	 *            the comentarios to set
	 */
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
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
	 * @param idtipo
	 */
	public void setIdtipo(int idtipo) {
		this.idtipo = idtipo;
	}

	/**
	 * 
	 * @return
	 */
	public int getIdtipo() {
		return this.idtipo;
	}

	/**
	 * 
	 * @param proyecto
	 */
	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	/**
	 * 
	 * @return
	 */
	public String getProyecto() {
		return this.proyecto;
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

	/**
	 * 
	 * @param inicio
	 */
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	/**
	 * 
	 * @return
	 */
	public Date getInicio() {
		return this.inicio;
	}

	/**
	 * 
	 * @param fin
	 */
	public void setFin(Date fin) {
		this.fin = fin;
	}

	/**
	 * 
	 * @return
	 */
	public Date getFin() {
		return this.fin;
	}
	
	public String toString() {
		return this.proyecto;
	}
}
