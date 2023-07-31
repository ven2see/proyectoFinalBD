package SqlDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logico.AlticeSystem;
import logico.Cuenta;
import logico.P_Administrador;
import logico.P_Trabajador;
import logico.Persona;

public class LlenarLista {

    public void listar(Connection conn) {
    	Persona aux = null;
        try {
            PreparedStatement statment = conn.prepareStatement("select * from personal join persona on persona.id_persona = personal.id_personal");
            ResultSet rs = statment.executeQuery();
            
            //Llenar Lista de persona
            while(rs.next()) {
            	if(rs.getString("tipoEmpleado").toString().equalsIgnoreCase("Admin")) {
					aux = new P_Administrador(rs.getString("id_persona"), rs.getString("pNombre"),rs.getString("sNombre"),rs.getString("pApellido"),rs.getString("sApellido"), rs.getString("Genero"), rs.getString("telefono"),rs.getString("id_ciudad_fk"),rs.getString("calle"),rs.getString("Numcasa"),rs.getString("Tipo"));
					
		        			AlticeSystem.getInstance().insertarPersona(aux);
		        			
		        			
//		        			(String cedula, String nombre, String apellido, String snombre, String sapellido,
//		        					String genero, String telefono, String ciudadNacim, String calle, String numCasa, String direccion)
		        			
				}else if(rs.getString("tipoEmpleado").toString().equalsIgnoreCase("trabajador")) {
					aux = new P_Trabajador(rs.getString("id_personal"), rs.getString("pNombre"),rs.getString("Snombre"),rs.getString("Papellido"),rs.getString("Sapellido"), rs.getString("Genero"), rs.getString("telefono"),rs.getString("id_ciudad_fk"),rs.getString("calle"),rs.getString("Numcasa"),rs.getString(null));
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
