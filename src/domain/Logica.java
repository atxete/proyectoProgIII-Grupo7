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

public class Logica implements Serializable {
    
    public static ArrayList<Producto> listaProductos = new ArrayList<>();
    private static Logger logger = Logger.getLogger("Logica");
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
        return "Logica [ usuario=" + usuario + "]";
    }
    
    public static boolean existeUsuario(String email) {
        if (BaseDatos1.getUsuarios().containsKey(email)) {
            return true;
        } else {
            return false;
        }
    }

    public static Usuario usuarioCorrecto(String email, String contrasenya) {
        if (BaseDatos1.getUsuarios().get(email).getContrasenya().equals(contrasenya)) {
            Logica.usuario = BaseDatos1.getUsuarios().get(email);
            logger.log(Level.INFO, "Existe usuario en la BD");
            return BaseDatos1.getUsuarios().get(email);
        } else {
            return null;
        }
    }

    public static boolean UsuarioComprador(String email) {
        if (BaseDatos1.getUsuarios().get(email) instanceof Comprador) {
            return true;
        } else {
            return false;
        }
    }

    public static void crearUsuario(String nombre, String apellidos, String usuario, String email, String contrasenya) {
        // Verificar si el usuario ya existe por email
        if (existeUsuario(email)) {
            logger.log(Level.WARNING, "El usuario con el email " + email + " ya existe.");
            return;
        }
        
        Comprador c1 = new Comprador(nombre, apellidos, usuario, contrasenya, email, 0);
        BaseDatos1.getUsuarios().put(c1.getEmail(), c1);
    }

    public static void guardarProductos(String fichero) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero));
            oos.writeObject(listaProductos);
            logger.log(Level.INFO, "Productos guardados correctamente en " + fichero);
            oos.close();
        } catch (IOException e) {
            logger.log(Level.INFO, "ERROR AL GUARDAR LOS PRODUCTOS EN " + fichero);
        }
    }

    public static void cargarProductos(String fichero) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero));
            @SuppressWarnings("unchecked")
            ArrayList<Producto> cargado = (ArrayList<Producto>) ois.readObject();
            listaProductos = cargado;
            ois.close();
            logger.log(Level.INFO, "Productos cargados correctamente desde " + fichero);
        } catch (Exception e) {
            logger.log(Level.INFO, "ERROR EN LA CARGA DE FICHERO: " + fichero);
        }
    }
    
    public static boolean existeProducto(Producto producto) {
        for (Producto p : listaProductos) {
            if (p.getCodigo() == producto.getCodigo()) {
                return true; 
            }
        }
        return false; 
    }

    public static void anyadirProducto(Producto producto) {
        if (existeProducto(producto)) {
            logger.log(Level.WARNING, "El producto con el ID " + producto.getCodigo() + " ya existe en la lista.");
        } else {
            listaProductos.add(producto);
            logger.log(Level.INFO, "Producto añadido correctamente.");
        }
    }
}
