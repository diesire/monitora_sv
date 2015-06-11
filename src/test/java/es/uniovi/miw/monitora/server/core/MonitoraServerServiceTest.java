package es.uniovi.miw.monitora.server.core;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static es.uniovi.miw.monitora.server.ui.util.TestUtils.*;

import java.util.Calendar;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.miw.monitora.core.api.Ack;
import es.uniovi.miw.monitora.server.conf.ServicesFactory;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.ui.util.TestUtils;

public class MonitoraServerServiceTest {

	private static MonitoraServerService service;
	private Calendar now;
	private static TestUtils testUtils = new TestUtils();
	private static Agente agente;

	@BeforeClass
	public static void setUpClass() throws BusinessException {
		service = ServicesFactory.getMonitoraServerService();
		service.start();
		agente = testUtils.createHierarchy();
	}

	@Before
	public void setUp() throws Exception {
		now = Calendar.getInstance();
	}

	@AfterClass
	public static void tearDownClass() throws BusinessException {
		service.stop();
	}

	@Test
	public void testPing() throws BusinessException {
		Ack ack = service.ping(AGENTE_ID_INVALID);
		// TODO check agent exists
		assertNotNull(ack);
		assertTrue(now.compareTo(ack.getUpdate()) <= 0);
	}

	@Test
	public void testGetAgentes() throws BusinessException {
		List<Agente> agentes = service.getAgentes();

		assertNotNull(agentes);
		assertTrue("Should be at least one Agente", !agentes.isEmpty());
		for (Agente agente : agentes) {
			testUtils.testHierarchy(agente);
		}
	}

	@Test
	public void testGetAgente() throws BusinessException {
		assertNotNull(agente);
		testUtils.testHierarchy(agente);
	}
}
