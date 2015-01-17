package es.uniovi.miw.monitora.server.core;

import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public interface AgenteService {

	void updateAgente(Agente agente) throws BusinessException;

	Agente findAgenteById(int agenteId) throws BusinessException;

}
