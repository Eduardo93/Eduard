package ImportarExportar.View;

import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.AbstractButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import ImportarExportar.Controller.CExportarConceptos;
import Model.Entity.AspectoSelection;
import Model.Entity.ConceptoSelection;
import Model.Entity.PartidaSelection;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JCheckBox;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;

import com.itextpdf.text.DocumentException;

/**
 * Clase para visualizar la ventana de Exportación de Catálogo
 * 
 * @author Luis Ange Hernández Lázaro
 *
 */
public class VExportarConceptos extends JInternalFrame {
	/**
	 * Variables de componentes dentro de la ventana de Exportación de Conceptos
	 */
	private static final Logger log = Logger.getLogger(VExportarConceptos.class);
	private static final long serialVersionUID = 1L;

	private JButton btnExportarSeleccionadosPDF;
	private JButton btnExportarSeleccionadosExcel;

	private JScrollPane scrollPartidas;
	private JScrollPane scrollConceptos;
	private JScrollPane scrollAspectos;

	private JTable tabPartidas;
	private TableModelPartidaSelection modelPartidaSel;
	private LinkedList<PartidaSelection> listPartidasExport;

	private JTable tabConceptos;
	private TableModelConceptoSelection modelConceptoSel;

	private JTable tabAspectos;
	private TableModelDesgloseAspectoSelection modelAspectosSel;

	private JCheckBox checkValueDesgconcp;
	private JCheckBox checkValueConcepos;
	private JCheckBox checkValuePartidas;
	private JButton btnNewButton;

