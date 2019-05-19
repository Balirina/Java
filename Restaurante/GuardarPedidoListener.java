package restaurante;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.DefaultListModel;

public class GuardarPedidoListener implements ActionListener {

	private DefaultListModel<PedidoPlato>modelo;
	private String fich;
	
	public GuardarPedidoListener(DefaultListModel modelo, String fich) {
		this.modelo=modelo;
		this.fich=fich;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		PrintWriter pw;
		try {
			pw=new PrintWriter(fich);
			for(int i=0; i<modelo.size(); i++)
			{
				pw.println(modelo.getElementAt(i).toString());
			}
			pw.close();
		}catch(FileNotFoundException er)
		{
			System.out.println("Fichero no encontrado");
		}
		
	}

}
