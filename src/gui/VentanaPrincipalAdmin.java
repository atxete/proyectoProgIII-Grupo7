package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import domain.GestorUsuarios;
import domain.Producto;
import domain.Producto.tipo;

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
		ventanaPrincipalAdmin.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		ventanaPrincipalAdmin.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Object[] opciones = {"Aceptar", "Cancelar"};
		        int opcion = JOptionPane.showOptionDialog(
		        	ventanaPrincipalAdmin,
		            "¿Está seguro de que desea salir?",
		            "Confirmación de salida",
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE,
		            null,
		            opciones,
		            opciones[1] // Opciones[1] es "Cancelar", el botón seleccionado por defecto
		        );
		        
		        if(opcion == JOptionPane.YES_OPTION) {
		        	ventanaPrincipalAdmin.dispose();
		        	VentanaInicioSesion vis = new VentanaInicioSesion(new GestorUsuarios());
		        	vis.setVisible(true);
		        }
			}
		});
		
		setLayout(new BorderLayout());
		
		
		//cargamos los productos (funcion que generara una lista de productos)
		/* 
		 * productos = crearProductos()
		 */
		List<Producto> productos = new ArrayList();
		Producto p1 = new Producto(tipo.Bebidas, "P1",  1, "imagenes/agua.jpg");
		//p1.setTipo(Producto.tipo.Bebidas);
		Producto p2 = new Producto(tipo.Desayuno, "P2", 2, "imagenes/cerealesFibra.jpg");
		//p2.setTipo(Producto.tipo.Desayuno);
		Producto p3 = new Producto(tipo.Bebidas, "P3", 3, "imagenes/cerveza.jpg");
		//p3.setTipo(Producto.tipo.Bebidas);
		Producto p4 = new Producto(tipo.Desayuno, "P4",  4, "imagenes/donuts.jpg");
		//p4.setTipo(Producto.tipo.Desayuno);
		Producto p5 = new Producto(tipo.CarnePescado, "P5", 4, "imagenes/fileteDeCarne.jpg");
		//p5.setTipo(Producto.tipo.CarnePescado);
		Producto p6 = new Producto(tipo.CarnePescado, "P6", 4, "imagenes/merluza.jpg");
		//p6.setTipo(Producto.tipo.CarnePescado);
		Producto p7 = new Producto(tipo.CarnePescado, "P7",  4, "imagenes/entrecot.jpg");
		//p7.setTipo(Producto.tipo.CarnePescado);
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
		
		
		
		VentanaPrincipalAdmin ventPrinAdmin = this;
		
		
		
		for(Producto p: productos) {
			JLabel nombreProducto = new JLabel("Nombre: " + p.getNombre());
			JLabel precioProducto = new JLabel("Precio: "+p.getPrecio());
			
			ImageIcon imagenProducto = new ImageIcon(p.getFoto());
			Image imagenRedimensionada = imagenProducto.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			ImageIcon imagen = new ImageIcon(imagenRedimensionada);
			JLabel lblFoto = new JLabel(imagen);
			lblFoto.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					dispose();
					new VentanaProductoAdmin(/*p*/ ventPrinAdmin);
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
