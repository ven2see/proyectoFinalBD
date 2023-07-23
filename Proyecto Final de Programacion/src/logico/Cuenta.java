package logico;

import java.io.Serializable;

public class Cuenta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String password;
	private String usuario;
	
	public Cuenta(String password, String usuario) {
		super();
		this.password = password;
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public String getUsuario() {
		return usuario;
	}	
}
