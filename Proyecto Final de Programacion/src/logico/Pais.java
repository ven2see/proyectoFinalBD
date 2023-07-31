package logico;

import java.util.ArrayList;

public class Pais {
	protected String IDPais;
	protected String NombrePais;
	protected ArrayList<Ciudad> ciudades;
	
	public Pais(String iDPais, String nombrePais, ArrayList<Ciudad> ciudades) {
		super();
		IDPais = iDPais;
		NombrePais = nombrePais;
		this.ciudades = ciudades;
	}

	public String getIDPais() {
		return IDPais;
	}

	public void setIDPais(String iDPais) {
		IDPais = iDPais;
	}

	public String getNombrePais() {
		return NombrePais;
	}

	public void setNombrePais(String nombrePais) {
		NombrePais = nombrePais;
	}

	public ArrayList<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(ArrayList<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}
	
	
	
}
