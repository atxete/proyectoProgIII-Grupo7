package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import domain.Producto;
import domain.TipoIva;

public class VentanaFavoritosUsuario extends JFrame{
	private ModeloFavoritosUsuario modeloFavoritosUsuarios;
	private JTable tablaFavoritos;
	private JScrollPane scrollFavoritos;
	private JButton seguirComprando, cesta;
	private JPanel panelBotones;
	private JLabel foto;

	public VentanaFavoritosUsuario() {
		super();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		List<Producto> productos = new ArrayList<>(); 
		modeloFavoritosUsuarios = new ModeloFavoritosUsuario(productos);
		tablaFavoritos = new JTable(modeloFavoritosUsuarios);
		scrollFavoritos = new JScrollPane(tablaFavoritos);
		
		getContentPane().add(scrollFavoritos, BorderLayout.CENTER);
	
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
	
	
	
	public static void main(String[] args) {
		new VentanaFavoritosUsuario();
	}

}
