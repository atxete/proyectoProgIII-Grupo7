package gui;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import domain.Producto;

public class ModeloFavoritosUsuario extends DefaultTableModel{
	private List<Producto> listaProductos;
	private List<String> listaTitulos = Arrays.asList("Código", "Nombre", "Precio",  "Precio final");

	@Override
	public boolean isCellEditable(int row, int column) {
		
		return false;
	}


	public ModeloFavoritosUsuario(List<Producto> lp) {
		this.listaProductos = lp;
	}


	@Override
	public void addRow(Object[] rowData) {
		super.addRow(rowData);
	}


	@Override
	public void removeRow(int row) {
		
		super.removeRow(row);
	}
 

	@Override
	public int getRowCount() {
		if (listaProductos== null) {
			return 0;
		}else {
			return listaProductos.size();
		}
	}
	
	@Override
	public int getColumnCount() {
		return listaTitulos.size();
	}


	@Override
	public String getColumnName(int column) {
		return listaTitulos.get(column);
	}

	
	@Override
	public Object getValueAt(int row, int column) {
		Producto p = listaProductos.get(row);
		switch(column) {
			case 0: return p.getCodigo();
			case 1: return p.getNombre();
			case 2: return p.getPrecio();
			case 3: return p.getPrecio();
			default: return null;
		}
			
		
	}


	@Override
	public void setValueAt(Object aValue, int row, int column) {
		// TODO Auto-generated method stub
		super.setValueAt(aValue, row, column);
	}
	
	
}
