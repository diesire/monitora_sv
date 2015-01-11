package es.uniovi.miw.monitora.server.core.impl.agente;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.AgenteFinder;

public class FindAgenteById implements Command {

	private int id;

	public FindAgenteById(int id) {
		this.id = id;
	}

	@Override
	public Object execute() throws BusinessException {
		Agente a = AgenteFinder.findById(id);
		return a;
	}

}
