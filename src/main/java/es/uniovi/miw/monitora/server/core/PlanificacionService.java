package es.uniovi.miw.monitora.server.core;

import java.util.Date;

import es.uniovi.miw.monitora.server.model.Planificacion;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public interface PlanificacionService {

	Planificacion createPlanificacion(Date fModif) throws BusinessException;

	void addPlanificacion(Planificacion plan) throws BusinessException;

	void updatePlanificacion(Planificacion plan) throws BusinessException;

	Planificacion findPlanificacionById(Integer id) throws BusinessException;

	void deletePlanificacion(Integer id) throws BusinessException;
}
