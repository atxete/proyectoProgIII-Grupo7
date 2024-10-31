package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Producto;

public class VentanaAnadirProductoAdmin  extends JFrame{

	private JLabel lblTipoProductos;
	private JComboBox<Producto.tipo> tipoProductos;
	private JPanel pnlProductos;
	private JLabel lblNombre;
	private JLabel lblPrecio;
	private JLabel lblNombre_foto;
	private JTextField tfNombre;
	private JTextField tfPrecio;
	private JTextField tfNombre_foto;
	private JPanel pnlTexto;
	private JPanel pnlTextoTamanyo;
	private JButton btnCancelar;
	private JButton btnGuardar;
	private JPanel pnlBotones;
	private VentanaPrincipalAdmin ventanaPrincipalAdmin;
	
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
		lblNombre_foto = new JLabel("Nombre foto:");
		tfNombre_foto = new JTextField();
		tfNombre_foto.setPreferredSize(new Dimension(150,25));
		
		pnlTexto = new JPanel(new GridLayout(3,2,10,20));
		pnlTexto.add(lblNombre);
		pnlTexto.add(tfNombre);
		pnlTexto.add(lblPrecio);
		pnlTexto.add(tfPrecio);
		pnlTexto.add(lblNombre_foto);
		pnlTexto.add(tfNombre_foto);
		
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
		
		
		btnGuardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Producto producto = new Producto(tfNombre.getText(), "", Float.valueOf(tfPrecio.getText()), tfNombre_foto.getText()); 
				ventanaPrincipalAdmin.getProductos().add(producto);
				JOptionPane.showMessageDialog(null, "El producto se ha añadido correctamente al supermercado.", "Producto añadido correctamente", JOptionPane.INFORMATION_MESSAGE);
				dispose();
				ventanaPrincipalAdmin.setVisible(true);
			}
			
		});
		
		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				ventanaPrincipalAdmin.setVisible(true);
			}
			
		});
		
		setVisible(true);
	}

	public static void main(String[] args) {
		VentanaPrincipalAdmin ventanaPrincipalAdmin = new VentanaPrincipalAdmin();
		ventanaPrincipalAdmin.setVisible(false);
		new VentanaAnadirProductoAdmin(ventanaPrincipalAdmin);
	}
}
