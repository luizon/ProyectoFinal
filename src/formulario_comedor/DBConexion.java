package formulario_comedor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DBConexion
{
	@SuppressWarnings("finally")
	public static Connection GetConnection()
	{
		Connection conexion = null;
		try
		{
			String usuario = "formularioComedor", contraseña = "UzumakiRasenganSasukeShidori", db = "comedor";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conexion = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName="+db, usuario, contraseña);
		} catch (ClassNotFoundException ex)
		{
			JOptionPane.showMessageDialog(null, ex, "Error con la clase del driver de SQL Server" + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
			System.out.println("Error ClassNotFoundException oh no :o");
			conexion = null;
		} catch (SQLException ex)
		{
			JOptionPane.showMessageDialog(null, ex, "Error en la Conexión con la BD" + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
			System.out.println("Error SQLException oh no :o");
			conexion = null;
		} catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex, "Error del programa" + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
			System.out.println("Error Exception oh no :o");
			conexion = null;
		} finally
		{
			return conexion;
		}
	}
}
