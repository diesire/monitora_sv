package es.uniovi.miw.monitora.server.core;

import java.util.Date;
import java.util.List;

import es.uniovi.miw.monitora.server.model.Consulta;
import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.InformeConsulta;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public interface InformeConsultaService {
	
	InformeConsulta createInformeConsulta(Informe info, Consulta con, Date fechaModif) throws BusinessException;
	
	void addInformeConsulta(InformeConsulta InformeConsulta) throws BusinessException;

	void updateInformeConsulta(InformeConsulta InformeConsulta) throws BusinessException;

	InformeConsulta findInformeConsultaById(Integer informeId, Integer consultaId) throws BusinessException;

	void deleteInformeConsulta(Integer informeId, Integer consultaId) throws BusinessException;

	List<InformeConsulta> findAll() throws BusinessException;
}
