
package es.uniovi.miw.monitora.server.core;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.miw.monitora.server.conf.PersistenceFactory;
import es.uniovi.miw.monitora.server.conf.ServicesFactory;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.PersistenceService;

public class ClienteServiceTest {

	private static final String CLIENTE1 = "Cliente1";
	private static PersistenceService db;
	private ClienteService service;
	
	@BeforeClass
	public static void setUpClass() throws BusinessException {
		db = PersistenceFactory.getPersistenceService();
		db.start();
	}

	@AfterClass
	public static void tearDownClass() throws BusinessException {
		db.stop();
	}

	@Before
	public void setUp() throws Exception {
		service = ServicesFactory.getClienteService();
	}

	/**
	 * Create a Client filled with mandatory fields
	 * @throws BusinessException
	 */
	@Test
	public void testCreateCliente() throws BusinessException {
		Cliente cli = service.createCliente(CLIENTE1);
		assertNull(cli.getIdCliente());
		assertEquals(CLIENTE1, cli.getNombre());
	}

	/**
	 * Add null raise BusinessException
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddClienteNull() throws BusinessException {
		service.addCliente(null);
	}

	/**
	 * Add empty Cliente raise BusinessException
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddClienteEmpty() throws BusinessException {
		service.addCliente(new Cliente());
	}
	
	@Test
	public void testAddCliente() throws BusinessException {
		Cliente cli = service.createCliente(CLIENTE1);

		service.addCliente(cli);
		assertNotNull(cli.getIdCliente());
	}

	@Test(expected = BusinessException.class)
	public void testDeleteNull() throws BusinessException {
		service.deleteCliente(null);
	}

	@Test
	public void testDeleteCliente() throws BusinessException {
		Cliente cli = service.createCliente(CLIENTE1);
		service.addCliente(cli);

		service.deleteCliente(cli.getIdCliente());
	}

	@Test(expected = BusinessException.class)
	public void testFindNullCliente() throws BusinessException {
		service.findClienteById(null);
	}

	@Test
	public void testFindCliente() throws BusinessException {
		Cliente cli = service.createCliente(CLIENTE1);
		service.addCliente(cli);

		Cliente found = service.findClienteById(cli.getIdCliente());
		assertNotNull(found);
		assertEquals(cli, found);
	}

	@Test
	public void testUpdateCliente() throws BusinessException {
		Cliente cli = service.createCliente(CLIENTE1);
		service.addCliente(cli);
		assertNotNull(cli.getIdCliente());

		Cliente found = service.findClienteById(cli.getIdCliente());
		assertNotNull(found);

		found.setNombre("Cliente2");
		service.updateCliente(found);

		Cliente found2 = service.findClienteById(found.getIdCliente());
		found2 = service.findClienteById(found.getIdCliente());
		assertNotNull(found2);
		assertEquals("Cliente2", found2.getNombre());
	}

}
