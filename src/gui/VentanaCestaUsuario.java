package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import domain.Producto;

public class VentanaCestaUsuario extends JFrame{
	private ModeloCestaUsuario modeloCestaUsuario;
	private JTable tabla;
	private JScrollPane scrolltabla;
		
	public VentanaCestaUsuario() {
		super();
		setBounds(300, 200, 600, 400);
		
		List<Producto> productos = new ArrayList<>();
		modeloCestaUsuario = new ModeloCestaUsuario(productos);
		tabla = new JTable(modeloCestaUsuario);
		scrolltabla = new JScrollPane(tabla);
		
		getContentPane().add(scrolltabla, BorderLayout.CENTER);
		
		
		
		tabla.setDefaultRenderer(Object.class, new TableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				
				return null;
			}
		});
		
		
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new VentanaCestaUsuario();
		

	
	}
	
}
