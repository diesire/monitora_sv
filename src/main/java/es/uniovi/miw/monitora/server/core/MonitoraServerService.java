package es.uniovi.miw.monitora.server.core;

import es.uniovi.miw.monitora.core.api.Ack;
import es.uniovi.miw.monitora.core.snapshot.Snapshot;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public interface MonitoraServerService {

	Ack ping(int agenteId) throws BusinessException;

	void setSnapshot(int agenteId, Snapshot snapshot) throws BusinessException;

	Agente getAgente(int agenteId) throws BusinessException;

}
