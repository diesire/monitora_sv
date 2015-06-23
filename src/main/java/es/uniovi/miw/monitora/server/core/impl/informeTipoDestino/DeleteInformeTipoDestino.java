package es.uniovi.miw.monitora.server.core.impl.informeTipoDestino;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.InformeTipoDestino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.InformeTipoDestinoFinder;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class DeleteInformeTipoDestino implements Command {

	private Integer infoId;
	private Integer tDesId;

	public DeleteInformeTipoDestino(Integer infoId, Integer tDesId) {
		this.infoId = infoId;
		this.tDesId = tDesId;
	}

	@Override
	public Object execute() throws BusinessException {
		InformeTipoDestino ag = InformeTipoDestinoFinder.findById(infoId, tDesId);
		assertNotNull(ag);
		// assertCanBeDeleted(m); //TODO: check logic rules

		try {
			Jpa.getManager().remove(ag);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}

	private void assertNotNull(InformeTipoDestino ag) throws BusinessException {
		if (ag == null) {
			throw new BusinessException("El InformeTipoDestino no existe");
		}
	}

}
