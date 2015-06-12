package es.uniovi.miw.monitora.server.core.impl.tipoDestino;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.TipoDestino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.TipoDestinoFinder;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class DeleteTipoDestino implements Command {

	private Integer id;

	public DeleteTipoDestino(Integer id) {
		this.id = id;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			TipoDestino cli = TipoDestinoFinder.findById(id);
			Jpa.getManager().remove(cli);
		} catch (IllegalArgumentException e) {
			throw new BusinessException("El TipoDestino no existe");
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}
}
