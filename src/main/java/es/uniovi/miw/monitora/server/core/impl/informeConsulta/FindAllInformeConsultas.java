package es.uniovi.miw.monitora.server.core.impl.informeConsulta;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.InformeConsultaFinder;

public class FindAllInformeConsultas implements Command {

	@Override
	public Object execute() throws BusinessException {
		return InformeConsultaFinder.findAll();
	}

}
