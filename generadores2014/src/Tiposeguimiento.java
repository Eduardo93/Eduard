import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Esta clase no esta implementada en el sistema :(
 * @author Pablo Rivera
 * @colaboración luiiis lazaro
 *
 */
public class Tiposeguimiento extends javax.swing.JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JDesktopPane escritorio;
	private JPanel Panel;
	private JRadioButton anterior;
	private JRadioButton inicial;

	/**
	 * 
	 */
	public Tiposeguimiento ( ) {
		super( "Tipo Creacion", false, true, false, true );
		initGUI( );
	}

	/**
	 * 
	 */
	private void initGUI ( ) {
		try {
			this.setPreferredSize( new java.awt.Dimension( 312, 114 ) );
			this.setBounds( 0, 0, 312, 114 );
			setVisible( true );

			escritorio = new JDesktopPane( );
			getContentPane( ).add( escritorio, BorderLayout.CENTER );

			Panel = new JPanel( );
			escritorio.add( Panel, JLayeredPane.DEFAULT_LAYER );
			Panel.setBounds( 0, 0, 310, 89 );
			Panel.setLayout( null );

			inicial = new JRadioButton( );
			Panel.add( inicial );
			inicial.setText( "En base a la estimacion inicial" );
			inicial.setBounds( 12, 14, 231, 20 );
			inicial.addActionListener( new ActionListener( ) {
				/**
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					dispose( );
				}
			} );

			anterior = new JRadioButton( );
			Panel.add( anterior );
			anterior.setText( "En base a una estimacion anterior" );
			anterior.setBounds( 12, 43, 217, 20 );
			anterior.addActionListener( new ActionListener( ) {
				/**
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				public void actionPerformed ( ActionEvent e ) {
					dispose( );
				}
			} );
		} catch ( Exception e ) {
			e.printStackTrace( );
		}
	}

}
