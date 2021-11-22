package com.cajero.co;

import java.util.Map;

/**
 * 
 * @author  Alexander Lozano
 *
 */
public class Cuenta implements Cajero {

	private String usuarioCuenta;
	private double saldo = 40500;
	private Map<String, String> historial = null;
	
	public Cuenta() {
		super();
	}

	public Cuenta(String usuarioCuenta, double saldo) {
		super();
		this.usuarioCuenta = usuarioCuenta;
		this.saldo = saldo;
	}

	public String getUsuarioCuenta() {
		return usuarioCuenta;
	}

	public void setUsuarioCuenta(String usuarioCuenta) {
		this.usuarioCuenta = usuarioCuenta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Map<String, String> getHistorial() {
		return historial;
	}

	public void setHistorial(String clave, String valor) {
		this.historial.put(clave, valor);
	}

	@Override
	public double consultaSaldo() {
		// TODO Auto-generated method stub
		return getSaldo();
	}

	@Override
	public void depositarDinero(double monto) {
		// TODO Auto-generated method stub
		double montoFinal = getSaldo() + monto;
		setSaldo(montoFinal);		
	}

	@Override
	public void retirarDinero(double monto) {
		// TODO Auto-generated method stub
		double montoFinal = getSaldo() - monto;
		setSaldo(montoFinal);	
	}

	@Override
	public void AdicionaHistrorialOperaciones(String Accion, String resultado) {
		// TODO Auto-generated method stub
		setHistorial(Accion, resultado);
	}
		
}
