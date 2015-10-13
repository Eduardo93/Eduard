package Manejodetablas2;

import javax.swing.table.*;
import javax.swing.event.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.LinkedList;

import ObjetosSerializables.Rgenerador;

/**
 * 
 * @author luiiis
 *
 */
public class ModeloTablaGenerador implements TableModel {

	private LinkedList<Rgenerador> datos = new LinkedList<Rgenerador>();
	private LinkedList<TableModelListener> listeners = new LinkedList<TableModelListener>();
	private Rgenerador asp;
	private boolean editable = true;

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return 15;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return datos.size();
	}

	/**
	 * 
	 */
	public void Limpiar() {
		this.datos = new LinkedList<Rgenerador>();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		Rgenerador aux;
		aux = (Rgenerador) (datos.get(rowIndex));
		switch (columnIndex) {
		case 0:
			return aux.getClave();
		case 1:
			return aux.getDescripcion();
		case 2:
			return aux.getUnidad();
		case 3:
			return aux.getX();
		case 4:
			return aux.getY();
		case 5:
			return aux.getZ();
		case 6:
			return aux.getAlto();
		case 7:
			return aux.getLargo();
		case 8:
			return aux.getAncho();
		case 9:
			return aux.getCantidad();
		case 10:
			return aux.getPiezas();
		case 11:
			return aux.getCosto();
		case 12:
			return aux.getImporte();
		case 13:
			return aux.getFecha();
		case 14:
			return aux.getClavePublica();
		default:
			return null;
		}
	}

	/**
	 * 
	 * @param fila
	 * @param veces
	 */
	public void borraAspecto(int fila, int veces) {
		int b = veces;
		while (b > 0) {
			datos.remove(fila);
			TableModelEvent evento = new TableModelEvent(this, fila, fila, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
			avisaSuscriptores(evento);
			b--;
		}
	}

	/**
	 * 
	 * @param asp
	 */
	public void anhadeAspecto(Rgenerador asp) {
		datos.add(asp);
		TableModelEvent evento;
		evento = new TableModelEvent(this, this.getRowCount() - 1, this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
		avisaSuscriptores(evento);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#addTableModelListener(javax.swing.event.TableModelListener)
	 */
	public void addTableModelListener(TableModelListener l) {
		listeners.add(l);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnClass(int)
	 */
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
		// return getValueAt( 0, columnIndex ).getClass( );
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnName(int)
	 */
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "Clave";
		case 1:
			return "Descripción";
		case 2:
			return "Unidad";
		case 3:
			return "       X";
		case 4:
			return "       Y";
		case 5:
			return "       Z";
		case 6:
			return "    Alto";
		case 7:
			return "    Largo";
		case 8:
			return "    Ancho";
		case 9:
			return "Cantidad";
		case 10:
			return "Piezas";
		case 11:
			return "Costo";
		case 12:
			return "Importe";
		case 13:
			return "Fecha";
		case 14:
			return "Clave Pública";
		default:
			return null;
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		boolean ban = false;
		if (editable == true) {
			if (columnIndex == 0 || columnIndex == 1 || columnIndex == 2 || columnIndex == 11 || columnIndex == 12 || columnIndex == 9 || columnIndex == 13 || columnIndex == 14) {
				ban = false;
			} else {
				ban = true;
				asp = (Rgenerador) datos.get(rowIndex);
				if (asp.getUnidad().equals("m2") == true || asp.getUnidad().equals("M2") == true) {
					if (columnIndex == 6) {
						ban = false;
					}
				}
				if (asp.getUnidad().equals("ML") == true || asp.getUnidad().equals("ml") == true) {
					if (columnIndex == 6 || columnIndex == 8) {
						ban = false;
					}
				}
				if (asp.getUnidad().equals("pieza") == true || asp.getUnidad().equals("PZA") == true) {
					if (columnIndex == 6 || columnIndex == 8 || columnIndex == 7) {
						ban = false;
					}
				}
				if (asp.getUnidad().equals("kg") == true || asp.getUnidad().equals("ton") == true || asp.getUnidad().equals("LOTE") == true || asp.getUnidad().equals("SALIDA") == true) {
					if (columnIndex == 6 || columnIndex == 8 || columnIndex == 7) {
						ban = false;
					}
				}
			}
		} else {
			ban = false;
		}
		return ban;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#removeTableModelListener(javax.swing.event.TableModelListener)
	 */
	public void removeTableModelListener(TableModelListener l) {
		listeners.remove(l);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#setValueAt(java.lang.Object, int, int)
	 */
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat formateador = new DecimalFormat("####.##", simbolos);
		
		String alto = "0.0", largo = "0.0", ancho = "0.0", cantidad = "0.0", precio = "0.0";
		float res = 0, al = 0, an = 0, la = 0, ca = 0, pre = 0, can = 0;
		
		String unidad = "";
		
		Rgenerador aux;
		
		aux = (Rgenerador) (datos.get(rowIndex));
		unidad = aux.getUnidad();
		if (columnIndex == 3) {
			aux.setX((String) aValue);
		}
		if (columnIndex == 4) {
			aux.setY((String) aValue);
		}
		if (columnIndex == 5) {
			aux.setZ((String) aValue);
		}
		if (columnIndex == 6) {
			aux.setAlto((String) aValue);
			alto = aux.getAlto();
			al = Float.parseFloat(alto);
			largo = aux.getLargo();
			la = Float.parseFloat(largo);
			ancho = aux.getAncho();
			an = Float.parseFloat(ancho);
			cantidad = aux.getPiezas();
			ca = Float.parseFloat(cantidad);
			if ((unidad.equals("m2") == true || unidad.equals("M2") == true) && an > 0 && la > 0 && ca > 0) {
				precio = aux.getCosto();
				pre = Float.parseFloat(precio);
				can = la * an;
				aux.setCantidad(formateador.format(can));
				res = ((can) * ca) * pre;
				aux.setImporte(formateador.format(res));
			}
			if ((unidad.equals("m3") == true) && (al > 0) && (la > 0) && (an > 0) && (ca > 0)) {
				precio = aux.getCosto();
				pre = Float.parseFloat(precio);
				can = (la * an) * al;
				aux.setCantidad(formateador.format(can));
				res = ((can) * ca) * pre;
				aux.setImporte(formateador.format(res));
			}
			if ((unidad.equals("kg") == true || unidad.equals("ton") == true || unidad.equals("LOTE") == true || unidad.equals("SALIDA") == true) && ca > 0) {
				precio = aux.getCosto();
				pre = Float.parseFloat(precio);
				can = ca;
				aux.setCantidad(formateador.format(can));
				res = ca * pre;
				aux.setImporte(formateador.format(res));
			}
			if ((unidad.equals("ML") == true || unidad.equals("ml") == true || unidad.equals("pieza") == true || unidad.equals("PZA") == true) && ca > 0) {
				precio = aux.getCosto();
				pre = Float.parseFloat(precio);
				can = ca;
				aux.setCantidad(formateador.format(can));
				res = ca * pre;
				aux.setImporte(formateador.format(res));
			}
			// *****************************************************************************************************************
		}
		if (columnIndex == 7) {
			aux.setLargo((String) aValue);
			// *******************************************************************************************************
			alto = aux.getAlto();
			al = Float.parseFloat(alto);
			largo = aux.getLargo();
			la = Float.parseFloat(largo);
			ancho = aux.getAncho();
			an = Float.parseFloat(ancho);
			cantidad = aux.getPiezas();
			ca = Float.parseFloat(cantidad);
			if ((unidad.equals("m2") == true || unidad.equals("M2") == true) && an > 0 && la > 0 && ca > 0) {
				precio = aux.getCosto();
				pre = Float.parseFloat(precio);
				can = la * an;
				aux.setCantidad(formateador.format(can));
				res = ((can) * ca) * pre;
				aux.setImporte(formateador.format(res));
			}
			if ((unidad.equals("m3") == true) && (al > 0) && (la > 0) && (an > 0) && (ca > 0)) {
				precio = aux.getCosto();
				pre = Float.parseFloat(precio);
				can = (la * an) * al;
				aux.setCantidad(formateador.format(can));
				res = ((can) * ca) * pre;
				aux.setImporte(formateador.format(res));
			}
			if ((unidad.equals("kg") == true || unidad.equals("ton") == true || unidad.equals("LOTE") == true || unidad.equals("SALIDA") == true) && ca > 0) {
				precio = aux.getCosto();
				pre = Float.parseFloat(precio);
				can = ca;
				aux.setCantidad(formateador.format(can));
				res = ca * pre;
				aux.setImporte(formateador.format(res));
			}
			if ((unidad.equals("ML") == true || unidad.equals("ml") == true || unidad.equals("pieza") == true || unidad.equals("PZA") == true) && ca > 0) {
				precio = aux.getCosto();
				pre = Float.parseFloat(precio);
				can = ca;
				aux.setCantidad(formateador.format(can));
				res = ca * pre;
				aux.setImporte(formateador.format(res));
			}
			// *****************************************************************************************************************
		}
		if (columnIndex == 8) {
			aux.setAncho((String) aValue);
			// *******************************************************************************************************
			alto = aux.getAlto();
			al = Float.parseFloat(alto);
			largo = aux.getLargo();
			la = Float.parseFloat(largo);
			ancho = aux.getAncho();
			an = Float.parseFloat(ancho);
			cantidad = aux.getPiezas();
			ca = Float.parseFloat(cantidad);
			if ((unidad.equals("m2") == true || unidad.equals("M2") == true) && an > 0 && la > 0 && ca > 0) {
				precio = aux.getCosto();
				pre = Float.parseFloat(precio);
				can = la * an;
				aux.setCantidad(formateador.format(can));
				res = ((can) * ca) * pre;
				aux.setImporte(String.valueOf(res % 2f));
			}
			if ((unidad.equals("m3") == true) && (al > 0) && (la > 0) && (an > 0) && (ca > 0)) {
				precio = aux.getCosto();
				pre = Float.parseFloat(precio);
				can = (la * an) * al;
				aux.setCantidad(formateador.format(can));
				res = ((can) * ca) * pre;
				aux.setImporte(formateador.format(res));
			}
			if ((unidad.equals("kg") == true || unidad.equals("ton") == true || unidad.equals("LOTE") == true || unidad.equals("SALIDA") == true) && ca > 0) {
				precio = aux.getCosto();
				pre = Float.parseFloat(precio);
				can = ca;
				aux.setCantidad(formateador.format(can));
				res = ca * pre;
				aux.setImporte(formateador.format(res));
			}
			if ((unidad.equals("ML") == true || unidad.equals("ml") == true || unidad.equals("pieza") == true || unidad.equals("PZA") == true) && ca > 0) {
				precio = aux.getCosto();
				pre = Float.parseFloat(precio);
				can = ca;
				aux.setCantidad(formateador.format(can));
				res = ca * pre;
				aux.setImporte(formateador.format(res));
			}
			// *****************************************************************************************************************
		}
		if (columnIndex == 10) {
			aux.setPiezas((String) aValue);
			// *******************************************************************************************************
			alto = aux.getAlto();
			al = Float.parseFloat(alto);
			largo = aux.getLargo();
			la = Float.parseFloat(largo);
			ancho = aux.getAncho();
			an = Float.parseFloat(ancho);
			cantidad = aux.getPiezas();
			ca = Float.parseFloat(cantidad);
			if ((unidad.equals("m2") == true || unidad.equals("M2") == true) && an > 0 && la > 0 && ca > 0) {
				precio = aux.getCosto();
				pre = Float.parseFloat(precio);
				can = la * an;
				aux.setCantidad(formateador.format(can));
				res = ((can) * ca) * pre;
				aux.setImporte(formateador.format(res));
			}
			if ((unidad.equals("m3") == true)) {
				precio = aux.getCosto();
				pre = Float.parseFloat(precio);
				can = (la * an) * al;
				aux.setCantidad(formateador.format(can));
				res = ((can) * ca) * pre;
				aux.setImporte(formateador.format(res));
			}
			if ((unidad.equals("kg") == true || unidad.equals("ton") == true || unidad.equals("LOTE") == true || unidad.equals("SALIDA") == true) && ca > 0) {
				precio = aux.getCosto();
				pre = Float.parseFloat(precio);
				can = ca;
				aux.setCantidad(formateador.format(can));
				res = ca * pre;
				aux.setImporte(formateador.format(res));
			}
			if ((unidad.equals("ML") == true || unidad.equals("ml") == true || unidad.equals("pieza") == true || unidad.equals("PZA") == true) && ca > 0) {
				precio = aux.getCosto();
				pre = Float.parseFloat(precio);
				can = ca;
				aux.setCantidad(formateador.format(can));
				res = ca * pre;
				aux.setImporte(formateador.format(res));
			}
			// *****************************************************************************************************************
		}
		TableModelEvent evento = new TableModelEvent(this, rowIndex, rowIndex, columnIndex);
		avisaSuscriptores(evento);
	}

	/**
	 * 
	 * @param evento
	 */
	private void avisaSuscriptores(TableModelEvent evento) {
		int i;
		for (i = 0; i < listeners.size(); i++)
			listeners.get(i).tableChanged(evento);
	}

	/**
	 * 
	 * @return
	 */
	public LinkedList<Rgenerador> getListaDatos() {
		return this.datos;
	}

	/**
	 * 
	 * @param asp
	 * @param indice
	 */
	public void anhadeAspectoAbajo(Rgenerador asp, int indice) {
		datos.add(indice + 1, asp);
		TableModelEvent evento;
		evento = new TableModelEvent(this, indice + 1, indice + 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
		avisaSuscriptores(evento);
	}

	/**
	 * 
	 * @param editable
	 */
	public void Editable(boolean editable) {
		this.editable = editable;
	}
}