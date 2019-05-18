package tanda1;

import java.io.Serializable;

/**
 * @author Balirina
 *Clase para crear un telefono movil
 */
public class TelefonoMovil implements Serializable{
	private String numero;
	private float saldo;
	
	public TelefonoMovil(String n, float s)
	{
		numero=n;
		saldo=s;
	}
	
	public void ver()
	{
		System.out.println("El movil con el numero "+numero+" tiene el saldo "+saldo+"\n");
	}
	public void cargar(int s)
	{
		saldo+=s;
	}
	public boolean llamar(int minutos)
	{
		if(saldo>=minutos*2)
		{
			saldo-=minutos*2;
			return true;
		}
		return false;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public float getSaldo() {
		return saldo;
	}
	
	
}
