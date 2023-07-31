package logico;

import java.io.Serializable;

public class Persona implements Serializable{
	
	private static final long serialVersionUID = 1L;
	protected String cedula;
	protected String nombre;
	protected String apellido;
	protected String Snombre;
	protected String Sapellido;
	protected String genero;
	protected String telefono;
	protected String ciudadNacim;
	protected String calle;
	protected String numCasa;
	protected String direccion;

	/*
	 * public Persona (String cedula, String nombre, String apellido,String genero,
	 * String nacionalidad, String direccion, String telefono, String tipo, String
	 * codigoUsuario) { super(); this.cedula = cedula; this.nombre = nombre;
	 * this.genero = genero; this.nacionalidad = nacionalidad; this.direccion =
	 * direccion; this.telefono = telefono; this.apellido = apellido; }
	 */
	
	public Persona(String cedula, String nombre, String apellido, String snombre, String sapellido, String genero,
			String telefono, String ciudadNacim, String calle, String numCasa, String direccion) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.Snombre = snombre;
		this.Sapellido = sapellido;
		this.genero = genero;
		this.telefono = telefono;
		this.ciudadNacim = ciudadNacim;
		this.calle = calle;
		this.numCasa = numCasa;
		this.direccion = direccion;
	}

	
	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}


	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getSnombre() {
		return Snombre;
	}


	public void setSnombre(String snombre) {
		Snombre = snombre;
	}


	public String getSapellido() {
		return Sapellido;
	}


	public void setSapellido(String sapellido) {
		Sapellido = sapellido;
	}


	public String getCiudadNacim() {
		return ciudadNacim;
	}


	public void setCiudadNacim(String ciudadNacim) {
		this.ciudadNacim = ciudadNacim;
	}


	public String getCalle() {
		return calle;
	}


	public void setCalle(String calle) {
		this.calle = calle;
	}


	public String getNumCasa() {
		return numCasa;
	}


	public void setNumCasa(String numCasa) {
		this.numCasa = numCasa;
	}

}
