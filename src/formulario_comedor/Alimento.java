package formulario_comedor;

import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Alimento extends JFrame implements ActionListener, FocusListener{
	JLabel titulo, lNombre, lLista, lTipo, lInfoNutricional, lCalorias, lCarbohidratos, lProteinas, lGrasas, lPorciones;
	JTextField txtNombre, txtPorciones, txtCalorias, txtCarbohidratos, txtProteinas, txtGrasas;
	JButton btnAgregar, btnActualizar, btnQuitar, btnConsultar;
	JTable tablaIngredientes;
	DefaultTableModel modelotabla;
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
		modelotabla = new DefaultTableModel(encabezado, 0);
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
		lProteinas = new JLabel("Proteinas");
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
		
		btnAgregar.addActionListener(this);
		btnQuitar.addActionListener(this);
		btnActualizar.addActionListener(this);
		btnConsultar.addActionListener(this);
	
		txtNombre.addFocusListener(this);
		txtPorciones.addFocusListener(this);
		txtCalorias.addFocusListener(this);
		txtCarbohidratos.addFocusListener(this);
		txtGrasas.addFocusListener(this);
		txtProteinas.addFocusListener(this);
		
		setVisible(true);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////
	
	private void buscarAlimento()
	{
		try
		{
			AlimentoObjeto n = DBAlimento.leeAlimento(txtNombre.getText());
			actualizarDatos(n);
		} catch (SQLException e)
		{
			String n = txtNombre.getText();
			JOptionPane.showMessageDialog(this.getParent(), "No se encontró un alimento llamado " + n, "Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void actualizarDatos(AlimentoObjeto n)
	{
		txtNombre.setText(n.nombre);
		txtPorciones.setText(n.PorcionesRestantes+"");
		txtCalorias.setText(n.calorias+"");
		txtCarbohidratos.setText(n.carbohidratos+"");
		txtGrasas.setText(n.grasas+"");
		txtProteinas.setText(n.proteinas+"");
		
		try {
			modelotabla = llenarTabla(n.id);
			tablaIngredientes.setModel(modelotabla);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static DefaultTableModel llenarTabla(int id) throws SQLException
	{
		String encabezado[] = {"Ingrediente", "Cantidad por porcion"};
		String renglon[] = new String[19];
		DefaultTableModel dtm = new DefaultTableModel(encabezado, 0);
		
		Connection con = DBConexion.GetConnection();
		//corregir
		String select = "SELECT * FROM niño WHERE id_tutor = "+id+"";/////aaaaaaaaaaaaaaaaaaaah
		PreparedStatement st = con.prepareStatement(select);
		ResultSet rs = st.executeQuery();

		while (rs.next())
		{
			IngredienteObjeto reg = crearObjeto(rs);
			renglon[0] = "" + reg.nombre;
			renglon[1] = "" + reg.existencias; //no se que pedo aqui

			dtm.addRow(renglon);
		}
		return dtm;
	}

	private static IngredienteObjeto crearObjeto(ResultSet rs) throws SQLException
	{
		IngredienteObjeto reg = new IngredienteObjeto();
		reg.nombre = rs.getString(2);
		reg.caducidad = rs.getString(3);
		reg.existencias = rs.getInt(4);
		//maybe poner aqui el de cantidad por porcion ? 
		return reg;
	}

	private void eliminarAlimento() 
	{
		String nom;
		nom = txtNombre.getText();
		try
		{
			AlimentoObjeto n = DBAlimento.leeAlimento(txtNombre.getText());
			eliminarlo(n);
		} catch (SQLException e)
		{
			JOptionPane.showMessageDialog(this.getParent(), "No se pudo eliminar al alimento llamado " + nom , "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		JOptionPane.showMessageDialog(this.getParent(), "El alimento llamado " + nom + " " + "ha sido eliminado.", "Exito", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void eliminarlo(AlimentoObjeto n) throws SQLException
	{
		Connection con = DBConexion.GetConnection();
		String query = "DELETE FROM alimento WHERE nombre = '" + n.nombre + "'";
		PreparedStatement st = con.prepareStatement(query);
		st.executeUpdate();
	}
	
	
	

	///////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAgregar) {
			return;
		}
		if(e.getSource() == btnActualizar) {
			return;
		}
		if(e.getSource() == btnQuitar) {
			eliminarAlimento();
			return;
		}
		if(e.getSource() == btnConsultar) {
			buscarAlimento();
			return;
		}
		
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource() == txtNombre) {
			txtNombre.selectAll();
			return;
		}
		if(e.getSource() == txtPorciones) {
			txtPorciones.selectAll();
			return;
		}
		if(e.getSource() == txtCalorias) {
			txtCalorias.selectAll();
			return;
		}
		if(e.getSource() == txtCarbohidratos) {
			txtCarbohidratos.selectAll();
			return;
		}
		if(e.getSource() == txtGrasas) {
			txtGrasas.selectAll();
			return;
		}
		if(e.getSource() == txtProteinas) {
			txtProteinas.selectAll();
			return;
		}
	}
	
	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
