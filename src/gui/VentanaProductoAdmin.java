package gui;

import java.awt.*;

import javax.swing.*;

public class VentanaProductoAdmin extends JFrame{
	
	JPanel pnlBotones;
	JPanel pnlInfoProd;
	JPanel pnlFoto;
	JPanel pnlCentro;
	JButton btnVolver;
	JButton btnModNombre;
	JButton btnModPrecio;
	JButton btnModFoto;
	JLabel lblNombreProd;
	JLabel lblPrecioProd;
	ImageIcon imagenProducto;
	JLabel lblFoto;
	
	
	public VentanaProductoAdmin(/*Producto p*/) {
		setTitle("Ventana Producto Administrador");
		setBounds(300, 100, 700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		/**SE PUEDE AÑADIR EN EL PANEL NORTE DE LA VENTANA EL LOGO DEL SUPERMERCADO**/
		
		pnlBotones = new JPanel(new GridLayout(1, 4));
		pnlInfoProd = new JPanel(new GridLayout(1,2));
		pnlFoto = new JPanel();
		pnlCentro = new JPanel(new GridLayout(2,1));
		btnVolver = new JButton("VOLVER");
		btnModNombre = new JButton("Cambiar Nombre");
		btnModPrecio = new JButton("Cambiar Precio");
		btnModFoto = new JButton("Cambiar Foto");
		lblNombreProd = new JLabel("Nombre Producto"  /*p.getNombre()*/);
		lblPrecioProd = new JLabel("Precio Producto"  /*p.getPrecio()*/);
		
		imagenProducto = new ImageIcon("imagenes/Logo.jpg");
		Image imagenRedimensionada = imagenProducto.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		ImageIcon imagenRedimensionadaIcono = new ImageIcon(imagenRedimensionada);
		lblFoto = new JLabel(imagenRedimensionadaIcono);
		
		pnlInfoProd.add(lblNombreProd);
		pnlInfoProd.add(lblPrecioProd);
		pnlFoto.add(lblFoto);
		pnlCentro.add(pnlFoto);
		pnlCentro.add(pnlInfoProd);
		getContentPane().add(pnlCentro, BorderLayout.CENTER);
		
		pnlBotones.add(btnVolver);
		pnlBotones.add(btnModNombre);
		pnlBotones.add(btnModPrecio);
		pnlBotones.add(btnModFoto);
		getContentPane().add(pnlBotones, BorderLayout.SOUTH);
		
		getContentPane().setBackground(Color.WHITE);
		pnlCentro.setBackground(Color.WHITE);
		pnlInfoProd.setBackground(Color.WHITE);
		pnlFoto.setBackground(Color.WHITE);
		
		
		
		
		btnVolver.addActionListener((e)->{
			//new VentanaPrincipalAdmin();
			dispose();
		});
		
		btnModNombre.addActionListener((e)->{
			String nuevoNombre = JOptionPane.showInputDialog("Introduce el nuevo nombre para este producto");
			
			if(nuevoNombre!=null && !nuevoNombre.trim().isEmpty()) {
				/*p.setNombre(nuevoNombre);*/
				JOptionPane.showMessageDialog(null, "El nombre del producto se ha cambiado a: "+nuevoNombre);
			}else {
		        JOptionPane.showMessageDialog(null, "No se realizó ningún cambio en el nombre del producto.", 
		                                      "Cambio Cancelado", JOptionPane.WARNING_MESSAGE);
		    }
		});
		
		btnModPrecio.addActionListener((e)->{
			
			 String nuevoPrecioStr = JOptionPane.showInputDialog("Introduce el nuevo precio para este producto");

			
			if (nuevoPrecioStr != null && !nuevoPrecioStr.trim().isEmpty()) { 
		        try {
		            Float nuevoPrecio = Float.parseFloat(nuevoPrecioStr);

		            
		            // p.setPrecio(nuevoPrecio);

		            
		            JOptionPane.showMessageDialog(null, "El precio del producto se ha cambiado a: " + nuevoPrecio);
		        } catch (NumberFormatException ex) {
		            
		            JOptionPane.showMessageDialog(null, "Por favor, introduce un valor numérico válido para el precio.", 
		                                          "Error de entrada", JOptionPane.ERROR_MESSAGE);
		        }
		    } else {
		        
		        JOptionPane.showMessageDialog(null, "No se realizó ningún cambio en el precio del producto.", 
		                                      "Cambio Cancelado", JOptionPane.WARNING_MESSAGE);
		    }
		});
		
		btnModFoto.addActionListener((e)->{
			String nuevaFoto = JOptionPane.showInputDialog("Introduce el source de la nueva imagen para este producto");
			
			
			if (nuevaFoto != null && !nuevaFoto.trim().isEmpty()) { 
		        ImageIcon nuevaImagenIcon = new ImageIcon(nuevaFoto);

		        
		        if (nuevaImagenIcon.getIconWidth() > 0) {
		            
		            // p.setFoto(nuevaFoto);
		            
		            
		            JOptionPane.showMessageDialog(null, "La imagen del producto se ha actualizado.");
		        } else {
		            
		            JOptionPane.showMessageDialog(null, "La ruta proporcionada no es válida o no corresponde a una imagen.", 
		                                          "Error en la imagen", JOptionPane.ERROR_MESSAGE);
		        }
		    } else {
		        
		        JOptionPane.showMessageDialog(null, "No se realizó ningún cambio en la imagen del producto.", 
		                                      "Cambio Cancelado", JOptionPane.WARNING_MESSAGE);
		    }
			
		});
		
		
		
		setVisible(true);
		
	}
	public static void main(String[] args) {
		new VentanaProductoAdmin();
	}
}
