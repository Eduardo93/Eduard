
package Excel;

import java.io.FileOutputStream;
import java.util.LinkedList;
import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import ObjetosSerializables.Rgenerador;

public class Reportefrente {

	private static String FILE;;
	private HSSFWorkbook libro;
	private HSSFRow encabezado, encabezado2;
	private HSSFSheet hoja;
	private HSSFCell Cencabezado, Cencabezado2;
	private HSSFFont fuentet, fuenteen, fuentein, fuenteinicial,
	        fuenteinicial2;
	private HSSFCellStyle esceldat, esceldaen, esceldain, estiloinicial,
	        estiloinicial2;
	private Rgenerador meter;
	private int j, i;
	private float gastoexedente2, gastopre = 0, gastoreal = 0,
	        gastoexedente = 0, gastorestante = 0;
	private float gastoexedentet, gastopreto = 0, gastorealto = 0,
	        gastoexedenteto = 0, gastorestanteto = 0;
	private boolean bandera = false;
	private String partida;

	public Reportefrente ( LinkedList datos , LinkedList datos2 , String path ) {
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

		// Definicion del estilo para marcar los datos que si estan en la
		// propuesta inicial
		// *****************************************************************************************************************************************************
		// definicion estilos de celdas, establecimineto del tipo de fuente
		fuenteinicial = libro.createFont( );
		fuenteinicial.setFontHeightInPoints( ( short ) 12 );
		fuenteinicial.setFontName( fuentet.FONT_ARIAL );
		fuenteinicial.setBoldweight( HSSFFont.BOLDWEIGHT_BOLD );

		// Creacion del objeto que se encargara de aplicar el estilo a la celda
		estiloinicial = libro.createCellStyle( );
		estiloinicial.setWrapText( true );
		estiloinicial.setAlignment( HSSFCellStyle.ALIGN_CENTER );
		estiloinicial.setVerticalAlignment( HSSFCellStyle.VERTICAL_TOP );
		estiloinicial.setFont( fuentet );

		// establecimiento de bordes
		estiloinicial.setBorderBottom( HSSFCellStyle.BORDER_MEDIUM );
		estiloinicial.setBottomBorderColor( ( short ) 8 );
		estiloinicial.setBorderLeft( HSSFCellStyle.BORDER_MEDIUM );
		estiloinicial.setLeftBorderColor( ( short ) 8 );
		estiloinicial.setBorderRight( HSSFCellStyle.BORDER_MEDIUM );
		estiloinicial.setRightBorderColor( ( short ) 8 );
		estiloinicial.setBorderTop( HSSFCellStyle.BORDER_MEDIUM );
		estiloinicial.setRightBorderColor( ( short ) 8 );

		// establecimiento de sombreado de nuestra celda
		estiloinicial.setFillForegroundColor( ( short ) 10 );
		estiloinicial.setFillPattern( HSSFCellStyle.SOLID_FOREGROUND );

		// *****************************************************************************************************************************************************
		// definicion estilos de celdas, establecimineto del tipo de fuente
		fuenteinicial2 = libro.createFont( );
		fuenteinicial2.setFontHeightInPoints( ( short ) 12 );
		fuenteinicial2.setFontName( fuentet.FONT_ARIAL );
		fuenteinicial2.setBoldweight( HSSFFont.BOLDWEIGHT_BOLD );

		// Creacion del objeto que se encargara de aplicar el estilo a la celda
		estiloinicial2 = libro.createCellStyle( );
		estiloinicial2.setWrapText( true );
		estiloinicial2.setAlignment( HSSFCellStyle.ALIGN_CENTER );
		estiloinicial2.setVerticalAlignment( HSSFCellStyle.VERTICAL_TOP );
		estiloinicial2.setFont( fuenteinicial2 );

		// establecimiento de bordes
		estiloinicial2.setBorderBottom( HSSFCellStyle.BORDER_MEDIUM );
		estiloinicial2.setBottomBorderColor( ( short ) 8 );
		estiloinicial2.setBorderLeft( HSSFCellStyle.BORDER_MEDIUM );
		estiloinicial2.setLeftBorderColor( ( short ) 8 );
		estiloinicial2.setBorderRight( HSSFCellStyle.BORDER_MEDIUM );
		estiloinicial2.setRightBorderColor( ( short ) 8 );
		estiloinicial2.setBorderTop( HSSFCellStyle.BORDER_MEDIUM );
		estiloinicial2.setRightBorderColor( ( short ) 8 );

		// establecimiento de sombreado de nuestra celda
		estiloinicial2.setFillForegroundColor( ( short ) 27 );
		estiloinicial2.setFillPattern( HSSFCellStyle.SOLID_FOREGROUND );

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
		Cencabezado.setCellValue( "Conceptos normales" );
		hoja.addMergedRegion( new Region( 5, ( short ) 2, 6, ( short ) 4 ) );
		Cencabezado.setCellStyle( esceldaen );
		// ********************************************************************************************************************
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

		// ***************************************************************************************************************************
		// writing data to the cells
		cc.setCellValue( "Clave" );
		cc.setCellStyle( esceldat );

		cd.setCellValue( "Descripción" );
		cd.setCellStyle( esceldat );

		cu.setCellValue( "Unidad" );
		cu.setCellStyle( esceldat );

		cx.setCellValue( "P.U" );
		cx.setCellStyle( esceldat );

		cy.setCellValue( "Volumen presupuestado" );
		cy.setCellStyle( esceldat );

		cz.setCellValue( "Gasto presupuestado" );
		cz.setCellStyle( esceldat );

		ca.setCellValue( "Volumen real" );
		ca.setCellStyle( esceldat );

		cl.setCellValue( "Gasto real" );
		cl.setCellStyle( esceldat );

		cal.setCellValue( "Exedente" );
		cal.setCellStyle( esceldat );

		cca.setCellValue( "Restante" );
		cca.setCellStyle( esceldat );
		j = 0;
		partida = "";
		i = 0;
		// ****************************************************************************************************************************

		while ( i < datos.size( ) ) {
			bandera = false;
			meter = new Rgenerador( );
			meter = ( Rgenerador ) datos.get( i );

			if ( partida.equals( "" ) == false ) {

				if ( meter.getPartida( ).equals( partida ) == false ) {

					HSSFRow rowt = hoja.createRow( ( short ) 8 + j );
					HSSFCell cto = rowt.createCell( ( short ) 6 );
					HSSFCell ctpre = rowt.createCell( ( short ) 7 );
					HSSFCell ctb = rowt.createCell( ( short ) 8 );
					HSSFCell ctre = rowt.createCell( ( short ) 9 );
					HSSFCell cte = rowt.createCell( ( short ) 10 );
					HSSFCell ctr = rowt.createCell( ( short ) 11 );

					cto.setCellValue( "Totales por partida" );
					cto.setCellStyle( esceldat );

					ctpre.setCellValue( String.valueOf( gastopre ) );
					ctpre.setCellStyle( estiloinicial );
					gastopreto += gastopre;
					gastopre = 0;

					ctb.setCellValue( "" );
					ctb.setCellStyle( estiloinicial );

					ctre.setCellValue( String.valueOf( gastoreal ) );
					ctre.setCellStyle( estiloinicial );
					gastorealto += gastoreal;
					gastoreal = 0;

					cte.setCellValue( gastoexedente );
					cte.setCellStyle( estiloinicial );
					gastoexedenteto += gastoexedente;
					gastoexedente = 0;

					ctr.setCellValue( gastorestante );
					ctr.setCellStyle( estiloinicial );
					gastorestanteto += gastorestante;
					j++;
					gastorestante = 0;
				}
			}

			// crear un una columna
			HSSFRow row = hoja.createRow( ( short ) 8 + j );
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

			if ( meter.getPartida( ).equals( partida ) == false ) {

				cc1.setCellValue( "" );
				cc1.setCellStyle( estiloinicial2 );

				cd1.setCellValue( meter.getPartida( ) );
				cd1.setCellStyle( estiloinicial2 );

				cu1.setCellValue( "" );
				cu1.setCellStyle( estiloinicial2 );

				cx1.setCellValue( "" );
				cx1.setCellStyle( estiloinicial2 );

				cy1.setCellValue( "" );
				cy1.setCellStyle( estiloinicial2 );

				cz1.setCellValue( "" );
				cz1.setCellStyle( estiloinicial2 );

				ca1.setCellValue( "" );
				ca1.setCellStyle( estiloinicial2 );

				cl1.setCellValue( "" );
				cl1.setCellStyle( estiloinicial2 );

				cal1.setCellValue( " " );
				cal1.setCellStyle( estiloinicial2 );

				cca1.setCellValue( "" );
				cca1.setCellStyle( estiloinicial2 );

				partida = meter.getPartida( );
				j++;
			} else {
				// **********************************************************************************************

				cc1.setCellValue( meter.getClave( ) );
				cc1.setCellStyle( esceldain );

				cd1.setCellValue( meter.getDescripcion( ) );
				cd1.setCellStyle( esceldain );

				cu1.setCellValue( meter.getUnidad( ) );
				cu1.setCellStyle( esceldain );

				cx1.setCellValue( meter.getCosto( ) );
				cx1.setCellStyle( esceldain );

				float an, l, re = 0, al, re2;

				if ( meter.getUnidad( ).equals( "M2" ) == true ) {
					an = Float.parseFloat( meter.getAncho( ) );
					l = Float.parseFloat( meter.getLargo( ) );
					re = an * l;
				}
				if ( meter.getUnidad( ).equals( "M3" ) == true ) {
					an = Float.parseFloat( meter.getAncho( ) );
					l = Float.parseFloat( meter.getLargo( ) );
					al = Float.parseFloat( meter.getAlto( ) );
					re = ( an * l ) * al;
				}
				if ( meter.getUnidad( ).equals( "Pza" ) == true ) {
					// re=Float.parseFloat( meter.getPiezas());
				}

				if ( meter.getUnidad( ).equals( "l" ) == true ) {
					re = Float.parseFloat( meter.getPiezas( ) );
				}

				if ( meter.getUnidad( ).equals( "M" ) == true ) {
					re = Float.parseFloat( meter.getLargo( ) );
				}

				cy1.setCellValue( String.valueOf( re ) );
				cy1.setCellStyle( esceldain );

				cz1.setCellValue( meter.getImporte( ) );
				cz1.setCellStyle( esceldain );

				gastopre += Float.parseFloat( meter.getImporte( ) );

				if ( datos2.size( ) > 0 ) {

					for ( int t = 0 ; t < datos2.size( ) ; t++ ) {
						Rgenerador objeto2 = ( Rgenerador ) datos2.get( t );

						if ( meter.getClave( ).equals( objeto2.getClave( ) ) == true ) {
							bandera = true;
							if ( objeto2.getUnidad( ).equals( "M2" ) == true ) {
								an = Float.parseFloat( objeto2.getAncho( ) );
								l = Float.parseFloat( objeto2.getLargo( ) );
								re = an * l;
							}
							if ( objeto2.getUnidad( ).equals( "M3" ) == true ) {
								an = Float.parseFloat( objeto2.getAncho( ) );
								l = Float.parseFloat( objeto2.getLargo( ) );
								al = Float.parseFloat( objeto2.getAlto( ) );
								re = ( an * l ) * al;
							}
							if ( objeto2.getUnidad( ).equals( "Pza" ) == true ) {
								re = Float.parseFloat( objeto2.getPiezas( ) );
							}

							if ( objeto2.getUnidad( ).equals( "l" ) == true ) {
								re2 = Float.parseFloat( objeto2.getPiezas( ) );
							}

							if ( objeto2.getUnidad( ).equals( "M" ) == true ) {
								re = Float.parseFloat( objeto2.getLargo( ) );
							}

							ca1.setCellValue( String.valueOf( re ) );
							ca1.setCellStyle( esceldain );

							cl1.setCellValue( objeto2.getImporte( ) );
							cl1.setCellStyle( esceldain );

							gastoreal += Float.parseFloat( objeto2.getImporte( ) );

							float uno = 0, dos = 0, resultado = 0;
							uno = Float.parseFloat( objeto2.getImporte( ) );
							dos = Float.parseFloat( meter.getImporte( ) );
							if ( uno > dos ) {
								resultado = uno - dos;
								cal1.setCellValue( String.valueOf( resultado ) );
								cal1.setCellStyle( esceldain );

								cca1.setCellValue( "0" );
								cca1.setCellStyle( esceldain );
								gastoexedente += resultado;

							} else {
								if ( dos > uno ) {
									resultado = dos - uno;
									cal1.setCellValue( "0" );
									cal1.setCellStyle( esceldain );

									cca1.setCellValue( String.valueOf( resultado ) );
									cca1.setCellStyle( esceldain );
									gastorestante += resultado;

								}

							}

						}

					}

					if ( bandera == false ) {

						ca1.setCellValue( "0" );
						ca1.setCellStyle( esceldat );

						cl1.setCellValue( "0" );
						cl1.setCellStyle( esceldat );

						cal1.setCellValue( "" );
						cal1.setCellStyle( esceldat );
						gastorestante += Float.parseFloat( meter.getImporte( ) );

						cca1.setCellValue( meter.getImporte( ) );
						cca1.setCellStyle( esceldain );

					}

				}

				HSSFSheet sheet = libro.getSheetAt( 0 );
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
				j++;
				// *************************************************************************************************
				i++;
			}

		}

		// *****************************************************************************************************************
		HSSFRow rowt = hoja.createRow( ( short ) 8 + j );
		HSSFCell cto = rowt.createCell( ( short ) 6 );
		HSSFCell ctpre = rowt.createCell( ( short ) 7 );
		HSSFCell ctb = rowt.createCell( ( short ) 8 );
		HSSFCell ctre = rowt.createCell( ( short ) 9 );
		HSSFCell cte = rowt.createCell( ( short ) 10 );
		HSSFCell ctr = rowt.createCell( ( short ) 11 );

		cto.setCellValue( "Totales por partida" );
		cto.setCellStyle( esceldat );

		ctpre.setCellValue( String.valueOf( gastopre ) );
		ctpre.setCellStyle( estiloinicial );
		gastopreto += gastopre;
		ctb.setCellValue( "" );
		ctb.setCellStyle( estiloinicial );

		ctre.setCellValue( String.valueOf( gastoreal ) );
		ctre.setCellStyle( estiloinicial );
		gastorealto += gastoreal;

		cte.setCellValue( gastoexedente );
		cte.setCellStyle( estiloinicial );
		gastoexedenteto += gastoexedente;

		ctr.setCellValue( gastorestante );
		ctr.setCellStyle( estiloinicial );
		gastorestanteto += gastorestante;
		// ***************************************************************************************************************************************************************************************
		HSSFRow rowt2 = hoja.createRow( ( short ) 10 + j );
		HSSFCell cto2 = rowt2.createCell( ( short ) 6 );
		HSSFCell ctpre2 = rowt2.createCell( ( short ) 7 );
		HSSFCell ctb2 = rowt2.createCell( ( short ) 8 );
		HSSFCell ctre2 = rowt2.createCell( ( short ) 9 );
		HSSFCell cte2 = rowt2.createCell( ( short ) 10 );
		HSSFCell ctr2 = rowt2.createCell( ( short ) 11 );

		cto2.setCellValue( "Totales" );
		cto2.setCellStyle( esceldat );

		ctpre2.setCellValue( String.valueOf( gastopreto ) );
		ctpre2.setCellStyle( esceldain );

		ctb2.setCellValue( "" );
		ctb2.setCellStyle( esceldain );

		ctre2.setCellValue( String.valueOf( gastorealto ) );
		ctre2.setCellStyle( esceldain );

		cte2.setCellValue( gastoexedenteto );
		cte2.setCellStyle( estiloinicial );

		ctr2.setCellValue( gastorestanteto );
		ctr2.setCellStyle( esceldain );

		encabezado2 = hoja.createRow( ( short ) 12 + j );
		Cencabezado2 = encabezado2.createCell( ( short ) 2 );
		Cencabezado2.setCellValue( "Conceptos Exedentes" );
		hoja.addMergedRegion( new Region( 12 + j, ( short ) 2, 13 + j, ( short ) 4 ) );
		Cencabezado2.setCellStyle( esceldaen );

		// crear un una columna
		HSSFRow fila1 = hoja.createRow( ( short ) 14 + j );
		// create de las celdas
		HSSFCell tc = fila1.createCell( ( short ) 2 );
		HSSFCell td = fila1.createCell( ( short ) 3 );
		HSSFCell tu = fila1.createCell( ( short ) 4 );
		HSSFCell tx = fila1.createCell( ( short ) 5 );
		HSSFCell ta = fila1.createCell( ( short ) 6 );
		HSSFCell tl = fila1.createCell( ( short ) 7 );
		HSSFCell tal = fila1.createCell( ( short ) 8 );

		// ***************************************************************************************************************************
		// writing data to the cells
		tc.setCellValue( "Clave" );
		tc.setCellStyle( esceldat );

		td.setCellValue( "Descripción" );
		td.setCellStyle( esceldat );

		tu.setCellValue( "Unidad" );
		tu.setCellStyle( esceldat );

		tx.setCellValue( "P.U" );
		tx.setCellStyle( esceldat );

		ta.setCellValue( "Volumen exedente" );
		ta.setCellStyle( esceldat );

		tl.setCellValue( "Gasto exedente" );
		tl.setCellStyle( esceldat );

		gastoexedente2 = 0;
		partida = "";
		i = 0;
		gastopre = 0;
		gastopreto = 0;

		while ( i < datos2.size( ) ) {
			Rgenerador objeto1 = new Rgenerador( );
			objeto1 = ( Rgenerador ) datos2.get( i );
			boolean ban2 = false;

			if ( partida.equals( "" ) == false ) {

				if ( objeto1.getPartida( ).equals( partida ) == false ) {

					HSSFRow rowt3 = hoja.createRow( ( short ) 15 + j );
					HSSFCell cto3 = rowt3.createCell( ( short ) 6 );
					HSSFCell ctpre3 = rowt3.createCell( ( short ) 7 );

					cto3.setCellValue( "Totales por partida" );
					cto3.setCellStyle( esceldat );

					ctpre3.setCellValue( String.valueOf( gastopre ) );
					ctpre3.setCellStyle( estiloinicial );
					gastopreto += gastopre;
					gastopre = 0;

					j++;

				}
			}

			HSSFRow fila2 = hoja.createRow( ( short ) 15 + j );
			HSSFCell cc1 = fila2.createCell( ( short ) 2 );
			HSSFCell cd1 = fila2.createCell( ( short ) 3 );
			HSSFCell cu1 = fila2.createCell( ( short ) 4 );
			HSSFCell cx1 = fila2.createCell( ( short ) 5 );
			HSSFCell ca1 = fila2.createCell( ( short ) 6 );
			HSSFCell cl1 = fila2.createCell( ( short ) 7 );

			if ( objeto1.getPartida( ).equals( partida ) == false ) {

				cc1.setCellValue( "" );
				cc1.setCellStyle( estiloinicial2 );

				cd1.setCellValue( objeto1.getPartida( ) );
				cd1.setCellStyle( estiloinicial2 );

				cu1.setCellValue( "" );
				cu1.setCellStyle( estiloinicial2 );

				cx1.setCellValue( "" );
				cx1.setCellStyle( estiloinicial2 );

				ca1.setCellValue( "" );
				ca1.setCellStyle( estiloinicial2 );

				cl1.setCellValue( "" );
				cl1.setCellStyle( estiloinicial2 );
				j++;
				partida = objeto1.getPartida( );

			} else {
				estiloinicial.setFillForegroundColor( ( short ) 10 );
				for ( int t = 0 ; t < datos.size( ) ; t++ ) {
					Rgenerador objeto2 = new Rgenerador( );
					objeto2 = ( Rgenerador ) datos.get( t );
					if ( objeto2.getClave( ).equals( objeto1.getClave( ) ) == true ) {
						t = datos.size( ) + 2;
						ban2 = true;
					}

				}
				if ( ban2 == false ) {

					cc1.setCellValue( objeto1.getClave( ) );
					cc1.setCellStyle( estiloinicial );

					cd1.setCellValue( objeto1.getDescripcion( ) );
					cd1.setCellStyle( esceldain );

					cu1.setCellValue( objeto1.getUnidad( ) );
					cu1.setCellStyle( esceldain );

					cx1.setCellValue( objeto1.getCosto( ) );
					cx1.setCellStyle( esceldain );

					float an, l, re = 0, al;

					if ( objeto1.getUnidad( ).equals( "M2" ) == true ) {
						an = Float.parseFloat( objeto1.getAncho( ) );
						l = Float.parseFloat( objeto1.getLargo( ) );
						re = an * l;
					}
					if ( objeto1.getUnidad( ).equals( "M3" ) == true ) {
						an = Float.parseFloat( objeto1.getAncho( ) );
						l = Float.parseFloat( objeto1.getLargo( ) );
						al = Float.parseFloat( objeto1.getAlto( ) );
						re = ( an * l ) * al;
					}
					if ( objeto1.getUnidad( ).equals( "Pza" ) == true ) {
						re = Float.parseFloat( objeto1.getCantidad( ) );
					}

					if ( objeto1.getUnidad( ).equals( "l" ) == true ) {
						re = Float.parseFloat( objeto1.getCantidad( ) );
					}

					if ( objeto1.getUnidad( ).equals( "M" ) == true ) {
						re = Float.parseFloat( objeto1.getLargo( ) );
					}

					ca1.setCellValue( String.valueOf( re ) );
					ca1.setCellStyle( esceldain );

					cl1.setCellValue( objeto1.getImporte( ) );
					cl1.setCellStyle( estiloinicial );
					gastopre += Float.parseFloat( objeto1.getImporte( ) );
					gastoexedente2 += Float.parseFloat( objeto1.getImporte( ) );

					// gastopre+=Float.parseFloat(objeto1.getImporte());

					j++;

				}
				i++;
			}

		}

		HSSFRow fila3 = hoja.createRow( ( short ) 15 + j );
		HSSFCell cc1 = fila3.createCell( ( short ) 6 );
		HSSFCell cd1 = fila3.createCell( ( short ) 7 );

		cc1.setCellValue( "Total por partida" );
		cc1.setCellStyle( esceldat );

		cd1.setCellValue( String.valueOf( gastopre ) );
		cd1.setCellStyle( estiloinicial );

		HSSFRow fila4 = hoja.createRow( ( short ) 18 + j );
		HSSFCell cc14 = fila4.createCell( ( short ) 6 );
		HSSFCell cd14 = fila4.createCell( ( short ) 7 );

		cc14.setCellValue( "Total Gasto real" );
		cc14.setCellStyle( esceldat );

		cd14.setCellValue( String.valueOf( gastoexedente2 + gastorealto ) );
		cd14.setCellStyle( esceldain );

		HSSFRow fila5 = hoja.createRow( ( short ) 19 + j );
		HSSFCell cc15 = fila5.createCell( ( short ) 6 );
		HSSFCell cd15 = fila5.createCell( ( short ) 7 );

		cc15.setCellValue( "Total exedente" );
		cc15.setCellStyle( esceldat );

		cd15.setCellValue( String.valueOf( gastoexedente2 + gastoexedenteto ) );
		cd15.setCellStyle( esceldain );

		HSSFRow fila6 = hoja.createRow( ( short ) 20 + j );
		HSSFCell cc16 = fila6.createCell( ( short ) 6 );
		HSSFCell cd16 = fila6.createCell( ( short ) 7 );

		cc16.setCellValue( "Total Restante" );
		cc16.setCellStyle( esceldat );

		cd16.setCellValue( String.valueOf( gastorestanteto ) );
		cd16.setCellStyle( esceldain );

		try {
			FileOutputStream elFichero = new FileOutputStream( FILE );
			libro.write( elFichero );
			elFichero.close( );
		} catch ( Exception e ) {
			e.printStackTrace( );
		}

	}

}
