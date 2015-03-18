package es.uniovi.miw.monitora.server.core;

import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public interface AgenteService {
	
	void addAgente(Agente agente) throws BusinessException;

	void updateAgente(Agente agente) throws BusinessException;

	Agente findAgenteById(Integer agenteId) throws BusinessException;

	void deleteAgente(Integer agenteId) throws BusinessException;
}
