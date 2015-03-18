package es.uniovi.miw.monitora.server.persistence;

import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class ClienteFinder {

	public static Cliente findById(Integer id) {
		return Jpa.getManager().find(Cliente.class, id);
	}

}
