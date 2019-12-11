package formulario_comedor;

import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Alimento extends JFrame {
	JLabel titulo, lNombre, lLista, lTipo, lInfoNutricional, lCalorias, lCarbohidratos, lProteinas, lGrasas, lPorciones;
	JTextField txtNombre, txtPorciones, txtCalorias, txtCarbohidratos, txtProteinas, txtGrasas;
	JButton btnAgregar, btnActualizar, btnQuitar, btnConsultar;
	JTable tablaIngredientes;
	JComboBox<String> comboTipo;
	
	public Alimento(int w, int h) {
		super("Alimentos");
		setSize(w, h);
		int x = w/100, y = h/100;
		setLayout(null);
		setLocationRelativeTo(null);
		titulo = new JLabel("Formulario de alimentos");
		Font fBold = new Font("Arial", Font.BOLD, 15);
		titulo.setFont(fBold);
		titulo.setHorizontalAlignment(JLabel.CENTER);
		add(titulo);
		titulo.setBounds(0, 0, w, 50);
		
		Font fPlain = new Font("Arial", Font.PLAIN, 15);
		lNombre = new JLabel("Nombre");
		lNombre.setFont(fBold);
		lNombre.setBounds(x*5, y*10, x*10, y*10);
		add(lNombre);
		txtNombre = new JTextField();
		txtNombre.setBounds(x*15, y*10, x*50, y*10);
		add(txtNombre);

		lPorciones = new JLabel("Porciones");
		lPorciones.setFont(fBold);
		lPorciones.setBounds(x*70, y*10, x*10, y*10);
		add(lPorciones);
		txtPorciones = new JTextField();
		txtPorciones.setBounds(x*80, y*10, x*10, y*10);
		add(txtPorciones);
		
		lLista = new JLabel("Ingredientes");
		lLista.setFont(fBold);
		lLista.setBounds(x*5, y*25, x*10, y*10);
		add(lLista);
		String[] encabezado = new String[] {"Ingrediente", "Cantidad por porción"};
		DefaultTableModel modelotabla = new DefaultTableModel(encabezado, 0);
		tablaIngredientes  = new JTable()
		{
			private static final long serialVersionUID = 1L;
			boolean editable[] = new boolean[] {false,false};
			public boolean isCellEditable(int rowIndex, int columnIndex)
			{
				return editable[columnIndex];
			}
		};
		tablaIngredientes.setModel(modelotabla);
		JScrollPane sp = new JScrollPane(tablaIngredientes);
		sp.setBounds(x*15, y*25, x*50, y*20);
		modelotabla.addRow(new String[] {"si", "no"});
		modelotabla.addRow(new String[] {"fuck", "24"});
		modelotabla.addRow(new String[] {"lal", "55555"});
		add(sp);

		lInfoNutricional = new JLabel("Información nutrimental");
		lInfoNutricional.setFont(fBold);
		lInfoNutricional.setBounds(x*5, y*45, x*60, y*10);
		lInfoNutricional.setHorizontalAlignment(JLabel.CENTER);
		add(lInfoNutricional);
		lCalorias = new JLabel("Calorias");
		lCalorias.setFont(fBold);
		lCalorias.setBounds(x*5, y*50, x*15, y*10);
		lCalorias.setHorizontalAlignment(JLabel.CENTER);
		add(lCalorias);
		lCarbohidratos = new JLabel("Carbohidratos");
		lCarbohidratos.setFont(fBold);
		lCarbohidratos.setBounds(x*20, y*50, x*15, y*10);
		lCarbohidratos.setHorizontalAlignment(JLabel.CENTER);
		add(lCarbohidratos);
		lGrasas = new JLabel("Grasas");
		lGrasas.setFont(fBold);
		lGrasas.setBounds(x*35, y*50, x*15, y*10);
		lGrasas.setHorizontalAlignment(JLabel.CENTER);
		add(lGrasas);
		lProteinas = new JLabel("Calorias");
		lProteinas.setFont(fBold);
		lProteinas.setBounds(x*50, y*50, x*15, y*10);
		lProteinas.setHorizontalAlignment(JLabel.CENTER);
		add(lProteinas);
		txtCalorias = new JTextField();
		txtCalorias.setBounds(x*5, y*60, x*15, y*10);
		txtCalorias.setHorizontalAlignment(JLabel.CENTER);
		add(txtCalorias);
		txtCarbohidratos = new JTextField();
		txtCarbohidratos.setBounds(x*20, y*60, x*15, y*10);
		txtCarbohidratos.setHorizontalAlignment(JLabel.CENTER);
		add(txtCarbohidratos);
		txtGrasas = new JTextField();
		txtGrasas.setBounds(x*35, y*60, x*15, y*10);
		txtGrasas.setHorizontalAlignment(JLabel.CENTER);
		add(txtGrasas);
		txtProteinas = new JTextField();
		txtProteinas.setBounds(x*50, y*60, x*15, y*10);
		txtProteinas.setHorizontalAlignment(JLabel.CENTER);
		add(txtProteinas);

		
		lTipo = new JLabel("Tipo");
		lTipo.setFont(fBold);
		lTipo.setBounds(x*5, y*76, x*10, y*10);
		add(lTipo);
		comboTipo = new JComboBox(new String[] {"Comida", "Bebida", "Postre"});
		comboTipo.setBounds(x*15, y*76, x*50, y*10);
		add(comboTipo);
		
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(fBold);
		btnAgregar.setBounds(x*73, y*55, x*15, y*5);
		add(btnAgregar);
		btnQuitar = new JButton("Quitar");
		btnQuitar.setFont(fBold);
		btnQuitar.setBounds(x*73, y*61, x*15, y*5);
		add(btnQuitar);
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setFont(fBold);
		btnActualizar.setBounds(x*73, y*67, x*15, y*5);
		add(btnActualizar);
		btnConsultar = new JButton("Consultar");
		btnConsultar.setFont(fBold);
		btnConsultar.setBounds(x*73, y*73, x*15, y*5);
		add(btnConsultar);
		
		
		setVisible(true);
	}
}
