package es.uniovi.miw.monitora.server.persistence.util.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class HsqldbServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void run() throws BusinessException {
		HsqldbService db = new HsqldbService();
		db.start();
		db.stop();
	}

}
