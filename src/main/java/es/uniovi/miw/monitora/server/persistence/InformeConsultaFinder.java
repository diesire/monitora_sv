package es.uniovi.miw.monitora.server.persistence;

import es.uniovi.miw.monitora.server.model.InformeConsulta;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.model.keys.InformeConsultaPK;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class InformeConsultaFinder {

	public static InformeConsulta findById(Integer informeId, Integer consultaId)
			throws BusinessException {
		InformeConsultaPK infoConPk = new InformeConsultaPK();
		infoConPk.setIdInforme(informeId);
		infoConPk.setIdConsulta(consultaId);
		try {
			return Jpa.getManager().find(InformeConsulta.class, infoConPk);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	public static Object findAll() throws BusinessException {
		try {
			return Jpa.getManager().createNamedQuery("InformeConsulta.findAll")
					.getResultList();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

}
