package es.uniovi.miw.monitora.server.core.impl.lineaCron;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.LineaCron;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.ClienteFinder;
import es.uniovi.miw.monitora.server.persistence.LineaCronFinder;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class DeleteLineaCron implements Command {

	private Integer idLineaCron;

	public DeleteLineaCron(Integer idLineaCron) {
		this.idLineaCron = idLineaCron;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			LineaCron lCron = LineaCronFinder.findById(idLineaCron);
			Jpa.getManager().remove(lCron);
		} catch (IllegalArgumentException e) {
			throw new BusinessException("La línea cron no existe");
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}

}
