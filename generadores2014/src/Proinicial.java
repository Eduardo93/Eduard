import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import ObjetosSerializables.Rgenerador;

/**
 * Clase para mostrar el detalle del aspecto agregado...
 * aspectos contemplados en la estimación de los números generadores
 * @author Pablo Rivera
 * @colaboración luiiis lazaro
 *
 */
public class Proinicial extends javax.swing.JInternalFrame {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private JDesktopPane escritorio;
	private JPanel panel;
	private JTextField ancho;
	private JLabel jLabel14;
	private JTextField largo;
	private JLabel jLabel13;
	private JTextField alto;
	private JLabel jLabel12;
	private JPanel Paneldimenciones;
	private JButton salir;
	private JTextField importe;
	private JLabel jLabel11;
	private JTextField costo;
	private JLabel jLabel10;
	private JTextField piezas;
	private JLabel jLabel9;
	private JTextField cantidad;
	private JLabel jLabel8;
	private JPanel pcantidades;
	private JTextField z;
	private JTextField y;
	private JTextField x;
	private JLabel jLabel7;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JPanel Panelubicaciones;
	private JTextArea descripcion;
	private JLabel jLabel4;
	private JTextField unidad;
	private JLabel jLabel3;
	private JTextField clave;
	private JLabel jLabel2;
	private JPanel Paneldatos;
	private JLabel jLabel1;

	/**
	 * Constructor de la clase
	 * @param ob --- referencia del aspecto que se va a consultar para su detalle 
	 */
	public Proinicial ( Rgenerador ob ) {
		super( "Datos iniciales", false, true, false, true );
		initGUI( ob );
	}

	/**
	 * inicialización de los componentes gráficos 
	 * @param ob --- referencia del aspecto que se va a consultar para su detalle
	 * se llenan los campos con la información que se obtiene del parámetro...
	 */
	private void initGUI ( Rgenerador ob ) {
		try {
			this.setPreferredSize( new java.awt.Dimension( 487, 406 ) );
			this.setBounds( 0, 0, 487, 406 );
			setVisible( true );
			
			escritorio = new JDesktopPane( );
			getContentPane( ).add( escritorio, BorderLayout.CENTER );
			
			panel = new JPanel( );
			escritorio.add( panel, JLayeredPane.DEFAULT_LAYER );
			panel.setBounds( 0, 0, 485, 381 );
			panel.setLayout( null );
			
			jLabel1 = new JLabel( );
			panel.add( jLabel1 );
			jLabel1.setText( "Datos del concepto" );
			jLabel1.setBounds( 165, 12, 146, 16 );
			jLabel1.setForeground( new java.awt.Color( 255, 0, 0 ) );
			jLabel1.setFont( new java.awt.Font( "Segoe UI", 1, 14 ) );
			
			Paneldatos = new JPanel( );
			panel.add( Paneldatos );
			Paneldatos.setBounds( 17, 38, 452, 101 );
			Paneldatos.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );
			Paneldatos.setLayout( null );
			
			jLabel2 = new JLabel( );
			Paneldatos.add( jLabel2 );
			jLabel2.setText( "Clave:" );
			jLabel2.setBounds( 10, 11, 42, 16 );
			
			/**
			 * Visualización de la clave
			 */
			clave = new JTextField( );
			clave.setText( ob.getClave( ) );
			Paneldatos.add( clave );
			clave.setBounds( 48, 9, 176, 23 );
			clave.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );
			clave.setEditable( false );
			
			jLabel3 = new JLabel( );
			Paneldatos.add( jLabel3 );
			jLabel3.setText( "Unidad:" );
			jLabel3.setBounds( 240, 10, 46, 16 );
			