	/**
	 * Create the frame.
	 */
	public VExportarConceptos() {
		super("Exportar Catálogo de Conceptos", false, true, false, true);
		setClosable(true);
		setVisible(true);
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		this.requestFocus();

		/**
		 * Sección de Botones
		 */
		btnExportarSeleccionadosPDF = new JButton("Exportar Seleccionados a PDF");
		btnExportarSeleccionadosPDF.addActionListener(new ActionListener() {
			/**
			 * llamada al método para generar el archivo en pdf
			 */
			public void actionPerformed(ActionEvent arg0) {
				exportPDF();
			}
		});
		btnExportarSeleccionadosPDF.setBounds(360, 532, 201, 28);
		getContentPane().add(btnExportarSeleccionadosPDF);

		btnExportarSeleccionadosExcel = new JButton("Exportar Seleccionados a Excel");
		btnExportarSeleccionadosExcel.addActionListener(new ActionListener() {
			/**
			 * llamada al método para genrerar el archivo en excel
			 */
			public void actionPerformed(ActionEvent arg0) {
				exportExcel();
			}
		});
		btnExportarSeleccionadosExcel.setBounds(97, 532, 201, 28);
		getContentPane().add(btnExportarSeleccionadosExcel);

		/**
		 * Sección de Tablas, acompañadas de su Scroll
		 */
		scrollPartidas = new JScrollPane();
		scrollPartidas.setBounds(4, 20, 370, 187);
		getContentPane().add(scrollPartidas);
		modelPartidaSel = new TableModelPartidaSelection();
		tabPartidas = new JTable(modelPartidaSel);
		tabPartidas.addMouseListener(new MouseAdapter() {
			@Override
			/**
			 * verificación de la primera columna y asignación del contenido de conceptos dependiendo de la partida seleccionada 
			 */
			public void mouseClicked(MouseEvent arg0) {
				// si es la primera columna a que recibe el clic, se actualiza
				// la información de la partida para asignar los nuevos valores.
				// (true o false)
				if (tabPartidas.getColumnModel().getSelectionModel().getLeadSelectionIndex() == 0) {
					listPartidasExport.get(tabPartidas.getSelectionModel().getLeadSelectionIndex()).setSelect((boolean) tabPartidas.getValueAt(tabPartidas.getSelectionModel().getLeadSelectionIndex(), 0));
					// actualización de la infomación en el contenido de la
					// lista
					setValueConceptos(tabPartidas.getSelectionModel().getLeadSelectionIndex());
				}
				// se llena la información del modelo de la tabla de conceptos
				fillTableModelConcepto(tabPartidas.getSelectionModel().getLeadSelectionIndex());
			}
		});
		scrollPartidas.setViewportView(tabPartidas);

		scrollConceptos = new JScrollPane();
		scrollConceptos.setBounds(386, 20, 488, 187);
		getContentPane().add(scrollConceptos);
		modelConceptoSel = new TableModelConceptoSelection();
		tabConceptos = new JTable(modelConceptoSel);
		tabConceptos.addMouseListener(new MouseAdapter() {
			@Override
			/**
			 * verficacion de la primera columna y asignación del contenido de desgloses dependiendo del concepto seleccionado
			 */
			public void mouseClicked(MouseEvent arg0) {
				// si es la primera columna la que recibe el clic, se actualiza
				// la información de los desgloses para asignar los nuevos
				// valores (true o false)
				if (tabConceptos.getColumnModel().getSelectionModel().getLeadSelectionIndex() == 0) {
					listPartidasExport.get(tabPartidas.getSelectionModel().getLeadSelectionIndex()).getSubConcepto(tabConceptos.getSelectionModel().getLeadSelectionIndex()).setSelect((boolean) tabConceptos.getValueAt(tabConceptos.getSelectionModel().getLeadSelectionIndex(), 0));
					// actualización del modelo de la tabla de desgloses de
					// concepto
					setValueAspectos(listPartidasExport.get(tabPartidas.getSelectionModel().getLeadSelectionIndex()).getSubConcepto(tabConceptos.getSelectionModel().getLeadSelectionIndex()));
					tabPartidas.setValueAt(true, tabPartidas.getSelectionModel().getLeadSelectionIndex(), 0);
					// seleccion de la partida en true
					listPartidasExport.get(tabPartidas.getSelectionModel().getLeadSelectionIndex()).setSelect(true);
				}
				// se llena la información del modelo de la tabla de desgloses
				// de concepto
				fillTableModelAspectos(tabPartidas.getSelectionModel().getLeadSelectionIndex(), tabConceptos.getSelectionModel().getLeadSelectionIndex());
			}
		});
		scrollConceptos.setViewportView(tabConceptos);

		scrollAspectos = new JScrollPane();
		scrollAspectos.setBounds(6, 219, 868, 308);
		getContentPane().add(scrollAspectos);
		modelAspectosSel = new TableModelDesgloseAspectoSelection();
		tabAspectos = new JTable(modelAspectosSel);
		tabAspectos.addMouseListener(new MouseAdapter() {
			@Override
			/**
			 * método para asignar el valor al desglose de concepto seleccionado
			 */
			public void mouseClicked(MouseEvent e) {
				if (tabAspectos.getColumnModel().getSelectionModel().getLeadSelectionIndex() == 0) {
					setValueAspecto();
				}
			}
		});
		scrollAspectos.setViewportView(tabAspectos);

		checkValuePartidas = new JCheckBox("Seleccionar Todas las Partidas");
		checkValuePartidas.addActionListener(new ActionListener() {
			/**
			 * método para marcar o desmarcar todas las partidas
			 */
			public void actionPerformed(ActionEvent e) {
				// llamada al método para marcar todas las partidas
				checkAll(((AbstractButton) e.getSource()).getModel().isSelected());
				// llamada al método para marcar las partidas en el modelo
				fillTableModelPartida();
			}
		});
		checkValuePartidas.setSelected(true);
		checkValuePartidas.setBounds(17, 0, 357, 18);
		getContentPane().add(checkValuePartidas);

		checkValueConcepos = new JCheckBox("Seleccionar Todos los Conceptos");
		checkValueConcepos.addActionListener(new ActionListener() {
			/**
			 * método para marcar o desmarcar todos los conceptos de una partida
			 */
			public void actionPerformed(ActionEvent e) {
				// llamada al método para marcar todos los conceptos
				checkConceptos(tabPartidas.getSelectionModel().getLeadSelectionIndex(), ((AbstractButton) e.getSource()).getModel().isSelected());
				// llamada al método para marcar los conceptos en el modelo
				fillTableModelConcepto(tabPartidas.getSelectionModel().getLeadSelectionIndex());
			}
		});
		checkValueConcepos.setSelected(true);
		checkValueConcepos.setBounds(404, 0, 379, 18);
		getContentPane().add(checkValueConcepos);

		checkValueDesgconcp = new JCheckBox("Seleccionar Todos los Desgloses de Conceptos");
		checkValueDesgconcp.setSelected(true);
		checkValueDesgconcp.addActionListener(new ActionListener() {
			/**
			 * método para marcar o desmarcar todos los desgloses de concepto de
			 * un concepto seleccionado
			 */
			public void actionPerformed(ActionEvent e) {
				// llamada al método para marcar todos los conceptos
				checkConceptos(tabPartidas.getSelectionModel().getLeadSelectionIndex(), ((AbstractButton) e.getSource()).getModel().isSelected());
				// llamada al método para marcar los desgloses de concepto en el
				// modelo de la tabla
				fillTableModelAspectos(tabPartidas.getSelectionModel().getLeadSelectionIndex(), tabConceptos.getSelectionModel().getLeadSelectionIndex());
			}
		});
		checkValueDesgconcp.setBounds(14, 204, 338, 18);
		getContentPane().add(checkValueDesgconcp);

		btnNewButton = new JButton("Reiniciar Ventana");
		btnNewButton.addActionListener(new ActionListener() {
			/**
			 * llamada al método para reiniciar la exportación de catálogos
			 */
			public void actionPerformed(ActionEvent arg0) {
				displayInformation();
				checkValuePartidas.setSelected(true);
				checkValueConcepos.setSelected(true);
				checkValueDesgconcp.setSelected(true);
			}
		});
		btnNewButton.setBounds(624, 532, 184, 28);
		getContentPane().add(btnNewButton);

		// diseño de la tabla
		designTables();

		// despliege de la información
		displayInformation();

	}

