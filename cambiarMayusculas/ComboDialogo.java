package cambiarMayusculas;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ComboDialogo extends JDialog implements ActionListener{

	private Ventana owner;
	//private JLabel lbl;
	private JRadioButton[] radios;
	
	public ComboDialogo(Ventana owner)
	{
		this.setTitle("Tipos de Mayusculas");
		this.owner=owner;
		this.setModal(true);
		this.setLayout(new FlowLayout());
		JPanel panel=new JPanel();
		
		ButtonGroup bg=new ButtonGroup();
		radios=new JRadioButton[] {new JRadioButton("CAMBIAR A MAYUSCULAS"),
									new JRadioButton("Cambiar a mayusculas"),
									new JRadioButton("Cambiar A Mayusculas")};
		
		for(int i=0; i<radios.length; i++)
		{
			bg.add(radios[i]);
			panel.add(radios[i]);
		}
		radios[0].setSelected(true);
		panel.setBorder(BorderFactory.createTitledBorder("Elija tipo de alineamiento"));
		panel.setLayout(new GridLayout(0,1));
		this.getContentPane().add(panel);
		
		for(int i=0; i<radios.length; i++)
		{
			radios[i].addActionListener(this);
		}
		
		setSize(300,200);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String contenido=owner.getTexto().getText();
		if(radios[0].isSelected())
		{
			owner.getTexto().setText(contenido.toUpperCase());
			setVisible(false);
		}
		if(radios[1].isSelected())
		{
			char[] fraseCar=contenido.toCharArray();
			String cadResp="";
			cadResp+=Character.toUpperCase(fraseCar[0]);
			for(int i=1; i<fraseCar.length-1; i++)
			{
				if(fraseCar[i]=='.'||fraseCar[i]=='!')
				{
					cadResp+=fraseCar[i];
					cadResp+=Character.toUpperCase(fraseCar[i+1]);
					i++;
				}
				else
					cadResp+=Character.toLowerCase(fraseCar[i]);
			}
			//tratar los casos cuando la frase acaba en una letra o un ./!
			if(fraseCar[fraseCar.length-1]=='.'||fraseCar[fraseCar.length-1]=='!')
				cadResp+=fraseCar[fraseCar.length-1];
			else
				cadResp+=Character.toLowerCase(fraseCar[fraseCar.length-1]);
			owner.getTexto().setText(cadResp);
			setVisible(false);
		}
		if(radios[2].isSelected())
		{
			char[] fraseCar=contenido.toCharArray();
			String cadResp="";
			cadResp+=Character.toUpperCase(fraseCar[0]);
			for(int i=1; i<fraseCar.length-1; i++)
			{
				if(fraseCar[i]==' '|| fraseCar[i]=='.'||fraseCar[i]==',')
				{
					while(fraseCar[i]==' '|| fraseCar[i]=='.'||fraseCar[i]==',')
					{
						cadResp+=fraseCar[i];
						cadResp+=Character.toUpperCase(fraseCar[i+1]);
						i++;
					}
				}
				
				else
					cadResp+=Character.toLowerCase(fraseCar[i]);
			}
			//tratar los casos cuando la frase acaba en una letra o un ./!
			if(fraseCar[fraseCar.length-1]=='.'||fraseCar[fraseCar.length-1]=='!')
				cadResp+=fraseCar[fraseCar.length-1];
			else
				cadResp+=Character.toLowerCase(fraseCar[fraseCar.length-1]);
			owner.getTexto().setText(cadResp);
			setVisible(false);
		}
		
	}
		
	}
