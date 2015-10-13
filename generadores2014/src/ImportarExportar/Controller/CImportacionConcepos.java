package ImportarExportar.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Model.Entity.AspectoSelection;
import Model.Entity.ConceptoSelection;
import Model.Entity.PartidaSelection;
import Model.EntityCRUD.ImportacionCRUD;
import Model.EntityCRUD.Exceptions.DuplicateKey;
import Model.EntityCRUD.Exceptions.ForeingKeyReference;
import Model.EntityCRUD.Exceptions.ImportarExportar.IncompleteInformationDesglose;

/**
 * clase controladora para la importación de conceptos
 * 
 * @author Luis Angel Hernández Lázaro
 *
 */
public class CImportacionConcepos {

	private static Logger log = Logger.getLogger(CImportacionConcepos.class);

	/**
	 * método para verficar la extensión del archivo seleccionado
	 * 
	 * @param fileInput
	 *            -- archivo seleccionado para la importación de conceptos
	 * @return si la extensión es .xlx o .xlsx? true : false
	 */
	public static boolean checkExtensionFile(File fileInput) {
		boolean result = false;
		// se crea un split del nombre del archivo seleccionado
		String[] properties = fileInput.getName().split("\\.");
		// se verifica la extensión del archivo seleccionado
		if (properties[1].equals("xls") || properties[1].equals("xlsx")) {
			result = true;
		}
		return result;
	}

	/**
	 * método para obtener las partidas, conceptos, y desglose de conceptos del
	 * archivo seleccionado
	 * 
	 * @param fileInput
	 *            -- archivo de importación seleccionado
	 * @return lista de partidas de seleccion encontradas en el archivo
	 *         seleccionado
	 * @throws IOException
	 * @throws IncompleteInformationDesglose
	 * @throws Exception
	 */
	public static LinkedList<PartidaSelection> findPartidasFileInput(File fileInput) throws IOException, IncompleteInformationDesglose {
		LinkedList<PartidaSelection> listPartidaSelectionFileInput = new LinkedList<PartidaSelection>();
		String[] properties = fileInput.getName().split("\\.");
		// se verifica la extensión del archivo una vez más
		if (properties[1].equals("xls")) {
			// si es un archivo de excel 97-03 .xls
			listPartidaSelectionFileInput = findPartidasFileXls(fileInput);
		} else if (properties[1].equals("xlsx")) {
			// si es un archivo de excel 2007 .xlsx
			listPartidaSelectionFileInput = findPartidasFileXlsx(fileInput);
		} else {
			// en caso contrario no regresar ningún elemento en la lista
			return listPartidaSelectionFileInput = null;
		}
		return listPartidaSelectionFileInput;
	}

