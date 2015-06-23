package es.uniovi.miw.monitora.server.core.impl.lineaCron;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.LineaCron;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class UpdateLineaCron implements Command {

	private LineaCron lCron;

	public UpdateLineaCron(LineaCron lCron) {
		this.lCron = lCron;
	}

	@Override
	public Object execute() throws BusinessException {
		Jpa.getManager().merge(lCron);
		return null;
	}

}
