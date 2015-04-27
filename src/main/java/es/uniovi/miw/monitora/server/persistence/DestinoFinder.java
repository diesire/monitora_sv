package es.uniovi.miw.monitora.server.persistence;

import es.uniovi.miw.monitora.server.model.Destino;
import es.uniovi.miw.monitora.server.model.keys.DestinoPK;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class DestinoFinder {

	public static Destino findById(Integer idDestino, Integer idCliente) {
		DestinoPK destPk = new DestinoPK();
		destPk.setIdDestino(idDestino);
		destPk.setIdCliente(idCliente);
		
		return Jpa.getManager().find(Destino.class, destPk);
	}

}
