package es.uniovi.miw.monitora.server.core;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.miw.monitora.core.api.Ack;
import es.uniovi.miw.monitora.server.conf.ServicesFactory;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class MonitoraServerServiceTest {

	private static final int TEST = 0;
	private static MonitoraServerService service;
	private Calendar now;

	@BeforeClass
	public static void setUpClass() throws BusinessException {
		service = ServicesFactory.getMonitoraServerService();
		service.start();
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
		Ack ack = service.ping(TEST);

		assertNotNull(ack);
		assertTrue(now.compareTo(ack.getUpdate()) <= 0);
	}

	@Test
	public void testGetAgentes() throws BusinessException {
		List<Agente> agentes = service.getAgentes();
		
		assertNotNull(agentes);
	}
	
	@Test
	public void testGetAgente() throws BusinessException {
		List<Agente> agentes = service.getAgentes();
		
		Agente ag = service.getAgente(agentes.get(0).getAgenteId());
		
		assertNotNull(ag);
		
		assertNotNull(ag.getCliente());
		assertNotNull(ag.getCliente().getIdCliente());
		
	}
}
