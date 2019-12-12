package formulario_comedor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Niño extends JFrame implements ActionListener, FocusListener {
	JLabel titulo, lNombre, lAula, lTutor, lNacimiento, lMenu, lAlergias;
	JTextField txtNombre, txtApa, txtAma, txtAula, txtTutorN, txtTutorAp, txtTutorAm, txtNacimiento, txtMenu, txtAlergias;
	JButton btnAgregar, btnActualizar, btnQuitar, btnConsultar;
	
	public Niño(int w, int h) {
		super("Niño");
		setSize(w, h);
		int x = w/100, y = h/100;
		setLayout(null);
		setLocationRelativeTo(null);
		titulo = new JLabel("Formulario de niños");
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
		txtNombre = new JTextField("Jose");
		txtNombre.setBounds(x*15, y*10, x*20, y*10);
		add(txtNombre);
		txtApa = new JTextField("Martinez");
		txtApa.setBounds(x*35, y*10, x*15, y*10);
		add(txtApa);
		txtAma = new JTextField("Gonzales");
		txtAma.setBounds(x*50, y*10, x*15, y*10);
		add(txtAma);

		lAula = new JLabel("Aula");
		lAula.setFont(fBold);
		lAula.setBounds(x*70, y*10, x*10, y*10);
		add(lAula);
		txtAula = new JTextField();
		txtAula.setBounds(x*80, y*10, x*10, y*10);
		add(txtAula);
		
		lTutor = new JLabel("Tutor");
		lTutor.setFont(fBold);
		lTutor.setBounds(x*5, y*25, x*10, y*10);
		add(lTutor);
		txtTutorN = new JTextField();
		txtTutorN.setBounds(x*15, y*25, x*20, y*10);
		add(txtTutorN);
		txtTutorAp = new JTextField();
		txtTutorAp.setBounds(x*35, y*25, x*15, y*10);
		add(txtTutorAp);
		txtTutorAm = new JTextField();
		txtTutorAm.setBounds(x*50, y*25, x*15, y*10);
		add(txtTutorAm);

		lNacimiento= new JLabel("Nacimiento");
		lNacimiento.setFont(fBold);
		lNacimiento.setBounds(x*5, y*40, x*10, y*10);
		add(lNacimiento);
		txtNacimiento = new JTextField();
		txtNacimiento.setBounds(x*15, y*40, x*50, y*10);
		add(txtNacimiento);

		lMenu = new JLabel("Menu");
		lMenu.setFont(fBold);
		lMenu.setBounds(x*5, y*55, x*10, y*10);
		add(lMenu);
		txtMenu = new JTextField();
		txtMenu.setBounds(x*15, y*55, x*50, y*10);
		add(txtMenu);
		
		lAlergias = new JLabel("Alergias");
		lAlergias.setFont(fBold);
		lAlergias.setBounds(x*5, y*70, x*10, y*10);
		add(lAlergias);
		txtAlergias = new JTextField();
		txtAlergias.setBounds(x*15, y*70, x*50, y*10);
		add(txtAlergias);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(fBold);
		btnAgregar.setBounds(x*73, y*55, x*15, y*5);
		btnAgregar.addActionListener(this);
		add(btnAgregar);
		btnQuitar = new JButton("Quitar");
		btnQuitar.setFont(fBold);
		btnQuitar.setBounds(x*73, y*61, x*15, y*5);
		btnQuitar.addActionListener(this);
		add(btnQuitar);
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setFont(fBold);
		btnActualizar.setBounds(x*73, y*67, x*15, y*5);
		btnActualizar.addActionListener(this);
		add(btnActualizar);
		btnConsultar = new JButton("Consultar");
		btnConsultar.setFont(fBold);
		btnConsultar.setBounds(x*73, y*73, x*15, y*5);
		btnConsultar.addActionListener(this);
		add(btnConsultar);
	
		txtNombre.addFocusListener(this);
		txtApa.addFocusListener(this);
		txtAma.addFocusListener(this);
		txtAula.addFocusListener(this);
		txtTutorN.addFocusListener(this);
		txtTutorAp.addFocusListener(this);
		txtTutorAm.addFocusListener(this);
		txtNacimiento.addFocusListener(this);
		txtMenu.addFocusListener(this);
		txtAlergias.addFocusListener(this);
		
		setVisible(true);
	}

	private void buscarNombre()
	{
		try
		{
			NiñoObjeto n = DBNiño.leeNiño(txtNombre.getText(), txtApa.getText(), txtAma.getText());
			actualizarDatos(n);
		} catch (SQLException e)
		{
			String n = txtNombre.getText(), apa = txtApa.getText(), ama = txtAma.getText();
			JOptionPane.showMessageDialog(this.getParent(), "No se encontró un niño llamado " + n + " " + apa + " " + ama, "Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	private void actualizarDatos(NiñoObjeto n)
	{
		txtNombre.setText(n.nombre);
		txtApa.setText(n.apaterno);
		txtAma.setText(n.amaterno);
		txtAula.setText(n.nivel+n.grado);
		txtTutorN.setText(n.tutorNombre+"");
		txtTutorAp.setText(n.tutorApaterno+"");
		txtTutorAm.setText(n.tutorAmaterno+"");
		txtNacimiento.setText(n.nacimiento);
		Connection con = DBConexion.GetConnection();
		String proc1 = "declare @alergias NVARCHAR(200) " + 
				"exec alergiasNiñoConcatenadas '"+n.nombre+"', '"+n.apaterno+"', '"+n.amaterno+"', @alergias OUTPUT " + 
				"SELECT @alergias";
		String proc2 = "declare @menus NVARCHAR(200) " + 
				"exec menuNiñoConcatenadas '"+n.nombre+"', '"+n.apaterno+"', '"+n.amaterno+"', @menus OUTPUT " + 
				"SELECT @menus";
		String alergias = "";
		String menus = "";
		try {
			PreparedStatement st2 = con.prepareStatement(proc1);
			ResultSet rs2 = st2.executeQuery();
			PreparedStatement st3 = con.prepareStatement(proc2);
			ResultSet rs3 = st3.executeQuery();
			alergias = llamarProc(con, n.nombre, n.apaterno, n.amaterno, "alergiasNiñoConcatenadas", rs2, java.sql.Types.NVARCHAR);
			menus = llamarProc(con, n.nombre, n.apaterno, n.amaterno, "menuNiñoConcatenadas", rs3, java.sql.Types.NVARCHAR);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		txtMenu.setText(menus);
		txtAlergias.setText(alergias);
	}
	
	private void EliminarAlMorro() 
	{
		String nom, apa, ama;
		nom = txtNombre.getText();
		apa = txtApa.getText();
		ama = txtAma.getText();
		try
		{
			NiñoObjeto n = DBNiño.leeNiño(txtNombre.getText(), txtApa.getText(), txtAma.getText());
			eliminarAlumno(n);
		} catch (SQLException e)
		{
			JOptionPane.showMessageDialog(this.getParent(), "No se pudo eliminar al niño llamado " + nom + " " + apa + " " + ama, "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		JOptionPane.showMessageDialog(this.getParent(), "El niño llamado " + nom + " " + apa + " " + ama +" ha sido eliminado.", "Exito", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void eliminarAlumno(NiñoObjeto n) throws SQLException
	{
		Connection con = DBConexion.GetConnection();
		String query = "DELETE FROM niño WHERE nombre+apaterno+amaterno = '" + n.nombre + n.apaterno + n.amaterno + "'";
		PreparedStatement st = con.prepareStatement(query);
		st.executeUpdate();
	}	
	
	public void actualizarAlumno() throws SQLException {
		Connection con = DBConexion.GetConnection();
		String tnombre = txtTutorN.getText(), tapaterno = txtTutorAp.getText(), tamaterno = txtTutorAm.getText();
		String sp = "declare @alergias NVARCHAR(200) " + 
				"exec alergiasNiñoConcatenadas '"+tnombre+"', '"+tapaterno+"', '"+tamaterno+"', @alergias OUTPUT " + 
				"SELECT @alergias";
		PreparedStatement st = con.prepareStatement(sp);
		ResultSet rs = st.executeQuery();
		int id = Integer.parseInt(llamarProc(con, tnombre, tapaterno, tamaterno, "devuelveIDTutor", rs, java.sql.Types.INTEGER));
		System.out.println(id);
	}

	private static String llamarProc(Connection con, String nombre, String apa, String ama, String sp, ResultSet rs, int tipo) throws SQLException
	{
		CallableStatement cst = con.prepareCall("{call "+sp+" (?, ?, ?, ?)}");
		cst.registerOutParameter(4, tipo);
		cst.setString(1, nombre);
		cst.setString(2, apa);
		cst.setString(3, ama);		
		cst.setEscapeProcessing(true);
		cst.execute();
		return cst.getString(4);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAgregar) {
			return;
		}
		if(e.getSource() == btnActualizar) {
			try {
				actualizarAlumno();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return;
		}
		if(e.getSource() == btnQuitar) {
			EliminarAlMorro();
			return;
		}
		if(e.getSource() == btnConsultar) {
			buscarNombre();
			return;
		}
	}
	public void focusGained(FocusEvent e) {
		if(e.getSource() == txtNombre) {
			txtNombre.selectAll();
			return;
		}
		if(e.getSource() == txtApa) {
			txtApa.selectAll();
			return;
		}
		if(e.getSource() == txtAma) {
			txtAma.selectAll();
			return;
		}
		if(e.getSource() == txtAula) {
			txtAula.selectAll();
			return;
		}
		if(e.getSource() == txtNacimiento) {
			txtNacimiento.selectAll();
			return;
		}
		if(e.getSource() == txtTutorN) {
			txtTutorN.selectAll();
			return;
		}
		if(e.getSource() == txtTutorAp) {
			txtTutorAp.selectAll();
			return;
		}
		if(e.getSource() == txtTutorAm) {
			txtTutorAm.selectAll();
			return;
		}
		if(e.getSource() == txtAlergias) {
			txtAlergias.selectAll();
			return;
		}
		if(e.getSource() == txtMenu) {
			txtMenu.selectAll();
			return;
		}
	}
	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
