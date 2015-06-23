package es.uniovi.miw.monitora.server.persistence.util.impl;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;

import javax.sql.rowset.WebRowSet;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.NoSuchTableException;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl.AclFormatException;

import com.sun.rowset.WebRowSetImpl;

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
			connection = DriverManager.getConnection(getURL(), "sa", "");
		} catch (SQLException e) {
			throw new BusinessException(e);
		}
		return connection;
	}

	private String getURL() {
		return MessageFormat
				.format("jdbc:hsqldb:hsql://localhost:{0}/{1};shutdown=true;ifexists=true",
						Conf.get("db.port"), Conf.get("db.dbname"));
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

	public synchronized void dumpToXML(String tabla, Path tempFile)
			throws BusinessException {
		// try {
		// WebRowSet webRS = new WebRowSetImpl();
		// webRS.setCommand("SELECT * FROM " + tabla);
		// webRS.execute(getConnection());
		// FileWriter fw = new FileWriter(tempFile.toFile());
		// webRS.writeXml(fw);
		// fw.flush();
		// fw.close();
		// webRS.close();
		//
		// } catch (SQLException | IOException e) {
		// throw new BusinessException(e);
		// }
		// database connection

		try {
			IDatabaseConnection connection = new DatabaseConnection(
					getConnection());

			// partial database export
			QueryDataSet partialDataSet = new QueryDataSet(connection);
			partialDataSet.addTable(tabla);
			FlatXmlDataSet.write(partialDataSet,
					new FileOutputStream(tempFile.toFile()));
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	public synchronized void fromXML(Path tempFile) throws BusinessException {
		// //
		// https://docs.oracle.com/javase/tutorial/jdbc/basics/cachedrowset.html#updating-data-source
		// // https://docs.oracle.com/javase/tutorial/jdbc/basics/webrowset.html
		// try {
		// FileReader fr = new FileReader(tempFile.toFile());
		// WebRowSet webRs1 = new WebRowSetImpl();
		// webRs1.readXml(fr);
		// while (webRs1.next()) {
		// webRs1.getR
		// }
		//
		// WebRowSet webRs2 = new WebRowSetImpl();
		// String url = getURL();
		// webRs2.setUrl(url);
		// webRs2.setUsername("sa");
		// webRs2.setPassword("");
		//
		// webRs2.acceptChanges();
		// // webRs.acceptChanges(getConnection());
		// fr.close();
		// webRs2.close();
		// } catch (SQLException | IOException e) {
		// throw new BusinessException(e);
		// }
		try {
			FlatXmlDataSet dataSet = new FlatXmlDataSetBuilder().build(tempFile
					.toFile());
			IDatabaseConnection connection = new DatabaseConnection(
					getConnection());
			try {
				DatabaseOperation.REFRESH.execute(connection, dataSet);
			} catch (NoSuchTableException e) {
				DatabaseOperation.INSERT.execute(connection, dataSet);
			}
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
}
