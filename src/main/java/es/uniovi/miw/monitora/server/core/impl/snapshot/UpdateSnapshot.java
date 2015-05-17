package es.uniovi.miw.monitora.server.core.impl.snapshot;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Snapshot;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class UpdateSnapshot implements Command {

	private Snapshot snap;

	public UpdateSnapshot(Snapshot snap) {
		this.snap = snap;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			Jpa.getManager().merge(snap);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}

}