	/**
	 * método para encontrar las partidas en un archivo .xls
	 * 
	 * @param fileInput
	 *            -- archivo de importación
	 * @return lista de partidas de seleccion encontradas
	 * @throws IOException
	 * @throws IncompleteInformationDesglose
	 * @throws Exception
	 */
	public static LinkedList<PartidaSelection> findPartidasFileXls(File fileInput) throws IOException, IncompleteInformationDesglose {
		LinkedList<PartidaSelection> listPartidaSelection = new LinkedList<PartidaSelection>();

		// creacion de los archivos para manipular el contenido del archivo
		FileInputStream fInputStream = new FileInputStream(fileInput);
		POIFSFileSystem pfFileSystem = new POIFSFileSystem(fInputStream);

		// recuperación del libro de excel del archivo
		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook(pfFileSystem);

		// recuperación de la primera hoja del libro de excel
		HSSFSheet sheet = workbook.getSheetAt(0);

		// recuperación de la primera fila de la hoja de excel
		HSSFRow titles = sheet.getRow(0);
		// se verifica que el orden de las columnas sea el correcto
		if (titles.getCell(0).toString().equals("Clave") && titles.getCell(1).toString().equals("Descripción") && titles.getCell(2).toString().equals("Unidad") && titles.getCell(3).toString().equals("Costo")) {
			// si es correcto el formato se crean una partida y concepto para
			// recuperar la información
			PartidaSelection partida = new PartidaSelection();
			ConceptoSelection concepto = new ConceptoSelection();

			// se recorren las filas en la hoja del libro para obtener la
			// información
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				// se recupera la fila del recorrido
				HSSFRow row = sheet.getRow(i);
				// se realiza un try catch para contemplar columnas combinadas
				// en el nombre de las partidas
				try {
					// si la segunda columna se encuentra vacia se crea una
					// nueva partida y concepto con el nombre de la celda actual
					if (row.getCell(1).toString().isEmpty()) {
						partida = new PartidaSelection();
						concepto = new ConceptoSelection();

						partida.setNombre(row.getCell(0).toString());
						partida.setSelect(true);

						concepto.setNombre(row.getCell(0).toString());
						concepto.setSelect(true);
						// se agrega el concepto a la lista de conceptos de la
						// partida
						partida.addSubsConceptos(concepto);
						// se agrega la partida a la lista de partidas
						// encontradas
						listPartidaSelection.add(partida);

					} else {
						int index = 0;// variable para localizar la columna que
										// casue problemas
						AspectoSelection desgloseConcepto = new AspectoSelection();
						desgloseConcepto.setSelect(true);

						try {
							// si la segunda columna no esta vacia y las
							// primeras 4 columnas son diferenten de estar
							// vacias
							// se crea un nuevo desglose de concepto y se agrega
							// a la lista de aspectos del concepto actual
							if (!row.getCell(0).toString().isEmpty()) {
								index++;
								desgloseConcepto.setClave(row.getCell(0).toString());
								if (!row.getCell(1).toString().isEmpty()) {
									index++;
									desgloseConcepto.setDescripcion(row.getCell(1).toString());
									if (!row.getCell(2).toString().isEmpty()) {
										index++;
										desgloseConcepto.setUnidad(row.getCell(2).toString());
										if (!row.getCell(3).toString().isEmpty()) {
											index++;
											desgloseConcepto.setCosto(row.getCell(3).toString());
											concepto.addSubsAspecto(desgloseConcepto);
										}
									}
								}
							}
						} catch (NullPointerException e) {
							IncompleteInformationDesglose eIncompleteInformationDesglose = new IncompleteInformationDesglose("---", i + 1);
							switch (index) {
							case 0:
								eIncompleteInformationDesglose = new IncompleteInformationDesglose("Clave", i + 1);
								break;
							case 1:
								eIncompleteInformationDesglose = new IncompleteInformationDesglose("Descripción", i + 1);
								break;
							case 2:
								eIncompleteInformationDesglose = new IncompleteInformationDesglose("Unidad", i + 1);
								break;
							case 3:
								eIncompleteInformationDesglose = new IncompleteInformationDesglose("Clave", i + 1);
								break;
							default:
								break;
							}
							// si algún concepto no tiene información se crea
							// una nueva excepción para informar al usuario
							log.error(eIncompleteInformationDesglose);
							throw eIncompleteInformationDesglose;
						}

					}
				} catch (NullPointerException e) {
					log.info(e, e);

					// si la celda no se encuentra combinada se crea la partida
					// y el concepto con el nombre en la fila actual .. se
					// registra la excepción en el archivo
					partida = new PartidaSelection();
					concepto = new ConceptoSelection();

					partida.setIdpartida(i);
					partida.setNombre(row.getCell(0).toString());
					partida.setSelect(true);

					concepto.setIdpartida(i);
					concepto.setIdconcepto(i);
					concepto.setNombre(row.getCell(0).toString());
					concepto.setSelect(true);

					partida.addSubsConceptos(concepto);

					listPartidaSelection.add(partida);

					log.warn(e);

				}

			}
		}
		// se regresa la lista obtenida
		return listPartidaSelection;
	}

	/**
	 * método para encotnrar las partidas en un archivo .xlsx
	 * 
	 * @param fileInput
	 *            -- archivo de importación
	 * @return lista de partidas de seleccion encontradas
	 * @throws IOException
	 * @throws IncompleteInformationDesglose
	 * @throws Exception
	 */
	public static LinkedList<PartidaSelection> findPartidasFileXlsx(File fileInput) throws IOException, IncompleteInformationDesglose {
		LinkedList<PartidaSelection> listPartidaSelection = new LinkedList<PartidaSelection>();

		// creación del archivo para manupular el contenido
		FileInputStream fInputStream = new FileInputStream(fileInput);
		@SuppressWarnings("resource")
		// creación del libro de excel
		XSSFWorkbook workBook = new XSSFWorkbook(fInputStream);
		// recuperación de la primera hoja de excel del archivo
		XSSFSheet sheet = workBook.getSheetAt(0);

		// recuperación de la primera fila para ver los titulos
		XSSFRow titles = sheet.getRow(0);
		// verificación de los titulos de las columnas para ver si coinciden con
		// el formato
		if (titles.getCell(0).toString().equals("Clave") && titles.getCell(1).toString().equals("Descripción") && titles.getCell(2).toString().equals("Unidad") && titles.getCell(3).toString().equals("Costo")) {
			// creación de la partida y concepto en caso de respetar el formato
			// del contenido del archivo
			PartidaSelection partida = new PartidaSelection();
			ConceptoSelection concepto = new ConceptoSelection();

			// recorrido para cada una de las filas en el archivo
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				// recuperación de la fila
				XSSFRow row = sheet.getRow(i);
				try {
					// se pregunta si la segunda fila esta vacía
					if (row.getCell(1).toString().isEmpty()) {
						// si esta vacia se crea la partida y el concepto con el
						// contenido de la fila
						partida = new PartidaSelection();
						concepto = new ConceptoSelection();

						partida.setNombre(row.getCell(0).toString());
						partida.setSelect(true);

						concepto.setNombre(row.getCell(0).toString());
						concepto.setSelect(true);
						// se agrega el concepto a la lista de conceptos de la
						// partida
						partida.addSubsConceptos(concepto);
						// se agrega la partida a la lista de partidas
						// encontradas
						listPartidaSelection.add(partida);

					} else if ((!row.getCell(0).toString().isEmpty()) && (!row.getCell(1).toString().isEmpty()) && (!row.getCell(2).toString().isEmpty()) && (!row.getCell(3).toString().isEmpty())) {
						// si la segunda columna no esta vacia y las primeras 4
						// columnas son diferenten de estar vacias
						// se crea un nuevo desglose de concepto y se agrega a
						// la lista de aspectos del concepto actual
						AspectoSelection desgloseConcepto = new AspectoSelection();
						desgloseConcepto.setSelect(true);
						desgloseConcepto.setClave(row.getCell(0).toString());
						desgloseConcepto.setDescripcion(row.getCell(1).toString());
						desgloseConcepto.setUnidad(row.getCell(2).toString());
						desgloseConcepto.setCosto(row.getCell(3).toString());
						// se agrega el concepto a la lista de conceptos actual
						concepto.addSubsAspecto(desgloseConcepto);
					} else {
						// si las columnas no cumplen con el formato se genera
						// una excepción para nofiticar al usuario
						IncompleteInformationDesglose eIncompleteInformationDesglose = new IncompleteInformationDesglose("---", i + 1);
						if (row.getCell(0).toString().isEmpty()) {
							eIncompleteInformationDesglose = new IncompleteInformationDesglose("Clave", i + 1);
						} else if (row.getCell(1).toString().isEmpty()) {
							eIncompleteInformationDesglose = new IncompleteInformationDesglose("Descripción", i + 1);
						} else if (row.getCell(2).toString().isEmpty()) {
							eIncompleteInformationDesglose = new IncompleteInformationDesglose("Unidad", i + 1);
						} else if (row.getCell(3).toString().isEmpty()) {
							eIncompleteInformationDesglose = new IncompleteInformationDesglose("Clave", i + 1);
						}
						// si algún concepto no tiene información se crea una
						// nueva excepción para informar al usuario
						log.error(eIncompleteInformationDesglose);
						throw eIncompleteInformationDesglose;
					}
				} catch (NullPointerException e) {
					// si la celda de la partida no esta combinada se crea
					// partida y el concepto con el contenido celda
					partida = new PartidaSelection();
					concepto = new ConceptoSelection();

					partida.setIdpartida(i);
					partida.setNombre(row.getCell(0).toString());
					partida.setSelect(true);

					concepto.setIdpartida(i);
					concepto.setIdconcepto(i);
					concepto.setNombre(row.getCell(0).toString());
					concepto.setSelect(true);
					partida.addSubsConceptos(concepto);
					listPartidaSelection.add(partida);
					log.warn(e);
				}
			}
		}
		return listPartidaSelection;
	}

	/**
	 * método para guardar la información en la base de datos de las partidas,
	 * conceptos y desgloses encontrados en el archivo
	 * 
	 * @param data
	 *            -- lista de partidas encontradas en el archivo
	 * @throws SQLException
	 * @throws ForeingKeyReference
	 */
	public static boolean SavePartidas(LinkedList<PartidaSelection> data) throws ForeingKeyReference, SQLException  {
		return ImportacionCRUD.SavePartidas(data);
	}

	/**
	 * método para visualizar la estructura del archivo para realizar la
	 * importacion de conceptos.
	 * 
	 * @param rute
	 *            String --- ruta del directorio donde se guardará el archivo de
	 *            prueba
	 * @throws IOException 
	 */
	public static void getFormat(String rute) throws IOException {

		// titulos de las colunas del Archivo
		final String[] columnTitle = { "Clave Pública", "Descripción", "Unidad", "Costo" };
		// creación del archivo
		FileOutputStream newFile = new FileOutputStream(rute);
		@SuppressWarnings("resource")
		// creación del libro
		HSSFWorkbook book = new HSSFWorkbook();
		// creación de la hoja
		HSSFSheet sheet = book.createSheet("Catálogo de Conceptos");
		// creación de la fila
		HSSFRow row = sheet.createRow(0);
		// creación de la celda
		HSSFCell cell = row.createCell(0);
		HSSFRichTextString text;
		// creación de los titulos para las columnas
		row = sheet.createRow(0);
		for (int i = 0; i < columnTitle.length; i++) {
			cell = row.createCell(i);
			text = new HSSFRichTextString(columnTitle[i]);
			cell.setCellValue(text);
			sheet.autoSizeColumn((short) i);
		}
		int rowIndex=1;
		
		sheet.setColumnWidth(1, 9000);
		
		/**
		 * Primera partida del archivo 
		 */
		HSSFRow rowPartida = sheet.createRow(rowIndex);
		text = new HSSFRichTextString("Partida ABC");
		rowPartida.createCell(0).setCellValue(text);
		rowIndex++;
		//primer concepto del archivo
		// con sus propiedades, clave, descripcion, unidad y costo
		HSSFRow rowConcepto = sheet.createRow(rowIndex);
		text = new HSSFRichTextString("ABC12345");
		rowConcepto.createCell(0).setCellValue(text);
		
		text = new HSSFRichTextString("Descripción del concepto ABC12345");
		rowConcepto.createCell(1).setCellValue(text);
		
		text = new HSSFRichTextString("M2");
		rowConcepto.createCell(2).setCellValue(text);
		
		text = new HSSFRichTextString("100.10");
		rowConcepto.createCell(3).setCellValue(text);
		rowIndex++;
		
		//segundo concepto del archivo
		HSSFRow rowConcepto2 = sheet.createRow(rowIndex);
		text = new HSSFRichTextString("DEF67890");
		rowConcepto2.createCell(0).setCellValue(text);
		
		text = new HSSFRichTextString("Descripción del concepto DEF67890");
		rowConcepto2.createCell(1).setCellValue(text);
		
		text = new HSSFRichTextString("M3");
		rowConcepto2.createCell(2).setCellValue(text);
		
		text = new HSSFRichTextString("200.20");
		rowConcepto2.createCell(3).setCellValue(text);
		rowIndex++;
		
		/**
		 * segunda partida del archivo
		 */
		HSSFRow rowPartida2 = sheet.createRow(rowIndex);
		text = new HSSFRichTextString("Partida XYZ");
		rowPartida2.createCell(0).setCellValue(text);
		rowIndex++;
		
		//tercer concepto del archivo
		HSSFRow rowConcepto3 = sheet.createRow(rowIndex);
		text = new HSSFRichTextString("XYZ12345");
		rowConcepto3.createCell(0).setCellValue(text);
		
		text = new HSSFRichTextString("Descripción del concepto XYZ12345");
		rowConcepto3.createCell(1).setCellValue(text);
		
		text = new HSSFRichTextString("M2");
		rowConcepto3.createCell(2).setCellValue(text);
		
		text = new HSSFRichTextString("100.10");
		rowConcepto3.createCell(3).setCellValue(text);
		
		//guardar archivo y mostrarlo en pantalla
		book.write(newFile);
		newFile.close();
		// se reutiliza el método de la clase Cexportarconceptos para mostrar el archivo
		CExportarConceptos.showFile(rute);
	}

}
