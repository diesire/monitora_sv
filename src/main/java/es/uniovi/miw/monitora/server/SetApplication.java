package es.uniovi.miw.monitora.server;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

public class SetApplication extends ResourceConfig {

	public SetApplication() {
		this(new SetCallHandler());
	}

	public SetApplication(final SetCallHandler setCallHandler) {
		/*
		 * Register the mapping between internal exceptions and their outward
		 * facing messages.
		 */
		// register(InvalidRequestExceptionMapper.class);

		register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(setCallHandler).to(SetCallHandler.class);
			}
		});

		packages(true, "es.uniovi.miw.monitora.server.api");
	}
}