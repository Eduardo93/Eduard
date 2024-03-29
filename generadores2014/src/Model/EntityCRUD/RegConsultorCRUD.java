package Model.EntityCRUD;

import java.net.ConnectException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import org.apache.log4j.Logger;

import Model.Config.SQL;

import Model.EntityCRUD.Exceptions.AttributeIsEmpty;
import Model.EntityCRUD.Exceptions.ErrorConnectionDB;
import Model.EntityCRUD.Exceptions.MaxLength;
import Model.EntityCRUD.Exceptions.Unidad.NoSelectAttributeUnidad;
import ObjetosSerializables.Consultor;


/**
 * clase para las operaciones a la base de datos de los consultores
 * @author JosEduardo Hern�ndez Tapia
 *
 */
public class RegConsultorCRUD {
	// variable estatica para el l�mite de los caracteres en el nombre del
	// consultor
	private static final int maxLengthConsultor = 35;
	private static final Logger log = Logger.getLogger(UnidadCRUD.class);
	
	/**
	 * m�todo para recuperar la informaci�nd e todos los consultores en la base de datos....
	 * s�lo se recuperan id, nombre, ap paterno, ap materno ... por que s�lo son necesarios en las nuevas actualizaciones, si m�s adelante se agregan los dem�s atributos del consultor no existe problema.
	 * @return
	 * @throws SQLException
	 */
	public static LinkedList<Consultor> findConsultors () throws SQLException{
		LinkedList<Consultor> listconConsultors = new LinkedList<Consultor>();
		String query="select idConsultor, paterno, materno, nombre from consultor;";
		ResultSet result = SQL.query(query);
		while(result!= null && result.next()) {
			Consultor consultor = new Consultor();
			consultor.setIdconsultor(result.getInt("idConsultor"));
			consultor.setNombre(result.getString("nombre"));
			consultor.setPaterno(result.getString("paterno"));
			consultor.setMaterno(result.getString("materno"));
			consultor.setLogin(result.getString("login"));
			consultor.setPassword(result.getString("password"));
			consultor.setTipousu(result.getString("tipousu"));
			listconConsultors.add(consultor);
		}
		SQL.close();
		return listconConsultors;
	}
	
	
	
	/**
	 * M�todo para agregar los consultores
	 * 
	 * @param nameConsultor
	 *            --- nombre del nuevo consultor
	 * @return insert con �xito ? true : false
	 * @throws AttributeIsEmpty
	 * @throws MaxLength
	 * @throws NoSelectAttributeUnidad
	 * @throws ConnectException 
	 * @throws Exception
	 */
	public static int save(Consultor consultor) throws SQLException, NoSelectAttributeUnidad, MaxLength, AttributeIsEmpty {
		validateConsultor(consultor);
		int idConsultor = 0;
			// escape de caracteres en el nombre de la unidad
			String query = "SELECT `agregar_consultor`('" + consultor.getPaterno() + "', " + consultor.getMaterno() + ", " + consultor.getNombre() + ", " + consultor.getlogin() + ", " + consultor.getPassword() + ", " + consultor.getTipousu() + ");";
			ResultSet result = SQL.query(query);
			while (result != null && result.next()) {
				idConsultor = result.getInt(1);
			}
			SQL.close();
		return idConsultor;

	}
	
	//QUITAR ESTE MEOTOD AGREGAR
	/**
	 * m�todo para insertar un consultor en la base de datos....
	 * se insertan los atributos id, nombre, ap paterno, ap materno, nombre, login, pass y tipousu
	 * @return
	 * @throws SQLException
	 */
	public static boolean agregarConsultor(Consultor relation) throws SQLException{
		String query="INSERT INTO `consultor` (`paterno`, `materno`, `nombre`, `login`, `pass`, `tipousu`) VALUES ("+relation.getPaterno()+", "+relation.getMaterno()+", "+relation.getNombre()+", "+relation.getlogin()+", "+relation.getPassword()+", "+relation.getTipousu()+");";
		boolean result = SQL.execute(query);
		
		SQL.close();
		return result;
	}
	
	
	
	
	
	
	/**
	 * 
	 * @param nameConsultor
	 * @return
	 * @throws ConnectException 
	 */
	public static int delete(Consultor consultor) throws SQLException {
		String query = "DELETE FROM `consultor` WHERE  `id_consultor`=" + consultor.getIdconsultor() + ";";
		int resutl = SQL.update(query);
		SQL.close();
		return resutl;
	}
	
