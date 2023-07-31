package SqlDB;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class ConexionDB {

	private static Connection conn;
	public static Connection getConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://192.168.100.118" + ":1433;DatabaseName=Altice_group_5" + ";encrypt=true;trustServerCertificate=true";
			conn = DriverManager.getConnection(url,"dernest","DdE200004@@");

			JOptionPane.showMessageDialog(null,"La conexión ha sido exitosa.");
		}catch (Exception e) {
			conn=null;
			JOptionPane.showMessageDialog(null,"No hemos podido establecer una conexión con el servido "+e);
		}
		return conn;
	}
	public ConexionDB() {

		Connection prueba = ConexionDB.getConnection();
		if(prueba!=null) {
			JOptionPane.showMessageDialog(null,"Conexión encontrada!");
		}

	}
}