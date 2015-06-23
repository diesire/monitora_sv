package es.uniovi.miw.monitora.server.core.impl;

import java.util.Date;

import es.uniovi.miw.monitora.server.core.InformePlanDestinoService;
import es.uniovi.miw.monitora.server.core.impl.informePlanDestino.AddInformePlanDestino;
import es.uniovi.miw.monitora.server.core.impl.informePlanDestino.CreateInfPlanDest;
import es.uniovi.miw.monitora.server.core.impl.informePlanDestino.DeleteInfPlanDest;
import es.uniovi.miw.monitora.server.core.impl.informePlanDestino.FindInfPlanDestById;
import es.uniovi.miw.monitora.server.core.impl.informePlanDestino.UpdateInfPlanDest;
import es.uniovi.miw.monitora.server.model.Destino;
import es.uniovi.miw.monitora.server.model.InfPlanDest;
import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.Planificacion;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class InformePlanDestinoServiceImpl implements InformePlanDestinoService {
	private CommandExecutor executor = new CommandExecutor();

	@Override
	public InfPlanDest createInfPlanDest(Informe info, Planificacion plan,
			Destino des, Date fModif, Date fApli) throws BusinessException {
		return (InfPlanDest) executor.execute(new CreateInfPlanDest(info, plan,
				des, fModif, fApli));
	}

	@Override
	public void addInfPlanDest(InfPlanDest infoPlanDes)
			throws BusinessException {
		executor.execute(new AddInformePlanDestino(infoPlanDes));
	}

	@Override
	public void updateInfPlanDest(InfPlanDest infoPlanDes)
			throws BusinessException {
		executor.execute(new UpdateInfPlanDest(infoPlanDes));
	}

	@Override
	public InfPlanDest findInfPlanDestById(Integer idInfo, Integer idDestino,
			Integer idCliente) throws BusinessException {
		if (idInfo == null || idDestino == null || idCliente == null) {
			throw new BusinessException("Empty key");
		}
		return (InfPlanDest) executor.execute(new FindInfPlanDestById(idInfo,
				idDestino, idCliente));
	}

	@Override
	public void deleteInfPlanDest(Integer idInfo, Integer idDestino,
			Integer idCliente) throws BusinessException {
		executor.execute(new DeleteInfPlanDest(idInfo, idDestino, idCliente));
	}

}
