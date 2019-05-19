package cambiarMayusculas;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author Balirina
 * Ventana que trata el texto introducido por el usuario
 * Los tratamientos son: vaciar el texto, invertir el texto o cambiarlo a mayusculas de 3 modos distintos.
 *
 */
public class Ventana extends JFrame implements ActionListener{
	
	private JLabel lbl;
	private JTextArea texto;
	private JPanel butons;
	
	public Ventana()
	{
		this.setTitle("Probando");
		dibujar();
		setSize(500,500);
		setVisible(true);
	}
	
	public JTextArea getTexto() {
		return texto;
	}

	public void setTexto(JTextArea texto) {
		this.texto = texto;
	}
	
	private void dibujar()
	{
		Container container=this.getContentPane();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		lbl=new JLabel("Escriba aqui el texto:");
		texto=new JTextArea();
		JScrollPane scroll=new JScrollPane(texto, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		butons=new JPanel();
		JButton btn1=new JButton("VACIAR");
		JButton btn2=new JButton("MAYUSCULAS");
		JButton btn3=new JButton("INVERTIR");
		butons.add(btn1);
		butons.add(btn2);
		butons.add(btn3);
		
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				texto.setText("");
			}
		});
		
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				String inverso="";
				String contenido=texto.getText();
				for(int i=contenido.length()-1; i>=0; i--)
				{
					inverso+=contenido.charAt(i);
					//contenido=contenido.substring(0, i);
					
				}
				texto.setText(inverso);
				
			}
		});
		
		btn2.addActionListener(this);
		
		this.getContentPane().add(lbl);
		this.getContentPane().add(scroll);
		this.getContentPane().add(butons);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ComboDialogo cd=new ComboDialogo(this);
		//llega aqui cuando se ha elegido un radio
		
	}
	public static void main(String[] args) {
		new Ventana();
	}

	

}