			/**
			 * Visualización de la unidad 
			 */
			unidad = new JTextField( );
			unidad.setText( ob.getUnidad( ) );
			Paneldatos.add( unidad );
			unidad.setBounds( 298, 10, 128, 23 );
			unidad.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );
			unidad.setEditable( false );
		
			jLabel4 = new JLabel( );
			Paneldatos.add( jLabel4 );
			jLabel4.setText( "Descripción:" );
			jLabel4.setBounds( 8, 44, 70, 16 );
			
			/**
			 * Visualización de la descripción  
			 */
			descripcion = new JTextArea( );
			descripcion.setText( ob.getDescripcion( ) );
			descripcion.setLineWrap( true );
			Paneldatos.add( descripcion );
			descripcion.setBounds( 78, 45, 348, 47 );
			descripcion.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );
			descripcion.setEditable( false );
		
			Panelubicaciones = new JPanel( );
			panel.add( Panelubicaciones );
			Panelubicaciones.setBounds( 17, 145, 452, 48 );
			Panelubicaciones.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );
			Panelubicaciones.setLayout( null );
		
			jLabel5 = new JLabel( );
			Panelubicaciones.add( jLabel5 );
			jLabel5.setText( "Ubicacion X:" );
			jLabel5.setBounds( 13, 18, 82, 16 );

			jLabel6 = new JLabel( );
			Panelubicaciones.add( jLabel6 );
			jLabel6.setText( "Ubicación Y:" );
			jLabel6.setBounds( 156, 18, 79, 16 );

			jLabel7 = new JLabel( );
			Panelubicaciones.add( jLabel7 );
			jLabel7.setText( "Ubicación Z:" );
			jLabel7.setBounds( 301, 18, 66, 16 );
			
			/**
			 * Visualización del eje en x 
			 */
			x = new JTextField( );
			x.setText( ob.getX( ) );
			Panelubicaciones.add( x );
			x.setBounds( 82, 14, 47, 23 );
			x.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );
			x.setEditable( false );
			
			/**
			 * Visualización del eje en y 
			 */
			y = new JTextField( );
			y.setText( ob.getY( ) );
			Panelubicaciones.add( y );
			y.setBounds( 235, 15, 48, 23 );
			y.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );
			y.setEditable( false );
			
			/**
			 * Visualización del eje en z 
			 */
			z = new JTextField( );
			z.setText( ob.getZ( ) );
			Panelubicaciones.add( z );
			z.setBounds( 372, 15, 47, 23 );
			z.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );
			z.setEditable( false );
			
			pcantidades = new JPanel( );
			panel.add( pcantidades );
			pcantidades.setBounds( 17, 258, 452, 75 );
			pcantidades.setLayout( null );
			pcantidades.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );
			
			jLabel8 = new JLabel( );
			pcantidades.add( jLabel8 );
			jLabel8.setText( "Cantidad:" );
			jLabel8.setBounds( 44, 13, 66, 16 );
			
			/**
			 * Visualización de la cantidad  
			 */
			cantidad = new JTextField( );
			cantidad.setText( ob.getCantidad( ) );
			pcantidades.add( cantidad );
			cantidad.setBounds( 48, 35, 70, 23 );
			cantidad.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );
			cantidad.setEditable( false );
		
			jLabel9 = new JLabel( );
			pcantidades.add( jLabel9 );
			jLabel9.setText( "piezas:" );
			jLabel9.setBounds( 144, 13, 40, 16 );
		
			/**
			 * Visualización del número de piezas  
			 */
			piezas = new JTextField( );
			piezas.setText( ob.getPiezas( ) );
			pcantidades.add( piezas );
			piezas.setBounds( 144, 35, 76, 23 );
			piezas.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );
			piezas.setEditable( false );

			jLabel10 = new JLabel( );
			pcantidades.add( jLabel10 );
			jLabel10.setText( "Costo:" );
			jLabel10.setBounds( 247, 13, 40, 16 );
			
			/**
			 * Visualización del costo del aspecto 
			 */
			costo = new JTextField( );
			costo.setText( ob.getCosto( ) );
			pcantidades.add( costo );
			costo.setBounds( 247, 35, 73, 23 );
			costo.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );
			costo.setEditable( false );

			jLabel11 = new JLabel( );
			pcantidades.add( jLabel11 );
			jLabel11.setText( "Importe:" );
			jLabel11.setBounds( 351, 13, 45, 16 );
			
			/**
			 * Visualización del importe del aspecto seleccionado 
			 */
			importe = new JTextField( );
			importe.setText( ob.getImporte( ) );
			pcantidades.add( importe );
			importe.setBounds( 351, 35, 65, 23 );
			importe.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );
			importe.setEditable( false );

			ImageIcon Imagensalir = new ImageIcon( getClass( ).getResource( "iconos/salir.png" ) );
			salir = new JButton( );
			panel.add( salir );
			salir.setText( "salir" );
			salir.setBounds( 207, 339, 98, 31 );
			salir.setFont( new java.awt.Font( "Segoe UI", 1, 12 ) );
			salir.setIcon( Imagensalir );
			salir.addActionListener( new ActionListener( ) {
				public void actionPerformed ( ActionEvent e ) {
					dispose( );
				}
			} );
			
			Paneldimenciones = new JPanel( );
			panel.add( Paneldimenciones );
			Paneldimenciones.setBounds( 17, 199, 452, 53 );
			Paneldimenciones.setBorder( new LineBorder( new java.awt.Color( 0, 0, 0 ), 1, false ) );
			Paneldimenciones.setLayout( null );
			
			jLabel12 = new JLabel( );
			Paneldimenciones.add( jLabel12 );
			jLabel12.setText( "Alto:" );
			jLabel12.setBounds( 13, 18, 54, 16 );
		
			/**
			 * Visualización de la medida altura  
			 */
			alto = new JTextField( );
			Paneldimenciones.add( alto );
			alto.setBounds( 67, 15, 58, 23 );
			alto.setEditable( false );
			alto.setText( ob.getAlto( ) );
		
			jLabel13 = new JLabel( );
			Paneldimenciones.add( jLabel13 );
			jLabel13.setText( "Largo:" );
			jLabel13.setBounds( 164, 18, 43, 16 );
		
			/**
			 * Visualización de la medida de largo 
			 */
			largo = new JTextField( );
			Paneldimenciones.add( largo );
			largo.setBounds( 207, 15, 75, 23 );
			largo.setEditable( false );
			largo.setText( ob.getLargo( ) );
		
			/**
			 * Visualización de la medida de ancho 
			 */
			jLabel14 = new JLabel( );
			Paneldimenciones.add( jLabel14 );
			jLabel14.setText( "ancho:" );
			jLabel14.setBounds( 294, 18, 36, 16 );
			
			ancho = new JTextField( );
			Paneldimenciones.add( ancho );
			ancho.setBounds( 337, 15, 81, 23 );
			ancho.setEditable( false );
			ancho.setText( ob.getAncho( ) );
		} catch ( Exception e ) {
			e.printStackTrace( );
		}
	}
}
