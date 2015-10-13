import java.awt.BorderLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;

/**
 * clase para abrir y visualizar un archivo de imagen 
 * @author Rivera Pablo 
 * @colaboración luiiis lazaro
 *
 */
public class Lplano extends javax.swing.JInternalFrame {

	/**
     * escritorio --- panel del JInternalFrame
     * panelFoto --- panel para cargar la imagen
     */
    private static final long serialVersionUID = 1L;
	private JDesktopPane escritorio;
	private JScrollPane PanelFoto;

	/**
	 * 
	 * Constructor de la clase, recibe la ruta de la imagen 
	 * @param ruta string --- ruta de la imagen a cargar en el panel 
	 */
	public Lplano ( String ruta ) {
		super( "Plano", false, true, false, true );
		initGUI( ruta );
	}

	/**
	 * inicialización de componentes gráficos, sólo carga la foto  
	 * @param ruta
	 */
	private void initGUI ( String ruta ) {
		try {
			this.setPreferredSize( new java.awt.Dimension( 551, 427 ) );
			this.setBounds( 0, 0, 551, 427 );
			setVisible( true );
			
			escritorio = new JDesktopPane( );
			getContentPane( ).add( escritorio, BorderLayout.CENTER );
			
			PanelFoto = new JScrollPane( );
			escritorio.add( PanelFoto, JLayeredPane.DEFAULT_LAYER );
			PanelFoto.setBounds( 0, 0, 543, 402 );
			JLabel etiqueta = new JLabel( );
			Icon imagen = new ImageIcon( ruta );
			etiqueta.setIcon( imagen );
			PanelFoto.setViewportView( etiqueta );
		} catch ( Exception e ) {
			e.printStackTrace( );
		}
	}
}
