package es.uniovi.miw.monitora.server.core.impl.informe;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class AddInforme implements Command {

	private Informe inf;

	public AddInforme(Informe inf) {
		this.inf = inf;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			Jpa.getManager().persist(inf);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}

}
