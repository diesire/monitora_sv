package es.uniovi.miw.monitora.server.view.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uniovi.miw.monitora.core.api.Ack;
import es.uniovi.miw.monitora.core.api.Task;
import es.uniovi.miw.monitora.core.snapshot.Snapshot;
import es.uniovi.miw.monitora.server.core.MonitoraServer;

/**
 * Root resource (exposed at "c2" path)
 * 
 * Open http://localhost:8080/monitora_sv/rest/c2/test
 */
@Path("c2")
public class RestApi {
	static private Logger logger = LoggerFactory.getLogger(RestApi.class);

	@Inject
	private MonitoraServer monitora;

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "app/json" media type.
	 * 
	 * @return Ack that will be returned as a app/json response.
	 */
	@GET
	@Path("/ping/{clientId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response ping(@Context UriInfo header,
			@PathParam("clientId") final String clientId) {
		logger.debug("GET {}", header.getPath());
		Ack ack = monitora.ping(clientId);
		return Response.status(Response.Status.OK).entity(ack).build();
	}

	@GET
	@Path("/tasks/{clientId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response tasks(@Context UriInfo header,
			@PathParam("clientId") final String clientId) {
		logger.debug("GET {}", header.getPath());
		List<Task> tasks = monitora.tasks(clientId);
		return Response.status(Response.Status.OK).entity(tasks).build();
	}

	@POST
	@Path("/snapshots/{clientId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response snapshots(@Context UriInfo header,
			@PathParam("clientId") final String clientId,
			final Snapshot snapshot) {
		logger.debug("POST {}", header.getPath());

		monitora.snapshots(clientId, snapshot);

		return Response.status(Response.Status.OK).build();
	}
}
