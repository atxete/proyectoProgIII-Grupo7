package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import domain.Producto;

public class VentanaPrincipalAdmin extends JFrame{
	
	protected JPanel pnlProductos;
	protected JButton btnAnyadirProd;
	protected JPanel pnlBotones;
	protected List<Producto> productos;
	protected VentanaPrincipalAdmin ventanaPrincipalAdmin;
	
	public VentanaPrincipalAdmin() {
		ventanaPrincipalAdmin = this;
		
		setBounds(300, 100, 700, 500);
		setTitle("Ventana Principal Admin");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		
		//cargamos los productos (funcion que generara una lista de productos)
		/* 
		 * productos = crearProductos()
		 */
		List<Producto> productos = new ArrayList();
		Producto p1 = new Producto("P1", "id1", 1, "imagenes/Logo.jpg");
		p1.setTipo(Producto.tipo.Bebidas);
		Producto p2 = new Producto("P2", "id2", 2, "imagenes/Logo.jpg");
		p2.setTipo(Producto.tipo.Desayuno);
		Producto p3 = new Producto("P3", "id3", 3, "imagenes/Logo.jpg");
		p3.setTipo(Producto.tipo.Bebidas);
		Producto p4 = new Producto("P4", "id4", 4, "imagenes/Logo.jpg");
		p4.setTipo(Producto.tipo.Desayuno);
		Producto p5 = new Producto("P5", "id4", 4, "imagenes/Logo.jpg");
		p5.setTipo(Producto.tipo.CarnePescado);
		Producto p6 = new Producto("P6", "id4", 4, "imagenes/Logo.jpg");
		p6.setTipo(Producto.tipo.CarnePescado);
		Producto p7 = new Producto("P7", "id4", 4, "imagenes/Logo.jpg");
		p7.setTipo(Producto.tipo.CarnePescado);
		productos.add(p1);
		productos.add(p2);
		productos.add(p3);
		productos.add(p4);
		productos.add(p5);
		productos.add(p6);
		productos.add(p7);
		
		pnlProductos = new JPanel(new GridLayout(0, 2, 10, 10));
		JScrollPane scrollProductos = new JScrollPane(pnlProductos);
		scrollProductos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		for(Producto p: productos) {
			JLabel nombreProducto = new JLabel("Nombre: " + p.getNombre());
			JLabel precioProducto = new JLabel("Precio: "+p.getPrecio());
			
			ImageIcon imagenProducto = new ImageIcon(p.getFoto());
			Image imagenRedimensionada = imagenProducto.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			ImageIcon imagen = new ImageIcon(imagenRedimensionada);
			JLabel lblFoto = new JLabel(imagen);
			lblFoto.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					new VentanaProductoAdmin(/*p*/);
				}
			});
			
			JPanel pnlPrincipal = new JPanel(new BorderLayout());
			pnlPrincipal.setPreferredSize(new Dimension(200,100));
			JPanel pnlCentral = new JPanel();
	        
	        pnlCentral.add(nombreProducto);
	        pnlCentral.add(precioProducto);
	        
	        pnlPrincipal.add(lblFoto, BorderLayout.NORTH);
	        pnlPrincipal.add(pnlCentral, BorderLayout.SOUTH);
	        
	        pnlProductos.add(pnlPrincipal);
		}
		
		btnAnyadirProd = new JButton("Añadir Producto");
		btnAnyadirProd.addActionListener((e) ->{
			new VentanaAnadirProductoAdmin(ventanaPrincipalAdmin);
		});
		
		pnlBotones = new JPanel();
		
		pnlBotones.add(btnAnyadirProd);
		this.add(scrollProductos, BorderLayout.CENTER);
		this.add(pnlBotones, BorderLayout.SOUTH);
		
		
		setVisible(true);
	}
	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	public static void main(String[] args) {
		new VentanaPrincipalAdmin();
		
	}
}
