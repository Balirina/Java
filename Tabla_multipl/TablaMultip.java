package tanda1;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TablaMultip extends JFrame{

	private JButton[] arrButons;
	private ArrayList<JLabel> labels;
	
	public TablaMultip()
	{
		crearVentana();
		tratarEventos();
		setSize(400,500);
		setVisible(true);
		
	}
	private ImageIcon redim (String fichImag)
	{
	            ImageIcon imIcon=new ImageIcon(fichImag);

	            Image im=imIcon.getImage();

	            Image im2= im.getScaledInstance(40, 40, 0);

	            return new ImageIcon(im2);

	}
	public void crearVentana()
	{
		JLabel lbl=new JLabel("Practice miltuplying tables");
		lbl.setFont(new Font("Arial", Font.BOLD,25));
		
		JPanel body=new JPanel(new GridLayout(8,2));
		
		/*arrButons=new JButton[] {new JButton("2"),new JButton("3"),new JButton("4"),new JButton("5"),
								new JButton("6"),new JButton("7"),new JButton("8"),new JButton("9")};
								*/
		arrButons=new JButton[8];
		for(int i=0; i<8; i++)
		{
			arrButons[i]=new JButton(i+2+"");
		}
		labels=new ArrayList<JLabel>();
		
		for(int i=0; i<arrButons.length; i++)
		{
			JLabel imglbl=new JLabel(redim("img/img.png"));
			labels.add(imglbl);
		}
		for(int i=0; i<arrButons.length; i++)
		{
			body.add(arrButons[i]);
			body.add(labels.get(i));
		}
		
		this.getContentPane().add(lbl,"North");
		this.getContentPane().add(body,"Center");
	}
	public void tratarEventos()
	{
		
		for (int i=0; i<arrButons.length; i++)
		{
			arrButons[i].addActionListener(new EscuchadorOperMultiplicar(i));
		}
	}
	
	class EscuchadorOperMultiplicar implements ActionListener
	{
		int pos;
		public EscuchadorOperMultiplicar(int i) {
			pos=i;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			try{JButton  btnaux=(JButton)e.getSource();
			int digito=Integer.parseInt(btnaux.getText());
			int digito2=(int)(Math.random()*10);
			String strResp=JOptionPane.showInputDialog(null, digito+"*"+digito2+"=");
			int respUser=Integer.parseInt(strResp);
			int resp=digito*digito2;
			if(resp==respUser)
			{
				JLabel lblaux=labels.get(pos);
				lblaux.setIcon(redim("img/img2.png"));
				btnaux.setEnabled(false);
			}
			}catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(null, "Introduce un numero");
			}
			
		}
		
	}
	public static void main(String[] args) {
		new TablaMultip();
	}
}
