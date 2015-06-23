package es.uniovi.miw.monitora.server.core.impl.consulta;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.ConsultaFinder;

public class FindConsultaById implements Command {

	private Integer id;

	public FindConsultaById(Integer id) {
		this.id = id;
	}

	@Override
	public Object execute() throws BusinessException {
		return ConsultaFinder.findById(id);
	}

}
