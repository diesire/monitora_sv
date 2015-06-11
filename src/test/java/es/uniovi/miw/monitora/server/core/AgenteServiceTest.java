package es.uniovi.miw.monitora.server.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static es.uniovi.miw.monitora.server.ui.util.TestUtils.*;

import java.util.HashSet;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.miw.monitora.server.conf.PersistenceFactory;
import es.uniovi.miw.monitora.server.conf.ServicesFactory;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.Destino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.PersistenceService;
import es.uniovi.miw.monitora.server.ui.util.TestUtils;

public class AgenteServiceTest {

	private static Cliente cli;
	private static Destino des;
	private static PersistenceService db;
	private AgenteService service;
	TestUtils testUtils = new TestUtils();

	@BeforeClass
	static public void setUpClass() throws Exception {
		db = PersistenceFactory.getPersistenceService();
		db.start();

		ClienteService cliServ = ServicesFactory.getClienteService();
		cli = cliServ.createCliente("ClienteAgente");
		cliServ.addCliente(cli);

		DestinoService desServ = ServicesFactory.getDestinoService();
		des = desServ.createDestino(cli);
		des.setIdTipoDestino(0); // FIXME: TipoDestino not linked with
		// corresponding table???);
		desServ.addDestino(des);
	}

	@Before
	public void setUp() throws Exception {
		service = ServicesFactory.getAgenteService();
	}

	@AfterClass
	public static void tearDownClass() throws BusinessException {
		db.stop();
	}

	@Test
	public void testCreateAgente() throws BusinessException {
		Agente ag = service.createAgente(new Cliente());
		assertNotNull(ag);
		assertNull(ag.getAgenteId());

	}

	/**
	 * Add null raise BusinessException
	 * 
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddAgenteNull() throws BusinessException {
		service.addAgente(null);
	}

	/**
	 * Add empty Agente raise BusinessException
	 * 
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddAgenteEmpty() throws BusinessException {
		service.addAgente(new Agente());
	}

	/**
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void testAddAgente() throws BusinessException {
		createPersistentAgente(cli);
	}

	@Test(expected = BusinessException.class)
	public void testDeleteNull() throws BusinessException {
		service.deleteAgente(null);
	}

	@Test
	public void testDeleteAgente() throws BusinessException {
		Agente ag = createPersistentAgente(cli);

		service.deleteAgente(ag.getAgenteId());
	}

	@Test(expected = BusinessException.class)
	public void testFindNullAgente() throws BusinessException {
		service.findAgenteById(null);
	}

	@Test
	public void testFindAgente() throws BusinessException {
		Agente ag = createPersistentAgente(cli);

		Agente found = service.findAgenteById(ag.getAgenteId());
		assertNotNull(found);
		assertEquals(ag, found);
	}

	@Test
	public void testUpdateAgente() throws BusinessException {
		Agente ag = createPersistentAgente(cli);
		Agente found = service.findAgenteById(ag.getAgenteId());
		assertNotNull(found);

		found.setIpAgente("192.168.0.2");
		service.updateAgente(found);

		Agente found2 = service.findAgenteById(found.getAgenteId());
		found2 = service.findAgenteById(found.getAgenteId());
		assertNotNull(found2);
		assertEquals("192.168.0.2", found2.getIpAgente());
	}

	@Test
	public void testCreateAgenteCascade() throws BusinessException {
		ClienteService cliServ = ServicesFactory.getClienteService();
		Cliente _cli = cliServ.createCliente("ClienteAgente");

		DestinoService desServ = ServicesFactory.getDestinoService();
		Destino _des = desServ.createDestino(_cli);
		_des.setIdTipoDestino(0); // FIXME: TipoDestino not linked with
									// corresponding table???);

		Agente ag = service.createAgente(cli);
		ag.setComentarios("Comentario");
		ag.setIpAgente("127.0.0.1");
		ag.linkDestino(des);

		service.addAgente(ag);

		assertNotNull(ag.getAgenteId());
		Agente agFound = service.findAgenteById(ag.getAgenteId());
		assertNotNull(agFound);

		Cliente cliFound = cliServ.findClienteById(agFound.getCliente()
				.getIdCliente());
		testUtils.testLink(cliFound, agFound);

		Destino desFound = desServ.findDestinoById(agFound.getDestinos()
				.iterator().next().getId().getIdDestino(), agFound.getCliente()
				.getIdCliente());
		
		testUtils.testLink(desFound, agFound);
		testUtils.testLink(cliFound, desFound);
	}

	private Agente createPersistentAgente(Cliente cli) throws BusinessException {
		Agente ag = service.createAgente(cli);
		ag.setComentarios("Comentario");
		ag.setIpAgente("127.0.0.1");
		ag.linkDestino(des);

		service.addAgente(ag);
		// FIXME compare against persisted instance

		assertNotNull(ag.getAgenteId());
		assertEquals("Comentario", ag.getComentarios());
		assertEquals("127.0.0.1", ag.getIpAgente());

		// agente <-> cliente
		assertEquals(cli, ag.getCliente());
		// PersistentSet.contains fails
		assertTrue(new HashSet<Agente>(ag.getCliente().getAgentes())
				.contains(ag));

		// agente <-> destino
		assertTrue(new HashSet<Destino>(ag.getDestinos()).contains(des));
		assertTrue(new HashSet<Agente>(des.getAgentes()).contains(ag));

		return ag;
	}

}
