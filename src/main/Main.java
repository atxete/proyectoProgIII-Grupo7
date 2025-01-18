package main;

import domain.BaseDatos1;
import gui.VentanaInicial;

public class Main {

	public static void main(String[] args) {
		
		//BaseDeDatos.abrirConexion("db/BaseDatos.db", true);
		BaseDatos1.abrirConexion("BaseDatos.db", true);
		
		new VentanaInicial();
		
	}

}
  