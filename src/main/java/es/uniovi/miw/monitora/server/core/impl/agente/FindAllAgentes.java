package es.uniovi.miw.monitora.server.core.impl.agente;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.AgenteFinder;

public class FindAllAgentes implements Command {

	@Override
	public Object execute() throws BusinessException {
		return AgenteFinder.findAll();
	}

}
