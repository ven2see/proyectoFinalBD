package logico;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Cliente extends Persona {
	
	private static final long serialVersionUID = 1L;
	private ArrayList <Factura> misFacturas;
	private ArrayList<PlanAdquirido> misPlanesAd;

//	public Cliente(String cedula, String nombre, String apellido, String genero, String nacionalidad, String direccion,
//			String telefono, String tipo, String codigoUsuario) {
//		super(cedula, nombre, apellido, genero, nacionalidad, direccion, telefono, tipo, codigoUsuario);
//		this.misPlanesAd = new ArrayList<PlanAdquirido>();
//		this.misFacturas = new ArrayList<Factura>();
//	}

	public Cliente(String cedula, String nombre, String apellido, String snombre, String sapellido, String genero,
			String telefono, int ciudadNacim, String calle, String numCasa, String direccion) {
		super(cedula, nombre, apellido, snombre, sapellido, genero, telefono, ciudadNacim, calle, numCasa, direccion);
		this.misPlanesAd = new ArrayList<PlanAdquirido>();
		this.misFacturas = new ArrayList<Factura>();
	}

	public ArrayList<Factura> getMisFacturas() {
		return misFacturas;
	}
	
	
	public ArrayList<PlanAdquirido> getMisPlanesAd() {
		return misPlanesAd;
	}

	public void insertarPlanAd(PlanAdquirido auxPlanAd) {
		misPlanesAd.add(auxPlanAd);
	}
	
	public void eliminarPlanAd(PlanAdquirido auxPlanAd) {
	    if(auxPlanAd != null) {
	      if(auxPlanAd.isPagoPendiente()) {
	         JOptionPane.showMessageDialog(null, "Favor completar el pago de su factura.");
	      }else {
	    	  misPlanesAd.remove(auxPlanAd); 
	      }
	    }
	}
	
	public void addFactura(Factura fac) {
		misFacturas.add(fac);
	}
}