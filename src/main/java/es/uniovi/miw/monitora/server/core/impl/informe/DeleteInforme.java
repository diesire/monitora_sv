package es.uniovi.miw.monitora.server.core.impl.informe;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.AgenteFinder;
import es.uniovi.miw.monitora.server.persistence.InformeFinder;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class DeleteInforme implements Command {

	private Integer idInforme;

	public DeleteInforme(Integer idInforme) {
		this.idInforme = idInforme;
	}

	@Override
	public Object execute() throws BusinessException {
		Informe inf = InformeFinder.findById(idInforme);
		assertNotNull(inf);
		// assertCanBeDeleted(m); //TODO: check logic rules

		try {
			Jpa.getManager().remove(inf);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}

	private void assertNotNull(Informe inf) throws BusinessException {
		if (inf == null) {
			throw new BusinessException("El informe no existe");
		}
	}

}
