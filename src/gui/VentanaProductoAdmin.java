package gui;

import java.awt.*;
import java.io.File;

import javax.swing.*;

import domain.Logica;
import domain.Producto;
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
	
	
	public VentanaProductoAdmin(Producto p, VentanaPrincipalAdmin ventAnt) {
		JFrame ventanaAnterior = ventAnt;
		setTitle("Ventana Producto Administrador");
		setBounds(300, 100, 700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		/**SE PUEDE AÑADIR EN EL PANEL NORTE DE LA VENTANA EL LOGO DEL SUPERMERCADO**/
		
		
		
		pnlBotones = new JPanel(new GridLayout(1, 4));
		pnlInfoProd = new JPanel(new GridLayout(1,2));
		pnlFoto = new JPanel();
		pnlCentro = new JPanel(new GridLayout(2,1));
		//btnVolver = new JButton("VOLVER");
		
		 
        ImageIcon iconoOriginalFlecha = new ImageIcon("imagenes/flechaVolver.png");
        if (iconoOriginalFlecha.getIconWidth() != -1) {
            Image imagenRedimensionadaFlecha = iconoOriginalFlecha.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            ImageIcon iconoRedimensionadoFlecha = new ImageIcon(imagenRedimensionadaFlecha);

            btnVolver = new JButton(iconoRedimensionadoFlecha);  // Usar la imagen redimensionada en el botón
        } else {
            btnVolver = new JButton("VOLVER");  // En caso de error en la carga de la imagen
        }
		
		btnModNombre = new JButton("Cambiar Nombre");
		btnModPrecio = new JButton("Cambiar Precio");
		btnModFoto = new JButton("Cambiar Foto");
		lblNombreProd = new JLabel("Nombre Producto: " + p.getNombre());
		lblPrecioProd = new JLabel("Precio Producto: "  +p.getPrecio());
		
		imagenProducto = new ImageIcon(p.getFoto());
		Image imagenRedimensionada = imagenProducto.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
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
			ventanaAnterior.setVisible(true);
			dispose();
		});
		
		btnModNombre.addActionListener((e)->{
			String nuevoNombre = JOptionPane.showInputDialog("Introduce el nuevo nombre para este producto");
			
			if(nuevoNombre!=null && !nuevoNombre.trim().isEmpty()) {
				p.setNombre(nuevoNombre);
				JOptionPane.showMessageDialog(null, "El nombre del producto se ha cambiado a: "+nuevoNombre);
				Logica.guardarProductos("ProductosFinales.dat");
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

		            
		            p.setPrecio(nuevoPrecio);

		            
		            JOptionPane.showMessageDialog(null, "El precio del producto se ha cambiado a: " + nuevoPrecio);
		            
		            Logica.guardarProductos("ProductosFinales.dat");
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
			
			//IAG: (herramienta: chatGPT)
			//ADAPTADO (sabíamos como crear el file chooser y añadirlo al boton. 
			//Sin embargo, no sabíamos como configurarlo para que el usuario pueda elegir la imagen que desee de su archivo.
			//Tampoco sabíamos como hacer para que solo se puedan elegir los tipos de archivo especificados en la línea 142).
			
		    JFileChooser fileChooser = new JFileChooser();

		    File directorioProyecto = new File(System.getProperty("user.dir"));
		    fileChooser.setCurrentDirectory(directorioProyecto);

		    fileChooser.setDialogTitle("Selecciona una imagen");

		    fileChooser.setAcceptAllFileFilterUsed(false);
		    fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos de imagen", "jpg", "png", "jpeg", "gif"));

		    int resultado = fileChooser.showOpenDialog(null);

		    if (resultado == JFileChooser.APPROVE_OPTION) {
		        
		        String nuevaFoto = fileChooser.getSelectedFile().getAbsolutePath();

		        ImageIcon nuevaImagenIcon = new ImageIcon(nuevaFoto);

		       
		        if (nuevaImagenIcon.getIconWidth() > 0) {
		           
		             p.setFoto(nuevaFoto);

		            JOptionPane.showMessageDialog(null, "La imagen del producto se ha actualizado a: "+p.getFoto());
		            Logica.guardarProductos("ProductosFinales.dat");
		        } else {
		            JOptionPane.showMessageDialog(null, "El archivo seleccionado no es una imagen válida.", 
		                                          "Error en la imagen", JOptionPane.ERROR_MESSAGE);
		        }
		    } else {
		        JOptionPane.showMessageDialog(null, "No se realizó ningún cambio en la imagen del producto.", 
		                                      "Cambio Cancelado", JOptionPane.WARNING_MESSAGE);
		    }
			
		});
		
		
		
		setVisible(true);
		
	}
	/*public static void main(String[] args) {
		new VentanaProductoAdmin();
	}*/
}
