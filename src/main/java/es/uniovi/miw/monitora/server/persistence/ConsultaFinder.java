package es.uniovi.miw.monitora.server.persistence;

import es.uniovi.miw.monitora.server.model.Consulta;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class ConsultaFinder {

	public static Consulta findById(Integer id) throws BusinessException {
		try {
			return Jpa.getManager().find(Consulta.class, id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	public static Object findAll() throws BusinessException {
		try {
		return Jpa.getManager().createNamedQuery("Consulta.findAll").getResultList();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

}
