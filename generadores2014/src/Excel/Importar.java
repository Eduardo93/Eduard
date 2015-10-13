
package Excel;

import ObjetosSerializables.Objetoexecel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class Importar {

	private int Y = 0;
	private boolean Run = true;

	public LinkedList < Objetoexecel > leer_archivo_exel ( String archivo ) {

		LinkedList < Objetoexecel > lista_aspectos = new LinkedList < Objetoexecel >( );

		try {
			// Abrir Archivo de MICROSOFT a analizar.
			POIFSFileSystem fs = new POIFSFileSystem( new FileInputStream( archivo ) );

			// Se obtiene libro de Excel.
			HSSFWorkbook LibroAnalizar = new HSSFWorkbook( fs );

			// Se obtiene la primera hoja de el libro a analizar.
			HSSFSheet HojaAnalizar = LibroAnalizar.getSheetAt( 0 );

			// Se declara variable para la celda.
			HSSFCell titulo, guion, clave, descripcion, unidad, costo;
			HSSFRow FilaLibroAnalizar;

			Objetoexecel objeto;

			while ( Run == true ) {
				FilaLibroAnalizar = HojaAnalizar.getRow( Y );
				if ( FilaLibroAnalizar != null ) {
					titulo = FilaLibroAnalizar.getCell( ( short ) 0 );
					guion = FilaLibroAnalizar.getCell( ( short ) 1 );
					clave = FilaLibroAnalizar.getCell( ( short ) 2 );
					descripcion = FilaLibroAnalizar.getCell( ( short ) 3 );
					unidad = FilaLibroAnalizar.getCell( ( short ) 4 );
					costo = FilaLibroAnalizar.getCell( ( short ) 5 );

					objeto = new Objetoexecel( );

					if ( titulo != null ) {
						objeto.setTitulo( titulo.toString( ) );
					} else {
						objeto.setTitulo( "" );
					}
					if ( guion != null ) {
						objeto.setGuion( guion.toString( ) );
					} else {
						objeto.setGuion( "" );
					}
					if ( clave != null ) {
						objeto.setClave( clave.toString( ) );
					} else {
						objeto.setClave( "" );
					}
					if ( descripcion != null ) {
						objeto.setDescripcion( descripcion.toString( ) );
					} else {
						objeto.setDescripcion( "" );
					}
					if ( unidad != null ) {
						objeto.setUnidad( unidad.toString( ) );
					} else {
						objeto.setUnidad( "no tiene" );
					}
					if ( costo != null ) {
						objeto.setCosto( costo.toString( ) );
					} else {
						objeto.setCosto( "0.00" );
					}

					lista_aspectos.add( objeto );

					if ( clave == null && descripcion == null ) {
						Run = false;
					} else {
						Y++;
					}
				} else {
					Run = false;
				}
			}
		} catch ( IOException e ) {
			JOptionPane.showMessageDialog( null, "verifica que sea extecion xls", "Error", JOptionPane.ERROR_MESSAGE );
		}

		return lista_aspectos;

	}

}
