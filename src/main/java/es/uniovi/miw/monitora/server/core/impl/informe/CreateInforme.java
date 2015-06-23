package es.uniovi.miw.monitora.server.core.impl.informe;

import java.util.Date;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class CreateInforme implements Command {

	private String nombre;
	private String descripcion;
	private Date fUltimaModificacion;

	public CreateInforme(String nombre, String descripcion, Date fUltimaModificacion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fUltimaModificacion = fUltimaModificacion;
	}

	@Override
	public Object execute() throws BusinessException {
		Informe inf = new Informe();
		inf.setNombre(nombre);
		inf.setDescLarga(descripcion);
		inf.setFUltimaModificacion(fUltimaModificacion);
		return inf;
	}

}
