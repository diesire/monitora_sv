package es.uniovi.miw.monitora.server.core.impl.destino;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.Destino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class CreateDestino implements Command {

	private Cliente cli;

	public CreateDestino(Cliente cli) {
		this.cli = cli;
	}

	@Override
	public Object execute() throws BusinessException {
		Destino dest = new Destino();
		cli.addDestino(dest);
		return dest;
	}

}
