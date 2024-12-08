package main;

import domain.BaseDeDatos;
import gui.VentanaInicial;

public class Main {

	public static void main(String[] args) {
		
		BaseDeDatos.abrirConexion("src/BaseDatos.db", true);
		
		new VentanaInicial();
	}

}
  