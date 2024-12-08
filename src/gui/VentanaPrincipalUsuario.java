package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import domain.Comprador;
import domain.GestorUsuarios;
import domain.Producto;
import domain.Producto.tipo;

public class VentanaPrincipalUsuario extends JFrame{
	
	protected JPanel pnlProductos;
	protected JButton btnCesta;
	protected JButton btnFavoritos;
	protected JPanel pnlBotones;
	protected JPanel pnlFiltro;
	protected JComboBox<String> filtroTipos;
	protected JTextField buscador;
	protected List<Producto> productos;
	protected static VentanaCestaUsuario vcu;
	
	
	public VentanaPrincipalUsuario() {
		
		JFrame vActual = this;
		
		setBounds(300, 100, 700, 500);
		setTitle("Ventana Principal Usuario");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		vActual.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Object[] opciones = {"Aceptar", "Cancelar"};
		        int opcion = JOptionPane.showOptionDialog(
		            vActual,
		            "¿Está seguro de que desea salir?",
		            "Confirmación de salida",
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE,
		            null,
		            opciones,
		            opciones[1] // Opciones[1] es "Cancelar", el botón seleccionado por defecto
		        );
		         
		        if(opcion == JOptionPane.YES_OPTION) {
		        	vActual.dispose();
		        	VentanaInicioSesion vis = new VentanaInicioSesion(new GestorUsuarios());
		        	vis.setVisible(true);
		        }
			}
		});
		
		
		setLayout(new BorderLayout()); 
		//inicializamos esta ventana para mas adelante poder añadir los productos que queramos 
		// a la lista de productos que compone la cesta
		
		
		//cargamos los productos (funcion que generara una lista de productos)
		/* 
		 * productos = crearProductos()
		 */
		List<Producto> productos = new ArrayList();
		Producto p1 = new Producto(null, "P1",  1, "imagenes/agua.jpg", 22);
		p1.setTipo(Producto.tipo.Bebidas);
		Producto p2 = new Producto(null, "P2", 2, "imagenes/cerealesFibra.jpg", 22);
		p2.setTipo(Producto.tipo.Desayuno);
		Producto p3 = new Producto(null, "P3",  3, "imagenes/cerveza.jpg", 22);
		p3.setTipo(Producto.tipo.Bebidas);
		Producto p4 = new Producto(null, "P4",  4, "imagenes/donuts.jpg", 22);
		p4.setTipo(Producto.tipo.Desayuno);
		Producto p5 = new Producto(null, "P5", 4, "imagenes/fileteDeCarne.jpg", 22);
		p5.setTipo(Producto.tipo.CarnePescado);
		Producto p6 = new Producto(null, "P6", 4, "imagenes/merluza.jpg", 22);
		p6.setTipo(Producto.tipo.CarnePescado);
		Producto p7 = new Producto(null, "P7",  4, "imagenes/entrecot.jpg", 22);
		p7.setTipo(Producto.tipo.CarnePescado);
		productos.add(p1);
		productos.add(p2);
		productos.add(p3);
		productos.add(p4);
		productos.add(p5);
		productos.add(p6);
		productos.add(p7);
		
		
		
		pnlProductos = new JPanel(new GridLayout(0, 3, 10, 10));
		JScrollPane scrollProductos = new JScrollPane(pnlProductos);
		scrollProductos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		actualizarPanel(productos);
		
		
		filtroTipos = new JComboBox<>();
		filtroTipos.addItem("Todos"); //de inicio no habra filtro
		buscador = new JTextField();
		buscador.setPreferredSize(new Dimension(300,30));
		JButton btnBuscar = new JButton("Buscar");
		
		 for(Producto.tipo tipo : Producto.tipo.values()){
		  	filtroTipos.addItem(tipo.name());
		 }
		
		 //IAG (herramienta: ChatGPT)
		 //Adaptado: sabiamos por donde empezar pero no acababa de funcionar bien del todo. 
		 //Tuvimos que buscar lo del revalidate ya que sino se hacia muy lento.
		 filtroTipos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String seleccionado = (String) filtroTipos.getSelectedItem();
				List<Producto> productosFiltrados = new ArrayList<Producto>();
				
				if(seleccionado.equals("Todos")) {
					productosFiltrados = productos;
				} else {
					for(Producto p : productos) {
						if(p.getTipo().name().equals(seleccionado)) {
							productosFiltrados.add(p);
						}
					}
				}
				actualizarPanel(productosFiltrados);
				pnlProductos.repaint();
				pnlProductos.revalidate();
			}
		});
		 
		 btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = buscador.getText().trim();
				String tipo = (String) filtroTipos.getSelectedItem();
				List<Producto> pf = new ArrayList<Producto>();
				 for (Producto p : productos) {
					 boolean coincideTipo = tipo.equals("Todos") || p.getTipo().name().equals(tipo);
			         boolean coincideNombre = nombre.isBlank() || p.getNombre().toLowerCase().contains(nombre.toLowerCase());
			            
			         if (coincideTipo && coincideNombre) {
			              pf.add(p);
			            }
			        }
				actualizarPanel(pf);
				pnlProductos.repaint();
				pnlProductos.revalidate();
			}
		});
		 
		buscador.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					btnBuscar.doClick();
				}
			}	
		});
		  
		pnlFiltro = new JPanel();
		pnlFiltro.add(filtroTipos);
		pnlFiltro.add(buscador);
		pnlFiltro.add(btnBuscar);
		
		btnCesta = new JButton("Cesta");
		btnFavoritos = new JButton("Favoritos");
		
		btnCesta.addActionListener((e)->{
			new VentanaCestaUsuario();
		});
		
		btnFavoritos.addActionListener((e)->{
			new VentanaFavoritosUsuario();
		});
		
		pnlBotones = new JPanel();
		
		pnlBotones.add(btnCesta);
		pnlBotones.add(btnFavoritos);
		
		this.add(pnlFiltro, BorderLayout.NORTH);
		this.add(scrollProductos, BorderLayout.CENTER);
		this.add(pnlBotones, BorderLayout.SOUTH);
		
		
		setVisible(true);
	}
	
	private void actualizarPanel(List<Producto> listaProductos) {
		pnlProductos.removeAll();
		
		for(Producto p: listaProductos) {
			JLabel nombreProducto = new JLabel("Nombre: " + p.getNombre());
			JLabel precioProducto = new JLabel("Precio: "+p.getPrecio());
			
			SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 100, 1);
	        JSpinner spinnerCantidad = new JSpinner(model);
	        spinnerCantidad.addChangeListener(e->{
	        	int cantidad = (Integer) ((JSpinner)e.getSource()).getValue();
	        	//vcu.anyadirProducto(p, cantidad);
	        });
			
			ImageIcon imagenProducto = new ImageIcon(p.getFoto());
			Image imagenRedimensionada = imagenProducto.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			ImageIcon imagen = new ImageIcon(imagenRedimensionada);
			JLabel lblFoto = new JLabel(imagen);
			
			
			VentanaPrincipalUsuario ventPrinUsu = this;
			
			
			lblFoto.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					dispose();
					//AQUI DA ERRO PORQUE HAY QUE PASARLE COMO PARAMETRO
					// EL PRODUCTO -> mirarlo en bitxi-bitxiak (está puesto dentro de
					//un for que pone todos los productos de la base de datos en el grid layout)
					new VentanaProductoUsuario( ventPrinUsu);
				}
			});
			JPanel pnlPrincipal = new JPanel(new BorderLayout());
			pnlPrincipal.setSize(new Dimension(200,100));
			pnlProductos.setMaximumSize(new Dimension(200,100));
			JPanel pnlCentral = new JPanel();
			JPanel pnlBtnProd = new JPanel(new GridLayout(1,2));
			
	        pnlBtnProd.add(spinnerCantidad);
	        pnlCentral.add(nombreProducto);
	        pnlCentral.add(precioProducto);
	        
	        pnlPrincipal.add(lblFoto, BorderLayout.NORTH);
	        pnlPrincipal.add(pnlBtnProd, BorderLayout.SOUTH);
	        pnlPrincipal.add(pnlCentral, BorderLayout.CENTER);
	        
	        pnlProductos.add(pnlPrincipal);
		}
		
	}
	
		
	public static void main(String[] args) {
		new VentanaPrincipalUsuario();
		
	}
	
}
