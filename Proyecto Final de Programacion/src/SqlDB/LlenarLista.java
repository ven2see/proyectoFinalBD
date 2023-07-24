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

    public void listar() {
    	Persona aux = null;
        try {
            Connection conn = ConexionDB.getConnection();
            PreparedStatement statment = conn.prepareStatement("select * from Personal");
            ResultSet rs = statment.executeQuery();
            
            //Llenar Lista de persona
            while(rs.next()) {
            	if(rs.getString("tipoEmpleado").toString().equalsIgnoreCase("admin")) {
					aux = new P_Administrador(rs.getString("id_persona"), rs.getString("pNombre"),
							rs.getString("Papellido"), "M",
							"Haitiana", " ", " "  ,
							rs.getString("tipoEmpleado").toString(),"hola");
		        			AlticeSystem.getInstance().insertarPersona(aux);
		        			
				}else if(rs.getString("tipoEmpleado").toString().equalsIgnoreCase("trabajador")) {
					aux = new P_Trabajador(rs.getString("id_persona"), rs.getString("pNombre"),
							rs.getString("Papellido"), "M",
							"Haitiana", " ", " "  ,
							rs.getString("tipoEmpleado").toString(),"hola");
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
						AlticeSystem.getInstance().addUser(rs2.getString("id_persona"), cuenta);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
