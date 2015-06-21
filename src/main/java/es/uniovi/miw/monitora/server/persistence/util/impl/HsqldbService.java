package es.uniovi.miw.monitora.server.persistence.util.impl;

import java.io.IOException;
import java.sql.Connection;
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
	private static PersistenceService instance;
	private Server server;

	private HsqldbService() throws BusinessException {
		try {
			DriverManager.registerDriver(new org.hsqldb.jdbcDriver());
		} catch (SQLException e) {
			throw new BusinessException(e);
		}
	}

	public void start() throws BusinessException {
		HsqlProperties p = new HsqlProperties();
		server = new Server();

		p.setProperty("server.database.0", Conf.get("db.database"));
		p.setProperty("server.dbname.0", Conf.get("db.dbname"));
		p.setProperty("server.port", Conf.get("db.port"));
		p.setProperty("readonly", "true");

		try {
			server.setProperties(p);
			server.setNoSystemExit(true);
			server.setRestartOnShutdown(false);
			server.start();
		} catch (IOException | AclFormatException e) {
			throw new BusinessException(e);
		}
	}

	public Connection getConnection() throws BusinessException {
		// TODO: change localhost to Conf.get
		Connection connection;
		try {
			connection = DriverManager
					.getConnection(
							MessageFormat
									.format("jdbc:hsqldb:hsql://localhost:{0}/{1};shutdown=true;ifexists=true",
											Conf.get("db.port"),
											Conf.get("db.dbname")), "sa", "");
		} catch (SQLException e) {
			throw new BusinessException(e);
		}
		return connection;
		// Statement stat = c.createStatement();
		// Boolean result = stat
		// .execute("CREATE TABLE Test( Id INTEGER PRIMARY KEY, FirstName VARCHAR(20), Name VARCHAR(50), ZIP INTEGER)");
	}

	public void stop() throws BusinessException {
		server.shutdownWithCatalogs(org.hsqldb.Database.CLOSEMODE_IMMEDIATELY);
	}

	public static PersistenceService getInstance() throws BusinessException {
		if (instance == null) {
			instance = new HsqldbService();
		}
		return instance;
	}
}
