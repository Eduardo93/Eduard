package MetodosRemotos;

import java.sql.*;
import java.util.*;

import ObjetosSerializables.Rgenerador;
import ObjetosSerializables.Consultor;
import ObjetosSerializables.Estimacion;
import ObjetosSerializables.Partida;
import ObjetosSerializables.Proyecto;
import ObjetosSerializables.Frente;
import ObjetosSerializables.Concepto;
import ObjetosSerializables.Aspecto;
import ObjetosSerializables.Rutas;
import ObjetosSerializables.Tipo;
import ObjetosSerializables.Plantilla;
import Options.OptionsText;

/**
 * Clase para la implementar métodos para la manipulación de la base de datos
 * 
 * @author
 * 
 */

public class Metodos {

	private Statement instancia_sql;
	private Connection conexion;
	private String direccion;
	private static String connectionStrinsg;

	
	
	/**
	 * @return the connectionStrinsg
	 */
	public static final String getConnectionStrinsg() {
		return connectionStrinsg;
	}
	/**
	 * @param connectionStrinsg the connectionStrinsg to set
	 */
	public final void setConnectionStrinsg(String connectionStrinsg) {
		Metodos.connectionStrinsg = connectionStrinsg;
	}
	/**
	 * Método que asigna la dirección del servidor
	 * 
	 * @param direccion
	 */
	public void setDirecccion(String direccion) {
		this.direccion = direccion;
	}
	public String getDireccion() {
		return direccion;
	}
	
	/**
	 * Conectar --- Método que crea la conexión con la base de datos.
	 * 
	 * @param usuario
	 *            String --- nombre del usuario para ingresar al sistema
	 * @param password
	 *            char[] --- arreglo de caracteres que contienen
	 *            JPasswordField.getPassword
	 * @return conexión exitosa ? true : false
	 */
	public boolean Conectar(String usuario, char[] password) {
		try {
			String passMYSQL = new String(password); // conversión de
														// contraseña
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			this.setConnectionStrinsg("jdbc:mysql://" + direccion + "/generadores?user=" + usuario + "&password=" + passMYSQL );
			conexion = DriverManager.getConnection(Metodos.getConnectionStrinsg());
			if (conexion != null) {
				instancia_sql = this.conexion.createStatement();				
			}
			return true;
		} catch (java.sql.SQLException e) {// error al conectar con el
											// servidor
			System.out.println(e.toString());
			return false;
		}
	}

