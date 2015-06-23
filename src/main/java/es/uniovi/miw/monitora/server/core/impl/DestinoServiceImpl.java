package es.uniovi.miw.monitora.server.core.impl;

import es.uniovi.miw.monitora.server.core.DestinoService;
import es.uniovi.miw.monitora.server.core.impl.destino.AddDestino;
import es.uniovi.miw.monitora.server.core.impl.destino.CreateDestino;
import es.uniovi.miw.monitora.server.core.impl.destino.DeleteDestino;
import es.uniovi.miw.monitora.server.core.impl.destino.FindDestinoById;
import es.uniovi.miw.monitora.server.core.impl.destino.UpdateDestino;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.Destino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class DestinoServiceImpl implements DestinoService {
	private CommandExecutor executor = new CommandExecutor();

	@Override
	public Destino createDestino(Cliente cli) throws BusinessException {
		return (Destino) executor.execute(new CreateDestino(cli));
	}

	@Override
	public void addDestino(Destino dest) throws BusinessException {
		executor.execute(new AddDestino(dest));
	}

	@Override
	public void updateDestino(Destino dest) throws BusinessException {
		executor.execute(new UpdateDestino(dest));
	}

	@Override
	public Destino findDestinoById(Integer idDestino, Integer idCliente)
			throws BusinessException {
		if (idDestino == null || idCliente == null) {
			throw new BusinessException("Empty key");
		}
		return (Destino) executor.execute(new FindDestinoById(idDestino,
				idCliente));
	}

	@Override
	public void deleteDestino(Integer idDestino, Integer idCliente)
			throws BusinessException {
		executor.execute(new DeleteDestino(idDestino, idCliente));
	}

}
