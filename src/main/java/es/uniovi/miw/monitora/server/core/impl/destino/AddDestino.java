package es.uniovi.miw.monitora.server.core.impl.destino;

import javax.persistence.EntityManager;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Destino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class AddDestino implements Command {

	private Destino dest;

	public AddDestino(Destino dest) {
		this.dest = dest;
	}

	@Override
	public Object execute() throws BusinessException {
		try {
			
			//Autogenerate idDestino
			//TODO delete sequence generator in SQL script
			Integer idDestino = (Integer) Jpa.getManager().createNamedQuery("Destino.getNextDestinoId").getSingleResult();
			idDestino = idDestino != null ? idDestino : 1;
			assert idDestino != null;
			
			dest.getId().setIdDestino(idDestino);
			Jpa.getManager().persist(dest);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return null;
	}

}
