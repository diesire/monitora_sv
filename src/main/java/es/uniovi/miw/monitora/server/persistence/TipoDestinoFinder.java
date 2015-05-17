package es.uniovi.miw.monitora.server.persistence;

import es.uniovi.miw.monitora.server.model.TipoDestino;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class TipoDestinoFinder {

	public static TipoDestino findById(Integer id) {
		return Jpa.getManager().find(TipoDestino.class, id);
	}

}
