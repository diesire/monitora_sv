package es.uniovi.miw.monitora.server.persistence;

import es.uniovi.miw.monitora.server.model.Planificacion;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class PlanificacionFinder {

	public static Planificacion findById(Integer id) {
		return Jpa.getManager().find(Planificacion.class, id);
	}

}
