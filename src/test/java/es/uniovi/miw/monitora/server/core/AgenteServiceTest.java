package es.uniovi.miw.monitora.server.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.miw.monitora.server.conf.ServicesFactory;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class AgenteServiceTest {

	private static Cliente cli;
	private AgenteService service;
	
	@BeforeClass
	static public void setUpClass() throws Exception {
		ClienteService cliServ= ServicesFactory.getClienteService();
		cli = cliServ.createCliente("ClienteAgente");
		cliServ.addCliente(cli);
	}

	@Before
	public void setUp() throws Exception {
		service = ServicesFactory.getAgenteService();
	}

	@Test
	public void testCreateAgente() throws BusinessException {
		Agente ag = service.createAgente(new Cliente());
		assertNotNull(ag);
		assertNull(ag.getAgenteId());
	}

	/**
	 * Add null raise BusinessException
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddAgenteNull() throws BusinessException {
		service.addAgente(null);
	}

	/**
	 * Add empty Agente raise BusinessException
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
		Agente ag = createPersistentAgente();
		assertNotNull(ag.getAgenteId());
	}

	@Test(expected = BusinessException.class)
	public void testDeleteNull() throws BusinessException {
		service.deleteAgente(null);
	}

	@Test
	public void testDeleteAgente() throws BusinessException {
		Agente ag = createPersistentAgente();

		service.deleteAgente(ag.getAgenteId());
	}

	@Test(expected = BusinessException.class)
	public void testFindNullAgente() throws BusinessException {
		service.findAgenteById(null);
	}

	@Test
	public void testFindAgente() throws BusinessException {
		Agente ag = createPersistentAgente();

		Agente found = service.findAgenteById(ag.getAgenteId());
		assertNotNull(found);
		assertEquals(ag, found);
	}

	@Test
	public void testUpdateAgente() throws BusinessException {
		Agente ag = createPersistentAgente();
		Agente found = service.findAgenteById(ag.getAgenteId());
		assertNotNull(found);

		found.setIpAgente("192.168.0.2");
		service.updateAgente(found);

		Agente found2 = service.findAgenteById(found.getAgenteId());
		found2 = service.findAgenteById(found.getAgenteId());
		assertNotNull(found2);
		assertEquals("192.168.0.2", found2.getIpAgente());
	}

	private Agente createPersistentAgente() throws BusinessException {
		Agente ag = service.createAgente(cli);
		service.addAgente(ag);
		return ag;
	}

}
