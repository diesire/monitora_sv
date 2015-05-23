package es.uniovi.miw.monitora.server.core.impl.planificacion;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Planificacion;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class AddPlanificacion implements Command {

	private Planificacion plan;

	public AddPlanificacion(Planificacion cli) {
		this.plan = cli;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			Jpa.getManager().persist(plan);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}
}
