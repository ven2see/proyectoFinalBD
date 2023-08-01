package logico;

import java.util.ArrayList;

public class P_Trabajador extends Persona{

	private static final long serialVersionUID = 1L;
	private ArrayList<String> actividades;
	private Cuenta cuenta;
	private int puntosGanados;
	private float salario;
	private int cantHorasTXmes;



	public P_Trabajador(String cedula, String nombre, String apellido, String snombre, String sapellido, String genero,
			String telefono, int ciudadNacim, String calle, String numCasa, String direccion) {
		super(cedula, nombre, apellido, snombre, sapellido, genero, telefono, ciudadNacim, calle, numCasa, direccion);
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