package es.uniovi.miw.monitora.server.ui.util;

import static es.uniovi.miw.monitora.server.ui.util.TestUtils.DESC_L;
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
import es.uniovi.miw.monitora.server.core.LineaCronService;
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
import es.uniovi.miw.monitora.server.model.LineaCron;
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
	public static final String TIPO_S = "S";
	public static final String COMANDO_LS = "ls -la";

	public static final String DESC_C = "Descripción corta";
	public static final String DESC_L = "Descripción larga";

	public static final String EXPRESION = "0 0/1 * * * ?";

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

		// lineaCron
		LineaCron lCron = plan.getLineaCrons().iterator().next();
		assertNotNull(lCron);
		testLink(lCron, plan);

	}

	public Agente createHierarchy() throws BusinessException {
		Cliente cli = createCliente();
		Destino des = createDestino(cli);
		Agente agente = createAgente(des);
		TipoDestino tDes = createTipoDestino();
		Informe info = createInforme();
		Planificacion plan = createPlanificación();
		createInfoPlanDestino(des, info, plan);
		createSnapshot(des, info);
		Consulta con = createConsulta(tDes);
		createInformeConsulta(info, con);
		createInformeTipoDestino(info, tDes);
		createLineaCron(plan);
		return agente;
	}

	public void createLineaCron(Planificacion plan) throws BusinessException {
		LineaCronService lCronSrv = ServicesFactory.getLineaCronService();

		LineaCron lCron = lCronSrv.createLineaCron(plan);
		lCron.setDescripcion(DESC_C);
		lCron.setFUltimaModificacion(NOW);
		lCron.setExpresion(EXPRESION);
		lCronSrv.addLineaCron(lCron);

		testLink(lCron, plan);
	}

	public void testLink(LineaCron lCron, Planificacion plan) {
		assertEquals(plan, lCron.getPlanificacion());
		assertTrue(new HashSet<LineaCron>(plan.getLineaCrons()).contains(lCron));
	}

	public InformeTipoDestino createInformeTipoDestino(Informe info,
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

	public void testLink(Informe info, TipoDestino tDes,
			InformeTipoDestino infoTDes) {
		assertTrue(new HashSet<InformeTipoDestino>(
				info.getInformeTipoDestinos()).contains(infoTDes));
		assertEquals(info, infoTDes.getInforme());
		assertTrue(new HashSet<InformeTipoDestino>(
				tDes.getInformeTipoDestinos()).contains(infoTDes));
		assertEquals(tDes, infoTDes.getTipoDestino());
	}

	public InformeConsulta createInformeConsulta(Informe info, Consulta con)
			throws BusinessException {
		InformeConsultaService infoConSrv = ServicesFactory
				.getInformeConsultaService();

		InformeConsulta infoCon = infoConSrv.createInformeConsulta(info, con,
				NOW);
		infoConSrv.addInformeConsulta(infoCon);

		testLink(info, con, infoCon);

		return infoCon;
	}

	public void testLink(Informe info, Consulta con, InformeConsulta infoCon) {
		assertEquals(info, infoCon.getInforme());
		assertTrue(new HashSet<InformeConsulta>(info.getInformeConsultas())
				.contains(infoCon));
		assertEquals(con, infoCon.getConsulta());
		assertTrue(new HashSet<InformeConsulta>(info.getInformeConsultas())
				.contains(infoCon));
	}

	public Consulta createConsulta(TipoDestino tDes) throws BusinessException {
		ConsultaService conSrv = ServicesFactory.getConsultaService();

		Consulta con = conSrv.createConsulta(TIPO_S, DESC_L, DESC_L, NOW);
		con.setComandoSo(COMANDO_LS);
		con.setSqlCreate("CREATE TABLE COMANDO_LS (ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, COMANDO VARCHAR(4000))");
		con.setSqlInsert("INSERT INTO COMANDO_LS VALUES (?)");
		con.setSqlSelect("");
		con.setSqlDelete("DROP TABLE COMANDO_LS");
		con.setTabla("COMANDO_LS");

		conSrv.addConsulta(con);

		tDes.addConsulta(con);
		testLink(con, tDes);

		return con;
	}

	public void testLink(Consulta con, TipoDestino tDes) {
		assertTrue(new HashSet<TipoDestino>(con.getTipoDestinos())
				.contains(tDes));
		assertTrue(new HashSet<Consulta>(tDes.getConsultas()).contains(con));
	}

	public Snapshot createSnapshot(Destino des, Informe info)
			throws BusinessException {
		SnapshotService snapSrv = ServicesFactory.getSnapshotService();

		Snapshot snap = snapSrv.createSnapshot(des, info, NOW);
		snapSrv.addSnapshot(snap);
		// snap.addTcon1(tcon1) //FIXME Delete entity

		testLink(snap, des, info);

		return snap;
	}

	public void testLink(Snapshot snap, Destino des, Informe info) {
		assertEquals(des, snap.getDestino());
		assertTrue(new HashSet<Snapshot>(des.getSnapshots()).contains(snap));
		assertEquals(info, snap.getInforme());
		assertTrue(new HashSet<Snapshot>(info.getSnapshots()).contains(snap));
	}

	public InfPlanDest createInfoPlanDestino(Destino des, Informe info,
			Planificacion plan) throws BusinessException {
		InformePlanDestinoService infoPlanDesSrv = ServicesFactory
				.getInfPlanDestService();

		InfPlanDest infoPlanDes = infoPlanDesSrv.createInfPlanDest(info, plan,
				des, NOW, NOW);
		infoPlanDesSrv.addInfPlanDest(infoPlanDes);

		testLink(infoPlanDes, des, info, plan);

		return infoPlanDes;
	}

	public void testLink(InfPlanDest infoPlanDes, Destino des, Informe info,
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

	public Planificacion createPlanificación() throws BusinessException {
		PlanificacionService planSrv = ServicesFactory
				.getPlanificacionService();

		Planificacion plan = planSrv.createPlanificacion(NOW);
		plan.setDescripcion(DESC_C);
		planSrv.addPlanificacion(plan);
		// plan.setLineaCrons(lineaCrons);
		// plan.addLineaCron(lineaCron)

		return plan;
	}

	public Informe createInforme() throws BusinessException {
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

	public TipoDestino createTipoDestino() throws BusinessException {
		TipoDestinoService tDEsSrv = ServicesFactory.getTipoDestinoService();

		TipoDestino tDes = tDEsSrv.createTipoDestino(NOMBRE);
		tDes.setDescripcion(DESC_C);
		tDEsSrv.addTipoDestino(tDes);

		return tDes;
	}

	public Cliente createCliente() throws BusinessException {
		ClienteService cliSrv = ServicesFactory.getClienteService();

		Cliente cli = cliSrv.createCliente(CLIENTE1);
		// cli.setLogo(LOGO); //FIXME: SQL error
		cli.setNombre(NOMBRE);
		cliSrv.addCliente(cli);

		return cli;
	}

	public Destino createDestino(Cliente cli) throws BusinessException {
		DestinoService desSrv = ServicesFactory.getDestinoService();

		Destino des = desSrv.createDestino(cli);
		des.setIdTipoDestino(TIPO_DESTINO_0); // FIXME TipoDestino
		testLink(cli, des);
		desSrv.addDestino(des);
		testLink(cli, des);

		return des;
	}

	public void testLink(Cliente cli, Destino des) {
		assertEquals(cli, des.getCliente());
		assertTrue(new HashSet<Destino>(cli.getDestinos()).contains(des));
	}

	public Agente createAgente(Destino des) throws BusinessException {
		AgenteService agSrv = ServicesFactory.getAgenteService();

		Agente ag = agSrv.createAgente(des);
		testLink(des.getCliente(), ag);

		ag.setComentarios(COMENTARIOS);
		ag.setIpAgente(IP_LOCAL);

		testLink(des, ag);

		agSrv.addAgente(ag);
		testLink(des.getCliente(), ag);
		testLink(des, ag);

		return ag;
	}

	public void testLink(Destino des, Agente ag) {
		assertTrue(new HashSet<Agente>(des.getAgentes()).contains(ag));
		assertTrue(new HashSet<Destino>(ag.getDestinos()).contains(des));
	}

	public void testLink(Cliente cli, Agente ag) {
		assertEquals(cli, ag.getCliente());
		assertTrue(new HashSet<Agente>(cli.getAgentes()).contains(ag));
	}

	public Agente createHierarchyCascade() throws BusinessException {
		Cliente cli = ServicesFactory.getClienteService().createCliente(
				CLIENTE1);
		cli.setNombre(NOMBRE);

		Destino des = ServicesFactory.getDestinoService().createDestino(cli);
		des.setIdTipoDestino(TIPO_DESTINO_0); // FIXME TipoDestino

		Agente ag = ServicesFactory.getAgenteService().createAgente(des);
		testLink(des.getCliente(), ag);
		ag.setComentarios(COMENTARIOS);
		ag.setIpAgente(IP_LOCAL);
		testLink(des, ag);
		testLink(des.getCliente(), ag);

		TipoDestino tDes = ServicesFactory.getTipoDestinoService()
				.createTipoDestino(NOMBRE);
		tDes.setDescripcion(DESC_C);

		Informe info = ServicesFactory.getInformeService().createInforme(
				NOMBRE, DESC_C, NOW);
		// info.setContenedores(contenedores);
		// info.setContenidos(contenidos);
		info.setDescLarga(DESC_L);
		// info.addContenido(informe)
		// info.addInformeTipoDestino(informeTipoDestino);

		Planificacion plan = ServicesFactory.getPlanificacionService()
				.createPlanificacion(NOW);
		plan.setDescripcion(DESC_C);
		// plan.setLineaCrons(lineaCrons);
		// plan.addLineaCron(lineaCron)

		InfPlanDest infoPlanDes = ServicesFactory.getInfPlanDestService()
				.createInfPlanDest(info, plan, des, NOW, NOW);
		testLink(infoPlanDes, des, info, plan);

		Snapshot snap = ServicesFactory.getSnapshotService().createSnapshot(
				des, info, NOW);
		// snap.addTcon1(tcon1) //FIXME Delete entity
		testLink(snap, des, info);

		Consulta con = ServicesFactory.getConsultaService().createConsulta(
				TIPO_B, DESC_L, DESC_L, NOW);
		con.setComandoSo("ComandoSo");
		con.setSqlCreate("Create");
		con.setSqlSelect("Select");
		con.setTabla("Tabla");

		tDes.addConsulta(con);
		testLink(con, tDes);

		InformeConsulta infoCon = ServicesFactory.getInformeConsultaService()
				.createInformeConsulta(info, con, NOW);
		testLink(info, con, infoCon);

		InformeTipoDestino infoTDes = ServicesFactory
				.getInformeTipoDestinoService().createInformeTipoDestino(info,
						tDes);
		infoTDes.setPorDefecto("S"); // FIXME set by default or by constructor
		testLink(info, tDes, infoTDes);

		// createLineaCon(); //TODO

		LineaCron lCron = ServicesFactory.getLineaCronService()
				.createLineaCron(plan);
		lCron.setDescripcion(DESC_C);
		lCron.setFUltimaModificacion(NOW);
		lCron.setExpresion(EXPRESION);

		testLink(lCron, plan);

		ServicesFactory.getAgenteService().updateAgente(ag);
		return ag;
	}

}
