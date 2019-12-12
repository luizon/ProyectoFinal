package formulario_comedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBIngrediente extends DBConexion{

	public static IngredienteObjeto leeIngrediente(String nombre) throws SQLException
	{
		IngredienteObjeto reg = new IngredienteObjeto();
		Connection con = GetConnection();
		String Select = "SELECT * FROM ingrediente WHERE nombre = '" + nombre + "'";
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

	private static IngredienteObjeto crearObjeto(ResultSet rs) throws SQLException
	{
		IngredienteObjeto reg = new IngredienteObjeto();
		reg.id = rs.getInt(1);
		reg.nombre = rs.getString(2);
		reg.caducidad = rs.getString(3);
		reg.existencias = rs.getInt(4);
		return reg;
	}
	
	public static int getMaxID() throws SQLException
	{
		Connection con = GetConnection();
		String select = "SELECT max(id) FROM ingrediente";
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
