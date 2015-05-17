package es.uniovi.miw.monitora.server.persistence;

import es.uniovi.miw.monitora.server.model.Snapshot;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.model.keys.SnapshotPK;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class SnapshotFinder {

	public static Snapshot findById(Integer idSnapshot, Integer idDestino,
			Integer idCliente) throws BusinessException {
		SnapshotPK snapPk = new SnapshotPK();
		snapPk.setIdSnapshot(idSnapshot);
		snapPk.setIdDestino(idDestino);
		snapPk.setIdCliente(idCliente);

		return Jpa.getManager().find(Snapshot.class, snapPk);
	}

}
