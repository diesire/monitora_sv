package es.uniovi.miw.monitora.server.core.impl.lineaCron;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.ClienteFinder;
import es.uniovi.miw.monitora.server.persistence.LineaCronFinder;

public class FindLineaCronById implements Command {

	private Integer idLineaCron;

	public FindLineaCronById(Integer idLineaCron) {
		this.idLineaCron = idLineaCron;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			return LineaCronFinder.findById(idLineaCron);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

}
