package formulario_comedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBTutores extends DBConexion
{

	public static TutorObjeto leeTutor(String nombre, String apa, String ama) throws SQLException
	{
		TutorObjeto reg = new TutorObjeto();
		Connection con = GetConnection();
		String Select = "SELECT * FROM ViewTutorFormulario WHERE nombre+apaterno+amaterno = '" + nombre + apa + ama + "'";
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

	private static TutorObjeto crearObjeto(ResultSet rs) throws SQLException
	{
		TutorObjeto reg = new TutorObjeto();
		reg.id = rs.getInt(1);
		reg.nombre = rs.getString(2);
		reg.apaterno = rs.getString(3);
		reg.amaterno = rs.getString(4);
		reg.Trabajo = rs.getString(5);
		reg.telTrabajo = rs.getLong(6);
		reg.cel = rs.getLong(7);
		reg.adeudo = rs.getDouble(8);
		return reg;
	}

	public static void insertTutores(String nombre, String apa, String ama, String trabajo, int telefonoTrabajo, int telefonoCelular) throws SQLException
	{
		Connection con = GetConnection();
		String query = "insert into tutor(nombre, apaterno, amaterno, lugar_de_trabajo, telefono_trabajo, telefono_celular) values(\'" + nombre + "\',\'" + apa + "\',\'" + ama + "\',\'" + trabajo + "\'," + telefonoTrabajo + "," + telefonoCelular + ")";
		PreparedStatement st = con.prepareStatement(query);
		st.executeUpdate();
	}
	
	public static void updateTutoreS(int id, String nombre, String apa, String ama, String trabajo, int telefonoTrabajo, int telefonoCelular) throws SQLException
	{
		Connection con = GetConnection();
		String query = "update tutor set nombre = \'"+nombre+"\', apaterno = \'"+apa+"\', amaterno =\'"+ama+"\', lugar_de_trabajo =\'"+trabajo+"\', telefono_trabajo ="+telefonoTrabajo+", telefono_celular ="+telefonoCelular+" where id = " + id;
		PreparedStatement st = con.prepareStatement(query);
		st.executeUpdate();
	}
	public static int getMaxID() throws SQLException
	{
		Connection con = GetConnection();
		String select = "SELECT max(id) FROM tutor";
		PreparedStatement st = con.prepareStatement(select);
		ResultSet rs = st.executeQuery();
		int Clave = 1;
		if (!rs.wasNull())
		{
			rs.next();
			Clave = rs.getInt(1) + 1;
		}
		con.close();
		return Clave;
	}
}
