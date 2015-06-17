package es.uniovi.miw.monitora.server.persistence.util.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.PersistenceService;

public class HsqldbServiceTest {

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

}
