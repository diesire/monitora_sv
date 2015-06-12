package es.uniovi.miw.monitora.server.core.impl;

import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public interface Command {
	Object execute() throws BusinessException;
}
