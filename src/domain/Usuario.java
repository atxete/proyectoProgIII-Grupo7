package domain;

public class Usuario {

	private String nombre;
	private String apellidos;
	private String usuario;
	private String contrasenya;
	private String repetirContrasenya;
	private String email;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasenya() {
		return contrasenya;
	}
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	public String getRepetirContrasenya() {
		return repetirContrasenya;
	}
	public void setRepetirContrasenya(String repetirContrasenya) {
		this.repetirContrasenya = repetirContrasenya;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Usuario(String nombre, String apellidos, String usuario, String contrasenya, String repetirContrasenya,
			String email) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.usuario = usuario;
		this.contrasenya = contrasenya;
		this.repetirContrasenya = repetirContrasenya;
		this.email = email;
	}
	
	
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellidos=" + apellidos + ", usuario=" + usuario + ", contrasenya="
				+ contrasenya + ", repetirContrasenya=" + repetirContrasenya + ", email=" + email + "]";
	}
	
	
	
}
