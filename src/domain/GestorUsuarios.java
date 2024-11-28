package domain;

import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {

	private List<Usuario> usuariosRegistrados;
	
	public GestorUsuarios() {
		usuariosRegistrados = new ArrayList<>();
		//usuarios de prueba
		usuariosRegistrados.add(new Administrador("Aratz", "Bergado Fuentes", "atxete", "1234","ianaratz.bergado@opendeusto.es"));
		usuariosRegistrados.add(new Administrador("Xabier", "Aguiriano Fernández", "XabierAguiriano", "1234","xabier.aguiriano@opendeusto.es"));
		usuariosRegistrados.add(new Administrador("Naroa", "Manterola Nazabal", "naroaManterola", "1234","n.manterola@opendeusto.es"));
		usuariosRegistrados.add(new Administrador("Jon", "Ruiz Mezo", "jonru21", "1234","jon.r@opendeusto.es"));
		usuariosRegistrados.add(new Comprador("Juan", "Gallego Rica", "juanga", "1234","juanga@opendeusto.es",0));
		usuariosRegistrados.add(new Comprador("Iker", "Carrasco Llorente", "ikerca", "1234","ikerca@opendeusto.es",0));
		
	}
	
	public boolean registrarUsuario(String nombre, String apellidos, String usuario, String contrasenya, String repetirContrasenya, String email, int admin) {
		if(buscarUsuarioPorNombreDeUsuario(usuario) == null && contrasenya.equals(repetirContrasenya)) {
			Usuario nuevoUsuario;
			if(admin == 0) {
				nuevoUsuario = new Comprador(nombre, apellidos, usuario, contrasenya, email,admin);
			}else {
				nuevoUsuario = new Administrador(nombre, apellidos, usuario, contrasenya, email);
			}
			usuariosRegistrados.add(nuevoUsuario);
			return true;
		}
		return false;
	}
	
	public Usuario iniciarSesion(String usuario, String contrasenya) {
		Usuario usuarioEncontrado = buscarUsuarioPorNombreDeUsuario(usuario);
		if(usuarioEncontrado != null && usuarioEncontrado.getContrasenya().equals(contrasenya)) {
			return usuarioEncontrado;
		}
		return null;
	}
	
	
	//IAG (herramienta:chatGPT)
	//ADAPTADO (para crear los StringBuilder se ha necesitado ayuda de chatGPT, ya que no sabíamos como hacer para imprimir los usuarios por pantalla.) 
	public String verUsuariosRegistrados() {
		if(usuariosRegistrados.isEmpty()) {
			return "No hay usuarios registrados";
		}
		StringBuilder listadoAdministradores = new StringBuilder(" - Administradores:\n ");;
		StringBuilder listadoCompradores = new StringBuilder(" - Compradores:\n ");
		StringBuilder listadoUsuarios = new StringBuilder("Usuarios registrados: \n");
		int i = 1;
		int j = 1;
		for(Usuario u: usuariosRegistrados) {
			if(u.getAdmin() == 1) {
				listadoAdministradores.append("   Administrador ").append(i).append(": ")
					.append(u.getUsuario())
					.append(" Nombre: ").append(u.getNombre())
					.append(" Email: ").append(u.getEmail()).append("\n ");
				i = i+1;
			}else {
				listadoCompradores.append("   Comprador ").append(j).append(": ")
					.append(u.getUsuario())
					.append(" Nombre: ").append(u.getNombre())
					.append(" Email: ").append(u.getEmail()).append("\n ");
				j = j+1;
			}
		}
		listadoUsuarios.append(listadoAdministradores).append("\n").append(listadoCompradores);
		return listadoUsuarios.toString(); 
	}
	
	public Usuario buscarUsuarioPorNombreDeUsuario(String usuario) {
		for(Usuario u: usuariosRegistrados) {
			if(u.getUsuario().equals(usuario)) {
				return u;
			}
		}
		return null;
	}
}
