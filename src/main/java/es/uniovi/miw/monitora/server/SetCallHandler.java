package es.uniovi.miw.monitora.server;

import es.uniovi.miw.monitora.bean.Foo;

public class SetCallHandler {

	public Foo get() {
		Foo test = new Foo();
		test.setBar("OK");
		return test;
	}
}
