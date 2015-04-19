package es.uniovi.miw.monitora.server.conf;

import es.uniovi.miw.monitora.server.core.AgenteService;
import es.uniovi.miw.monitora.server.core.ClienteService;
import es.uniovi.miw.monitora.server.core.DestinoService;
import es.uniovi.miw.monitora.server.core.impl.AgenteServiceImpl;
import es.uniovi.miw.monitora.server.core.impl.ClienteServiceImpl;
import es.uniovi.miw.monitora.server.core.impl.DestinoServiceImpl;

public class ServicesFactory {
	public static AgenteService getAgenteService() {
		return new AgenteServiceImpl();
	}
	
	public static ClienteService getClienteService() {
		return new ClienteServiceImpl();
	}
	
	public static DestinoService getDestinoService() {
		return new DestinoServiceImpl();
	}
}
