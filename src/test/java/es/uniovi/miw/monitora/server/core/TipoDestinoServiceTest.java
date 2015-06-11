package es.uniovi.miw.monitora.server.core;

import static org.junit.Assert.*;
import static es.uniovi.miw.monitora.server.ui.util.TestUtils.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.miw.monitora.server.conf.PersistenceFactory;
import es.uniovi.miw.monitora.server.conf.ServicesFactory;
import es.uniovi.miw.monitora.server.model.TipoDestino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.PersistenceService;

public class TipoDestinoServiceTest {

	private static PersistenceService db;
	private TipoDestinoService service;

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
		service = ServicesFactory.getTipoDestinoService();
	}

	/**
	 * Create a Client filled with mandatory fields
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void testCreateTipoDestino() throws BusinessException {
		TipoDestino cli = service.createTipoDestino(TIPO_DESTINO1);
		assertNull(cli.getIdTipoDestino());
		assertEquals(TIPO_DESTINO1, cli.getNombreCorto());
	}

	/**
	 * Add null raise BusinessException
	 * 
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddTipoDestinoNull() throws BusinessException {
		service.addTipoDestino(null);
	}

	/**
	 * Add empty TipoDestino raise BusinessException
	 * 
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddTipoDestinoEmpty() throws BusinessException {
		service.addTipoDestino(new TipoDestino());
	}

	@Test
	public void testAddTipoDestino() throws BusinessException {
		createPersistentTipoDestino();
	}

	@Test(expected = BusinessException.class)
	public void testDeleteNull() throws BusinessException {
		service.deleteTipoDestino(null);
	}

	@Test
	public void testDeleteTipoDestino() throws BusinessException {
		TipoDestino cli = service.createTipoDestino(TIPO_DESTINO1);
		service.addTipoDestino(cli);

		service.deleteTipoDestino(cli.getIdTipoDestino());
	}

	@Test(expected = BusinessException.class)
	public void testFindNullTipoDestino() throws BusinessException {
		service.findTipoDestinoById(null);
	}

	@Test
	public void testFindTipoDestino() throws BusinessException {
		TipoDestino cli = service.createTipoDestino(TIPO_DESTINO1);
		service.addTipoDestino(cli);

		TipoDestino found = service.findTipoDestinoById(cli.getIdTipoDestino());
		assertNotNull(found);
		assertEquals(cli, found);
	}

	@Test
	public void testUpdateTipoDestino() throws BusinessException {
		TipoDestino cli = createPersistentTipoDestino();

		TipoDestino found = service.findTipoDestinoById(cli.getIdTipoDestino());
		assertNotNull(found);

		found.setNombreCorto(TIPO_DESTINO2);
		service.updateTipoDestino(found);

		TipoDestino found2 = service.findTipoDestinoById(found
				.getIdTipoDestino());
		found2 = service.findTipoDestinoById(found.getIdTipoDestino());
		assertNotNull(found2);
		assertEquals(TIPO_DESTINO2, found2.getNombreCorto());
	}

	private TipoDestino createPersistentTipoDestino() throws BusinessException {
		TipoDestino cli = service.createTipoDestino(TIPO_DESTINO1);
		// TODO add more properties
		service.addTipoDestino(cli);

		assertNotNull(cli.getIdTipoDestino());
		return cli;
	}

}
