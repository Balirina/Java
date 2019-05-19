package restaurante;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Balirina
 * Clase que imita una aplicacion para realizar pedidos en un restaurante
 * Los platos elegidos son de 3 tipos, y se muestran en una lista cada vez que eliges el tipo de plato.
 * cuando se elige un plato una ventana te pide el numero de unidades de este plato
 * los nombres de platos con los unidades se guardan en un fichero al pulsar el boton Añadir
 *
 */
public class Restaurante extends JFrame {

	private JComboBox<String> comboTipos;
	private JList<String> lstPlatos;
	private JList<PedidoPlato> lstPedido;
	private JButton botAnadir;
	private JButton botGuardar;
	private JButton reset;
	private HashMap<String, String[]> mapaPlatos;
	private DefaultListModel modeloPlatos, modeloPedido;
	
	
	public Restaurante()
	{
		crearMapa();
		dibujar();
		tratarEventos();
		setSize(400,300);
		setVisible(true);
	}
	
	private void crearMapa()
	{
		mapaPlatos=new HashMap<String, String[]>();
		mapaPlatos.put("Primero", new String[] {"Alubias","Garbanzos","Sopa","Pasta"});
		mapaPlatos.put("Segundo", new String[] {"Pollo","Carne de cerdo","Pescado"});
		mapaPlatos.put("Postre", new String[] {"Flan","Tarta de queso","Tiramisu","Helado"});
	}
	private void tratarEventos()
	{
		comboTipos.addActionListener(new EscComboTipos());
		botAnadir.addActionListener(
				new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent e) {
						String plato=lstPlatos.getSelectedValue();
						if(plato==null)
							JOptionPane.showMessageDialog(null,"Elige un plato");
						else {
							try {
							String strUds=JOptionPane.showInputDialog("Cuantas unidades de "+plato+" ?");
							int uds=Integer.parseInt(strUds);
							PedidoPlato pp=new PedidoPlato(plato, uds);
							if(!modeloPedido.contains(pp))
								modeloPedido.addElement(pp);
							else {
								int iAnterior=modeloPedido.indexOf(pp);
								PedidoPlato ppAnterior=(PedidoPlato) modeloPedido.elementAt(iAnterior);
								//sumar cantidad mas la nueva cant
								int totalUds=ppAnterior.getCantidad()+uds;
								modeloPedido.set(iAnterior, new PedidoPlato(plato, totalUds));	}
							}
							catch(Exception err)
							{
								JOptionPane.showMessageDialog(null, "Entrada no valida");
							}
					}			
					}});
		botGuardar.addActionListener(new GuardarPedidoListener(modeloPedido,"pedido.txt"));
		reset.addActionListener(new VaciarPedidoListener(this));
	}
	
	public void vaciarPedido()
	{
		modeloPedido.removeAllElements();
	}
	
	private void dibujar()
	{
		//layout por defecto: BorderLayout
		DefaultComboBoxModel modeloCombo=new DefaultComboBoxModel<String>();
		Iterator<String> it=mapaPlatos.keySet().iterator();
		while(it.hasNext())
		{
			String clave=it.next();
			modeloCombo.addElement(clave);
		}
		
		comboTipos=new JComboBox(modeloCombo);
		//
		JPanel panCentro=new JPanel(new FlowLayout());
		modeloPlatos=new DefaultListModel();
		cargarPlatosTipo("Primero");
		modeloPedido=new DefaultListModel();
		
		lstPlatos=new JList(modeloPlatos);
		botAnadir=new JButton(">>");
		lstPedido=new JList(modeloPedido);
		
		lstPlatos.setPreferredSize(new Dimension(150,200));
		lstPedido.setPreferredSize(new Dimension(150,200));
		panCentro.add(lstPlatos);
		panCentro.add(botAnadir);
		panCentro.add(lstPedido);
		
		JPanel panSur=new JPanel(new GridLayout(2,1));
		botGuardar=new JButton("Guardar pedido");
		reset=new JButton("Reset");
		panSur.add(botGuardar);
		panSur.add(reset);
		
		this.getContentPane().add(comboTipos,"North");
		this.getContentPane().add(panCentro,"Center");
		this.getContentPane().add(panSur,"South");
	}
	private void cargarPlatosTipo(String tipo)
	{
		modeloPlatos.removeAllElements();
		String[] platos=mapaPlatos.get(tipo);
		for(int i=0; i<platos.length; i++)
		{
			modeloPlatos.addElement(platos[i]);
		}
	}
	
	class EscComboTipos implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			cargarPlatosTipo((String) comboTipos.getSelectedItem());
			
		}
		
	}
	public static void main(String[] args) {
		new Restaurante();
	}
}
