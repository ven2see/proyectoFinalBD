
package logico;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;


public class PlanAdquirido implements Serializable{

	private static final long serialVersionUID = 1L;
	private String cedulaCliente;
	private String numCliente;
	private Plan plan;
	private Date fecha;
	private float pagoInicial;
	private float pagoMensual;
	private String switch1;
	private String codigo;
	
	private boolean facGen;
	private boolean PIniPend;
	private boolean pagoPendiente;

	public PlanAdquirido(String cedulaCliente, String numCliente, Date fecha, float pagoInicial, float pagoMensual, String codigo, Plan plan) {
		super();
		this.cedulaCliente = cedulaCliente;
		this.numCliente = numCliente;
		this.fecha = fecha;
		this.pagoInicial = pagoInicial;
		this.pagoMensual = pagoMensual;
		this.pagoPendiente = false;
		this.plan = plan;
		this.switch1 = "Activado";
		this.codigo = codigo;
		this.facGen = false;
		this.PIniPend=true;
	}

	public String getCedulaCliente() {
		return cedulaCliente;
	}


	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public float getPagoMensual() {
		return pagoMensual;
	}

	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

	public String getNumCliente() {
		return numCliente;
	}

	public void setNumCliente(String numCliente) {
		this.numCliente = numCliente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getPagoInicial() {
		return pagoInicial;
	}

	public void setPagoInicial(float pagoInicial) {
		this.pagoInicial = pagoInicial;
	}

	public float getpagoMensual() {
		return pagoMensual;
	}

	public void setpagoMensual(float pagoMensual) {
		this.pagoMensual = pagoMensual;
	}

	public boolean isPagoPendiente() {
		return pagoPendiente;
	}

	public void setPagoPendiente(boolean pagoPendiente) {
		this.pagoPendiente = pagoPendiente;
	}


	public String getSwitch1() {
		return switch1;
	}

	
	public String getCodigo() {
		return codigo;
	}

	public boolean isFacGen() {
		return facGen;
	}

	public void setFacGen(boolean facGen) {
		this.facGen = facGen;
	}

	public boolean isPIniPend() {
		return PIniPend;
	}

	public void setPIniPend(boolean pIniPend) {
		PIniPend = pIniPend;
	}

	public void setSwitch1(String switch1) {
		this.switch1 = switch1;
	}

	public float total() {
		float total=0;
		if(PIniPend) {
			total = pagoInicial+pagoMensual;
		}else {
			total = pagoMensual;
		}
		
		return total;
	}
}
