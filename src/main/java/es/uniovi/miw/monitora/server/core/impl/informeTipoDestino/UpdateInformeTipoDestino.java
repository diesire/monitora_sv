package es.uniovi.miw.monitora.server.core.impl.informeTipoDestino;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.InformeTipoDestino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class UpdateInformeTipoDestino implements Command {

	private InformeTipoDestino infoTDes;

	public UpdateInformeTipoDestino(InformeTipoDestino infoTDes) {
		this.infoTDes = infoTDes;
	}

	@Override
	public Object execute() throws BusinessException {
		Jpa.getManager().merge(infoTDes);
		return null;
	}

}
