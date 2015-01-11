package es.uniovi.miw.monitora.server.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uniovi.miw.monitora.core.api.Ack;
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
import es.uniovi.miw.monitora.server.persistence.util.Jpa;

/**
 * 
 * @author diesire
 * 
 */
public class RestApiTest extends JerseyTest {
	static private Logger logger = LoggerFactory.getLogger(RestApiTest.class);
	private int agenteId = 0;

	private Agente agente;
	private Cliente cliente;
	private Informe informePadre;
	private Informe informeHijo;
	private List<Object> graph;
	private Consulta consulta;
	private InformeConsulta informeConsulta;
	private TipoDestino tipoDestino;
	private InformeTipoDestino informeTipoDestino;
	private Destino destino;
	private Snapshot snapshot;
	private Date fecha;
	private Planificacion planificacion;
	private LineaCron linea1;
	private LineaCron linea2;
	private InfPlanDest infoPlanDest;

	@Override
	protected Application configure() {
		logger.debug("App configuration");
		return new RestServer();
	}

	@Test
	public void pingGet() {
		String serviceUri = ""; // no servlet mapping, empty URI
		String pathToCall = "c2/ping/" + agenteId;

		logger.debug("Ping from {}", agenteId);
		Response response = target(serviceUri).path(pathToCall)
				.request(MediaType.APPLICATION_JSON_TYPE).get();
		Calendar update = response.readEntity(Ack.class).getUpdate();

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertTrue(Calendar.getInstance().after(update));
	}

	@Test
	public void tasksGet() {
		Agente agente = getAgente();
		assertNotNull(agente);
	}

	private Agente getAgente() {
		String serviceUri = ""; // no servlet mapping, empty URI
		String pathToCall = "c2/agente/" + agenteId;

		logger.debug("Tasks from {}", agenteId);
		Response response = target(serviceUri).path(pathToCall)
				.request(MediaType.APPLICATION_JSON_TYPE).get();
		Agente agente = response.readEntity(Agente.class);

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		return agente;
	}

