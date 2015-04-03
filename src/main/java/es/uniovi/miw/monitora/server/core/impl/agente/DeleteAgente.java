package es.uniovi.miw.monitora.server.core.impl.agente;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.AgenteFinder;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class DeleteAgente implements Command {

	private Integer agenteId;

	public DeleteAgente(Integer agenteId) {
		this.agenteId = agenteId;
	}

	@Override
	public Object execute() throws BusinessException {
		Agente ag = AgenteFinder.findById(agenteId);
		assertNotNull(ag);
		// assertCanBeDeleted(m); //TODO: check logic rules

		try {
			Jpa.getManager().remove(ag);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}

	private void assertNotNull(Agente ag) throws BusinessException {
		if (ag == null) {
			throw new BusinessException("El agente no existe");
		}
	}

}
