package tanda1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Balirina
 * Clase que copia una imagen de 3 maneras: byte a byte, en bloques de bytes y utilizando solo dos operaciones
 *
 */
public class CopyImages {
	
	//metodo que copia byte a byte
	public static boolean copiarBytes(String nombre) throws IOException
	{
		boolean iguales=false;
		String aux=nombre.substring(0, nombre.lastIndexOf("."))+"_CPY"+nombre.substring(nombre.lastIndexOf("."));
		FileInputStream fis=new FileInputStream(nombre);
		FileOutputStream fos=new FileOutputStream(aux);
		while(fis.available()>0)
		{
			int byteleido=fis.read();
			fos.write(byteleido);
		}
		
		fis.close();
		fos.close();
		return iguales;
	}
	//metodo que copia en bloques
	public static void copiarBloques(String nombre) throws IOException
	{
		final int N=512;
		String aux=nombre.substring(0, nombre.lastIndexOf("."))+"_CPY"+nombre.substring(nombre.lastIndexOf("."));
		FileInputStream fis=new FileInputStream(nombre);
		FileOutputStream fos=new FileOutputStream(aux);
		byte[] bloque=new byte[N];
		int cant=fis.read(bloque);
		while(cant==N)
		{
			fos.write(bloque);
			cant=fis.read(bloque);
		}
		fos.write(bloque, 0, cant);
		fis.close();
		fos.close();
	}
	
	//metodo que copia usando 2 operaciones;
	public static void copiarEntero(String nombre) throws IOException
	{
		String aux=nombre.substring(0, nombre.lastIndexOf("."))+"_CPY"+nombre.substring(nombre.lastIndexOf("."));
		FileInputStream fis=new FileInputStream(nombre);
		FileOutputStream fos=new FileOutputStream(aux);
		int c=fis.available();
		byte[] cant=new byte[c];
		fis.read(cant);
		fos.write(cant);
		fis.close();
		fos.close();
	}
	
	public static void main(String[] args) throws IOException {
		copiarBytes("aaa.jpg");
		copiarBloques("bbb.JPG");
		copiarEntero("ccc.JPG");
		
	}

}
