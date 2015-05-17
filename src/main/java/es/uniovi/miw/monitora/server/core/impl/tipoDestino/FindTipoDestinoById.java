package es.uniovi.miw.monitora.server.core.impl.tipoDestino;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.TipoDestinoFinder;

public class FindTipoDestinoById implements Command {

	private Integer id;

	public FindTipoDestinoById(Integer id) {
		this.id = id;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			return TipoDestinoFinder.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

}
