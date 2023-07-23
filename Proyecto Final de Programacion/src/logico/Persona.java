package logico;

import java.io.Serializable;

public class Persona implements Serializable{
	
	private static final long serialVersionUID = 1L;
	protected String cedula;
	protected String nombre;
	protected String apellido;
	protected String genero;
	protected String nacionalidad;
	protected String direccion;
	protected String telefono;

	public Persona (String cedula, String nombre, String apellido,String genero, String nacionalidad, String direccion, String telefono, String tipo, String codigoUsuario) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.genero = genero;
		this.nacionalidad = nacionalidad;
		this.direccion = direccion;
		this.telefono = telefono;
		this.apellido = apellido;
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

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
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


}
