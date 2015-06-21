package es.uniovi.miw.monitora.server.core.impl.lineaCron;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.LineaCron;
import es.uniovi.miw.monitora.server.model.Planificacion;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class CreateLineaCron implements Command {

	private Planificacion plan;

	public CreateLineaCron(Planificacion plan) {
		this.plan = plan;
	}

	@Override
	public Object execute() throws BusinessException {
		LineaCron lCron = new LineaCron();
		plan.addLineaCron(lCron);
		return lCron;
	}

}
