package main;

import db.BaseDatos1;
import domain.Logica;
import gui.VentanaInicial;

public class Main {

	public static void main(String[] args) {
		
		//BaseDeDatos.abrirConexion("db/BaseDatos.db", true);
		BaseDatos1.abrirConexion("BaseDatos.db", true);
		
		Logica.cargarProductos("ProductosFinales.dat");
		
		new VentanaInicial();
		
	}

}
   