package domain;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Logica implements Serializable{
	
	public static ArrayList<Producto> listaProductos = new ArrayList<>();
	private static Logger logger = Logger.getLogger( "Logica" );
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
	
	
	
	
	
	//comprobar si un usuario existe en la base de datos
		public static boolean existeUsuario(String email) {
			if(BaseDeDatos.getUsuarios().containsKey(email)) {
				return true;
			}else {
				return false;
			}
		}
		
		
		//comprobar que el email y la contraseña coinciden
		public static Usuario usuarioCorrecto(String email, String contraseya) {
			if(BaseDeDatos.getUsuarios().get(email).getContrasenya().equals(contraseya)) {
				Logica.usuario=BaseDeDatos.getUsuarios().get(email);
				logger.log(Level.INFO, "Existe usuario en la BD");
				return BaseDeDatos.getUsuarios().get(email);
			}else {
				return null;
			}
		}
		
		//comprobar si el usuario es comprador
		public static boolean UsuarioComprador(String email) {
			if(BaseDeDatos.getUsuarios().get(email) instanceof Comprador) {
				return true;
			}else {
				return false;
			}
		}
		
		//crear un nuevo usuario
		public static void crearUsuario(String nombre, String apellidos, String usuario, String email, String contrasenya) {
			Comprador c1 = new Comprador(nombre, apellidos,usuario, contrasenya, email,0);
			BaseDeDatos.getUsuarios().put(c1.getEmail(), c1);
		}

		//guardar los productos en un fichero serializado
		public static void guardarProductos(String fichero) {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero));
				oos.writeObject(listaProductos);
				logger.log(Level.INFO, "Productos guardados correctamente en " + fichero);
				oos.close();
			}catch(IOException e) {
				logger.log(Level.INFO, "ERROR AL GUARDAR LOS PRODUCTOS EN " + fichero);
			}
		}
		
		//cargar los productos desde el fichero
		public static void cargarProductos(String fichero) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero));
				@SuppressWarnings("unchecked")
				ArrayList<Producto> cargado = (ArrayList<Producto>) ois.readObject();
				listaProductos = cargado;
				ois.close();
				logger.log(Level.INFO, "Productos cargados correctamente desde "+ fichero);
			}catch(Exception e){
				logger.log(Level.INFO, "ERROR EN LA CARGA DE FICHERO: " + fichero);
			}
		}
	
	
	
	
	
	
}
