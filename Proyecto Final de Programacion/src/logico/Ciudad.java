package logico;

public class Ciudad {
	protected String IDCiudad;
	protected String nombreCiudad;
	protected String IDPais;
	public Ciudad(String iDCiudad, String nombreCiudad, String pais) {
		super();
		IDCiudad = iDCiudad;
		this.nombreCiudad = nombreCiudad;
		this.IDPais = pais;
	}
	public String getIDCiudad() {
		return IDCiudad;
	}
	public void setIDCiudad(String iDCiudad) {
		IDCiudad = iDCiudad;
	}
	public String getNombreCiudad() {
		return nombreCiudad;
	}
	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}
	public String getIDPais() {
		return IDPais;
	}
	public void setIDPais(String iDPais) {
		IDPais = iDPais;
	}
	
	
	
}
