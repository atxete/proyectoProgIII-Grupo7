package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;






public class MensajeAutomatico {
	
	public  MensajeAutomatico(String mensaje, String titulo) {
		JOptionPane optionPane = new JOptionPane(mensaje, JOptionPane.INFORMATION_MESSAGE);
		
		JDialog dialogo = optionPane.createDialog(titulo);
		
		Timer temporizador = new Timer(5000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dialogo.dispose();
				
			}
			 
		});
		
		temporizador.setRepeats(false);
		
		temporizador.start();
		
		dialogo.setContentPane(optionPane);
		
		dialogo.addWindowListener(new WindowAdapter() {

			

			@Override
			public void windowClosing(WindowEvent e) {
				temporizador.stop();
				
			}

						
			
		});
		
		dialogo.setVisible(true);
	}
	
}
