package es.uniovi.miw.monitora.server.core.impl.cliente;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.AgenteFinder;
import es.uniovi.miw.monitora.server.persistence.ClienteFinder;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class DeleteCliente implements Command {

	private Integer id;

	public DeleteCliente(Integer id) {
		this.id = id;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			Cliente cli = ClienteFinder.findById(id);
			Jpa.getManager().remove(cli);
		} catch (IllegalArgumentException e) {
			throw new BusinessException("El cliente no existe");
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}
}
