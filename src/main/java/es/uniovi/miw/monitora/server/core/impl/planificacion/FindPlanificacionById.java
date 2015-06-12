package es.uniovi.miw.monitora.server.core.impl.planificacion;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.PlanificacionFinder;

public class FindPlanificacionById implements Command {

	private Integer id;

	public FindPlanificacionById(Integer id) {
		this.id = id;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			return PlanificacionFinder.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

}
