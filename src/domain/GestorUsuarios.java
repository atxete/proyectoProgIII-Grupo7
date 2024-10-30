package domain;

import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {

	private List<Usuario> usuariosRegistrados;
	
	public GestorUsuarios() {
		usuariosRegistrados = new ArrayList<>();
	}
	
	public boolean registrarUsuario(Usuario nuevoUsuario) {
		if(buscarUsuarioPorNombreDeUsuario(nuevoUsuario.getUsuario()) == null) {
			usuariosRegistrados.add(nuevoUsuario);
			return true;
		}
		return false;
	}
	
	public boolean iniciarSesion(String usuario, String contrasenya) {
		Usuario usuarioEncontrado = buscarUsuarioPorNombreDeUsuario(usuario);
		if(usuarioEncontrado != null && usuarioEncontrado.getContrasenya().equals(contrasenya)) {
			return true;
		}
		return false;
	}
	
	public String verUsuariosRegistrados() {
		if(usuariosRegistrados.isEmpty()) {
			return "No hay usuarios registrados";
		}
		
		StringBuilder listadoUsuarios = new StringBuilder("Usuarios registrados: \n");
		for(Usuario u: usuariosRegistrados) {
			listadoUsuarios.append("Usuario: ").append(u.getUsuario())
						   .append("Nombre: ").append(u.getNombre())
						   .append("Email: ").append(u.getEmail());
		}
		return listadoUsuarios.toString();
	}
	
	private Usuario buscarUsuarioPorNombreDeUsuario(String usuario) {
		for(Usuario u: usuariosRegistrados) {
			if(u.getUsuario().equals(usuario)) {
				return u;
			}
		}
		return null;
	}
}
