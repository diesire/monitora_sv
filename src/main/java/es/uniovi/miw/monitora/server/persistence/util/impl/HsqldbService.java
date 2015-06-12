package es.uniovi.miw.monitora.server.persistence.util.impl;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;

import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl.AclFormatException;

import es.uniovi.miw.monitora.server.conf.Conf;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.PersistenceService;

public class HsqldbService implements PersistenceService {
	private Server server2;

	public void start() throws BusinessException {
		HsqlProperties p = new HsqlProperties();
		server2 = new Server();

		p.setProperty("server.database.0", Conf.get("database"));
		p.setProperty("server.dbname.0", Conf.get("dbname"));
		p.setProperty("server.port", Conf.get("port"));
		p.setProperty("readonly", "true");

		try {
			server2.setProperties(p);
			server2.setNoSystemExit(true);
			server2.setRestartOnShutdown(false);
			server2.start();

			DriverManager.registerDriver(new org.hsqldb.jdbcDriver());
			DriverManager.getConnection(
							MessageFormat
									.format("jdbc:hsqldb:hsql://localhost:{0}/{1};shutdown=true;ifexists=true",
											Conf.get("port"),
											Conf.get("dbname")), "sa", "");

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
