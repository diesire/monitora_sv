package es.uniovi.miw.monitora.server.core;

import java.util.Date;

import es.uniovi.miw.monitora.server.model.Destino;
import es.uniovi.miw.monitora.server.model.InfPlanDest;
import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.Planificacion;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public interface InformePlanDestinoService {
	InfPlanDest createInfPlanDest(Informe info, Planificacion plan,
			Destino des, Date fModif, Date fApli) throws BusinessException;

	void addInfPlanDest(InfPlanDest infoPlanDes) throws BusinessException;

	void updateInfPlanDest(InfPlanDest infoPlanDes) throws BusinessException;

	InfPlanDest findInfPlanDestById(Integer idInfo, Integer idDestino,
			Integer idCliente) throws BusinessException;

	void deleteInfPlanDest(Integer idInfo, Integer idDestino, Integer idCliente)
			throws BusinessException;
}
