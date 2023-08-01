package SqlDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import logico.AlticeSystem;
import logico.Ciudad;
import logico.Cliente;
import logico.Cuenta;
import logico.Factura;
import logico.P_Administrador;
import logico.P_Trabajador;
import logico.Persona;
import logico.PlanAdquirido;

public class LlenarLista {

    public void listar(Connection conn) {
    	Persona aux = null;
        try {
            PreparedStatement statment = conn.prepareStatement("select * from personal join persona on persona.id_persona = personal.id_personal");
            ResultSet rs = statment.executeQuery();
            int indiceCIUD = 0;
            
            //Llenar Lista de persona
            while(rs.next()) {
            	
            	indiceCIUD = AlticeSystem.getInstance().buscarCiudadBYCODE(rs.getString("id_ciudad_FK"));
            	if(rs.getString("tipoEmpleado").toString().equalsIgnoreCase("Admin")) {
					aux = new P_Administrador(rs.getString("id_persona"), rs.getString("pNombre"),rs.getString("sNombre"),rs.getString("pApellido"),rs.getString("sApellido"), rs.getString("Genero"), rs.getString("telefono"),indiceCIUD,rs.getString("calle"),rs.getString("Numcasa"),rs.getString("Tipo"));
					
		        			AlticeSystem.getInstance().insertarPersona(aux);
		
				}else if(rs.getString("tipoEmpleado").toString().equalsIgnoreCase("trabajador")) {
					aux = new P_Trabajador(rs.getString("id_persona"), rs.getString("pNombre"),rs.getString("Snombre"),rs.getString("Papellido"),rs.getString("Sapellido"), rs.getString("Genero"), rs.getString("telefono"),indiceCIUD,rs.getString("calle"),rs.getString("Numcasa"),rs.getString("Tipo"));
		        			AlticeSystem.getInstance().insertarPersona(aux);
				}

            }
            
        	for(int ind=0; ind<AlticeSystem.getInstance().getMisPersonas().size(); ind++) {
        		if(AlticeSystem.getInstance().getMisPersonas() != null && AlticeSystem.getInstance().getMisPersonas().get(ind) instanceof P_Administrador) {
            		System.out.println(AlticeSystem.getInstance().getMisPersonas().get(ind).getCedula());
        		}
        	}
            	
        	
            	//Listar Cuenta
                PreparedStatement statment2 = conn.prepareStatement("select * from cuenta");
                ResultSet rs2 = statment2.executeQuery();
                
                while(rs2.next()) {
                		Cuenta cuenta = new Cuenta(rs2.getString("contrasenia"),rs2.getString("nombre_usuario"));
						AlticeSystem.getInstance().addUser(rs2.getString("id_personal"), cuenta);
                }
                
                PreparedStatement statmentCiud = conn.prepareStatement("select * from ciudad");
                ResultSet rsCiud = statmentCiud.executeQuery();
                
                while(rsCiud.next()) {
                		Ciudad ciud = new Ciudad(rsCiud.getString("id_ciudad"), rsCiud.getString("nombre_ciudad"), rsCiud.getString("ID_pais_fk"));
						AlticeSystem.getInstance().addCiudad(ciud);
                }
                
                PreparedStatement statmentFac = conn.prepareStatement("select * from Cliente join factura on cliente.id_cliente = factura.id_cliente_fk inner join persona on persona.id_persona = cliente.id_cliente");
                ResultSet rsFac = statmentFac.executeQuery();
                Cliente cli = null;
                while(rsFac.next()) {
                	cli = new Cliente(rs.getString("id_cliente"), rs.getString("pNombre"),rs.getString("Snombre"),rs.getString("Papellido"),rs.getString("Sapellido"), rs.getString("Genero"), rs.getString("telefono"),indiceCIUD,rs.getString("calle"),rs.getString("Numcasa"),rs.getString("Tipo"));
                	
                	AlticeSystem.getInstance().addFactura(cli);
                	
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



//2. Se define la clase `LlenarLista` y el método `listar` que recibe como parámetro una conexión a la base de datos.
//
//3. Se inicia una consulta SQL para obtener los datos de la tabla `personal` unida con la tabla `persona` usando una cláusula JOIN. Se ejecuta la consulta y se obtiene un conjunto de resultados `ResultSet`.
//
//4. Se recorre el resultado obtenido para llenar la lista de personas (`AlticeSystem.getInstance().getMisPersonas()`). Dependiendo del valor del campo `tipoEmpleado` obtenido de la base de datos, se crea una instancia de `P_Administrador` o `P_Trabajador`, y luego se agrega a la lista de personas.
//
//5. Después de llenar la lista de personas, se inicia otra consulta SQL para obtener los datos de la tabla `cuenta`. Se ejecuta la consulta y se recorre el resultado para crear instancias de `Cuenta` y agregarlas al sistema `AlticeSystem` usando el método `addUser`.
//
//6. Se realiza una tercera consulta SQL para obtener los datos de la tabla `ciudad`. Se ejecuta la consulta y se recorre el resultado para crear instancias de `Ciudad` y agregarlas al sistema `AlticeSystem` usando el método `addCiudad`.
//
//7. Finalmente, se inicia una última consulta SQL para obtener los datos de la tabla `Cliente` unida con la tabla `factura` y con la tabla `persona`. Se ejecuta la consulta y se recorre el resultado para crear instancias de `Cliente`, agregarlas al sistema `AlticeSystem` y asociar las facturas a los clientes usando el método `addFactura`.
//
//8. Si ocurre una excepción `SQLException` durante el proceso de obtención y llenado de datos, se imprime la traza de la excepción.
//

