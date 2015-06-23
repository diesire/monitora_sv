package es.uniovi.miw.monitora.server.core;

import es.uniovi.miw.monitora.server.model.TipoDestino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public interface TipoDestinoService {
	
	TipoDestino createTipoDestino(String nombre) throws BusinessException;
	
	void addTipoDestino(TipoDestino tDes) throws BusinessException;

	void updateTipoDestino(TipoDestino tDes) throws BusinessException;

	TipoDestino findTipoDestinoById(Integer id) throws BusinessException;

	void deleteTipoDestino(Integer id) throws BusinessException;
}
