
package Excel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.util.IOUtils;

import ObjetosSerializables.Rgenerador;

public class Reporteconcepto {

	private static String FILE = "";
	private HSSFWorkbook libro;
	private HSSFRow encabezado;
	private HSSFSheet hoja;
	private HSSFCell Cencabezado;
	private HSSFFont fuentet, fuenteen, fuentein;
	private HSSFCellStyle esceldat, esceldaen, esceldain;

	@SuppressWarnings("deprecation")
	public Reporteconcepto ( Rgenerador objeto , Rgenerador objeto2 , Boolean siinicial , String partida , String ruta , String ruta2 , String path ) throws IOException {
		this.FILE = path;
		// creacion del libro que contedra nuestro reporte
		libro = new HSSFWorkbook( );

		// cracion de la hoja que estara contenida en nuestro libro
		hoja = libro.createSheet( "new sheet" );

		// Definicion de estilo que contendra nuestro encabezado
		// *****************************************************************************************************************************************************
		// definicion estilos de celdas, establecimineto del tipo de fuente
		fuenteen = libro.createFont( );
		fuenteen.setFontHeightInPoints( ( short ) 12 );
		fuenteen.setFontName( fuenteen.FONT_ARIAL );
		fuenteen.setBoldweight( HSSFFont.BOLDWEIGHT_BOLD );

		// Creacion del objeto que se encargara de aplicar el estilo a la celda
		esceldaen = libro.createCellStyle( );
		esceldaen.setWrapText( true );
		esceldaen.setAlignment( HSSFCellStyle.ALIGN_LEFT );
		esceldaen.setVerticalAlignment( HSSFCellStyle.VERTICAL_TOP );
		esceldaen.setFont( fuenteen );

		// establecimiento de sombreado de nuestra celda
		esceldaen.setFillForegroundColor( ( short ) 44 );
		esceldaen.setFillPattern( HSSFCellStyle.SOLID_FOREGROUND );

		// Definicion de estilo que contendra los tiulos
		// *****************************************************************************************************************************************************
		// definicion estilos de celdas, establecimineto del tipo de fuente
		fuentet = libro.createFont( );
		fuentet.setFontHeightInPoints( ( short ) 11 );
		fuentet.setFontName( fuentet.FONT_ARIAL );
		fuentet.setBoldweight( HSSFFont.BOLDWEIGHT_BOLD );

		// Creacion del objeto que se encargara de aplicar el estilo a la celda
		esceldat = libro.createCellStyle( );
		esceldat.setWrapText( true );
		esceldat.setAlignment( HSSFCellStyle.ALIGN_CENTER );
		esceldat.setVerticalAlignment( HSSFCellStyle.VERTICAL_TOP );
		esceldat.setFont( fuentet );

		// establecimiento de bordes
		esceldat.setBorderBottom( HSSFCellStyle.BORDER_MEDIUM );
		esceldat.setBottomBorderColor( ( short ) 8 );
		esceldat.setBorderLeft( HSSFCellStyle.BORDER_MEDIUM );
		esceldat.setLeftBorderColor( ( short ) 8 );
		esceldat.setBorderRight( HSSFCellStyle.BORDER_MEDIUM );
		esceldat.setRightBorderColor( ( short ) 8 );
		esceldat.setBorderTop( HSSFCellStyle.BORDER_MEDIUM );
		esceldat.setRightBorderColor( ( short ) 8 );

		// establecimiento de sombreado de nuestra celda
		esceldat.setFillForegroundColor( ( short ) 22 );
		esceldat.setFillPattern( HSSFCellStyle.SOLID_FOREGROUND );
		// *****************************************************************************************************************************************************

		// Definicion del estilo de la celda de nuestros datos que contendra el
		// reporte
		// definicion estilos de celdas, establecimineto del tipo de fuente
		fuentein = libro.createFont( );
		fuentein.setFontHeightInPoints( ( short ) 10 );
		fuentein.setFontName( fuentein.FONT_ARIAL );
		fuentein.setBoldweight( HSSFFont.BOLDWEIGHT_BOLD );

		// Creacion del objeto que se encargara de aplicar el estilo a la celda
		esceldain = libro.createCellStyle( );
		esceldain.setWrapText( true );
		esceldain.setAlignment( HSSFCellStyle.ALIGN_CENTER );
		esceldain.setVerticalAlignment( HSSFCellStyle.VERTICAL_TOP );
		esceldain.setFont( fuentet );

		// establecimiento de bordes
		esceldain.setBorderBottom( HSSFCellStyle.BORDER_MEDIUM );
		esceldain.setBottomBorderColor( ( short ) 8 );
		esceldain.setBorderLeft( HSSFCellStyle.BORDER_MEDIUM );
		esceldain.setLeftBorderColor( ( short ) 8 );
		esceldain.setBorderRight( HSSFCellStyle.BORDER_MEDIUM );
		esceldain.setRightBorderColor( ( short ) 8 );
		esceldain.setBorderTop( HSSFCellStyle.BORDER_MEDIUM );
		esceldain.setRightBorderColor( ( short ) 8 );

		// definimos el numero de filas que contedra nuestro decumento.
		encabezado = hoja.createRow( ( short ) 5 );
		Cencabezado = encabezado.createCell( ( short ) 2 );
		Cencabezado.setCellValue( "Datos Verificados: Partida(" + partida + ")" );
		hoja.addMergedRegion( new Region( 5, ( short ) 2, 6, ( short ) 4 ) );
		Cencabezado.setCellStyle( esceldaen );
		// ********************************************************************************************************************

		HSSFRow fila1 = hoja.createRow( ( short ) 2 );

		HSSFCell ccontrato = fila1.createCell( ( short ) 8 );
		ccontrato.setCellValue( "contrato:" );
		ccontrato.setCellStyle( esceldain );

		HSSFCell rcontrato = fila1.createCell( ( short ) 9 );
		rcontrato.setCellValue( "          " );
		rcontrato.setCellStyle( esceldain );

		HSSFCell cgenrencia = fila1.createCell( ( short ) 10 );
		cgenrencia.setCellValue( "Gerencia:" );
		cgenrencia.setCellStyle( esceldain );

		HSSFCell rgerencia = fila1.createCell( ( short ) 11 );
		rgerencia.setCellValue( "          " );
		rgerencia.setCellStyle( esceldain );

		HSSFCell choja = fila1.createCell( ( short ) 12 );
		choja.setCellValue( "Hoja:" );
		choja.setCellStyle( esceldain );

		HSSFCell rhoja = fila1.createCell( ( short ) 13 );
		rhoja.setCellValue( "          " );
		rhoja.setCellStyle( esceldain );
		// *******************************************************************************************************************************************************
		HSSFRow fila2 = hoja.createRow( ( short ) 3 );

		HSSFCell cnc = fila2.createCell( ( short ) 8 );
		cnc.setCellValue( "N.C. :" );
		cnc.setCellStyle( esceldain );

		HSSFCell rcnc = fila2.createCell( ( short ) 9 );
		rcnc.setCellValue( "          " );
		rcnc.setCellStyle( esceldain );

		HSSFCell ctipo = fila2.createCell( ( short ) 10 );
		ctipo.setCellValue( "Tipo de obra:" );
		ctipo.setCellStyle( esceldain );

		HSSFCell rtipo = fila2.createCell( ( short ) 11 );
		rtipo.setCellValue( "          " );
		rtipo.setCellStyle( esceldain );

		HSSFCell cunidad = fila2.createCell( ( short ) 12 );
		cunidad.setCellValue( "Unidad:" );
		cunidad.setCellStyle( esceldain );

		HSSFCell runidad = fila2.createCell( ( short ) 13 );
		runidad.setCellValue( "          " );
		runidad.setCellStyle( esceldain );

		// *******************************************************************************************************************************************************
		HSSFRow fila3 = hoja.createRow( ( short ) 4 );

		HSSFCell clocalidad = fila3.createCell( ( short ) 8 );
		clocalidad.setCellValue( "localidad:" );
		clocalidad.setCellStyle( esceldain );

		HSSFCell rlocalidad = fila3.createCell( ( short ) 9 );
		rlocalidad.setCellValue( "          " );
		rlocalidad.setCellStyle( esceldain );
		hoja.addMergedRegion( new Region( 4, ( short ) 9, 4, ( short ) 11 ) );
		rlocalidad.setCellStyle( esceldain );

		HSSFCell runo = fila3.createCell( ( short ) 10 );
		runo.setCellValue( "          " );
		runo.setCellStyle( esceldain );
		HSSFCell rdos = fila3.createCell( ( short ) 11 );
		rdos.setCellValue( "          " );
		rdos.setCellStyle( esceldain );

		HSSFCell cfecha = fila3.createCell( ( short ) 12 );
		cfecha.setCellValue( "Fecha:" );
		cfecha.setCellStyle( esceldain );

		HSSFCell rfecha = fila3.createCell( ( short ) 13 );
		rfecha.setCellValue( "          " );
		rfecha.setCellStyle( esceldain );

		// ***************************************************************************************************************************************************************
		HSSFRow fila4 = hoja.createRow( ( short ) 5 );
		HSSFCell ccontratista = fila4.createCell( ( short ) 8 );
		ccontratista.setCellValue( "Contratista:" );
		ccontratista.setCellStyle( esceldain );

		HSSFCell rcontratista = fila4.createCell( ( short ) 9 );
		rcontratista.setCellValue( "          " );
		rcontratista.setCellStyle( esceldain );
		hoja.addMergedRegion( new Region( 5, ( short ) 9, 5, ( short ) 13 ) );
		rcontratista.setCellStyle( esceldain );

		HSSFCell runoc = fila4.createCell( ( short ) 10 );
		runoc.setCellValue( "          " );
		runoc.setCellStyle( esceldain );
		HSSFCell rdosc = fila4.createCell( ( short ) 11 );
		rdosc.setCellValue( "          " );
		rdosc.setCellStyle( esceldain );

		HSSFCell rtres = fila4.createCell( ( short ) 12 );
		rtres.setCellValue( "          " );
		rtres.setCellStyle( esceldain );
		HSSFCell rcuatro = fila4.createCell( ( short ) 13 );
		rcuatro.setCellValue( "          " );
		rcuatro.setCellStyle( esceldain );

		// ***************************************************************************************************************************************************************
		HSSFRow fila5 = hoja.createRow( ( short ) 6 );
		HSSFCell cperiodo = fila5.createCell( ( short ) 8 );
		cperiodo.setCellValue( "Consultor:" );
		cperiodo.setCellStyle( esceldain );

		HSSFCell rperiodo = fila5.createCell( ( short ) 9 );
		rperiodo.setCellValue( "          " );
		rperiodo.setCellStyle( esceldain );
		hoja.addMergedRegion( new Region( 6, ( short ) 9, 6, ( short ) 13 ) );
		rperiodo.setCellStyle( esceldain );

		HSSFCell runop = fila5.createCell( ( short ) 10 );
		runop.setCellValue( "          " );
		runop.setCellStyle( esceldain );
		HSSFCell rdosp = fila5.createCell( ( short ) 11 );
		rdosp.setCellValue( "          " );
		rdosp.setCellStyle( esceldain );

		HSSFCell rtresp = fila5.createCell( ( short ) 12 );
		rtresp.setCellValue( "          " );
		rtresp.setCellStyle( esceldain );
		HSSFCell rcuatrop = fila5.createCell( ( short ) 13 );
		rcuatrop.setCellValue( "          " );
		rcuatrop.setCellStyle( esceldain );

		// crear un una columna
		HSSFRow row1 = hoja.createRow( ( short ) 7 );
		// create de las celdas
		HSSFCell cc = row1.createCell( ( short ) 2 );
		HSSFCell cd = row1.createCell( ( short ) 3 );
		HSSFCell cu = row1.createCell( ( short ) 4 );
		HSSFCell cx = row1.createCell( ( short ) 5 );
		HSSFCell cy = row1.createCell( ( short ) 6 );
		HSSFCell cz = row1.createCell( ( short ) 7 );
		HSSFCell ca = row1.createCell( ( short ) 8 );
		HSSFCell cl = row1.createCell( ( short ) 9 );
		HSSFCell cal = row1.createCell( ( short ) 10 );
		HSSFCell cca = row1.createCell( ( short ) 11 );
		HSSFCell cpz = row1.createCell( ( short ) 12 );
		HSSFCell cim = row1.createCell( ( short ) 13 );

		// writing data to the cells
		cc.setCellValue( "Clave" );
		cc.setCellStyle( esceldat );

		cd.setCellValue( "Descripción" );
		cd.setCellStyle( esceldat );

		cu.setCellValue( "Unidad" );
		cu.setCellStyle( esceldat );

		cx.setCellValue( "X" );
		cx.setCellStyle( esceldat );

		cy.setCellValue( "Y" );
		cy.setCellStyle( esceldat );

		cz.setCellValue( "Z" );
		cz.setCellStyle( esceldat );

		ca.setCellValue( "Alto" );
		ca.setCellStyle( esceldat );

		cl.setCellValue( "Largo" );
		cl.setCellStyle( esceldat );

		cal.setCellValue( "ancho" );
		cal.setCellStyle( esceldat );

		cca.setCellValue( "Cantidad" );
		cca.setCellStyle( esceldat );

		cpz.setCellValue( "Piezas" );
		cpz.setCellStyle( esceldat );

		cim.setCellValue( "Importe" );
		cim.setCellStyle( esceldat );

		// crear un una columna
		HSSFRow row = hoja.createRow( ( short ) 8 );
		// create de las celdas
		HSSFCell cc1 = row.createCell( ( short ) 2 );
		HSSFCell cd1 = row.createCell( ( short ) 3 );
		HSSFCell cu1 = row.createCell( ( short ) 4 );
		HSSFCell cx1 = row.createCell( ( short ) 5 );
		HSSFCell cy1 = row.createCell( ( short ) 6 );
		HSSFCell cz1 = row.createCell( ( short ) 7 );
		HSSFCell ca1 = row.createCell( ( short ) 8 );
		HSSFCell cl1 = row.createCell( ( short ) 9 );
		HSSFCell cal1 = row.createCell( ( short ) 10 );
		HSSFCell cca1 = row.createCell( ( short ) 11 );
		HSSFCell cpz1 = row.createCell( ( short ) 12 );
		HSSFCell cim1 = row.createCell( ( short ) 13 );

		cc1.setCellValue( objeto.getClave( ) );
		cc1.setCellStyle( esceldain );

		cd1.setCellValue( objeto.getDescripcion( ) );
		cd1.setCellStyle( esceldain );

		cu1.setCellValue( objeto.getUnidad( ) );
		cu1.setCellStyle( esceldain );

		cx1.setCellValue( objeto.getX( ) );
		cx1.setCellStyle( esceldain );

		cy1.setCellValue( objeto.getY( ) );
		cy1.setCellStyle( esceldain );

		cz1.setCellValue( objeto.getZ( ) );
		cz1.setCellStyle( esceldain );

		ca1.setCellValue( objeto.getAlto( ) );
		ca1.setCellStyle( esceldain );

		cl1.setCellValue( objeto.getLargo( ) );
		cl1.setCellStyle( esceldain );

		cal1.setCellValue( objeto.getAncho( ) );
		cal1.setCellStyle( esceldain );

		cca1.setCellValue( objeto.getCantidad( ) );
		cca1.setCellStyle( esceldain );

		cpz1.setCellValue( objeto.getPiezas( ) );
		cpz1.setCellStyle( esceldain );

		cim1.setCellValue( objeto.getImporte( ) );
		cim1.setCellStyle( esceldain );

		HSSFSheet sheet = libro.getSheetAt( 0 );

		HSSFPatriarch patriarch = sheet.createDrawingPatriarch( );
		// contendor que contiene las imagenes
		HSSFClientAnchor anchor;

		if ( ruta != null ) {
			File fis = new File( ruta );
			anchor = new HSSFClientAnchor( 0, 0, 0, 0, ( short ) 3, 11, ( short ) 5, 24 );
			anchor.setAnchorType( 2 );
			HSSFPicture imagen = patriarch.createPicture( anchor, Cargarimagen( fis, libro ) );
		}
		if ( ruta2 != null ) {
			File fis2 = new File( ruta2 );
			anchor = new HSSFClientAnchor( 0, 0, 0, 0, ( short ) 9, 11, ( short ) 12, 24 );
			anchor.setAnchorType( 2 );
			HSSFPicture imagen2 = patriarch.createPicture( anchor, Cargarimagen( fis2, libro ) );
		}

		sheet.autoSizeColumn( ( short ) 2 );
		sheet.autoSizeColumn( ( short ) 3 );
		sheet.autoSizeColumn( ( short ) 4 );
		sheet.autoSizeColumn( ( short ) 5 );
		sheet.autoSizeColumn( ( short ) 6 );
		sheet.autoSizeColumn( ( short ) 7 );
		sheet.autoSizeColumn( ( short ) 8 );
		sheet.autoSizeColumn( ( short ) 9 );
		sheet.autoSizeColumn( ( short ) 10 );
		sheet.autoSizeColumn( ( short ) 11 );
		sheet.autoSizeColumn( ( short ) 12 );
		sheet.autoSizeColumn( ( short ) 13 );

		if ( siinicial == true ) {

			// ************************************************************************************
			HSSFRow encabezado2 = hoja.createRow( ( short ) 26 );
			HSSFCell Cencabezado2 = encabezado2.createCell( ( short ) 2 );
			Cencabezado2.setCellValue( "Datos: Partida(" + partida + ")" );
			hoja.addMergedRegion( new Region( 26, ( short ) 2, 27, ( short ) 4 ) );
			Cencabezado2.setCellStyle( esceldaen );

			// crear un una columna
			HSSFRow row27 = hoja.createRow( ( short ) 28 );
			// create de las celdas
			HSSFCell cc2 = row27.createCell( ( short ) 2 );
			HSSFCell cd2 = row27.createCell( ( short ) 3 );
			HSSFCell cu2 = row27.createCell( ( short ) 4 );
			HSSFCell cx2 = row27.createCell( ( short ) 5 );
			HSSFCell cy2 = row27.createCell( ( short ) 6 );
			HSSFCell cz2 = row27.createCell( ( short ) 7 );
			HSSFCell ca2 = row27.createCell( ( short ) 8 );
			HSSFCell cl2 = row27.createCell( ( short ) 9 );
			HSSFCell cal2 = row27.createCell( ( short ) 10 );
			HSSFCell cca2 = row27.createCell( ( short ) 11 );
			HSSFCell cpz2 = row27.createCell( ( short ) 12 );
			HSSFCell cim2 = row27.createCell( ( short ) 13 );

			// writing data to the cells
			cc2.setCellValue( "Clave" );
			cc2.setCellStyle( esceldat );

			cd2.setCellValue( "Descripción" );
			cd2.setCellStyle( esceldat );

			cu2.setCellValue( "Unidad" );
			cu2.setCellStyle( esceldat );

			cx2.setCellValue( "X" );
			cx2.setCellStyle( esceldat );

			cy2.setCellValue( "Y" );
			cy2.setCellStyle( esceldat );

			cz2.setCellValue( "Z" );
			cz2.setCellStyle( esceldat );

			ca2.setCellValue( "Alto" );
			ca2.setCellStyle( esceldat );

			cl2.setCellValue( "Largo" );
			cl2.setCellStyle( esceldat );

			cal2.setCellValue( "ancho" );
			cal2.setCellStyle( esceldat );

			cca2.setCellValue( "Cantidad" );
			cca2.setCellStyle( esceldat );

			cpz2.setCellValue( "Piezas" );
			cpz2.setCellStyle( esceldat );

			cim2.setCellValue( "Importe" );
			cim2.setCellStyle( esceldat );

			// crear un una columna
			HSSFRow row28 = hoja.createRow( ( short ) 29 );
			// create de las celdas
			HSSFCell cc12 = row28.createCell( ( short ) 2 );
			HSSFCell cd12 = row28.createCell( ( short ) 3 );
			HSSFCell cu12 = row28.createCell( ( short ) 4 );
			HSSFCell cx12 = row28.createCell( ( short ) 5 );
			HSSFCell cy12 = row28.createCell( ( short ) 6 );
			HSSFCell cz12 = row28.createCell( ( short ) 7 );
			HSSFCell ca12 = row28.createCell( ( short ) 8 );
			HSSFCell cl12 = row28.createCell( ( short ) 9 );
			HSSFCell cal12 = row28.createCell( ( short ) 10 );
			HSSFCell cca12 = row28.createCell( ( short ) 11 );
			HSSFCell cpz12 = row28.createCell( ( short ) 12 );
			HSSFCell cim12 = row28.createCell( ( short ) 13 );

			cc12.setCellValue( objeto2.getClave( ) );
			cc12.setCellStyle( esceldain );

			cd12.setCellValue( objeto2.getDescripcion( ) );
			cd12.setCellStyle( esceldain );

			cu12.setCellValue( objeto2.getUnidad( ) );
			cu12.setCellStyle( esceldain );

			cx12.setCellValue( objeto2.getX( ) );
			cx12.setCellStyle( esceldain );

			cy12.setCellValue( objeto2.getY( ) );
			cy12.setCellStyle( esceldain );

			cz12.setCellValue( objeto2.getZ( ) );
			cz12.setCellStyle( esceldain );

			ca12.setCellValue( objeto2.getAlto( ) );
			ca12.setCellStyle( esceldain );

			cl12.setCellValue( objeto2.getLargo( ) );
			cl12.setCellStyle( esceldain );

			cal12.setCellValue( objeto2.getAncho( ) );
			cal12.setCellStyle( esceldain );

			cca12.setCellValue( objeto2.getCantidad( ) );
			cca12.setCellStyle( esceldain );

			cpz12.setCellValue( objeto2.getPiezas( ) );
			cpz12.setCellStyle( esceldain );

			cim12.setCellValue( objeto2.getImporte( ) );
			cim12.setCellStyle( esceldain );

			float uno = 0, dos = 0, resultado = 0;
			uno = Float.parseFloat( objeto.getImporte( ) );
			dos = Float.parseFloat( objeto2.getImporte( ) );
			if ( uno > dos ) {
				HSSFRow row31 = hoja.createRow( ( short ) 33 );
				// create de las celdas
				HSSFCell cc131 = row31.createCell( ( short ) 3 );
				resultado = uno - dos;
				cc131.setCellValue( "Execedente: " + String.valueOf( resultado ) );
				cc131.setCellStyle( esceldain );
			} else {
				if ( dos > uno ) {
					HSSFRow row31 = hoja.createRow( ( short ) 33 );
					// create de las celdas
					HSSFCell cc131 = row31.createCell( ( short ) 3 );
					resultado = dos - uno;
					cc131.setCellValue( "Restante: " + String.valueOf( resultado ) );
					cc131.setCellStyle( esceldain );

				}
			}
			// ****************************************************************************************
		} else {
			HSSFRow encabezado2 = hoja.createRow( ( short ) 26 );
			HSSFCell Cencabezado2 = encabezado2.createCell( ( short ) 2 );
			Cencabezado2.setCellValue( "El aspecto:" + objeto.getDescripcion( ) + "  no esta comtemplado en la estimacion inicial" );
			hoja.addMergedRegion( new Region( 26, ( short ) 2, 27, ( short ) 4 ) );
			Cencabezado2.setCellStyle( esceldaen );

			HSSFRow row28 = hoja.createRow( ( short ) 29 );
			// create de las celdas
			HSSFCell cc12 = row28.createCell( ( short ) 3 );
			cc12.setCellValue( "Execedente: " + objeto.getImporte( ) );
			cc12.setCellStyle( esceldain );

		}
		try {
			FileOutputStream elFichero = new FileOutputStream( FILE );
			libro.write( elFichero );
			elFichero.close( );
		} catch ( Exception e ) {
			e.printStackTrace( );
		}

	}

	// Metodo que permite insertar imagenes a el excel
	private static int Cargarimagen ( File ruta , HSSFWorkbook libro ) throws IOException {
		int indice;
		FileInputStream origen = null;
		ByteArrayOutputStream destino = null;
		try {
			// Leer la imagen del archivo
			origen = new FileInputStream( ruta );
			destino = new ByteArrayOutputStream( );
			int c;
			// Copiar la imagen bit a bit para ser pasada al execel
			while ( ( c = origen.read( ) ) != -1 )
				destino.write( c );

			// Agregamos la iamgen al libro execel
			indice = libro.addPicture( destino.toByteArray( ), HSSFWorkbook.PICTURE_TYPE_JPEG );

		} finally {
			if ( origen != null )
				origen.close( );
			if ( destino != null )
				destino.close( );
		}
		return indice;
	}

}
