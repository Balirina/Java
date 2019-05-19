package ecuacion2grado;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Balirina
 * Clase que calcula las salociones de una ecuacion grafica de 2 grado
 * cuyos coeficientes se rellenan por el usuario
 *
 */
public class EcuGrafica extends JFrame implements ActionListener{

	private JTextField coef1;
	private JTextField coef2;
	private JTextField coef3;
	private JLabel sol1;
	private JLabel sol2;
	private JButton boton;
	
	public EcuGrafica()
	{
		dibujar();
		tratarEventos();
		this.setSize(400,150);
		this.setVisible(true);
	}
	private void dibujar()
	{
		this.setLayout(new BorderLayout());
		
		JPanel p1=new JPanel();
		p1.setLayout(new GridLayout(1,0));
		
		p1.add(coef1=new JTextField());
		p1.add(new JLabel(" x2+ "));
		p1.add(coef2=new JTextField());
		p1.add(new JLabel(" x+ "));
		p1.add(coef3=new JTextField());
		p1.add(new JLabel(" =0"));
		
		JPanel p3=new JPanel();
		boton=new JButton("RESOLVER");
		p3.add(boton);
		
		JPanel p2=new JPanel();
		p2.add(new JLabel("Solucion 1: "));
		p2.add(sol1=new JLabel());
		p2.add(new JLabel("Solucion 2: "));
		p2.add(sol2=new JLabel());
		
		this.getContentPane().add("North",p1);
		this.getContentPane().add("Center",p3);
		this.getContentPane().add("South",p2);
	}
	private void tratarEventos()
	{
		boton.addActionListener(this); 
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		double[] sol=new double[2];
		try {
		double a=Double.parseDouble(coef1.getText());
		double b=Double.parseDouble(coef2.getText());
		double c=Double.parseDouble(coef3.getText());		
			if(a==0)
				throw new RaizException("Denominador 0");
			double delta=Math.pow(b, 2)-4*a*c;
			if(delta<0)
				throw new RaizException("Raiz negativa");
			sol[0]=(-b+Math.sqrt(delta))/2*a;
			sol[1]=(-b-Math.sqrt(delta))/2*a;
			
			if(sol!=null)
			{
				sol1.setText(String.valueOf(sol[0]));
				sol2.setText(String.valueOf(sol[1]));
			}
		}catch(RaizException  ex2)
		{
			JOptionPane.showMessageDialog(null, "Error "+ex2.getMessage());
			vaciar();
		}
		catch(NumberFormatException ex)
		{
			JOptionPane.showMessageDialog(null, "Debes rellenar con 3 enteros");
			vaciar();
		}
		
		
	}
	private double[] resolverEc()
	{
		double[] sol=new double[2];
		try {
		double a=Double.parseDouble(coef1.getText());
		double b=Double.parseDouble(coef2.getText());
		double c=Double.parseDouble(coef3.getText());		
			if(a==0)
				throw new RaizException("Denominador 0");
			double delta=Math.pow(b, 2)-4*a*c;
			if(delta<0)
				throw new RaizException("Raiz negativa");
			sol[0]=(-b+Math.sqrt(delta))/2*a;
			sol[1]=(-b-Math.sqrt(delta))/2*a;
		}catch(RaizException  ex2)
		{
			JOptionPane.showMessageDialog(null, "Error "+ex2.getMessage());
			vaciar();
		}
		catch(NumberFormatException ex)
		{
			JOptionPane.showMessageDialog(null, "Debes rellenar con 3 enteros");
			vaciar();
		}
		return sol;
	}
	private void vaciar()
	{
		coef1.setText("");
		coef2.setText("");
		coef3.setText("");
		sol1.setText("");
		sol2.setText("");
	}
	public static void main(String[] args) {
		new EcuGrafica();
	}
	
}
