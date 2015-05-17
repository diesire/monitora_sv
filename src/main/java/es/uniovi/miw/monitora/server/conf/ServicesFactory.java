package es.uniovi.miw.monitora.server.conf;

import es.uniovi.miw.monitora.server.core.AgenteService;
import es.uniovi.miw.monitora.server.core.ClienteService;
import es.uniovi.miw.monitora.server.core.ConsultaService;
import es.uniovi.miw.monitora.server.core.DestinoService;
import es.uniovi.miw.monitora.server.core.InformeConsultaService;
import es.uniovi.miw.monitora.server.core.InformeService;
import es.uniovi.miw.monitora.server.core.MonitoraServerService;
import es.uniovi.miw.monitora.server.core.SnapshotService;
import es.uniovi.miw.monitora.server.core.TipoDestinoService;
import es.uniovi.miw.monitora.server.core.impl.AgenteServiceImpl;
import es.uniovi.miw.monitora.server.core.impl.ClienteServiceImpl;
import es.uniovi.miw.monitora.server.core.impl.ConsultaServiceImpl;
import es.uniovi.miw.monitora.server.core.impl.DestinoServiceImpl;
import es.uniovi.miw.monitora.server.core.impl.InformeConsultaServiceImpl;
import es.uniovi.miw.monitora.server.core.impl.InformeServiceImpl;
import es.uniovi.miw.monitora.server.core.impl.MonitoraServer;
import es.uniovi.miw.monitora.server.core.impl.SnapshotServiceImpl;
import es.uniovi.miw.monitora.server.core.impl.TipoDestinoServiceImpl;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

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

	public static InformeService getInformeService() {
		return new InformeServiceImpl();
	}

	public static MonitoraServerService getMonitoraServerService()
			throws BusinessException {
		return new MonitoraServer();
	}

	public static InformeConsultaService getInformeConsultaService() {
		return new InformeConsultaServiceImpl();
	}

	public static ConsultaService getConsultaService() {
		return new ConsultaServiceImpl();
	}

	public static SnapshotService getSnapshotService() {
		return new SnapshotServiceImpl();
	}

	public static TipoDestinoService getTipoDestinoService() {
		return new TipoDestinoServiceImpl();
	}
}
