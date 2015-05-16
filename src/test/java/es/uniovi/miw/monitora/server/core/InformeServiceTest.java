package es.uniovi.miw.monitora.server.core;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.miw.monitora.server.conf.PersistenceFactory;
import es.uniovi.miw.monitora.server.conf.ServicesFactory;
import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.PersistenceService;

import static es.uniovi.miw.monitora.server.ui.util.Utils.*;

public class InformeServiceTest {

	private InformeService service;
	private static PersistenceService db;

	@Before
	public void setUp() throws Exception {
		service = ServicesFactory.getInformeService();
	}

	@BeforeClass
	public static void setUpClass() throws BusinessException {
		db = PersistenceFactory.getPersistenceService();
		db.start();
	}

	@AfterClass
	public static void tearDownClass() throws BusinessException {
		db.stop();
	}

	@Test
	public void testCreateInforme() throws BusinessException {
		Informe inf = service.createInforme(INFORME1, DESC_C, NOW);
		assertNotNull(inf);
		assertNull(inf.getInfoId());
	}

	/**
	 * Add null raise BusinessException
	 * 
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddInformeNull() throws BusinessException {
		service.addInforme(null);
	}

	/**
	 * Add empty Informe raise BusinessException
	 * 
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddInformeEmpty() throws BusinessException {
		service.addInforme(new Informe());
	}

	/**
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void testAddInforme() throws BusinessException {
		Informe inf = createPersistentInforme();
		assertNotNull(inf.getInfoId());
	}

	@Test(expected = BusinessException.class)
	public void testDeleteNull() throws BusinessException {
		service.deleteInforme(null);
	}

	@Test
	public void testDeleteInforme() throws BusinessException {
		Informe ag = createPersistentInforme();

		service.deleteInforme(ag.getInfoId());
	}

	@Test(expected = BusinessException.class)
	public void testFindNullInforme() throws BusinessException {
		service.findInformeById(null);
	}

	@Test
	public void testFindInforme() throws BusinessException {
		Informe inf = createPersistentInforme();

		Informe found = service.findInformeById(inf.getInfoId());
		assertNotNull(found);
		assertEquals(inf, found);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testUpdateInforme() throws BusinessException {
		Date later = new Date(System.currentTimeMillis());
		Informe ag = createPersistentInforme();
		Informe found = service.findInformeById(ag.getInfoId());
		assertNotNull(found);

		found.setFUltimaModificacion(later);
		service.updateInforme(found);

		Informe found2 = service.findInformeById(found.getInfoId());
		assertNotNull(found2);
		assertEquals(later.getDate(), found2.getFUltimaModificacion().getDate());
	}

	private Informe createPersistentInforme() throws BusinessException {
		Informe inf = service.createInforme(INFORME1, DESC_C, NOW);

		service.addInforme(inf);
		return inf;
	}

}
