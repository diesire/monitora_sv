package es.uniovi.miw.monitora.server.core.impl.informeConsulta;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.InformeConsulta;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class AddInformeConsulta implements Command {

	private InformeConsulta informeConsulta;

	public AddInformeConsulta(InformeConsulta informeConsulta) {
		this.informeConsulta = informeConsulta;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			Jpa.getManager().persist(informeConsulta);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}

}
