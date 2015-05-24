package es.uniovi.miw.monitora.server.core.impl.informePlanDestino;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.InfPlanDestFinder;

public class FindInfPlanDestById implements Command {

	private Integer idInfo;
	private Integer idCliente;
	private Integer idDestino;

	public FindInfPlanDestById(Integer idInfo, Integer idDestino,
			Integer idCliente) {
		this.idInfo = idInfo;
		this.idDestino = idDestino;
		this.idCliente = idCliente;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			return InfPlanDestFinder.findById(idInfo, idDestino, idCliente);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
}
