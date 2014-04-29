
package es.uniovi.miw.monitora.server.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uniovi.miw.monitora.core.model.Ack;
import es.uniovi.miw.monitora.core.model.Foo;
import es.uniovi.miw.monitora.server.SetCallHandler;

/**
 * Root resource (exposed at "c2" path)
 * 
 * Open http://localhost:8080/monitora_sv/rest/c2/test
 */
@Path("c2")
public class CommandControlRest {
	static private Logger logger = LoggerFactory.getLogger(CommandControlRest.class);
	
	@Inject
	private SetCallHandler callHandler;

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/xml" media type.
	 * 
	 * @return String that will be returned as a text/xml response.
	 */
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_XML)
	public Response simpleXml() {
		Foo result = callHandler.get();
		return Response.status(Response.Status.OK).entity(result).build();
	}
	
	@GET
	@Path("/ping/{clientId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response ping(@Context UriInfo header, @PathParam("clientId") final String clientId) {
		logger.debug("GET {}", header.getPath());
		Ack result = callHandler.ping(clientId);
		return Response.status(Response.Status.OK).entity(result).build();
	}
}
