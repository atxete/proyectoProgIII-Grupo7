package gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.*;

public class VentanaLoadingUsuario extends JFrame{
	
	private JPanel pnlTitulo, pnlCentro;
	private JLabel lblTitulo;
	private JProgressBar progressBar;
	
	public VentanaLoadingUsuario(JFrame va) {
		super();
		setBounds(va.getX()+10, va.getY()+10, 200, 100);
		setResizable(false);
		setLocationRelativeTo(null);
		
		pnlTitulo = new JPanel();
		pnlCentro = new JPanel();
		getContentPane().add(pnlTitulo, BorderLayout.NORTH);
		getContentPane().add(pnlCentro, BorderLayout.CENTER);
		
		lblTitulo = new JLabel("LOADING...");
		lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		pnlTitulo.add(lblTitulo);
		
		JFrame v = this;
		
		progressBar = new JProgressBar(0,100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		pnlCentro.add(progressBar);
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<100;i++) {
					progressBar.setValue(progressBar.getValue()+1);
					try {
						Thread.sleep((int) (Math.random()*50)+50);
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
				v.dispose();
				VentanaPrincipalUsuario ventana = new VentanaPrincipalUsuario();
			}
		};
		
		Thread t = new Thread(r);
		t.start();
		
	}
	
	
}
