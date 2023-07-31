package logico;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class Factura implements Serializable{

	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private PlanAdquirido miPlanAd;
	private LocalDate fechaGen;
	private Date fechaPagado;
	private String codigo;
	private String estado;
	private float pago;

	public Factura(Cliente cliente, LocalDate localDate, String codigo, float pago,PlanAdquirido pl) {
		super();
		this.cliente = cliente;
		this.fechaGen = localDate;
		this.fechaPagado = null;
		this.codigo = codigo;
		this.pago = pago;
		this.miPlanAd = null;
		this.estado = "";
		this.miPlanAd = pl;

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
	public PlanAdquirido getMiPlanAd() {
		return miPlanAd;
	}

	public void setMiPlanAd(PlanAdquirido miPlanAd) {
		this.miPlanAd = miPlanAd;
	}

	public LocalDate getFecha() {
		return fechaGen;
	}

	public void setFecha(LocalDate fecha) {
		this.fechaGen = fecha;
	}

	public Date getFechaPago() {
		return fechaPagado;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPagado = fechaPago;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public float getPago() {
		return pago;
	}

	public LocalDate getFechaGen() {
		return fechaGen;
	}

	public Date getFechaPagado() {
		return fechaPagado;
	}

	public String isEstado() {
		return estado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setPago(float pago) {
		this.pago = pago;
	}
}