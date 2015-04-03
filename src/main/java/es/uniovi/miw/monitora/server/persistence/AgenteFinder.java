package es.uniovi.miw.monitora.server.persistence;

import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class AgenteFinder {
	public static Agente findById(Integer id) throws BusinessException {
		try {
			return Jpa.getManager().find(Agente.class, id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
}
