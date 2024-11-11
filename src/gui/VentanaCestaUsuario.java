package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import domain.Producto;
import domain.TipoIva;

public class VentanaCestaUsuario extends JFrame{
	private ModeloCestaUsuario modeloCestaUsuario;
	private JTable tabla;
	private JScrollPane scrolltabla;
	private JPanel panelBotones, panelTotal;
	private JButton botonAniadirFavoritos, botonPagar, botonSeguirComprando;
	private JLabel pieDePagina, textoTotal, valorTotal;
	private List<Producto> productos;
		
	public VentanaCestaUsuario() {
		super();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		productos = new ArrayList<>();
		modeloCestaUsuario = new ModeloCestaUsuario(productos);
		tabla = new JTable(modeloCestaUsuario);
		scrolltabla = new JScrollPane(tabla);
		getContentPane().add(scrolltabla, BorderLayout.CENTER);
		
		panelTotal = new JPanel();
		textoTotal = new JLabel("Total a pagar: ");
		valorTotal = new JLabel("0.00€");
		
		panelTotal.add(textoTotal);
		panelTotal.add(valorTotal);
		
		
		getContentPane().add(panelTotal, BorderLayout.SOUTH);
		
		panelBotones = new JPanel();
		
		//Creamos los 3 botones
		botonPagar = new JButton("Pagar");
		botonSeguirComprando = new JButton("Seguir comprando");
		//Los añadimos al panel
		panelBotones.add(botonPagar);
		panelBotones.add(botonSeguirComprando);
		//Añadimos el panel a la ventana
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		 
		botonPagar.addActionListener((e)->{
			JOptionPane.showMessageDialog(VentanaCestaUsuario.this, "Compra pagada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
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
        	   int precio = (int) value;
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
        	
        	else {
        		labelCestaUsuario.setBackground(Color.white);
        		labelCestaUsuario.setForeground(Color.black);
        	}
        	
        	return labelCestaUsuario;
        });
		
		
		setVisible(true);
	}
	
	public void anyadirProducto(Producto p, Integer cantidad) {
		productos.add(p);
	}
	
	
	public static void main(String[] args) {
		new VentanaCestaUsuario();
		

	
	}
	
}
