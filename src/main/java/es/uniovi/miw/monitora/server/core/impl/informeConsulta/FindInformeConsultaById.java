package es.uniovi.miw.monitora.server.core.impl.informeConsulta;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.InformeConsultaFinder;

public class FindInformeConsultaById implements Command {

	private Integer informeId;
	private Integer consultaId;

	public FindInformeConsultaById(Integer informeId, Integer consultaId) {
		this.informeId = informeId;
		this.consultaId = consultaId;
	}

	@Override
	public Object execute() throws BusinessException {
		if(informeId == null || consultaId == null) {
			throw new BusinessException("Empty key");
		}
		return InformeConsultaFinder.findById(informeId, consultaId);
	}

}
