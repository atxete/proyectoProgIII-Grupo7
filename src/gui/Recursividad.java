package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.sqlite.jdbc4.JDBC4PreparedStatement;

import domain.BaseDatos1;
import domain.GestorUsuarios;
import domain.Producto;

public class Recursividad extends JFrame{
	
	protected ArrayList<Producto> lTodosLosProducto;
	private JTextField txtPresupuesto;
	private JButton btnCalcular;
	private JTable tablaResultados;
	private DefaultTableModel modeloTabla;
	
	
	public Recursividad() {
		
		
		setTitle("Calculadora de combinaciones");
		setBounds(300, 100, 700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panelSuperior = new JPanel(new FlowLayout());
		JLabel lblPresupuesto = new JLabel("Presupuesto (€): ");
		txtPresupuesto = new JTextField(10);
		btnCalcular = new JButton("Calcular");
		 
		panelSuperior.add(lblPresupuesto);
		panelSuperior.add(txtPresupuesto);
		panelSuperior.add(btnCalcular);
		add(panelSuperior, BorderLayout.NORTH);
		
		//Tabla con las combinaciones
		String[] columnNames = {"Combinación", "Total (€)"};
		modeloTabla = new DefaultTableModel(columnNames, 0);
		tablaResultados = new JTable(modeloTabla);
		JScrollPane scroll = new JScrollPane(tablaResultados);
		add(scroll, BorderLayout.CENTER);
		
		btnCalcular.addActionListener((e)->{
			calcularCombinaciones();
		});
		
		setVisible(true);
		
	}
	
	private void calcularCombinaciones() {
		//vaciar tabla
		modeloTabla.setRowCount(0);
		
		String input = txtPresupuesto.getText();
        double presupuesto;
        try {
            presupuesto = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
		
        lTodosLosProducto = BaseDatos1.getProductos();
        List<List<Producto>> resultado = new ArrayList<List<Producto>>();
        calcularCombinacionesR(resultado, new ArrayList<Producto>(), presupuesto, 0, lTodosLosProducto);
        
        //Ordenar las combinaciones por coste ascendente
        resultado.sort((combinacion1, combinacion2)->{
        	double total1 = combinacion1.stream().mapToDouble(Producto::getPrecio).sum();
        	double total2 = combinacion2.stream().mapToDouble(Producto::getPrecio).sum();
        	return Double.compare(total1, total2);
        });
        
        //formatear para que solo tenga 3 decimales
        DecimalFormat df = new DecimalFormat("#.###");
        
        for (List<Producto> combinacion : resultado) {
            StringBuilder nombresProductos = new StringBuilder();
            double total = 0;
            for (Producto p : combinacion) {
                nombresProductos.append(p.getNombre()).append(", ");
                total += p.getPrecio();
            }
            if (nombresProductos.length() > 0) {
                nombresProductos.setLength(nombresProductos.length() - 2); // Eliminar la última coma y espacio
            }
            modeloTabla.addRow(new Object[]{nombresProductos.toString(), df.format(total)});
        }
        
        if (resultado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron combinaciones dentro del presupuesto.", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
        
        
	}
	
	private static void calcularCombinacionesR(List<List<Producto>> resultado, List<Producto> temp, double dinero, double cont, List<Producto> lProductos) {
		if(cont>dinero+5) {
			return;
		}else if(cont >= dinero-1.5 && cont <= dinero+1.5) {
			ArrayList<Producto> copia = new ArrayList<Producto>(temp);
			Comparator<Producto> c = new Comparator<Producto>() {
				
				@Override
				public int compare(Producto o1, Producto o2) {
					return Integer.compare(o1.getCodigo(), o2.getCodigo());
				}
			};
			copia.sort(c);
			if(!resultado.contains(copia)) {
				resultado.add(new ArrayList<Producto>(temp));
			}
			
		}else {
			for(Producto p:lProductos) {
				temp.add(p);
				calcularCombinacionesR(resultado, temp, dinero, cont+p.getPrecio(), lProductos);
				temp.remove(temp.size()-1);
			}
		}
	}
	public static void main(String[] args) {
		Recursividad r= new Recursividad();
	}
	
}
