package es.uniovi.miw.monitora.server.conf;

import es.uniovi.miw.monitora.server.core.AgenteService;
import es.uniovi.miw.monitora.server.core.ClienteService;
import es.uniovi.miw.monitora.server.core.impl.AgenteServiceImpl;
import es.uniovi.miw.monitora.server.core.impl.ClienteServiceImpl;

public class ServicesFactory {
	public static AgenteService getAgenteService() {
		return new AgenteServiceImpl();
	}
	
	public static ClienteService getClienteService() {
		return new ClienteServiceImpl();
	}
}
