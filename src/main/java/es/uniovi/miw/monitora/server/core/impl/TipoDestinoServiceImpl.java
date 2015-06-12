package es.uniovi.miw.monitora.server.core.impl;

import es.uniovi.miw.monitora.server.core.TipoDestinoService;
import es.uniovi.miw.monitora.server.core.impl.tipoDestino.AddTipoDestino;
import es.uniovi.miw.monitora.server.core.impl.tipoDestino.CreateTipoDestino;
import es.uniovi.miw.monitora.server.core.impl.tipoDestino.DeleteTipoDestino;
import es.uniovi.miw.monitora.server.core.impl.tipoDestino.FindTipoDestinoById;
import es.uniovi.miw.monitora.server.core.impl.tipoDestino.UpdateTipoDestino;
import es.uniovi.miw.monitora.server.model.TipoDestino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class TipoDestinoServiceImpl implements TipoDestinoService {
	private CommandExecutor executor = new CommandExecutor();

	@Override
	public void addTipoDestino(TipoDestino tDes) throws BusinessException {
		executor.execute(new AddTipoDestino(tDes));
	}

	@Override
	public void updateTipoDestino(TipoDestino tDes) throws BusinessException {
		executor.execute(new UpdateTipoDestino(tDes));
	}

	@Override
	public TipoDestino findTipoDestinoById(Integer id) throws BusinessException {
		return (TipoDestino) executor.execute(new FindTipoDestinoById(id));
	}

	@Override
	public void deleteTipoDestino(Integer id) throws BusinessException {
		executor.execute(new DeleteTipoDestino(id));
	}

	@Override
	public TipoDestino createTipoDestino(String nombre) throws BusinessException {
		return (TipoDestino) executor.execute(new CreateTipoDestino(nombre));
	}

}