	/**
	 * obtener información de la base de datos para mostrar en pantalla al
	 * usuario
	 */
	public void displayInformation() {
		// obtener partidas conceptos y aspectos
		try {
			// recuperación de las partidas, conceptos y desgloses existentes en
			// la base de datos
			listPartidasExport = CExportarConceptos.findPartidaSelection();
		} catch (SQLException e) {
			// si no se puede recuperar la información , se registra el error
			log.error(e);
			showMessageErrorConnectDB();
		}
		// llenar el modelo de la tabla de partidas
		fillTableModelPartida();
	}

	/**
	 * método para llenar el modelo de la tabla de partidas
	 */
	public void fillTableModelPartida() {
		// limpiar el contenido del modelo
		modelPartidaSel.clear();
		// para cada partida encontrada en la lista, agregar al modelo
		for (PartidaSelection partidaSelection : listPartidasExport) {
			modelPartidaSel.addPartida(partidaSelection);
		}
		tabPartidas.changeSelection(0, 1, false, false);
		// llamada al método para llenar los conceptos
		fillTableModelConcepto(tabPartidas.getSelectionModel().getLeadSelectionIndex());
	}

	/**
	 * método para llenar el modelo de los concepto
	 * 
	 * @param index
	 *            --- identificador de la partida para llenar sus conceptos
	 */
	public void fillTableModelConcepto(int index) {
		// limpiar el contenido del modelo
		modelConceptoSel.clear();
		tabConceptos.repaint();
		// para cada concepto de la lista, agregarlo al modelo
		for (ConceptoSelection conceptoSelection : listPartidasExport.get(index).getSubsConceptos()) {
			modelConceptoSel.addConcepto(conceptoSelection);
		}
		tabConceptos.changeSelection(0, 1, false, false);
		// llamada al método para llenar los desgloses de concepto
		fillTableModelAspectos(index, tabConceptos.getSelectionModel().getLeadSelectionIndex());
	}

	/**
	 * método para llenar la lista de desgloses de concepto
	 * 
	 * @param indexPartida
	 *            -- identificador de la partida seleccionada
	 * @param indexConcepto
	 *            -- identificador del concepto seleccionado
	 */
	public void fillTableModelAspectos(int indexPartida, int indexConcepto) {
		modelAspectosSel.clear();
		tabAspectos.repaint();
		// para cada desglose agregar al modelo
		for (AspectoSelection aspectoSelection : listPartidasExport.get(indexPartida).getSubConcepto(indexConcepto).getSubsAspectos()) {
			modelAspectosSel.addAspecto(aspectoSelection);
		}
		tabAspectos.changeSelection(0, 1, false, false);
	}

