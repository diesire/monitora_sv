package es.uniovi.miw.monitora.server.service;

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
import es.uniovi.miw.monitora.core.snapshot.Snapshot;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

/**
 * Root resource (exposed at "c2" path)
 * 
 * Open http://localhost:8080/monitora_sv/rest/<ROOT_PATH>
 */
@Path(RestApi.ROOT_PATH)
public class RestApi {
	public static final String ROOT_PATH = "c2";
	static private Logger logger = LoggerFactory.getLogger(RestApi.class);

	@Inject
	private es.uniovi.miw.monitora.server.core.impl.MonitoraServer monitora;

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "app/json" media type.
	 * 
	 * @return Ack that will be returned as a app/json response.
	 */
	@GET
	@Path("/ping/{agenteId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response ping(@Context UriInfo header,
			@PathParam("agenteId") final int agenteId) {

		logger.debug("GET {}", header.getPath());

		try {

			Ack ack = monitora.ping(agenteId);
			return Response.status(Response.Status.OK).entity(ack).build();
		} catch (BusinessException e) {

			logger.error(e.getLocalizedMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.build();
		}
	}

	@GET
	@Path("/agente/{agenteId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response agente(@Context UriInfo header,
			@PathParam("agenteId") final int agenteId) {

		logger.debug("GET {}", header.getPath());

		try {

			Agente agente;
			agente = monitora.getAgente(agenteId);
			return Response.status(Response.Status.OK).entity(agente).build();
		} catch (BusinessException e) {

			logger.error(e.getLocalizedMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.build();
		}
	}

	@POST
	@Path("/snapshot/{agenteId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response snapshot(@Context UriInfo header,
			@PathParam("agenteId") final int agenteId, final Snapshot snapshot) {

		logger.debug("POST {}", header.getPath());

		try {

			monitora.setSnapshot(agenteId, snapshot);
			return Response.status(Response.Status.OK).build();
		} catch (BusinessException e) {

			logger.error(e.getLocalizedMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.build();
		}
	}
}