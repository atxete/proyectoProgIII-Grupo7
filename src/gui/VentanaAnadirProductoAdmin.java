package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Logica;
import domain.Producto;
import domain.Producto.tipo;

public class VentanaAnadirProductoAdmin  extends JFrame{

	private JLabel lblTipoProductos;
	private JComboBox<Producto.tipo> tipoProductos;
	private JPanel pnlProductos;
	private JLabel lblNombre;
	private JLabel lblPrecio;
	private JLabel lblFoto;
	private JTextField tfNombre;
	private JTextField tfPrecio;
	private JButton btnFoto;
	private JPanel pnlTexto;
	private JPanel pnlTextoTamanyo;
	private JButton btnCancelar;
	private JButton btnGuardar;
	private JPanel pnlBotones;
	private JFileChooser fileChooser;
	private String nuevaFoto;
	private ImageIcon nuevaImagenIcon;
	private VentanaPrincipalAdmin ventanaPrincipalAdmin;
	private boolean fotoSeleccionada = false;
	
	
	private static Logger logger = Logger.getLogger("AnyadirProducto");
	
	
	public VentanaAnadirProductoAdmin(VentanaPrincipalAdmin ventanaPrincipalAdmin) {
		this.ventanaPrincipalAdmin = ventanaPrincipalAdmin;
		
		setTitle("Añadir producto administrador");
		setSize(600,300);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		lblTipoProductos = new JLabel("Selecciona el tipo de producto a añadir:");
		tipoProductos = new JComboBox<>(Producto.tipo.values());
		tipoProductos.setPreferredSize(new Dimension(200,25));
		tipoProductos.setBackground(Color.WHITE);
		pnlProductos = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlProductos.add(lblTipoProductos);
		pnlProductos.add(tipoProductos);
		
		lblNombre = new JLabel("Nombre:");
		tfNombre = new JTextField();
		tfNombre.setPreferredSize(new Dimension(150,25));
		lblPrecio = new JLabel("Precio:");
		tfPrecio = new JTextField();
		tfPrecio.setPreferredSize(new Dimension(150,25));
		lblFoto = new JLabel("Foto:");
		btnFoto = new JButton("Seleccionar foto");
		btnFoto.setPreferredSize(new Dimension(150,25));
		
		pnlTexto = new JPanel(new GridLayout(3,2,10,20));
		pnlTexto.add(lblNombre);
		pnlTexto.add(tfNombre);
		{		pnlTexto.add(lblPrecio);
		pnlTexto.add(tfPrecio);
		pnlTexto.add(lblFoto);
		pnlTexto.add(btnFoto);
		
		pnlTextoTamanyo = new JPanel(new FlowLayout(FlowLayout.CENTER,30,30));
		pnlTextoTamanyo.add(pnlTexto);
		
		btnCancelar = new JButton("Cancelar");
		btnGuardar = new JButton("Guardar");
		pnlBotones = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
		pnlBotones.add(btnCancelar);
		pnlBotones.add(btnGuardar);
		
		getContentPane().add(pnlProductos, BorderLayout.NORTH);
		getContentPane().add(pnlTextoTamanyo, BorderLayout.CENTER);
		getContentPane().add(pnlBotones, BorderLayout.SOUTH);
		
		//IAG (herramienta: chatGPT)
		//ADAPTADO (sabíamos como crear el file chooser y añadirlo al boton. 
		//Sin embargo, no sabíamos como configurarlo para que el usuario pueda elegir la imagen que desee de su archivo.
		//Tampoco sabíamos como hacer para que solo se puedan elegir los tipos de archivo especificados en la línea 107).
		btnFoto.addActionListener((e)->{
			
		    fileChooser = new JFileChooser();

		    File directorioProyecto = new File(System.getProperty("user.dir"));
		    fileChooser.setCurrentDirectory(directorioProyecto);

		    fileChooser.setDialogTitle("Selecciona una imagen");

		    fileChooser.setAcceptAllFileFilterUsed(false);
		    fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos de imagen", "jpg", "png", "jpeg", "gif"));

		    int resultado = fileChooser.showOpenDialog(null);

		    if (resultado == JFileChooser.APPROVE_OPTION) {
		        
		        nuevaFoto = fileChooser.getSelectedFile().getAbsolutePath();

		        nuevaImagenIcon = new ImageIcon(nuevaFoto);

		       
		        if (nuevaImagenIcon.getIconWidth() > 0) {
		           fotoSeleccionada=true;
		            // p.setFoto(nuevaFoto);

		            JOptionPane.showMessageDialog(null, "La imagen del producto se ha actualizado.");
		        } else {
		        	fotoSeleccionada=false;
		            JOptionPane.showMessageDialog(null, "El archivo seleccionado no es una imagen válida.", 
		                                          "Error en la imagen", JOptionPane.ERROR_MESSAGE);
		        }
		    } else {
		    	fotoSeleccionada=false;
		        JOptionPane.showMessageDialog(null, "No se realizó ningún cambio en la imagen del producto.", 
		                                      "Cambio Cancelado", JOptionPane.WARNING_MESSAGE);
		    }
			
		});
		;
		
		btnGuardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			/*	Producto producto = new Producto((tipo) tipoProductos.getSelectedItem(), tfNombre.getText(), Float.valueOf(tfPrecio.getText()), lblFoto.getText());
				ventanaPrincipalAdmin.getProductos().add(producto);
				JOptionPane.showMessageDialog(null, "El producto se ha añadido correctamente al supermercado.", "Producto añadido correctamente", JOptionPane.INFORMATION_MESSAGE);
				dispose();
				ventanaPrincipalAdmin.setVisible(true);*/
				
				Producto p;
				if(tipoProductos.getSelectedItem()!=null && !tfNombre.getText().equals("") && !tfPrecio.getText().equals("") && fotoSeleccionada) {
					if(esNumero(tfPrecio.getText())) {
						Producto.tipo tipo;
						tipo = (domain.Producto.tipo) tipoProductos.getSelectedItem();
						
						p = new Producto((tipo)tipoProductos.getSelectedItem(), tfNombre.getText(), Float.parseFloat(tfPrecio.getText()), nuevaFoto);
						
						//INSERT
						Logica.listaProductos.add(p);
						logger.log(Level.INFO, p.getNombre()+" añadido correctamente");
						Logica.guardarProductos("ProductosFinales.dat");
						dispose();
					}else {
						MensajeAutomatico ma = new MensajeAutomatico("El precio debe ser un número entero o un decimal (con punto)","Error");
						
					}
				}else {
					MensajeAutomatico ma2 = new MensajeAutomatico("Todos los campos son obligatorios", "Error");
				}
			}		 
		});
		
		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				//en ningún momento se cierra la ventana principal admin, se queda atrás abierta;
				//asi que con que se cierre la de añadir producto, se ve la otra.
				//ventanaPrincipalAdmin.setVisible(true);
			}
			
		});
		
		setVisible(true);
	}
	}
	
	/* 
	public static void main(String[] args) {
		VentanaPrincipalAdmin ventanaPrincipalAdmin = new VentanaPrincipalAdmin();
		ventanaPrincipalAdmin.setVisible(false);
		new VentanaAnadirProductoAdmin(ventanaPrincipalAdmin);
	}
	*/
	public boolean esNumero(String s) {
		try {
			Float.parseFloat(s);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
}
	
	
