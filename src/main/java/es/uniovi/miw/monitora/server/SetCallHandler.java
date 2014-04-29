package es.uniovi.miw.monitora.server;

import java.util.GregorianCalendar;

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

	public Ack ping() {
		Ack ack = new Ack();
		ack.setUpdate(new GregorianCalendar(2014, 0, 1));
		return ack;
	}
}
