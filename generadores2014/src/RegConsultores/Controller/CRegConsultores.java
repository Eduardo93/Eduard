package RegConsultores.Controller;

import java.sql.SQLException;
import java.util.LinkedList;
import Model.EntityCRUD.RegConsultorCRUD;
import Model.EntityCRUD.Exceptions.AttributeIsEmpty;
import Model.EntityCRUD.Exceptions.MaxLength;
import Model.EntityCRUD.Exceptions.Unidad.NoSelectAttributeUnidad;
import ObjetosSerializables.Consultor;

/**
 * Clase para realizar las llamadas al modelo del catalogo de los consultores
 * @author José Eduardo Hernández Tapia
 *
 */
public class CRegConsultores {
	
	/**
	 * Guardar un consultor en la base de datos
	 * @return - int = id_consultor agregado
	 * @throws SQLException
	 * @throws AttributeIsEmpty 
	 * @throws MaxLength 
	 * @throws NoSelectAttributeUnidad 
	 */
	public static int save(Consultor consultor) throws SQLException, NoSelectAttributeUnidad, MaxLength, AttributeIsEmpty{
		return RegConsultorCRUD.save(consultor);
	}
	
	/**
	 * Actualizar un consultor en la base de datos
	 * @param consultor - objeto a actualizar
	 * @return - int = verificación del consultor actualizado 
	 * @throws SQLException
	 * @throws AttributeIsEmpty 
	 * @throws MaxLength 
	 * @throws NoSelectAttributeUnidad 
	 */
	//public static int update(Consultor consultor) throws SQLException, NoSelectAttributeConsultor, MaxLength, AttributeIsEmpty {
	//	return RegConsultorCRUD.update(consultor);
	//}
	
	/**
	 * Eliminar un consultor de la base de datos
	 * @param consultor - objeto a eliminar 
	 * @return - int = verificacion del consultor eliminado
	 * @throws SQLException
	 */
	public static int delete(Consultor consultor) throws SQLException {
		return RegConsultorCRUD.delete(consultor);
	}
	
	/**
	 * Recuperación de los consultores en la base de datos
	 * @return
	 * @throws SQLException
	 */
	public static LinkedList<Consultor> findConsultors() throws SQLException {
		return RegConsultorCRUD.findConsultors();
	}

}