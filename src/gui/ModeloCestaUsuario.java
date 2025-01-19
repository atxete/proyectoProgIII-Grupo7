package gui;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import domain.Producto;

public class ModeloCestaUsuario extends DefaultTableModel{

	
	//  dz
	
	private List<Producto> listaProductos;
	private List<String> listaTitulos = Arrays.asList("Código", "Nombre", "Precio", "Cantidad","Precio final");
	
	
	
	public ModeloCestaUsuario(List<Producto> lp) {
		listaProductos = lp; 		
	}



	@Override
	public void addRow(Object[] rowData) {
		// TODO Auto-generated method stub
		super.addRow(rowData);
	}

 

	@Override
	public void removeRow(int row) {
		// TODO Auto-generated method stub
		super.removeRow(row);
	}



	@Override
	public int getRowCount() {
		if(listaProductos == null) {
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
		// TODO Auto-generated method stub
		return listaTitulos.get(column);
	}
	
	



	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}



	@Override  
	public Object getValueAt(int row, int column) {
		Producto p = listaProductos.get(row);
		switch (column){
			case 0: return p.getCodigo();
			case 1: return p.getNombre();
			case 2: return p.getPrecio();
			case 3: return p.getCantidad();
			case 4: return p.getCantidad()*p.getPrecio();
			default: return null;
			} 
		

	}



	@Override
	public void setValueAt(Object aValue, int row, int column) {
		// TODO Auto-generated method stub
		super.setValueAt(aValue, row, column);
	}
		
}
