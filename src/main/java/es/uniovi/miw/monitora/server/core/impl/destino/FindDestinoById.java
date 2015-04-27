package es.uniovi.miw.monitora.server.core.impl.destino;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.DestinoFinder;

public class FindDestinoById implements Command {

	private Integer idDestino;
	private Integer idCliente;

	public FindDestinoById(Integer idDestino, Integer idCliente) {
		this.idDestino = idDestino;
		this.idCliente = idCliente;		
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			return DestinoFinder.findById(idDestino, idCliente);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

}
