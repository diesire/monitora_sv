package es.uniovi.miw.monitora.server.persistence.util.impl;

import static org.junit.Assert.*;

import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uniovi.miw.monitora.server.conf.Conf;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.PersistenceService;

public class HsqldbServiceTest {

	private static final String WEB_ROW_RESULT_FILE = Conf
			.get("server.snapshot.path") + "/test.xml";

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void run() throws BusinessException {
		PersistenceService db = HsqldbService.getInstance();
		db.start();
		db.stop();
	}

	@Test
	public void fromXML() throws BusinessException {
		PersistenceService db = HsqldbService.getInstance();
		db.start();
//		db.dumpToXML("CLIENTE", Paths.get(WEB_ROW_RESULT_FILE));
		db.fromXML(Paths.get(WEB_ROW_RESULT_FILE));
		db.stop();
	}

}
