package es.uniovi.miw.monitora.server.core.impl.consulta;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Consulta;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class UpdateConsulta implements Command {

	private Consulta consulta;

	public UpdateConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	@Override
	public Object execute() throws BusinessException {
		Jpa.getManager().merge(consulta);
		return null;
	}

}
