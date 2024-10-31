package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import domain.Producto;

public class VentanaPrincipalAdmin extends JFrame{
	
	protected JPanel pnlProductos;
	protected JButton btnAnyadirProd;
	protected JPanel pnlBotones;
	
	public VentanaPrincipalAdmin() {
		
		setBounds(300, 100, 700, 500);
		setTitle("Ventana Principal Admin");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		
		//cargamos los productos (funcion que generara una lista de productos)
		/* 
		 * productos = supermercado.crearProductos()
		 */
		List<Producto> productos = new ArrayList();
		productos.add(new Producto("P1", "id1", 1, "foto1"));
		
		pnlProductos = new JPanel(new GridLayout(2, productos.size()));
		
		for(Producto p: productos) {
			JLabel nombreProducto = new JLabel("Nombre: " + p.getNombre());
			JLabel precioProducto = new JLabel("Precio: "+p.getPrecio());
			
			//ImageIcon imagenProducto = new ImageIcon(p.getFoto());
			//JLabel lblFoto = new JLabel(imagenProducto);
			
			JPanel pnlPrincipal = new JPanel();
			JPanel pnlCentral = new JPanel();
	        
	        pnlCentral.add(nombreProducto);
	        pnlCentral.add(precioProducto);
	        
	        //pnlPrincipal.add(lblFoto, BorderLayout.NORTH);
	        pnlPrincipal.add(pnlCentral, BorderLayout.CENTER);
	        
	        pnlProductos.add(pnlPrincipal);
		}
		
		btnAnyadirProd = new JButton("Añadir Producto");
		btnAnyadirProd.addActionListener((e) ->{
			new VentanaAnadirProductoAdmin();
		});
		
		pnlBotones = new JPanel();
		
		pnlBotones.add(btnAnyadirProd);
		this.add(pnlProductos, BorderLayout.CENTER);
		this.add(pnlBotones, BorderLayout.SOUTH);
		
		
		setVisible(true);
	}
	public static void main(String[] args) {
		new VentanaPrincipalAdmin();
		
	}
}
