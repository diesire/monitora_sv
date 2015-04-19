package es.uniovi.miw.monitora.server.core.impl.destino;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Destino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class AddDestino implements Command {

	private Destino dest;

	public AddDestino(Destino dest) {
		this.dest = dest;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			Jpa.getManager().persist(dest);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}

}
