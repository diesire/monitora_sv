package es.uniovi.miw.monitora.server.core.impl;

import java.util.List;

import es.uniovi.miw.monitora.server.core.InformeTipoDestinoService;
import es.uniovi.miw.monitora.server.core.impl.informeTipoDestino.AddInformeTipoDestino;
import es.uniovi.miw.monitora.server.core.impl.informeTipoDestino.CreateInformeTipoDestino;
import es.uniovi.miw.monitora.server.core.impl.informeTipoDestino.DeleteInformeTipoDestino;
import es.uniovi.miw.monitora.server.core.impl.informeTipoDestino.FindInformeTipoDestinoById;
import es.uniovi.miw.monitora.server.core.impl.informeTipoDestino.UpdateInformeTipoDestino;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.InformeTipoDestino;
import es.uniovi.miw.monitora.server.model.TipoDestino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class InformeTipoDestinoServiceImpl implements InformeTipoDestinoService {
	private CommandExecutor executor = new CommandExecutor();

	@Override
	public void updateInformeTipoDestino(InformeTipoDestino infoTDes)
			throws BusinessException {
		executor.execute(new UpdateInformeTipoDestino(infoTDes));
	}

	@Override
	public InformeTipoDestino findInformeTipoDestinoById(Integer infoId,
			Integer tDesId) throws BusinessException {
		if (infoId == null || tDesId == null) {
			throw new BusinessException();
		}
		return (InformeTipoDestino) executor
				.execute(new FindInformeTipoDestinoById(infoId, tDesId));
	}

	@Override
	public void addInformeTipoDestino(InformeTipoDestino infoTDes)
			throws BusinessException {
		executor.execute(new AddInformeTipoDestino(infoTDes));
	}

	@Override
	public void deleteInformeTipoDestino(Integer infoId, Integer tDesId)
			throws BusinessException {
		executor.execute(new DeleteInformeTipoDestino(infoId, tDesId));
	}

	@Override
	public InformeTipoDestino createInformeTipoDestino(Informe info,
			TipoDestino tDes) throws BusinessException {
		return (InformeTipoDestino) executor
				.execute(new CreateInformeTipoDestino(info, tDes));
	}
}
