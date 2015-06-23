package es.uniovi.miw.monitora.server.core;

import java.util.Date;

import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public interface InformeService {
	Informe createInforme(String nombre, String descripción, Date fUltimaModificacion) throws BusinessException;
	
	void addInforme(Informe inf) throws BusinessException;

	void updateInforme(Informe inf) throws BusinessException;

	Informe findInformeById(Integer idInforme) throws BusinessException;

	void deleteInforme(Integer idInforme) throws BusinessException;
}
