package es.uniovi.miw.monitora.server.core.impl;

import java.util.Date;

import es.uniovi.miw.monitora.server.core.InformeService;
import es.uniovi.miw.monitora.server.core.impl.destino.CreateDestino;
import es.uniovi.miw.monitora.server.core.impl.informe.AddInforme;
import es.uniovi.miw.monitora.server.core.impl.informe.CreateInforme;
import es.uniovi.miw.monitora.server.core.impl.informe.DeleteInforme;
import es.uniovi.miw.monitora.server.core.impl.informe.FindInformeById;
import es.uniovi.miw.monitora.server.core.impl.informe.UpdateInforme;
import es.uniovi.miw.monitora.server.model.Destino;
import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class InformeServiceImpl implements InformeService {
	private CommandExecutor executor = new CommandExecutor();

	@Override
	public Informe createInforme(String nombre, String descripcion, Date fUltimaModificacion) throws BusinessException {
		return (Informe) executor.execute(new CreateInforme(nombre, descripcion, fUltimaModificacion));
	}

	@Override
	public void addInforme(Informe inf) throws BusinessException {
		executor.execute(new AddInforme(inf));
	}

	@Override
	public void updateInforme(Informe inf) throws BusinessException {
		executor.execute(new UpdateInforme(inf));
	}

	@Override
	public Informe findInformeById(Integer idInforme) throws BusinessException {
		return (Informe) executor.execute(new FindInformeById(idInforme));
	}

	@Override
	public void deleteInforme(Integer idInforme) throws BusinessException {
		executor.execute(new DeleteInforme(idInforme));
	}

}
