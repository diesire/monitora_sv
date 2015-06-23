package es.uniovi.miw.monitora.server.core.impl.informe;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.AgenteFinder;
import es.uniovi.miw.monitora.server.persistence.InformeFinder;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class FindInformeById implements Command {

	private Integer idInforme;

	public FindInformeById(Integer idInforme) {
		this.idInforme = idInforme;
	}

	@Override
	public Object execute() throws BusinessException {
		return InformeFinder.findById(idInforme);
	}

}
