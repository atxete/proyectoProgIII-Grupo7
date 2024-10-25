package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

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
	protected JPanel pnlSpinner;
	
	public VentanaProductoUsuario(/**Producto p**/) {
		
		setBounds(300, 100, 700, 500);
		setTitle("Ventana Producto Usuario");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
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
		
        SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 100, 1); //valor que aparece, min, max, +1
        spinnerCantidad = new JSpinner(model);

		
		
		imagenProducto = new ImageIcon("imagenes/Logo.jpg");
		
		//Redimensiono la imagen
		Image imagenRedimensionada = imagenProducto.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon imagenRedimensionadaIcono = new ImageIcon(imagenRedimensionada);
		
		lblFoto = new JLabel(imagenRedimensionadaIcono);
		pnlIzq = new JPanel();
		pnlDerecha = new JPanel();
		pnlBotones = new JPanel(new GridLayout(1, 3));
		pnlSpinner = new JPanel(new GridLayout(1, 2));
		
		btnAnyadirCesta = new JButton("AÑADIR A LA CESTA");
		Font defaultFont = btnAnyadirCesta.getFont(); // fuente por defecto de btn
        btnAnyadirCesta.setFont(defaultFont.deriveFont(11f));
        btnAnyadirWish = new JButton("AÑADIR A FAVORITOS");
        btnAnyadirWish.setFont(defaultFont.deriveFont(10f));
		btnVolver = new JButton("VOLVER");
		btnVolver.setFont(defaultFont.deriveFont(11f));
		
		
		pnlIzq.add(lblFoto, BorderLayout.CENTER);
		pnlDerecha.setLayout(new GridLayout(0, 1)); //BOX LAYOUT
		pnlDerecha.add(nombreProducto);
		pnlDerecha.add(precioProducto);
		pnlSpinner.add(cantidadProducto);
		pnlSpinner.add(spinnerCantidad);
		pnlDerecha.add(pnlSpinner);
		pnlBotones.add(btnAnyadirCesta);
		pnlBotones.add(btnAnyadirWish);
		pnlBotones.add(btnVolver);
		pnlDerecha.add(pnlBotones);
		
		//esto es para dividir el panel por la mitad y el panel izquierdo ocupe la mitad izquierda y el derecho la mitad derecha
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnlIzq, pnlDerecha);
        splitPane.setDividerLocation(getWidth()/3); 
        splitPane.setResizeWeight(0.5); // Para que ambos paneles mantengan el 50% del espacio

        
        getContentPane().add(splitPane);

			
		
        setBackground(Color.WHITE);
        pnlSpinner.setBackground(Color.WHITE);
        pnlBotones.setBackground(Color.WHITE);
        pnlDerecha.setBackground(Color.WHITE);
        pnlIzq.setBackground(Color.WHITE);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		//aqui habria que ponerle la ventana de que podructo se abre
		//falta indicar el parametro de entrada
		/**new VentanaProductoUsuario(new Producto("Producto1", "id1",9.99f, "/imagenes/IMG-20231017-WA0003.jpg"));**/
		new VentanaProductoUsuario();
		
	}
}
