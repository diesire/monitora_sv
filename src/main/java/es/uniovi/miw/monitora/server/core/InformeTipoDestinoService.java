package es.uniovi.miw.monitora.server.core;

import es.uniovi.miw.monitora.server.model.InformeTipoDestino;
import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.TipoDestino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public interface InformeTipoDestinoService {
	
	InformeTipoDestino createInformeTipoDestino(Informe info, TipoDestino tDes) throws BusinessException;
	
	void addInformeTipoDestino(InformeTipoDestino infoTDes) throws BusinessException;

	void updateInformeTipoDestino(InformeTipoDestino infoTDes) throws BusinessException;

	InformeTipoDestino findInformeTipoDestinoById(Integer infoId, Integer tDesId) throws BusinessException;

	void deleteInformeTipoDestino(Integer infoId, Integer tDesId) throws BusinessException;
}
