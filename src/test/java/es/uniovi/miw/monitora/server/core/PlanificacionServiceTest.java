package es.uniovi.miw.monitora.server.core;

import static org.junit.Assert.*;
import static es.uniovi.miw.monitora.server.ui.util.TestUtils.*;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.miw.monitora.server.conf.PersistenceFactory;
import es.uniovi.miw.monitora.server.conf.ServicesFactory;
import es.uniovi.miw.monitora.server.model.Planificacion;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.PersistenceService;

public class PlanificacionServiceTest {

	private static final Date NOW2 = new Date(System.currentTimeMillis());
	private static PersistenceService db;
	private PlanificacionService service;

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
		service = ServicesFactory.getPlanificacionService();
	}

	/**
	 * Create a Client filled with mandatory fields
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void testCreatePlanificacion() throws BusinessException {
		Planificacion plan = service.createPlanificacion(NOW);
		assertNull(plan.getIdPlan());
	}

	/**
	 * Add null raise BusinessException
	 * 
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddPlanificacionNull() throws BusinessException {
		service.addPlanificacion(null);
	}

	/**
	 * Add empty Planificacion raise BusinessException
	 * 
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddPlanificacionEmpty() throws BusinessException {
		service.addPlanificacion(new Planificacion());
	}

	@Test
	public void testAddPlanificacion() throws BusinessException {
		createPersistentPlanificacion(NOW);
	}

	@Test(expected = BusinessException.class)
	public void testDeleteNull() throws BusinessException {
		service.deletePlanificacion(null);
	}

	@Test
	public void testDeletePlanificacion() throws BusinessException {
		Planificacion plan = service.createPlanificacion(NOW);
		service.addPlanificacion(plan);

		service.deletePlanificacion(plan.getIdPlan());
	}

	@Test(expected = BusinessException.class)
	public void testFindNullPlanificacion() throws BusinessException {
		service.findPlanificacionById(null);
	}

	@Test
	public void testFindPlanificacion() throws BusinessException {
		Planificacion plan = service.createPlanificacion(NOW);
		service.addPlanificacion(plan);

		Planificacion found = service.findPlanificacionById(plan.getIdPlan());
		assertNotNull(found);
		assertEquals(plan, found);
	}

	@Test
	public void testUpdatePlanificacion() throws BusinessException {
		Planificacion cli = createPersistentPlanificacion(NOW);

		Planificacion found = service.findPlanificacionById(cli.getIdPlan());
		assertNotNull(found);

		found.setDescripcion(DESC_C);
		found.setFUltimaModificacion(NOW2);
		service.updatePlanificacion(found);

		Planificacion found2 = service.findPlanificacionById(found.getIdPlan());
		assertNotNull(found2);
		assertEquals(DESC_C, found2.getDescripcion());
		assertEquals(NOW2.getDate(), found2.getFUltimaModificacion().getDate());
	}

	private Planificacion createPersistentPlanificacion(Date fModif)
			throws BusinessException {
		Planificacion cli = service.createPlanificacion(fModif);
		// TODO add more properties
		service.addPlanificacion(cli);

		assertNotNull(cli.getIdPlan());
		return cli;
	}

}
