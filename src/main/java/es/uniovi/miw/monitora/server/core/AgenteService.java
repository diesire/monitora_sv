package es.uniovi.miw.monitora.server.core;

import java.util.List;
import java.util.Set;

import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public interface AgenteService {
	
	Agente createAgente(Cliente cliente) throws BusinessException;
	
	void addAgente(Agente agente) throws BusinessException;

	void updateAgente(Agente agente) throws BusinessException;

	Agente findAgenteById(Integer agenteId) throws BusinessException;

	void deleteAgente(Integer agenteId) throws BusinessException;

	List<Agente> findAll() throws BusinessException;
}
