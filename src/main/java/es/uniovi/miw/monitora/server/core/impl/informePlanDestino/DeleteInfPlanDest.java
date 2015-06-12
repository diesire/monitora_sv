package es.uniovi.miw.monitora.server.core.impl.informePlanDestino;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.InfPlanDest;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.InfPlanDestFinder;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class DeleteInfPlanDest implements Command {

	private Integer idCliente;
	private Integer idDestino;
	private Integer idInfo;

	public DeleteInfPlanDest(Integer idInfo, Integer idDestino,
			Integer idCliente) {
		this.idInfo = idInfo;
		this.idDestino = idDestino;
		this.idCliente = idCliente;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			InfPlanDest dest = InfPlanDestFinder.findById(idInfo, idDestino,
					idCliente);
			Jpa.getManager().remove(dest);
		} catch (IllegalArgumentException e) {
			throw new BusinessException("El InfPlanDest no existe");
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}

}
