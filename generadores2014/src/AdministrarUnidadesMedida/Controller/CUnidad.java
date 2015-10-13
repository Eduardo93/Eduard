/**
 * 
 */
package AdministrarUnidadesMedida.Controller;

import java.sql.SQLException;
import java.util.LinkedList;

import Model.Entity.Unidad;
import Model.EntityCRUD.UnidadCRUD;
import Model.EntityCRUD.Exceptions.AttributeIsEmpty;
import Model.EntityCRUD.Exceptions.MaxLength;
import Model.EntityCRUD.Exceptions.Unidad.NoSelectAttributeUnidad;

/**
 * Clase para realizar las llamadas al modelo de la unidad
 * @author Luis Angel Hernández Lázaro
 */
public class CUnidad {
	
	/**
	 * Guardar una unidad en la base de datos
	 * @param unidad - objeto a guardar
	 * @return - int = id_unidad agregada
	 * @throws SQLException
	 * @throws AttributeIsEmpty 
	 * @throws MaxLength 
	 * @throws NoSelectAttributeUnidad 
	 */
	public static int save(Unidad unidad) throws SQLException, NoSelectAttributeUnidad, MaxLength, AttributeIsEmpty{
		return UnidadCRUD.save(unidad);
	}
	
	/**
	 * Actualizar una unidad en la base de datos
	 * @param unidad - objeto a actualizar
	 * @return - int = verificación de la unidad actualizada 
	 * @throws SQLException
	 * @throws AttributeIsEmpty 
	 * @throws MaxLength 
	 * @throws NoSelectAttributeUnidad 
	 */
	public static int update(Unidad unidad) throws SQLException, NoSelectAttributeUnidad, MaxLength, AttributeIsEmpty {
		return UnidadCRUD.update(unidad);
	}
	
	/**
	 * Eliminar una unidad de la base de datos
	 * @param unidad - objeto a eliminar 
	 * @return - int = verificacion de la unidad eliminada
	 * @throws SQLException
	 */
	public static int delete(Unidad unidad) throws SQLException {
		return UnidadCRUD.delete(unidad);
	}
	
	/**
	 * Recuperación de las unidades en la base de datos
	 * @return
	 * @throws SQLException
	 */
	public static LinkedList<Unidad> findUnidades() throws SQLException {
		return UnidadCRUD.findUnidades();
	}

}
