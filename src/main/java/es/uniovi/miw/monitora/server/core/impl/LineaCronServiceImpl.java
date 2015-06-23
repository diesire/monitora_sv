package es.uniovi.miw.monitora.server.core.impl;

import es.uniovi.miw.monitora.server.core.LineaCronService;
import es.uniovi.miw.monitora.server.core.impl.lineaCron.AddLineaCron;
import es.uniovi.miw.monitora.server.core.impl.lineaCron.CreateLineaCron;
import es.uniovi.miw.monitora.server.core.impl.lineaCron.DeleteLineaCron;
import es.uniovi.miw.monitora.server.core.impl.lineaCron.FindLineaCronById;
import es.uniovi.miw.monitora.server.core.impl.lineaCron.UpdateLineaCron;
import es.uniovi.miw.monitora.server.core.impl.planificacion.CreatePlanificacion;
import es.uniovi.miw.monitora.server.core.impl.snapshot.FindSnapshotById;
import es.uniovi.miw.monitora.server.model.LineaCron;
import es.uniovi.miw.monitora.server.model.Planificacion;
import es.uniovi.miw.monitora.server.model.Snapshot;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class LineaCronServiceImpl implements LineaCronService {
	private CommandExecutor executor = new CommandExecutor();

	@Override
	public LineaCron createLineaCron(Planificacion plan) throws BusinessException {
		return (LineaCron) executor.execute(new CreateLineaCron(plan));
	}

	@Override
	public void addLineaCron(LineaCron lCron) throws BusinessException {
		executor.execute(new AddLineaCron(lCron));
	}

	@Override
	public void updateLineaCron(LineaCron lCron) throws BusinessException {
		executor.execute(new UpdateLineaCron(lCron));
	}

	@Override
	public LineaCron findLineaCronById(Integer idLineaCron)
			throws BusinessException {
		if (idLineaCron == null) {
			throw new BusinessException("Empty key");
		}
		return (LineaCron) executor.execute(new FindLineaCronById(idLineaCron));
	}

	@Override
	public void deleteLineaCron(Integer idLineaCron) throws BusinessException {
		executor.execute(new DeleteLineaCron(idLineaCron));		
	}
}
