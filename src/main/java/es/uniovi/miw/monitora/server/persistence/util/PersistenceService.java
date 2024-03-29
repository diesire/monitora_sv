package es.uniovi.miw.monitora.server.persistence.util;

import java.nio.file.Path;
import java.sql.Connection;

import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public interface PersistenceService {
	void start() throws BusinessException;
	void stop() throws BusinessException;
	Connection getConnection() throws BusinessException;
	void dumpToXML(String tabla, Path tempFile) throws BusinessException;
	void fromXML(Path tempFile) throws BusinessException;
}
