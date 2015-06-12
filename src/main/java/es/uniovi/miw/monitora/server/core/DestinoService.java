package es.uniovi.miw.monitora.server.core;

import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.Destino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public interface DestinoService {
	Destino createDestino(Cliente cli) throws BusinessException;
	
	void addDestino(Destino dest) throws BusinessException;

	void updateDestino(Destino dest) throws BusinessException;

	Destino findDestinoById(Integer idDestino, Integer idCliente) throws BusinessException;

	void deleteDestino(Integer idDestino, Integer idCliente) throws BusinessException;
}