	/**
	 * método para actualizar el valor de los elementos en la lista de conceptos
	 * 
	 * @param index
	 */
	public void setValueConceptos(int index) {
		// para cada concepto la lista de conceptos, en la lista de partidas del
		// indice seleccionado
		for (ConceptoSelection conceptoSelection : listPartidasExport.get(index).getSubsConceptos()) {
			conceptoSelection.setSelect((boolean) tabPartidas.getValueAt(tabPartidas.getSelectionModel().getLeadSelectionIndex(), 0));
			// actualización del valor de los aspectos
			setValueAspectos(conceptoSelection);
		}
	}

	/**
	 * método para actualizar el valor delos elementos en la lista de desgloses
	 * del concepto seleccionado aplica para una lista de desgloses de concepto
	 * 
	 * @param index
	 *            -- concepto seleccionado
	 */
	public void setValueAspectos(ConceptoSelection index) {
		// para cada desglose, actualizar el valor del elemento
		for (AspectoSelection aspectoSelection : index.getSubsAspectos()) {
			aspectoSelection.setSelect((boolean) tabConceptos.getValueAt(tabConceptos.getSelectionModel().getLeadSelectionIndex(), 0));
		}
	}

	/**
	 * método para actualizar el valor del aspecto selecionado aplica para un
	 * sólo desglose
	 */
	public void setValueAspecto() {
		// actualización del los elementos (partida y concepto) en caso de no
		// estar marcados
		tabPartidas.setValueAt(true, tabPartidas.getSelectionModel().getLeadSelectionIndex(), 0);
		listPartidasExport.get(tabPartidas.getSelectionModel().getLeadSelectionIndex()).setSelect(true);
		tabConceptos.setValueAt(true, tabConceptos.getSelectionModel().getLeadSelectionIndex(), 0);
		listPartidasExport.get(tabPartidas.getSelectionModel().getLeadSelectionIndex()).getSubConcepto(tabConceptos.getSelectionModel().getLeadSelectionIndex()).setSelect(true);
	}

	/**
	 * método para seleccionar todos los elementos (partidas , conceptos y
	 * desgloses de concepto
	 * 
	 * @param value
	 *            - valor para asignar a los elementos (true/false)
	 */
	public void checkAll(boolean value) {
		// se recorren todas las partidas y para cada lista de conceptos todos
		// sus aspectos y para cada lista de desgloses
		for (PartidaSelection partidaSelection : listPartidasExport) {
			partidaSelection.setSelect(value);
			for (ConceptoSelection conceptoSelection : partidaSelection.getSubsConceptos()) {
				conceptoSelection.setSelect(value);
				for (AspectoSelection aspectoSelection : conceptoSelection.getSubsAspectos()) {
					aspectoSelection.setSelect(value);
				}
			}
		}
	}

	/**
	 * método para asignar el valor a los conceptos de una partida
	 * 
	 * @param indexPartida
	 *            -- indice de la partida seleccionada
	 * @param value
	 *            -- valor asignado (true/false)
	 */
	public void checkConceptos(int indexPartida, boolean value) {
		for (ConceptoSelection conceptoSelection : listPartidasExport.get(indexPartida).getSubsConceptos()) {
			conceptoSelection.setSelect(value);
			for (AspectoSelection aspectoSelection : conceptoSelection.getSubsAspectos()) {
				aspectoSelection.setSelect(value);
			}
		}
	}

