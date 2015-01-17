package es.uniovi.miw.monitora.server.conf;

import es.uniovi.miw.monitora.server.core.AgenteService;
import es.uniovi.miw.monitora.server.core.impl.AgenteServiceImpl;

public class ServicesFactory {
	public static AgenteService getAgenteService() {
		return new AgenteServiceImpl();
	}
}
