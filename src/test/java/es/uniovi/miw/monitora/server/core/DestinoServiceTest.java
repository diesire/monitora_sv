package es.uniovi.miw.monitora.server.core;

import static org.junit.Assert.*;
import static es.uniovi.miw.monitora.server.ui.util.TestUtils.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.miw.monitora.server.conf.PersistenceFactory;
import es.uniovi.miw.monitora.server.conf.ServicesFactory;
import es.uniovi.miw.monitora.server.model.Destino;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.PersistenceService;

public class DestinoServiceTest {

	private static Cliente cli;
	private static PersistenceService db;
	private DestinoService service;

	@BeforeClass
	static public void setUpClass() throws Exception {
		db = PersistenceFactory.getPersistenceService();
		db.start();
		
		ClienteService cliServ = ServicesFactory.getClienteService();
		cli = cliServ.createCliente("ClienteDestino");
		cliServ.addCliente(cli);
	}

	@Before
	public void setUp() throws Exception {
		service = ServicesFactory.getDestinoService();
	}
	
	

	@AfterClass
	public static void tearDownClass() throws BusinessException {
		db.stop();
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
	 * 
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddDestinoNull() throws BusinessException {
		service.addDestino(null);
	}

	/**
	 * Add empty Destino raise BusinessException
	 * 
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
		assertEquals(cli.getIdCliente(), dest.getId().getIdCliente());
		assertNotNull(dest.getId().getIdDestino());
	}

	@Test(expected = BusinessException.class)
	public void testDeleteNull() throws BusinessException {
		service.deleteDestino(null, null);
	}

	@Test
	public void testDeleteDestino() throws BusinessException {
		Destino dest = createPersistentDestino();

		service.deleteDestino(dest.getId().getIdDestino(), dest.getId()
				.getIdCliente());
	}

	@Test(expected = BusinessException.class)
	public void testFindNullDestino() throws BusinessException {
		service.findDestinoById(null, null);
	}

	@Test
	public void testFindDestino() throws BusinessException {
		Destino dest = createPersistentDestino();

		Destino found = service.findDestinoById(dest.getId().getIdDestino(),
				dest.getId().getIdCliente());
		assertNotNull(found);
		assertEquals(dest, found);
	}

	@Test
	public void testUpdateDestino() throws BusinessException {
		Destino dest = createPersistentDestino();
		Destino found = service.findDestinoById(dest.getId().getIdDestino(),
				dest.getId().getIdCliente());
		assertNotNull(found);

		 found.setIdTipoDestino(found.getIdTipoDestino()+1);
		 service.updateDestino(found);
		
		 Destino found2 = service.findDestinoById(found.getId().getIdDestino(), found.getId().getIdCliente());
		 assertNotNull(found2);
		 assertEquals(found.getIdTipoDestino(), found2.getIdTipoDestino());
	}

	private Destino createPersistentDestino() throws BusinessException {
		Destino dest = service.createDestino(cli);
		dest.setIdTipoDestino(TIPO_DESTINO_0); // FIXME: TipoDestino not linked with
									// corresponding table???
		service.addDestino(dest);

		return dest;
	}

}
