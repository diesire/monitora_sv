package es.uniovi.miw.monitora.server.core.impl.snapshot;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Snapshot;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.SnapshotFinder;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class DeleteSnapshot implements Command {

	private Integer idSnapshot;
	private Integer idCliente;
	private Integer idDestino;

	public DeleteSnapshot(Integer idSnapshot, Integer idDestino, Integer idCliente) {
		this.idSnapshot = idSnapshot;
		this.idDestino = idDestino;
		this.idCliente = idCliente;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			Snapshot dest = SnapshotFinder.findById(idSnapshot, idDestino, idCliente);
			Jpa.getManager().remove(dest);
		} catch (IllegalArgumentException e) {
			throw new BusinessException("El Snapshot no existe");
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}

}
