package es.uniovi.miw.monitora.server.core.impl.informeConsulta;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.InformeConsulta;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class UpdateInformeConsulta implements Command {

	private InformeConsulta InformeConsulta;

	public UpdateInformeConsulta(InformeConsulta InformeConsulta) {
		this.InformeConsulta = InformeConsulta;
	}

	@Override
	public Object execute() throws BusinessException {
		Jpa.getManager().merge(InformeConsulta);
		return null;
	}

}
