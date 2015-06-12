package es.uniovi.miw.monitora.server.persistence.util;

import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public interface PersistenceService {
	void start() throws BusinessException;
	void stop() throws BusinessException;
}
