package domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import domain.Producto.tipo;

public class BaseDatos1 {

	public static Connection conexion;
	private static Logger logger = Logger.getLogger("BaseDeDatos");
	//private static Logger logger = Logger.getLogger("BaseDatos");
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
//				String sent = "CREATE TABLE IF NOT EXISTS usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre varchar(10), apellidos varchar(25), usuario varchar(15), contrasenya varchar(25), email varchar(25), admin int);";
//				logger.log(Level.INFO, "Statement: " + sent);
//				stmt.executeUpdate(sent);
//				
//				sent = "CREATE TABLE IF NOT EXISTS wishlist (id INTEGER PRIMARY KEY AUTOINCREMENT, idUsuario INTEGER REFERENCES usuario (id), idProducto INTEGER REFERENCES producto (codigo));";
//				logger.log(Level.INFO, "Statement: " + sent);
//				stmt.executeUpdate(sent);
//				
//				sent = "CREATE TABLE IF NOT EXISTS cestas (id INTEGER PRIMARY KEY AUTOINCREMENT, idUsuario INTEGER REFERENCES usuario (id), idProducto INTEGER REFERENCES producto (codigo), cantidad int);";
//				logger.log(Level.INFO, "Statement: " + sent);
//				stmt.executeUpdate(sent);
//				
//				sent = "CREATE TABLE IF NOT EXISTS compra (id INTEGER PRIMARY KEY AUTOINCREMENT, idUsuario INTEGER REFERENCES usuario(id), fecha bigint, precio float);";
//				logger.log(Level.INFO, "Statement: " + sent);
//				stmt.executeUpdate(sent);
//				
//				sent = "CREATE TABLE IF NOT EXISTS compraP (idCompra INTEGER REFERENCES compra(id), idProducto INTEGER REFERENCES producto (codigo));";
//				logger.log(Level.INFO, "Statement: " + sent);
//				stmt.executeUpdate(sent);
//				
//				
//				sent = "CREATE TABLE IF NOT EXISTS producto (codigo INTEGER PRIMARY KEY AUTOINCREMENT, tipo varchar(10), nombre varchar(10), id int, precio float, foto varchar(50), cantidad int);";
//				logger.log(Level.INFO, "Statement: " + sent);
//				stmt.executeUpdate(sent);
//				
//				
//				//creación de productos
//				sent = "INSERT INTO producto(tipo, nombre, id, precio, foto, cantidad) VALUES ('Bebidas', 'Agua', '1', '1.95', 'imagenes/agua.jpg', 22);";
//				stmt.executeUpdate(sent);
//				
//				sent = "INSERT INTO producto(tipo, nombre, id, precio, foto, cantidad) VALUES ('CarnePescado', 'Entrecot', '2', '8.5', 'imagenes/entrecot.jpg', 22);";
//				stmt.executeUpdate(sent);
//				
//				sent = "INSERT INTO producto(tipo, nombre, id, precio, foto, cantidad) VALUES ('ProductosSecos', 'Almendras', '3', '2.45', 'imagenes/almendras.jpg', 22);";
//				stmt.executeUpdate(sent);
//				
//				sent = "INSERT INTO producto(tipo, nombre, id, precio, foto, cantidad) VALUES ('Desayuno', 'Cereales de fibra', '4', '3.69', 'imagenes/cerealesFibra.jpg', 22);";
//				stmt.executeUpdate(sent);
//				
//				sent = "INSERT INTO producto(tipo, nombre, id, precio, foto, cantidad) VALUES ('VerduraFruta', 'Kiwi', '5', '0.97', 'imagenes/kiwi.jpg', 22);";
//				stmt.executeUpdate(sent);
//				
//				
//				//creación de usuarios base (administradores y algún comprador)
//				sent = "INSERT INTO usuario(nombre, apellidos, usuario, contrasenya, email, admin) VALUES ('Aratz', 'Bergado Fuentes', 'atxete', '1234', 'ianaratz.bergado@opendeusto.es', 1);";
//				stmt.executeUpdate(sent);
//				
//				sent = "INSERT INTO usuario(nombre, apellidos, usuario, contrasenya, email, admin) VALUES ('Xabier', 'Aguiriano Fernández', 'XabierAguiriano', '1234', 'xabier.aguiriano@opendeusto.es', 1);";
//				stmt.executeUpdate(sent);
//				
//				sent = "INSERT INTO usuario(nombre, apellidos, usuario, contrasenya, email, admin) VALUES ('Naroa', 'Manterola Nazabal', 'naroaManterola', '1234', 'n.manterola@opendeusto.es', 1);";
//				stmt.executeUpdate(sent);
//				
//				sent = "INSERT INTO usuario(nombre, apellidos, usuario, contrasenya, email, admin) VALUES ('Jon', 'Ruiz Mezo', 'jonru21', '1234', 'jon.r@opendeusto.es', 1);";
//				stmt.executeUpdate(sent);
//				
//				sent = "INSERT INTO usuario(nombre, apellidos, usuario, contrasenya, email, admin) VALUES ('Juan', 'Gallego Rica', 'juanga', '1234', 'juanga@opendeusto.es', 0);";
//				stmt.executeUpdate(sent);
//				
//				sent = "INSERT INTO usuario(nombre, apellidos, usuario, contrasenya, email, admin) VALUES ('Iker', 'Carrasco Llorente', 'ikerca', '1234', 'ikerca@opendeusto.es', 0);";
//				stmt.executeUpdate(sent);
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
					Comprador c = new Comprador(nombre, apellidos, usuario, contrasenya, email,rs.getInt("admin"));
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
					Comprador c = new Comprador(nombre, apellidos, usuario, contrasenya, email,rs.getInt("admin"));
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
			
