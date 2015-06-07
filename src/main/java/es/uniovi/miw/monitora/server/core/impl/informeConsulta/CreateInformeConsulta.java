package es.uniovi.miw.monitora.server.core.impl.informeConsulta;

import java.util.Date;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Consulta;
import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.InformeConsulta;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class CreateInformeConsulta implements Command {

	private Informe info;
	private Consulta con;
	private Date fechaModif;

	public CreateInformeConsulta(Informe info, Consulta con, Date fechaModif) {
		this.info = info;
		this.con = con;
		this.fechaModif = fechaModif;
	}

	@Override
	public Object execute() throws BusinessException {
		InformeConsulta infoCon = new InformeConsulta(info, con);
		infoCon.setFUltimaModificacion(fechaModif);

		return infoCon;
	}

}
