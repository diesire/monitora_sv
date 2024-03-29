package es.uniovi.miw.monitora.server.core;

import java.util.List;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.Destino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public interface AgenteService {
	
	Agente createAgente(Destino des) throws BusinessException;
	
	void addAgente(Agente agente) throws BusinessException;

	void updateAgente(Agente agente) throws BusinessException;

	Agente findAgenteById(Integer agenteId) throws BusinessException;

	void deleteAgente(Integer agenteId) throws BusinessException;

	List<Agente> findAll() throws BusinessException;
}
