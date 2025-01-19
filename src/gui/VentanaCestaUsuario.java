package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import domain.BaseDatos1;
import domain.Comprador;
import domain.Logica;
import domain.Producto;
import domain.TipoIva;

public class VentanaCestaUsuario extends JFrame{
	private ModeloCestaUsuario modeloCestaUsuario;
	private JTable tabla;
	private JScrollPane scrolltabla;
	private JPanel panelBotones;
	private JButton botonAniadirFavoritos, botonPagar, botonSeguirComprando;
	private JLabel pieDePagina, precioTotal;
	private List<Producto> productos;
	private List<Producto> prods;
	
	
	//Generar un comprador
	Comprador c1 = (Comprador) Logica.getUsuario();
	
	
	
	public VentanaCestaUsuario() {
		super();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		//productos = c1.getCesta();
		productos = BaseDatos1.getWishListOCesta(c1.getCodigoUsuario(), 1); // 1 -> cesta,  0 -> wish
		modeloCestaUsuario = new ModeloCestaUsuario(productos);
		tabla = new JTable(modeloCestaUsuario);
		scrolltabla = new JScrollPane(tabla);
		getContentPane().add(scrolltabla, BorderLayout.CENTER);
		
				
		/*
		panelTotal = new JPanel();
		textoTotal = new JLabel("Total a pagar: ");
		valorTotal = new JLabel("0.00€");
		
		panelTotal.add(textoTotal);
		panelTotal.add(valorTotal);
		 
		
		getContentPane().add(panelTotal, BorderLayout.SOUTH);
		*/
		
		
		cargarTablaCesta(c1.getCodigoUsuario());
		
		panelBotones = new JPanel();
		
		//Creamos los 2 botones y el label para que se vea el precio total
		botonPagar = new JButton("Pagar");
		botonSeguirComprando = new JButton("Seguir comprando");
		precioTotal = new JLabel("Precio total: " + actualizarPrecio(c1.getCodigoUsuario()/*productos*/));
		precioTotal.setAlignmentX(RIGHT_ALIGNMENT);
		precioTotal.setBackground(Color.white);
		//Los añadimos al panel
		panelBotones.add(botonPagar);
		panelBotones.add(botonSeguirComprando);
		panelBotones.add(precioTotal);
		
		//Añadimos el panel a la ventana
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		 
		botonPagar.addActionListener((e)->{
			JOptionPane.showMessageDialog(VentanaCestaUsuario.this, "Compra pagada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			
			if(!BaseDatos1.getWishListOCesta(c1.getCodigoUsuario(), 1).isEmpty() /*!c1.getCesta().isEmpty()*/) {
				long fecha = System.currentTimeMillis();
				int id = BaseDatos1.anyadirCompra(c1.getCodigoUsuario(), fecha, actualizarPrecio(c1.getCodigoUsuario()/*BaseDatos1.getWishListOCesta(c1.getCodigoUsuario(), 1)/*c1.getCesta()*/));
				for(Producto p : BaseDatos1.getWishListOCesta(c1.getCodigoUsuario(), 1)/*c1.getCesta()*/) {
					BaseDatos1.anyadirCompraP(id, p.getCodigo());
				}
				
				/**TOTALPRECIO.SETTEXT(PRECIO TOTAL: 0.00 €");**/
				precioTotal.setText("Precio total: " + actualizarPrecio(c1.getCodigoUsuario()/*productos*/));
				
				
				JOptionPane.showMessageDialog(null, "Tu compra ha sido registrada");
				c1.getCesta().removeAll(c1.getCesta());
				BaseDatos1.eliminarCestaUsuario(c1.getCodigoUsuario());
				actualizarLista();
			}else {
				JOptionPane.showMessageDialog(null, "ERROR: Cesta vacia");
			}
			
			
		});
		botonSeguirComprando.addActionListener((e)->{
			VentanaPrincipalUsuario ventanausuario = new VentanaPrincipalUsuario();
			ventanausuario.setVisible(false);
			dispose();
		});
		
		
		ImageIcon logo = new ImageIcon("C:\\Users\\usuario\\trabajoProg3\\proyectoProgIII-Grupo7\\imagenes\\Logo.jpg");
		pieDePagina = new JLabel(logo);
		pieDePagina.setSize(100, 50);
		
		getContentPane().add(pieDePagina, BorderLayout.NORTH);
		

        tabla.setDefaultRenderer(Object.class, (table, value, isSelected, hasFocus, row, column) -> {
           JLabel labelCestaUsuario = new JLabel(value.toString());
           labelCestaUsuario.setOpaque(true);
        	if(column==2) {
        	   float precio = Float.parseFloat(value.toString());
        	   if(precio <1) {
        		   labelCestaUsuario.setBackground(new Color(173, 216, 230));
        	   }
        	   else if(precio <2) {
        		   labelCestaUsuario.setBackground(new Color(255, 255, 204));
        	   }else if(precio <4) {
        		   labelCestaUsuario.setBackground(Color.orange);
        	   }else {
        		   labelCestaUsuario.setBackground(new Color(255, 182, 193));
        	   }
        	}
        	else if(column ==4) {
        		float precio1 = (float) value;
         	   	if(precio1 <10) {
         		   labelCestaUsuario.setBackground(new Color(204, 255, 204));
         	   	}
         	   	else {
         		   labelCestaUsuario.setBackground(new Color(255, 182, 193));
         	   	}
        	}else {
        		labelCestaUsuario.setBackground(Color.white);
        		labelCestaUsuario.setForeground(Color.black);
        	}
        	/*
        	if(column ==3) {
        		TipoIva tipoIva = (TipoIva) value;
        		if(tipoIva == tipoIva.General) {
        			labelCestaUsuario.setForeground(Color.black);
        		}else if(tipoIva == tipoIva.Reducido) {
        			labelCestaUsuario.setForeground(Color.ORANGE);
        		}else if(tipoIva == tipoIva.Superreducido) {
        			labelCestaUsuario.setForeground(Color.yellow);
        		}else {
        			labelCestaUsuario.setForeground(Color.green);
        		}
        	}
        	*/
        	
        	
        	
        	return labelCestaUsuario;
        });
		
        //Para borrar algún producto de la cesta
        //Poner el ratón encima del producto que se desea eliminar y pulsar CTRL + SUPR
        //el resultado se actualiza tanto en la lista de la ventana como en la base de datos
        
        KeyListener kl = new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.isControlDown() && (e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {
					int pos = tabla.getSelectedRow();
					try {
						BaseDatos1.eliminarProducto(c1.getCodigoUsuario(), c1.cesta.get(pos).getCodigo(), 1);
					}catch(SQLException ex) {
						ex.printStackTrace();
					}
					c1.getCesta().remove(pos);
					
					actualizarLista();
					
					cargarTablaCesta(c1.getCodigoUsuario());
					precioTotal.setText(String.format("%.2f", actualizarPrecio(c1.getCodigoUsuario()))+"€");
				}
			}
			
		};
		tabla.addKeyListener(kl);
		
		setVisible(true);
	}
	
