package es.uniovi.miw.monitora.server.core.impl.informePlanDestino;

import java.util.Date;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.Destino;
import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.InfPlanDest;
import es.uniovi.miw.monitora.server.model.InfPlanDest;
import es.uniovi.miw.monitora.server.model.Planificacion;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class CreateInfPlanDest implements Command {

	private Destino des;
	private Informe info;
	private Planificacion plan;
	private Date fModif;
	private Date fApli;

	public CreateInfPlanDest(Informe info, Planificacion plan, Destino des,
			Date fModif, Date fApli) {
		this.plan = plan;
		this.des = des;
		this.info = info;
		this.fModif = fModif;
		this.fApli = fApli;
	}

	@Override
	public Object execute() throws BusinessException {
		InfPlanDest infoPlanDes = new InfPlanDest(info, des);
		plan.addInfPlanDest(infoPlanDes);

		infoPlanDes.setFUltimaModificacion(fModif);
		infoPlanDes.setFUltimaAplicacion(fApli);

		return infoPlanDes;
	}

}
