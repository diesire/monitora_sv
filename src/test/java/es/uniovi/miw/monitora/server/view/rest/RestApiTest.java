package es.uniovi.miw.monitora.server.view.rest;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uniovi.miw.monitora.core.api.Ack;
import es.uniovi.miw.monitora.core.api.Task;
import es.uniovi.miw.monitora.core.snapshot.Snapshot;
import es.uniovi.miw.monitora.core.snapshot.StdoutResult;
import es.uniovi.miw.monitora.server.view.rest.RestServer;

/**
 * 
 * @author diesire
 * 
 */
public class RestApiTest extends JerseyTest {
	static private Logger logger = LoggerFactory.getLogger(RestApiTest.class);

	@Override
	protected Application configure() {
		logger.debug("App configuration");
		return new RestServer();
	}

	@Test
	public void pingGet() {
		String serviceUri = ""; // no servlet mapping, empty URI
		String clientId = "C2RestTestClient";
		String pathToCall = "c2/ping/" + clientId;

		logger.debug("Ping from {}", clientId);
		Response response = target(serviceUri).path(pathToCall)
				.request(MediaType.APPLICATION_JSON_TYPE).get();
		Calendar update = response.readEntity(Ack.class).getUpdate();

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertTrue(Calendar.getInstance().after(update));
	}

	@Test
	public void tasksGet() {
		List<Task> tasks = getTasks();
		assertNotNull(tasks);
		assertEquals(2, tasks.size());
	}

	private List<Task> getTasks() {
		String clientId = "C2RestTestClient";
		String serviceUri = ""; // no servlet mapping, empty URI
		String pathToCall = "c2/tasks/" + clientId;

		logger.debug("Tasks from {}", clientId);
		Response response = target(serviceUri).path(pathToCall)
				.request(MediaType.APPLICATION_JSON_TYPE).get();
		List<Task> tasks = response.readEntity(new GenericType<List<Task>>() {
		});

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		return tasks;
	}

	@Test
	public void postSnapshot() {
		String serviceUri = ""; // no servlet mapping, empty URI
		String clientId = "C2RestTestClient";
		String pathToCall = "c2/snapshots/" + clientId;

		logger.debug("Snapshot from {}", clientId);
		Snapshot snapshot = new Snapshot();
		snapshot.creationDate = Calendar.getInstance();
		snapshot.tasks = getTasks();
		// TODO add results

		Entity<Snapshot> entity = Entity.entity(snapshot,
				MediaType.APPLICATION_JSON_TYPE);
		Response response = target(serviceUri).path(pathToCall)
				.request(MediaType.APPLICATION_JSON_TYPE).post(entity);

		assertEquals("Response details: " + response,
				Response.Status.OK.getStatusCode(), response.getStatus());
	}
}