			sent="INSERT INTO usuario (nombre, apellidos, usuario, contrasenya, email, admin) values ('"+nombre+"', '"+apellidos+"', '"+usuario+"', '"+contrasenya+"', '"+email+"', 0)";
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
					sent="SELECT idProducto FROM wishList WHERE idUsuario = "+id+"";
					break;
				case 1: //si tipo=1 -> cesta
					sent="SELECT idProducto FROM cestas WHERE idUsuario = "+id+"";
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
	
	public static List<Producto> obtenerProductosCesta(int idUsuario){
		List<Producto> productosCesta = new ArrayList<Producto>();
		
		String sql = "SELECT p.nombre, p.tipo, p.precio, p.foto, c.cantidad " +
                "FROM producto p " +
                "JOIN cestas c ON p.codigo = c.idProducto " +
                "WHERE c.idUsuario = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
	        stmt.setInt(1, idUsuario);  // Pasamos el idUsuario de la sesión
	        ResultSet rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	        	String tipo = rs.getString("tipo");
	            String nombre = rs.getString("nombre");
	            float precio = rs.getFloat("precio");
	            String foto = rs.getString("foto");
	            int cantidad = rs.getInt("cantidad");
	            Producto producto = new Producto(domain.Producto.tipo.valueOf(tipo), nombre, precio, foto, cantidad);
	            productosCesta.add(producto);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return productosCesta;
	}
	
	
	public static List<Producto> obtenerProductosWishList(int idUsuario){
		List<Producto> productosWishList = new ArrayList<Producto>();
		
		 String query = "SELECT p.nombre, p.tipo, p.foto, p.precio, p.cantidad " +
                 "FROM producto p " +
                 "JOIN wishlist w ON p.codigo = w.idProducto " +
                 "WHERE w.idUsuario = ?";
		 
		 try (PreparedStatement stmt = conexion.prepareStatement(query)) {
		        stmt.setInt(1, idUsuario);  
		        ResultSet rs = stmt.executeQuery();
		        
		        while (rs.next()) {
		        	String tipo = rs.getString("tipo");
		            String nombre = rs.getString("nombre");
		            float precio = rs.getFloat("precio");
		            String foto = rs.getString("foto");
		            int cantidad = rs.getInt("cantidad");
		            Producto producto = new Producto(domain.Producto.tipo.valueOf(tipo), nombre, precio, foto, cantidad);
		            productosWishList.add(producto);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    
		    return productosWishList ;
		 
	}

	
	public static void eliminarCestaUsuario(int idUsuario) {
		String sql ="DELETE FROM cestas WHERE id = ?;";
		try {
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, idUsuario);
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//añadir un nuevo producto a la cesta o a la wishlist dentro de la base de datos
	public static void anyadirProducto(int idUsuario, int idProducto, int tipo, int cantidad) throws SQLException{ //tipo se refiere si lo queremos guardar en la wishlist(0) o en la compra(1)
		String sent="";
		if(cantidad==0) {
			switch (tipo) {
            case 0: // Si es wishlist
                sent = "DELETE FROM wishList WHERE idUsuario = " + idUsuario + " AND idProducto = " + idProducto;
                break;
            case 1: // Si es cesta
                sent = "DELETE FROM cestas WHERE idUsuario = " + idUsuario + " AND idProducto = " + idProducto;
                break;
            default:
                throw new SQLException("Tipo no definido para eliminación");
			}
		}else {
			switch (tipo) {
            case 0: // Wishlist
                sent = "INSERT INTO wishList(idUsuario, idProducto) VALUES(" + idUsuario + ", " + idProducto + ")";
                break;
            case 1: // Cesta
                sent = "INSERT INTO cestas (idUsuario, idProducto, cantidad) VALUES (" + idUsuario + ", " + idProducto + ", " + cantidad + ")";
                break;
            default:
                throw new SQLException("Tipo no definido");
			}
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
	
	
	//añadir nueva compra a la base de datos una vez esta es confirmada por el usuario
	public static int anyadirCompra(int idUsuario, long fecha, double precio) {
		
		String sent="";
		try(Statement statement = conexion.createStatement()){
			sent="INSERT INTO compra (idUsuario, fecha, precio) VALUES("+idUsuario+", '"+fecha+"',"+(float)precio+");";
			logger.log(Level.INFO, "Lanzada la actualización a la base de datos: "+sent);
			int val=statement.executeUpdate(sent);
			logger.log(Level.INFO, "Añadida "+val+" fila a base de datos\t"+sent);
			sent="SELECT id FROM compra ORDER BY id DESC";
			ResultSet rs = statement.executeQuery(sent);
			while(rs.next()) {
				int id=rs.getInt("id");
				return id;
			}
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Error en inserción de base de datos\t"+e);
		}
		return 0;
	}
	
	
	//añadir los productos que se han adquirido en una compra
	public static void anyadirCompraP(int idCompra, int idProducto) {
		String sent = "";
		try(Statement statement = conexion.createStatement()){
			sent="INSERT INTO compraP(id, idProducto) VALUES ("+idCompra+","+idProducto+")";
			logger.log(Level.INFO, "Lanzada actualización a la base de datos:"+sent);
			int val = statement.executeUpdate(sent);
			logger.log(Level.INFO, "Añadida "+val+" fila a base de datos\t"+sent);
			
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Error en inserción de base de datos\t"+e);
		}
	}
	
	
	//obtener las compras registradas en la base de datos
	//se podran obtener compras hechas entre dos fechas (type=1)
	public static HashMap<Integer, Compra> getCompras(int type, long fecha1, long fecha2){
		HashMap<Integer, Compra> mapaCompras = new HashMap<>();
		ArrayList<Producto> ps = new ArrayList<Producto>();
		String sent="";
		try(Statement statement = conexion.createStatement()){
			if(type==1) sent = "SELECT * FROM compra WHERE fecha BETWEEN " + fecha1 + " AND " + fecha2 ;
			else sent = "SELECT * FROM compra";
			logger.log(Level.INFO, "Statement: " + sent);
			ResultSet rs = statement.executeQuery(sent);
			while(rs.next()) {
				int id = rs.getInt("id");
				int idUsuario = rs.getInt("idUsuario");
				long fecha = rs.getLong("fecha");
				float precio = rs.getFloat("precio");
				sent = "SELECT idProducto FROM compraP WHERE id = " + id;
				logger.log(Level.INFO, "Statement: "+sent);
				ResultSet rs2 = statement.executeQuery(sent);
				while(rs2.next()) {
					int idProducto=rs2.getInt("idUsuario");
					for(Producto p : Logica.getListaProductos()) {
						if(idProducto==p.getCodigo()) {
							ps.add(p);
							break;
						}
					}
					
				}
				Compra c = new Compra(id, getUsuarioId(idUsuario), ps, fecha, (double) precio);
				mapaCompras.put(id, c);
			} 
			return mapaCompras;
			
			}catch(Exception e) {
				logger.log(Level.SEVERE, "Excepción", e);
				return null;
		}
		
	}
	
	public static ArrayList<Producto> getProductos(){
		ArrayList<Producto> productos = new ArrayList<>();
		String sent = "";
		
		try(Statement statement = conexion.createStatement()){
			sent = "SELECT * FROM producto";
			logger.log(Level.INFO, "Statement: " + sent);
			ResultSet rs = statement.executeQuery(sent);
			
			while(rs.next()) {
				Producto p = new Producto();
				p.setTipo(tipo.valueOf(rs.getString("tipo")));
				p.setNombre(rs.getString("nombre"));
				p.setPrecio(rs.getFloat("precio"));
				p.setFoto(rs.getString(/*"images/" + */ "foto"));
				//p.setCantidad(rs.getInt("cantidad"));
				p.setCodigo(rs.getInt("codigo"));
				productos.add(p);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.log(Level.SEVERE, "Error al obtener productos de la base de datos", e);
		}
		
		return productos;
	}
	
	public static void anyadirProductoaPagina(Producto p) {
		String sent = "";
		try(Statement statement = conexion.createStatement()) {
			
			sent = "INSERT INTO producto(tipo, nombre, id, precio, foto, cantidad) VALUES (" + p.getTipo().name() + ", " + p.getNombre() + ", " + p.getCodigo() + ", " + p.getPrecio() + ", " + p.getFoto() + ", " + p.getCantidad() + ");";
			System.out.println(p.getFoto());
			logger.log(Level.INFO, "Lanzada actualización a la base de datos:"+sent);
			int val = statement.executeUpdate(sent);
			logger.log(Level.INFO, "Añadida "+val+" fila a base de datos\t"+sent);
			
		}catch(SQLException e) {
			logger.log(Level.SEVERE, "Error en inserción de base de datos\t"+e);
		}
	}
	 
	
	
	
	public static Producto obtenerProducto(String nombre, ArrayList<Producto> listaProductos) {
		for(Producto p : listaProductos) {
			if(p.getNombre().equals(nombre)) {
				return p;
			}
		}
		return null;
	}
	
	public static int obtenerCantidadProducto(int idUsuario, int idProducto) throws SQLException {
	    String sent = "SELECT cantidad FROM cestas WHERE idUsuario = ? AND idProducto = ?";
	    int cantidad = 0;

	    try (PreparedStatement preparedStatement = conexion.prepareStatement(sent)) {
	        // Establecer los parámetros de la consulta
	        preparedStatement.setInt(1, idUsuario);
	        preparedStatement.setInt(2, idProducto);

	        // Ejecutar la consulta
	        ResultSet resultSet = preparedStatement.executeQuery();

	        // Si se encuentra el producto en la cesta, obtenemos la cantidad
	        if (resultSet.next()) {
	            cantidad = resultSet.getInt("cantidad");
	        }
	    } catch (SQLException e) {
	        logger.log(Level.SEVERE, "Error al obtener la cantidad del producto en la cesta\t" + e);
	    }

	    return cantidad;
	}
	
	public static void cambiarNombreProd(Producto p, String nombreNuevo) {
		String sql = "UPDATE producto SET nombre = ? WHERE codigo = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, nombreNuevo);
			ps.setInt(2, p.getCodigo());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void cambiarPrecioProd(Producto p, double precioNuevo) {
		String sql = "UPDATE producto SET precio = ? WHERE codigo = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setDouble(1, precioNuevo);
			ps.setInt(2, p.getCodigo());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
	}
	
	public static void cambiarFotoProd(Producto p, String fotoNueva) {
		String sql = "UPDATE producto SET foto = ? WHERE codigo = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, fotoNueva);
			ps.setInt(2, p.getCodigo());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static boolean esFavorito(Producto p, int idUsuario) {
		boolean esFavorito=false;
		String sql = "SELECT * FROM wishlist WHERE idUsuario = ? AND idProducto = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, idUsuario);
			ps.setInt(2, p.getCodigo());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				esFavorito =true;
			}else {
				esFavorito =false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return esFavorito;
		
	}
	
}
