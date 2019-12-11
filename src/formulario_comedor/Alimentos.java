package formulario_comedor;

import java.awt.Font;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Alimentos extends JFrame{

	JLabel titulo;
	String[] encabezado;
	JTable tabla;
	DefaultTableModel modelotabla;
	
	public Alimentos(int w, int h) {
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
		
		//tabla
		encabezado = new String[] {"Nombre", "Tipo", "Calorias", "Carbohidratos", "Proteinas", "Grasas", "Porciones restantes"};
		try {
			modelotabla = llenarTabla();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tabla = new JTable()
		{
			private static final long serialVersionUID = 1L;
			boolean editable[] = new boolean[] {true,false,false,false,false};
			public boolean isCellEditable(int rowIndex, int columnIndex)
			{
				return editable[columnIndex];
			}
		};
		tabla.getTableHeader().setResizingAllowed(false);
		tabla.getTableHeader().setReorderingAllowed(false);
		tabla.setModel(modelotabla);
        add(tabla);
		JScrollPane scroll = new JScrollPane(tabla);
		add(scroll);
		scroll.setBounds(x*2, y*10, x*94, y*79);
		
		setVisible(true);
	}
	public static DefaultTableModel llenarTabla() throws SQLException
	{
		String encabezado[] = {"Nombre", "Tipo", "Calorias", "Carbohidratos", "Proteinas", "Grasas", "Porciones restantes"};
		String renglon[] = new String[19];
		DefaultTableModel dtm = new DefaultTableModel(encabezado, 0);
		
		Connection con = DBConexion.GetConnection();
		String select = "SELECT * FROM alimento";
		PreparedStatement st = con.prepareStatement(select);
		ResultSet rs = st.executeQuery();

		while (rs.next())
		{
			AlimentoObjeto reg = crearObjeto(rs);
			renglon[0] = "" + reg.id;
			renglon[1] = "" + reg.Nombre;
			renglon[2] = "" + reg.Tipo;
			renglon[3] = "" + reg.Calorias;
			renglon[4] = "" + reg.Carbohidratos;
			renglon[5] = "" + reg.Proteinas;
			renglon[6] = "" + reg.Grasas;
			renglon[7] = "" + reg.PorcionesRestantes;

			dtm.addRow(renglon);
		}
		return dtm;
	}

	private static AlimentoObjeto crearObjeto(ResultSet rs) throws SQLException
	{
		AlimentoObjeto reg = new AlimentoObjeto();
		reg.id = rs.getInt(1);
		reg.Nombre = rs.getString(2);
		reg.Tipo = rs.getString(3);
		reg.Calorias = rs.getDouble(4);
		reg.Carbohidratos = rs.getDouble(5);
		reg.Proteinas = rs.getDouble(6);
		reg.Grasas = rs.getDouble(7);
		reg.PorcionesRestantes = rs.getInt(8);
		return reg;
	}
	
	private static String llamarProc(Connection con, String nombre, String apa, String ama, String select, ResultSet rs) throws SQLException
	{
		System.out.println("damn");
		CallableStatement cst = con.prepareCall("{? = call alergiasNiñoConcatenadas (?, ?, ?)}");
		cst.registerOutParameter(1, java.sql.Types.NVARCHAR);
		cst.setString(2, nombre);
		cst.setString(3, apa);
		cst.setString(4, ama);		
		cst.setEscapeProcessing(true);
		cst.execute();
		System.out.println(cst.getString(1));
		return cst.getString(1);
	}

	public static int getMaxID() throws SQLException
	{
		Connection con = DBConexion.GetConnection();
		String select = "SELECT max(id) FROM niño";
		PreparedStatement st = con.prepareStatement(select);
		ResultSet rs = st.executeQuery();
		int Clave = 1;
		if(!rs.wasNull())
		{
			rs.next();
			Clave = rs.getInt(1)+1;
		}
		con.close();
		return Clave;
	}

}