	@Test
	public void postSnapshot() {
		String serviceUri = ""; // no servlet mapping, empty URI
		String pathToCall = "c2/snapshot/" + agenteId;

		logger.debug("Snapshot from {}", agenteId);
		// Snapshot snapshot = new Snapshot();
		// snapshot.creationDate = Calendar.getInstance();
		// snapshot.tasks = getAgente();

		// Entity<Snapshot> entity = Entity.entity(snapshot,
		// MediaType.APPLICATION_JSON_TYPE);
		// Response response = target(serviceUri).path(pathToCall)
		// .request(MediaType.APPLICATION_JSON_TYPE).post(entity);
		//
		// assertEquals("Response details: " + response,
		// Response.Status.OK.getStatusCode(), response.getStatus());
	}

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		logger.debug("setUp");
		graph = createGraph();
		persistGraph(graph);
	}

	@After
	@Override
	public void tearDown() throws Exception {
		super.tearDown();
		logger.debug("tearDown");
		removeGraph();
	}

	private void persistGraph(List<Object> graph) {
		logger.debug("persistGraph {}", graph);
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		for (Object o : graph) {
			mapper.persist(o);
			logger.debug("\t + {}", o);
		}

		trx.commit();
		mapper.close();
	}

	private List<Object> createGraph() {
		logger.debug("createGraph");
		List<Object> res = new LinkedList<Object>();

		fecha = new Date(System.currentTimeMillis());

		informePadre = new Informe();
		informePadre.setNombre("Nombre");
		informePadre.setDescLarga("DescLarga");
		informePadre.setFUltimaModificacion(fecha);
		informePadre.setIdPlan(0);
		informePadre.setInfoId(0);

		informeHijo = new Informe();
		informeHijo.setNombre("Nombre");
		informeHijo.setDescLarga("DescLarga");
		informeHijo.setFUltimaModificacion(fecha);
		informeHijo.setIdPlan(0);
		informeHijo.setInfoId(1);

		consulta = new Consulta();
		consulta.setTipo("S");
		consulta.setDescCorta("DescCorta");
		consulta.setDescLarga("DescLarga");
		consulta.setFUltimaModificacion(fecha);

		informeConsulta = new InformeConsulta();
		informeConsulta.setFUltimaModificacion(fecha);

		informeTipoDestino = new InformeTipoDestino();
		informeTipoDestino.setPorDefecto("PorDefecto");

		tipoDestino = new TipoDestino();
		tipoDestino.setDescripcion("Descripción");
		tipoDestino.setNombreCorto("NombreCorto");

		cliente = new Cliente();
		cliente.setNombre("Cliente");

		agente = new Agente();
		agente.setComentarios("Comentarios");

		destino = new Destino();

		snapshot = new Snapshot();
		snapshot.setFecha(fecha);

		planificacion = new Planificacion();
		planificacion.setFUltimaModificacion(fecha);

		linea1 = new LineaCron();
		linea1.setDescripcion("Descripcion");
		linea1.setFUltimaModificacion(fecha);

		linea2 = new LineaCron();
		linea2.setDescripcion("Descripcion");
		linea2.setFUltimaModificacion(fecha);
		linea2.setIdLineaCron(1); // FIXME: auto id

		infoPlanDest = new InfPlanDest();
		infoPlanDest.setFUltimaAplicacion(fecha);
		infoPlanDest.setFUltimaModificacion(fecha);

		// link
		informePadre.addInfPlanDest(infoPlanDest);
		planificacion.addInfPlanDest(infoPlanDest);
		destino.addInfPlanDest(infoPlanDest);

		planificacion.addLineaCron(linea1);
		planificacion.addLineaCron(linea2);

		informePadre.addSnapshot(snapshot);
		destino.addSnapshot(snapshot);

		cliente.addAgente(agente);
		cliente.addDestino(destino);

		agente.addDestino(destino);
		destino.addAgente(agente);

		informePadre.addInformeConsulta(informeConsulta);
		consulta.addInformeConsulta(informeConsulta);

		consulta.addTipoDestino(tipoDestino);
		tipoDestino.addConsulta(consulta);

		informePadre.addInformeTipoDestino(informeTipoDestino);
		tipoDestino.addInformeTipoDestino(informeTipoDestino);

		informePadre.addContenido(informeHijo); // TODO add informe nesting

		res.add(cliente);
		res.add(agente);
		res.add(informeHijo);
		res.add(informePadre);
		res.add(consulta);
		res.add(tipoDestino);
		res.add(destino);
		res.add(snapshot);
		res.add(planificacion);
		res.add(linea1);
		res.add(linea2);
		res.add(infoPlanDest);

		logger.debug("\t -> {}", res);
		return res;
	}

	private List<Object> mergeGraph(EntityManager mapper) {
		logger.debug("mergeGraph (from mapper {})", mapper);
		List<Object> res = new LinkedList<Object>();
		Cliente cl = mapper.merge(cliente);
		Agente ag = mapper.merge(agente);
		Informe in1 = mapper.merge(informePadre);
		Informe in2 = mapper.merge(informeHijo);
		Consulta cons = mapper.merge(consulta);
		TipoDestino tDes = mapper.merge(tipoDestino);
		Destino des = mapper.merge(destino);
		Snapshot snap = mapper.merge(snapshot);
		Planificacion plan = mapper.merge(planificacion);
		LineaCron l1 = mapper.merge(linea1);
		LineaCron l2 = mapper.merge(linea2);
		InfPlanDest inPD = mapper.merge(infoPlanDest);

		res.add(cl);
		res.add(ag);
		res.add(in1);
		res.add(cons);
		res.add(tDes);
		res.add(in2);
		res.add(des);
		res.add(snap);
		res.add(plan);
		res.add(l1);
		res.add(l2);
		res.add(inPD);

		logger.debug("\t -> {}", res);
		return res;
	}

	private void removeGraph() {
		logger.debug("removeGraph");
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		for (Object o : mergeGraph(mapper)) {
			mapper.remove(o);
			logger.debug("\t - {}", o);
		}

		trx.commit();
		mapper.close();
	}
}