	public void anyadirProducto(Producto p, Integer cantidad) {
		productos.add(p);
		cargarTablaCesta(c1.getCodigoUsuario());
	}
	
	/*
	public static void main(String[] args) {
		new VentanaCestaUsuario();
		

	
	}*/
	
	public void actualizarLista() {
		List<Producto> anyadidos = new ArrayList<Producto>();
		modeloCestaUsuario = new ModeloCestaUsuario(new ArrayList<Producto>());
		for(Producto p : BaseDatos1.getWishListOCesta(c1.getCodigoUsuario(), 1) /*c1.getCesta()*/) {
			int cont=0;
			for(Producto p2:BaseDatos1.getWishListOCesta(c1.getCodigoUsuario(), 1)/*c1.getCesta()*/) {
				if(p.equals(p2)) {
					cont++;
				}
			}
			if(!anyadidos.contains(p)) {
				modeloCestaUsuario.addRow(new Object[] {p.getCodigo(), p.getNombre(), p.getPrecio()+"€", cont, p.getPrecio()*cont});
				anyadidos.add(p);
			}
			
		}
		tabla.setModel(modeloCestaUsuario);
	}
	
	public double actualizarPrecio(int idUsuario) { //lista es la lista de los productos del que queremos obtener el precio total --> se calcula el precio total de los productos en esa lista
		double precioT =0.0;
		
//		DecimalFormat df = new DecimalFormat("#,00"); //el formato del decimal es el siguiente: cualquier digito antes del punto decimal + punto decimal se indicará mediante un punto '.' + debe tener exactamente dos digitos después del punto decimal
		
		prods = BaseDatos1.obtenerProductosCesta(idUsuario);
		
		for(Producto p : prods) {
			precioT += p.getPrecio()*p.getCantidad();
		}
		
		
		//IAG (herramienta: ChatGPT)
		 //para que un double tenga solamente dos decimales, no sabiamos cómo hacerlo
		BigDecimal bd = new BigDecimal(precioT).setScale(2, RoundingMode.DOWN);
		return bd.doubleValue();
	}
	
	
	public void cargarTablaCesta(int idUsuario) {
	    List<Producto> productosCesta = BaseDatos1.obtenerProductosCesta(idUsuario);
	    
	    ModeloCestaUsuario model = new ModeloCestaUsuario(productosCesta);
	    
	    tabla.setModel(model);  
	    tabla.revalidate();
	    tabla.repaint();


	}
	
	
}
