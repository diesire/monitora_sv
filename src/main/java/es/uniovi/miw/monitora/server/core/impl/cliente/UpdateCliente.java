package es.uniovi.miw.monitora.server.core.impl.cliente;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class UpdateCliente implements Command {

	private Cliente cli;

	public UpdateCliente(Cliente cli) {
		this.cli = cli;
	}

	@Override
	public Object execute() throws BusinessException {
		Jpa.getManager().merge(cli);
		return null;
	}
}
