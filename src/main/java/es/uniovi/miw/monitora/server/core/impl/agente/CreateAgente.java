package es.uniovi.miw.monitora.server.core.impl.agente;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.Destino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class CreateAgente implements Command {


	private Destino destino;

	public CreateAgente(Destino destino) {
		this.destino = destino;
	}

	@Override
	public Object execute() throws BusinessException {
		Agente ag = new Agente();
		destino.getCliente().addAgente(ag);
		ag.addDestino(destino);		
		return ag;
	}

}
