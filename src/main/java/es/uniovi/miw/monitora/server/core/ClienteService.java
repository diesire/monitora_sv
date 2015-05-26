package es.uniovi.miw.monitora.server.core;

import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public interface ClienteService {

	Cliente createCliente(String nombreCliente) throws BusinessException;

	void addCliente(Cliente cli) throws BusinessException;

	void updateCliente(Cliente cli) throws BusinessException;

	Cliente findClienteById(Integer id) throws BusinessException;

	void deleteCliente(Integer id) throws BusinessException;
}
