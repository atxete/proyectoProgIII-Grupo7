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
		super(nombre, apellidos, usuario, contrasenya, email, admin);
		this.cesta = cesta;
		this.listaFavoritos = listaFavoritos;
	}
	
	public Comprador(String nombre, String apellidos, String usuario, String contrasenya, String email, int admin) {
		super(nombre, apellidos, usuario, contrasenya, email, admin);
		
	}
	
	@Override
	public String toString() {
		return "Comprador [cesta=" + cesta + ", listaFavoritos=" + listaFavoritos + "]";
	}
	
	
	//añadir producto a favoritos
	public void anyadirWishList(Producto p) {
		listaFavoritos.add(p);
	}
	
	
	//añadir producto a cesta
		public void anyadirCesta(Producto p) {
			cesta.add(p);
		}
	

}
