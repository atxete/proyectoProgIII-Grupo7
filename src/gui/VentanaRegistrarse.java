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
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import db.BaseDatos1;
import domain.Comprador;
import domain.Logica;
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
	private JFrame ventanaActual;
	
	//private GestorUsuarios gestorUsuarios;
	public VentanaRegistrarse(/*GestorUsuarios gestor*/) {
		//this.gestorUsuarios = gestor;
		
		ventanaActual = this;
		
		setTitle("Registro/Inicio Sesión");
	    setSize(625, 325);
	    setLocationRelativeTo(null);
	    setResizable(false);
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // descomentar más tarde
		 
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
	    	//GestorUsuarios gestorUsuarios = new GestorUsuarios();
			VentanaInicioSesion ventanaInicioSesion = new VentanaInicioSesion();
			ventanaInicioSesion.setVisible(true);
			dispose();
		});
	    
	    btnRegistrarse.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	registrarUsuario();
	           /**
	        	// Obtener los datos del formulario
	            String nombre = tfNombre.getText();
	            String apellidos = tfApellidos.getText();
	            String usuario = tfUsuario.getText();
	            String email = tfEmail.getText();
	            String contrasenya = new String(psContrasenya.getPassword());
	            String repetirContrasenya = new String(psRepetirContrasenya.getPassword());

	            // Verificar si hay campos vacíos
	            if (nombre.equals("") || apellidos.equals("") || usuario.equals("") || email.equals("") || contrasenya.equals("") || repetirContrasenya.equals("")) {
	                JOptionPane.showMessageDialog(null, "ERROR: Todos los campos son obligatorios. Por favor, complete todos los datos.");
	                return; // Salir del método si falta algún dato
	            }

	            // Verificar si las contraseñas coinciden
	            if (!contrasenya.equals(repetirContrasenya)) {
	                JOptionPane.showMessageDialog(null, "ERROR: Las contraseñas no coinciden. Por favor, inténtelo de nuevo.");
	                return; // Salir del método si las contraseñas no coinciden
	            }

	            // Verificar si el email ya está en uso
	            if (Logica.existeUsuario(email)) {
	                JOptionPane.showMessageDialog(null, "ERROR: El email ya está en uso. Por favor, ingrese otro email.");
	                return; // Salir del método si el email ya está registrado
	            }

	            // Si todo es correcto, crear el nuevo usuario
	            Logica.crearUsuario(nombre, apellidos, usuario, email, contrasenya);
	            BaseDeDatos.anyadirUsuario(nombre, apellidos, usuario, email, contrasenya);
	            
	            // Mostrar mensaje de éxito
	            JOptionPane.showMessageDialog(null, "¡Registro exitoso! Bienvenido, " + nombre + " " + apellidos + ".", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);

	            VentanaLoadingUsuario vl = new VentanaLoadingUsuario(ventanaActual);
				dispose();
				vl.setVisible(true);
				**/
	        	
	        	/*
	        	if(Logica.existeUsuario(tfEmail.getText())) {
	        		JOptionPane.showMessageDialog(null, "ERROR: Ya existe una cuenta con ese email. Utilice otro");
	        		tfEmail.setText("");
	        	}
	        	*/
	        	//esto lo he puesto para comprobar si las dos veces que pone la contraseña pone la misma
	        	
	        	/*if(!psContrasenya.getPassword().toString().equals(psRepetirContrasenya.getPassword().toString())) {
	        		JOptionPane.showMessageDialog(null, "ERROR: Las contraseñas no coinciden. Intentalo de nuevo");
	        		psContrasenya.setText("");
	        		psRepetirContrasenya.setText("");
	        		
	        	}*/
	        	/*
	        	if(!tfEmail.getText().equals("") && psContrasenya.getPassword().equals(psRepetirContrasenya.getPassword()) && !psContrasenya.getPassword().equals("") && !tfNombre.getText().equals("") && !tfApellidos.getText().equals("") && !tfUsuario.getText().equals("")) {
	        		String er="[a-zA-Z]{1,}.{0,}[a-zA-Z]{0,}@[a-zA-Z]{1,}\\.[a-z]{2,}";
	        		String email = tfEmail.getText();
	        		if(Pattern.matches(er, email)) {
	        			Logica.crearUsuario(tfNombre.getText(), tfApellidos.getText(), tfUsuario.getText(), tfEmail.getText(), psContrasenya.getPassword().toString());
	        			//aquí hay que añadir el codigo tanto arriba como abajo
	        			BaseDeDatos.anyadirUsuario(tfNombre.getText(), tfApellidos.getText(), tfUsuario.getText(), tfEmail.getText(), psContrasenya.getPassword().toString());
	        			
	        		}else {
	        			JOptionPane.showMessageDialog(null, "ERROR: El formato del email no es correcto");
	        		}
	        	}else JOptionPane.showMessageDialog(null, "ERROR: Rellene todos los campos");
				
	        	*/
	        }
	    });

	     
	    //IAG: (herramienta: chatGPT)
	    //ADAPTADO (crear el keyListener lo sabíamos hacer, y saber cuál teníamos que usar también.
	    //Sin embargo, no sabíamos como acceder exactamente a la tecla enter para todos los keyListener creados.)
	    tfNombre.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					registrarUsuario();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		
			
		});
	    
	    tfApellidos.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					registrarUsuario();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		
			
		});
	    
	    tfUsuario.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					registrarUsuario();
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
					registrarUsuario();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		
			
		});
	    
	    psRepetirContrasenya.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					registrarUsuario();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		
			
		});
	    
	    
	    tfEmail.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					registrarUsuario();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		
			
		});
	    
	    setVisible(true);
	    this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				BaseDatos1.cerrarConexion();
			}
			
		});
		//BaseDeDatos.cerrarConexion();
	}
	
	private void registrarUsuario() {
	    String email = tfEmail.getText();
	    String contrasena = String.valueOf(psContrasenya.getPassword());
	    String repetirContrasena = String.valueOf(psRepetirContrasenya.getPassword());
	    String nombre = tfNombre.getText();
	    String apellidos = tfApellidos.getText();
	    String usuario = tfUsuario.getText();

	    if (email.isEmpty() || contrasena.isEmpty() || repetirContrasena.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || usuario.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "ERROR: Rellene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    if (!contrasena.equals(repetirContrasena)) {
	        JOptionPane.showMessageDialog(null, "ERROR: Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    String er = "[a-zA-Z]{1,}.{0,}[a-zA-Z]{0,}@[a-zA-Z]{1,}\\.[a-z]{2,}";
	    if (!Pattern.matches(er, email)) {
	        JOptionPane.showMessageDialog(null, "ERROR: El formato del email no es correcto", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    if (Logica.existeUsuario(email)) {
	        JOptionPane.showMessageDialog(null, "ERROR: Ya existe una cuenta con ese email. Utilice otro", "Error", JOptionPane.ERROR_MESSAGE);
	        tfEmail.setText(""); 
	        return;
	    }

	    Logica.crearUsuario(nombre, apellidos, usuario, email, contrasena);
	    BaseDatos1.anyadirUsuario(nombre, apellidos, usuario, email, contrasena);
	    JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente (" + nombre + " " + apellidos +")", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	    VentanaLoadingUsuario vl = new VentanaLoadingUsuario(ventanaActual);
		dispose();
		vl.setVisible(true);
		
	    
	    tfEmail.setText("");
	    psContrasenya.setText("");
	    psRepetirContrasenya.setText("");
	    tfNombre.setText("");
	    tfApellidos.setText("");
	    tfUsuario.setText("");
	}

		
		/*
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
		
		if(gestorUsuarios.buscarUsuarioPorNombreDeEmail(email) != null) {
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
			//dispose();
			this.setVisible(false);
			vlu.setVisible(true);			
			//new VentanaPrincipalUsuario();
		}else {
			JOptionPane.showMessageDialog(this, "Error al registrar usuario. Inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	*/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//GestorUsuarios gestorUsuarios = new GestorUsuarios();
		//new VentanaRegistrarse(gestorUsuarios);
		//System.out.println(gestorUsuarios.verUsuariosRegistrados());
	}

}
