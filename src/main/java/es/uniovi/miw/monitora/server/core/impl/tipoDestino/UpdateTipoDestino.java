package es.uniovi.miw.monitora.server.core.impl.tipoDestino;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.TipoDestino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class UpdateTipoDestino implements Command {

	private TipoDestino tDes;

	public UpdateTipoDestino(TipoDestino tDes) {
		this.tDes = tDes;
	}

	@Override
	public Object execute() throws BusinessException {
		Jpa.getManager().merge(tDes);
		return null;
	}
}
