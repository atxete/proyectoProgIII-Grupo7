package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import domain.BaseDatos1;
import domain.Comprador;
import domain.Logica;
import domain.Producto;
import domain.TipoIva;

public class VentanaFavoritosUsuario extends JFrame{
	private ModeloFavoritosUsuario modeloFavoritosUsuarios;
	private JTable tablaFavoritos;
	private JScrollPane scrollFavoritos;
	private JButton seguirComprando, cesta;
	private JPanel panelBotones;
	private JLabel foto;
	
	//generar un comprador
	Comprador c1 = (Comprador) Logica.getUsuario();

	public VentanaFavoritosUsuario() {
		super();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		List<Producto> productos = BaseDatos1.getWishListOCesta(c1.getCodigoUsuario(), 0); 
		modeloFavoritosUsuarios = new ModeloFavoritosUsuario(productos);
		tablaFavoritos = new JTable(modeloFavoritosUsuarios);
		scrollFavoritos = new JScrollPane(tablaFavoritos);
		
		getContentPane().add(scrollFavoritos, BorderLayout.CENTER);
		
		cargarTablaWishList(c1.getCodigoUsuario());
	
		panelBotones = new JPanel();
		cesta = new JButton("Cesta");
		seguirComprando = new JButton("Seguir Comprando");
		panelBotones.add(seguirComprando);
		panelBotones.add(cesta);
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		
		ImageIcon logo = new ImageIcon("C:\\Users\\usuario\\trabajoProg3\\proyectoProgIII-Grupo7\\imagenes\\Logo.jpg");
		foto = new JLabel(logo);
		
		getContentPane().add(foto, BorderLayout.NORTH);
		
		cesta.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				VentanaCestaUsuario ventanaCesta = new VentanaCestaUsuario();
				ventanaCesta.setVisible(true);
				dispose();
				  
				
			}
		});
		
		seguirComprando.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipalUsuario ventanausuario = new VentanaPrincipalUsuario();
				ventanausuario.setVisible(false);
				dispose();
				
			}
		});
		
        tablaFavoritos.setDefaultRenderer(Object.class, (table, value, isSelected, hasFocus, row, column) -> {
            JLabel labelCestaUsuario = new JLabel(value.toString());
            labelCestaUsuario.setOpaque(true);
         	if(column==2) {
         	   float precio = (float) value;
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
					int pos = tablaFavoritos.getSelectedRow();
					try {
						BaseDatos1.eliminarProducto(c1.getCodigoUsuario(), c1.listaFavoritos.get(pos).getCodigo(), 0);
					}catch(SQLException ex) {
						ex.printStackTrace();
					}
					c1.getListaFavoritos().remove(pos);
					actualizarLista();
					
					cargarTablaWishList(c1.getCodigoUsuario());
					
				}
			}
			
		};
		tablaFavoritos.addKeyListener(kl);
         
        
		
		setVisible(true);
	}
	
	public void actualizarLista() {
		List<Producto> anyadidos = new ArrayList<Producto>();
		modeloFavoritosUsuarios = new ModeloFavoritosUsuario(new ArrayList<Producto>());
		for(Producto p : c1.getListaFavoritos()) {
			int cont=0;
			for(Producto p2: BaseDatos1.getWishListOCesta(c1.getCodigoUsuario(), 0) /*c1.getListaFavoritos()*/) {
				if(p.equals(p2)) {
					cont++;
				}
			}
			if(!anyadidos.contains(p)) {
				modeloFavoritosUsuarios.addRow(new Object[] {p.getCodigo(), p.getNombre(), p.getPrecio()+"€", p.getPrecio()});
				anyadidos.add(p);
				
			}
		}
		tablaFavoritos.setModel(modeloFavoritosUsuarios);
	}
	
	
	public static void main(String[] args) {
		new VentanaFavoritosUsuario();
	}
	
	
	public void cargarTablaWishList(int idUsuario) {
		List<Producto> productosWishlist = BaseDatos1.obtenerProductosWishList(idUsuario);
	    
	    ModeloFavoritosUsuario model = new ModeloFavoritosUsuario(productosWishlist);
	    
	    
	    tablaFavoritos.setModel(model); 
	    tablaFavoritos.revalidate();
	    tablaFavoritos.repaint();
	}
	
	
	
}
