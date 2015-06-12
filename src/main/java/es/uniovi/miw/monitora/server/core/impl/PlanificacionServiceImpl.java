package es.uniovi.miw.monitora.server.core.impl;

import java.util.Date;

import es.uniovi.miw.monitora.server.core.PlanificacionService;
import es.uniovi.miw.monitora.server.core.impl.planificacion.AddPlanificacion;
import es.uniovi.miw.monitora.server.core.impl.planificacion.CreatePlanificacion;
import es.uniovi.miw.monitora.server.core.impl.planificacion.DeletePlanificacion;
import es.uniovi.miw.monitora.server.core.impl.planificacion.FindPlanificacionById;
import es.uniovi.miw.monitora.server.core.impl.planificacion.UpdatePlanificacion;
import es.uniovi.miw.monitora.server.model.Planificacion;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class PlanificacionServiceImpl implements PlanificacionService {
	private CommandExecutor executor = new CommandExecutor();

	@Override
	public void addPlanificacion(Planificacion plan) throws BusinessException {
		executor.execute(new AddPlanificacion(plan));
	}

	@Override
	public void updatePlanificacion(Planificacion plan)
			throws BusinessException {
		executor.execute(new UpdatePlanificacion(plan));
	}

	@Override
	public Planificacion findPlanificacionById(Integer id)
			throws BusinessException {
		return (Planificacion) executor.execute(new FindPlanificacionById(id));
	}

	@Override
	public void deletePlanificacion(Integer id) throws BusinessException {
		executor.execute(new DeletePlanificacion(id));
	}

	@Override
	public Planificacion createPlanificacion(Date fModif)
			throws BusinessException {
		return (Planificacion) executor
				.execute(new CreatePlanificacion(fModif));
	}

}
