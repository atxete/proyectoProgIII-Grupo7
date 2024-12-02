package gui;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import domain.BaseDeDatos;
import domain.GestorUsuarios;

public class VentanaInicial extends JFrame {
	
	private boolean temp = true;
	
	
	public VentanaInicial() {
		
		BaseDeDatos.abrirConexion("BaseDatos.db", true);
		
		VentanaInicial ventanaActual = this;
		
		int ancho = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
		int alto = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
		this.setSize(ancho, alto);
		this.setTitle("Bienvenido/a al supermercado Deuski");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		
		
		try {
			JLabel imagen = new JLabel();
			ImageIcon logo = new ImageIcon("imagenes/Logo.jpg");
			imagen.setIcon(logo);
            imagen.setHorizontalAlignment(JLabel.CENTER);
            
            imagen.setOpaque(true);
            imagen.setBackground(Color.WHITE);
            
            
            this.add(imagen);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		Timer temporizador = new Timer(5000, (e)->{
			if(temp) {
				VentanaInicial.this.setVisible(false);
				GestorUsuarios gestorUsuarios = new GestorUsuarios();
				new VentanaInicioSesion(gestorUsuarios);
			}
		});
		
		temporizador.setRepeats(false);
		temporizador.start();
		
		
		
		//AL PULSAR CTRL+D SE CIERRA ESTA VENTANA Y SE ABRE DIRECTAMENTE LA DE INICIO SESIÓN, SIN ESPERAR 5 SEGUNDOS
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_D && e.isControlDown()) {
					GestorUsuarios gestorUsuarios = new GestorUsuarios();
					VentanaInicioSesion v = new VentanaInicioSesion(gestorUsuarios);
					ventanaActual.setVisible(false);
					//VentanaInicial.this.dispose();
					temp=false;
				}
			}
		});
		
		
		this.setVisible(true);
		//BaseDeDatos.cerrarConexion();
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				BaseDeDatos.cerrarConexion();
			}
			
			
		});
		
		
		
	}
	public static void main(String[] args) {
		new VentanaInicial();
	}
}
