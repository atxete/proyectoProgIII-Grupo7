package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import domain.Comprador;
import domain.GestorUsuarios;
import domain.Usuario;

public class VentanaRegistrarse extends JFrame{
	private JLabel lblNombre;
	private JLabel lblApellidos;
	private JLabel lblUsuario;
	private JLabel lblContrasenya;
	private JLabel lblRepetirContrasenya;
	private JLabel lblEmail;
	private JTextField tfNombre;
	private JTextField tfApellidos;
	private JTextField tfUsuario;
	private JPasswordField psContrasenya;
	private JPasswordField psRepetirContrasenya;
	private JTextField tfEmail;
	private JPanel panelTexto;
	private JPanel panelTextoTamanyo;
	private JButton btnRegistrarse;
	private JButton btnInicioSesion;
	private JPanel panelBotones;
	private JLabel lblInformacion;
	private JPanel panelInformacion;
	private JPanel panelSur;
	
	private GestorUsuarios gestorUsuarios;
	public VentanaRegistrarse(GestorUsuarios gestor) {
		this.gestorUsuarios = gestor;
		
		setTitle("Registro/Inicio Sesión");
	    setSize(625, 325);
	    setLocationRelativeTo(null);
	    setResizable(false);
	    //setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);   descomentar más tarde
		
	    lblNombre = new JLabel("Nombre: ");
	    lblApellidos = new JLabel("Apellidos: ");
	    lblUsuario = new JLabel("Usuario: ");
	    lblContrasenya = new JLabel("Contraseña: ");
	    lblRepetirContrasenya = new JLabel("Repetir contraseña: ");
	    lblEmail = new JLabel("Email: ");
	    
	    tfNombre = new JTextField();
	    tfNombre.setPreferredSize(new Dimension(150,25));
	    tfApellidos = new JTextField();
	    tfApellidos.setPreferredSize(new Dimension(150,25));
	    tfUsuario = new JTextField();
	    tfUsuario.setPreferredSize(new Dimension(150,25));
	    psContrasenya = new JPasswordField();
	    psContrasenya.setPreferredSize(new Dimension(150,25));
	    psRepetirContrasenya = new JPasswordField();
	    psRepetirContrasenya.setPreferredSize(new Dimension(150,25));
	    tfEmail = new JTextField();
	    tfEmail.setPreferredSize(new Dimension(150,25));
	    
	    panelTexto = new JPanel(new GridLayout(6,6,10,10));
	    panelTexto.add(lblNombre);
	    panelTexto.add(tfNombre);
	    panelTexto.add(lblApellidos);
	    panelTexto.add(tfApellidos);
	    panelTexto.add(lblUsuario);
	    panelTexto.add(tfUsuario);
	    panelTexto.add(lblContrasenya);
	    panelTexto.add(psContrasenya);
	    panelTexto.add(lblRepetirContrasenya);
	    panelTexto.add(psRepetirContrasenya);
	    panelTexto.add(lblEmail);
	    panelTexto.add(tfEmail);
	  
	    panelTextoTamanyo = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    panelTextoTamanyo.add(panelTexto);
	    
	    btnRegistrarse = new JButton("Registrarse");
	    btnInicioSesion = new JButton("Iniciar sesión");
	    panelBotones = new JPanel(new FlowLayout());
	    panelBotones.add(btnRegistrarse);
	    panelBotones.add(btnInicioSesion);
	    
	    lblInformacion = new JLabel("Si ya se ha registrado anteriormente, pulse iniciar sesión.");
	    panelInformacion = new JPanel();
	    panelInformacion.add(lblInformacion);
	    
	    panelSur = new JPanel(new GridLayout(2,1));
	    panelSur.add(panelBotones);
	    panelSur.add(panelInformacion);
	    
	    getContentPane().add(panelTextoTamanyo, BorderLayout.CENTER);
	    getContentPane().add(panelSur, BorderLayout.SOUTH);
	    
	    btnInicioSesion.addActionListener((e)->{
	    	GestorUsuarios gestorUsuarios = new GestorUsuarios();
			VentanaInicioSesion ventanaInicioSesion = new VentanaInicioSesion(gestorUsuarios);
			ventanaInicioSesion.setVisible(true);
			dispose();
		});
	    
	    btnRegistrarse.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		registrarUsuario();
	    	}
	    });
	    
	    setVisible(true);
	}
	
	private void registrarUsuario() {
		JFrame v = new JFrame();
		v = this;
		
		String nombre = tfNombre.getText();
		String apellidos = tfApellidos.getText();
		String usuario = tfUsuario.getText();
		String contrasenya = String.valueOf(psContrasenya.getPassword());
		String repetirContrasenya = String.valueOf(psRepetirContrasenya.getPassword());
		String email = tfEmail.getText();
		
		if(nombre.isEmpty() || apellidos.isEmpty() || usuario.isEmpty() || contrasenya.isEmpty() || repetirContrasenya.isEmpty() || email.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios. Por favor, rellénalos", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(gestorUsuarios.buscarUsuarioPorNombreDeUsuario(usuario) != null) {
			JOptionPane.showMessageDialog(this, "El usuario ya existe. Inténtalo con otro nombre de usuario", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(!contrasenya.equals(repetirContrasenya)) {
			JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden. Inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		boolean registroExitoso = gestorUsuarios.registrarUsuario(nombre, apellidos, usuario, contrasenya, repetirContrasenya, email, 0);
		if(registroExitoso) {
			JOptionPane.showMessageDialog(this, "Bienvenido comprador", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
			VentanaLoadingUsuario vlu = new VentanaLoadingUsuario(v);
			dispose();
			vlu.setVisible(true);			
			//new VentanaPrincipalUsuario();
		}else {
			JOptionPane.showMessageDialog(this, "Error al registrar usuario. Inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestorUsuarios gestorUsuarios = new GestorUsuarios();
		new VentanaRegistrarse(gestorUsuarios);
		System.out.println(gestorUsuarios.verUsuariosRegistrados());
	}

}
