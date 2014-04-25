package es.uniovi.miw.monitora.server.api;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import es.uniovi.miw.monitora.bean.Foo;
import es.uniovi.miw.monitora.server.SetApplication;

/**
 * 
 * @author diesire
 *
 */
public class CommandControlRestTest extends JerseyTest {

	@Override
	protected Application configure() {
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

	private void checkGetCallResponse(final Response responseWrapper,
			final Foo expectedResponse) {
		assertEquals(Response.Status.OK.getStatusCode(),
				responseWrapper.getStatus());
		assertEquals(expectedResponse,
				responseWrapper.readEntity(Foo.class));
	}
}