package es.uniovi.miw.monitora.server.core;

import static es.uniovi.miw.monitora.server.ui.util.Utils.DESC_C;
import static es.uniovi.miw.monitora.server.ui.util.Utils.INFORME1;
import static es.uniovi.miw.monitora.server.ui.util.Utils.NOW;
import static es.uniovi.miw.monitora.server.ui.util.Utils.TIPO_DESTINO1;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.miw.monitora.server.conf.PersistenceFactory;
import es.uniovi.miw.monitora.server.conf.ServicesFactory;
import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.InformeTipoDestino;
import es.uniovi.miw.monitora.server.model.TipoDestino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.PersistenceService;

public class InformeTipoDestinoServiceTest {

	private static PersistenceService db;
	private static Informe info;
	private static TipoDestino tDes;
	private InformeTipoDestinoService service;

	@BeforeClass
	static public void setUpClass() throws Exception {
		db = PersistenceFactory.getPersistenceService();
		db.start();

		InformeService infoServ = ServicesFactory.getInformeService();
		info = infoServ.createInforme(INFORME1, DESC_C, NOW);
		infoServ.addInforme(info);

		TipoDestinoService tDesServ = ServicesFactory.getTipoDestinoService();
		tDes = tDesServ.createTipoDestino(TIPO_DESTINO1);
		tDesServ.addTipoDestino(tDes);
	}

	@Before
	public void setUp() throws Exception {
		service = ServicesFactory.getInformeTipoDestinoService();
	}

	@AfterClass
	public static void tearDownClass() throws BusinessException {
		db.stop();
	}

	@Test
	public void testCreateInformeTipoDestino() throws BusinessException {
		InformeTipoDestino infoTDes = service.createInformeTipoDestino(info,
				tDes);
		assertNotNull(infoTDes);
		assertNotNull(infoTDes.getId());
		assertNotNull(infoTDes.getId().getIdInforme());
		assertNotNull(infoTDes.getId().getIdTipoDestino());
	}

	/**
	 * Add null raise BusinessException
	 * 
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddInformeTipoDestinoNull() throws BusinessException {
		service.addInformeTipoDestino(null);
	}

	/**
	 * Add empty InformeTipoDestino raise BusinessException
	 * 
	 * @throws BusinessException
	 */
	@Test(expected = BusinessException.class)
	public void testAddInformeTipoDestinoEmpty() throws BusinessException {
		service.addInformeTipoDestino(new InformeTipoDestino());
	}

	/**
	 * 
	 * @throws BusinessException
	 */
	@Test
	public void testAddInformeTipoDestino() throws BusinessException {
		createPersistentInformeTipoDestino(info, tDes);
	}

	@Test(expected = BusinessException.class)
	public void testDeleteNull() throws BusinessException {
		service.deleteInformeTipoDestino(null, null);
	}

	@Test
	public void testDeleteInformeTipoDestino() throws BusinessException {
		InformeTipoDestino infoTDes = createPersistentInformeTipoDestino(info,
				tDes);

		service.deleteInformeTipoDestino(infoTDes.getId().getIdInforme(),
				infoTDes.getId().getIdTipoDestino());
	}

	@Test(expected = BusinessException.class)
	public void testFindNullInformeTipoDestino() throws BusinessException {
		service.findInformeTipoDestinoById(null, null);
	}

	@Test
	public void testFindInformeTipoDestino() throws BusinessException {
		InformeTipoDestino infoTDes = createPersistentInformeTipoDestino(info,
				tDes);

		InformeTipoDestino found = service.findInformeTipoDestinoById(infoTDes
				.getId().getIdInforme(), infoTDes.getId().getIdTipoDestino());
		assertNotNull(found);
		assertEquals(infoTDes, found);
	}

	@Test
	public void testUpdateInformeTipoDestino() throws BusinessException {
		InformeTipoDestino ag = createPersistentInformeTipoDestino(info, tDes);
		InformeTipoDestino found = service.findInformeTipoDestinoById(ag
				.getId().getIdInforme(), ag.getId().getIdTipoDestino());
		assertNotNull(found);

		found.setPorDefecto("N");
		service.updateInformeTipoDestino(found);

		InformeTipoDestino found2 = service.findInformeTipoDestinoById(found
				.getId().getIdInforme(), found.getId().getIdTipoDestino());

		assertNotNull(found2);
		assertEquals("N", found2.getPorDefecto());
	}

	private InformeTipoDestino createPersistentInformeTipoDestino(Informe info,
			TipoDestino tDes) throws BusinessException {
		InformeTipoDestino infoTDes = service.createInformeTipoDestino(info,
				tDes);
		infoTDes.setPorDefecto("S");
		findOrAdd(infoTDes);

		// FIXME compare against persisted instance

		assertNotNull(infoTDes.getId());
		assertNotNull(infoTDes.getId().getIdInforme());
		assertNotNull(infoTDes.getId().getIdTipoDestino());
		assertEquals("S", infoTDes.getPorDefecto());

		assertEquals(info, infoTDes.getInforme());
		assertTrue(new HashSet<InformeTipoDestino>(infoTDes.getInforme()
				.getInformeTipoDestinos()).contains(infoTDes));

		assertEquals(tDes, infoTDes.getTipoDestino());
		assertTrue(new HashSet<InformeTipoDestino>(infoTDes.getTipoDestino()
				.getInformeTipoDestinos()).contains(infoTDes));

		return infoTDes;
	}

	private void findOrAdd(InformeTipoDestino infoTDes)
			throws BusinessException {

		InformeTipoDestino found;
		try {
			found = service.findInformeTipoDestinoById(infoTDes.getId()
					.getIdInforme(), infoTDes.getId().getIdTipoDestino());
		} catch (Exception e) {
			found = null;
		}

		if (found == null) {
			service.addInformeTipoDestino(infoTDes);
		}
	}
}
