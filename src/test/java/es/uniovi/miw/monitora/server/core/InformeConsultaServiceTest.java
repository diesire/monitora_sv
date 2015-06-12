package es.uniovi.miw.monitora.server.core;

import static org.junit.Assert.*;
import static es.uniovi.miw.monitora.server.ui.util.TestUtils.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.catalina.tribes.util.Arrays;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.miw.monitora.server.conf.PersistenceFactory;
import es.uniovi.miw.monitora.server.conf.ServicesFactory;
import es.uniovi.miw.monitora.server.model.Consulta;
import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.InformeConsulta;
import es.uniovi.miw.monitora.server.model.InformeConsulta;
import es.uniovi.miw.monitora.server.model.Destino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.PersistenceService;

public class InformeConsultaServiceTest {

	private static InformeConsulta infoCon;
	private static Informe info;
	private static Consulta con;
	private static PersistenceService db;
	private InformeConsultaService service;

	@BeforeClass
	static public void setUpClass() throws Exception {
		db = PersistenceFactory.getPersistenceService();
		db.start();
	}

	@Before
	public void setUp() throws Exception {
		service = ServicesFactory.getInformeConsultaService();
		info = ServicesFactory.getInformeService().createInforme(INFORME1,
				DESC_C, NOW);
		ServicesFactory.getInformeService().addInforme(info);
		con = ServicesFactory.getConsultaService().createConsulta(TIPO_B,
				DESC_C, DESC_L, NOW);
		ServicesFactory.getConsultaService().addConsulta(con);
	}

	@AfterClass
	public static void tearDownClass() throws BusinessException {
		db.stop();
	}

	@Test
	public void testCreateInformeConsulta() throws BusinessException {
		InformeConsulta infoCon = service.createInformeConsulta(info, con, NOW);
		assertNotNull(infoCon);
		assertNotNull(infoCon.getId());
		assertNotNull(infoCon.getId().getIdInforme());
		assertNotNull(infoCon.getId().getIdConsulta());
	}

	/**
	 * Add null raise BusinessException
	 * 
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddInformeConsultaNull() throws BusinessException {
		service.addInformeConsulta(null);
	}

	/**
	 * Add empty InformeConsulta raise BusinessException
	 * 
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddInformeConsultaEmpty() throws BusinessException {
		service.addInformeConsulta(new InformeConsulta());
	}

	/**
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void testAddInformeConsulta() throws BusinessException {
		createPersistentInformeConsulta();
	}

	@Test(expected = BusinessException.class)
	public void testDeleteNull() throws BusinessException {
		service.deleteInformeConsulta(null, null);
	}

	@Test
	public void testDeleteInformeConsulta() throws BusinessException {
		InformeConsulta ag = createPersistentInformeConsulta();

		service.deleteInformeConsulta(ag.getId().getIdInforme(), ag.getId()
				.getIdConsulta());
	}

	@Test(expected = BusinessException.class)
	public void testFindNullInformeConsulta() throws BusinessException {
		service.findInformeConsultaById(null, null);
	}

	@Test
	public void testFindInformeConsulta() throws BusinessException {
		InformeConsulta infoCon = createPersistentInformeConsulta();

		InformeConsulta found = service.findInformeConsultaById(infoCon.getId()
				.getIdInforme(), infoCon.getId().getIdConsulta());
		assertNotNull(found);
		assertEquals(infoCon, found);
	}

	@Test
	public void testUpdateInformeConsulta() throws BusinessException {
		InformeConsulta infoCon = createPersistentInformeConsulta();
		InformeConsulta found = service.findInformeConsultaById(infoCon.getId()
				.getIdInforme(), infoCon.getId().getIdConsulta());
		assertNotNull(found);

		// found.setIpInformeConsulta("192.168.0.2");
		// service.updateInformeConsulta(found);

		InformeConsulta found2 = service.findInformeConsultaById(found.getId()
				.getIdInforme(), found.getId().getIdConsulta());
		found2 = service.findInformeConsultaById(found.getId().getIdInforme(),
				found.getId().getIdConsulta());
		assertNotNull(found2);
		// assertEquals("192.168.0.2", found2.getIpInformeConsulta());
	}

	private InformeConsulta createPersistentInformeConsulta()
			throws BusinessException {
		InformeConsulta infoCon = service.createInformeConsulta(info, con, NOW);

		service.addInformeConsulta(infoCon);

		// //InformeConsulta <-> InformeConsulta
		// assertEquals(cli, ag.getInformeConsulta());
		// // PersistentSet.contains fails
		// assertTrue(new
		// HashSet<InformeConsulta>(ag.getInformeConsulta().getInformeConsultas())
		// .contains(ag));
		//
		//
		// //InformeConsulta <-> destino
		// assertTrue(new HashSet<Destino>(ag.getDestinos()).contains(des));
		// assertTrue(new
		// HashSet<InformeConsulta>(des.getInformeConsultas()).contains(ag));

		return infoCon;
	}

}
