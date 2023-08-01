package logico;

import java.io.Serializable;

public class Plan implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String nombre;
	private String cantCanales;
	private String cantMinutos;
	private String cantInternet;
	private String estado;
	private float precioInicial;
	private float precioMensual;
    private float dineroGenerado;
    private int cantVentas = 0;

	public Plan( String id, String nombre, String cantCanales, String cantMinutos, String cantInternet,
			float precioInicial, float precioMensual, String estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cantCanales = cantCanales;
		this.cantMinutos = cantMinutos;
		this.cantInternet = cantInternet;
		this.precioInicial = precioInicial;
		this.precioMensual = precioMensual;
		this.estado = estado;
		this.dineroGenerado = 0;
	}

	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}



	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCantCanales() {
		return cantCanales;
	}

	public void setCantCanales(String cantCanales) {
		this.cantCanales = cantCanales;
	}

	public String getCantMinutos() {
		return cantMinutos;
	}

	public void setCantMinutos(String cantMinutos) {
		this.cantMinutos = cantMinutos;
	}

	public String getCantInternet() {
		return cantInternet;
	}

	public void setCantInternet(String cantInternet) {
		this.cantInternet = cantInternet;
	}

	public float getPrecioInicial() {
		return precioInicial;
	}

	public void setPrecioInicial(float precioInicial) {
		this.precioInicial = precioInicial;
	}

	public float getPrecioMensual() {
		return precioMensual;
	}

	public void setPrecioMensual(float precioMensual) {
		this.precioMensual = precioMensual;
	}
	
	public int getCantVentas() {
		return cantVentas;
	}

	public float getDineroGenerado() {
		return dineroGenerado;
	}

	public void setDineroGenerado(float dineroGenerado) {
		this.dineroGenerado = dineroGenerado;
	}
	
	public void cantVentasPlan(Plan auxPlan) {
		if(auxPlan != null) {
           auxPlan.cantVentas = auxPlan.cantVentas + 1;
		}
	}
}