package es.uniovi.miw.monitora.server.ui.util;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class AddAgente {
	static private Logger logger = LoggerFactory.getLogger(AddAgente.class);
	private static int agenteId = -1;

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

	public static void main(String[] args) {
		System.out.println("Id agente");
		Scanner scan = new Scanner(System.in);
		agenteId = scan.nextInt();
		scan.close();
		new AddAgente(agenteId);
		System.out.println("Ok");
		System.exit(0);
	}

	public AddAgente(int agenteId) {
		graph = createGraph(agenteId);
		persistGraph(graph);
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

	private List<Object> createGraph(int agenteId) {
		logger.debug("createGraph");
		List<Object> res = new LinkedList<Object>();

		fecha = new Date(System.currentTimeMillis());

		informePadre = new Informe();
		informePadre.setNombre("Nombre");
		informePadre.setDescLarga("DescLarga");
		informePadre.setFUltimaModificacion(fecha);

		informeHijo = new Informe();
		informeHijo.setNombre("Nombre");
		informeHijo.setDescLarga("DescLarga");
		informeHijo.setFUltimaModificacion(fecha);

		consulta = new Consulta();
		consulta.setTipo("S");
		consulta.setDescCorta("DescCorta");
		consulta.setDescLarga("DescLarga");
		consulta.setFUltimaModificacion(fecha);
		consulta.setConsId(agenteId);

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
		agente.setAgenteId(agenteId);

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

		// logger.debug("\t -> {}", res);
		return res;
	}

	private void removeGraph() {
		// logger.debug("removeGraph");
		EntityManager mapper = Jpa.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		for (Object o : mergeGraph(mapper)) {
			mapper.remove(o);
			// logger.debug("\t - {}", o);
		}

		trx.commit();
		mapper.close();
	}
}