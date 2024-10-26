package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaPrincipalUsuario extends JFrame{
	
	/**ESTO LO HE ESCRITO PARA VER SI EL ACTION LISTENER DE 
	 * MI VENTANA FUNCIONA Y ABRE ESTA VENTANA**/
	
	JLabel lblPrueba;
	public VentanaPrincipalUsuario(){
		setBounds(200, 50, 400, 400);
		setTitle("Ventana Principal Usuario");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		lblPrueba = new JLabel("LABEL DE PRUEBA");
		getContentPane().add(lblPrueba);
		setVisible(true);
	}
	public static void main(String[] args) {
		new VentanaPrincipalUsuario();
	}
}
