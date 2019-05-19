package restaurante;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VaciarPedidoListener implements ActionListener {

	private Restaurante owner;
	public VaciarPedidoListener(Restaurante r) {
		this.owner=r;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		owner.vaciarPedido();

	}

}
