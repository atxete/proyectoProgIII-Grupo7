package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import domain.Producto;
import javax.swing.*;

public class VentanaProductoUsuario extends JFrame{
	
	protected JLabel nombreProducto;
	protected JLabel precioProducto;
	protected JLabel cantidadProducto;
	protected ImageIcon imagenProducto;
	protected JLabel lblFoto;
	protected JButton btnVolver;
	protected JButton btnAnyadirWish;
	protected JButton btnAnyadirCesta;
	protected JPanel pnlIzq;
	protected JPanel pnlDerecha;
	protected JPanel pnlBotones;
	protected JSpinner spinnerCantidad;
	
	public VentanaProductoUsuario(/**Producto p**/) {
		
		setBounds(200, 100, 500, 500);
		
		/**nombreProducto = new JLabel("Nombre: " + p.getNombre());
		precioProducto = new JLabel("Precio: "+p.getPrecio());
		cantidadProducto = new JLabel("Canridad: "+ new JSpinner());
		imagenProducto = new ImageIcon(p.getFoto());
		lblFoto = new JLabel(imagenProducto);
		pnlIzq = new JPanel();
		pnlDerecha = new JPanel();
		pnlBotones = new JPanel();
		btnAnyadirCesta = new JButton("AÑADIR A LA CESTA");
		btnAnyadirWish = new JButton("AÑADIR A FAVORITOS");
		btnVolver = new JButton("VOLVER");**/
		
		nombreProducto = new JLabel("Nombre: " );
		precioProducto = new JLabel("Precio: ");
		cantidadProducto = new JLabel("Cantidad: ");
		//imagenProducto = new ImageIcon(p.getFoto());
		//lblFoto = new JLabel(imagenProducto);
		pnlIzq = new JPanel();
		pnlDerecha = new JPanel();
		pnlBotones = new JPanel();
		btnAnyadirCesta = new JButton("AÑADIR A LA CESTA");
		btnAnyadirWish = new JButton("AÑADIR A FAVORITOS");
		btnVolver = new JButton("VOLVER");
		
		
		//pnlIzq.add(lblFoto);
		pnlDerecha.setLayout(new GridLayout(0, 1));
		pnlDerecha.add(nombreProducto);
		pnlDerecha.add(precioProducto);
		pnlDerecha.add(cantidadProducto);
		pnlBotones.add(btnAnyadirCesta);
		pnlBotones.add(btnAnyadirWish);
		pnlBotones.add(btnVolver);
		pnlDerecha.add(pnlBotones);
		getContentPane().add(pnlDerecha, BorderLayout.EAST);
		getContentPane().add(pnlIzq, BorderLayout.WEST);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		//aqui habria que ponerle la ventana de que podructo se abre
		//falta indicar el parametro de entrada
		/**new VentanaProductoUsuario(new Producto("Producto1", "id1",9.99f, "/imagenes/IMG-20231017-WA0003.jpg"));**/
		new VentanaProductoUsuario();
		
	}
}
