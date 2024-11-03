package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import domain.Producto;

public class VentanaCestaUsuario extends JFrame{
	private ModeloCestaUsuario modeloCestaUsuario;
	private JTable tabla;
	private JScrollPane scrolltabla;
	private JPanel panelBotones, panelTotal;
	private JButton botonAniadirFavoritos, botonPagar, botonSeguirComprando;
	private JLabel pieDePagina, textoTotal, valorTotal;
	private List<Producto> productos;
		
	public VentanaCestaUsuario() {
		super();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		productos = new ArrayList<>();
		modeloCestaUsuario = new ModeloCestaUsuario(productos);
		tabla = new JTable(modeloCestaUsuario);
		scrolltabla = new JScrollPane(tabla);
		getContentPane().add(scrolltabla, BorderLayout.CENTER);
		
		panelTotal = new JPanel();
		textoTotal = new JLabel("Total a pagar: ");
		valorTotal = new JLabel("0.00€");
		
		panelTotal.add(textoTotal);
		panelTotal.add(valorTotal);
		
		
		getContentPane().add(panelTotal, BorderLayout.SOUTH);
		
		panelBotones = new JPanel();
		
		//Creamos los 3 botones
		botonAniadirFavoritos = new JButton("Añadir a favoritos");
		botonPagar = new JButton("Pagar");
		botonSeguirComprando = new JButton("Seguir comprando");
		//Los añadimos al panel
		panelBotones.add(botonAniadirFavoritos);
		panelBotones.add(botonPagar);
		panelBotones.add(botonSeguirComprando);
		//Añadimos el panel a la ventana
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		 
		botonPagar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(VentanaCestaUsuario.this, "Compra pagada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
		botonSeguirComprando.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipalUsuario ventanausuario = new VentanaPrincipalUsuario();
				ventanausuario.setVisible(true);
				dispose();
				
			}
		});
		
		
		
		//Hay que revisar la foto!!!!!!
		ImageIcon logo = new ImageIcon("C:\\Users\\usuario\\trabajoProg3\\proyectoProgIII-Grupo7\\imagenes\\Logo.jpg");
		pieDePagina = new JLabel(logo);
		
		getContentPane().add(pieDePagina, BorderLayout.NORTH);
		
		tabla.setDefaultRenderer(Object.class, new TableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				
				return null;
			}
		});
		
		
		setVisible(true);
	}
	
	public void anyadirProducto(Producto p) {
		productos.add(p);
	}
	
	public static void main(String[] args) {
		new VentanaCestaUsuario();
		

	
	}
	
}
