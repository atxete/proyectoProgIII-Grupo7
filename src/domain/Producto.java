package domain;

import javax.swing.ImageIcon;

public class Producto {
	
	protected String nombre;
	//NO SE SI SE QUIERE INT O STRING, si eso cambiarlo
	protected String id;
	public enum tipo{VerduraFruta, CarnePescado, ProductosSecos, Bebidas, Desayuno};
	protected tipo tipo;
	protected float precio;
	protected String foto;
	protected TipoIva TipoIva;
	protected int cantidad;
	protected int precioTotal;
	
	
	
	public String getNombre() {
		return nombre;
	}
	
	public TipoIva getTipoIva() {
		return TipoIva;
	}

	public void setTipoIva(TipoIva tipoIva) {
		TipoIva = tipoIva;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(int precioTotal) {
		this.precioTotal = precioTotal;
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
	public tipo getTipo() {
		return tipo;
	}
	public void setTipo(tipo tipo) {
		this.tipo = tipo;
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
