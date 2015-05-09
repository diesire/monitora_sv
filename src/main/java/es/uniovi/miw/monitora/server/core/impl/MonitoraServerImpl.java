package es.uniovi.miw.monitora.server.core.impl;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uniovi.miw.monitora.core.api.Ack;
import es.uniovi.miw.monitora.core.snapshot.Snapshot;
import es.uniovi.miw.monitora.server.conf.ServicesFactory;
import es.uniovi.miw.monitora.server.core.ClienteService;
import es.uniovi.miw.monitora.server.core.MonitoraServerService;
import es.uniovi.miw.monitora.server.core.impl.agente.FindAgenteById;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class MonitoraServerImpl implements MonitoraServerService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CommandExecutor executor = new CommandExecutor();

	@Override
	public Ack ping(int agenteId) throws BusinessException {
		logger.trace("Ping from", agenteId);
		return createAck(agenteId);
	}

	@Override
	public Agente getAgente(int agenteId) throws BusinessException {
		logger.trace("Agent from", agenteId);
		return (Agente) executor.execute(new FindAgenteById(agenteId));
	}

	@Override
	public void setSnapshot(int agenteId, Snapshot snapshot)
			throws BusinessException {
		// TODO Auto-generated method stub
	}

	private Ack createAck(int agenteId) {
		Ack ack = new Ack();
		ack.setUpdate(Calendar.getInstance());
		return ack;
	}
}
