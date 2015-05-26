package es.uniovi.miw.monitora.server.core;

import static org.junit.Assert.*;
import static es.uniovi.miw.monitora.server.ui.util.Utils.*;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uniovi.miw.monitora.core.api.Ack;
import es.uniovi.miw.monitora.server.conf.ServicesFactory;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.Destino;
import es.uniovi.miw.monitora.server.model.InfPlanDest;
import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.Planificacion;
import es.uniovi.miw.monitora.server.model.Snapshot;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class MonitoraServerServiceTest {

	private static final int TEST = 0;
	private static MonitoraServerService service;
	private Calendar now;

	@BeforeClass
	public static void setUpClass() throws BusinessException {
		service = ServicesFactory.getMonitoraServerService();
		service.start();
	}

	@Before
	public void setUp() throws Exception {
		now = Calendar.getInstance();
	}

	@AfterClass
	public static void tearDownClass() throws BusinessException {
		service.stop();
	}

	@Test
	public void testPing() throws BusinessException {
		Ack ack = service.ping(TEST);

		assertNotNull(ack);
		assertTrue(now.compareTo(ack.getUpdate()) <= 0);
	}

	@Test
	public void testGetAgentes() throws BusinessException {
		List<Agente> agentes = service.getAgentes();

		assertNotNull(agentes);
	}

	@Test
	public void testGetAgente() throws BusinessException {
		List<Agente> agentes = service.getAgentes();

		Agente ag = service.getAgente(agentes.get(0).getAgenteId());

		assertNotNull(ag);

		Cliente cli = ag.getCliente();
		assertNotNull(cli);
		assertNotNull(cli.getIdCliente());
		assertTrue(cli.getAgentes().contains(ag));

		Set<Destino> desSet = ag.getDestinos();
		Destino des = desSet.iterator().next();
		assertNotNull(des);
		assertEquals(cli, des.getCliente());
		assertTrue(des.getAgentes().contains(ag));

		Set<Snapshot> snapSet = des.getSnapshots();
		Snapshot snap = snapSet.iterator().next();
		assertNotNull(snap);
		assertEquals(des, snap.getDestino());
		// TODO snap.getInforme()
		// TODO snap.getInfPlanDes()

	}

	@Test
	public void testCreateHierarchy() throws BusinessException {

		// Services
		ClienteService cliSrv = ServicesFactory.getClienteService();
		DestinoService desSrv = ServicesFactory.getDestinoService();
		AgenteService agSrv = ServicesFactory.getAgenteService();
		SnapshotService snapSrv = ServicesFactory.getSnapshotService();
		InformeService infoSrv = ServicesFactory.getInformeService();
		InformePlanDestinoService infoPlanDesSrv = ServicesFactory
				.getInfPlanDestService();
		PlanificacionService planSrv = ServicesFactory
				.getPlanificacionService();

		// Cliente
		Cliente cli = cliSrv.createCliente(CLIENTE1);
		// cli.setLogo(LOGO); //FIXME: SQL error
		cli.setNombre(NOMBRE);
		cliSrv.addCliente(cli);

		// Destino
		Destino des = desSrv.createDestino(cli);
		des.setIdTipoDestino(TIPO_DESTINO_0);
		desSrv.addDestino(des);

		des.linkCliente(cli);
		assertEquals(cli, des.getCliente());
		assertTrue(new HashSet<Destino>(cli.getDestinos()).contains(des));

		// Agente
		Agente ag = agSrv.createAgente(cli);
		ag.setComentarios(COMENTARIOS);
		ag.setIpAgente(IP_LOCAL);
		agSrv.addAgente(ag);

		ag.linkCliente(cli);
		assertEquals(cli, ag.getCliente());
		assertTrue(new HashSet<Agente>(cli.getAgentes()).contains(ag));

		ag.linkDestino(des);
		assertTrue(new HashSet<Agente>(des.getAgentes()).contains(ag));
		assertTrue(new HashSet<Destino>(ag.getDestinos()).contains(des));

		// Informe
		Informe info = infoSrv.createInforme(NOMBRE, DESC_C, NOW);
		// info.setContenedores(contenedores);
		// info.setContenidos(contenidos);
		info.setDescLarga(DESC_L);
		// info.setInformeConsultas(informeConsultas);
		// info.addContenido(informe)
		// info.addInformeConsulta(informeConsulta);
		// info.addInformeTipoDestino(informeTipoDestino);
		infoSrv.addInforme(info);

		// Planificacion
		Planificacion plan = planSrv.createPlanificacion(NOW);
		plan.setDescripcion(DESC_C);
		planSrv.addPlanificacion(plan);
		// plan.setLineaCrons(lineaCrons);
		// plan.addLineaCron(lineaCron)

		// InfoPlanDestino
		InfPlanDest infoPlanDes = infoPlanDesSrv.createInfPlanDest(info, plan,
				des, NOW, NOW);
		infoPlanDesSrv.addInfPlanDest(infoPlanDes);

		// Snapshot
		Snapshot snap = snapSrv.createSnapshot(des, info, NOW);
		snapSrv.addSnapshot(snap);
		// snap.addTcon1(tcon1) //FIXME Delete entity
	}
}
