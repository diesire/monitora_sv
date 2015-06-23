package es.uniovi.miw.monitora.server.conf;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfTest {

	@Test
	public void get() {
		assertEquals("MONITORASV", Conf.get("dbname"));
	}

	@Test(expected = RuntimeException.class)
	public void getException() {
		Conf.get(null);
		fail("Must raise an exception");
	}

}
