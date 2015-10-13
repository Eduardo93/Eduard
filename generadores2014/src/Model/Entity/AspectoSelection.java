package Model.Entity;

import ObjetosSerializables.Aspecto;

/**
 * clase para crear desgloses de concepto con u atributo de seleccion 
 * @author Luis Angel Hernández Lázaro
 *
 */
public class AspectoSelection extends Aspecto{
	//nuevo atributo
	private boolean select;
	
	//contructor 
	public AspectoSelection(boolean select, int idaspecto, int idconcepto, String clave, String unidad, String tipo, String costo, String descripcion, String clave_privada, String descripcion_Completa) {
		super(idaspecto, idconcepto, clave, unidad, tipo, costo, descripcion, clave_privada, descripcion_Completa);
		this.select = select;
	}

	/**
	 * recuperacion del atributo select
	 * @return
	 */
	public boolean isSelect() {
		return select;
	}

	/**
	 * asignación del atributo select
	 * @param select
	 */
	public void setSelect(boolean select) {
		this.select = select;
	}
	
	//constructor
	public AspectoSelection() {
		
	}
}
