package es.uniovi.miw.monitora.server.core;

import es.uniovi.miw.monitora.server.model.LineaCron;
import es.uniovi.miw.monitora.server.model.Planificacion;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public interface LineaCronService {

	LineaCron createLineaCron(Planificacion plan) throws BusinessException;

	void addLineaCron(LineaCron lCron) throws BusinessException;

	void updateLineaCron(LineaCron lCron) throws BusinessException;

	LineaCron findLineaCronById(Integer idLineaCron) throws BusinessException;

	void deleteLineaCron(Integer idLineaCron) throws BusinessException;

}
