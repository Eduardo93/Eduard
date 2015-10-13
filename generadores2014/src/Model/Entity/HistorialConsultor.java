package Model.Entity;

import java.sql.Timestamp;

import ObjetosSerializables.Consultor;
import ObjetosSerializables.Proyecto;

/**
 * Clase para la representación de historiales de consultor
 * @author Luis Angel Hernández Lázaro
 *
 */
public class HistorialConsultor {
	
	/**
	 * elementos de para crear el historial
	 */
	private int idHistorial;
	private Proyecto proyecto;
	private Consultor idConsultorAnterior;
	private Consultor idConsultorNuevo;
	private Timestamp fechaRegistro;
	
	/**
	 * recuperar la fecha de creación del historial
	 * @return fechaRegistro -- fecha de registro del historial 
	 */
	public Timestamp getFechaRegistro() {
		return fechaRegistro;
	}
	
	/**
	 * asignación de la fecha de registro
	 * @param fechaRegistro - fecha de registro del historial
	 */
	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	/**
	 * recuperación del identificador del historial
	 * @return the idHistorial
	 */
	public int getIdHistorial() {
		return idHistorial;
	}
	
	/**
	 * asignación del historial del consultor
	 * @param idHistorial the idHistorial to set
	 */
	public void setIdHistorial(int idHistorial) {
		this.idHistorial = idHistorial;
	}
	
	/**
	 * recuperación del proyecto del historial
	 * @return the proyecto
	 */
	public Proyecto getProyecto() {
		return proyecto;
	}
	
	/**
	 * asignación del proyecto del historial
	 * @param proyecto 
	 */
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	
	/**
	 * recuperación del identificador del consultor anterior
	 * @return the idConsultorAnterior
	 */
	public Consultor getIdConsultorAnterior() {
		return idConsultorAnterior;
	}
	
	/**
	 * asignación del identificador del consultor anterior
	 * @param idConsultorAnterior the idConsultorAnterior to set
	 */
	public void setIdConsultorAnterior(Consultor idConsultorAnterior) {
		this.idConsultorAnterior = idConsultorAnterior;
	}
	
	/**
	 * recuperación del nuevo consultor
	 * @return the idConsultorNUevo
	 */
	public Consultor getIdConsultorNuevo() {
		return idConsultorNuevo;
	}
	
	/**
	 * asignación del nuevo consultor
	 * @param idConsultorNUevo the idConsultorNUevo to set
	 */
	public void setIdConsultorNuevo(Consultor idConsultorNUevo) {
		this.idConsultorNuevo = idConsultorNUevo;
	}
}
