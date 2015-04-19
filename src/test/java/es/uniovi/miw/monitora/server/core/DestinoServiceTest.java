package es.uniovi.miw.monitora.server.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.miw.monitora.server.conf.ServicesFactory;
import es.uniovi.miw.monitora.server.model.Destino;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class DestinoServiceTest {

	private static Cliente cli;
	private DestinoService service;
	
	@BeforeClass
	static public void setUpClass() throws Exception {
		ClienteService cliServ= ServicesFactory.getClienteService();
		cli = cliServ.createCliente("ClienteDestino");
		cliServ.addCliente(cli);
	}

	@Before
	public void setUp() throws Exception {
		service = ServicesFactory.getDestinoService();
	}

	@Test
	public void testCreateDestino() throws BusinessException {
		Destino dest = service.createDestino(new Cliente());
		assertNotNull(dest);
		assertNull(dest.getId().getIdDestino());
		assertNull(dest.getId().getIdCliente());
	}

	/**
	 * Add null raise BusinessException
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddDestinoNull() throws BusinessException {
		service.addDestino(null);
	}

	/**
	 * Add empty Destino raise BusinessException
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddDestinoEmpty() throws BusinessException {
		service.addDestino(new Destino());
	}
	
	/**
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void testAddDestino() throws BusinessException {
		Destino dest = createPersistentDestino();
		assertNotNull(dest.getId());
	}

	@Test(expected = BusinessException.class)
	public void testDeleteNull() throws BusinessException {
		service.deleteDestino(null, null);
	}

	@Test
	public void testDeleteDestino() throws BusinessException {
		Destino dest = createPersistentDestino();

		service.deleteDestino(dest.getId().getIdDestino(), dest.getId().getIdCliente());
	}

	@Test(expected = BusinessException.class)
	public void testFindNullDestino() throws BusinessException {
		service.findDestinoById(null, null);
	}

	@Test
	public void testFindDestino() throws BusinessException {
		Destino dest = createPersistentDestino();

		Destino found = service.findDestinoById(dest.getId().getIdDestino(), dest.getId().getIdCliente());
		assertNotNull(found);
		assertEquals(dest, found);
	}

	@Test
	public void testUpdateDestino() throws BusinessException {
		Destino dest = createPersistentDestino();
		Destino found = service.findDestinoById(dest.getId().getIdDestino(), dest.getId().getIdCliente());
		assertNotNull(found);

//		found.setIpDestino("192.168.0.2");
//		service.updateDestino(found);
//
//		Destino found2 = service.findDestinoById(found.getDestinoId());
//		found2 = service.findDestinoById(found.getDestinoId());
//		assertNotNull(found2);
//		assertEquals("192.168.0.2", found2.getIpDestino());
	}

	private Destino createPersistentDestino() throws BusinessException {
		Destino dest = service.createDestino(cli);
		service.addDestino(dest);
		return dest;
	}

}
