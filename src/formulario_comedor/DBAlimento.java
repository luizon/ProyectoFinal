package formulario_comedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBAlimento extends DBConexion{

	public static AlimentoObjeto leeAlimento(String nombre) throws SQLException
	{
		AlimentoObjeto reg = new AlimentoObjeto();
		Connection con = GetConnection();
		String Select = "SELECT * FROM Alimento WHERE nombre = '" + nombre + "'";
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

	private static AlimentoObjeto crearObjeto(ResultSet rs) throws SQLException
	{
		AlimentoObjeto reg = new AlimentoObjeto();
		reg.id = rs.getInt(1);
		reg.nombre = rs.getString(2);
		reg.tipo = rs.getString(3);//AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHHHH
		reg.calorias = rs.getDouble(4);
		reg.carbohidratos = rs.getDouble(5);
		reg.proteinas = rs.getDouble(6);
		reg.grasas = rs.getDouble(7);
		reg.PorcionesRestantes = rs.getInt(8);
		return reg;
	}
}
