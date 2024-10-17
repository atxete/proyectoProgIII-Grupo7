package domain;

import javax.swing.ImageIcon;

public class Producto {
	
	protected String nombre;
	//NO SE SI SE QUIERE INT O STRING, si eso cambiarlo
	protected String id;
	protected enum tipo{VerduraFruta, CarnePescado, ProductosSecos, Bebidas, Desayuno};
	protected float precio;
	protected String foto;
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
	
	public Producto(String nombre, String id, float precio, String foto) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.precio = precio;
		this.foto = foto;
	}
	
	
	
	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", id=" + id + ", precio=" + precio+ "]";
	}
	
	
	
	
}