	/**
	 * Método que cierra la conexión con la base de datos
	 */
	public void cerrar() {
		try {
			this.conexion.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	/**
	 * APARTADO DE MÉTODOS PARA CONSULTOR 
	 * ADD,GET,UPDATE,DELETE
	 * 	
	 */
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Método que inserta un nuevo usuario en la base de datos
	 * 
	 * @param paterno
	 *            String --- apellido paterno del usuario
	 * @param materno
	 *            String --- apellido materno del usuario
	 * @param nombre
	 *            String --- nombre del usuario
	 * @param login
	 *            String --- login (nickname) del usuario
	 * @param pass
	 *            String --- contraseÃ±a del usuario
	 * @param tipousu
	 *            String --- tipo de usuario
	 * @return insert con éxito ? true : false
	 */
	public boolean agregarUsuario(String paterno, String materno, String nombre, String login, char[] pass, String tipousu) {
		boolean ban = false;
		String passUser = new String(pass);
		String consulta = "INSERT INTO consultor " + " SET pass='" + passUser + "', " + " paterno='" + paterno + "'," + " materno='" + materno + "'," + " nombre='" + nombre + "', " + "login='" + login + "', " + "tipousu='" + tipousu + "'";
		try {
			ConsultaConsola(consulta);
			instancia_sql.execute(consulta);
			ban = true;
		} catch (Exception e) {
			ban = false;
			System.out.println(e.toString());
		}
		return ban;
	}
	
	/**
	 * Método que prueba si existe un usuario en la base de datos
	 * 
	 * @param paterno
	 *            String --- apellido parterno del usuario
	 * @param materno
	 *            String --- apellido materno del usuario
	 * @param nombre
	 *            String --- nombre del usuario
	 * @return usuario existe ? true : false
	 */
	public boolean existeUsuario(String paterno, String materno, String nombre) {
		boolean ban = false;
		String consulta = "SELECT paterno " + "FROM consultor " + "WHERE paterno = '" + paterno + "' AND materno = '" + materno + "' AND nombre = '" + nombre + "'";
		ResultSet resultado;
		String res = "";
		try {
			ConsultaConsola(consulta);
			resultado = instancia_sql.executeQuery(consulta);
			while (resultado.next()) {
				res = resultado.getString("paterno");
			}
			if (res.equals("") == false) {
				ban = true;
			}
		} catch (Exception e) {
			ban = false;
			System.out.println(e.toString());
		}
		return ban;
	}

	/**
	 * Consultores --- Método que regresa la Vector de consultores en el sistema
	 * (Usuarios)
	 * 
	 * @return consultores Vector<Consultor> --- Vector de consultores
	 *         existentes en el sistema
	 */
	public Vector<Consultor> GetConsultores() {
		Consultor consul;
		ResultSet consultores = null;
		Vector<Consultor> Lconsultores = new Vector<Consultor>();
		String consulta = " SELECT c.idConsultor, c.paterno, c.materno, c.nombre, c.login, c.pass, c.tipousu " + " FROM consultor AS c " + " ORDER BY c.nombre ASC, c.paterno ASC, c.nombre ASC ";
		try {
			ConsultaConsola(consulta);
			consultores = instancia_sql.executeQuery(consulta);
			while (consultores.next() == true) {
				consul = new Consultor();
				consul.setIdconsultor(consultores.getInt("idConsultor"));
				consul.setPaterno(consultores.getString("paterno"));
				consul.setMaterno(consultores.getString("materno"));
				consul.setNombre(consultores.getString("nombre"));
				consul.setLogin(consultores.getString("login"));
				consul.setPassword(consultores.getString("pass"));
				consul.setTipousu(consultores.getString("tipousu"));
				Lconsultores.add(consul);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return Lconsultores;
	}

	/**
	 * Método que modifica el contenido de un usuario
	 * 
	 * @param paterno
	 *            String ---nuevo apellido paterno
	 * @param materno
	 *            String --- nuevo apellido materno
	 * @param nombre
	 *            String --- nuevo nombre
	 * @param login
	 *            String --- nuevo login
	 * @param pass
	 *            String --- nueva contraseÃ±a
	 * @param tipousu
	 *            String --- nuevo tipo de usuario
	 * @param idusuario
	 *            String --- identificador del usuario a ser actualizado
	 * @return
	 */
	public boolean modificarUsuario(String paterno, String materno, String nombre, String login, char[] pass, String tipousu, String idusuario) {
		boolean ban = false;
		String passUser = new String(pass);
		String consulta = "UPDATE consultor " + "SET paterno='" + paterno + "', " + " materno='" + materno + "', " + " nombre='" + nombre + "', " + " login='" + login + "', " + " pass='" + passUser + "', " + " tipousu='" + tipousu + "'  " + " WHERE idConsultor = " + idusuario;
		try {
			ConsultaConsola(consulta);
			instancia_sql.execute(consulta);
			ban = true;
		} catch (Exception e) {
			ban = false;
			System.out.println(e.toString());
		}
		return ban;
	}

	/**
	 * Método que elimina un usuario
	 * 
	 * @param idusuario
	 *            String --- identificador del usuario a eliminar
	 * @return delete con éxito ? true : false
	 */
	public boolean eliminarUsuario(String idusuario) {
		boolean ban = false;
		String consulta = "DELETE FROM consultor  " + " WHERE idConsultor = " + idusuario;
		try {
			ConsultaConsola(consulta);
			instancia_sql.execute(consulta);
			ban = true;
		} catch (Exception e) {
			ban = false;
			System.out.println(e.toString());
		}
		return ban;
	}
	
	/**
	 * VerificarUsuario --- Método que hace la verificación de usuario a la base
	 * de datos
	 * 
	 * @param login
	 *            String --- Nombre del Usuario para ingresar al sistema
	 * @param password
	 *            char[] --- Arreglo de caracteres que contienen
	 *            JFieldPassword.getPassword
	 * @return usuario existe ? true : false
	 */
	public String verificarusuario(String login, char[] password) {
		String tusuario = "nada";
		ResultSet rusuario;
		String passUser = new String(password);
		if (login.isEmpty() || passUser.isEmpty()) {
			return tusuario;
		}
		String consulta = " SELECT login, pass, tipousu " + " FROM consultor " + " WHERE login= '" + login.toString() + "' AND pass = '" + passUser.toString() + "'";
		ConsultaConsola(consulta);
		try {
			rusuario = instancia_sql.executeQuery(consulta);
			while (rusuario.next() == true) {
				tusuario = rusuario.getString("tipousu");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			//System.out.println("Error al verificar el usuario en la base de datos");
		}
		return tusuario;
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * APARTADO DE METODOS PARA PARTIDAS
	 * ADD,GET,UPDATE,DELETE
	 * 
	 */
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Insertar Partida --- Método para agregar una nueva partida presupuestal a
	 * la base de datos.
	 * 
	 * @param partida
	 *            String --- Nombre de la nueva Partida Presupuestal
	 * @return insert con éxito ? true : false
	 */
	public boolean insertarPartida(String partida) {
		boolean ban = false;
		String consulta = " INSERT INTO partida " + " SET nombre='" + partida + "' ";
		try {
			ConsultaConsola(consulta);
			instancia_sql.execute(consulta);
			ban = true;
		} catch (Exception e) {
			System.out.println(e.toString());
			ban = false;
		}
		return ban;
	}

	/**
	 * ExistePartida --- Método que verifica si una partida ya se encuentra
	 * registrada en la base de datos
	 * 
	 * @param nombre
	 *            String --- Nombre de la partida que se quiere encontrar
	 * @return existe partida ? true : false
	 */
	public boolean existepartida(String nombre) {
		boolean ban = false;
		String consulta = " SELECT p.nombre " + " FROM partida AS p" + " WHERE p.nombre = '" + nombre + "' ";
		ResultSet resultado;
		String nom = "";
		try {
			resultado = null;
			ConsultaConsola(consulta);
			resultado = instancia_sql.executeQuery(consulta);
			// mientras encuentre coincidencias en la bï¿½squeda en la base de
			// datos,
			while (resultado.next()) {
				nom = resultado.getString("nombre");
			}
			nom = nom.trim();
			if (nom.isEmpty()) {
				ban = false;
			} else {
				ban = true;
			}
		} catch (Exception e) {
			ban = false;
			System.out.println(e.toString());
		}
		return ban;
	}

	
	/**
	 * Existe Partida id --- Método que regresa el ID de una partida si la
	 * encuentra en la base de datos
	 * 
	 * @param nombre
	 *            String --- Nombre de la partida que se quiere buscar en la
	 *            base de datos
	 * @return existe Partida ? idPartidaBD : -1
	 */
	public String existepartidaid(String nombre) {
		String id = "0";
		String consulta = " SELECT p.idpartida " + " FROM partida AS p " + " WHERE p.nombre = '" + nombre + "' ";
		ResultSet resultado;
		String nom = "";
		try {
			resultado = null;
			ConsultaConsola(consulta);
			resultado = instancia_sql.executeQuery(consulta);
			while (resultado.next()) {
				nom = resultado.getString("idpartida");
			}
			nom = nom.trim();
			if (nom.isEmpty()) {
				id = "-1";
			} else {
				id = nom;
			}
		} catch (Exception e) {
			id = "-1";
			System.out.println(e.toString());
		}
		return id;
	}

	/**
	 * Insertar Partida Ex --- Método para registrar una nueva partida
	 * presupuestal
	 * 
	 * @param partida
	 *            String --- Nombre de la nueva partida a ser registrada en la
	 *            base de datos
	 * @return insert con éxito ? idPartidaNueva : 0
	 */
	public String insertarPartidaex(String partida) {
		ResultSet res;
		String id = "0";
		// Ejecución de la función para agregar una nueva partida,
		// selecciona el ID que acaba de registrar como nuevo
		String consulta = "SELECT agregarpartida('" + partida + "');";
		try {
			ConsultaConsola(consulta);
			res = instancia_sql.executeQuery(consulta);
			while (res.next()) {
				id = res.getString(1);
			}
		} catch (Exception e) {
			id = "0";
			System.out.println(e.toString());
		}
		return id;
	}

	/**
	 * Partidas --- Método que regresa las partidas almacenadas en la base de datos
	 * 
	 * @return Lpartidas LinkedList<Partida> --- Lista de Partidas
	 */
	public LinkedList<Partida> getPartidas() {
		Partida par;
		ResultSet partidas;
		LinkedList<Partida> Lpartidas = new LinkedList<Partida>();
		String consulta = " SELECT idpartida, nombre FROM partida  ORDER BY nombre ASC";
		ConsultaConsola(consulta);
		try {
			partidas = instancia_sql.executeQuery(consulta);
			while (partidas.next() == true) {
				par = new Partida();
				par.setIdpartida(partidas.getInt("idpartida"));
				par.setNombre(partidas.getString("nombre"));
				Lpartidas.add(par);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return Lpartidas;
	}

	/**
	 * Modificar Partida --- Método para modificar la informaciï¿½n de una
	 * partida existente en la base de datos
	 * 
	 * @param nombre
	 *            String --- nuevo nombre de la partida ha ser modificado
	 * @param id
	 *            String --- Identificador de la partida que serï¿½ modificado
	 * @return update con éxito ? true : false
	 */
	public boolean modificarPartida(String nombre, String id) {
		boolean ban = false;
		String consulta = "UPDATE partida SET nombre = '" + nombre + "' " + "WHERE idpartida = " + id + ";";
		try {
			ConsultaConsola(consulta);
			instancia_sql.execute(consulta);
			ban = true;
		} catch (Exception e) {
			ban = false;
			System.out.println(e.toString());
		}
		return ban;
	}

	/**
	 * Eliminar Partida --- Método para Eliminar una partida de la base de datos
	 * 
	 * @param id
	 *            String --- Identificador de la Partida a ser Eliminado
	 * @return delete con éxito ? true : false
	 */
	public boolean eliminarPartida(String id) {
		boolean ban = false;
		String consulta = "DELETE FROM partida " + " WHERE idpartida=" + id;
		try {
			ConsultaConsola(consulta);
			instancia_sql.execute(consulta);
			ban = true;
		} catch (Exception e) {
			System.out.println(e.toString());
			ban = false;
		}
		return ban;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	/**
	 * APARTADO DE METODOS PARA CONCEPTOS
	 * ADD,GET,UPDATE,DELETE
	 * 
	 */
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * insertarConcepto --- Método
	 * 
	 * @param nombre
	 * @param id
	 * @return
	 */
	public boolean insertarconcepto(String nombre, String id) {
		boolean ban = false;
		String consulta = "INSERT INTO concepto SET idpartida = " + id + ", " + " nombre = '" + nombre + "';";
		try {
			ConsultaConsola(consulta);
			instancia_sql.execute(consulta);
			ban = true;
		} catch (Exception e) {
			ban = false;
			System.out.println(e.toString());
		}
		return ban;
	}

	/**
	 * ExisteConceptoex --- Método que verifica si un concepto existe en la base
	 * de datos
	 * 
	 * @param nombre
	 *            String --- nombre del concepto que se va a buscar
	 * @return existe concepto ? idconcepto : 0
	 */
	public String existeconceptoex(String nombre) {
		String id = "0";
		String consulta = "SELECT idconcepto " + " FROM concepto " + " WHERE nombre = '" + nombre + "'";
		ResultSet res;
		try {
			ConsultaConsola(consulta);
			res = instancia_sql.executeQuery(consulta);
			while (res.next()) {
				id = res.getString("idconcepto");
			}
		} catch (Exception e) {
			id = "0";
			System.out.println(e.toString());
		}
		return id;
	}

	/**
	 * InsertarConceptoEx --- Método que invoca al procedimento insertarConcepto
	 * de MYSQL
	 * 
	 * @param idc
	 *            String --- Id del nuevo concepto
	 * @param nombre
	 *            String --- Nombre del Nuevo Concepto
	 * @return
	 */
	public String insertarconceptoex(String idc, String nombre) {
		String id = "0";
		String consulta = "SELECT insertarconcepto(" + idc + ",'" + nombre + "')";
		ResultSet res;
		try {
			ConsultaConsola(consulta);
			res = instancia_sql.executeQuery(consulta);
			while (res.next()) {
				id = res.getString(1);
			}
		} catch (Exception e) {
			id = "0";
			System.out.println(e.toString());
		}
		return id;
	}

	/**
	 * Conceptos --- Método que regresa todas los conceptos que estan en la base
	 * de datos
	 * 
	 * @return Lconcepto LinkedList<Concepto> --- lista de conceptos
	 */
	public LinkedList<Concepto> GetConceptos() {
		Concepto con;
		ResultSet conceptos;
		LinkedList<Concepto> Lconceptos = new LinkedList<Concepto>();
		String consulta = " SELECT c.idconcepto, c.idpartida, c.nombre FROM concepto AS c  ORDER BY c.idpartida ASC, c.nombre ASC";
		try {
			ConsultaConsola(consulta);
			conceptos = instancia_sql.executeQuery(consulta);
			while (conceptos.next() == true) {
				con = new Concepto();
				con.setIdconcepto(conceptos.getInt("idconcepto"));
				con.setIdpartida(conceptos.getInt("idpartida"));
				con.setNombre(conceptos.getString("nombre"));
				Lconceptos.add(con);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return Lconceptos;
	}
	/**
	 * modificarConceptos --- Método que modifica la información de un concepto
	 * seleccionado
	 * 
	 * @param idconcepto
	 *            String --- id del concepto a actualizar
	 * @param nombre
	 *            String --- nombre del concepto que vamos a actualizar
	 * @param idpartida
	 *            String --- id de la partida del concepto que se va a
	 *            actualizar
	 * @return insert con éxito ? true : false
	 */
	public boolean modificarconceptos(String idconcepto, String nombre, String idpartida) {
		boolean ban = false;
		String consulta = "UPDATE concepto SET nombre = '" + nombre + "', " + "idpartida = " + idpartida + " " + "WHERE idconcepto = " + idconcepto + ";";
		try {
			ConsultaConsola(consulta);
			instancia_sql.execute(consulta);
			ban = true;
		} catch (Exception e) {
			ban = false;
			System.out.println(e.toString());
		}
		return ban;
	}

	/**
	 * elimiarconcepto --- Método que elimina un concepto de la base de datos
	 * 
	 * @param idconcepto
	 *            String --- identificador del concepto a eliminar
	 * @return eliminación con éxito ? true : false
	 */
	public boolean eliminarConcepto(String idconcepto) {
		boolean ban = false;
		String consulta = "DELETE FROM concepto " + "WHERE idconcepto = " + idconcepto;
		try {
			ConsultaConsola(consulta);
			instancia_sql.execute(consulta);
			ban = true;
		} catch (Exception e) {
			ban = false;
			System.out.println(e.toString());
		}
		return ban;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
/**
* APARTADO DE METODOS PARA ASPECTOS
* ADD,GET,UPDATE,DELETE
* 
*/
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * AgregarAspecto --- Método que agrega un nuevo aspecto a la base de datos
	 * 
	 * @param idconcepto
	 *            String --- identificador del concepto para hacer el insert
	 * @param clave
	 *            String --- clave del aspecto
	 * @param unidad
	 *            String ---unidad del aspecto
	 * @param tipo
	 *            String --- tipo del aspecto
	 * @param costo
	 *            String ---costo del aspecto
	 * @param descripcion
	 *            --- descripción del aspecto
	 * @return ban boolean --- insert con éxito ? true : false
	 */
	public boolean agregaraspecto(String idconcepto, String clave, String unidad, String tipo, String costo, String descripcion, String clave_publica) {
		boolean ban = false;

		String consulta = " INSERT INTO `aspecto` " + " SET `idConcepto`= '" + idconcepto + "' , " + " `clave` = '" + clave + "' , " + " `unidad` = '" + unidad + "' , " + " `tipo` = '" + tipo + "' , " + " `costo` = '" + costo + "' , " + "`descripcion` = '" + descripcion + "', clave_publica='" + clave_publica + "' ; ";
		try {
			ConsultaConsola(consulta);
			instancia_sql.execute(consulta);
			ban = true;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return ban;
	}

	/**
	 * ExistenciaAspectoEx --- Método que selecciona el aspecto dependiendo de
	 * la clave
	 * 
	 * @param clave
	 *            String --- filtro para la consulta en la base de datos
	 * @return id String --- id del aspecto
	 */
	public String existeaspectoex(String clave) {
		String id = "0";
		String consulta = " SELECT a.idaspecto " + " FROM aspecto AS a " + " WHERE a.clave = '" + clave + "'";
		ResultSet res;
		try {
			ConsultaConsola(consulta);
			res = instancia_sql.executeQuery(consulta);
			while (res.next()) {
				id = res.getString("idaspecto");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return id;
	}

	/**
	 * AspectosMétodo que regresa todos los aspectos de la base de datos
	 * 
	 * @return aspectos LinkedList<Aspectodos> --- Lista de Aspectos
	 */
	public LinkedList<Aspecto> GetAspectos() {
		Aspecto aspe;
		ResultSet aspecto;
		LinkedList<Aspecto> Laspecto = new LinkedList<Aspecto>();
		String consulta = " SELECT a.idaspecto, a.idConcepto, a.clave, a.unidad, a.tipo, a.costo, a.descripcion, a.clave_publica FROM aspecto AS a  ORDER BY a.idConcepto ASC, a.clave ASC";
		try {
			ConsultaConsola(consulta);
			aspecto = instancia_sql.executeQuery(consulta);
			while (aspecto.next() == true) {
				aspe = new Aspecto();
				aspe.setIdaspecto(aspecto.getInt("idaspecto"));
				aspe.setIdconcepto(aspecto.getInt("idConcepto"));
				aspe.setClave(aspecto.getString("clave"));
				aspe.setUnidad(aspecto.getString("unidad"));
				aspe.setTipo(aspecto.getString("tipo"));
				aspe.setCosto(aspecto.getString("costo"));
				aspe.setDescripcion(aspecto.getString("descripcion"));
				aspe.setClave_privada(aspecto.getString("clave_publica"));
				Laspecto.add(aspe);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return Laspecto;
	}
	
	/**
	 * ModificarAspecto --- Método que actualiza la informaciï¿½n de los
	 * conceptos
	 * 
	 * @param clave
	 *            String --- clave del aspecto a Actualizar
	 * @param unidad
	 *            --- unidad del nuevo aspecto a actualizar
	 * @param costo
	 *            --- costo del nuevo aspecto
	 * @param descripcion
	 *            --- descripción del nuevo aspecto
	 * @param indice
	 *            --- índice del nuevo aspecto
	 * @return ban boolean --- update con éxito ? true : false
	 */
	public boolean modificaraspecto(String clave, String unidad, String costo, String descripcion, String indice, String clave_publica) {
		boolean ban = false;
		String consulta = "UPDATE aspecto SET  clave='" + clave + "', " + " unidad='" + unidad + "', " + " costo=" + costo + ", " + " descripcion='" + OptionsText.replaceString(descripcion) + "', clave_publica='" + clave_publica + "' " + " WHERE idaspecto=" + indice + ";";
		try {
			ConsultaConsola(consulta);
			if (instancia_sql.execute(consulta) == false) {
				ban = true;
				System.out.println("Actualización");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return ban;
	}

	/**
	 * Eliminar Aspecto --- Método que elimina un aspecto por índice
	 * 
	 * @param indice
	 *            String --- índice del aspecto a eliminar
	 * @return
	 */
	public boolean eliminaraspecto(String indice) {
		boolean ban = false;
		String consulta = " DELETE FROM aspecto " + " WHERE idaspecto = " + indice;
		try {
			ConsultaConsola(consulta);
			if (instancia_sql.execute(consulta) == false) {
				ban = true;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return ban;
	}
	
	/**
	 * Método para obtener la descripción completa de un aspecto
	 * @param idAspecto --- identificador del aspecto a recuparar su descripcion 
	 * @return aspectoResult --- información del aspecto recuperado
	 */
	public Aspecto getSeeMoreDescriptionAspecto(int idAspecto) {
		String query = "SELECT a.descripcion_completa, a.clave, a.clave_publica " + "FROM aspecto AS a " + "WHERE a.idaspecto = " + idAspecto;
		ResultSet result;
		Aspecto aspectoResult = new Aspecto();
		try {
			// ConsultaConsola(query);
			result = instancia_sql.executeQuery(query);
			while (result.next()) {
				aspectoResult.setDescripcion_Completa(result.getString("descripcion_completa"));
				aspectoResult.setClave_privada(result.getString("clave_publica"));
				aspectoResult.setClave(result.getString("clave"));
			}
		} catch (Exception e) {
		}
		return aspectoResult;
	}
	
	/**
	 * Actualización de la descripción del aspecto seleccionado
	 * @param idaspecto --- ID del aspecto 
	 * @param text --- nueva descripción del aspecto 
	 * @return update con exitr : true ? false
	 */
	public boolean updateDescriptionAspecto(int idaspecto, String text) {
		String query = "UPDATE `aspecto` SET `descripcion_completa`='" + text + "' WHERE (`idaspecto`='" + idaspecto + "')";
		try {
			// ConsultaConsola(query);
			instancia_sql.execute(query);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
/**
* APARTADO DE METODOS PARA PROYECTOS
* ADD,GET,UPDATE,DELETE
* 
*/
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Método que inserta un proyecto y regresa su identificador de la base de
	 * datos
	 * 
	 * @param idtipo
	 *            String --- identificador del proyecto
	 * @param inicio
	 *            String --- fecha de inicio del proyecto
	 * @param fin
	 *            String --- fecha final del proyecto
	 * @param descripcion
	 *            String --- descripción del proyecto
	 * @param nombre
	 *            String --- nombre del proyecto
	 * @return insert con éxito ? true : false
	 */
	public int Insertarproyecto(String idtipo, String inicio, String fin, String descripcion, String nombre, String comentarios) {
		String consulta;
		if (fin == "") {
			consulta = "SELECT Insertarproyecto(" + idtipo + ",'" + inicio + "','" + OptionsText.replaceString(descripcion) + "','" + nombre + "');";
		} else {
			consulta = "SELECT Insertarproyecto_final(" + idtipo + ",'" + inicio + "','" + fin + "','" + OptionsText.replaceString(descripcion) + "','" + nombre + "');";
		}
		ResultSet res;
		int indice = -1;
		try {
			ConsultaConsola(consulta);
			res = instancia_sql.executeQuery(consulta);
			while (res.next()) {
				indice = res.getInt(1);
			}
		} catch (Exception e) {
			indice = -1;
			System.out.println(e.toString());
		}
		return indice;
	}

	/**
	 * Mpetodo que pregutna si existe un proyecto registrado en la base de datos
	 * 
	 * @param nombre
	 *            String --- nombre del proyecto para buscar coincidencias en la
	 *            base de datos
	 * @return existe nombre proyecto ? true : false
	 */
	public boolean existeproyecto(String nombre) {
		boolean ban = false;
		String consulta = "SELECT proyecto " + " FROM proyecto " + " WHERE proyecto = '" + nombre + "'";
		ResultSet resultado;
		String nom = "";
		try {
			ConsultaConsola(consulta);
			resultado = instancia_sql.executeQuery(consulta);
			while (resultado.next()) {
				nom = resultado.getString("proyecto");
			}
			if (nom.equals("") == false) {
				ban = true;
			}
		} catch (Exception e) {
			ban = false;
			System.out.println(e.toString());
		}
		return ban;
	}

	/**
	 * Proyectos --- Método que regresa la lista de proyecto que maneja el
	 * sistema SysGeneral
	 * 
	 * @return proye LinkedList<Proyecto> --- Lista de Proyectos de la base de
	 *         datos
	 */
	public LinkedList<Proyecto> GetProyectos() {
		LinkedList<Proyecto> proye = new LinkedList<Proyecto>();
		Proyecto pro;
		ResultSet proyectos = null;
		String consulta = " SELECT p.idproyecto, p.idtipo, p.proyecto, p.descripcion, p.inicio, p.fin, p.comentarios " + " FROM proyecto AS p ";
		try {
			ConsultaConsola(consulta);
			proyectos = instancia_sql.executeQuery(consulta);
			while (proyectos.next() == true) {
				pro = new Proyecto();
				pro.setIdproyecto(proyectos.getInt("idproyecto"));
				pro.setIdtipo(proyectos.getInt("idtipo"));
				pro.setProyecto(proyectos.getString("proyecto"));
				pro.setDescripcion(proyectos.getString("descripcion"));
				pro.setInicio(proyectos.getDate("inicio"));
				pro.setFin(proyectos.getDate("fin"));
				proye.add(pro);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return proye;
	}

	/**
	 * Método que modifica el contenido de un proyecto
	 * 
	 * @param idtipo
	 *            String --- Identificador del tipo de proyecto de obra
	 * @param inicio
	 *            String --- fecha de inicio del proyecto
	 * @param fin
	 *            String --- fecha final del proyecto
	 * @param descripcion
	 *            String --- descripción del proyecto
	 * @param nombre
	 *            String --- nombre del proyecto
	 * @param idproyecto
	 *            String --- identificador del proyecto
	 * @return update con éxito ? true : false
	 */
	public boolean modificarProyecto(String idtipo, String inicio, String fin, String descripcion, String nombre, String idproyecto) {
		boolean ban = false;
		// ConsultaConsola(fin);
		String consulta = "UPDATE proyecto " + " SET idtipo = " + idtipo + "," + " inicio = '" + inicio + "',";
		if (!fin.isEmpty()) {
			consulta += " fin = '" + fin + "', ";
		}
		consulta += " descripcion = '" + OptionsText.replaceString(descripcion) + "', " + " proyecto = '" + nombre + "' " + " WHERE idproyecto = " + idproyecto;
		try {
			ConsultaConsola(consulta);
			instancia_sql.execute(consulta);
			ban = true;
		} catch (Exception e) {
			ban = false;
			System.out.println(e.toString());
		}
		return ban;
	}

	/**
	 * Método que elimina un proyecto
	 * 
	 * @param idproyecto
	 *            String --- Identificador del proyecto a elmininar
	 * @return delete con éxito ? true : false
	 */
	public boolean eliminarProyecto(String idproyecto) {
		boolean ban = false;
		String consulta = "DELETE FROM proyecto " + " WHERE idproyecto = " + idproyecto;
		try {
			ConsultaConsola(consulta);
			instancia_sql.execute(consulta);
			ban = true;
		} catch (Exception e) {
			ban = false;
			System.out.println(e.toString());
		}
		return ban;
	}

	/**
	 * Método para actualizar la información de los comentarios de un proyecto
	 * @param idProj --- ID del proyecto
	 * @param comments --- nuevos comentarios del proyecto
	 * @return update con éxito ? true : false 
	 */
	public boolean updateCommentsProj(int idProj, String comments) {
		String query = "UPDATE `proyecto` SET `comentarios`='" + comments + "' WHERE (`idproyecto`='" + idProj + "')";
		try {
			// ConsultaConsola(query);
			instancia_sql.execute(query);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}

	/**
	 * Método para obtener los comentarios de un proyecto
	 * @param idProj --- Id del proyecto para recuperar sus comentarios
	 * @return proj--- información del proyecto
	 */
	public Proyecto getCommentsProj(int idProj) {
		String query = "SELECT p.comentarios, p.proyecto FROM proyecto AS p WHERE p.idproyecto = " + idProj;
		ResultSet result;
		Proyecto proj = new Proyecto();
		try {
			// ConsultaConsola(query);
			result = instancia_sql.executeQuery(query);
			while (result.next()) {
				proj.setComentarios(result.getString("comentarios"));
				proj.setProyecto(result.getString("proyecto"));
			}
		} catch (Exception e) {

		}
		return proj;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
/**
* APARTADO DE METODOS PARA TIPOS DE PROYECTOS
* ADD,GET,UPDATE,DELETE
* 
*/
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Método que inserta un tipo de obra (tipo de proyecto)
	 * 
	 * @param tipo
	 *            String --- nombre del tipo de obra a guardar en la base de
	 *            datos
	 * @return insert con éxito ? true : false
	 */
	public boolean insertatipo(String tipo) {
		String consulta = "INSERT INTO tipos " + " SET tipo='" + tipo + "';";
		boolean ban = false;
		try {
			ConsultaConsola(consulta);
			instancia_sql.execute(consulta);
			ban = true;
		} catch (Exception e) {
			ban = false;
			System.out.println(e.toString());
		}
		return ban;
	}

	/**
	 * Método que verifica la existencia de un tipo de proyecto en la base de
	 * datos
	 * 
	 * @param tipo
	 *            String --- nombre del tipo de proyecto a buscar
	 * @return existe tipo proyecto ? true : false
	 */
	public boolean existetipo(String tipo) {
		boolean ban = false;
		String ti = "";
		ResultSet resultado;
		String consulta = "SELECT tipo " + "FROM tipos " + "WHERE tipo='" + tipo + "'";
		try {
			ConsultaConsola(consulta);
			resultado = instancia_sql.executeQuery(consulta);
			while (resultado.next()) {
				ti = resultado.getString("tipo");
			}
			if (ti.equals("") == false) {
				ban = true;
			}
		} catch (Exception e) {
			ban = false;
			System.out.println(e.toString());
		}
		return ban;
	}

	/**
	 * Método que regresa los tipos de proyectos
	 * 
	 * @return Ltipos --- lista de tipos de proyectos
	 */
	public LinkedList<Tipo> Tiposproyectos() {
		String consulta = "SELECT idtipo,tipo" + " FROM tipos";
		ResultSet resultado;
		LinkedList<Tipo> Ltipos = new LinkedList<Tipo>();
		Tipo ti;
		try {
			ConsultaConsola(consulta);
			resultado = instancia_sql.executeQuery(consulta);
			while (resultado.next()) {
				ti = new Tipo();
				ti.setIdtipo(resultado.getString("idtipo"));
				ti.setTipo(resultado.getString("tipo"));
				Ltipos.add(ti);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return Ltipos;
	}

		/**
	 * Método que modifica un tipo de obra en la base de datos
	 * 
	 * @param tipo
	 *            String --- nombre del nuevo tipo de obra
	 * @param idtipo
	 *            String --- identificador del tipo a actualizar
	 * @return update con éxito ? true : false
	 */
	public boolean modificartipo(String tipo, String idtipo) {
		String consulta = "UPDATE tipos " + "SET tipo = '" + tipo + "' " + " WHERE idtipo = " + idtipo;
		boolean ban = false;
		try {
			ConsultaConsola(consulta);
			instancia_sql.execute(consulta);
			ban = true;
		} catch (Exception e) {
			ban = false;
			System.out.println(e.toString());
		}
		return ban;
	}

	/**
	 * Método que elimina un tipo de proyecto de la tabla tipos de proyectos
	 * 
	 * @param indice
	 *            Strinng --- identificador del tipo de proyecto a eliminar
	 * @return delete con éxito ? true : false
	 */
	public boolean eliminartipo(String indice) {
		String consulta = "DELETE FROM tipos " + " WHERE idtipo = " + indice;
		boolean ban = false;
		try {
			ConsultaConsola(consulta);
			instancia_sql.execute(consulta);
			ban = true;
		} catch (Exception e) {
			ban = false;
			System.out.println(e.toString());
		}
		return ban;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
/**
* APARTADO DE METODOS PARA FRENTES
* ADD,GET,UPDATE,DELETE
* 
*/
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Método que inserta un frente
	 * 
	 * @param idproyecto
	 *            String --- Identificador del proyecto que se relaciona con el
	 *            frente
	 * @param identificador
	 *            String --- identificador del frente a insertar
	 * @param ubicacion
	 *            string --- ubicación del frente
	 * @return insert con éxito ? true : false
	 */
	public int Insetarfrente(String idproyecto, String identificador, String ubicacion) {
		int indice = -1;
		String consulta = "SELECT AgregarFrente(" + idproyecto + ",'" + identificador + "','" + ubicacion + "')";
		ResultSet res;
		try {
			ConsultaConsola(consulta);
			res = instancia_sql.executeQuery(consulta);
			while (res.next()) {
				indice = res.getInt(1);
			}
		} catch (Exception e) {
			indice = -1;
			System.out.println(e.toString());
		}
		return indice;
	}

	/**
	 * Frentes --- Método que regresa todos los frentes que han en la base de
	 * datos, cargandolos en memoria
	 * 
	 * @return Lfrentes LinkedList<Frente> --- Lista de frentes existentes
	 */
	public LinkedList<Frente> Frentes() {
		Frente fre;
		ResultSet frentes = null;
		LinkedList<Frente> Lfrentes = new LinkedList<Frente>();
		String consulta = " SELECT f.idfrente, f.idproyecto, f.identificador, f.ubicacion " + " FROM frente AS f ";
		try {
			ConsultaConsola(consulta);
			frentes = instancia_sql.executeQuery(consulta);
			while (frentes.next() == true) {
				fre = new Frente();
				fre.setIdfrente(frentes.getInt("idfrente"));
				fre.setIdproyecto(frentes.getInt("idproyecto"));
				fre.setIdentificador(frentes.getString("identificador"));
				fre.setUbicacion(frentes.getString("ubicacion"));
				Lfrentes.add(fre);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return Lfrentes;
	}

	/**
	 * Métod que modifica un frente
	 * 
	 * @param identificador
	 *            String --- identificador del frente
	 * @param ubicacion
	 *            String --- ubicación del frente
	 * @param idfrente
	 *            String --- identificador del frente (llave primaria)
	 * @return update con éxito ? true : false
	 */
	public boolean ModificarFrente(String identificador, String ubicacion, String idfrente) {
		boolean ban = false;
		String consulta = "UPDATE frente " + " SET identificador = '" + identificador + "', " + " ubicacion = '" + ubicacion + "'  " + " WHERE idfrente = " + idfrente;
		try {
			ConsultaConsola(consulta);
			instancia_sql.execute(consulta);
			ban = true;
		} catch (Exception e) {
			ban = false;
			System.out.println(e.toString());
		}
		return ban;
	}

	/**
	 * Método que elimina un frente de las tabla de frentes
	 * 
	 * @param idfrente
	 *            string --- Identificador del frente a eliminar
	 * @return delete con éxtio ? true : false
	 */
	public boolean eliminafrente(String idfrente) {
		boolean ban;
		String consulta = "DELETE FROM frente " + " WHERE idfrente = " + idfrente;
		try {
			ConsultaConsola(consulta);
			instancia_sql.execute(consulta);
			ban = true;
		} catch (Exception e) {
			ban = false;
			System.out.println(e.toString());
		}
		return ban;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
/**
* APARTADO DE METODOS PARA ESTIMACIÓN INICIAL
* ADD,GET,UPDATE,DELETE
* 
*/
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * insertarestimacion --- Método que registra una nueva estimación
	 * 
	 * @param idfrente
	 *            --- identificador del frente de la estimación
	 * @param idconsultor
	 *            --- identificador del consultor
	 * @param porcentaje
	 *            --- porcentaje de la estimación
	 * @param fecha
	 *            --- fecha de la estimación
	 * @param tipo
	 *            --- tipo de estimación
	 * @param nestimacion
	 *            --- Revisar
	 *            !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * @return ban --- nÃºmero mayor o igual a 1 si se ejecuto con éxito el
	 *         insert sino -1
	 */
	public int insertarestimacion(String idfrente, String idconsultor, String porcentaje, String fecha, String tipo, String nestimacion) {
		int ban = 1;
		String consulta = "SELECT Insertarestimacion (" + idfrente + "," + idconsultor + "," + porcentaje + ",'" + fecha + "','" + tipo + "'," + nestimacion + ");";
		ResultSet re;
		try {
			ConsultaConsola(consulta);
			re = instancia_sql.executeQuery(consulta);
			while (re.next()) {
				ban = re.getInt(1);
			}
		} catch (Exception e) {
			ban = -1;
			System.out.println(e.toString());
		}
		return ban;
	}

	/**
	 * Método que inserta los conceptos que estanran contenidos en la estimación
	 * incial
	 * 
	 * @param idaspecto
	 * @param idestimacion
	 * @param cantidad
	 * @param importe
	 * @param ubicacionx
	 * @param ubicaciony
	 * @param ubicacionz
	 * @param alto
	 * @param largo
	 * @param ancho
	 * @param costo
	 * @param idpartida
	 * @param repeticion
	 * @param fechaestimacion
	 * @return insert con éxito ? true : false
	 */
	public boolean estimacionaspecto(String idaspecto, String idestimacion, String cantidad, String importe, String ubicacionx, String ubicaciony, String ubicacionz, String alto, String largo, String ancho, String costo, String idpartida, String repeticion, String fechaestimacion) {
		boolean ban = false;
		String consulta = "SELECT InsertarAC(" + idaspecto + "," + idestimacion + "," + cantidad + "," + importe + "," + ubicacionx + "," + ubicaciony + "," + ubicacionz + "," + alto + "," + largo + "," + ancho + "," + costo + "," + idpartida + "," + repeticion + ",'" + fechaestimacion + "');";
		try {
			ConsultaConsola(consulta);
			instancia_sql.executeQuery(consulta);
			ban = true;
		} catch (Exception e) {
			ban = false;
			System.out.println(e.toString());
		}
		return ban;
	}

	/**
	 * Método para seleccionar los elementos que tendra el reporte general
	 * 
	 * @param idestimacion
	 *            --- identificador de la estimación que va a ser seleccionada
	 * @return lista --- lista con los datos que seran enviados al reporte
	 */
	public LinkedList<Rgenerador> reportegeneral(String idestimacion) {
		LinkedList<Rgenerador> lista = new LinkedList<Rgenerador>();
		ResultSet res;
		Rgenerador ob;
		String consulta = "SELECT partida.nombre, aspecto.idConcepto, esti_aspec.idaspecto, aspecto.clave, aspecto.clave_publica, aspecto.descripcion, aspecto.unidad, esti_aspec.ubicacionX, " + // CAMPOS DE SELECT
				" esti_aspec.ubicacionY, esti_aspec.ubicacionZ, esti_aspec.alto, esti_aspec.largo, esti_aspec.Ancho, " + // CAMPOS
				" esti_aspec.importe, esti_aspec.cantidad, esti_aspec.costo, esti_aspec.repeticion, esti_aspec.fechae " + // CAMPOS
				" FROM aspecto " + " join partida join esti_aspec WHERE aspecto.idaspecto = esti_aspec.idaspecto " + " AND partida.idpartida = esti_aspec.idpartida  " + " AND esti_aspec.idestimacion = " + idestimacion;
		try {
			ConsultaConsola(consulta);
			res = instancia_sql.executeQuery(consulta);
			while (res.next()) {
				ob = new Rgenerador();
				ob.setIdconcepto(res.getString("aspecto.idConcepto"));
				ob.setIdaspecto(res.getString("esti_aspec.idaspecto"));
				ob.setPartida(res.getString("partida.nombre"));
				ob.setClave(res.getString("aspecto.clave"));
				ob.setClavePublica(res.getString("aspecto.clave_publica"));
				ob.setDescripcion(res.getString("aspecto.descripcion"));
				ob.setUnidad(res.getString("aspecto.unidad"));
				ob.setX(res.getString("esti_aspec.ubicacionX"));
				ob.setY(res.getString("esti_aspec.ubicacionY"));
				ob.setZ(res.getString("esti_aspec.ubicacionZ"));
				ob.setAlto(res.getString("esti_aspec.alto"));
				ob.setLargo(res.getString("esti_aspec.largo"));
				ob.setAncho(res.getString("esti_aspec.Ancho"));
				ob.setImporte(res.getString("esti_aspec.importe"));
				ob.setCantidad(res.getString("esti_aspec.cantidad"));
				ob.setCosto(res.getString("esti_aspec.costo"));
				ob.setRepeticion(res.getInt("esti_aspec.repeticion"));
				ob.setFecha(res.getString("esti_aspec.fechae"));
				lista.add(ob);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lista;
	}

	/**
	 * Método para seleccionar los elementos del reporte por periodos
	 * 
	 * @param idestimacion
	 *            --- identificador de estimación a buscar
	 * @param fechai
	 *            --- fecha inicial a seleccionar
	 * @param fechaf
	 *            --- fecha final a seleccionar
	 * @return lista --- lista de elementos para generar el reporte por periodos
	 */
	public LinkedList<Rgenerador> reporteperiodo(String idestimacion, String fechai, String fechaf) {
		LinkedList<Rgenerador> lista = new LinkedList<Rgenerador>();
		ResultSet res = null;
		Rgenerador ob;
		String consulta = "SELECT partida.nombre, aspecto.idConcepto, esti_aspec.idaspecto, aspecto.clave, aspecto.descripcion, aspecto.unidad, esti_aspec.ubicacionX," + // CAMPOS DE	SELECT
				" esti_aspec.ubicacionY, esti_aspec.ubicacionZ, esti_aspec.alto, esti_aspec.largo, esti_aspec.Ancho, " + // CAMPOS DE SELECT
				" esti_aspec.importe, esti_aspec.cantidad, esti_aspec.costo, esti_aspec.repeticion, esti_aspec.fechae " + // CAMPOS DE SELECT
				"FROM aspecto " + "join partida join esti_aspec WHERE aspecto.idaspecto = esti_aspec.idaspecto " + "AND  partida.idpartida = esti_aspec.idpartida  " + "AND esti_aspec.idestimacion = " + idestimacion + "  AND fechae >= '" + fechaf + "' AND fechae <= '" + fechai + "' ;";
		try {
			ConsultaConsola(consulta);
			res = instancia_sql.executeQuery(consulta);
			while (res.next()) {
				ob = new Rgenerador();
				ob.setIdconcepto(res.getString("aspecto.idConcepto"));
				ob.setIdaspecto(res.getString("esti_aspec.idaspecto"));
				ob.setPartida(res.getString("partida.nombre"));
				ob.setClave(res.getString("aspecto.clave"));
				ob.setDescripcion(res.getString("aspecto.descripcion"));
				ob.setUnidad(res.getString("aspecto.unidad"));
				ob.setX(res.getString("esti_aspec.ubicacionX"));
				ob.setY(res.getString("esti_aspec.ubicacionY"));
				ob.setZ(res.getString("esti_aspec.ubicacionZ"));
				ob.setAlto(res.getString("esti_aspec.alto"));
				ob.setLargo(res.getString("esti_aspec.largo"));
				ob.setAncho(res.getString("esti_aspec.Ancho"));
				ob.setImporte(res.getString("esti_aspec.importe"));
				ob.setCantidad(res.getString("esti_aspec.cantidad"));
				ob.setCosto(res.getString("esti_aspec.costo"));
				ob.setRepeticion(res.getInt("esti_aspec.repeticion"));
				ob.setFecha(res.getString("esti_aspec.fechae"));
				lista.add(ob);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lista;
	}

		/**
	 * Método para seleccionar los elementos del reporte por estimación y
	 * partida
	 * 
	 * @param idestimacion
	 *            --- identificador de la estimación
	 * @param idpartida
	 *            --- identificador de la partida
	 * @return lista --- lista de los elementos para generar el reporte
	 */
	public LinkedList<Rgenerador> general(String idestimacion, String idpartida) {
		LinkedList<Rgenerador> lista = new LinkedList<Rgenerador>();
		ResultSet res;
		Rgenerador ob;
		String consulta = "SELECT esti_aspec.idaspecto,aspecto.clave,aspecto.clave_publica,aspecto.descripcion,aspecto.unidad,esti_aspec.ubicacionX," + // CAMPOS DE SELECT
				" esti_aspec.ubicacionY, esti_aspec.ubicacionZ, esti_aspec.alto, esti_aspec.largo, esti_aspec.Ancho, " + " esti_aspec.importe, esti_aspec.cantidad, esti_aspec.costo, esti_aspec.idpartida, esti_aspec.repeticion, esti_aspec.fechae " + " FROM aspecto " + " join partida join esti_aspec WHERE aspecto.idaspecto = esti_aspec.idaspecto " + " AND partida.idpartida = esti_aspec.idpartida  " + " AND esti_aspec.idestimacion = " + idestimacion + " AND esti_aspec.idpartida = " + idpartida;
		try {
			ConsultaConsola(consulta);
			res = instancia_sql.executeQuery(consulta);
			while (res.next()) {
				ob = new Rgenerador();
				ob.setIdaspecto(res.getString("esti_aspec.idaspecto"));
				ob.setClave(res.getString("aspecto.clave"));
				ob.setClavePublica(res.getString("aspecto.clave_publica"));
				ob.setDescripcion(res.getString("aspecto.descripcion"));
				ob.setUnidad(res.getString("aspecto.unidad"));
				ob.setX(res.getString("esti_aspec.ubicacionX"));
				ob.setY(res.getString("esti_aspec.ubicacionY"));
				ob.setZ(res.getString("esti_aspec.ubicacionZ"));
				ob.setAlto(res.getString("esti_aspec.alto"));
				ob.setLargo(res.getString("esti_aspec.largo"));
				ob.setAncho(res.getString("esti_aspec.Ancho"));
				ob.setImporte(res.getString("esti_aspec.importe"));
				ob.setPiezas(res.getString("esti_aspec.cantidad"));
				ob.setCosto(res.getString("esti_aspec.costo"));
				ob.setRepeticion(res.getInt("esti_aspec.repeticion"));
				ob.setFecha(res.getString("esti_aspec.fechae"));
				lista.add(ob);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lista;
	}



	/**
	 * Método para sacar las fechas de una estimación tabla esti-aspec
	 * 
	 * @param id
	 *            --- identificador de la estimación a buscar
	 * @return r --- lista de las fechas que coincidan con el criterio de
	 *         bÃºsqueda
	 */
	public LinkedList<String> sacarfechas(String id) {
		String consulta = "SELECT fechae " + " FROM esti_aspec " + " WHERE idestimacion = " + id + " GROUP BY fechae";
		ResultSet res;
		LinkedList<String> r = new LinkedList<String>();
		try {
			ConsultaConsola(consulta);
			res = instancia_sql.executeQuery(consulta);
			while (res.next()) {
				r.add(res.getString("fechae"));// agrega todas las fechas
												// que encuentre en los
												// resultados
			}
		} catch (Exception e) {
			r = null;
			System.out.println(e.toString());
		}
		return r;
	}

	/**
	 * Método que regresa las rutas de las fotografÃ­as que existen
	 * 
	 * @param idpartida
	 * @param idaspecto
	 * @param idestimacion
	 * @param repeticion
	 * @return
	 */
	public LinkedList<Rutas> Rutasfotos(String idpartida, String idaspecto, String idestimacion, String repeticion) {
		String consulta = "SELECT fotouno, fotodos " + "FROM esti_aspec " + "WHERE idpartida = " + idpartida + " AND idaspecto = " + idaspecto + " AND idestimacion = " + idestimacion + " AND repeticion = " + repeticion;
		ResultSet resultado;
		LinkedList<Rutas> Lruta = new LinkedList<Rutas>();
		Rutas ru;
		try {
			ConsultaConsola(consulta);
			resultado = instancia_sql.executeQuery(consulta);
			while (resultado.next()) {
				ru = new Rutas();
				ru.setUno(resultado.getString("fotouno"));
				ru.setDos(resultado.getString("fotodos"));
				Lruta.add(ru);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return Lruta;
	}
	
	/**
	 * Métood que inserta y/o modifica el contenido con fotografÃ­a
	 * 
	 * @param idaspecto
	 * @param idestimacion
	 * @param cantidad
	 * @param importe
	 * @param ubicacionx
	 * @param ubicaciony
	 * @param ubicacionz
	 * @param alto
	 * @param largo
	 * @param ancho
	 * @param costo
	 * @param idpartida
	 * @param repeticion
	 * @param uno
	 * @param dos
	 * @param fechaestimacion
	 * @return insert con éxito ? true : false
	 */
	public boolean aspectofoto(String idaspecto, String idestimacion, String cantidad, String importe, String ubicacionx, String ubicaciony, String ubicacionz, String alto, String largo, String ancho, String costo, String idpartida, String repeticion, String uno, String dos, String fechaestimacion) {
		boolean ban = false;
		String consulta = "SELECT insertarfotos(" + idaspecto + "," + idestimacion + "," + cantidad + "," + importe + "," + ubicacionx + "," + ubicaciony + "," + ubicacionz + "," + alto + "," + largo + "," + ancho + "," + costo + "," + idpartida + "," + repeticion + ",'" + uno + "','" + dos + "','" + fechaestimacion + "');";
		try {
			ConsultaConsola(consulta);
			instancia_sql.executeQuery(consulta);
			ban = true;
		} catch (Exception e) {
			ban = false;
			System.out.println(e.toString());
		}
		return ban;
	}

	/**
	 * Método que elimina un aspecto de control de estimaciones
	 * 
	 * @param idaspecto
	 *            --- identifiacador del aspecto a eliminar
	 * @param idestimacion
	 *            --- identificador de la estimación a eliminar
	 * @return delete con éxito ? true : false
	 */
	public boolean Eliminaresaspecto(String idaspecto, String idestimacion) {
		boolean ban = false;
		String consulta = "SELECT EliminarAC (" + idaspecto + "," + idestimacion + ");";
		try {
			ConsultaConsola(consulta);
			instancia_sql.executeQuery(consulta);
			ban = true;
		} catch (Exception e) {
			ban = false;
			System.out.println(e.toString());
		}
		return ban;
	}

	/**
	 * Método que elimina un aspecto de la estimaciíon seleccionado, en la base de datos
	 * 
	 * @param idaspecto
	 *            --- identificador del aspecto
	 * @param idestimacion
	 *            --- identificador de la estimación
	 * @param repeticion
	 *            --- repetición
	 * @return delete con éxito ? true : false
	 */
	public boolean eleminarunaspecto(String idaspecto, String idestimacion, String repeticion) {
		boolean ban = false;
		String consulta = "DELETE FROM esti_aspec " + " WHERE idaspecto = " + idaspecto + " AND idestimacion = " + idestimacion + " AND repeticion = " + repeticion;
		try {
			ConsultaConsola(consulta);
			instancia_sql.execute(consulta);
			ban = true;
		} catch (Exception e) {
			ban = false;
			System.out.println(e.toString());
		}
		return ban;
	}
	
	/**
	 * Método para eliminar una estimación
	 * 
	 * @param idestimacion
	 *            --- identificador de la estimación a eliminar
	 * @return delete con éxito ? true : false
	 */
	public boolean EliminarEstimacion(String idestimacion) {
		String consulta = "SELECT Eliminarestimacion(" + idestimacion + ")";
		boolean ban;
		try {
			ConsultaConsola(consulta);
			instancia_sql.executeQuery(consulta);
			ban = true;
		} catch (Exception e) {
			System.out.println(e.toString());
			ban = false;
		}
		return ban;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
/**
* APARTADO DE METODOS PARA CONTROL DE ESTIMACIÓN
* ADD,GET,UPDATE,DELETE
* 
*/
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * PartidasSeleccionadas --- Método que devuelve las partidas seleccionadas
	 * 
	 * @param idestimacion
	 *            String --- Filtro
	 * @return lista LinkedList<String> --- Lista de Nombres de partidas
	 */
	public LinkedList<String> partidasSelecionadas(String idestimacion) {
		LinkedList<String> lista = new LinkedList<String>();
		ResultSet res;
		String nombre;
		String consulta = " SELECT p.nombre " + " FROM partida AS p " + " INNER JOIN esti_aspec AS ea ON p.idpartida = ea.idpartida " + " INNER JOIN control_estimacion AS ce ON ea.idestimacion = ce.idestimacion AND ce.idestimacion = " + idestimacion + " GROUP BY p.nombre " + " ORDER BY p.nombre ASC ";
		ConsultaConsola(consulta);
		try {
			res = instancia_sql.executeQuery(consulta);
			while (res.next()) {
				nombre = new String();
				nombre = res.getString("partida.nombre");
				lista.add(nombre);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lista;
	}
	
	/**
	 * Método que regresa si un frente ya tiene asociados estimaciones iniciales
	 * 
	 * @param idfrente
	 *            --- identificador del frente a buscar
	 * @param tipo
	 *            --- tipo de estimación a buscar
	 * @return list --- lista de Estimaciones encontradas con el criterio de
	 *         bÃºsqueda
	 */
	public LinkedList<Estimacion> sacarPreinicial(String idfrente, String tipo) {
		LinkedList<Estimacion> list = new LinkedList<Estimacion>();
		ResultSet resul;
		Estimacion es;
		String consulta = "SELECT idestimacion, idfrente, idconsultor, pocentaje, fecha, tipo, nestimacion " + " FROM control_estimacion " + " WHERE idfrente = " + idfrente + " AND tipo = '" + tipo + "';";
		try {
			ConsultaConsola(consulta);
			resul = instancia_sql.executeQuery(consulta);
			while (resul.next()) {
				es = new Estimacion();
				es.setIdestimacion(String.valueOf(resul.getInt("idestimacion")));
				es.setIdfrente(String.valueOf(resul.getInt("idfrente")));
				es.setIdConsultor(String.valueOf(resul.getInt("idconsultor")));
				es.setPorcentaje(String.valueOf(resul.getFloat("pocentaje")));
				es.setFecha(String.valueOf(resul.getString("fecha")));
				es.setTipo(String.valueOf(resul.getString("tipo")));
				es.setNestimacion(resul.getString("nestimacion"));
				list.add(es);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	/**
	 * Sacar la fecha de un control de estimación
	 * 
	 * @param id
	 *            --- identificador de estimación a buscar
	 * @return fe --- fecha de la estimación que cumpla con el criterio de
	 *         bÃºsqueda
	 */
	public String sacarfecha(String id) {
		String fe = "nada";
		String consulta = "SELECT fecha " + " FROM control_estimacion " + " WHERE idestimacion = " + id;
		ResultSet res;
		try {
			ConsultaConsola(consulta);
			res = instancia_sql.executeQuery(consulta);
			while (res.next()) {
				fe = res.getString("fecha");
			}
		} catch (Exception e) {
			fe = "Error";
			System.out.println(e.toString());
		}
		return fe;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
/**
* APARTADO DE METODOS PARA PLANTILLAS
* ADD,GET,UPDATE,DELETE
* 
*/
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Método que inserta una nueva plantilla y regresa su id
	 * 
	 * @param nombre
	 *            --- nombre de la plantilla
	 * @param fecha
	 *            --- fecha de la plantilla
	 * @return
	 */
	public int InsertarPlantilla(String nombre, String fecha) {
		String consulta = "SELECT AgregarPlantilla('" + nombre + "','" + fecha + "')";
		ResultSet resultado;
		int id = -1;
		try {
			ConsultaConsola(consulta);
			resultado = instancia_sql.executeQuery(consulta);
			while (resultado.next()) {
				id = resultado.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return id;
	}

	/**
	 * Método que inserta los aspectos en la tabla plantilla y los asocia a la
	 * plantilla
	 * 
	 * @param idplantilla
	 *            --- identificador de la plantilla
	 * @param idaspecto
	 *            --- identificador del aspecto
	 * @return insert con éxito ? true : false
	 */
	public boolean Insetaraspectospalntillas(String idplantilla, String idaspecto) {
		String consulta = "INSERT aspectos_plantillas " + "SET plantillas_idplantilla = " + idplantilla + ", idaspecto = " + idaspecto;
		boolean ban;
		try {
			ConsultaConsola(consulta);
			instancia_sql.execute(consulta);
			ban = true;
		} catch (Exception e) {
			ban = false;
			System.out.println(e.toString());
		}
		return ban;
	}

	/**
	 * Método que regrsa todas las plantillas existentes en la base de datos
	 * 
	 * @return lista --- lisata de las plantillas
	 */
	public LinkedList<Plantilla> consultarPlantillas() {
		String consulta = "SELECT idplantilla, nombre, fecha " + "FROM plantillas";
		LinkedList<Plantilla> lista = new LinkedList<Plantilla>();
		ResultSet res;
		Plantilla pla;
		try {
			ConsultaConsola(consulta);
			res = instancia_sql.executeQuery(consulta);
			while (res.next()) {
				pla = new Plantilla();
				pla.setIdplantilla(res.getString("idplantilla"));
				pla.setNombre(res.getString("nombre"));
				pla.setFecha(res.getString("fecha"));
				lista.add(pla);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lista;
	}
	
	/**
	 * Método que regresa una plantilla determinada
	 * 
	 * @param idplantilla
	 *            --- string identificador de la platilla a buscar
	 * @return lista de Rgenerador
	 */
	public LinkedList<Rgenerador> Regresarplantilla(String idplantilla) {
		String consulta = "SELECT concepto.nombre,concepto.idpartida,aspecto.idaspecto,aspecto.clave ,aspecto.descripcion,aspecto.unidad,aspecto.costo" + "  FROM plantillas join aspectos_plantillas join aspecto join concepto where  concepto.idconcepto=aspecto.idConcepto " + "  AND aspectos_plantillas.idaspecto=aspecto.idaspecto and aspectos_plantillas.plantillas_idplantilla = " + idplantilla + " GROUP BY aspectos_plantillas.idaspecto ORDER BY concepto.idpartida";
		ResultSet lista;
		Rgenerador re;
		LinkedList<Rgenerador> li = new LinkedList<Rgenerador>();
		try {
			ConsultaConsola(consulta);
			lista = instancia_sql.executeQuery(consulta);
			while (lista.next()) {
				re = new Rgenerador();
				re.setPartida(lista.getString("concepto.idpartida"));
				re.setIdaspecto(lista.getString("aspecto.idaspecto"));
				re.setClave(lista.getString("aspecto.clave"));
				re.setDescripcion(lista.getString("aspecto.descripcion"));
				re.setUnidad(lista.getString("aspecto.unidad"));
				re.setCosto(lista.getString("aspecto.costo"));
				re.setNombre(lista.getString("concepto.nombre"));
				re.setRepeticion(1);
				li.add(re);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return li;
	}
	/**
	 * Método que elimina una plantilla en especifico
	 * 
	 * @param id
	 *            --- identificador de la plantilla a eliminar
	 * @return delete con éxito ? true : false
	 */
	public boolean eliminarplantilla(String id) {
		boolean ban = false;
		String consulta = "SELECT eliminarplantilla(" + id + ")";
		ResultSet respuesta;
		int re = 0;
		try {
			ConsultaConsola(consulta);
			respuesta = instancia_sql.executeQuery(consulta);
			while (respuesta.next()) {
				re = respuesta.getInt(1);
			}
			if (re == 1) {
				ban = true;
			}
		} catch (Exception e) {
			ban = false;
			System.out.println(e.toString());
		}
		return ban;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
/**
* APARTADO DE METODOS 
* ADD,GET,UPDATE,DELETE
* 
*/
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//para recuperar solo aspectos de aceros>>estimacion de aceros
	
	/*SELECT
	aspecto.idaspecto,
	aspecto.idConcepto,
	aspecto.clave,
	aspecto.unidad,
	aspecto.tipo,
	aspecto.costo,
	aspecto.descripcion,
	aspecto.clave_publica,
	aspecto.descripcion_completa
	FROM
	aspecto
	INNER JOIN concepto ON aspecto.idConcepto = concepto.idconcepto
	INNER JOIN partida ON concepto.idpartida = partida.idpartida
	WHERE
	partida.nombre = 'ACEROS';
	/*/
	public void ConsultaConsola(String consulta) {
		System.out.println(consulta);
	}
}