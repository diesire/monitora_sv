package es.uniovi.miw.monitora.server.core.impl.agente;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class AddAgente implements Command {

	private Agente agente;

	public AddAgente(Agente agente) {
		this.agente = agente;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			Jpa.getManager().persist(agente);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}

}
