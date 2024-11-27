package domain;

import java.io.Serializable;
import java.lang.System.Logger;
import java.util.ArrayList;

public class Logica implements Serializable{
	
	public static ArrayList<Producto> listaProductos = new ArrayList<>();
	//private static Logger logger = Logger.getLogger( "Logica" );
	private static Usuario usuario;
	
	
	public Logica(Usuario usuario) {
		super();
		Logica.usuario = usuario;
	}

	public Logica() {
		super();
	}
	
	public static Usuario getUsuario() {
		return usuario;
	}

	public static void setUsuario(Usuario usuario) {
		Logica.usuario = usuario;
	}

	public static ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}

	public static void setListaProductos(ArrayList<Producto> listaProductos) {
		Logica.listaProductos = listaProductos;
	}
	@Override
	public String toString() {
		return "Logica [ usuario="+ usuario+ "]";
	}
	
	
	
	
	
	
	
}
