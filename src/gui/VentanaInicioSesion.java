package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
	
	public VentanaInicioSesion() {
		setTitle("Registro/Inicio Sesión");
	    setSize(400, 200);
	    setLocationRelativeTo(null);
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
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
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new VentanaInicioSesion();
	}
}
