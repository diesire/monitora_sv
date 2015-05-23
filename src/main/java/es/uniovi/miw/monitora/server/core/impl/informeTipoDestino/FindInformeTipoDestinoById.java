package es.uniovi.miw.monitora.server.core.impl.informeTipoDestino;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.InformeTipoDestino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.InformeTipoDestinoFinder;

public class FindInformeTipoDestinoById implements Command {

	private Integer tDesId;
	private Integer infoId;

	public FindInformeTipoDestinoById(Integer infoId, Integer tDesId) {
		this.infoId = infoId;
		this.tDesId = tDesId;
	}

	@Override
	public Object execute() throws BusinessException {
		return InformeTipoDestinoFinder.findById(infoId, tDesId);
	}

}
