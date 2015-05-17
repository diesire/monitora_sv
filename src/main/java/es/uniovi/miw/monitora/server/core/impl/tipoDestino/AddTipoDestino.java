package es.uniovi.miw.monitora.server.core.impl.tipoDestino;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.TipoDestino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class AddTipoDestino implements Command {

	private TipoDestino tDes;

	public AddTipoDestino(TipoDestino tDes) {
		this.tDes = tDes;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			Jpa.getManager().persist(tDes);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}
}
