package es.uniovi.miw.monitora.server.core.impl;

import java.util.Date;
import java.util.List;

import es.uniovi.miw.monitora.server.core.ConsultaService;
import es.uniovi.miw.monitora.server.core.impl.consulta.AddConsulta;
import es.uniovi.miw.monitora.server.core.impl.consulta.CreateConsulta;
import es.uniovi.miw.monitora.server.core.impl.consulta.DeleteConsulta;
import es.uniovi.miw.monitora.server.core.impl.consulta.FindAllConsultas;
import es.uniovi.miw.monitora.server.core.impl.consulta.FindConsultaById;
import es.uniovi.miw.monitora.server.core.impl.consulta.UpdateConsulta;
import es.uniovi.miw.monitora.server.model.Consulta;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class ConsultaServiceImpl implements ConsultaService {

	private CommandExecutor executor = new CommandExecutor();

	@Override
	public void updateConsulta(Consulta Consulta) throws BusinessException {
		executor.execute(new UpdateConsulta(Consulta));
	}

	@Override
	public Consulta findConsultaById(Integer ConsultaId)
			throws BusinessException {
		return (Consulta) executor.execute(new FindConsultaById(ConsultaId));
	}

	@Override
	public void addConsulta(Consulta Consulta) throws BusinessException {
		executor.execute(new AddConsulta(Consulta));
	}

	@Override
	public void deleteConsulta(Integer ConsultaId) throws BusinessException {
		executor.execute(new DeleteConsulta(ConsultaId));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Consulta> findAll() throws BusinessException {
		return (List<Consulta>) executor.execute(new FindAllConsultas());
	}

	@Override
	public Consulta createConsulta(String tipo, String descC, String descL,
			Date fecha) throws BusinessException {
		return (Consulta) executor.execute(new CreateConsulta(tipo, descC, descL, fecha));
	}

}