	//QUITAR ESTE METOO ELIMINAR
	/**
	 * m�todo para eliminar un consultor en la base de datos....
	 * se insertan los atributos id, nombre, ap paterno, ap materno, nombre, login, pass y tipousu
	 * @return
	 * @throws SQLException
	 */
	public static int deleteConsultor(Consultor relation) throws SQLException{
		String query="DELETE FROM `consultor` WHERE `idConsultor`=" + relation.getIdconsultor() + ";";
		int resutl = SQL.update(query);
		SQL.close();
		return resutl;
	}
	
	
	
	
	
	/**
	 * M�todo para actualizar el nombre de la unidad
	 * 
	 * @param nameUnitNew
	 *            --- nuevo nombre de la unidad
	 * @param nameUnitOld
	 *            --- nombre actual en la base de datos
	 * @return update con �xito ? true : false
	 * @throws AttributeIsEmpty
	 * @throws MaxLength
	 * @throws NoSelectAttributeUnidad
	 * @throws ConnectException 
	 */
	public static int update(Consultor consultor) throws SQLException, NoSelectAttributeUnidad, MaxLength, AttributeIsEmpty {

		validateConsultor(consultor);
		int result = 0;
		try {
			String query = "UPDATE `consultor` SET `paterno`='" + consultor.getPaterno() + "', `materno`=" + consultor.getMaterno() + ", `nombre`=" + consultor.getNombre() + ", `login`=" + consultor.getlogin() + ", `pass`=" + consultor.getPassword() + ", `tipousu`=" + consultor.getTipousu() + " WHERE  `idConsultor`=" + consultor.getIdconsultor() + ";";
			result = SQL.update(query);
			SQL.close();
		} catch (SQLException eSQL) {
			ErrorConnectionDB eConnectionDB = new ErrorConnectionDB();
			log.error(eConnectionDB+"crud", eSQL);
			throw eConnectionDB;
		}
		return result;
	}	
	
	
	//QUITAR ESTE METODO ACTUALIZAR
	/**
	 * m�todo para actualizar un consultor en la base de datos....
	 * se insertan los atributos id, nombre, ap paterno, ap materno, nombre, login, pass y tipousu
	 * @return
	 * @throws SQLException
	 */
	public static int updateConsultor(Consultor relation) throws SQLException{
	int result=0;
	try{
		String query="UPDATE `consultor` SET `paterno`=" + relation.getPaterno() + "', `materno`=" + relation.getMaterno() + ", `nombre`=" + relation.getNombre() + ", `login`=" + relation.getlogin() + ",  `pass`=" + relation.getPassword() + ",  `tipousu`=" + relation.getTipousu() + " WHERE  `idConsultor`=" + relation.getIdconsultor() +";";
		result = SQL.update(query);
		SQL.close();
	} catch (SQLException eSQL) {
		ErrorConnectionDB eConnectionDB = new ErrorConnectionDB();
		log.error(eConnectionDB+"crud", eSQL);
		throw eConnectionDB;
	}
	return result;
	}
	
	
	
	/**
	 * 
	 * @param consultor
	 * @throws Exception
	 */
	public static void validateConsultor(Consultor consultor) throws NoSelectAttributeUnidad, MaxLength, AttributeIsEmpty {
		// verificaci�n del n�mero de caracteres ingresados en el nombre del
		// consultor
		if (consultor.getNombre().length() > maxLengthConsultor) {//
			MaxLength e = new MaxLength("Nombre de Consultor");
			log.warn(e);
			throw e;
		}
		//
		if (consultor.getNombre().trim().isEmpty()) {
			AttributeIsEmpty e = new AttributeIsEmpty("Nombre de Consultor");
			log.warn(e);
			throw e;
		}
	}

}
