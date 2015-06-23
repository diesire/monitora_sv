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
import es.uniovi.miw.monitora.server.model.LineaCron;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.Destino;
import es.uniovi.miw.monitora.server.model.LineaCron;
import es.uniovi.miw.monitora.server.model.Planificacion;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.PersistenceService;

public class LineaCronServiceTest {

	private static Cliente cli;
	private static Destino des;
	private static PersistenceService db;
	private LineaCronService service;
	private static Planificacion plan;

	@BeforeClass
	static public void setUpClass() throws Exception {
		db = PersistenceFactory.getPersistenceService();
		db.start();

		plan = ServicesFactory.getPlanificacionService().createPlanificacion(
				NOW);
		ServicesFactory.getPlanificacionService().addPlanificacion(plan);
	}

	@Before
	public void setUp() throws Exception {
		service = ServicesFactory.getLineaCronService();
	}

	@AfterClass
	public static void tearDownClass() throws BusinessException {
		db.stop();
	}

	@Test
	public void testCreateLineaCron() throws BusinessException {
		LineaCron lCron = service.createLineaCron(plan);
		assertNotNull(lCron);
		assertNull(lCron.getIdLineaCron());
	}

	/**
	 * Add null raise BusinessException
	 * 
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddLineaCronNull() throws BusinessException {
		service.addLineaCron(null);
	}

	/**
	 * Add empty LineaCron raise BusinessException
	 * 
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddLineaCronEmpty() throws BusinessException {
		service.addLineaCron(new LineaCron());
	}

	/**
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void testAddLineaCron() throws BusinessException {
		createPersistentLineaCron(des);
	}

	@Test(expected = BusinessException.class)
	public void testDeleteNull() throws BusinessException {
		service.deleteLineaCron(null);
	}

	@Test
	public void testDeleteLineaCron() throws BusinessException {
		LineaCron lCron = createPersistentLineaCron(des);
		service.deleteLineaCron(lCron.getIdLineaCron());
	}

	@Test(expected = BusinessException.class)
	public void testFindNullLineaCron() throws BusinessException {
		service.findLineaCronById(null);
	}

	@Test
	public void testFindLineaCron() throws BusinessException {
		LineaCron lCron = createPersistentLineaCron(des);

		LineaCron found = service.findLineaCronById(lCron.getIdLineaCron());
		assertNotNull(found);
		assertEquals(lCron, found);
	}

	@Test
	public void testUpdateLineaCron() throws BusinessException {
		LineaCron ag = createPersistentLineaCron(des);
		LineaCron found = service.findLineaCronById(ag.getIdLineaCron());
		assertNotNull(found);

		found.setDescripcion(DESC_L);
		service.updateLineaCron(found);

		LineaCron found2 = service.findLineaCronById(found.getIdLineaCron());
		found2 = service.findLineaCronById(found.getIdLineaCron());
		assertNotNull(found2);
		assertEquals(DESC_L, found2.getDescripcion());
	}

	private LineaCron createPersistentLineaCron(Destino des)
			throws BusinessException {
		LineaCron ag = service.createLineaCron(plan);
		ag.setDescripcion(DESC_C);
		ag.setFUltimaModificacion(NOW);

		ag.setExpresion("");

		service.addLineaCron(ag);
		// FIXME compare against persisted instance

		assertNotNull(ag.getIdLineaCron());
		assertEquals(DESC_C, ag.getDescripcion());
		assertEquals(NOW, ag.getFUltimaModificacion());

		// LineaCron <-> Planificacion
		assertEquals(plan, ag.getPlanificacion());
		assertTrue(new HashSet<LineaCron>(ag.getPlanificacion().getLineaCrons())
				.contains(ag));

		return ag;
	}

}
