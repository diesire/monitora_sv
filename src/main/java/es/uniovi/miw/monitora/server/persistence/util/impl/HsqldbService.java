package es.uniovi.miw.monitora.server.persistence.util.impl;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl.AclFormatException;

import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.PersistenceService;

public class HsqldbService implements PersistenceService {
	private static final String MONITORASV = "monitorasv";
	private static final String TEST = "file:./data/serverdb";
	private Server server2;

	public void start() throws BusinessException {
		HsqlProperties p = new HsqlProperties();
		server2 = new Server();

		p.setProperty("server.database.0", TEST);
		p.setProperty("server.dbname.0", MONITORASV);
		p.setProperty("server.port", "9001");
		p.setProperty("readonly", "true");

		try {
			server2.setProperties(p);
			server2.setNoSystemExit(true);
			server2.setRestartOnShutdown(false);
			server2.start();

			DriverManager.registerDriver(new org.hsqldb.jdbcDriver());
			DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9001/"
					+ MONITORASV + ";shutdown=true;ifexists=true", "sa", "");

			// Statement stat = c.createStatement();
			// Boolean result = stat
			// .execute("CREATE TABLE Test( Id INTEGER PRIMARY KEY, FirstName VARCHAR(20), Name VARCHAR(50), ZIP INTEGER)");

		} catch (IOException | AclFormatException e) {
			throw new BusinessException(e);
		} catch (SQLException e) {
			throw new BusinessException(e);
		}

	}

	public void stop() throws BusinessException {
		server2.shutdownWithCatalogs(org.hsqldb.Database.CLOSEMODE_IMMEDIATELY);
	}
}
