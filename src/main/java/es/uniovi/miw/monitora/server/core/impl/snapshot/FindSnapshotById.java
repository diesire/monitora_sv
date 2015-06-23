package es.uniovi.miw.monitora.server.core.impl.snapshot;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.SnapshotFinder;

public class FindSnapshotById implements Command {

	private Integer idSnapshot;
	private Integer idCliente;
	private Integer idDestino;

	public FindSnapshotById(Integer idSnapshot, Integer idDestino,
			Integer idCliente) {
		this.idSnapshot = idSnapshot;
		this.idDestino = idDestino;
		this.idCliente = idCliente;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			return SnapshotFinder.findById(idSnapshot, idDestino, idCliente);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
}
