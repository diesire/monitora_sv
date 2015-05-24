package es.uniovi.miw.monitora.server.core;

import static org.junit.Assert.*;
import static es.uniovi.miw.monitora.server.ui.util.Utils.*;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.miw.monitora.server.conf.PersistenceFactory;
import es.uniovi.miw.monitora.server.conf.ServicesFactory;
import es.uniovi.miw.monitora.server.model.Destino;
import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.InfPlanDest;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.InformeTipoDestino;
import es.uniovi.miw.monitora.server.model.Planificacion;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.PersistenceService;

public class InformePlanDestinoServiceTest {

	private static final Date NOW2 = new Date(System.currentTimeMillis());

	private static Destino des;
	private static PersistenceService db;
	private static Cliente cli;
	private InformePlanDestinoService service;
	private static Informe info;
	private static Planificacion plan;

	@BeforeClass
	static public void setUpClass() throws Exception {
		db = PersistenceFactory.getPersistenceService();
		db.start();

		ClienteService cliServ = ServicesFactory.getClienteService();
		cli = cliServ.createCliente(CLIENTE1);
		cliServ.addCliente(cli);

		DestinoService desServ = ServicesFactory.getDestinoService();
		des = desServ.createDestino(cli);
		des.setIdTipoDestino(TIPO_DESTINO_0);
		desServ.addDestino(des);

		InformeService infoServ = ServicesFactory.getInformeService();
		info = infoServ.createInforme(INFORME1, DESC_C, NOW);
		infoServ.addInforme(info);

		PlanificacionService planServ = ServicesFactory
				.getPlanificacionService();
		plan = planServ.createPlanificacion(NOW);
		planServ.addPlanificacion(plan);
	}

	@Before
	public void setUp() throws Exception {
		service = ServicesFactory.getInfPlanDestService();
	}

	@AfterClass
	public static void tearDownClass() throws BusinessException {
		db.stop();
	}

	@Test
	public void testCreateInfPlanDest() throws BusinessException {
		InfPlanDest infoPlanDes = service.createInfPlanDest(info, plan, des,
				NOW, NOW);

		assertNotNull(infoPlanDes);
		assertNotNull(infoPlanDes.getId());
		assertNotNull(infoPlanDes.getId().getIdInforme());
		assertNotNull(infoPlanDes.getId().getIdDestino());
		assertNotNull(infoPlanDes.getId().getIdCliente());

	}

	/**
	 * Add null raise BusinessException
	 * 
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddInfPlanDestNull() throws BusinessException {
		service.addInfPlanDest(null);
	}

	/**
	 * Add empty InfPlanDest raise BusinessException
	 * 
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddInfPlanDestEmpty() throws BusinessException {
		service.addInfPlanDest(new InfPlanDest());
	}

	/**
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void testAddInfPlanDest() throws BusinessException {
		createPersistentInfPlanDest();
	}

	@Test(expected = BusinessException.class)
	public void testDeleteNull() throws BusinessException {
		service.deleteInfPlanDest(null, null, null);
	}

	@Test
	public void testDeleteInfPlanDest() throws BusinessException {
		InfPlanDest infoPlanDes = createPersistentInfPlanDest();

		service.deleteInfPlanDest(infoPlanDes.getId().getIdInforme(),
				infoPlanDes.getId().getIdDestino(), infoPlanDes.getId()
						.getIdCliente());
	}

	@Test(expected = BusinessException.class)
	public void testFindNullInfPlanDest() throws BusinessException {
		service.findInfPlanDestById(null, null, null);
	}

	@Test
	public void testFindInfPlanDest() throws BusinessException {
		InfPlanDest infoPlanDes = createPersistentInfPlanDest();

		InfPlanDest found = service.findInfPlanDestById(infoPlanDes.getId()
				.getIdInforme(), infoPlanDes.getId().getIdDestino(),
				infoPlanDes.getId().getIdCliente());

		assertNotNull(found);
		assertEquals(infoPlanDes, found);
	}

	@Test
	public void testUpdateInfPlanDest() throws BusinessException {
		InfPlanDest infoPlanDes = createPersistentInfPlanDest();
		InfPlanDest found = service.findInfPlanDestById(infoPlanDes.getId()
				.getIdInforme(), infoPlanDes.getId().getIdDestino(),
				infoPlanDes.getId().getIdCliente());
		assertNotNull(found);

		found.setFUltimaAplicacion(NOW2);

		service.updateInfPlanDest(found);

		InfPlanDest found2 = service.findInfPlanDestById(found.getId()
				.getIdInforme(), found.getId().getIdDestino(), found.getId()
				.getIdCliente());
		assertNotNull(found2);
		assertEquals(NOW2.getDate(), found2.getFUltimaAplicacion().getDate());
	}

	private InfPlanDest createPersistentInfPlanDest() throws BusinessException {
		InfPlanDest infoPlanDes = service.createInfPlanDest(info, plan, des,
				NOW, NOW);
		findOrAdd(infoPlanDes);

		assertNotNull(infoPlanDes);
		assertNotNull(infoPlanDes.getId().getIdInforme());
		assertNotNull(infoPlanDes.getId().getIdDestino());
		assertNotNull(infoPlanDes.getId().getIdCliente());

		return infoPlanDes;
	}

	private void findOrAdd(InfPlanDest infoPlanDes) throws BusinessException {

		InfPlanDest found;
		try {
			found = service.findInfPlanDestById(infoPlanDes.getId()
					.getIdInforme(), infoPlanDes.getId().getIdDestino(),
					infoPlanDes.getId().getIdCliente());
		} catch (Exception e) {
			found = null;
		}

		if (found == null) {
			service.addInfPlanDest(infoPlanDes);
		}
	}

}
