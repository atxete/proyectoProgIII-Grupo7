package domain;

public class Administrador extends Usuario{

	public Administrador(String nombre, String apellidos, String usuario, String contrasenya, String email) {
		super(nombre, apellidos, usuario, contrasenya, email, 1);
		
	} 
}
