package es.uniovi.miw.monitora.server.core;

import java.util.List;
import java.util.Set;

import es.uniovi.miw.monitora.core.api.Ack;
import es.uniovi.miw.monitora.core.snapshot.Snapshot;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public interface MonitoraServerService {

	void start() throws BusinessException;

	void stop() throws BusinessException;

	Ack ping(int agenteId) throws BusinessException;

	void setSnapshot(int agenteId, Snapshot snapshot) throws BusinessException;

	Agente getAgente(int agenteId) throws BusinessException;

	List<Agente> getAgentes() throws BusinessException;

}
