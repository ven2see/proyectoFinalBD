package SqlDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import logico.AlticeSystem;
import logico.P_Administrador;
import logico.P_Trabajador;
import logico.Persona;

public class SQLInsert {
	public void insertar(Connection conexion ) {

		try {
			// Datos que deseas insertar
			String consultaExistencia = "SELECT COUNT(*) AS total FROM personal WHERE id_persona = ?";
			String sqlInsert = "insert into Personal(id_persona,pNombre,sNombre,tipoEmpleado) VALUES (?, ?, ?, ?)";
			PreparedStatement stmtExistencia = conexion.prepareStatement(consultaExistencia);
			PreparedStatement statment = conexion.prepareStatement(sqlInsert);

			String sqlCuenta = "Insert into Cuenta(id_persona,nombre_usuario,contrasenia) VALUES(?, ?, ?)";
			PreparedStatement statment2 = conexion.prepareStatement(sqlCuenta);

			for(Persona per: AlticeSystem.getInstance().getMisPersonas()) {
				stmtExistencia.setString(1,per.getCedula());
				ResultSet rs = stmtExistencia.executeQuery();
				String tipoEmpleado = AlticeSystem.getInstance().tipoP(AlticeSystem.getInstance().buscarPersonaByCedula(per.getCedula()));
				rs.next();
				int total = rs.getInt("total");
				rs.close();


				if (total == 0) {
					// La persona no existe en la base de datos, realizar la inserción
					statment.setString(1, per.getCedula());
					statment.setString(2, per.getNombre());
					statment.setString(4,tipoEmpleado);
					statment.setString(3,per.getApellido());

					if(per instanceof P_Administrador) {
						statment2.setString(1,per.getCedula());
						statment2.setString(2,((P_Administrador)per).getCuenta().getUsuario());
						statment2.setString(3,((P_Administrador)per).getCuenta().getPassword());
					}else if(per instanceof P_Trabajador) {
						statment2.setString(1,per.getCedula());
						statment2.setString(2,((P_Trabajador)per).getCuenta().getUsuario());
						statment2.setString(3,((P_Trabajador)per).getCuenta().getPassword());
					}
					// Asigna otros valores a los campos de la tabla según tu estructura de datos

					statment.executeUpdate();
					statment2.executeUpdate();
					System.out.println("Persona insertada en la base de datos: " + per.getNombre());
				} else {
					System.out.println("La persona ya existe en la base de datos: " + per.getNombre());
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
