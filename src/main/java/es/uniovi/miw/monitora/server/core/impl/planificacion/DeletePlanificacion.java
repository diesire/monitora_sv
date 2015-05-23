package es.uniovi.miw.monitora.server.core.impl.planificacion;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.Planificacion;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.AgenteFinder;
import es.uniovi.miw.monitora.server.persistence.PlanificacionFinder;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class DeletePlanificacion implements Command {

	private Integer id;

	public DeletePlanificacion(Integer id) {
		this.id = id;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			Planificacion plan = PlanificacionFinder.findById(id);
			Jpa.getManager().remove(plan);
		} catch (IllegalArgumentException e) {
			throw new BusinessException("El Planificacion no existe");
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}
}
