package com.cajero.co;

/**
 * 
 * @author  Alexander Lozano
 *
 */
public class Cliente extends Cuenta{
	
	private String nombre;
	private String usuario;
	private String clave;
	
	public Cliente() {
		super();
	}

	public Cliente(String nombre, String usuario, String clave) {
		super();
		this.nombre = nombre;
		this.usuario = usuario;
		this.clave = clave;
	}

	@Override
	public String toString() {
		return "cliente [nombre=" + nombre + ", usuario=" + usuario + ", contraseña=" + clave + "]";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
}
