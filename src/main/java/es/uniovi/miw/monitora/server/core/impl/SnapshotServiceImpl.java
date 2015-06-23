package es.uniovi.miw.monitora.server.core.impl;

import java.util.Date;

import es.uniovi.miw.monitora.server.core.SnapshotService;
import es.uniovi.miw.monitora.server.core.impl.snapshot.AddSnapshot;
import es.uniovi.miw.monitora.server.core.impl.snapshot.CreateSnapshot;
import es.uniovi.miw.monitora.server.core.impl.snapshot.DeleteSnapshot;
import es.uniovi.miw.monitora.server.core.impl.snapshot.FindSnapshotById;
import es.uniovi.miw.monitora.server.core.impl.snapshot.UpdateSnapshot;
import es.uniovi.miw.monitora.server.model.Destino;
import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.Snapshot;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class SnapshotServiceImpl implements SnapshotService {
	private CommandExecutor executor = new CommandExecutor();

	@Override
	public Snapshot createSnapshot(Destino des, Informe info, Date fecha)
			throws BusinessException {
		return (Snapshot) executor
				.execute(new CreateSnapshot(des, info, fecha));
	}

	@Override
	public void addSnapshot(Snapshot dest) throws BusinessException {
		executor.execute(new AddSnapshot(dest));
	}

	@Override
	public void updateSnapshot(Snapshot dest) throws BusinessException {
		executor.execute(new UpdateSnapshot(dest));
	}

	@Override
	public Snapshot findSnapshotById(Integer idSnapshot, Integer idDestino,
			Integer idCliente) throws BusinessException {
		if (idSnapshot == null || idDestino == null || idCliente == null) {
			throw new BusinessException("Empty key");
		}
		return (Snapshot) executor.execute(new FindSnapshotById(idSnapshot,
				idDestino, idCliente));
	}

	@Override
	public void deleteSnapshot(Integer idSnapshot, Integer idDestino,
			Integer idCliente) throws BusinessException {
		executor.execute(new DeleteSnapshot(idSnapshot, idDestino, idCliente));
	}

}
