package es.uniovi.miw.monitora.server.core.impl.snapshot;

import java.util.Date;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.Destino;
import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.Snapshot;
import es.uniovi.miw.monitora.server.model.Snapshot;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class CreateSnapshot implements Command {

	private Destino des;
	private Date fecha;
	private Informe info;

	public CreateSnapshot(Destino des, Informe info, Date fecha) {
		this.des = des;
		this.info = info;
		this.fecha = fecha;
	}

	@Override
	public Object execute() throws BusinessException {
		Snapshot snap = new Snapshot();
		des.addSnapshot(snap);
		info.addSnapshot(snap);
		snap.setFecha(fecha);
		return snap;
	}
}