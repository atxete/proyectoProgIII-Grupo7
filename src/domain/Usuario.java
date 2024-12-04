package domain;

public abstract class Usuario {
	int codigoUsuario = 0;
	String nombreUsuario;
	String apellidosUsuario;
	String usuarioUsuario;
	String contrasenya;
	String email;
	int admin; //si es administrador (1) o si es comprador (0)
	
	 
	public int getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
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
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Usuario(String nombre, String apellidos, String usuario, String contrasenya, String email, int admin) {
		super();
		this.nombreUsuario = nombre;
		this.apellidosUsuario = apellidos;
		this.usuarioUsuario = usuario;
		this.contrasenya = contrasenya;
		this.email = email;
		this.admin = admin;
	}
	
	
	@Override
	public String toString() {
		return "Usuario [codigoUsuario=" + codigoUsuario + ", nombre=" + nombreUsuario + ", apellidos=" + apellidosUsuario + ", usuario=" + usuarioUsuario + ", contrasenya="
				+ contrasenya + ", email=" + email + ", admin=" + "]";
	}
	
	
	
}
