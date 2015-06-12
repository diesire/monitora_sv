package es.uniovi.miw.monitora.server.core.impl.snapshot;

import javax.persistence.EntityManager;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Snapshot;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class AddSnapshot implements Command {

	private Snapshot snap;

	public AddSnapshot(Snapshot snap) {
		this.snap = snap;
	}

	@Override
	public Object execute() throws BusinessException {
		try {

			// Autogenerate idSnapshot
			Integer idSnapshot = (Integer) Jpa.getManager()
					.createNamedQuery("Snapshot.getNextSnapshotId")
					.getSingleResult();
			idSnapshot = idSnapshot != null ? idSnapshot : 1;
			assert idSnapshot != null;

			snap.getId().setIdSnapshot(idSnapshot);
			Jpa.getManager().persist(snap);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}

}
