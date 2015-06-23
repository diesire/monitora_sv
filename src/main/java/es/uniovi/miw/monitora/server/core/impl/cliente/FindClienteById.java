package es.uniovi.miw.monitora.server.core.impl.cliente;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.ClienteFinder;

public class FindClienteById implements Command {

	private Integer id;

	public FindClienteById(Integer id) {
		this.id = id;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			return ClienteFinder.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

}
