package domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDeDatos {

	public static Connection conexion;
	private static Logger logger = Logger.getLogger("BaseDeDatos");
	
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
			logger.log(Level.INFO, "Cerrando conexión");
			conexion.close();
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Excepción", e);
		}
	}
		
}
