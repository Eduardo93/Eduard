
package Excel;

import java.io.FileOutputStream;
import java.util.LinkedList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import ObjetosSerializables.Rgenerador;

public class Generarexcel {

	private String FILE = "";
	private HSSFWorkbook libro;
	private HSSFRow encabezado;
	private HSSFSheet hoja;
	private HSSFCell Cencabezado;
	private HSSFFont fuentet, fuenteen, fuentein;
	private HSSFCellStyle esceldat, esceldaen, esceldain;
	private Rgenerador meter;

	@ SuppressWarnings ( {
            "deprecation", "static-access"
    } )
    public Generarexcel ( LinkedList < Rgenerador > datos , String path ) {
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
		Cencabezado.setCellValue( "Reporte generadores" );
		hoja.addMergedRegion( new Region( 5, ( short ) 2, 6, ( short ) 4 ) );
		Cencabezado.setCellStyle( esceldaen );
		// ********************************************************************************************************************

		HSSFRow fila1 = hoja.createRow( ( short ) 2 );

		HSSFCell ccontrato = fila1.createCell( ( short ) 8 );
		ccontrato.setCellValue( "Contrato:" );
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
		ctipo.setCellValue( "Tipo de Obra:" );
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
		clocalidad.setCellValue( "Localidad:" );
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
		cperiodo.setCellValue( "Contratista:" );
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
		HSSFCell ccpub = row1.createCell( ( short ) 1 );
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

		ccpub.setCellValue( "Clave Pública" );
		ccpub.setCellStyle( esceldat );

		cc.setCellValue( "Clave Privada" );
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

		cal.setCellValue( "Ancho" );
		cal.setCellStyle( esceldat );

		cca.setCellValue( "Cantidad" );
		cca.setCellStyle( esceldat );

		cpz.setCellValue( "Piezas" );
		cpz.setCellStyle( esceldat );

		cim.setCellValue( "Importe" );
		cim.setCellStyle( esceldat );

		for ( int i = 0 ; i < datos.size( ) ; i++ ) {
			// crear un una columna
			HSSFRow row = hoja.createRow( ( short ) 8 + i );
			// create de las celdas
			HSSFCell ccPub1 = row.createCell( ( short ) 1 );
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

			meter = new Rgenerador( );
			meter = ( Rgenerador ) datos.get( i );

			ccPub1.setCellValue( meter.getClavePublica( ) );
			ccPub1.setCellStyle( esceldain );

			cc1.setCellValue( meter.getClave( ) );
			cc1.setCellStyle( esceldain );

			cd1.setCellValue( meter.getDescripcion( ) );
			cd1.setCellStyle( esceldain );

			cu1.setCellValue( meter.getUnidad( ) );
			cu1.setCellStyle( esceldain );

			cx1.setCellValue( meter.getX( ) );
			cx1.setCellStyle( esceldain );

			cy1.setCellValue( meter.getY( ) );
			cy1.setCellStyle( esceldain );

			cz1.setCellValue( meter.getZ( ) );
			cz1.setCellStyle( esceldain );

			ca1.setCellValue( meter.getAlto( ) );
			ca1.setCellStyle( esceldain );

			cl1.setCellValue( meter.getLargo( ) );
			cl1.setCellStyle( esceldain );

			cal1.setCellValue( meter.getAncho( ) );
			cal1.setCellStyle( esceldain );

			cca1.setCellValue( meter.getCantidad( ) );
			cca1.setCellStyle( esceldain );

			cpz1.setCellValue( meter.getPiezas( ) );
			cpz1.setCellStyle( esceldain );

			cim1.setCellValue( meter.getImporte( ) );
			cim1.setCellStyle( esceldain );

			HSSFSheet sheet = libro.getSheetAt( 0 );
			sheet.autoSizeColumn( ( short ) 1 );
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

		}

		try {
			FileOutputStream elFichero = new FileOutputStream( FILE );
			libro.write( elFichero );
			elFichero.close( );
		} catch ( Exception e ) {
			e.printStackTrace( );
		}

	}
}
