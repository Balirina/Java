package juegoPalabra;

public class JuegoPalabra {

	private String palabra;
	private String palabraDesordenada;
	
	public JuegoPalabra(String palabra)
	{
		this.palabra=palabra;
	}
	
	public void crearDesordenada()
	{
		palabraDesordenada="";
		int tam=palabra.length();
		String[] palAux=new String[tam];
		for(int i=0; i<tam; i++)
		{
			int pos=(int)(Math.random()*tam);
			while(palAux[pos]!=null)
				pos=(int)(Math.random()*tam);
			palAux[pos]=""+palabra.charAt(i);
		}
		for(int i=0; i<tam; i++)
		{
			palabraDesordenada+=palAux[i];
		}
	}
	
	public static void main(String[] args) {
		JuegoPalabra juego=new JuegoPalabra("caracol");		
		juego.crearDesordenada();
		System.out.println(juego.palabraDesordenada);
		
	}

	public String getPalabra() {
		return palabra;
	}

	public String getPalabraDesordenada() {
		return palabraDesordenada;
	}
	
}
