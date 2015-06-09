package es.uniovi.miw.monitora.server.core;

import static org.junit.Assert.*;
import static es.uniovi.miw.monitora.server.ui.util.Utils.*;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.miw.monitora.core.api.Ack;
import es.uniovi.miw.monitora.server.conf.ServicesFactory;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.Consulta;
import es.uniovi.miw.monitora.server.model.Destino;
import es.uniovi.miw.monitora.server.model.InfPlanDest;
import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.InformeConsulta;
import es.uniovi.miw.monitora.server.model.InformeTipoDestino;
import es.uniovi.miw.monitora.server.model.Planificacion;
import es.uniovi.miw.monitora.server.model.Snapshot;
import es.uniovi.miw.monitora.server.model.TipoDestino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.ui.util.TestUtils;

public class MonitoraServerServiceTest {

	private static final int TEST = 0;
	private static MonitoraServerService service;
	private Calendar now;
	private Agente agente;

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
		new TestUtils().testHierarchy(null);
	}
}
