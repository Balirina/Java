package tanda1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Balirina
 * Clase que genera numeros random entre dos limites y las añade a ficheros.
 * Metodos para buscar un numero en el fichero y para visualizar el fichero.
 *
 */
public class Numeros {
	
	public static int generar1Num(int a, int b)
	{
		return a+(int)(Math.random()*(b-a+1));
	}
	public static ArrayList<Integer> generarNums(int inf, int sup, int cant)
	{
		ArrayList<Integer> nums=new ArrayList<Integer>();
		for(int i=0; i<cant; i++)		
		{
			int numero=generar1Num(inf, sup);
			while(nums.contains(numero))
				{
					numero=generar1Num(inf, sup);
				}
			nums.add(numero);
		}
		return nums;
	}
	public static void añade1Num(String fichero, int num) throws IOException
	{
		File f=new File(fichero);
		FileOutputStream fos=new FileOutputStream(f, true);
		
		fos.write(num);
		fos.close();
	}
	public static void añadeNums(String fichero, ArrayList<Integer > nums) throws IOException
	{
		File f=new File(fichero);
		FileOutputStream fos=new FileOutputStream(f,true);
		for(Integer i: nums)
		{
			fos.write(i);
		}
		fos.close();
	}
	
	public static int buscarEnFichero(String nombre, int pos) throws IOException
	{
		File f=new File(nombre);
		FileInputStream fis=new FileInputStream(f);
		if(pos<0 || pos>fis.available())
		{
			fis.close();
			return  -1;
		}
		fis.skip((int)pos-1);
		Integer numero=fis.read();
		fis.close();
		return numero;		
	}
	public static void verFichero(String nombre) throws IOException
	{
		File f=new File(nombre);
		FileInputStream fis=new FileInputStream(f);
		while(fis.available()>0)
		{
			System.out.print(fis.read()+" || ");
		}
		fis.close();
	}
	public static ArrayList<Integer> numerosEnPosImpar(String fichero) throws IOException
	{
		ArrayList<Integer>impares=new ArrayList<Integer>();
		FileInputStream fis=new FileInputStream(new File(fichero));
		while(fis.available()>0)
		{
			impares.add(fis.read());
			fis.read();
		}
		fis.close();
		return impares;
	}
	public static void main(String[] args) throws IOException {
		 ArrayList<Integer> arr= new ArrayList<Integer>();
		 arr.add(2);
		 arr.add(10);
		 arr.add(8);
		 arr.add(13);
		 arr.add(4);
		 añadeNums("fich1.bin", arr);
		 verFichero("fich1.bin");
		 
		añade1Num("fich1.bin", generar1Num(1, 5));
		 System.out.println("\n***********************");
		 verFichero("fich1.bin");
		 
		 añadeNums("fich1.bin", generarNums(1, 50, 5));
		 System.out.println("\n***********************");
		 verFichero("fich1.bin");
		 int num=buscarEnFichero("fich1.bin", 6);
		 if(num==-1)
			 System.out.println("Numero no encontrado");
		 else
			 System.out.println("\nEl numero en la posicion 6 es "+ num);
		 
		 int num2=buscarEnFichero("fich1.bin", 9);
		 if(num2==-1)
			 System.out.println("Numero no encontrado");
		 else
			 System.out.println("\nEl numero en la posicion 9 es "+ num2);
		 
		 FileInputStream fis=new FileInputStream(new File("fich1.bin"));
		int pos=fis.available();
		fis.close();
		int ultimo=buscarEnFichero("fich1.bin", pos);
		if(ultimo==-1)
			 System.out.println("Numero no encontrado");
		else
			System.out.println("\nEl ultimo numero es: "+ultimo);

		
		añadeNums("fich2.bin", generarNums(50, 70, 10));
		
		añadeNums("fich1.bin", numerosEnPosImpar("fich2.bin"));	 
		verFichero("fich1.bin");
		System.out.println("\n**************************");
		verFichero("fich2.bin");
		 
		 
	}

}
