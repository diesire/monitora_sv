package es.uniovi.miw.monitora.server.view.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.List;

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
	public void getPing() {
		String serviceUri = ""; //no servlet mapping, empty URI
		String clientId = "C2RestTestClient";
		String pathToCall = "c2/ping/" + clientId;
		
		logger.debug("Ping from {}", clientId);
		Response response = target(serviceUri).path(pathToCall)
				.request(MediaType.APPLICATION_JSON_TYPE).get();
		Calendar update = response.readEntity(Ack.class).getUpdate();
		
		assertEquals(Response.Status.OK.getStatusCode(),
				response.getStatus());
		assertTrue(Calendar.getInstance().after(update));
	}
	
	@Test
	public void getTasks() {
		String serviceUri = ""; //no servlet mapping, empty URI
		String clientId = "C2RestTestClient";
		String pathToCall = "c2/tasks/" + clientId;
		
		logger.debug("Tasks from {}", clientId);
		Response response = target(serviceUri).path(pathToCall)
				.request(MediaType.APPLICATION_JSON_TYPE).get();
		List<Task> tasks = response.readEntity(new GenericType<List<Task>>(){});
		
		assertEquals(Response.Status.OK.getStatusCode(),
				response.getStatus());
		assertNotNull(tasks);
		assertEquals(2, tasks.size());
	}
}