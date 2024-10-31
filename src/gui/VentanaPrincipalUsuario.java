package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import domain.Comprador;
import domain.Producto;

public class VentanaPrincipalUsuario extends JFrame{
	
	protected JPanel pnlProductos;
	protected JButton btnCesta;
	protected JButton btnFavoritos;
	protected JPanel pnlBotones;
	protected JPanel pnlFiltro;
	protected JComboBox<String> filtroTipos;
	//muri
	
	public VentanaPrincipalUsuario() {
		
		setBounds(300, 100, 700, 500);
		setTitle("Ventana Principal Usuario");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		VentanaCestaUsuario vcu = new VentanaCestaUsuario(); 
		//inicializamos esta ventana para mas adelante poder añadir los productos que queramos 
		// a la lista de productos que compone la cesta
		
		
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
			
			SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 100, 1);
	        JSpinner spinnerCantidad = new JSpinner(model);
			JLabel cantidadProducto = new JLabel("Cantidad: "+ spinnerCantidad);
			
			//ImageIcon imagenProducto = new ImageIcon(p.getFoto());
			//JLabel lblFoto = new JLabel(imagenProducto);
			JPanel pnlPrincipal = new JPanel();
			JPanel pnlCentral = new JPanel();
			JPanel pnlBtnProd = new JPanel(new GridLayout(1,2));
			
			JButton btnAnyadirCesta = new JButton("AÑADIR");
			btnAnyadirCesta.addActionListener((e)->{
				int cantidad = (int) spinnerCantidad.getValue();
				if(cantidad>0) {
					
				} else {
					JOptionPane.showMessageDialog(null, "No puedes añadir 0 productos", "Error", JOptionPane.ERROR_MESSAGE);;
				}
	        });
	        
	        pnlBtnProd.add(btnAnyadirCesta);
	        pnlBtnProd.add(spinnerCantidad);
	        
	        pnlCentral.add(nombreProducto);
	        pnlCentral.add(precioProducto);
	        
	        //pnlPrincipal.add(lblFoto, BorderLayout.NORTH);
	        pnlPrincipal.add(pnlBtnProd, BorderLayout.SOUTH);
	        pnlPrincipal.add(pnlCentral, BorderLayout.CENTER);
	        
	        pnlProductos.add(pnlPrincipal);
		}
		
		filtroTipos = new JComboBox<>();
		filtroTipos.addItem("Todos"); //de inicio no habra filtro
		
		  for(Producto.tipo tipo : Producto.tipo.values()){
		  	filtroTipos.addItem(tipo.name());
		  }
		pnlFiltro = new JPanel();
		pnlFiltro.add(filtroTipos, BorderLayout.WEST);
		
		btnCesta = new JButton("Cesta");
		btnFavoritos = new JButton("Favoritos");
		
		pnlBotones = new JPanel(new GridLayout(1,2));
		
		pnlBotones.add(btnCesta);
		pnlBotones.add(btnFavoritos);
		
		this.add(pnlFiltro, BorderLayout.NORTH);
		this.add(pnlProductos, BorderLayout.CENTER);
		this.add(pnlBotones, BorderLayout.SOUTH);
		
		
		setVisible(true);
	}
	public static void main(String[] args) {
		new VentanaPrincipalUsuario();
		
	}
	
}
