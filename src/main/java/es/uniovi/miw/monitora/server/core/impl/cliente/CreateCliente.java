package es.uniovi.miw.monitora.server.core.impl.cliente;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class CreateCliente implements Command {

	private String nombreCliente;

	public CreateCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	@Override
	public Object execute() throws BusinessException {
		Cliente cli = new Cliente();
		cli.setNombre(nombreCliente);		
		return cli;
	}

}
