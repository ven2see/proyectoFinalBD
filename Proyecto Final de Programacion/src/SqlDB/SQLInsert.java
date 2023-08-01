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
			String consultaExistencia = "SELECT COUNT(*) AS total FROM persona WHERE id_persona = ?";
			String sqlInsert = "insert into Persona(id_persona,pNombre,sNombre,pApellido,sapellido,genero,telefono,nacionalidad,calle,numcasa,Id_ciudad_FK,tipo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmtExistencia = conexion.prepareStatement(consultaExistencia);
			PreparedStatement statment = conexion.prepareStatement(sqlInsert);

			String sqlCuenta = "Insert into Cuenta(id_personal,nombre_usuario,contrasenia) VALUES(?, ?, ?)";
			PreparedStatement statment2 = conexion.prepareStatement(sqlCuenta);

			for(Persona per: AlticeSystem.getInstance().getMisPersonas()) {
				stmtExistencia.setString(1,per.getCedula());
				ResultSet rs = stmtExistencia.executeQuery();
				String tipoEmpleado = AlticeSystem.getInstance().tipoP(AlticeSystem.getInstance().buscarPersonaByCedula(per.getCedula()));
				String codCiudad= AlticeSystem.getInstance().getCiudades().get(per.getCiudadNacim()).getIDCiudad();
				
				rs.next();
				int total = rs.getInt("total");
				rs.close();

			//	genero,telefono,nacionalidad,calle,numcasa,Id_ciudad_FK,tipo)//
//Percalle,numcasa,Id_ciudad_FK,tipo)
				if (total == 0) {
					// La persona no existe en la base de datos, realizar la inserción
					statment.setString(1, per.getCedula());
					statment.setString(2, per.getNombre());
					statment.setString(3,per.getSnombre());
					statment.setString(4, per.getApellido());
					statment.setString(5,per.getSapellido());
					statment.setString(6, String.valueOf(per.getGenero()));
					statment.setString(7, per.getTelefono());
					statment.setString(8, codCiudad);
					statment.setString(9, per.getCalle());
					statment.setString(10,per.getNumCasa());
					statment.setString(11,codCiudad);
					statment.setString(12,tipoEmpleado);

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





//2. Se define la clase `SQLInsert` y el método `insertar` que recibe como parámetro una conexión a la base de datos.
//
//3. Se inicia un ciclo `for` que recorre una lista de personas almacenada en la instancia de la clase `AlticeSystem`, que parece ser una especie de sistema central donde se gestionan las personas y empleados.
//
//4. Dentro del ciclo, se realiza una verificación de existencia de la persona en la base de datos utilizando una consulta SQL para contar la cantidad de personas con el mismo `id_persona` (que se asume como `cedula`).
//
//5. Si la persona no existe en la base de datos (es decir, `total` es igual a 0), se procede a realizar la inserción de sus datos. Se utiliza una sentencia SQL `INSERT` para agregar los valores de los campos `id_persona`, `pNombre`, `sNombre`, `pApellido`, `sApellido`, `genero`, `telefono`, `nacionalidad`, `calle`, `numcasa`, `Id_ciudad_FK`, y `tipo` a la tabla `Persona`.
//
//6. Después de insertar los datos en la tabla `Persona`, se verifica si la persona es un administrador o un trabajador. Dependiendo del tipo de persona, se realiza una segunda inserción en la tabla `Cuenta` con los datos correspondientes.
//
//7. Una vez que se han realizado todas las inserciones necesarias, se imprime un mensaje indicando que la persona ha sido insertada en la base de datos.
//
//8. Si la persona ya existe en la base de datos (`total` es mayor que 0), se imprime un mensaje indicando que la persona ya existe y no se realiza ninguna inserción.
//
//9. En caso de que ocurra una excepción `SQLException` durante el proceso de inserción, se imprime la traza de la excepción.
//
