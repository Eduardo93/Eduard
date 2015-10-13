package Model.Entity;

import java.util.LinkedList;

import ObjetosSerializables.Partida;

/**
 * Clase para las partidas de seleccion
 * @author Luis Angel Hernández Lázaro
 *
 */
public class PartidaSelection extends Partida{

	//elementos de la clase, agregando el atributo select
	private boolean select;
	private LinkedList<ConceptoSelection> subsConceptos;
	
	/**
	 * constructor
	 */
	public PartidaSelection() {
		subsConceptos= new LinkedList<ConceptoSelection>();
	}
	
	/**
	 * agregar un concepto a la lista de conceptos de la partida
	 * @param concepto
	 */
	public void addSubsConceptos(ConceptoSelection concepto) {
		subsConceptos.add(concepto);
	}
	
	public ConceptoSelection getSubConcepto(int index) {
		return subsConceptos.get(index);
	}
	
	/**
	 * obtener la lista de conceptos de la partida 
	 * @return the subsConceptos
	 */
	public LinkedList<ConceptoSelection> getSubsConceptos() {
		return subsConceptos;
	}

	/**
	 * constructor
	 */
	public PartidaSelection(boolean select, int idpartida, String nombre) {
		super(idpartida, nombre);
		this.select = select;
		this.subsConceptos=new LinkedList<ConceptoSelection>();
	}
	
	/**
	 * obtener el valor del atributo select
	 * @return the select
	 */
	public boolean isSelect() {
		return select;
	}

	/**
	 * recuperación del atributo select
	 * @param select the select to set
	 */
	public void setSelect(boolean select) {
		this.select = select;
	}	
	
}
