import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
// import java.rmi.RemoteException;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Excel.Reportefrente;
import MetodosRemotos.Metodos;
import ObjetosSerializables.Rgenerador;

/**
 * clase para seleccionar la fecha de los periodos donde se desea exportar las
 * estimaciones
 * 
 * @author Rivera Pablo
 * @colaboración luiiis lazaro
 *
 */
public class Periodos extends javax.swing.JInternalFrame {

	/**
     * escritorio --- panel principal de la ventana
     * pmain --- panel para la informacion de la fecha
     * combo fechas --- combo para seleccionar las fechas de las estimaciones del proyecto
     * aceptar --- botón para guardar el reporte hecho por fechas
     * lblrealizada --- etiqueta para mostrar el titulo de periodos
     * lblinicial --- etiqueta para mostrar el titulo de las fechas 
     * fechai --- txt para establecer la fecha de estimaciones 
     */
	private static final long serialVersionUID = 1L;
	private JDesktopPane escritorio;
	private JPanel pMain;
	private JComboBox<String> combofechas;
	private JButton aceptar;
	private JLabel lblRealizada;
	private JLabel lblinicial;
	private JTextField fechai;

	/**
	 * constructor de la clase
	 * 
	 * @param cone --- metodos para la manipulación de la base de datos
	 * @param fechaini --- fecha inicial para crear el reporte
	 * @param inicial --- lista de aspectos inciales en la estimacion
	 * @param idestimacion --- identificador de estimación
	 */
	public Periodos(Metodos cone, String fechaini, LinkedList<Rgenerador> inicial, String idestimacion) {
		super("Reporte periodos", false, true, false, true);
		initGUI(cone, fechaini, inicial, idestimacion);
	}

	/**
	 * inicialización de los componentes gráficos.
	 * @param cone --- metodos para la manipulación de la base de datos
	 * @param fechaini --- fecha inicial para crear el reporte
	 * @param inicial --- lista de aspectos inciales en la estimacion
	 * @param idestimacion --- identificador de estimación
	 */
	private void initGUI(final Metodos cone, String fechaini, final LinkedList<Rgenerador> inicial, final String idestimacion) {
		try {
			this.setPreferredSize(new java.awt.Dimension(343, 200));
			this.setBounds(0, 0, 343, 200);
			setVisible(true);

			escritorio = new JDesktopPane();
			getContentPane().add(escritorio, BorderLayout.CENTER);

			pMain = new JPanel();
			escritorio.add(pMain, JLayeredPane.DEFAULT_LAYER);
			pMain.setBounds(0, 0, 341, 175);
			pMain.setLayout(null);

			lblinicial = new JLabel();
			pMain.add(lblinicial);
			lblinicial.setText("Estimacion inicial:");
			lblinicial.setBounds(17, 32, 138, 16);

			lblRealizada = new JLabel();
			pMain.add(lblRealizada);
			lblRealizada.setText("Estimaciones realizadas:");
			lblRealizada.setBounds(18, 67, 165, 16);

			combofechas = new JComboBox<String>();
			pMain.add(combofechas);
			combofechas.setBounds(18, 89, 165, 23);
			//llenar la lista de fechas de estimaciones creadas
			LinkedList<String> fechas = cone.sacarfechas(idestimacion);
			for (int i = 0; i < fechas.size(); i++) {
				combofechas.addItem(fechas.get(i));
			}

			fechai = new JTextField();
			pMain.add(fechai);
			fechai.setBounds(125, 29, 111, 26);
			fechai.setText(fechaini);
			fechai.setEditable(false);

			aceptar = new JButton();
			pMain.add(aceptar);
			aceptar.setText("Aceptar");
			aceptar.setBounds(109, 141, 79, 23);
			aceptar.addActionListener(new ActionListener() {
				/**
				 * opción para generar el reporte de las fechas de estimación seleccionada
				 * (non-Javadoc)
				 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
				 */
				@SuppressWarnings("unused")
				public void actionPerformed(ActionEvent e) {
					JFileChooser navega = new JFileChooser();
					String path = "";
					int estado = navega.showSaveDialog(pMain);
					navega.setDialogTitle("Salvar reporte");
					if (estado == JFileChooser.APPROVE_OPTION) {
						path = navega.getSelectedFile().getAbsolutePath();
						path += ".xls";
						if (path != null && path.equals("") == false) {
							Reportefrente repor = new Reportefrente(inicial, cone.reporteperiodo(idestimacion, fechai.getText(), String.valueOf(combofechas.getSelectedItem())), path);
							try {
								File file = new File(path);
								Desktop.getDesktop().open(file);
							} catch (IOException e1) {
								JOptionPane.showMessageDialog(null, "Error al lanzar archivo Execel");
							}
						}
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
