package es.uniovi.miw.monitora.server.core.impl.lineaCron;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.LineaCron;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class AddLineaCron implements Command {

	private LineaCron lCron;

	public AddLineaCron(LineaCron lCron) {
		this.lCron = lCron;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			Jpa.getManager().persist(lCron);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}

}
