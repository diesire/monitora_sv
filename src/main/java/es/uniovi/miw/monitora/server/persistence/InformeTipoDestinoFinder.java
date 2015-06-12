package es.uniovi.miw.monitora.server.persistence;

import es.uniovi.miw.monitora.server.model.InformeTipoDestino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.model.keys.InformeTipoDestinoPK;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class InformeTipoDestinoFinder {

	public static InformeTipoDestino findById(Integer infoId, Integer tDesId) throws BusinessException {
		// TODO Auto-generated method stub
		InformeTipoDestinoPK infoTDesPK = new InformeTipoDestinoPK();
		infoTDesPK.setIdInforme(infoId);
		infoTDesPK.setIdTipoDestino(tDesId);
		try {
			return Jpa.getManager().find(InformeTipoDestino.class, infoTDesPK);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
}
