package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import domain.BaseDeDatos;
import domain.GestorUsuarios;
import domain.Logica;
import domain.Usuario;


import java.util.regex.Pattern;

import javax.swing.*;

import domain.Comprador;
import domain.BaseDeDatos;
import domain.Logica;
import gui.VentanaPrincipalAdmin;



public class VentanaInicioSesion extends JFrame {
	private JLabel lblEmail;
	private JLabel lblContrasenya;
	private JTextField tfEmail;
	private JPasswordField psContrasenya;
	private JButton btnIniciarSesion;
	private JButton btnRegistrarse;
	private JLabel lblInformacion;
	private JPanel panelTexto;
	private JPanel panelTextoTamanyo;
	private JPanel panelBotones;
	private JPanel panelInformacion;
	private JPanel panelSur;
	private JFrame ventanaActual;
	
	private GestorUsuarios gestorUsuarios;
	public VentanaInicioSesion(GestorUsuarios gestor){
		this.gestorUsuarios = gestor;
		
		ventanaActual = this;
		
		setTitle("Registro/Inicio Sesión");
	    setSize(400, 200);
	    setLocationRelativeTo(null);
	    setResizable(false);
	    //setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);   descomentar más tarde
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		lblEmail = new JLabel("Email: ");
		lblContrasenya = new JLabel("Contraseña: ");
		tfEmail = new JTextField();
		psContrasenya = new JPasswordField();
		tfEmail.setPreferredSize(new Dimension(150,25));
		psContrasenya.setPreferredSize(new Dimension(150,25));
		
		panelTexto = new JPanel(new GridLayout(2,2,10,10));
		panelTexto.add(lblEmail);
		panelTexto.add(tfEmail);
		panelTexto.add(lblContrasenya);
		panelTexto.add(psContrasenya);
		
		panelTextoTamanyo = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelTextoTamanyo.add(panelTexto);		
		
		btnIniciarSesion = new JButton("Iniciar sesión");
		btnRegistrarse = new JButton("Registrarse");
		lblInformacion = new JLabel("Si no se ha registrado anteriormente, pulse registrarse.");
		
		panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
		panelBotones.add(btnRegistrarse);
		panelBotones.add(btnIniciarSesion);
		
		panelInformacion = new JPanel();
		panelInformacion.add(lblInformacion);
		
		panelSur = new JPanel(new GridLayout(2,1));
		panelSur.add(panelBotones);
		panelSur.add(panelInformacion);
		
		getContentPane().add(panelTextoTamanyo, BorderLayout.CENTER);
		getContentPane().add(panelSur, BorderLayout.SOUTH);
		
		btnRegistrarse.addActionListener((e)->{
			GestorUsuarios gestorUsuarios = new GestorUsuarios();
			VentanaRegistrarse ventanaRegistrarse = new VentanaRegistrarse(gestorUsuarios);
			ventanaRegistrarse.setVisible(true);
			dispose();
		});
		  
		
		btnIniciarSesion.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//iniciarSesion();
				if(!tfEmail.getText().equals("") && !psContrasenya.getText().equals("")) {
					if(Logica.existeUsuario(tfEmail.getText())) {
						if(Logica.usuarioCorrecto(tfEmail.getText(), psContrasenya.getText())!=null) {
							if(Logica.UsuarioComprador(tfEmail.getText())) {
								((Comprador) Logica.getUsuario()).setListaFavoritos(BaseDeDatos.getWishListOCesta(Logica.getUsuario().getCodigoUsuario(), 0));
								((Comprador) Logica.getUsuario()).setCesta(BaseDeDatos.getWishListOCesta(Logica.getUsuario().getCodigoUsuario(), 1));
								JOptionPane.showMessageDialog(ventanaActual, "Bienvenido comprador (" + Logica.getUsuario().getNombre() + " " + Logica.getUsuario().getApellidos() +")" , "Inicio de sesión exitoso.", JOptionPane.INFORMATION_MESSAGE);
								VentanaLoadingUsuario vl = new VentanaLoadingUsuario(ventanaActual);
								dispose();
								vl.setVisible(true);
								
							}else {
								JOptionPane.showMessageDialog(ventanaActual, "Bienvenido administrador (" + Logica.getUsuario().getNombre() + " " + Logica.getUsuario().getApellidos() +")", "Inicio de sesión exitoso.", JOptionPane.INFORMATION_MESSAGE);
								VentanaLoadingAdmin vl2 = new VentanaLoadingAdmin(ventanaActual);
								dispose();
								vl2.setVisible(true);
							}
						}else {
							JOptionPane.showMessageDialog(null, "ERROR: Contraseña incorrecta. Vuelve a intentarlo");
						}
					}else {
						JOptionPane.showMessageDialog(null, "ERROR: No existe ninguna cuenta con ese email. REGISTRESE");
					}
				}else {
					JOptionPane.showMessageDialog(null, "ERROR: Rellene todos los datos");
				}
				tfEmail.setText("");
				psContrasenya.setText("");
			}
		});
		
		
	    //IAG: (herramienta: chatGPT)
	    //ADAPTADO (crear el keyListener lo sabíamos hacer, y saber cuál teníamos que usar también.
	    //Sin embargo, no sabíamos como acceder exactamente a la tecla enter para todos los keyListener creados.)
		tfEmail.addKeyListener(new KeyListener() {
//estaba puesto tfUsuario
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					iniciarSesion();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		
		});
		

		psContrasenya.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					iniciarSesion();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		
			
		});
		
		setVisible(true);
		//BaseDeDatos.cerrarConexion();
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				BaseDeDatos.cerrarConexion();
			}
			
		});

	}

	private void iniciarSesion() {
		
		JFrame v = new JFrame();
		v = this;
		
		String usuario = tfEmail.getText(); // (aqui esta hecho cuando ponia tfUsuario)
		String contrasenya = String.valueOf(psContrasenya.getPassword());
		
		if(usuario.isEmpty() || contrasenya.isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo de usuario y contraseña son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		Usuario loginExitoso = gestorUsuarios.iniciarSesion(usuario, contrasenya);
		
		if(loginExitoso != null) {
			if(loginExitoso.getAdmin() == 1) {
				JOptionPane.showMessageDialog(this, "Bienvenido administrador", "Inicio de sesión exitoso.", JOptionPane.INFORMATION_MESSAGE);
				//new VentanaPrincipalAdmin();
				VentanaLoadingAdmin vla = new VentanaLoadingAdmin(v);
				dispose();
				vla.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(this, "Bienvenido comprador", "Inicio de sesión exitoso.", JOptionPane.INFORMATION_MESSAGE);
				VentanaLoadingUsuario vlu = new VentanaLoadingUsuario(v);
				dispose();
				vlu.setVisible(true);
				//new VentanaPrincipalUsuario();
			}
			dispose();
		}else {
			JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos. Inténtalo de nuevo", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public static void main(String[] args) {
		GestorUsuarios gestorUsuarios = new GestorUsuarios();
		new VentanaInicioSesion(gestorUsuarios);
	}

	
}
