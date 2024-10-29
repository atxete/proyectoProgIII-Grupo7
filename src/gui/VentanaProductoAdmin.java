package gui;

import java.awt.BorderLayout;
<<<<<<< HEAD
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.Producto;

public class VentanaProductoAdmin extends JFrame{
	
	protected JPanel pnlProductos;
	protected JButton btnAnyadirProd;
	protected JPanel pnlBotones;
	
	public VentanaProductoAdmin() {
		
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
		productos.add(new Producto("P2", "id2", 2, "foto2"));
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
		
		pnlBotones = new JPanel();
		
		pnlBotones.add(btnAnyadirProd);
		
		this.add(pnlProductos, BorderLayout.CENTER);
		this.add(pnlBotones, BorderLayout.SOUTH);
		
		
		setVisible(true);
	}
	public static void main(String[] args) {
		new VentanaProductoAdmin();
		
	}
=======
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
>>>>>>> branch 'master' of git@github.com:atxete/proyectoProgIII-Grupo7.git

import javax.swing.*;

public class VentanaProductoAdmin extends JFrame{
	
	JPanel pnlBotones;
	JPanel pnlInfoProd;
	JPanel pnlFoto;
	JPanel pnlCentro;
	JButton btnVolver;
	JButton btnModNombre;
	JButton btnModPrecio;
	JButton btnModFoto;
	JLabel lblNombreProd;
	JLabel lblPrecioProd;
	ImageIcon imagenProducto;
	JLabel lblFoto;
	
	
	public VentanaProductoAdmin(/*Producto p*/) {
		setTitle("Ventana Producto Administrador");
		setBounds(300, 100, 700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		/**SE PUEDE AÑADIR EN EL PANEL NORTE DE LA VENTANA EL LOGO DEL SUPERMERCADO**/
		
		pnlBotones = new JPanel(new GridLayout(1, 4));
		pnlInfoProd = new JPanel(new GridLayout(1,2));
		pnlFoto = new JPanel();
		pnlCentro = new JPanel(new GridLayout(2,1));
		btnVolver = new JButton("VOLVER");
		btnModNombre = new JButton("Cambiar Nombre");
		btnModPrecio = new JButton("Cambiar Precio");
		btnModFoto = new JButton("Cambiar Foto");
		lblNombreProd = new JLabel("Nombre Producto"  /*p.getNombre()*/);
		lblPrecioProd = new JLabel("Precio Producto"  /*p.getPrecio()*/);
		
		imagenProducto = new ImageIcon("imagenes/Logo.jpg");
		Image imagenRedimensionada = imagenProducto.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		ImageIcon imagenRedimensionadaIcono = new ImageIcon(imagenRedimensionada);
		lblFoto = new JLabel(imagenRedimensionadaIcono);
		
		pnlInfoProd.add(lblNombreProd);
		pnlInfoProd.add(lblPrecioProd);
		pnlFoto.add(lblFoto);
		pnlCentro.add(pnlFoto);
		pnlCentro.add(pnlInfoProd);
		getContentPane().add(pnlCentro, BorderLayout.CENTER);
		
		pnlBotones.add(btnVolver);
		pnlBotones.add(btnModNombre);
		pnlBotones.add(btnModPrecio);
		pnlBotones.add(btnModFoto);
		getContentPane().add(pnlBotones, BorderLayout.SOUTH);
		
		getContentPane().setBackground(Color.WHITE);
		pnlCentro.setBackground(Color.WHITE);
		pnlInfoProd.setBackground(Color.WHITE);
		pnlFoto.setBackground(Color.WHITE);
		
		
		
		
		btnVolver.addActionListener((e)->{
			new VentanaPrincipalAdmin();
			dispose();
		});
		
		btnModNombre.addActionListener((e)->{
			String nuevoNombre = JOptionPane.showInputDialog("Introduce el nuevo nombre para este producto");
			
			if(nuevoNombre!=null && !nuevoNombre.trim().isEmpty()) {
				/*p.setNombre(nuevoNombre);*/
				JOptionPane.showMessageDialog(null, "El nombre del producto se ha cambiado a: "+nuevoNombre);
			}else {
		        JOptionPane.showMessageDialog(null, "No se realizó ningún cambio en el nombre del producto.", 
		                                      "Cambio Cancelado", JOptionPane.WARNING_MESSAGE);
		    }
		});
		
		btnModPrecio.addActionListener((e)->{
			
			 String nuevoPrecioStr = JOptionPane.showInputDialog("Introduce el nuevo precio para este producto");

			
			if (nuevoPrecioStr != null && !nuevoPrecioStr.trim().isEmpty()) { 
		        try {
		            Float nuevoPrecio = Float.parseFloat(nuevoPrecioStr);

		            
		            // p.setPrecio(nuevoPrecio);

		            
		            JOptionPane.showMessageDialog(null, "El precio del producto se ha cambiado a: " + nuevoPrecio);
		        } catch (NumberFormatException ex) {
		            
		            JOptionPane.showMessageDialog(null, "Por favor, introduce un valor numérico válido para el precio.", 
		                                          "Error de entrada", JOptionPane.ERROR_MESSAGE);
		        }
		    } else {
		        
		        JOptionPane.showMessageDialog(null, "No se realizó ningún cambio en el precio del producto.", 
		                                      "Cambio Cancelado", JOptionPane.WARNING_MESSAGE);
		    }
		});
		
		btnModFoto.addActionListener((e)->{
			String nuevaFoto = JOptionPane.showInputDialog("Introduce el source de la nueva imagen para este producto");
			
			
			if (nuevaFoto != null && !nuevaFoto.trim().isEmpty()) { 
		        ImageIcon nuevaImagenIcon = new ImageIcon(nuevaFoto);

		        
		        if (nuevaImagenIcon.getIconWidth() > 0) {
		            
		            // p.setFoto(nuevaFoto);
		            
		            
		            JOptionPane.showMessageDialog(null, "La imagen del producto se ha actualizado.");
		        } else {
		            
		            JOptionPane.showMessageDialog(null, "La ruta proporcionada no es válida o no corresponde a una imagen.", 
		                                          "Error en la imagen", JOptionPane.ERROR_MESSAGE);
		        }
		    } else {
		        
		        JOptionPane.showMessageDialog(null, "No se realizó ningún cambio en la imagen del producto.", 
		                                      "Cambio Cancelado", JOptionPane.WARNING_MESSAGE);
		    }
			
		});
		
		
		
		setVisible(true);
		
	}
	public static void main(String[] args) {
		new VentanaProductoAdmin();
	}
}
