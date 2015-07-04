package es.uniovi.miw.monitora.server.core;

import static org.junit.Assert.*;
import static es.uniovi.miw.monitora.server.ui.util.TestUtils.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.miw.monitora.server.conf.PersistenceFactory;
import es.uniovi.miw.monitora.server.conf.ServicesFactory;
import es.uniovi.miw.monitora.server.model.Consulta;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.PersistenceService;

public class ConsultaServiceTest {

	private static PersistenceService db;
	private ConsultaService service;

	@BeforeClass
	static public void setUpClass() throws Exception {
		db = PersistenceFactory.getPersistenceService();
		db.start();
	}

	@Before
	public void setUp() throws Exception {
		service = ServicesFactory.getConsultaService();
	}

	@AfterClass
	public static void tearDownClass() throws BusinessException {
		db.stop();
	}

	@Test
	public void testCreateConsulta() throws BusinessException {
		createPersistentConsulta();
	}

	/**
	 * Add null raise BusinessException
	 * 
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddConsultaNull() throws BusinessException {
		service.addConsulta(null);
	}

	/**
	 * Add empty Consulta raise BusinessException
	 * 
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddConsultaEmpty() throws BusinessException {
		service.addConsulta(new Consulta());
	}

	/**
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void testAddConsulta() throws BusinessException {
		createPersistentConsulta();
	}

	@Test(expected = BusinessException.class)
	public void testDeleteNull() throws BusinessException {
		service.deleteConsulta(null);
	}

	@Test
	public void testDeleteConsulta() throws BusinessException {
		Consulta ag = createPersistentConsulta();

		service.deleteConsulta(ag.getConsId());
	}

	@Test(expected = BusinessException.class)
	public void testFindNullConsulta() throws BusinessException {
		service.findConsultaById(null);
	}

	@Test
	public void testFindConsulta() throws BusinessException {
		Consulta ag = createPersistentConsulta();

		Consulta found = service.findConsultaById(ag.getConsId());
		assertNotNull(found);
		assertEquals(ag, found);
	}

	@Test
	public void testUpdateConsulta() throws BusinessException {
		Consulta con = createPersistentConsulta();
		Consulta found = service.findConsultaById(con.getConsId());
		assertNotNull(found);

		found.setComandoSo("ls -la");
		service.updateConsulta(found);

		Consulta found2 = service.findConsultaById(found.getConsId());
		found2 = service.findConsultaById(found.getConsId());
		assertNotNull(found2);
		assertEquals("ls -la", found2.getComandoSo());
	}

	private Consulta createPersistentConsulta() throws BusinessException {
		Consulta con = service.createConsulta(TIPO_B, DESC_C, DESC_L, NOW);
		// con.addInformeConsulta(informeConsulta) //TODO
		// con.addTcon1(tcon1) //TODO
		// con.addTipoDestino(tipoDestino) //TODO
		con.setComandoSo("dir");
		con.setSqlCreate("create table");
		con.setSqlSelect("select * from ");
		con.setTabla("TTABLE");

		service.addConsulta(con);

		// FIXME compare against persisted instance

		// //Consulta <-> cliente
		// assertEquals(cli, ag.getCliente());
		// // PersistentSet.contains fails
		// assertTrue(new HashSet<Consulta>(ag.getCliente().getConsultas())
		// .contains(ag));
		//
		//
		// //Consulta <-> destino
		// assertTrue(new HashSet<Destino>(ag.getDestinos()).contains(des));
		// assertTrue(new HashSet<Consulta>(des.getConsultas()).contains(ag));

		return con;
	}

}
