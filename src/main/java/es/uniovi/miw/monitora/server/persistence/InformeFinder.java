package es.uniovi.miw.monitora.server.persistence;

import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class InformeFinder {

	public static Informe findById(Integer idInforme) throws BusinessException {
		try {
			return Jpa.getManager().find(Informe.class, idInforme);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

}
