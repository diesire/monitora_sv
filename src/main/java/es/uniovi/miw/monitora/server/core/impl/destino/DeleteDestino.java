package es.uniovi.miw.monitora.server.core.impl.destino;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Destino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.DestinoFinder;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class DeleteDestino implements Command {

	private Integer idDestino;
	private Integer idCliente;

	public DeleteDestino(Integer idDestino, Integer idCliente) {
		this.idDestino = idDestino;
		this.idCliente = idCliente;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			Destino dest = DestinoFinder.findById(idDestino, idCliente);
			Jpa.getManager().remove(dest);
		} catch (IllegalArgumentException e) {
			throw new BusinessException("El destino no existe");
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}

}
