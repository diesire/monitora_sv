package es.uniovi.miw.monitora.server.persistence;

import es.uniovi.miw.monitora.server.model.LineaCron;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class LineaCronFinder {
	public static LineaCron findById(Integer id) throws BusinessException {
		try {
			return Jpa.getManager().find(LineaCron.class, id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
}
