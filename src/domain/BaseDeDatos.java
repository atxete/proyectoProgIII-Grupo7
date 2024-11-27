package domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDeDatos {

	public static Connection conexion;
	private static Logger logger = Logger.getLogger("BaseDeDatos");
	private static HashMap<String, Usuario> users = new HashMap<String, Usuario>();
	
	//abrimos la conexión con la base de datos
	public static boolean abrirConexion(String nombreBD, boolean reiniciaBD) {
		try {
			logger.log(Level.INFO, "Carga de librería org.sqlite.JDBC");
			Class.forName("org.sqlite.JDBC");
			logger.log(Level.INFO, "Abriendo conexión con" + nombreBD);
			conexion = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
			
			if(reiniciaBD) {
				Statement stmt = conexion.createStatement();
				
				
				//creación de tablas
				String sent = "CREATE TABLE IF NOT EXISTS usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre varchar(10), apellidos varchar(25), usuario varchar(15), contrasenya varchar(25), email varchar(25), admin int);";
				logger.log(Level.INFO, "Statement: " + sent);
				stmt.executeUpdate(sent);
				
				sent = "CREATE TABLE IF NOT EXISTS wishlist (id INTEGER PRIMARY KEY AUTOINCREMENT, idUsuario INTEGER REFERENCES usuario (id), idProducto int);";
				logger.log(Level.INFO, "Statement: " + sent);
				stmt.executeUpdate(sent);
				
				sent = "CREATE TABLE IF NOT EXISTS cestas (id INTEGER PRIMARY KEY AUTOINCREMENT, idUsuario INTEGER REFERENCES usuario (id), idProducto int);";
				logger.log(Level.INFO, "Statement: " + sent);
				stmt.executeUpdate(sent);
				
				sent = "CREATE TABLE IF NOT EXISTS compra (id INTEGER PRIMARY KEY AUTOINCREMENT, idUsuario INTEGER REFERENCES usuario(id), fecha bigint, precio float);";
				logger.log(Level.INFO, "Statement: " + sent);
				stmt.executeUpdate(sent);
				
				sent = "CREATE TABLE IF NOT EXISTS compraP (id INTEGER REFERENCES compra(id), idProducto int);";
				logger.log(Level.INFO, "Statement: " + sent);
				stmt.executeUpdate(sent);
				
				sent = "CREATE TABLE IF NOT EXISTS producto (codigo INTEGER PRIMARY KEY AUTOINCREMENT, tipo varchar(10), nombre varchar(10), id int, precio float, foto varchar(50));";
				logger.log(Level.INFO, "Statement: " + sent);
				stmt.executeUpdate(sent);
				
				/*
				//creación de productos
				sent = "INSERT INTO producto(tipo, nombre, id, precio, foto) VALUES ('Bebidas', 'Agua', '1', '1.95', 'imagenes/agua.jpg');";
				stmt.executeUpdate(sent);
				
				sent = "INSERT INTO producto(tipo, nombre, id, precio, foto) VALUES ('CarnePescado', 'Entrecot', '2', '8.5', 'imagenes/entrecot.jpg');";
				stmt.executeUpdate(sent);
				
				sent = "INSERT INTO producto(tipo, nombre, id, precio, foto) VALUES ('ProductosSecos', 'Almendras', '3', '2.45', 'imagenes/almendras.jpg');";
				stmt.executeUpdate(sent);
				
				sent = "INSERT INTO producto(tipo, nombre, id, precio, foto) VALUES ('Desayuno', 'Cereales de fibra', '4', '3.69', 'imagenes/cerealesFibra.jpg');";
				stmt.executeUpdate(sent);
				
				sent = "INSERT INTO producto(tipo, nombre, id, precio, foto) VALUES ('VerduraFruta', 'Kiwi', '5', '0.97', 'imagenes/kiwi.jpg');";
				stmt.executeUpdate(sent);
				*/
			}
			
			return true;
		}catch(Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return false;
		}
	}
	
	//cerramos la conexión con la base de datos
	public static void cerrarConexion() {
		try {
			logger.log(Level.INFO, "Conexión cerrada");
			conexion.close();
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Excepción", e);
		}
	}
	
	/*
	 int codigoUsuario = 0;
	String nombreUsuario;
	String apellidosUsuario;
	String usuarioUsuario;
	String contrasenya;
	String email;
	int admin;
	 */
	
	
	//obtener todos los usuarios de la base de datos (compradores y administradores)
	public static HashMap<String, Usuario> getUsuarios(){
		try{
			Statement statement = conexion.createStatement();
			String sent = "SELECT * FROM usuario";
			logger.log(Level.INFO, "Statement: "+sent);
			ResultSet rs = statement.executeQuery(sent);
			while(rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				String usuario = rs.getString("usuario");
				String email = rs.getString("email");
				String contrasenya = rs.getString("contrasenya");
				if(rs.getInt("admin")==1) {
					Administrador a = new Administrador(nombre, apellidos, usuario, contrasenya, email);
					a.setCodigoUsuario(id);
					users.put(email, a);
				}else {
					Comprador c = new Comprador(nombre, apellidos, usuario, contrasenya, email);
					c.setCodigoUsuario(id);
					users.put(email, c);
				}
			}
			rs.close();
			statement.close();
			return users;
		}catch(Exception e) {
			logger.log(Level.SEVERE, "Excepción", e);
			return null;
		}		
	}
	
	//obtener un usuario de la base de datos sabiendo su id
	public static Usuario getUsuarioId(int id1) {
		try {
			Statement statement = conexion.createStatement();
			String sent = "SELECT * FROM usuario WHERE id = '"+id1+"'";
			logger.log(Level.INFO, "Statement: "+sent);
			ResultSet rs = statement.executeQuery(sent);
			while(rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				String usuario = rs.getString("usuario");
				String email = rs.getString("email");
				String contrasenya = rs.getString("contrasenya");
				if(rs.getInt("admin")==1) {
					Administrador a = new Administrador(nombre, apellidos, usuario, contrasenya, email);
					a.setCodigoUsuario(id);
					return a;
				}else {
					Comprador c = new Comprador(nombre, apellidos, usuario, contrasenya, email);
					c.setCodigoUsuario(id);
					return c;
				}
			}
			rs.close();
			statement.close();
		}catch(Exception e) {
			logger.log(Level.SEVERE, "Excepción",e);
			return null;
		}
		return null;
	}
	
	//añadir nuevo USUARIO COMPRADOR a la base de datos
	public static void anyadirUsuario(String nombre, String apellidos, String usuario, String email, String contrasenya) {
		String sent="";
		try{
			Statement statement = conexion.createStatement();
			
			sent="INSERT INTO usuario (nombre, apellidos, usuario, contrasenya, email, admin) values ('"+nombre+"', '"+apellidos+"', '"+usuario+"', '"+contrasenya+"', '"+email+"', 1)";
			logger.log(Level.INFO, "Lanzada actualización a la base de datos "+sent);
			int val = statement.executeUpdate(sent);
			logger.log(Level.INFO, "Añadida "+val+" fila a la base de datos\t" + sent);
						
			
			statement.close();
		}catch(Exception e) {
			logger.log(Level.SEVERE, "Error en inserción de admin a base de datos\t" + e);
		}
	}
	
	//obtenr lista de productos de la wishList o de la cesta del comprador
	public static ArrayList<Producto> getWishListOCesta(int id, int tipo){ //tipo hace referencia a wishlist o cesta
		String sent;
		try{
			Statement statement = conexion.createStatement();
			switch(tipo) {
				case 0: //si tipo=0 -> WishList
					sent="SELECT idProducto FROM wishList WHERE idUsurario = "+id+"";
					break;
				case 1: //si tipo=1 -> cesta
					sent="SELECT idProducto FROM cestas WHERE idUsurario = "+id+"";
					break;
				default:
					throw new SQLException("type not defined");
			}
			ArrayList<Producto> pl = new ArrayList<Producto>();
			logger.log(Level.INFO, "Statement: "+sent);
			ResultSet rs = statement.executeQuery(sent);
			while(rs.next()) {
				int idP = rs.getInt("idProducto");
				for(Producto p : Logica.getListaProductos()) {
					if(idP==p.getCodigo()) {
						pl.add(p);
						break;
					}
				}
			}
			rs.close();
			statement.close();
			return pl;
		}catch(Exception e) {
			logger.log(Level.SEVERE, "Excepción",e);
			return null;
		}
	}
	
	
	//añadir un nueco producto a la cesta o a la wishlist dentro de la base de datos
	public static void anyadirProducto(int idUsuario, int idProducto, int tipo) throws SQLException{ //tipo se refiere si lo queremos guardar en la wishlist(0) o en la compra(1)
		String sent="";
		switch(tipo) {
		case 0:
			sent = "INSERT INTO wishList(idUsuario, idProducto) VALUES("+idUsuario+", "+idProducto+")";
			break;
		case 1:
			sent = "INSERT INTO cestas (idUsuario, idProducto) VALUES ("+idUsuario + ", " + idProducto + ")";
			break;
		default:
			throw new SQLException("type not defined");
		}
		try(Statement statement = conexion.createStatement()){
			logger.log(Level.INFO, "Lanzada actualización a base de datos: "+sent);
			int val = statement.executeUpdate( sent );
			logger.log( Level.INFO, "Añadida " + val + " fila a base de datos\t" + sent );
			
		}catch (SQLException e) {
			logger.log( Level.SEVERE, "Error en inserción de base de datos\t" + e );
		}
	}
	
	
	//borrar producto de la cesta(1) o de la wishlist(0)
	public static void eliminarProducto(int idUsuario, int idProducto, int tipo)throws SQLException{
		String sent="";
		switch(tipo) {
		case 0: 
			sent = "DELETE FROM wishList WHERE idProducto = " + idProducto + " AND idUsuario = " + idUsuario + "";
			break;
		case 1:
			sent = "DELETE FROM cestas WHERE idProducto = " + idProducto + " AND idUsuario = " + idUsuario + ";";
			break;
		default:
			throw new SQLException("type not defined");
		}
		try(Statement statement = conexion.createStatement()){
			logger.log(Level.INFO, "Lanzada actualización a base de datos: "+sent);
			int val = statement.executeUpdate(sent);
			logger.log(Level.INFO, "Eliminada "+val+" fila de base de datos\t"+sent);
			
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Error en eliminación de base de datos\t"+e);
		}
	}
	
	
	
	
}
