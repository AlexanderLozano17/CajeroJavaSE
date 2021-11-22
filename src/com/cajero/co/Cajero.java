package com.cajero.co;

/**
 * 
 * @author Alexander Lozano
 *
 */
public interface Cajero {

	public double consultaSaldo();
	
	public void depositarDinero(double monto);
	
	public void retirarDinero(double monto);
	
	public void AdicionaHistrorialOperaciones(String Accion, String resultado);
	
}
