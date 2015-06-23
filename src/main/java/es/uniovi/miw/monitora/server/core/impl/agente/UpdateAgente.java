package es.uniovi.miw.monitora.server.core.impl.agente;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class UpdateAgente implements Command {

	private Agente agente;

	public UpdateAgente(Agente agente) {
		this.agente = agente;
	}

	@Override
	public Object execute() throws BusinessException {
		Jpa.getManager().merge(agente);
		return null;
	}

}
