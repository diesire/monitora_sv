package es.uniovi.miw.monitora.server.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.miw.monitora.server.conf.ServicesFactory;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class ClienteServiceTest {

	private ClienteService service;

	@Before
	public void setUp() throws Exception {
		service = ServicesFactory.getClienteService();
	}

	@Test(expected = BusinessException.class)
	public void testAddClienteNull() throws BusinessException {
		service.addCliente(null);
	}

	@Test(expected = BusinessException.class)
	public void testAddClienteEmpty() throws BusinessException {
		service.addCliente(new Cliente());
	}

	@Test
	public void testAddCliente() throws BusinessException {
		Cliente cli = new Cliente("Cliente1");
		assertNull(cli.getIdCliente());

		service.addCliente(cli);
		assertNotNull(cli.getIdCliente());
	}

	@Test(expected = BusinessException.class)
	public void testDeleteNull() throws BusinessException {
		service.deleteCliente(null);
	}

	@Test
	public void testDeleteCliente() throws BusinessException {
		Cliente cli = new Cliente("Cliente1");
		assertNull(cli.getIdCliente());

		service.addCliente(cli);
		assertNotNull(cli.getIdCliente());

		service.deleteCliente(cli.getIdCliente());
	}

	@Test(expected = BusinessException.class)
	public void testFindNullCliente() throws BusinessException {
		service.findClienteById(null);
	}

	@Test
	public void testFindCliente() throws BusinessException {
		Cliente cli = new Cliente("Cliente1");
		assertNull(cli.getIdCliente());

		service.addCliente(cli);
		assertNotNull(cli.getIdCliente());

		Cliente found = service.findClienteById(cli.getIdCliente());
		assertNotNull(found);
		assertEquals(cli, found);
	}

	@Test
	public void testUpdateCliente() throws BusinessException {
		Cliente cli = new Cliente("Cliente11");
		assertNull(cli.getIdCliente());

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
