package es.uniovi.miw.monitora.server.core.impl;

import es.uniovi.miw.monitora.server.core.ClienteService;
import es.uniovi.miw.monitora.server.core.impl.cliente.AddCliente;
import es.uniovi.miw.monitora.server.core.impl.cliente.CreateCliente;
import es.uniovi.miw.monitora.server.core.impl.cliente.DeleteCliente;
import es.uniovi.miw.monitora.server.core.impl.cliente.FindClienteById;
import es.uniovi.miw.monitora.server.core.impl.cliente.UpdateCliente;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class ClienteServiceImpl implements ClienteService {
	private CommandExecutor executor = new CommandExecutor();

	@Override
	public void addCliente(Cliente cli) throws BusinessException {
		executor.execute(new AddCliente(cli));
	}

	@Override
	public void updateCliente(Cliente cli) throws BusinessException {
		executor.execute(new UpdateCliente(cli));
	}

	@Override
	public Cliente findClienteById(Integer id) throws BusinessException {
		return (Cliente) executor.execute(new FindClienteById(id));
	}

	@Override
	public void deleteCliente(Integer id) throws BusinessException {
		executor.execute(new DeleteCliente(id));
	}

	@Override
	public Cliente createCliente(String nombreCliente) throws BusinessException {
		return (Cliente) executor.execute(new CreateCliente(nombreCliente));
	}

}
