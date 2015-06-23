package es.uniovi.miw.monitora.server.core.impl.informeTipoDestino;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.InformeTipoDestino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class AddInformeTipoDestino implements Command {

	private InformeTipoDestino infoTDes;

	public AddInformeTipoDestino(InformeTipoDestino infoTDes) {
		this.infoTDes = infoTDes;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			Jpa.getManager().persist(infoTDes);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}

}
