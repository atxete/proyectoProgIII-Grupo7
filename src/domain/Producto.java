package domain;

import javax.swing.ImageIcon;

public class Producto {
	
	protected String nombre;
	//NO SE SI SE QUIERE INT O STRING, si eso cambiarlo
	protected int codigo=0;
	public enum tipo{VerduraFruta, CarnePescado, ProductosSecos, Bebidas, Desayuno};
	protected static tipo tipo;
	protected float precio;
	protected String foto;
	protected int cantidad;

	public String getNombre() {
		return nombre;
	}
	
	

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
		Producto.tipo = tipo;
	}
	public Producto(tipo tipo, String nombre, float precio, String foto, int cantidad) {
		super();
		Producto.tipo=tipo;
		this.codigo = codigo;
		this.nombre = nombre;
		
		if(precio>0) {
			this.precio=precio;
		}
		this.foto = foto;
		this.cantidad = cantidad;
	}
	
	
	
	public Producto() {
		codigo = codigo++;
		this.nombre="";
		this.precio=0;
		Producto.tipo=null;
		this.cantidad=0;
	}



	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", codigo=" + codigo + ", tipo=" + tipo + ", precio=" + precio + ", foto="
				+ foto + ", cantidad=" + cantidad + "]";
	}

	
	
	
	
	
}
