package es.uniovi.miw.monitora.server.ui.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.PersistenceService;
import es.uniovi.miw.monitora.server.persistence.util.impl.HsqldbService;

public class HierarchyTest {

	private static PersistenceService db;

	@BeforeClass
	static public void setUp() throws Exception {
		db = HsqldbService.getInstance();
		db.start();
	}

	@AfterClass
	static public void tearDown() throws Exception {
		db.stop();
	}

	@Test
	public void test() throws BusinessException {
		TestUtils tUtils = new TestUtils();
		Agente ag = tUtils.createHierarchy();
		tUtils.testHierarchy(ag);
	}

	public void testCascade() throws BusinessException {
		// fails
		TestUtils tUtils = new TestUtils();
		Agente ag = tUtils.createHierarchyCascade();
		tUtils.testHierarchy(ag);
	}

}
