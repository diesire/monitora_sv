package es.uniovi.miw.monitora.server.core.impl.informeTipoDestino;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.InformeTipoDestino;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.TipoDestino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class CreateInformeTipoDestino implements Command {

	private Informe info;
	private TipoDestino tDes;

	public CreateInformeTipoDestino(Informe info, TipoDestino tDes) {
		this.info = info;
		this.tDes = tDes;
	}

	@Override
	public Object execute() throws BusinessException {
		InformeTipoDestino infoTDes = new InformeTipoDestino(info, tDes);
		return infoTDes;
	}

}
