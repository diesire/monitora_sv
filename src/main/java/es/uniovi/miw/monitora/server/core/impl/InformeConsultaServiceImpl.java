package es.uniovi.miw.monitora.server.core.impl;

import java.util.Date;
import java.util.List;

import es.uniovi.miw.monitora.server.core.InformeConsultaService;
import es.uniovi.miw.monitora.server.core.impl.informeConsulta.AddInformeConsulta;
import es.uniovi.miw.monitora.server.core.impl.informeConsulta.CreateInformeConsulta;
import es.uniovi.miw.monitora.server.core.impl.informeConsulta.DeleteInformeConsulta;
import es.uniovi.miw.monitora.server.core.impl.informeConsulta.FindAllInformeConsultas;
import es.uniovi.miw.monitora.server.core.impl.informeConsulta.FindInformeConsultaById;
import es.uniovi.miw.monitora.server.core.impl.informeConsulta.UpdateInformeConsulta;
import es.uniovi.miw.monitora.server.model.Consulta;
import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.InformeConsulta;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class InformeConsultaServiceImpl implements InformeConsultaService {
	private CommandExecutor executor = new CommandExecutor();

	@Override
	public void updateInformeConsulta(InformeConsulta InformeConsulta) throws BusinessException {
		executor.execute(new UpdateInformeConsulta(InformeConsulta));
	}

	@Override
	public InformeConsulta findInformeConsultaById(Integer informeId, Integer consultaId) throws BusinessException {
		return (InformeConsulta) executor.execute(new FindInformeConsultaById(informeId, consultaId));
	}

	@Override
	public void addInformeConsulta(InformeConsulta InformeConsulta) throws BusinessException {
		executor.execute(new AddInformeConsulta(InformeConsulta));		
	}

	@Override
	public void deleteInformeConsulta(Integer informeId, Integer consultaId) throws BusinessException {
		executor.execute(new DeleteInformeConsulta(informeId, consultaId));		
	}

	@Override
	public InformeConsulta createInformeConsulta(Informe info, Consulta con, Date fechaModif) throws BusinessException {
		return (InformeConsulta) executor.execute(new CreateInformeConsulta(info, con, fechaModif));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InformeConsulta> findAll() throws BusinessException {
		return (List<InformeConsulta>) executor.execute(new FindAllInformeConsultas());
	}

}
