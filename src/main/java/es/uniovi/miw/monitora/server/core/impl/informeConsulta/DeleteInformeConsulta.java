package es.uniovi.miw.monitora.server.core.impl.informeConsulta;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.InformeConsulta;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.InformeConsultaFinder;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class DeleteInformeConsulta implements Command {


	private Integer informeId;
	private Integer consultaId;

	public DeleteInformeConsulta(Integer informeId, Integer consultaId) {
		this.informeId = informeId;
		this.consultaId = consultaId;
	}

	@Override
	public Object execute() throws BusinessException {
		InformeConsulta infoCon = InformeConsultaFinder.findById(informeId, consultaId);
		assertNotNull(infoCon);
		// assertCanBeDeleted(m); //TODO: check logic rules

		try {
			Jpa.getManager().remove(infoCon);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}

	private void assertNotNull(InformeConsulta infoCon) throws BusinessException {
		if (infoCon == null) {
			throw new BusinessException("El InformeConsulta no existe");
		}
	}

}
