package es.uniovi.miw.monitora.server.core.impl.consulta;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Consulta;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.ConsultaFinder;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class DeleteConsulta implements Command {

	private Integer consultaId;

	public DeleteConsulta(Integer consultaId) {
		this.consultaId = consultaId;
	}

	@Override
	public Object execute() throws BusinessException {
		Consulta con = ConsultaFinder.findById(consultaId);
		assertNotNull(con);
		// assertCanBeDeleted(m); //TODO: check logic rules

		try {
			Jpa.getManager().remove(con);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}

	private void assertNotNull(Consulta con) throws BusinessException {
		if (con == null) {
			throw new BusinessException("La consulta no existe");
		}
	}

}
