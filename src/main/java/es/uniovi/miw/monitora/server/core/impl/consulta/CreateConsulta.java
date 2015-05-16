package es.uniovi.miw.monitora.server.core.impl.consulta;

import java.util.Date;

import es.uniovi.miw.monitora.server.core.impl.Command;
import es.uniovi.miw.monitora.server.model.Consulta;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class CreateConsulta implements Command {

	private String tipo;
	private String descC;
	private String descL;
	private Date fecha;

	public CreateConsulta(String tipo, String descC, String descL, Date fecha) {
		this.tipo = tipo;
		this.descC = descC;
		this.descL = descL;
		this.fecha = fecha;
	}

	@Override
	public Object execute() throws BusinessException {
		Consulta con = new Consulta();
		con.setTipo(tipo);
		con.setDescCorta(descC);
		con.setDescLarga(descL);
		con.setFUltimaModificacion(fecha);

		return con;
	}

}
