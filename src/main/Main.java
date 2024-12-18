package main;

import domain.BaseDeDatos;
import gui.VentanaInicial;

public class Main {

	public static void main(String[] args) {
		
		BaseDeDatos.abrirConexion("db/BaseDatos.db", true);
		
		new VentanaInicial();
		
	}

}
  