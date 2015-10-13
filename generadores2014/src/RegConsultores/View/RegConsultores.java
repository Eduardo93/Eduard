package RegConsultores.View;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import AdministrarUnidadesMedida.Controller.CUnidad;
import AdministrarUnidadesMedida.View.VUnidadesMedida;
import MetodosRemotos.Metodos;
import Model.Entity.Unidad;
import Model.EntityCRUD.Exceptions.AttributeIsEmpty;
import Model.EntityCRUD.Exceptions.DuplicateKey;
import Model.EntityCRUD.Exceptions.ErrorConnectionDB;
import Model.EntityCRUD.Exceptions.MaxLength;
import Model.EntityCRUD.Exceptions.Unidad.NoSelectAttributeUnidad;
import ObjetosSerializables.Consultor;
import Options.LenString;
import Options.OptionsText;
import RegConsultores.Controller.CRegConsultores;

/**
 * Clase para la actualizaciones de los consultores en la base de datos
 * (usuarios)
 * @author Pablo Rivera
 * @colaboración luiiis Lazaro
 * @access {@docRoot}
 */
public class RegConsultores extends javax.swing.JInternalFrame {
	
	private static Logger log = Logger.getLogger(VUnidadesMedida.class);


	private static final long serialVersionUID = 1L;
	private JDesktopPane escritorio;
	private JPanel Paneluno;
	private JRadioButton agregar;
	private JTextField materno;
	private JLabel ematerno;
	private JComboBox < String > tipo;
	private JLabel etipo;
	private JButton aceptar;
	private JPasswordField password2;
	private JLabel epassword2;
	private JPasswordField password;
	private JLabel epassword;
	private JTextField login;
	private JLabel elogin;
	private JTextField nombre;
	private JLabel Enombre;
	private JTextField paterno;
	private JLabel epaterno;
	private JPanel Pdatos;
	private JLabel Eusuarios;
	private JComboBox < String > Cusuarios;
	private JRadioButton eliminar;
	private JRadioButton modificar;
	private JPanel panelopc;
	private Vector < Consultor > Lconsultores = new Vector < Consultor >( );
	private int idconsultor = -1;

	/**
	 * contructor de la clase inicia componentes gráficos
	 * 
	 * @param cone
	 *            para las operaciones a la base de datos
	 */
	public RegConsultores ( Metodos cone ) {
		super( "Registro de consultores", false, true, false, true );
		initGUI( cone );
	}

	/**
	 * Inicialización de componentes gráficos para la ventana de consultores
	 * 
	 * @param cone
	 *            para las operaciones a la base de datos
	 *            JComboBox.enabled= true/false -->
	 *            JcomboBox.setenable(false/true)
	 */
	private void initGUI ( final Metodos cone ) {
		try {
			this.setPreferredSize( new java.awt.Dimension( 484, 420 ) );
			this.setBounds( 0, 0, 374, 420 );
			setVisible( true );

			escritorio = new JDesktopPane( );
			getContentPane( ).add( escritorio, BorderLayout.CENTER );

			Paneluno = new JPanel( );
			escritorio.add( Paneluno, JLayeredPane.DEFAULT_LAYER );
			Paneluno.setBounds( 0, 0, 362, 395 );
			Paneluno.setLayout( null );

			panelopc = new JPanel( );
			Paneluno.add( panelopc );
			panelopc.setBounds( 6, 6, 345, 71 );
			panelopc.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );
			panelopc.setLayout( null );

			agregar = new JRadioButton( );
			agregar.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );
			panelopc.add( agregar );
			agregar.setText( "Agregarr" );
			agregar.setBounds( 17, 11, 73, 20 );
			agregar.addActionListener( new ActionListener( ) {
				/**
				 * Opción para Agregar un nuevo Usuario
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 * 
				 */
				public void actionPerformed ( ActionEvent e ) {
					modificar.setSelected( false );
					eliminar.setSelected( false );
					Cusuarios.setEnabled( false );
					Limpiar( );
					paterno.requestFocusInWindow( );
				}
			} );

