package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import domain.BaseDatos1;
import domain.Comprador;
import domain.Logica;
import domain.Producto;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class VentanaProductoUsuario extends JFrame{
	
	protected JLabel nombreProducto;
	protected JLabel precioProducto;
	protected JLabel cantidadProducto;
	protected ImageIcon imagenProducto;
	protected JLabel lblFoto;
	protected JButton btnVolver;
	protected JButton btnAnyadirWish;
	//protected JButton btnAnyadirCesta;
	protected JPanel pnlIzq;
	protected JPanel pnlDerecha;
	protected JPanel pnlBotones;
	protected JSpinner spinnerCantidad;
	protected JPanel pnlSpinner;
	
	protected int cantidadEnCesta;
	
	private boolean esFavorito = false;
	private ImageIcon iconoBlancoRedimensionado;
	private ImageIcon iconoNegroRedimensionado;
	
	public VentanaProductoUsuario(Producto p, VentanaPrincipalUsuario ventAnt) {
		 
		JFrame ventanaAnterior = ventAnt;
		
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
		
		
		Comprador c1 = (Comprador) Logica.getUsuario();
		
		for(Producto pr:c1.listaFavoritos) {
			if (pr.getCodigo() == p.getCodigo()) {
				esFavorito=true;
			}
		}
		
		nombreProducto = new JLabel("Nombre: " + p.getNombre());
		precioProducto = new JLabel("Precio: "+p.getPrecio());
		cantidadProducto = new JLabel("Cantidad: ");
		
		int posicionProductoListaCesta=-1;
		for(int i=0; i<c1.cesta.size(); i++) {
			if(p.getCodigo()==c1.cesta.get(i).getCodigo()) {
				posicionProductoListaCesta=i;
			}
		}
		int cantidadInicial = 0;
		if (posicionProductoListaCesta != -1) {
		    // Si el producto ya está en la cesta, obtenemos su cantidad
		    cantidadInicial = c1.cesta.get(posicionProductoListaCesta).getCantidad();
		}
		
		try {
			cantidadEnCesta = BaseDatos1.obtenerCantidadProducto(c1.getCodigoUsuario(), p.getCodigo());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Configuramos el modelo del spinner con el valor inicial correcto
		SpinnerNumberModel model = new SpinnerNumberModel(cantidadEnCesta, 0, 100, 1);
		spinnerCantidad = new JSpinner(model);
		
		//c1.cesta.get(posicionProductoListaCesta).getCantidad()
        //SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 100, 1); //valor que aparece, min, max, +1
        //spinnerCantidad = new JSpinner(model);
		
		
		imagenProducto = new ImageIcon(p.getFoto());
		
		//Redimensiono la imagen
		Image imagenRedimensionada = imagenProducto.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon imagenRedimensionadaIcono = new ImageIcon(imagenRedimensionada);
		
		lblFoto = new JLabel(imagenRedimensionadaIcono);
		pnlIzq = new JPanel();
		pnlDerecha = new JPanel();
		pnlBotones = new JPanel(/*new GridLayout(1, 3)*/);
		pnlSpinner = new JPanel(new BorderLayout());
		
		pnlDerecha.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//pnlSpinner.setLayout(new BoxLayout(pnlSpinner, BoxLayout.X_AXIS));
		pnlSpinner.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		cantidadProducto.setAlignmentX(Component.LEFT_ALIGNMENT);
		spinnerCantidad.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//btnAnyadirCesta = new JButton("AÑADIR A LA CESTA");

        //btnAnyadirCesta.setFont(defaultFont.deriveFont(11f));
        
        
        ImageIcon iconoBlanco = new ImageIcon("imagenes/corazonBlanco.png");
        ImageIcon iconoNegro = new ImageIcon("imagenes/corazonNegro.png");
        
        //Para verificar si las imagenes se han cargado correctamente
        if (iconoBlanco.getIconWidth() == -1) {
            System.out.println("Error: No se pudo cargar corazonBlanco.png");
        }
        if (iconoNegro.getIconWidth() == -1) {
            System.out.println("Error: No se pudo cargar corazonNegro.png");
        }
        
        
        //Se redimensionan las imagenes, pero solo una vez que se hayan cargado correctamente
        if (iconoBlanco.getIconWidth() != -1) {
            Image imagenBlanca = iconoBlanco.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            iconoBlancoRedimensionado = new ImageIcon(imagenBlanca);
        }

        if (iconoNegro.getIconWidth() != -1) {
            Image imagenNegra = iconoNegro.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            iconoNegroRedimensionado = new ImageIcon(imagenNegra);
        }

        
        //btnAnyadirWish = new JButton(iconoBlancoRedimensionado);
        btnAnyadirWish = new JButton(esFavorito ? iconoNegroRedimensionado : iconoBlancoRedimensionado);
        btnAnyadirWish.setBackground(Color.WHITE);
        
        
        
        ImageIcon iconoOriginalFlecha = new ImageIcon("imagenes/flechaVolver.png");
        if (iconoOriginalFlecha.getIconWidth() != -1) {
            Image imagenRedimensionadaFlecha = iconoOriginalFlecha.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            ImageIcon iconoRedimensionadoFlecha = new ImageIcon(imagenRedimensionadaFlecha);

            btnVolver = new JButton(iconoRedimensionadoFlecha);  // Usar la imagen redimensionada en el botón
        } else {
            btnVolver = new JButton("VOLVER");  // En caso de error en la carga de la imagen
        }
        
        btnVolver.setBackground(Color.RED);
	
		
		
		pnlIzq.add(lblFoto, BorderLayout.CENTER);
		pnlDerecha.setLayout(new GridLayout(0, 1));
		pnlDerecha.add(nombreProducto);
		pnlDerecha.add(precioProducto);
		JPanel pnlCantidad = new JPanel();
		pnlCantidad.add(cantidadProducto);
		pnlCantidad.add(spinnerCantidad);
		pnlSpinner.add(pnlCantidad, BorderLayout.WEST);
		//pnlSpinner.add(Box.createHorizontalStrut(5)); //con esto se añade un espacio entre el label y el spinner
		
		pnlDerecha.add(pnlSpinner);
		//pnlBotones.add(btnAnyadirCesta);
		JButton btnAnyadirCesta = new JButton("añadir a la cesta");
		pnlBotones.add(btnAnyadirCesta);
		pnlBotones.add(btnAnyadirWish);
		pnlBotones.add(btnVolver);
		pnlDerecha.add(pnlBotones);
		
		//esto es para dividir el panel por la mitad y el panel izquierdo ocupe la mitad izquierda y el derecho la mitad derecha
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnlIzq, pnlDerecha);
        splitPane.setDividerLocation(getWidth()/3); 
        splitPane.setResizeWeight(0.5); // Para que ambos paneles mantengan el 50% del espacio
        
        splitPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        getContentPane().add(splitPane);

			
		
        setBackground(Color.WHITE);
        pnlSpinner.setBackground(Color.WHITE);
        pnlBotones.setBackground(Color.WHITE);
        pnlDerecha.setBackground(Color.WHITE);
        pnlIzq.setBackground(Color.WHITE);
        pnlCantidad.setBackground(Color.WHITE);
        
        
        
        btnVolver.addActionListener((e)->{
        	ventanaAnterior.setVisible(true);
        	dispose();
        });
        
        
        
        btnAnyadirWish.addActionListener((e)->{
        	//Para programar esto necesitariamos tener la base de datos 
        	//hecha para poder añadirle al usuario que ha iniciado sesión
        	//el producto que recibe la ventana como parametro a la lista 
        	//de los productos que él tenga en favoritos, para así poder visualizarlo en la tabla
        	
        	esFavorito = !esFavorito;
        	 
        	 int cantidad = 0; //lo pongo a 0 porque en la wishlist no 
        	 					//hace falta cantidad, pero en cesta si. y como se 
        	 					//comparte el código hay que ponerle algo
         	
        	 
        	if (esFavorito && iconoNegroRedimensionado != null) {
                btnAnyadirWish.setIcon(iconoNegroRedimensionado);
                //Se ha añadido a favoritos
                c1.anyadirWishList(p);
                
                try {
                	BaseDatos1.anyadirProducto(c1.getCodigoUsuario(), p.getCodigo(), 0, cantidad);
                }catch(SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Producto añadido a tu wishlist");
                
            } else if (!esFavorito && iconoBlancoRedimensionado != null) {
                btnAnyadirWish.setIcon(iconoBlancoRedimensionado);
                
                //Se ha eliminado de favoritos
                /**ELIMINAR PRODUCTO DE LA LISTA DE FAVORITOS DEL COMPRADOR**/
                
                try {
                	BaseDatos1.eliminarProducto(c1.getCodigoUsuario(), p.getCodigo(), 0);
                }catch(SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Producto eliminado de tu wishlist");
                
                
            }
        
        });
        
        int pos = posicionProductoListaCesta;
        btnAnyadirCesta.addActionListener((e)->{
        	c1.anyadirCesta(p);
        	try {
        		BaseDatos1.eliminarProducto(c1.getCodigoUsuario(), p.getCodigo(), 1); //borro si habia, para volver a insertar con la nueva cantidad
        		BaseDatos1.anyadirProducto(c1.getCodigoUsuario(), p.getCodigo(), 1, (int) spinnerCantidad.getValue());
        		c1.getCesta().remove(pos);
        	}catch(SQLException ex) {
        		ex.printStackTrace();
        	}
        });
        
        
        
//        spinnerCantidad.addChangeListener((e)->{
//        	//Para programar esto necesitariamos tener la base de datos 
//        	//hecha para poder añadirle al usuario que ha iniciado sesión
//        	//el producto que recibe la ventana como parametro a la lista 
//        	//de los productos que él tenga en compra por la cantidad que pone, (excepto si es 0, se elimina de la compra)
//        	// para así poder visualizarlo en la tabla
//        	int pSelectPosLista=-1;
//        	for(int i=0; i<c1.cesta.size(); i++) {
//        			Producto pr = c1.cesta.get(i);
//        			if(pr.getCodigo() == p.getCodigo()) {
//        				pSelectPosLista=i;
//        			}
//        		}
//        	        	
//        	int cantidad = (int) spinnerCantidad.getValue();
//        	if(cantidad==0) {
//        		
//        		try {
//        			BaseDatos1.eliminarProducto(c1.getCodigoUsuario(), p.getCodigo(), 1);
//        			c1.getCesta().remove(pSelectPosLista);
//        			/** (?) AQUI FALTA PONER QUE SE BORRE DE LA LISTA DE LA CESTA DEL COMPRADOR**/
//            		
//        			System.out.println("Producto eliminado de la cesta.");
//        		}catch(Exception ex) {
//        			ex.printStackTrace();
//        		}
//        	}else {
//        		c1.anyadirCesta(p);
//        		try {
//        			BaseDatos1.anyadirProducto(c1.getCodigoUsuario(), p.getCodigo(), 1, cantidad);
//        			System.out.println("Producto añadido/actualizado en la cesta con cantidad: " + cantidad);
//        		}catch(Exception ex) {
//        			ex.printStackTrace();
//        		}
//        	}
//        	
//        	
//        });
        
        
        
		setVisible(true);
	}
	
	/*
	public static void main(String[] args) {
		//aqui habria que ponerle la ventana de que podructo se abre
		//falta indicar el parametro de entrada
		//new VentanaProductoUsuario(new Producto("Producto1", "id1",9.99f, "/imagenes/IMG-20231017-WA0003.jpg"));
		new VentanaProductoUsuario();
		
	}*/
}
