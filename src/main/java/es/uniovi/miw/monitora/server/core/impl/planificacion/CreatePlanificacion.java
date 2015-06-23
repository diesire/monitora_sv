package es.uniovi.miw.monitora.server.core.impl.planificacion;

import java.util.Date;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Planificacion;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class CreatePlanificacion implements Command {

	private Date fModif;

	public CreatePlanificacion(Date fModif) {
		this.fModif = fModif;
	}

	@Override
	public Object execute() throws BusinessException {
		Planificacion plan = new Planificacion();
		plan.setFUltimaModificacion(fModif);
		return plan;
	}

}
