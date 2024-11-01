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

import domain.GestorUsuarios;
import domain.Usuario;

public class VentanaInicioSesion extends JFrame{
	private JLabel lblUsuario;
	private JLabel lblContrasenya;
	private JTextField tfUsuario;
	private JPasswordField psContrasenya;
	private JButton btnIniciarSesion;
	private JButton btnRegistrarse;
	private JLabel lblInformacion;
	private JPanel panelTexto;
	private JPanel panelTextoTamanyo;
	private JPanel panelBotones;
	private JPanel panelInformacion;
	private JPanel panelSur;
	
	private GestorUsuarios gestorUsuarios;
	public VentanaInicioSesion(GestorUsuarios gestor){
		this.gestorUsuarios = gestor;
		
		
		setTitle("Registro/Inicio Sesión");
	    setSize(400, 200);
	    setLocationRelativeTo(null);
	    setResizable(false);
	    //setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);   descomentar más tarde
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		lblUsuario = new JLabel("Usuario: ");
		lblContrasenya = new JLabel("Contraseña: ");
		tfUsuario = new JTextField();
		psContrasenya = new JPasswordField();
		tfUsuario.setPreferredSize(new Dimension(150,25));
		psContrasenya.setPreferredSize(new Dimension(150,25));
		
		panelTexto = new JPanel(new GridLayout(2,2,10,10));
		panelTexto.add(lblUsuario);
		panelTexto.add(tfUsuario);
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
				iniciarSesion();
			}
		});
		
		setVisible(true);
	}

	private void iniciarSesion() {
		
		JFrame v = new JFrame();
		v = this;
		
		String usuario = tfUsuario.getText();
		String contrasenya = String.valueOf(psContrasenya.getPassword());
		
		if(usuario.isEmpty() || contrasenya.isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo de usuario y contraseña son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		Usuario loginExitoso = gestorUsuarios.iniciarSesion(usuario, contrasenya);
		
		if(loginExitoso != null) {
			if(loginExitoso.getAdmin() == 1) {
				JOptionPane.showMessageDialog(this, "Bienvenido administrador", "Inicio de sesión exitoso.", JOptionPane.INFORMATION_MESSAGE);
				new VentanaPrincipalAdmin();
			}else {
				JOptionPane.showMessageDialog(this, "Bienvenido comprador", "Inicio de sesión exitoso.", JOptionPane.INFORMATION_MESSAGE);
				VentanaLoading vl = new VentanaLoading(v);
				dispose();
				vl.setVisible(true);
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
