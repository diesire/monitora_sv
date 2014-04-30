package es.uniovi.miw.monitora.server.view.rest;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uniovi.miw.monitora.server.core.MonitoraServer;

public class RestServer extends ResourceConfig {
	static Logger logger = LoggerFactory.getLogger(RestServer.class);

	public RestServer() {
		this(new MonitoraServer());
	}

	public RestServer(final MonitoraServer monitoraServer) {
		/*
		 * Register the mapping between internal exceptions and their outward
		 * facing messages.
		 */
		// register(InvalidRequestExceptionMapper.class);

		logger.debug("binding server core");
		register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(monitoraServer).to(MonitoraServer.class);
			}
		});

		logger.debug("API found at {}", RestApi.class.getPackage().getName());
		packages(true, RestApi.class.getPackage().getName());
	}
}