package es.uniovi.miw.monitora.server.core.impl.agente;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class CreateAgente implements Command {

	private Cliente cliente;

	public CreateAgente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public Object execute() throws BusinessException {
		Agente ag = new Agente();
		ag.setCliente(cliente);
		cliente.addAgente(ag);
		return ag;
	}

}
