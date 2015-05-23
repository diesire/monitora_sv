package es.uniovi.miw.monitora.server.core.impl.planificacion;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Planificacion;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class UpdatePlanificacion implements Command {

	private Planificacion cli;

	public UpdatePlanificacion(Planificacion cli) {
		this.cli = cli;
	}

	@Override
	public Object execute() throws BusinessException {
		Jpa.getManager().merge(cli);
		return null;
	}
}
