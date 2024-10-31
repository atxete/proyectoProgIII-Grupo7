package domain;

import java.util.ArrayList;

public class Comprador extends Usuario{

	public ArrayList<Producto> cesta = new ArrayList<>();
	public ArrayList<Producto> listaFavoritos = new ArrayList<>();
	
	public ArrayList<Producto> getCesta() {
		return cesta;
	}
	public void setCesta(ArrayList<Producto> cesta) {
		this.cesta = cesta;
	}
	public ArrayList<Producto> getListaFavoritos() {
		return listaFavoritos;
	}
	public void setListaFavoritos(ArrayList<Producto> listaFavoritos) {
		this.listaFavoritos = listaFavoritos;
	}
	
	public Comprador(String nombre, String apellidos, String usuario, String contrasenya,
			String email, int admin, ArrayList<Producto> cesta, ArrayList<Producto> listaFavoritos) {
		super(nombre, apellidos, usuario, contrasenya, email, 0);
		this.cesta = cesta;
		this.listaFavoritos = listaFavoritos;
	}
	
	public Comprador(String nombre, String apellidos, String usuario, String contrasenya, String email) {
		super(nombre, apellidos, usuario, contrasenya, email, 0);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Comprador [cesta=" + cesta + ", listaFavoritos=" + listaFavoritos + "]";
	}
	
	
	
}