	/**
	 * método para el diseño de las tablas
	 */
	public void designTables() {
		tabPartidas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabPartidas.getColumn("Seleccionar").setPreferredWidth(155);
		tabPartidas.getColumn("Partida Presupuestal").setPreferredWidth(500);
		tabPartidas.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tabPartidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tabConceptos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabConceptos.getColumn("Seleccionar").setPreferredWidth(155);
		tabConceptos.getColumn("Concepto").setPreferredWidth(750);
		tabConceptos.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tabConceptos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tabAspectos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabAspectos.getColumn("Seleccionar").setPreferredWidth(100);
		tabAspectos.getColumn("Clave Pública").setPreferredWidth(100);
		tabAspectos.getColumn("Descripción").setPreferredWidth(600);
		tabAspectos.getColumn("Unidad").setPreferredWidth(75);
		tabAspectos.getColumn("Costo").setPreferredWidth(75);
		tabAspectos.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tabAspectos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	/**
	 * método para generar el archivo de excel, solicita el directorio y el
	 * nombre de l archivo a guardar
	 */
	public void exportExcel() {
		// validación de la lista de partidas por lo menos un desglose
		// seleccionado
		if (CExportarConceptos.validateList(listPartidasExport)) {

			// seleccion del archivo para guardar
			JFileChooser saveFileChooser = new JFileChooser();
			FileFilter filterExcel = new FileNameExtensionFilter("Libro de Excel (.xls)", ".xls");
			saveFileChooser.addChoosableFileFilter(filterExcel);
			saveFileChooser.setFileFilter(filterExcel);
			saveFileChooser.setDialogTitle("Seleccionar Directorio para Guardar el Archivo");
			saveFileChooser.setAcceptAllFileFilterUsed(false);

			// recuperación de la opción seleccionada
			int selectionUser = saveFileChooser.showSaveDialog(null);

			// ruta del archivo para guardar
			String rute = saveFileChooser.getSelectedFile() + ((FileNameExtensionFilter) saveFileChooser.getFileFilter()).getExtensions()[0];
			if (selectionUser == JFileChooser.APPROVE_OPTION) {
				// si aprovo guardar el archivo el usuario
				try {
					// llamada al controlador para generar el archivo
					CExportarConceptos.saveFileExcel(rute, listPartidasExport);
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, "No se cuenta con permisos de lectura para el directorio seleccionado");
					log.error(e);
				} catch (IOException e) {
					// si no se puede generar el archivo se notifica al usuario
					showMessageErrorSave();
					log.error(e);
				}
			}
		} else {
			// si no se ha seleccionado ningún aspecto se notifica al usuario
			showMessageNothingSelected();
			log.warn("NO SE SELECCIONO NINGÚN DESGLOSE DE CONCEPTO PARA LA EXPORTACIÓN .... Para Realizar la Exportación Marcar por lo menos 1 Desglose de Concepto.");
		}
	}

	/**
	 * método para generar el archivo de exportación en pdf
	 */
	public void exportPDF() {

		// validación de la lista de partidas
		if (CExportarConceptos.validateList(listPartidasExport)) {
			// seleccion del directorio y nombre del archivo para guardar
			JFileChooser saveFileChooser = new JFileChooser();
			FileFilter filterPDF = new FileNameExtensionFilter("Portable Document Format (.pdf)", ".pdf");
			saveFileChooser.addChoosableFileFilter(filterPDF);
			saveFileChooser.setFileFilter(filterPDF);
			saveFileChooser.setDialogTitle("Seleccionar Directorio para Guardar el Archivo");
			saveFileChooser.setAcceptAllFileFilterUsed(false);

			// recuperación del archivo
			int selectionUser = saveFileChooser.showSaveDialog(null);

			// ruta del archivo seleccionado
			String rute = saveFileChooser.getSelectedFile() + ((FileNameExtensionFilter) saveFileChooser.getFileFilter()).getExtensions()[0];

			if (selectionUser == JFileChooser.APPROVE_OPTION) {
				// si el usuario confirma guardar el archivo
				try {
					// llamada al controlador para generar el archivo en pdf
					CExportarConceptos.saveFilePDF(rute, listPartidasExport);
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, "No se cuenta con permisos de lectura para el directorio seleccionado");
					log.error(e);
				} catch (DocumentException e) {
					JOptionPane.showMessageDialog(null, "Error al Generar el Archivo Formato en PDF");
					log.error(e);
				} catch(IOException oie){
					showMessageErrorSave();
					log.error(oie);
				}
			}
		} else {
			showMessageNothingSelected();
			log.warn("NO SE SELECCIONO NINGÚN DESGLOSE DE CONCEPTO PARA LA EXPORTACIÓN");
		}
	}

	/**
	 * método para notificar al usuario que no se ha seleccionado nigún desglose
	 */
	public void showMessageNothingSelected() {
		JOptionPane.showMessageDialog(null, "Para Realizar la Exportación Marcar por lo menos 1 Desglose de Concepto.");
	}

	/**
	 * método para notificar al usuario que no se puedo guardar el archivo
	 */
	public void showMessageErrorSave() {
		JOptionPane.showMessageDialog(null, "Lo sentimos, el sistema no puede guardar el archivo en este momento");
	}

	/**
	 * método para notifiicar al usuario que no se pudo connectar a la base de
	 * datos
	 */
	public void showMessageErrorConnectDB() {
		JOptionPane.showMessageDialog(null, "Ha ocurrido un error al Intentar al Conectar a la Base de Datos");
	}
}
