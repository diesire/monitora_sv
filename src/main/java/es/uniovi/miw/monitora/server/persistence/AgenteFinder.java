package es.uniovi.miw.monitora.server.persistence;

import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class AgenteFinder {
	public static Agente findById(int id) {
		return Jpa.getManager().find(Agente.class, id);
	}
}
