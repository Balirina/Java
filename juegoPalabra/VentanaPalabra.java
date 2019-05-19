package juegoPalabra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Balirina
 * Clase para simular el juego de adivinar una palabra.
 * En cada hueco si pinchamos Enter el color del textfield se pone en verde si se acerta, en caso contrario se pone en rojo.
 *
 */
public class VentanaPalabra extends JFrame{

	private JuegoPalabra juego;
	private JLabel[] letras;
	private ArrayList<JTextField> huecos;
	private String palabra;
	private String palabraDesord;
	private JButton boton;
	
	
	public VentanaPalabra()
	{
		juego=new JuegoPalabra("arcoiris");
		juego.crearDesordenada();
		palabra=juego.getPalabra();
		
		dibujar();
		tratarEventos();
		setSize(400,300);
		setVisible(true);
	}
	
	private void dibujar()
	{
		JPanel panelGeneral=new JPanel();
		panelGeneral.setLayout(new BoxLayout(panelGeneral, BoxLayout.Y_AXIS));
		palabraDesord=juego.getPalabraDesordenada();
		int tam=palabraDesord.length();
		letras=new JLabel[tam];
		JPanel panel1=new JPanel();
		panel1.setLayout(new FlowLayout());
		//Dibujar letras
		Font f = new Font("TimesRoman",Font.BOLD,25);
		for(int i=0; i<tam; i++)
		{
			JLabel lbl=new JLabel(""+palabraDesord.charAt(i));
			lbl.setPreferredSize(new Dimension(20,20));
			lbl.setFont(f);
			letras[i]=lbl;
			panel1.add(letras[i]);
		}
		//Dibujar huecos
		JPanel panel2=new JPanel();
		huecos=new ArrayList<JTextField>();
		//System.out.println("tamaó:"+tam);
		for(int i=0; i<tam; i++)
		{
			JTextField t=new JTextField();
			huecos.add(t);
			t.setPreferredSize(new Dimension(20,20));
			t.addActionListener(new EscuchadorLetraOK(i));			
			
		
			t.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {
					//borrar
					e.consume();
					Character key=e.getKeyChar();
					key=key.toLowerCase(key);
					if(key<'a' || key>'z')
					{
						e.consume();
					}
					else
					{
						t.setText(""+key);
					}
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					
				}
			});
			
			
			
			panel2.add(huecos.get(i));
			
		}
		//Añadir boton
		boton=new JButton("COMPROBAR");
		JPanel panel3=new JPanel();
		panel3.add(boton);
		
		panelGeneral.add(panel1);
		panelGeneral.add(panel2);
		panelGeneral.add(panel3);
		
		this.getContentPane().add(panelGeneral);
	}
	
	
	class EscuchadorLetraOK implements ActionListener {
		private int iPos;
		
		EscuchadorLetraOK (int iPos){
			this.iPos=iPos;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(huecos.get(iPos).getText().equals(palabra.charAt(iPos)+""))
			{
				huecos.get(iPos).setOpaque(true);
				huecos.get(iPos).setBackground(Color.green);	
			}
			else
			{
				huecos.get(iPos).setOpaque(true);
				huecos.get(iPos).setBackground(Color.red);	
			}
			
		}

	}
	
	private void redibujarLetras()
	{
		palabraDesord=juego.getPalabraDesordenada();
		int tam=palabraDesord.length();
		for(int i=0; i<tam; i++)
		{
			letras[i].setText(""+palabraDesord.charAt(i));
			huecos.get(i).setText("");
			huecos.get(i).setBackground(Color.white);
			letras[i].setOpaque(false);
		}
	}
	private void tratarEventos()
	{
		boton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean acertado=true;
				for(int i=0; i<huecos.size() && acertado==true; i++)
				{					
					if(String.valueOf(palabra.charAt(i)).equals(huecos.get(i).getText()))
					{						
						
						acertado=true;
					}
					else
						acertado=false;
				}
				if(acertado==true)
				{
					JOptionPane.showMessageDialog(null, "Enhorabuena. Palabra Adivinada");
					System.exit(0);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Error. Intentalo de nuevo");
					juego.crearDesordenada();
					redibujarLetras();
				
				}
				
			}
		});
		
	}
	public static void main(String[] args) {
		new VentanaPalabra();
	}
}

