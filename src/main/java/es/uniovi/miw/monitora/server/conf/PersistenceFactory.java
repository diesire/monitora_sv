package es.uniovi.miw.monitora.server.conf;

import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.PersistenceService;
import es.uniovi.miw.monitora.server.persistence.util.impl.HsqldbService;

public class PersistenceFactory {

	public static PersistenceService getPersistenceService() throws BusinessException {
		return HsqldbService.getInstance();
	}
}
