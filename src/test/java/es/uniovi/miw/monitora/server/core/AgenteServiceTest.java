package es.uniovi.miw.monitora.server.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.miw.monitora.server.conf.ServicesFactory;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class AgenteServiceTest {

	private AgenteService service;

	@Before
	public void setUp() throws Exception {
		service = ServicesFactory.getAgenteService();
	}

	@Test(expected = BusinessException.class)
	public void testAddAgenteNull() throws BusinessException {
		service.addAgente(null);
		
	}

	@Test(expected = BusinessException.class)
	public void testAddAgenteEmpty() throws BusinessException {
		service.addAgente(new Agente());
	}

	@Test
	public void testAddAgente() throws BusinessException {
		Agente ag = new Agente();
		assertNull(ag.getAgenteId());

		service.addAgente(ag);
		assertNotNull(ag.getAgenteId());
	}

	@Test(expected = BusinessException.class)
	public void testDeleteNull() throws BusinessException {
		service.deleteAgente(null);
	}

	@Test
	public void testDeleteAgente() throws BusinessException {
		Agente ag = new Agente();
		assertNull(ag.getAgenteId());

		service.addAgente(ag);
		assertNotNull(ag.getAgenteId());

		service.deleteAgente(ag.getAgenteId());
	}

	@Test(expected = BusinessException.class)
	public void testFindNullAgente() throws BusinessException {
		service.findAgenteById(null);
	}

	@Test
	public void testFindAgente() throws BusinessException {
		Agente ag = new Agente();
		assertNull(ag.getAgenteId());

		service.addAgente(ag);
		assertNotNull(ag.getAgenteId());

		Agente found = service.findAgenteById(ag.getAgenteId());
		assertNotNull(found);
		assertEquals(ag, found);
	}

	@Test
	public void testUpdateAgente() throws BusinessException {
		Agente ag = new Agente();
		assertNull(ag.getAgenteId());

		service.addAgente(ag);
		assertNotNull(ag.getAgenteId());

		Agente found = service.findAgenteById(ag.getAgenteId());
		assertNotNull(found);

		found.setIpAgente("192.168.0.2");
		service.updateAgente(found);

		Agente found2 = service.findAgenteById(found.getAgenteId());
		found2 = service.findAgenteById(found.getAgenteId());
		assertNotNull(found2);
		assertEquals("192.168.0.2", found2.getIpAgente());
	}

}
