package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
	
	
	//Generar un comprador
	Comprador c1 = (Comprador) Logica.getUsuario();
	
	
	
	public VentanaCestaUsuario() {
		super();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		productos = c1.getCesta();
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
		
		panelBotones = new JPanel();
		
		//Creamos los 2 botones y el label para que se vea el precio total
		botonPagar = new JButton("Pagar");
		botonSeguirComprando = new JButton("Seguir comprando");
		precioTotal = new JLabel("Precio total: " + actualizarPrecio(productos));
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
			
			if(!c1.getCesta().isEmpty()) {
				long fecha = System.currentTimeMillis();
				int id = BaseDatos1.anyadirCompra(c1.getCodigoUsuario(), fecha, actualizarPrecio(c1.getCesta()));
				for(Producto p : c1.getCesta()) {
					BaseDatos1.anyadirCompraP(id, p.getCodigo());
				}
				/**TOTALPRECIO.SETTEXT(PRECIO TOTAL: 0.00 €");**/
				JOptionPane.showMessageDialog(null, "Tu compra ha sido registrada");
				c1.getCesta().removeAll(c1.getCesta());
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
        	if(column==2) {
        	   float precio = (float) value;
        	   if(precio <10) {
        		   labelCestaUsuario.setBackground(Color.GREEN);
        	   }
        	   else if(precio <50) {
        		   labelCestaUsuario.setBackground(Color.yellow);
        	   }else if(precio <100) {
        		   labelCestaUsuario.setBackground(Color.orange);
        	   }else {
        		   labelCestaUsuario.setBackground(Color.red);
        	   }
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
        	
        	else {
        		labelCestaUsuario.setBackground(Color.white);
        		labelCestaUsuario.setForeground(Color.black);
        	}
        	
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
					precioTotal.setText(String.format("%.2f", actualizarPrecio(productos/*c1.getCesta()*/))+"€");
				}
			}
			
		};
		tabla.addKeyListener(kl);
		
		setVisible(true);
	}
	
	public void anyadirProducto(Producto p, Integer cantidad) {
		productos.add(p);
	}
	
	/*
	public static void main(String[] args) {
		new VentanaCestaUsuario();
		

	
	}*/
	
	public void actualizarLista() {
		List<Producto> anyadidos = new ArrayList<Producto>();
		modeloCestaUsuario = new ModeloCestaUsuario(new ArrayList<Producto>());
		for(Producto p : c1.getCesta()) {
			int cont=0;
			for(Producto p2:c1.getCesta()) {
				if(p.equals(p2)) {
					cont++;
				}
			}
			if(!anyadidos.contains(p)) {
				modeloCestaUsuario.addRow(new Object[] {p.getNombre(), p.getCodigo(),p.getClass().getSimpleName(), p.getPrecio()+"€", cont});
				anyadidos.add(p);
			}
		}
		tabla.setModel(modeloCestaUsuario);
	}
	
	public double actualizarPrecio(List<Producto> lista) { //lista es la lista de los productos del que queremos obtener el precio total --> se calcula el precio total de los productos en esa lista
		double precioT =0.0;
		
		DecimalFormat df = new DecimalFormat("#.00"); //el formato del decimal es el siguiente: cualquier digito antes del punto decimal + punto decimal se indicará mediante un punto '.' + debe tener exactamente dos digitos después del punto decimal
		for(Producto p : productos) {
			precioT += p.getPrecio();
		}
		
		return Double.parseDouble(df.format(precioT));
	}
	
	
}
