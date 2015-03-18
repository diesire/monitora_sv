package es.uniovi.miw.monitora.server.core.impl;

import es.uniovi.miw.monitora.server.core.AgenteService;
import es.uniovi.miw.monitora.server.core.impl.CommandExecutor;
import es.uniovi.miw.monitora.server.core.impl.agente.DeleteAgente;
import es.uniovi.miw.monitora.server.core.impl.agente.FindAgenteById;
import es.uniovi.miw.monitora.server.core.impl.agente.AddAgente;
import es.uniovi.miw.monitora.server.core.impl.agente.UpdateAgente;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class AgenteServiceImpl implements AgenteService {
	private CommandExecutor executor = new CommandExecutor();

	@Override
	public void updateAgente(Agente agente) throws BusinessException {
		executor.execute(new UpdateAgente(agente));
	}

	@Override
	public Agente findAgenteById(Integer agenteId) throws BusinessException {
		return (Agente) executor.execute(new FindAgenteById(agenteId));
	}

	@Override
	public void addAgente(Agente agente) throws BusinessException {
		executor.execute(new AddAgente(agente));		
	}

	@Override
	public void deleteAgente(Integer agenteId) throws BusinessException {
		executor.execute(new DeleteAgente(agenteId));		
	}

}
