package formulario_comedor;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Tutores extends JFrame implements ActionListener, FocusListener
{

	JLabel titulo, lNombre, ladeudo, lcelular, ltrabajo, lteltrabajo, lhijos;
	JTextField txtNombre, txtApa, txtAma, txtadeudo, txtcel, txttrabajo, txtteltrabajo;
	JButton btnAgregar, btnActualizar, btnQuitar, btnConsultar;
	String[] encabezado;
	JTable tabla;
	DefaultTableModel modelotabla;

	public Tutores(int w, int h)
	{
		super("Tutor");
		setSize(w, h);
		int x = w / 100, y = h / 100;
		setLayout(null);
		setLocationRelativeTo(null);
		titulo = new JLabel("Formulario de tutores");
		Font fBold = new Font("Arial", Font.BOLD, 15);
		titulo.setFont(fBold);
		titulo.setHorizontalAlignment(JLabel.CENTER);
		add(titulo);
		titulo.setBounds(0, 0, w, 50);

		Font fPlain = new Font("Arial", Font.PLAIN, 15);
		lNombre = new JLabel("Nombre");
		lNombre.setFont(fBold);
		lNombre.setBounds(x * 5, y * 10, x * 10, y * 10);
		add(lNombre);
		txtNombre = new JTextField("Douglas");
		txtNombre.setBounds(x * 15, y * 10, x * 20, y * 10);
		add(txtNombre);
		txtApa = new JTextField("Steger");
		txtApa.setBounds(x * 35, y * 10, x * 15, y * 10);
		add(txtApa);
		txtAma = new JTextField("Kraus");
		txtAma.setBounds(x * 50, y * 10, x * 15, y * 10);
		add(txtAma);

		ladeudo = new JLabel("Adeudo");
		ladeudo.setFont(fBold);
		ladeudo.setBounds(x * 70, y * 10, x * 10, y * 10);
		add(ladeudo);
		txtadeudo = new JTextField();
		txtadeudo.setBounds(x * 80, y * 10, x * 10, y * 10);
		add(txtadeudo);

		lcelular = new JLabel("Tel. Celular");
		lcelular.setFont(fBold);
		lcelular.setBounds(x * 5, y * 25, x * 10, y * 10);
		add(lcelular);
		txtcel = new JTextField();
		txtcel.setBounds(x * 15, y * 25, x * 50, y * 10);
		add(txtcel);

		ltrabajo = new JLabel("Lugar de trabajo");
		ltrabajo.setFont(fBold);
		ltrabajo.setBounds(x * 5, y * 40, x * 10, y * 10);
		add(ltrabajo);
		txttrabajo = new JTextField();
		txttrabajo.setBounds(x * 15, y * 40, x * 50, y * 10);
		add(txttrabajo);

		lteltrabajo = new JLabel("Tel. de trabajo");
		lteltrabajo.setFont(fBold);
		lteltrabajo.setBounds(x * 5, y * 55, x * 10, y * 10);
		add(lteltrabajo);
		txtteltrabajo = new JTextField();
		txtteltrabajo.setBounds(x * 15, y * 55, x * 50, y * 10);
		add(txtteltrabajo);

		lhijos = new JLabel("Hijos");
		lhijos.setFont(fBold);
		lhijos.setBounds(x * 5, y * 70, x * 10, y * 10);
		add(lhijos);
		/*
		 * txthijos = new JTextField(); txthijos.setBounds(x*15, y*70, x*50, y*10);
		 * add(txthijos);
		 */

		// tabla
		encabezado = new String[] { "Nombre", "Apellido P", "Apellido M", "Aula" };
		modelotabla = new DefaultTableModel(encabezado, 0);
		tabla = new JTable()
		{
			private static final long serialVersionUID = 1L;
			boolean editable[] = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex)
			{
				return editable[columnIndex];
			}
		};
		tabla.getTableHeader().setResizingAllowed(false);
		tabla.getTableHeader().setReorderingAllowed(false);
		tabla.setModel(modelotabla);
		tabla.setFocusable(false);
		JScrollPane scroll = new JScrollPane(tabla);
		add(scroll);
		scroll.setBounds(x * 15, y * 70, x * 50, y * 20);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(fBold);
		btnAgregar.setBounds(x * 73, y * 55, x * 15, y * 5);
		add(btnAgregar);
		btnQuitar = new JButton("Quitar");
		btnQuitar.setFont(fBold);
		btnQuitar.setBounds(x * 73, y * 61, x * 15, y * 5);
		add(btnQuitar);
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setFont(fBold);
		btnActualizar.setBounds(x * 73, y * 67, x * 15, y * 5);
		add(btnActualizar);
		btnConsultar = new JButton("Consultar");
		btnConsultar.setFont(fBold);
		btnConsultar.setBounds(x * 73, y * 73, x * 15, y * 5);
		add(btnConsultar);

		btnAgregar.addActionListener(this);
		btnQuitar.addActionListener(this);
		btnActualizar.addActionListener(this);
		btnConsultar.addActionListener(this);

		txtNombre.addFocusListener(this);
		txtApa.addFocusListener(this);
		txtAma.addFocusListener(this);
		txtadeudo.addFocusListener(this);
		txtcel.addFocusListener(this);
		txttrabajo.addFocusListener(this);
		txtteltrabajo.addFocusListener(this);

		setVisible(true);
	}

	// al 100 compa
	private void buscarNombre()
	{
		try
		{
			TutorObjeto n = DBTutores.leeTutor(txtNombre.getText(), txtApa.getText(), txtAma.getText());
			actualizarDatos(n);
		} catch (SQLException e)
		{
			String n = txtNombre.getText(), apa = txtApa.getText(), ama = txtAma.getText();
			JOptionPane.showMessageDialog(this.getParent(), "No se encontró un tutor llamado " + n + " " + apa + " " + ama, "Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// falta
	private void actualizarDatos(TutorObjeto n)
	{
		txtNombre.setText(n.nombre);
		txtApa.setText(n.apaterno);
		txtAma.setText(n.amaterno);
		txtadeudo.setText(n.adeudo + "");// esperar
		txtcel.setText(n.cel + "");
		txttrabajo.setText(n.Trabajo);
		txtteltrabajo.setText(n.telTrabajo + "");

		try
		{
			modelotabla = llenarTabla(n.id);
			tabla.setModel(modelotabla);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public static DefaultTableModel llenarTabla(int id) throws SQLException
	{
		String encabezado[] = { "Nombre", "Apellido P", "Apellido M", "Aula" };
		String renglon[] = new String[19];
		DefaultTableModel dtm = new DefaultTableModel(encabezado, 0);

		Connection con = DBConexion.GetConnection();
		String select = "SELECT * FROM niño WHERE id_tutor = " + id + "";
		PreparedStatement st = con.prepareStatement(select);
		ResultSet rs = st.executeQuery();

		while (rs.next())
		{
			NiñoObjeto reg = crearObjeto(rs);
			renglon[0] = "" + reg.nombre;
			renglon[1] = "" + reg.apaterno;
			renglon[2] = "" + reg.amaterno;
			renglon[3] = "" + reg.nivel + reg.grado;

			dtm.addRow(renglon);
		}
		return dtm;
	}

	private static NiñoObjeto crearObjeto(ResultSet rs) throws SQLException
	{
		NiñoObjeto reg = new NiñoObjeto();
		reg.nombre = rs.getString(2);
		reg.apaterno = rs.getString(3);
		reg.amaterno = rs.getString(4);
		reg.nivel = rs.getInt(5);
		reg.grado = rs.getString(6);
		return reg;
	}

	private void eliminarAlPadrino()
	{
		String nom, apa, ama;
		nom = txtNombre.getText();
		apa = txtApa.getText();
		ama = txtAma.getText();
		try
		{
			TutorObjeto n = DBTutores.leeTutor(txtNombre.getText(), txtApa.getText(), txtAma.getText());
			eliminarUsuario(n);
		} catch (SQLException e)
		{
			JOptionPane.showMessageDialog(this.getParent(), "No se pudo eliminar al tutor llamado " + nom + " " + apa + " " + ama, "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		JOptionPane.showMessageDialog(this.getParent(), "El tutor llamado " + nom + " " + apa + " " + ama + " ha sido eliminado.", "Exito", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void eliminarUsuario(TutorObjeto n) throws SQLException
	{
		Connection con = DBConexion.GetConnection();
		String query = "DELETE FROM tutor WHERE nombre+apaterno+amaterno = '" + n.nombre + n.apaterno + n.amaterno + "'";
		PreparedStatement st = con.prepareStatement(query);
		st.executeUpdate();
	}

	private void agregarAlPadrino()
	{
		try
		{
			DBTutores.insertTutores(txtNombre.getText(), txtApa.getText(), txtAma.getText(), txttrabajo.getText(), Integer.valueOf(txtteltrabajo.getText()), Integer.valueOf(txtcel.getText()));
			JOptionPane.showConfirmDialog(this.getParent(), "Tutor agregado con éxito");
		} catch (SQLException e)
		{
			e.printStackTrace();
			JOptionPane.showConfirmDialog(this.getParent(), "No se pudo agregar tutor", "Ya sé que no tienen sentido las otras opciones, nomás ando viendo", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void actualizarAlPadrino()
	{
		try
		{
			DBTutores.updateTutores(txtNombre.getText(), txtApa.getText(), txtAma.getText(), txttrabajo.getText(), Integer.valueOf(txtteltrabajo.getText()), Integer.valueOf(txtcel.getText()));
			JOptionPane.showMessageDialog(this.getParent(), "Tutor actualizado con éxito");
		} catch (SQLException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.getParent(), "No se pudo actualizar tutor", "Ya sé que no tienen sentido las otras opciones, nomás ando viendo", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnAgregar)
		{
			agregarAlPadrino();
			return;
		}
		if (e.getSource() == btnActualizar)
		{
			actualizarAlPadrino();
			return;
		}
		if (e.getSource() == btnQuitar)
		{
			eliminarAlPadrino();
			return;
		}
		if (e.getSource() == btnConsultar)
		{
			buscarNombre();
			return;
		}
	}

	@Override
	public void focusGained(FocusEvent e)
	{
		if (e.getSource() == txtNombre)
		{
			txtNombre.selectAll();
			return;
		}
		if (e.getSource() == txtApa)
		{
			txtApa.selectAll();
			return;
		}
		if (e.getSource() == txtAma)
		{
			txtAma.selectAll();
			return;
		}
		if (e.getSource() == txtadeudo)
		{
			txtadeudo.selectAll();
			return;
		}
		if (e.getSource() == txtcel)
		{
			txtcel.selectAll();
			return;
		}
		if (e.getSource() == txttrabajo)
		{
			txttrabajo.selectAll();
			return;
		}
		if (e.getSource() == txtteltrabajo)
		{
			txtteltrabajo.selectAll();
			return;
		}
	}

	@Override
	public void focusLost(FocusEvent arg0)
	{
		// TODO Auto-generated method stub

	}
}
