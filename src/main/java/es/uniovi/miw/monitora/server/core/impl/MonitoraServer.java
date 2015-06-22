package es.uniovi.miw.monitora.server.core.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uniovi.miw.monitora.core.api.Ack;
import es.uniovi.miw.monitora.server.conf.PersistenceFactory;
import es.uniovi.miw.monitora.server.conf.ServicesFactory;
import es.uniovi.miw.monitora.server.core.ClienteService;
import es.uniovi.miw.monitora.server.core.MonitoraServerService;
import es.uniovi.miw.monitora.server.core.impl.agente.FindAgenteById;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.Snapshot;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.PersistenceService;

public class MonitoraServer implements MonitoraServerService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CommandExecutor executor = new CommandExecutor();
	private PersistenceService persistence;

	public MonitoraServer() throws BusinessException {
		persistence = PersistenceFactory.getPersistenceService();
	}

	@Override
	public Ack ping(int agenteId) throws BusinessException {
		logger.trace("Ping from", agenteId);
		return createAck(agenteId);
	}

	@Override
	public Agente getAgente(int agenteId) throws BusinessException {
		logger.trace("Agent from", agenteId);
		return (Agente) executor.execute(new FindAgenteById(agenteId));
		// FIXME call service layer
	}

	@Override
	public List<Agente> getAgentes() throws BusinessException {
		logger.trace("Agentes");
		return ServicesFactory.getAgenteService().findAll();
	}

	@Override
	public void setSnapshot(int agenteId, Snapshot snapshot)
			throws BusinessException {
		// FIXME Auto-generated method stub

		System.err.println("--------------- TODO ----------------");
	}

	private Ack createAck(int agenteId) {
		Ack ack = new Ack();
		// TODO check if agente exists
		ack.setUpdate(Calendar.getInstance());
		return ack;
	}

	@Override
	public void start() throws BusinessException {
		logger.trace("Start server");
		persistence.start();
	}

	@Override
	public void stop() throws BusinessException {
		logger.trace("Stop server");
		persistence.stop();
	}
}
