package logico;

import java.util.ArrayList;

public class P_Administrador extends Persona {


	private static final long serialVersionUID = 1L;
	private float sueldoBruto;
	private float salario;
	private int cantHorasTXmes;
	private ArrayList<String> actividades;
	private Cuenta cuenta;
	private int puntosGanados;
	private int gradoPermiso; // 1 o 2

	public P_Administrador(String cedula, String nombre, String apellido, String genero, String nacionalidad,
			String direccion, String telefono, String tipo, String codigoUsuario) {
		super(cedula, nombre, apellido, genero, nacionalidad, direccion, telefono, tipo, codigoUsuario);
		this.actividades = new ArrayList<String>();
		this.puntosGanados=0;
		this.gradoPermiso = 1;
		this.cantHorasTXmes=0;
		this.cuenta = null;
	}

	public float getSueldoBruto() {
		return sueldoBruto;
	}

	public ArrayList<String> getActividades() {
		return actividades;
	}

	public int getPuntosGanados() {
		return puntosGanados;
	}


	public float calSalario() {
		return (salario*cantHorasTXmes)+puntosGanados;
	}

	public int getGradoPermiso() {
		return gradoPermiso;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void addCuenta(Cuenta cuentaAsignada) {
		cuenta = cuentaAsignada;
	}
}