package PDF;

import java.io.FileOutputStream;
import java.util.LinkedList;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ObjetosSerializables.Rgenerador;

/**
 * 
 * @author luiiis
 *
 */
public class Generarpdf {

	private static String archivo;
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	private static String FILE = "";

	/**
	 * 
	 * @param datos
	 * @param consultor
	 * @param path
	 */
	public Generarpdf(LinkedList<Rgenerador> datos, String consultor, String path) {
		try {
			FILE = path;

			Document document = new Document(PageSize.A4.rotate(), 10, 10, 10, 10);
			PdfWriter.getInstance(document, new FileOutputStream(FILE));
			document.open();
			addContent(document, datos, consultor, path);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param document
	 * @param datos
	 * @param consultor
	 * @param path
	 * @throws DocumentException
	 */
	private static void addContent(Document document, LinkedList<Rgenerador> datos, String consultor, String path) throws DocumentException {
		Anchor anchor = new Anchor("Datos del Reporte", catFont);
		anchor.setName("Datos");
		Chapter catPart = new Chapter(new Paragraph(anchor), 1);
		Paragraph subPara = new Paragraph("Datos de Creación", subFont);
		Section subCatPart = catPart.addSection(subPara);
		subCatPart.add(new Paragraph(consultor));
		subPara = new Paragraph("Datos del Proyecto", subFont);
		subCatPart = catPart.addSection(subPara);
		subCatPart.add(new Paragraph("Detalles o Descripciones"));
		//subCatPart.add(new Paragraph("Paragraph 2"));
		//subCatPart.add(new Paragraph("Paragraph 3"));
		subCatPart.add(new Paragraph(" "));
		createTable(subCatPart, datos);
		document.add(catPart);

	}

	/**
	 * 
	 * @param subCatPart
	 * @param datos
	 * @throws BadElementException
	 */
	private static void createTable(Section subCatPart, LinkedList<Rgenerador> datos) throws BadElementException {
		Rgenerador meter;
		System.out.println(archivo);
		PdfPTable table = new PdfPTable(17);
		table.setWidthPercentage(100);

		PdfPCell celda = new PdfPCell((new Paragraph("Reporte Números Generadores", FontFactory.getFont("arial", 22, Font.BOLD, BaseColor.RED))));
		celda.setColspan(17);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(12.0f);
		celda.setBackgroundColor(BaseColor.WHITE);
		table.addCell(celda);

		celda = new PdfPCell(new Paragraph("        "));
		celda.setColspan(1);
		celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(celda);

		celda = new PdfPCell(new Paragraph("         "));
		celda.setColspan(4);
		celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(celda);

		celda = new PdfPCell(new Paragraph("        "));
		celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(celda);

		celda = new PdfPCell((new Paragraph("Ubicación", FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.RED))));
		celda.setColspan(3);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(celda);

		celda = new PdfPCell((new Paragraph("Dimensiones", FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.RED))));
		celda.setColspan(3);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(celda);

		celda = new PdfPCell(new Paragraph(" "));
		celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celda);

		celda = new PdfPCell(new Paragraph(" "));
		celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celda);

		celda = new PdfPCell(new Paragraph(" "));
		celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celda);

		celda = new PdfPCell(new Paragraph(" "));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setColspan(2);
		celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(celda);

		// *********************************************************************************

		celda = new PdfPCell((new Paragraph("Clave Pública", FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.RED))));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celda);

		celda = new PdfPCell((new Paragraph("Clave Privada", FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.RED))));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celda);

		celda = new PdfPCell((new Paragraph("Descripción", FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.RED))));
		celda.setColspan(4);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celda);

		celda = new PdfPCell((new Paragraph("Unidad", FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.RED))));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celda);

		celda = new PdfPCell((new Paragraph("X", FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.RED))));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celda);

		celda = new PdfPCell((new Paragraph("Y", FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.RED))));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celda);

		celda = new PdfPCell((new Paragraph("Z", FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.RED))));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celda);

		celda = new PdfPCell((new Paragraph("Alto", FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.RED))));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celda);

		celda = new PdfPCell((new Paragraph("Largo", FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.RED))));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celda);

		celda = new PdfPCell((new Paragraph("Ancho", FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.RED))));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celda);

		celda = new PdfPCell((new Paragraph("Cantidad", FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.RED))));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celda);

		celda = new PdfPCell((new Paragraph("Pza", FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.RED))));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celda);

		celda = new PdfPCell((new Paragraph("C.U.", FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.RED))));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celda);

		celda = new PdfPCell((new Paragraph("Importe", FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.RED))));
		celda.setColspan(2);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(celda);
		for (int i = 0; i < datos.size(); i++) {
			meter = new Rgenerador();
			meter = (Rgenerador) datos.get(i);

			celda = new PdfPCell((new Paragraph(meter.getClavePublica(), FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK))));
			celda.setColspan(1);
			celda.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(celda);

			celda = new PdfPCell((new Paragraph(meter.getClave(), FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK))));
			celda.setColspan(1);
			celda.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(celda);

			celda = new PdfPCell((new Paragraph(meter.getDescripcion(), FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK))));
			celda.setColspan(4);
			celda.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(celda);

			celda = new PdfPCell((new Paragraph(meter.getUnidad(), FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK))));
			celda.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(celda);

			celda = new PdfPCell((new Paragraph(meter.getX(), FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK))));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(celda);

			celda = new PdfPCell((new Paragraph(meter.getY(), FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK))));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(celda);

			celda = new PdfPCell((new Paragraph(meter.getZ(), FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK))));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(celda);

			celda = new PdfPCell((new Paragraph(meter.getAlto(), FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK))));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(celda);

			celda = new PdfPCell((new Paragraph(meter.getLargo(), FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK))));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(celda);

			celda = new PdfPCell((new Paragraph(meter.getAncho(), FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK))));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(celda);

			celda = new PdfPCell((new Paragraph(meter.getCantidad(), FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK))));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(celda);

			celda = new PdfPCell((new Paragraph(meter.getPiezas(), FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK))));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(celda);

			celda = new PdfPCell((new Paragraph(meter.getCosto(), FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK))));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(celda);

			celda = new PdfPCell((new Paragraph(meter.getImporte(), FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK))));
			celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			celda.setColspan(2);
			table.addCell(celda);
		}
		subCatPart.add(table);
	}

	/*
	 * private static void addEmptyLine ( Paragraph paragraph , int number ) {
	 * for ( int i = 0 ; i < number ; i++ ) { paragraph.add( new Paragraph( " "
	 * ) ); } }
	 */

}
