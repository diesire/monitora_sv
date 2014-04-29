package es.uniovi.miw.monitora.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uniovi.miw.monitora.core.model.Ack;
import es.uniovi.miw.monitora.core.model.Foo;

public class SetCallHandler {
	static Logger logger = LoggerFactory.getLogger(SetCallHandler.class);

	public Foo get() {
		Foo test = new Foo();
		test.setBar("OK");
		return test;
	}

	public Ack ping(String clientId) {
		UpdateManager updater = new UpdateManager(clientId);
		Ack ack = new Ack();
		
		ack.setUpdate(updater.getLastUpdate());
		logger.trace("Ack to {}", clientId);
		return ack;
	}
}
