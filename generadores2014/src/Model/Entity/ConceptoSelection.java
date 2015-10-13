package Model.Entity;

import java.util.LinkedList;

import ObjetosSerializables.Concepto;

/**
 * clase para crear conceptos con un atributo extra 
 * @author Luis Angel Hernández Lázaro
 *
 */
public class ConceptoSelection extends Concepto{

	//elementos de la clase ... se agrega el atributo select|
	private boolean select;
	private LinkedList<AspectoSelection> subsAspectos;

	/**
	 * método para agregar un desglose a la lista de desgloses del concepto
	 * @param aspecto
	 */
	public void addSubsAspecto(AspectoSelection aspecto) {
		subsAspectos.add(aspecto);
	}
	
	/**
	 * recuperación del aspecto por el indice
	 * @param index - indice (posicion) del aspecto en la lista
	 * @return
	 */
	public AspectoSelection getSubAspecto(int index) {
		return subsAspectos.get(index);
	}
	
	
	/**
	 * recuperación de la lista de desgloses de conceptos
	 * @return the subsAspectos
	 */
	public LinkedList<AspectoSelection> getSubsAspectos() {
		return subsAspectos;
	}

	/**
	 * constructor
	 */
	public ConceptoSelection(boolean select, int idconcepto, int idpartida, String nombre) {
		super(idconcepto, idpartida, nombre);
		this.select = select;
		subsAspectos = new  LinkedList<AspectoSelection>();
	}
	
	/**
	 * recuperación del atributo select 
	 * @return valor del select del concepto
	 */
	public boolean isSelect() {
		return select;
	}

	/**
	 * asignación del atributo select del concepto
	 * @param select .valor para guardar
	 */
	public void setSelect(boolean select) {
		this.select = select;
	}
	
	//constructor
	public ConceptoSelection () {
		subsAspectos = new LinkedList<AspectoSelection>();
	}

	
}
