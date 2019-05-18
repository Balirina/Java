package tanda1;

import java.io.Serializable;

/**
 * @author Balirina
 *Clase para crear un Comercial que tiene un telefono  movil como atributo de clase
 */
public class Comercial implements Serializable{

	private String nombre;
	private float salario;
	private TelefonoMovil telefono;
	
	public Comercial(String nombre, float salario, TelefonoMovil telefono) 
	{
		this.nombre = nombre;
		this.salario = salario;
		this.telefono = telefono;
	}
	
	public void ver()
	{
		System.out.println(nombre+" con el salario "+salario+" tiene el telefono :");
		telefono.ver();
	}
	public boolean trabajar()
	{
		if(telefono.llamar(15)==true)
		{
			salario+=10;
			return true;
		}
		return false;
	}
	public boolean trabajar(int g, int m)
	{
		if(telefono.llamar(m)==true)
		{
			salario+=g;
			return true;
		}
		return false;
	}

	public String getNombre() {
		return nombre;
	}

	public TelefonoMovil getTelefono() {
		return telefono;
	}

	
	
	
	
	
}
