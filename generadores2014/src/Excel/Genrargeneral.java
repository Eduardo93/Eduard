
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

public class Genrargeneral {

	private String FILE = "";
	private HSSFWorkbook libro;
	private HSSFRow encabezado;
	private HSSFSheet hoja;
	private HSSFCell Cencabezado;
	private HSSFFont fuentet, fuenteen, fuentein;
	private HSSFCellStyle esceldat, esceldaen, esceldain;

	private String partida;
	private String cla;
	private String clavePublica;
	private String des;
	private String un;
	private String X;
	private String Y;
	private String Z;
	private String al;
	private String lar;
	private String anc;
	private String pza;
	private String ca;
	@ SuppressWarnings ( "unused" )
    private String co;
	private String im;
	private int i = 0, posi = 0;
	private HSSFRow fila11;

	@ SuppressWarnings ( {
            "deprecation", "static-access"
    } )
    public Genrargeneral ( LinkedList<Rgenerador> lista , String path ) {
		// creacion del libro que contedra nuestro reporte
		this.FILE = path;
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
		Cencabezado.setCellValue( "Reporte presupuestal" );
		hoja.addMergedRegion( new Region( 5, ( short ) 2, 6, ( short ) 6 ) );
		Cencabezado.setCellStyle( esceldaen );
		// ********************************************************************************************************************

		HSSFRow fila1 = hoja.createRow( ( short ) 2 );

		HSSFCell obra = fila1.createCell( ( short ) 8 );
		obra.setCellValue( "No.obra" );
		obra.setCellStyle( esceldain );

		HSSFCell descripcion = fila1.createCell( ( short ) 9 );
		descripcion.setCellValue( "Descripción de la Obra" );
		hoja.addMergedRegion( new Region( 2, ( short ) 9, 2, ( short ) 11 ) );
		descripcion.setCellStyle( esceldain );
		HSSFCell cdescripcion = fila1.createCell( ( short ) 10 );
		cdescripcion.setCellValue( " " );
		cdescripcion.setCellStyle( esceldain );
		HSSFCell cdescripcion2 = fila1.createCell( ( short ) 11 );
		cdescripcion2.setCellValue( " " );
		cdescripcion2.setCellStyle( esceldain );

		HSSFCell thoja = fila1.createCell( ( short ) 12 );
		thoja.setCellValue( "Hoja" );
		thoja.setCellStyle( esceldain );

		HSSFCell ehoja = fila1.createCell( ( short ) 13 );
		ehoja.setCellValue( "    " );
		ehoja.setCellStyle( esceldain );

		// ***************************************************************************************************************************************
		HSSFRow fila2 = hoja.createRow( ( short ) 3 );

		HSSFCell tobra = fila2.createCell( ( short ) 8 );
		tobra.setCellValue( "" );
		tobra.setCellStyle( esceldain );

		HSSFCell tdescripcion = fila2.createCell( ( short ) 9 );
		tdescripcion.setCellValue( "     " );
		hoja.addMergedRegion( new Region( 3, ( short ) 9, 3, ( short ) 11 ) );
		tdescripcion.setCellStyle( esceldain );

		HSSFCell ctdescripcion = fila2.createCell( ( short ) 9 );
		ctdescripcion.setCellValue( " " );
		ctdescripcion.setCellStyle( esceldain );
		HSSFCell ctdescripcion2 = fila2.createCell( ( short ) 10 );
		ctdescripcion2.setCellValue( " " );
		ctdescripcion2.setCellStyle( esceldain );
		HSSFCell ctdescripcion3 = fila2.createCell( ( short ) 11 );
		ctdescripcion3.setCellValue( " " );
		ctdescripcion3.setCellStyle( esceldain );

		HSSFCell unidad = fila2.createCell( ( short ) 12 );
		unidad.setCellValue( "Unidad" );
		unidad.setCellStyle( esceldain );

		HSSFCell tunidad = fila2.createCell( ( short ) 13 );
		tunidad.setCellValue( "  " );
		tunidad.setCellStyle( esceldain );

		// /**********************************************************************************************************************************
		HSSFRow fila3 = hoja.createRow( ( short ) 4 );

		HSSFCell localidad = fila3.createCell( ( short ) 8 );
		hoja.addMergedRegion( new Region( 4, ( short ) 8, 4, ( short ) 9 ) );
		localidad.setCellValue( "Localidad" );
		localidad.setCellStyle( esceldain );

		HSSFCell clocalidad = fila3.createCell( ( short ) 9 );
		clocalidad.setCellValue( " " );
		clocalidad.setCellStyle( esceldain );

		HSSFCell municipio = fila3.createCell( ( short ) 10 );
		hoja.addMergedRegion( new Region( 4, ( short ) 10, 4, ( short ) 11 ) );
		municipio.setCellValue( "Municipio" );
		municipio.setCellStyle( esceldain );

		HSSFCell cmunicipio = fila3.createCell( ( short ) 11 );
		cmunicipio.setCellValue( " " );
		cmunicipio.setCellStyle( esceldain );

		HSSFCell fecha = fila3.createCell( ( short ) 12 );
		hoja.addMergedRegion( new Region( 4, ( short ) 12, 4, ( short ) 13 ) );
		fecha.setCellValue( "Fecha" );
		fecha.setCellStyle( esceldain );

		HSSFCell dfecha = fila3.createCell( ( short ) 13 );
		dfecha.setCellValue( " " );
		dfecha.setCellStyle( esceldain );

		// /*****************************************************************************************************************************************
		HSSFRow fila4 = hoja.createRow( ( short ) 5 );

		HSSFCell tlocalidad = fila4.createCell( ( short ) 8 );
		hoja.addMergedRegion( new Region( 5, ( short ) 8, 5, ( short ) 9 ) );
		tlocalidad.setCellValue( "  " );
		tlocalidad.setCellStyle( esceldain );

		HSSFCell ctlocalidad = fila4.createCell( ( short ) 9 );
		ctlocalidad.setCellValue( " " );
		ctlocalidad.setCellStyle( esceldain );

		HSSFCell tmunicipio = fila4.createCell( ( short ) 10 );
		hoja.addMergedRegion( new Region( 5, ( short ) 10, 5, ( short ) 11 ) );
		tmunicipio.setCellValue( "  " );
		tmunicipio.setCellStyle( esceldain );

		HSSFCell ctmunicipio = fila4.createCell( ( short ) 11 );
		ctmunicipio.setCellValue( " " );
		ctmunicipio.setCellStyle( esceldain );

		HSSFCell tfecha = fila4.createCell( ( short ) 12 );
		hoja.addMergedRegion( new Region( 5, ( short ) 12, 5, ( short ) 13 ) );
		tfecha.setCellValue( "  " );
		tfecha.setCellStyle( esceldain );

		HSSFCell ctfecha = fila4.createCell( ( short ) 13 );
		ctfecha.setCellValue( " " );
		ctfecha.setCellStyle( esceldain );
		// *************************************************************************************************************************************

		HSSFRow fila5 = hoja.createRow( ( short ) 6 );
		HSSFCell contratista = fila5.createCell( ( short ) 8 );
		hoja.addMergedRegion( new Region( 6, ( short ) 8, 6, ( short ) 9 ) );
		contratista.setCellValue( "Contratista" );
		contratista.setCellStyle( esceldain );

		HSSFCell dcontratista = fila5.createCell( ( short ) 9 );
		dcontratista.setCellValue( "   " );
		dcontratista.setCellStyle( esceldain );

		HSSFCell tcontratista = fila5.createCell( ( short ) 10 );
		hoja.addMergedRegion( new Region( 6, ( short ) 10, 6, ( short ) 13 ) );
		tcontratista.setCellValue( "  " );
		tcontratista.setCellStyle( esceldain );

		HSSFCell dcontratista2 = fila5.createCell( ( short ) 11 );
		dcontratista2.setCellValue( "   " );
		dcontratista2.setCellStyle( esceldain );

		HSSFCell dcontratista3 = fila5.createCell( ( short ) 12 );
		dcontratista3.setCellValue( "   " );
		dcontratista3.setCellStyle( esceldain );

		HSSFCell dcontratista4 = fila5.createCell( ( short ) 13 );
		dcontratista4.setCellValue( "   " );
		dcontratista4.setCellStyle( esceldain );
		// *************************************************************************************************************************************************

		HSSFRow fila6 = hoja.createRow( ( short ) 7 );

		HSSFCell plano = fila6.createCell( ( short ) 8 );
		plano.setCellValue( "Plano" );
		plano.setCellStyle( esceldain );

		HSSFCell tplano = fila6.createCell( ( short ) 9 );
		tplano.setCellValue( " " );
		tplano.setCellStyle( esceldain );

		HSSFCell cuerpo = fila6.createCell( ( short ) 10 );
		cuerpo.setCellValue( "Cuerpo" );
		cuerpo.setCellStyle( esceldain );

		HSSFCell tcuerpo = fila6.createCell( ( short ) 11 );
		tcuerpo.setCellValue( " " );
		tcuerpo.setCellStyle( esceldain );

		HSSFCell nivel = fila6.createCell( ( short ) 12 );
		nivel.setCellValue( "Nivel" );
		nivel.setCellStyle( esceldain );

		HSSFCell tnivel = fila6.createCell( ( short ) 13 );
		tnivel.setCellValue( " " );
		tnivel.setCellStyle( esceldain );
		// ****************************************************************************************************************************************

		HSSFRow fila7 = hoja.createRow( ( short ) 8 );

		HSSFCell contrato = fila7.createCell( ( short ) 8 );
		contrato.setCellValue( "Contrato" );
		contrato.setCellStyle( esceldain );

		HSSFCell periodoeje = fila7.createCell( ( short ) 9 );
		hoja.addMergedRegion( new Region( 8, ( short ) 9, 8, ( short ) 10 ) );
		periodoeje.setCellValue( "Periodo Ejecución de Contrato " );
		periodoeje.setCellStyle( esceldain );

		HSSFCell dperiodoeje = fila7.createCell( ( short ) 10 );
		dperiodoeje.setCellValue( "   " );
		dperiodoeje.setCellStyle( esceldain );

		HSSFCell periodoejebi = fila7.createCell( ( short ) 11 );
		hoja.addMergedRegion( new Region( 8, ( short ) 11, 8, ( short ) 13 ) );
		periodoejebi.setCellValue( "Perido Ejecución Bítacora" );
		periodoejebi.setCellStyle( esceldain );

		HSSFCell dperiodoejebi = fila7.createCell( ( short ) 12 );
		dperiodoejebi.setCellValue( "   " );
		dperiodoejebi.setCellStyle( esceldain );

		HSSFCell dperiodoejebi2 = fila7.createCell( ( short ) 13 );
		dperiodoejebi2.setCellValue( "   " );
		dperiodoejebi2.setCellStyle( esceldain );

		// *********************************************************************************************************************************************

		HSSFRow fila8 = hoja.createRow( ( short ) 9 );

		HSSFCell tcontrato = fila8.createCell( ( short ) 8 );
		tcontrato.setCellValue( "   " );
		tcontrato.setCellStyle( esceldain );

		HSSFCell tinicio = fila8.createCell( ( short ) 9 );
		tinicio.setCellValue( "  " );
		tinicio.setCellStyle( esceldain );

		HSSFCell tfin = fila8.createCell( ( short ) 10 );
		tfin.setCellValue( "  " );
		tfin.setCellStyle( esceldain );

		HSSFCell tbitacora = fila8.createCell( ( short ) 11 );
		hoja.addMergedRegion( new Region( 9, ( short ) 11, 9, ( short ) 13 ) );
		tbitacora.setCellValue( "  " );
		tbitacora.setCellStyle( esceldain );

		HSSFCell tbitacora2 = fila8.createCell( ( short ) 12 );
		tbitacora2.setCellValue( "   " );
		tbitacora2.setCellStyle( esceldain );

		HSSFCell tbitacora3 = fila8.createCell( ( short ) 13 );
		tbitacora3.setCellValue( "   " );
		tbitacora3.setCellStyle( esceldain );
		// *****************************************************************************************************************************************************
		HSSFRow fila9 = hoja.createRow( ( short ) 10 );

		HSSFCell localizacion = fila9.createCell( ( short ) 3 );
		hoja.addMergedRegion( new Region( 10, ( short ) 3, 10, ( short ) 4 ) );
		localizacion.setCellValue( "Localización" );
		localizacion.setCellStyle( esceldain );

		HSSFCell dlocalizacion = fila9.createCell( ( short ) 4 );
		dlocalizacion.setCellValue( "   " );
		dlocalizacion.setCellStyle( esceldain );

		HSSFCell dimenciones = fila9.createCell( ( short ) 5 );
		hoja.addMergedRegion( new Region( 10, ( short ) 5, 10, ( short ) 7 ) );
		dimenciones.setCellValue( "Dimenciones" );
		dimenciones.setCellStyle( esceldain );

		HSSFCell ddimenciones = fila9.createCell( ( short ) 6 );
		ddimenciones.setCellValue( "   " );
		ddimenciones.setCellStyle( esceldain );

		HSSFCell ddimenciones2 = fila9.createCell( ( short ) 7 );
		ddimenciones2.setCellValue( "   " );
		ddimenciones2.setCellStyle( esceldain );
		// ****************************************************************************************************************************************************

		HSSFCell clavePub = fila9.createCell( ( short ) 0 );
		clavePub.setCellValue( "Clave Pública" );
		hoja.addMergedRegion( new Region( 10, ( short ) 0, 11, ( short ) 0 ) );
		clavePub.setCellStyle( esceldain );
		
		HSSFCell clave = fila9.createCell( ( short ) 1 );
		clave.setCellValue( "Clave Privada" );
		hoja.addMergedRegion( new Region( 10, ( short ) 1, 11, ( short ) 1 ) );
		clave.setCellStyle( esceldain );

		HSSFCell ddescripcion = fila9.createCell( ( short ) 2 );
		hoja.addMergedRegion( new Region( 10, ( short ) 2, 11, ( short ) 2 ) );
		ddescripcion.setCellValue( "Descripción" );
		ddescripcion.setCellStyle( esceldain );

		HSSFCell area = fila9.createCell( ( short ) 8 );
		hoja.addMergedRegion( new Region( 10, ( short ) 8, 11, ( short ) 9 ) );
		area.setCellValue( "Área" );
		area.setCellStyle( esceldain );

		HSSFCell area1 = fila9.createCell( ( short ) 9 );
		area1.setCellValue( "" );
		area1.setCellStyle( esceldain );

		HSSFCell veces = fila9.createCell( ( short ) 10 );
		hoja.addMergedRegion( new Region( 10, ( short ) 10, 11, ( short ) 10 ) );
		veces.setCellValue( "Nº Veces" );
		veces.setCellStyle( esceldain );

		HSSFCell piezas = fila9.createCell( ( short ) 11 );
		hoja.addMergedRegion( new Region( 10, ( short ) 11, 11, ( short ) 11 ) );
		piezas.setCellValue( "Nº Piezas" );
		piezas.setCellStyle( esceldain );

		HSSFCell uni = fila9.createCell( ( short ) 12 );
		hoja.addMergedRegion( new Region( 10, ( short ) 12, 11, ( short ) 12 ) );
		uni.setCellValue( "Unidad" );
		uni.setCellStyle( esceldain );

		HSSFCell total = fila9.createCell( ( short ) 13 );
		hoja.addMergedRegion( new Region( 10, ( short ) 13, 11, ( short ) 13 ) );
		total.setCellValue( "Total" );
		total.setCellStyle( esceldain );

		// **************************************************************************************************************

		HSSFRow fila10 = hoja.createRow( ( short ) 11 );
		HSSFCell dclave = fila10.createCell( ( short ) 1 );
		dclave.setCellValue( " " );
		dclave.setCellStyle( esceldain );

		HSSFCell descrip = fila10.createCell( ( short ) 2 );
		descrip.setCellValue( "" );
		descrip.setCellStyle( esceldain );

		HSSFCell eje = fila10.createCell( ( short ) 3 );
		eje.setCellValue( "Eje" );
		eje.setCellStyle( esceldain );

		HSSFCell entre = fila10.createCell( ( short ) 4 );
		entre.setCellValue( "Entre" );
		entre.setCellStyle( esceldain );

		HSSFCell ancho = fila10.createCell( ( short ) 5 );
		ancho.setCellValue( "Ancho" );
		ancho.setCellStyle( esceldain );

		HSSFCell largo = fila10.createCell( ( short ) 6 );
		largo.setCellValue( "Largo" );
		largo.setCellStyle( esceldain );

		HSSFCell alto = fila10.createCell( ( short ) 7 );
		alto.setCellValue( "Alto" );
		alto.setCellStyle( esceldain );

		HSSFCell area2 = fila10.createCell( ( short ) 8 );
		area2.setCellValue( "" );
		area2.setCellStyle( esceldain );

		HSSFCell area3 = fila10.createCell( ( short ) 9 );
		area3.setCellValue( "" );
		area3.setCellStyle( esceldain );

		HSSFCell veces2 = fila10.createCell( ( short ) 10 );
		veces2.setCellValue( " " );
		veces2.setCellStyle( esceldain );

		HSSFCell piezas2 = fila10.createCell( ( short ) 11 );
		piezas2.setCellValue( " " );
		piezas2.setCellStyle( esceldain );

		HSSFCell uni2 = fila10.createCell( ( short ) 12 );
		uni2.setCellValue( "  " );
		uni2.setCellStyle( esceldain );

		HSSFCell total2 = fila10.createCell( ( short ) 13 );
		total2.setCellValue( "  " );
		total2.setCellStyle( esceldain );

		// ****************************************************************************************************************************************************************
		Rgenerador le = new Rgenerador( );
		partida = "";
		float uno, dos, tres, res;

		while ( i < lista.size( ) ) {

			le = ( Rgenerador ) lista.get( i );
			// ******************************************************************************************************************************************************************
			if ( le.getPartida( ).equals( partida ) == false ) {

				partida = le.getPartida( );
				cla = "  ";
				clavePublica = "";
				des = le.getPartida( );
				un = "";
				X = "";
				Y = "";
				Z = "";
				al = "";
				lar = "";
				anc = "";
				pza = "";
				ca = "";
				co = "";
				im = "";

			} else {
				partida = le.getPartida( );
				cla = le.getClave( );
				clavePublica = le.getClavePublica( );
				des = le.getDescripcion( );
				un = le.getUnidad( );
				X = le.getX( );
				Y = le.getY( );
				Z = le.getZ( );
				al = le.getAlto( );
				lar = le.getLargo( );
				anc = le.getAncho( );
				pza = le.getCantidad( );
				ca = " ";
				if ( un.equals( "M2" ) ) {
					uno = Float.parseFloat( lar );
					dos = Float.parseFloat( anc );
					res = uno * dos;
					ca = String.valueOf( res );
				}
				if ( un.equals( "M3" ) ) {
					uno = Float.parseFloat( lar );
					dos = Float.parseFloat( anc );
					tres = Float.parseFloat( al );
					res = ( uno * dos ) * tres;
					ca = String.valueOf( res );
				}

				co = le.getCosto( );
				im = le.getImporte( );
				i++;

			}

			fila11 = hoja.createRow( ( short ) 12 + posi );
			posi++;

			HSSFCell clavePublicaCell= fila11.createCell( ( short ) 0 );
			clavePublicaCell.setCellValue( clavePublica );
			clavePublicaCell.setCellStyle( esceldain );
			
			HSSFCell mclave = fila11.createCell( ( short ) 1 );
			mclave.setCellValue( cla );
			mclave.setCellStyle( esceldain );

			HSSFCell mdescrip = fila11.createCell( ( short ) 2 );
			mdescrip.setCellValue( des );
			mdescrip.setCellStyle( esceldain );
			partida = le.getPartida( );

			HSSFCell meje = fila11.createCell( ( short ) 3 );
			meje.setCellValue( X );
			meje.setCellStyle( esceldain );

			HSSFCell mentre = fila11.createCell( ( short ) 4 );
			mentre.setCellValue( Y + "," + Z );
			mentre.setCellStyle( esceldain );

			HSSFCell mancho = fila11.createCell( ( short ) 5 );
			mancho.setCellValue( anc );
			mancho.setCellStyle( esceldain );

			HSSFCell mlargo = fila11.createCell( ( short ) 6 );
			mlargo.setCellValue( lar );
			mlargo.setCellStyle( esceldain );

			HSSFCell malto = fila11.createCell( ( short ) 7 );
			malto.setCellValue( al );
			malto.setCellStyle( esceldain );

			HSSFCell marea = fila11.createCell( ( short ) 8 );
			hoja.addMergedRegion( new Region( 12 + i, ( short ) 8, 12 + i, ( short ) 9 ) );
			marea.setCellValue( ca );
			marea.setCellStyle( esceldain );

			HSSFCell marea3 = fila11.createCell( ( short ) 9 );
			marea3.setCellValue( " " );
			marea3.setCellStyle( esceldain );

			HSSFCell mveces2 = fila11.createCell( ( short ) 10 );
			mveces2.setCellValue( pza );
			mveces2.setCellStyle( esceldain );

			HSSFCell mpiezas2 = fila11.createCell( ( short ) 11 );
			mpiezas2.setCellValue( pza );
			mpiezas2.setCellStyle( esceldain );

			HSSFCell muni2 = fila11.createCell( ( short ) 12 );
			muni2.setCellValue( un );
			muni2.setCellStyle( esceldain );

			HSSFCell mtotal2 = fila11.createCell( ( short ) 13 );
			mtotal2.setCellValue( im );
			mtotal2.setCellStyle( esceldain );

			HSSFSheet sheet = libro.getSheetAt( 0 );
			sheet.autoSizeColumn( ( short ) 1 );
			sheet.autoSizeColumn( ( short ) 2 );

			// ******************************************************************************************************************************************************************

		}

		// ******************************************************************************************************************************************************************
		try {
			FileOutputStream elFichero = new FileOutputStream( FILE );
			libro.write( elFichero );
			elFichero.close( );
		} catch ( Exception e ) {
			e.printStackTrace( );
		}

	}

}
