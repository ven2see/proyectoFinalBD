package logico;

import java.util.ArrayList;

public class P_Trabajador extends Persona{

	private static final long serialVersionUID = 1L;
	private ArrayList<String> actividades;
	private Cuenta cuenta;
	private int puntosGanados;
	private float salario;
	private int cantHorasTXmes;

	public P_Trabajador(String cedula, String nombre, String apellido, String genero, String nacionalidad,
			String direccion, String telefono, String tipo, String codigoUsuario) {
		super(cedula, nombre, apellido, genero, nacionalidad, direccion, telefono, tipo, codigoUsuario);
		this.actividades = new ArrayList<String>();
		this.puntosGanados = 0;
		this.cuenta = null;
	}

	public ArrayList<String> getActividades() {
		return actividades;
	}

	public int getPuntosGanados() {
		return puntosGanados;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public float calSalario() {
		return (salario*cantHorasTXmes)+puntosGanados;
	}

	public void addCuenta(Cuenta cuentaAsignada) {
		cuenta = cuentaAsignada;
	}
}