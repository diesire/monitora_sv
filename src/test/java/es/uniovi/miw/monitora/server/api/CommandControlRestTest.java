package es.uniovi.miw.monitora.server.api;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
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

import es.uniovi.miw.monitora.core.model.Ack;
import es.uniovi.miw.monitora.core.model.Task;
import es.uniovi.miw.monitora.server.SetApplication;

/**
 * 
 * @author diesire
 *
 */
public class CommandControlRestTest extends JerseyTest {
	static private Logger logger = LoggerFactory.getLogger(CommandControlRestTest.class);

	@Override
	protected Application configure() {
		logger.debug("App configuration");
		return new SetApplication();
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