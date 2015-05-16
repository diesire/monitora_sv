package es.uniovi.miw.monitora.server.core;

import java.util.Date;
import java.util.List;

import es.uniovi.miw.monitora.server.model.Consulta;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public interface ConsultaService {

	void addConsulta(Consulta consulta) throws BusinessException;

	void deleteConsulta(Integer consId) throws BusinessException;

	Consulta findConsultaById(Integer consId) throws BusinessException;

	void updateConsulta(Consulta found) throws BusinessException;

	List<Consulta> findAll() throws BusinessException;

	Consulta createConsulta(String tipo, String descC, String descL, Date fecha) throws BusinessException;

}
