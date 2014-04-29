package es.uniovi.miw.monitora.server.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uniovi.miw.monitora.core.model.Ack;
import es.uniovi.miw.monitora.core.model.Foo;
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
	public void getSingle() {
		final String serviceUri = ""; //no servlet mapping, empty URI
		final String pathToCall = "c2/test";
		final Response response = target(serviceUri).path(pathToCall)
				.request(MediaType.TEXT_XML).get();
		final Foo test2 = new Foo();
		test2.setBar("OK");
		checkGetCallResponse(response, test2);
	}
	
	@Test
	public void getPing() {
		String serviceUri = ""; //no servlet mapping, empty URI
		String clientId = "C2RestTestClient";
		String pathToCall = "c2/ping/" + clientId;
		
		logger.debug("Ping from {}", clientId);
		Response response = target(serviceUri).path(pathToCall)
				.request(MediaType.APPLICATION_JSON).get();
		Calendar update = response.readEntity(Ack.class).getUpdate();
		
		assertEquals(Response.Status.OK.getStatusCode(),
				response.getStatus());
		assertTrue(Calendar.getInstance().after(update));
	}

	private void checkGetCallResponse(final Response responseWrapper,
			final Foo expectedResponse) {
		assertEquals(Response.Status.OK.getStatusCode(),
				responseWrapper.getStatus());
		assertEquals(expectedResponse,
				responseWrapper.readEntity(Foo.class));
	}
}