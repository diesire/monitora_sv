package es.uniovi.miw.monitora.server.ui.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashSet;

import es.uniovi.miw.monitora.server.conf.ServicesFactory;
import es.uniovi.miw.monitora.server.core.AgenteService;
import es.uniovi.miw.monitora.server.core.ClienteService;
import es.uniovi.miw.monitora.server.core.ConsultaService;
import es.uniovi.miw.monitora.server.core.DestinoService;
import es.uniovi.miw.monitora.server.core.InformeConsultaService;
import es.uniovi.miw.monitora.server.core.InformePlanDestinoService;
import es.uniovi.miw.monitora.server.core.InformeService;
import es.uniovi.miw.monitora.server.core.InformeTipoDestinoService;
import es.uniovi.miw.monitora.server.core.PlanificacionService;
import es.uniovi.miw.monitora.server.core.SnapshotService;
import es.uniovi.miw.monitora.server.core.TipoDestinoService;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.Consulta;
import es.uniovi.miw.monitora.server.model.Destino;
import es.uniovi.miw.monitora.server.model.InfPlanDest;
import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.InformeConsulta;
import es.uniovi.miw.monitora.server.model.InformeTipoDestino;
import es.uniovi.miw.monitora.server.model.Planificacion;
import es.uniovi.miw.monitora.server.model.Snapshot;
import es.uniovi.miw.monitora.server.model.TipoDestino;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class TestUtils {

	public static final Integer AGENTE_ID_INVALID = -1;
	public static final String INFORME1 = "Informe1";
	public static final String CLIENTE1 = "Cliente1";
	public static final String TIPO_DESTINO1 = "TipoDestino1";
	public static final String TIPO_DESTINO2 = "TipoDestino2";
	public static final String LOGO = "Logo";
	public static final String NOMBRE = "Nombre";
	public static final String COMENTARIOS = "Comentarios";
	public static final String IP_LOCAL = "127.0.0.1";

	public static final Date NOW = new Date(System.currentTimeMillis());

	public static final String TIPO_B = "B"; // TODO solo B o S

	public static final String DESC_C = "Descripción corta";
	public static final String DESC_L = "Descripción larga";

	// XXX Check entities relactions
	public static final Integer TIPO_DESTINO_0 = 0;

	public void testHierarchy(Agente hierarchy) throws BusinessException {
		Agente ag = hierarchy;
		if (ag == null) {
			ag = ServicesFactory.getAgenteService().findAgenteById(
					createHierarchy().getAgenteId());
		}

		assertNotNull(ag);

		// agente
		Destino des1 = ag.getDestinos().iterator().next();
		assertNotNull(des1);
		testLink(des1, ag);
		Cliente cli = ag.getCliente();
		assertNotNull(cli);
		testLink(cli, ag);
		testLink(cli, des1);

		// cliente
		Agente ag2 = cli.getAgentes().iterator().next();
		assertNotNull(ag2);
		assertEquals(ag, ag2);
		testLink(cli, ag2);
		Destino des2 = cli.getDestinos().iterator().next();
		assertNotNull(des2);
		assertEquals(des1, des2);
		testLink(cli, des2);
		testLink(des2, ag2);

		// destino
		Agente ag3 = des1.getAgentes().iterator().next();
		assertNotNull(ag3);
		assertEquals(ag, ag3);
		testLink(des1, ag3);
		Cliente cli2 = des1.getCliente();
		assertNotNull(cli2);
		assertEquals(cli, cli2);
		testLink(cli2, des1);
		InfPlanDest infoPlanDest = des1.getInfPlanDests().iterator().next();
		assertNotNull(infoPlanDest);
		testLink(infoPlanDest, des1, infoPlanDest.getInforme(),
				infoPlanDest.getPlanificacion());

		Snapshot snap = des1.getSnapshots().iterator().next();
		assertNotNull(snap);
		testLink(snap, des1, infoPlanDest.getInforme());

		// infoPlanDest
		Informe info = infoPlanDest.getInforme();
		assertNotNull(info);
		testLink(snap, des1, info);
		Planificacion plan = infoPlanDest.getPlanificacion();
		assertNotNull(plan);
		assertEquals(des1, infoPlanDest.getDestino());

		// info
		// info.getContenedores() (/TODO
		// info.getContenidos() //TODO
		InformeConsulta infoCon = info.getInformeConsultas().iterator().next();
		assertNotNull(infoCon);
		InformeTipoDestino infoTDes = info.getInformeTipoDestinos().iterator()
				.next();

		// infoCon(info, con)
		Consulta con = infoCon.getConsulta();
		assertNotNull(con);
		testLink(info, con, infoCon);

		// infoTDes(info, tDes)
		Informe info2 = infoTDes.getInforme();
		testLink(info2, con, infoCon);
		TipoDestino tDes = infoTDes.getTipoDestino();
		testLink(info, tDes, infoTDes);

		// TODO snap
		// TODO con

	}

	public Agente createHierarchy() throws BusinessException {
		Cliente cli = createCliente();
		Destino des = createDestino(cli);
		Agente agente = createAgente(cli, des);
		TipoDestino tDes = createTipoDestino();
		Informe info = createInforme();
		Planificacion plan = createPlanificación();
		createInfoPlanDestino(des, info, plan);
		createSnapshot(des, info);
		Consulta con = createConsulta(tDes);
		createInformeConsulta(info, con);
		createInformeTipoDestino(info, tDes);
		// createLineaCon(); //TODO
		return agente;
	}

	private InformeTipoDestino createInformeTipoDestino(Informe info,
			TipoDestino tDes) throws BusinessException {

		InformeTipoDestinoService infoTDesSrv = ServicesFactory
				.getInformeTipoDestinoService();

		InformeTipoDestino infoTDes = infoTDesSrv.createInformeTipoDestino(
				info, tDes);
		infoTDes.setPorDefecto("S"); // FIXME set by default or by constructor
		infoTDesSrv.addInformeTipoDestino(infoTDes);

		testLink(info, tDes, infoTDes);

		return infoTDes;

	}

	private void testLink(Informe info, TipoDestino tDes,
			InformeTipoDestino infoTDes) {
		assertTrue(new HashSet<InformeTipoDestino>(
				info.getInformeTipoDestinos()).contains(infoTDes));
		assertEquals(info, infoTDes.getInforme());
		assertTrue(new HashSet<InformeTipoDestino>(
				tDes.getInformeTipoDestinos()).contains(infoTDes));
		assertEquals(tDes, infoTDes.getTipoDestino());
	}

	private InformeConsulta createInformeConsulta(Informe info, Consulta con)
			throws BusinessException {
		InformeConsultaService infoConSrv = ServicesFactory
				.getInformeConsultaService();

		InformeConsulta infoCon = infoConSrv.createInformeConsulta(info, con,
				NOW);
		infoConSrv.addInformeConsulta(infoCon);

		testLink(info, con, infoCon);

		return infoCon;
	}

	private void testLink(Informe info, Consulta con, InformeConsulta infoCon) {
		assertEquals(info, infoCon.getInforme());
		assertTrue(new HashSet<InformeConsulta>(info.getInformeConsultas())
				.contains(infoCon));
		assertEquals(con, infoCon.getConsulta());
		assertTrue(new HashSet<InformeConsulta>(info.getInformeConsultas())
				.contains(infoCon));
	}

	private Consulta createConsulta(TipoDestino tDes) throws BusinessException {
		ConsultaService conSrv = ServicesFactory.getConsultaService();

		Consulta con = conSrv.createConsulta(TIPO_B, DESC_L, DESC_L, NOW);
		con.setComandoSo("ComandoSo");
		con.setSqlCreate("Create");
		con.setSqlSelect("Select");
		con.setTabla("Tabla");
		// con.setTcon1s(tcon1s);
		conSrv.addConsulta(con);

		// con.addTcon1(tcon1); //FIXME delete entity
		con.linkTipoDestino(tDes);
		testLink(con, tDes);

		return con;
	}

	private void testLink(Consulta con, TipoDestino tDes) {
		assertTrue(new HashSet<TipoDestino>(con.getTipoDestinos())
				.contains(tDes));
		assertTrue(new HashSet<Consulta>(tDes.getConsultas()).contains(con));
	}

	private Snapshot createSnapshot(Destino des, Informe info)
			throws BusinessException {
		SnapshotService snapSrv = ServicesFactory.getSnapshotService();

		Snapshot snap = snapSrv.createSnapshot(des, info, NOW);
		snapSrv.addSnapshot(snap);
		// snap.addTcon1(tcon1) //FIXME Delete entity

		testLink(snap, des, info);

		return snap;
	}

	private void testLink(Snapshot snap, Destino des, Informe info) {
		assertEquals(des, snap.getDestino());
		assertTrue(new HashSet<Snapshot>(des.getSnapshots()).contains(snap));
		assertEquals(info, snap.getInforme());
		assertTrue(new HashSet<Snapshot>(info.getSnapshots()).contains(snap));
	}

	private InfPlanDest createInfoPlanDestino(Destino des, Informe info,
			Planificacion plan) throws BusinessException {
		InformePlanDestinoService infoPlanDesSrv = ServicesFactory
				.getInfPlanDestService();

		InfPlanDest infoPlanDes = infoPlanDesSrv.createInfPlanDest(info, plan,
				des, NOW, NOW);
		infoPlanDesSrv.addInfPlanDest(infoPlanDes);

		testLink(infoPlanDes, des, info, plan);

		return infoPlanDes;
	}

	private void testLink(InfPlanDest infoPlanDes, Destino des, Informe info,
			Planificacion plan) {
		assertEquals(info, infoPlanDes.getInforme());
		assertTrue(new HashSet<InfPlanDest>(info.getInfPlanDests())
				.contains(infoPlanDes));
		assertEquals(plan, infoPlanDes.getPlanificacion());
		assertTrue(new HashSet<InfPlanDest>(plan.getInfPlanDests())
				.contains(infoPlanDes));
		assertEquals(des, infoPlanDes.getDestino());
		assertTrue(new HashSet<InfPlanDest>(des.getInfPlanDests())
				.contains(infoPlanDes));
	}

	private Planificacion createPlanificación() throws BusinessException {
		PlanificacionService planSrv = ServicesFactory
				.getPlanificacionService();

		Planificacion plan = planSrv.createPlanificacion(NOW);
		plan.setDescripcion(DESC_C);
		planSrv.addPlanificacion(plan);
		// plan.setLineaCrons(lineaCrons);
		// plan.addLineaCron(lineaCron)

		return plan;
	}

	private Informe createInforme() throws BusinessException {
		InformeService infoSrv = ServicesFactory.getInformeService();

		Informe info = infoSrv.createInforme(NOMBRE, DESC_C, NOW);
		// info.setContenedores(contenedores);
		// info.setContenidos(contenidos);
		info.setDescLarga(DESC_L);
		// info.addContenido(informe)
		// info.addInformeTipoDestino(informeTipoDestino);
		infoSrv.addInforme(info);

		// TODO: contenedore-contenido

		return info;
	}

	private TipoDestino createTipoDestino() throws BusinessException {
		TipoDestinoService tDEsSrv = ServicesFactory.getTipoDestinoService();

		TipoDestino tDes = tDEsSrv.createTipoDestino(NOMBRE);
		tDes.setDescripcion(DESC_C);
		tDEsSrv.addTipoDestino(tDes);

		return tDes;
	}

	private Cliente createCliente() throws BusinessException {
		ClienteService cliSrv = ServicesFactory.getClienteService();

		Cliente cli = cliSrv.createCliente(CLIENTE1);
		// cli.setLogo(LOGO); //FIXME: SQL error
		cli.setNombre(NOMBRE);
		cliSrv.addCliente(cli);

		return cli;
	}

	private Destino createDestino(Cliente cli) throws BusinessException {
		DestinoService desSrv = ServicesFactory.getDestinoService();

		Destino des = desSrv.createDestino(cli);
		des.setIdTipoDestino(TIPO_DESTINO_0); // FIXME TipoDestino

		des.linkCliente(cli);
		testLink(cli, des);
		desSrv.addDestino(des);

		return des;
	}

	private void testLink(Cliente cli, Destino des) {
		assertEquals(cli, des.getCliente());
		assertTrue(new HashSet<Destino>(cli.getDestinos()).contains(des));
	}

	private Agente createAgente(Cliente cli, Destino des)
			throws BusinessException {
		AgenteService agSrv = ServicesFactory.getAgenteService();

		Agente ag = agSrv.createAgente(cli);
		ag.setComentarios(COMENTARIOS);
		ag.setIpAgente(IP_LOCAL);

		ag.linkCliente(cli);
		testLink(cli, ag);

		ag.linkDestino(des);
		testLink(des, ag);

		agSrv.addAgente(ag);

		return ag;
	}

	private void testLink(Destino des, Agente ag) {
		assertTrue(new HashSet<Agente>(des.getAgentes()).contains(ag));
		assertTrue(new HashSet<Destino>(ag.getDestinos()).contains(des));
	}

	private void testLink(Cliente cli, Agente ag) {
		assertEquals(cli, ag.getCliente());
		assertTrue(new HashSet<Agente>(cli.getAgentes()).contains(ag));
	}

}
