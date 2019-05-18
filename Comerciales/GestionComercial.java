package tanda1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Balirina
 * Clase que crea un fichero a partir de un arrayList de Comerciale
 * busca y visualiza los Comerciales, les hace trabajar
 * y genera un fichero con los numeros de estos comerciales
 *
 */
public class GestionComercial implements Serializable{
	
	private String nomfich;
	
	public GestionComercial(String n)
	{
		nomfich=n;
	}
	
	public void guardaComerciales(ArrayList<Comercial> comerciales) throws IOException
	{
		FileOutputStream fis=new FileOutputStream(nomfich);
		ObjectOutputStream ois=new ObjectOutputStream(fis);
		Iterator <Comercial>it=comerciales.iterator();
		while(it.hasNext())
		{
			ois.writeObject(it.next());
		}
		ois.writeObject(null);
		ois.close();
	}
	public void verComerciales() throws IOException, ClassNotFoundException
	{
		FileInputStream fis=new FileInputStream(nomfich);
		ObjectInputStream ois=new ObjectInputStream(fis);
		Comercial com=(Comercial)ois.readObject();
		while(com!=null)
		{
			com.ver();
			com=(Comercial)ois.readObject();
		}
		ois.close();
	}
	public Comercial buscaComecrial(String nomComer) throws IOException, ClassNotFoundException
	{
		FileInputStream fis=new FileInputStream(nomfich);
		ObjectInputStream ois=new ObjectInputStream(fis);
		Comercial com=(Comercial) ois.readObject();
		while(com!=null)
		{
			if(com.getNombre().equals(nomComer))
			{
				ois.close();
				return com;
			}
			com=(Comercial) ois.readObject();
		}
		ois.close();
		return com;
	}
	public void generaFichMoviles(String nomfich) throws IOException, ClassNotFoundException
	{
		ObjectOutputStream oosaux=new ObjectOutputStream(new FileOutputStream(nomfich));
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(this.nomfich));
		Comercial com=(Comercial)  ois.readObject();
		while(com!=null)
		{
			com.getTelefono().setSaldo(com.getTelefono().getSaldo()+10);
			oosaux.writeObject(com.getTelefono());
			com=(Comercial)  ois.readObject();
		}
		oosaux.writeObject(null);
		oosaux.close();
		ois.close();
		
	}
	public void trabajarTodos() throws IOException, IOException, ClassNotFoundException
	{
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(nomfich));
		ArrayList<Comercial>comerciales=new ArrayList<Comercial>();
		Comercial com=(Comercial) ois.readObject();
		while(com!=null)
		{
			com.trabajar();
			comerciales.add(com);
			com=(Comercial) ois.readObject();
		}
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(nomfich));
		Iterator<Comercial> it=comerciales.iterator();
		while(it.hasNext())
		{
			oos.writeObject(it.next());
		}
		oos.writeObject(null);
		ois.close();
		oos.close();
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		GestionComercial gc1=new GestionComercial("comerciales.obj");
		ArrayList<Comercial> comerc=new ArrayList<Comercial>();
		comerc.add(new Comercial("Antonio", 1530, new TelefonoMovil("699255144", 14)));
		comerc.add(new Comercial("Julio", 2200, new TelefonoMovil("622032032", 60)));
		comerc.add(new Comercial("Diana", 1850, new TelefonoMovil("654789789", 53)));
		comerc.add(new Comercial("Fran", 1965, new TelefonoMovil("636352410", 18)));
		comerc.add(new Comercial("Ana", 3575, new TelefonoMovil("987654321", 29)));
		gc1.guardaComerciales(comerc);
		gc1.verComerciales();
		
		System.out.println("--------El comercial que buscas es:----- \n");
		
		Comercial c1=gc1.buscaComecrial("Antonio");
		if(c1!=null)
			c1.ver();
		else
			System.out.println("El comercial no existe");
		
		Comercial c2=gc1.buscaComecrial("Atalia");
		if(c2!=null)
			c2.ver();
		else
			System.out.println("El comercial no existe");
		//ver telefonos
		gc1.generaFichMoviles("Moviles.obj");
		ObjectInputStream obj=new ObjectInputStream(new FileInputStream("Moviles.obj"));
		TelefonoMovil tel=(TelefonoMovil) obj.readObject();
		while(tel!=null)
		{
			tel.ver();
			tel=(TelefonoMovil) obj.readObject();
		}
		obj.close();
		
		gc1.trabajarTodos();
		System.out.println("\nVisualiar los comerciales despued de trabajar.");
		gc1.verComerciales();
		
		

	}

}
