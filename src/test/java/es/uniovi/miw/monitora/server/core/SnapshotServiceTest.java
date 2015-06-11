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
import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.Snapshot;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.PersistenceService;

public class SnapshotServiceTest {

	private static Destino des;
	private static PersistenceService db;
	private static Cliente cli;
	private SnapshotService service;
	private static Informe info;

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
	}

	@Before
	public void setUp() throws Exception {
		service = ServicesFactory.getSnapshotService();
	}

	@AfterClass
	public static void tearDownClass() throws BusinessException {
		db.stop();
	}

	@Test
	public void testCreateSnapshot() throws BusinessException {
		Snapshot snap = service.createSnapshot(des, info, NOW);

		assertNotNull(snap);
		assertNull(snap.getId().getIdSnapshot());
		assertNotNull(snap.getId().getIdCliente());

	}

	/**
	 * Add null raise BusinessException
	 * 
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddSnapshotNull() throws BusinessException {
		service.addSnapshot(null);
	}

	/**
	 * Add empty Snapshot raise BusinessException
	 * 
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddSnapshotEmpty() throws BusinessException {
		service.addSnapshot(new Snapshot());
	}

	/**
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void testAddSnapshot() throws BusinessException {
		createPersistentSnapshot();
	}

	@Test(expected = BusinessException.class)
	public void testDeleteNull() throws BusinessException {
		service.deleteSnapshot(null, null, null);
	}

	@Test
	public void testDeleteSnapshot() throws BusinessException {
		Snapshot snap = createPersistentSnapshot();

		service.deleteSnapshot(snap.getId().getIdSnapshot(), snap.getId()
				.getIdDestino(), snap.getId().getIdCliente());
	}

	@Test(expected = BusinessException.class)
	public void testFindNullSnapshot() throws BusinessException {
		service.findSnapshotById(null, null, null);
	}

	@Test
	public void testFindSnapshot() throws BusinessException {
		Snapshot snap = createPersistentSnapshot();

		Snapshot found = service.findSnapshotById(snap.getId().getIdSnapshot(),
				snap.getId().getIdDestino(), snap.getId().getIdCliente());
		assertNotNull(found);
		assertEquals(snap, found);
	}

	@Test
	public void testUpdateSnapshot() throws BusinessException {
		Snapshot snap = createPersistentSnapshot();
		Snapshot found = service.findSnapshotById(snap.getId().getIdSnapshot(),
				snap.getId().getIdDestino(), snap.getId().getIdCliente());
		assertNotNull(found);

		//
		// service.updateSnapshot(found);
		//
		// Snapshot found2 =
		// service.findSnapshotById(found.getId().getIdSnapshot(),
		// found.getId().getIdCliente());
		// assertNotNull(found2);
		// assertEquals(found.getIdTipoSnapshot(), found2.getIdTipoSnapshot());
	}

	private Snapshot createPersistentSnapshot() throws BusinessException {
		Snapshot snap = service.createSnapshot(des, info, NOW);
		service.addSnapshot(snap);

		assertNotNull(snap);
		assertNotNull(snap.getId().getIdSnapshot());
		assertNotNull(snap.getId().getIdCliente());

		return snap;
	}

}
