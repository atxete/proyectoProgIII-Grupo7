package domain;

public /*abstract*/ class Usuario {
	String nombreUsuario;
	String apellidosUsuario;
	String usuarioUsuario;
	String contrasenya;
	String repetirContrasenya;
	String email;
	//int admin; //si es administrador (1) o si es comprador (0)
	
	public String getNombre() {
		return nombreUsuario;
	}
	public void setNombre(String nombre) {
		this.nombreUsuario = nombre;
	}
	public String getApellidos() {
		return apellidosUsuario;
	}
	public void setApellidos(String apellidos) {
		this.apellidosUsuario = apellidos;
	}
	public String getUsuario() {
		return usuarioUsuario;
	}
	public void setUsuario(String usuario) {
		this.usuarioUsuario = usuario;
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
		this.nombreUsuario = nombre;
		this.apellidosUsuario = apellidos;
		this.usuarioUsuario = usuario;
		this.contrasenya = contrasenya;
		this.repetirContrasenya = repetirContrasenya;
		this.email = email;
	}
	
	
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombreUsuario + ", apellidos=" + apellidosUsuario + ", usuario=" + usuarioUsuario + ", contrasenya="
				+ contrasenya + ", repetirContrasenya=" + repetirContrasenya + ", email=" + email + "]";
	}
	
	
	
}
