package es.uniovi.miw.monitora.server.persistence;

import es.uniovi.miw.monitora.server.model.InfPlanDest;
import es.uniovi.miw.monitora.server.model.keys.InfPlanDestPK;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class InfPlanDestFinder {

	public static InfPlanDest findById(Integer idInfo, Integer idDestino,
			Integer idCliente) {
		InfPlanDestPK infoPlanDesPk = new InfPlanDestPK();
		infoPlanDesPk.setIdInforme(idInfo);
		infoPlanDesPk.setIdDestino(idDestino);
		infoPlanDesPk.setIdCliente(idCliente);

		return Jpa.getManager().find(InfPlanDest.class, infoPlanDesPk);

	}

}