			modificar = new JRadioButton( );
			modificar.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );
			panelopc.add( modificar );
			modificar.setText( "Modificar" );
			modificar.setBounds( 126, 11, 80, 20 );
			modificar.addActionListener( new ActionListener( ) {

				/**
				 * Opción para modificar/actualizar la información de un consultor 
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					agregar.setSelected( false );
					eliminar.setSelected( false );
					Lconsultores = cone.GetConsultores( );
					llenarConsultores( );
					Cusuarios.setEnabled( true );
					Limpiar( );
					llenarTXT( );
				}
			} );

			eliminar = new JRadioButton( );
			eliminar.setFont( new Font( "Tahoma", Font.PLAIN, 12 ) );
			panelopc.add( eliminar );
			eliminar.setText( "Eliminar" );
			eliminar.setBounds( 230, 11, 78, 20 );
			eliminar.addActionListener( new ActionListener( ) {

				/** Opción para Eliminar un consultor del sistema
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					agregar.setSelected( false );
					modificar.setSelected( false );
					Lconsultores = cone.GetConsultores( );
					llenarConsultores( );
					Cusuarios.setEnabled( true );
					Limpiar( );
					llenarTXT( );
				}
			} );

			ButtonGroup groupRadiosBtn = new ButtonGroup( );
			groupRadiosBtn.add( agregar );
			groupRadiosBtn.add( modificar );
			groupRadiosBtn.add( eliminar );

			Cusuarios = new JComboBox < String >( );
			panelopc.add( Cusuarios );
			Cusuarios.setBounds( 76, 37, 232, 26 );
			Cusuarios.setEnabled( false );
			Cusuarios.addActionListener( new ActionListener( ) {
				/**
				 * Sección encargada de llenar los campos de texto dependiendo de que usuario esta 
				 * seleccionado en el combo box
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					llenarTXT( );
				}
			} );

			Eusuarios = new JLabel( );
			panelopc.add( Eusuarios );
			Eusuarios.setText( "Usuarios:" );
			Eusuarios.setBounds( 7, 43, 60, 16 );

			Pdatos = new JPanel( );
			Paneluno.add( Pdatos );
			Pdatos.setBounds( 6, 83, 345, 305 );
			Pdatos.setLayout( null );
			Pdatos.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );

			epaterno = new JLabel( );
			Pdatos.add( epaterno );
			epaterno.setText( "Apellido Paterno:" );
			epaterno.setBounds( 10, 11, 107, 28 );

			paterno = new JTextField( );
			Pdatos.add( paterno );
			paterno.setBounds( 121, 11, 191, 28 );
			paterno.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );
			paterno.addKeyListener( new KeyListener( ) {

				/**
				 * Listener para escribir sólo caracteres permitidos en el JtextField
				 * invoca otro método statico que se encarga de revisar los caracteres. 
				 * @Override(non-Javadoc)
				 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
				 * validación de texto de entrada
				 */
				public void keyTyped ( KeyEvent e ) {
					OptionsText.onlyCharsSpace( e.getKeyChar( ), e );
				}
				public void keyPressed ( KeyEvent e ) {}
				public void keyReleased ( KeyEvent e ) {}
			} );

			ematerno = new JLabel( );
			Pdatos.add( ematerno );
			ematerno.setText( "Apellido Materno:" );
			ematerno.setBounds( 10, 51, 117, 28 );

			materno = new JTextField( );
			Pdatos.add( materno );
			materno.setBounds( 121, 51, 191, 29 );
			materno.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );
			materno.addKeyListener( new KeyListener( ) {

				/**
				 * Listener para escribir sólo caracteres permitidos en el JtextField
				 * invoca otro método statico que se encarga de revisar los caracteres. 
				 * @Override(non-Javadoc)
				 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
				 * validación del texto de entrada
				 */
				public void keyTyped ( KeyEvent e ) {
					OptionsText.onlyCharsSpace( e.getKeyChar( ), e );
				}

				public void keyPressed ( KeyEvent e ) {}

				public void keyReleased ( KeyEvent e ) {}
			} );

			Enombre = new JLabel( );
			Pdatos.add( Enombre );
			Enombre.setText( "Nombre(s):" );
			Enombre.setBounds( 47, 85, 70, 28 );

			nombre = new JTextField( );
			Pdatos.add( nombre );
			nombre.setBounds( 121, 86, 191, 27 );
			nombre.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );
			nombre.addKeyListener( new KeyListener( ) {

				/**
				 * Listener para escribir sólo caracteres permitidos en el JtextField
				 * invoca otro método statico que se encarga de revisar los caracteres. 
				 * @Override(non-Javadoc)
				 * @see	java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
				 * validación del texto de entrada
				 */
				public void keyTyped ( KeyEvent e ) {
					OptionsText.onlyCharsSpace( e.getKeyChar( ), e );
				}

				public void keyPressed ( KeyEvent e ) {}
				public void keyReleased ( KeyEvent e ) {}
			} );

			elogin = new JLabel( );
			Pdatos.add( elogin );
			elogin.setText( "Login:" );
			elogin.setBounds( 69, 123, 37, 23 );

			login = new JTextField( );
			Pdatos.add( login );
			login.setBounds( 121, 122, 191, 25 );
			login.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );
			login.addKeyListener( new KeyListener( ) {

				/**
				 * Listener para escribir sólo caracteres permitidos en el JtextField
				 * invoca otro método statico que se encarga de revisar los caracteres. 
				 * @Override (non-Javadoc)
				 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
				 * validacion de texto de entrada
				 */
				public void keyTyped ( KeyEvent e ) {
					OptionsText.onlyCharsNumbers( e.getKeyChar( ), e );
				}

				public void keyPressed ( KeyEvent e ) {}

				public void keyReleased ( KeyEvent e ) {}
			} );

			epassword = new JLabel( );
			Pdatos.add( epassword );
			epassword.setText( "Contraseña:" );
			epassword.setBounds( 41, 152, 77, 25 );

			password = new JPasswordField( );
			Pdatos.add( password );
			password.setBounds( 121, 152, 191, 25 );
			password.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );

			password.addKeyListener( new KeyListener( ) {

				/**
				 * Listener para escribir sólo caracteres permitidos en el JtextField
				 * invoca otro método statico que se encarga de revisar los caracteres.
				 * (non-Javadoc)
				 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
				 * validación de texto de entrada
				 */
				public void keyTyped ( KeyEvent e ) {
					OptionsText.onlyCharsNumbers( e.getKeyChar( ), e );
				}

				public void keyReleased ( KeyEvent e ) {}

				public void keyPressed ( KeyEvent arg0 ) {}
			} );

			epassword2 = new JLabel( );
			Pdatos.add( epassword2 );
			epassword2.setText( "Repita Contraseña:" );
			epassword2.setBounds( 6, 189, 121, 22 );

			password2 = new JPasswordField( );
			Pdatos.add( password2 );
			password2.setBounds( 131, 188, 181, 25 );
			password2.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );
			password2.addKeyListener( new KeyListener( ) {

				/**
				 * Listener para escribir sólo caracteres permitidos en el JtextField
				 * invoca otro método statico que se encarga de revisar los caracteres.
				 * (non-Javadoc)
				 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
				 * validación de texto de entrada
				 */
				public void keyTyped ( KeyEvent e ) {
					OptionsText.onlyCharsNumbers( e.getKeyChar( ), e );
				}

				public void keyReleased ( KeyEvent e ) {}

				public void keyPressed ( KeyEvent e ) {}
			} );

			/**
			 * Bloqueo CopyPaste en campos de usuario, y contraseñas
			 */
			OptionsText.disablePasteAndCopyAction( ( JComponent ) login );
			OptionsText.disablePasteAndCopyAction( ( JComponent ) password );
			OptionsText.disablePasteAndCopyAction( ( JComponent ) password2 );

			paterno.setDocument( new LenString( 20 ) );
			materno.setDocument( new LenString( 20 ) );
			nombre.setDocument( new LenString( 20 ) );
			login.setDocument( new LenString( 20 ) );
			password.setDocument( new LenString( 20 ) );
			password2.setDocument( new LenString( 20 ) );
			
			etipo = new JLabel( );
			Pdatos.add( etipo );
			etipo.setText( "Tipo Usuario:" );
			etipo.setBounds( 38, 223, 89, 25 );
			
			//String tipos;
			//tipos = tipo.getSelectedItem().toString();
			ComboBoxModel < String > tipoModel = new DefaultComboBoxModel < String >( new String [ ] {
			        "Administrador", "Empleado"
			} );

			tipo = new JComboBox < String >( );
			Pdatos.add( tipo );
			tipo.setModel( tipoModel );
			tipo.setBounds( 121, 220, 191, 27 );

			aceptar = new JButton( );
			Pdatos.add( aceptar );
			aceptar.setText( "Realizar Operación" );
			aceptar.setBounds( 41, 258, 271, 38 );
			//ImageIcon Imagenprocesar = new ImageIcon( getClass( ).getResource( "iconos/proceso.png" ) );
			//aceptar.setIcon( Imagenprocesar );
			
			
			aceptar.setFont( new java.awt.Font( "Segoe UI", 1, 12 ) );
			aceptar.addActionListener( new ActionListener( ) {

				/**
				 * Validación para comparación de contraseñas
				 * y para la información del usuario nuevo
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					String tu = new String( "" );
					String pass1 = new String( password.getPassword( ) );
					String pass2 = new String( password2.getPassword( ) );

					/**
					 * Sección para agregar el usuario
					 */
					if ( agregar.isSelected( ) ) {
							// verificación del tipo de usuario

									tu = "A";
									cone.agregarUsuario( paterno.getText( ), materno.getText( ), nombre.getText( ), login.getText( ), password.getPassword( ), tu );
									JOptionPane.showMessageDialog( null, "La inserción se realizó con éxito" );
									Limpiar( );

									// verficación si ya existe el usuario

									/*
									Consultor consultor = new Consultor(paterno.getText(), materno.getText(), nombre.getText(), login.getText(), password.getPassword(), tipo.getSelectedItem());
									try {
										// llamada al controlador para guardar el consultor
										int idConsultor = CRegConsultores.save(consultor);
										if (idConsultor > 0) {
											// si el resultado de guardar la unidad es mayor a 0 ,
											// significa que se guardo y recuperamos el ID_Unidad para
											// ser asignado, se agrega al modelo y se selecciona en la
											// tabla
											consultor.setIdconsultor(idConsultor);
											//modelConsultor.addConsultor(consultor);
											//tabUnidad.changeSelection(modelConsultor.getRowCount() - 1, 1, false, false);
										}
									} catch (NoSelectAttributeUnidad eNoSelect) {
										JOptionPane.showMessageDialog(null, eNoSelect.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
										log.warn(eNoSelect);
									} catch (MaxLength eLength) {
										JOptionPane.showMessageDialog(null, eLength.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
										log.warn(eLength);
									} catch (AttributeIsEmpty eAttributeEmpty) {
										JOptionPane.showMessageDialog(null, eAttributeEmpty.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
										log.warn(eAttributeEmpty);
									} catch (MySQLIntegrityConstraintViolationException eIntegridad) {
										if (eIntegridad.toString().contains("Duplicate")) {
											DuplicateKey eDuplicateKey = new DuplicateKey("Nombre Consultor", consultor.getNombre());
											JOptionPane.showMessageDialog(null, eDuplicateKey.getMessage());
											log.warn(eDuplicateKey, eIntegridad);
										}
									} catch (SQLException eSQL) {
										// mensaje = error de conexión a la base de datos
										ErrorConnectionDB eConnectionDB = new ErrorConnectionDB();
										log.error(eConnectionDB, eSQL);
										JOptionPane.showMessageDialog(null, eConnectionDB.getMessage());
									}
									
									
									*/
									
								
						
					} else if ( modificar.isSelected( ) ) {
						/**
						 * Sección para modificar la información
						 * de los datos del usuario
						 */
						if ( vacio( ) == false ) {
							if ( pass1.toString( ).equals( pass2.toString( ) ) == true ) {
								int res = JOptionPane.showConfirmDialog( null, "Deseas modificar el usuario", "Confirmación", JOptionPane.YES_NO_OPTION );
								if ( res == JOptionPane.YES_OPTION ) {
									if ( tipo.getSelectedIndex( ) == 0 ) {
										tu = "A";
									} else {
										tu = "U";
									}
									try {
										if ( cone.modificarUsuario( paterno.getText( ), materno.getText( ), nombre.getText( ), login.getText( ), password.getPassword( ), tu, String.valueOf( idconsultor ) ) ) {
											JOptionPane.showMessageDialog( null, "La Modificación se realizó con éxito" );
											Lconsultores = cone.GetConsultores( );
											llenarConsultores( );
											Limpiar( );
										}
									} catch ( HeadlessException e1 ) {
										JOptionPane.showMessageDialog( null, "Servidor fuera de servicio (Error al Modificar usuario) ", "Error", JOptionPane.ERROR_MESSAGE );
									}
								}
							} else {
								JOptionPane.showMessageDialog( null, "password no coincide", "verifica", JOptionPane.WARNING_MESSAGE );
							}
						}
					} else if ( eliminar.isSelected( ) ) {
						/**
						 * Sección para eliminar información
						 * de los usuarios
						 */
						if ( vacio( ) == false ) {
							if ( pass1.toString( ).equals( pass2.toString( ) ) == true ) {
								int res = JOptionPane.showConfirmDialog( null, "Deseas eliminar el usuario", "Confirmación", JOptionPane.YES_NO_OPTION );
								if ( res == JOptionPane.YES_OPTION ) {
									try {
										if ( cone.eliminarUsuario( String.valueOf( idconsultor ) ) == true ) {
											JOptionPane.showMessageDialog( null, "La eliminación se realizó con éxito" );
											Lconsultores = cone.GetConsultores( );
											llenarConsultores( );
											Limpiar( );
										} else {
											JOptionPane.showMessageDialog( null, "No se puede eliminar el consultor ", "Problema", JOptionPane.WARNING_MESSAGE );
										}
									} catch ( HeadlessException e1 ) {
										JOptionPane.showMessageDialog( null, "Error del servidor: (No se puede eliminar el consultor) ", "Error", JOptionPane.ERROR_MESSAGE );
									}// fin de try
								}// fin de option_yes
							}// fin de comparacion de cadenas
						}// fin de vacio en la comparcion con falso
					}// fin de eliminar
					else {
						JOptionPane.showMessageDialog( null, "Selecciona una Opción ha Realizar" );
					}

				}// fin de action performed
			} );// fin de action listener

			

		} catch ( Exception e ) {
			e.printStackTrace( );
		}
	}// fin de inicio de ventana

	/**
	 * Función Vacio() ->
	 * Verifica si uno o mas campos esta vacio o no
	 * JpasswordField..gettext Error ->> JpasswordField.getPassword
	 * 
	 * @return true si alguno esta vacio / false si todos tienen contenido
	 */
	public boolean vacio ( ) {
		String strPaterno = paterno.getText( ).trim( );
		String strMaterno = materno.getText( ).trim( );
		String strNombre = nombre.getText( ).trim( );
		String strLogin = login.getText( ).trim( );
		String strPass = new String( password.getPassword( ) ).trim( );
		String strPassConfirm = new String( password2.getPassword( ) ).trim( );
		if ( strPaterno.isEmpty( ) || strMaterno.isEmpty( ) || strNombre.isEmpty( ) || strLogin.isEmpty( ) || strPass.isEmpty( ) || strPassConfirm.isEmpty( ) ) {
			JOptionPane.showMessageDialog( null, "Uno o varios campos están vacíos", "Verifica", JOptionPane.WARNING_MESSAGE );
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Procedimiento llenarConsultores() ->
	 * LLena la lista de contultores que se muestra en el ComboBox de la GUI
	 * con el Formato Nombre(s), Apellido Paterno, Apellido Materno
	 */
	public void llenarConsultores ( ) {
		Cusuarios.removeAllItems( );
		Consultor con = new Consultor( );
		for ( int i = 0 ; i < Lconsultores.size( ) ; i++ ) {
			con = ( Consultor ) Lconsultores.get( i );
			Cusuarios.addItem( con.getNombre( ) + " " + con.getPaterno( ) + " " + con.getMaterno( ) );
		}
		if ( Cusuarios.getItemCount( ) > 0 ) {
			Cusuarios.setSelectedItem( 0 );
		}
	}

	/**
	 * Procedimiento Limpiar() ->
	 * Limpia los campos de la GUI
	 */
	public void Limpiar ( ) {
		paterno.setText( "" );
		materno.setText( "" );
		nombre.setText( "" );
		login.setText( "" );
		password.setText( "" );
		password2.setText( "" );
	}

	/**
	 * Procedimiento llenarTXT ->
	 * LLena los campos JtextField con la Informaciï¿½n del usuario seleccionado
	 * en el ComboBox
	 * Si encuentra un usuario de tipo Administrador cambia el tipo de
	 * selecciï¿½n en el comboBox
	 */
	public void llenarTXT ( ) {
		if ( ( Lconsultores != null ) && ( Cusuarios.getSelectedIndex( ) > -1 ) ) {
			Consultor con = new Consultor( );
			con = ( Consultor ) Lconsultores.get( Cusuarios.getSelectedIndex( ) );
			paterno.setText( con.getPaterno( ) );
			materno.setText( con.getMaterno( ) );
			nombre.setText( con.getNombre( ) );
			login.setText( con.getlogin( ) );
			if ( con.getTipousu( ).equals( "A" ) == true ) {
				tipo.setSelectedIndex( 0 );
			} else {
				tipo.setSelectedIndex( 1 );
			}
			password.setText( con.getPassword( ).toString( ) );
			password2.setText( con.getPassword( ).toString( ) );
			idconsultor = con.getIdconsultor( );
		}
	}
}
