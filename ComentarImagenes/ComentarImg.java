package comentarImagenes;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * @author Balirina
 * Clase que muestra las imagenes cuyas nombres estan en un ComboBox
 * y crea un fichero de texto en el que se guarda el comentario escrito por el usuario sobre la imagen elegida
 *
 */
public class ComentarImg extends JFrame implements ActionListener{
	
	private DefaultComboBoxModel<String> comboModelo;
	private JComboBox<String> comboImg;
	private JLabel lbl;
	private JCheckBox check;
	private JTextField texto;
	private JButton btn;
	private static final String CARPETA="imagenes";
	
	public ComentarImg()
	{
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				JOptionPane.showMessageDialog(null, "Hasta luego");
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		this.setLayout(null);
		dibujar();
		setSize(500,500);
		setVisible(true);
	
	}
	
	private ImageIcon redim (String fichImag)
	{
	            ImageIcon imIcon=new ImageIcon(fichImag);
	            Image im=imIcon.getImage();
	            Image im2= im.getScaledInstance(180, 200, 0);
	            return new ImageIcon(im2);

	}
	public void dibujar()
	{
		comboModelo=new DefaultComboBoxModel<String>();
		cargar_combo();
		comboImg=new JComboBox<String>(comboModelo);
		comboImg.setBounds(20,20,180,30);
		comboImg.addActionListener(new EscuchadorCombo());
		
		lbl=new JLabel();
		ImageIcon ii=redim(CARPETA+"/"+comboImg.getSelectedItem());
		lbl.setIcon(ii);
		lbl.setBounds(20,60,180,200);
		
		check=new JCheckBox("Guardar comentario");
		check.setBounds(40,290,150,20);
		check.setSelected(true);;
		
		texto=new JTextField();
		texto.setBounds(220,300,180,35);
		
		btn=new JButton("GUARDAR");
		btn.setBounds(100,360,100,35);
		btn.addActionListener(this);
		
		this.getContentPane().add(comboImg);
		this.getContentPane().add(lbl);
		this.getContentPane().add(check);
		this.getContentPane().add(texto);
		this.getContentPane().add(btn);
	}
	private void cargar_combo()
	{
		File f=new File(CARPETA);
		String[] contenido=f.list();
		for(int i=0; i<contenido.length; i++)
		{
			comboModelo.addElement(contenido[i]);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		if(check.isSelected())
		{
			String ruta=(String)comboImg.getSelectedItem();
			ruta=ruta.substring(ruta.indexOf('/')+1,ruta.lastIndexOf('.'))+".txt";
			try {
				String linea=ruta+" "+texto.getText();
				BufferedWriter bw=new BufferedWriter(new FileWriter(ruta,true));
				bw.write(linea);
				bw.newLine();
				bw.close();
			} catch (IOException e) {
				System.out.println("Error de entrada o salida");
			}
		}
		
	}
	
	class EscuchadorCombo implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ImageIcon ii=redim(CARPETA+"/"+comboImg.getSelectedItem());
			lbl.setIcon(ii);
			
		}
		
	}
	
	public static void main(String[] args) {
		String claveUser=JOptionPane.showInputDialog(null, "Introduzca contraseña");
		if(claveUser.equals("damocles"))
			new ComentarImg();
	}

	

}
