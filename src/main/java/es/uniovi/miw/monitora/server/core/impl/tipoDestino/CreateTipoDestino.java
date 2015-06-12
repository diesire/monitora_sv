package es.uniovi.miw.monitora.server.core.impl.tipoDestino;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.TipoDestino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

public class CreateTipoDestino implements Command {

	private String nombre;

	public CreateTipoDestino(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public Object execute() throws BusinessException {
		TipoDestino tDes = new TipoDestino();
		tDes.setNombreCorto(nombre);
		return tDes;
	}

}
