package es.uniovi.miw.monitora.server.core.impl.informePlanDestino;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.InfPlanDest;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class UpdateInfPlanDest implements Command {

	private InfPlanDest infoPlanDes;

	public UpdateInfPlanDest(InfPlanDest infoPlanDes) {
		this.infoPlanDes = infoPlanDes;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			Jpa.getManager().merge(infoPlanDes);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}

}
