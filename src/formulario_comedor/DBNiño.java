package formulario_comedor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class DBNiño extends DBConexion
{
	public static NiñoObjeto leeNiño(String nombre, String apa, String ama) throws SQLException
	{
		NiñoObjeto reg = new NiñoObjeto();
		Connection con = GetConnection();
		String Select = "SELECT * FROM ViewNiñoFormulario WHERE nombre+apaterno+amaterno = '" + nombre+apa+ama + "'";
		PreparedStatement st1 = con.prepareStatement(Select);
		ResultSet rs1 = st1.executeQuery();
		if (!rs1.wasNull())
		{
			rs1.next();
			reg = crearObjeto(rs1);
		}
		con.close();
		return reg;
	}

	private static NiñoObjeto crearObjeto(ResultSet rs) throws SQLException
	{
		NiñoObjeto reg = new NiñoObjeto();
		reg.id = rs.getInt(1);
		reg.nombre = rs.getString(2);
		reg.apaterno = rs.getString(3);
		reg.amaterno = rs.getString(4);
		reg.nivel = rs.getInt(5);
		reg.grado = rs.getString(6);
		reg.idTutor = rs.getInt(7);
		reg.nacimiento = rs.getString(8);
		reg.tutorNombre = rs.getString(9);
		reg.tutorApaterno = rs.getString(9);
		reg.tutorAmaterno = rs.getString(9);
		return reg;
	}
}
