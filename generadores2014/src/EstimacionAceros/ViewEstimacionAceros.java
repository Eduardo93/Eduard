package EstimacionAceros;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import MetodosRemotos.Metodos;

public class ViewEstimacionAceros extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtLong_1;
	private JTextField txtLong_2;
	private JTextField txtLong_4;
	private JTextField txtLong_9;
	private JTextField txtLong_3;
	private JTextField txtLong_5;
	private JTextField txtLong_6;
	private JTextField txtLong_7;
	private JTextField txtLong_8;
	private JTextField txtKilo_1;
	private JTextField txtKilo_2;
	private JTextField txtKilo_4;
	private JTextField txtKilo_3;
	private JTextField txtKilo_5;
	private JTextField txtKilo_6;
	private JTextField txtKilo_7;
	private JTextField txtKilo_8;
	private JTextField txtKilo_9;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTable tabAspectosBD;
	private JTable table_2;
	private Metodos conn; 

	/**
	 * @return the conn
	 */
	public Metodos getConn() {
		return conn;
	}

	/**
	 * @param conn the conn to set
	 */
	public void setConn(Metodos conn) {
		this.conn = conn;
	}

	/**
	 * Create the frame.
	 */
	public ViewEstimacionAceros(Metodos conn) {
		super( "Generador de N\u00FAmeros de Aceros", false, true, false, true );
		setVisible(true);
		setClosable(true);
		setBounds(100, 100, 1000, 722);
		getContentPane().setLayout(null);
		
		this.setConn(conn);
		
		JScrollPane scrollAspectosBD = new JScrollPane();
		scrollAspectosBD.setBounds(10, 61, 972, 186);
		getContentPane().add(scrollAspectosBD);
		
		tabAspectosBD = new JTable();
		tabAspectosBD.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Selecci\u00F3n", "Descripci\u00F3n"
			}
		));
		scrollAspectosBD.setViewportView(tabAspectosBD);
		
		JScrollPane scrollAspectosGenerador = new JScrollPane();
		scrollAspectosGenerador.setBounds(10, 284, 972, 264);
		getContentPane().add(scrollAspectosGenerador);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollAspectosGenerador.setViewportView(table_2);
		
		JLabel lblDesgloseDeConceptos = new JLabel("Desglose de Conceptos Agregados a la estimaci\u00F3n");
		lblDesgloseDeConceptos.setBounds(10, 258, 304, 14);
		getContentPane().add(lblDesgloseDeConceptos);
		
		JLabel lblConceptos = new JLabel("Desglose de Conceptos de Acero");
		lblConceptos.setBounds(10, 36, 200, 14);
		getContentPane().add(lblConceptos);
		
		JLabel lblConsultor = new JLabel("Consultor:");
		lblConsultor.setBounds(10, 11, 81, 14);
		getContentPane().add(lblConsultor);
		
		JLabel lblLongitudTotal = new JLabel("Longitud Total:");
		lblLongitudTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLongitudTotal.setBounds(312, 560, 81, 14);
		getContentPane().add(lblLongitudTotal);
		
		JLabel lblKg = new JLabel("KG de Varilla:");
		lblKg.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKg.setBounds(312, 588, 81, 14);
		getContentPane().add(lblKg);
		
		JLabel lblPesoTotal = new JLabel("Peso Total:");
		lblPesoTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPesoTotal.setBounds(322, 614, 71, 14);
		getContentPane().add(lblPesoTotal);
		
		JLabel lblExportarEstimacin = new JLabel("Exportar Estimaci\u00F3n:");
		lblExportarEstimacin.setBounds(248, 648, 119, 14);
		getContentPane().add(lblExportarEstimacin);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Excel ", "PDF"}));
		comboBox.setBounds(368, 645, 142, 20);
		getContentPane().add(comboBox);
		
		JButton btnGuardarSinExportar = new JButton("Guardar sin Exportar ");
		btnGuardarSinExportar.setBounds(522, 640, 158, 30);
		getContentPane().add(btnGuardarSinExportar);
		
		txtLong_1 = new JTextField();
		txtLong_1.setEditable(false);
		txtLong_1.setBounds(397, 549, 65, 30);
		getContentPane().add(txtLong_1);
		txtLong_1.setColumns(10);
		
		txtLong_2 = new JTextField();
		txtLong_2.setEditable(false);
		txtLong_2.setBounds(462, 549, 66, 30);
		getContentPane().add(txtLong_2);
		txtLong_2.setColumns(10);
		
		txtLong_4 = new JTextField();
		txtLong_4.setEditable(false);
		txtLong_4.setBounds(592, 549, 65, 30);
		getContentPane().add(txtLong_4);
		txtLong_4.setColumns(10);
		
		txtLong_9 = new JTextField();
		txtLong_9.setEditable(false);
		txtLong_9.setBounds(917, 549, 65, 30);
		getContentPane().add(txtLong_9);
		txtLong_9.setColumns(10);
		
		txtLong_3 = new JTextField();
		txtLong_3.setEditable(false);
		txtLong_3.setBounds(527, 549, 65, 30);
		getContentPane().add(txtLong_3);
		txtLong_3.setColumns(10);
		
		txtLong_5 = new JTextField();
		txtLong_5.setEditable(false);
		txtLong_5.setBounds(657, 549, 65, 30);
		getContentPane().add(txtLong_5);
		txtLong_5.setColumns(10);
		
		txtLong_6 = new JTextField();
		txtLong_6.setEditable(false);
		txtLong_6.setBounds(722, 549, 65, 30);
		getContentPane().add(txtLong_6);
		txtLong_6.setColumns(10);
		
		txtLong_7 = new JTextField();
		txtLong_7.setEditable(false);
		txtLong_7.setBounds(787, 549, 65, 30);
		getContentPane().add(txtLong_7);
		txtLong_7.setColumns(10);
		
		txtLong_8 = new JTextField();
		txtLong_8.setEditable(false);
		txtLong_8.setBounds(852, 549, 65, 30);
		getContentPane().add(txtLong_8);
		txtLong_8.setColumns(10);
		
		txtKilo_1 = new JTextField();
		txtKilo_1.setEditable(false);
		txtKilo_1.setColumns(10);
		txtKilo_1.setBounds(397, 577, 65, 30);
		getContentPane().add(txtKilo_1);
		
		txtKilo_2 = new JTextField();
		txtKilo_2.setEditable(false);
		txtKilo_2.setColumns(10);
		txtKilo_2.setBounds(462, 577, 66, 30);
		getContentPane().add(txtKilo_2);
		
		txtKilo_4 = new JTextField();
		txtKilo_4.setEditable(false);
		txtKilo_4.setColumns(10);
		txtKilo_4.setBounds(592, 577, 65, 30);
		getContentPane().add(txtKilo_4);
		
		txtKilo_3 = new JTextField();
		txtKilo_3.setEditable(false);
		txtKilo_3.setColumns(10);
		txtKilo_3.setBounds(527, 577, 65, 30);
		getContentPane().add(txtKilo_3);
		
		txtKilo_5 = new JTextField();
		txtKilo_5.setEditable(false);
		txtKilo_5.setColumns(10);
		txtKilo_5.setBounds(657, 577, 65, 30);
		getContentPane().add(txtKilo_5);
		
		txtKilo_6 = new JTextField();
		txtKilo_6.setEditable(false);
		txtKilo_6.setColumns(10);
		txtKilo_6.setBounds(722, 577, 65, 30);
		getContentPane().add(txtKilo_6);
		
		txtKilo_7 = new JTextField();
		txtKilo_7.setEditable(false);
		txtKilo_7.setColumns(10);
		txtKilo_7.setBounds(787, 577, 65, 30);
		getContentPane().add(txtKilo_7);
		
		txtKilo_8 = new JTextField();
		txtKilo_8.setEditable(false);
		txtKilo_8.setColumns(10);
		txtKilo_8.setBounds(852, 577, 65, 30);
		getContentPane().add(txtKilo_8);
		
		txtKilo_9 = new JTextField();
		txtKilo_9.setEditable(false);
		txtKilo_9.setColumns(10);
		txtKilo_9.setBounds(917, 577, 65, 30);
		getContentPane().add(txtKilo_9);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(397, 606, 65, 30);
		getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(462, 606, 66, 30);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(527, 606, 65, 30);
		getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(592, 606, 65, 30);
		getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(657, 606, 65, 30);
		getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(722, 606, 65, 30);
		getContentPane().add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(787, 606, 65, 30);
		getContentPane().add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(852, 606, 65, 30);
		getContentPane().add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBounds(917, 606, 65, 30);
		getContentPane().add(textField_8);

	}
}
