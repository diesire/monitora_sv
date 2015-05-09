package es.uniovi.miw.monitora.server.core;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.uniovi.miw.monitora.core.api.Ack;
import es.uniovi.miw.monitora.server.conf.ServicesFactory;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public class MonitoraServerServiceTest {

	private static final int TEST = 0;
	private MonitoraServerService service = ServicesFactory
			.getMonitoraServerService();
	private Calendar now;

	@Before
	public void setUp() throws Exception {
		now = Calendar.getInstance();
	}

	@Test
	public void testPing() throws BusinessException {
		Ack ack = service.ping(TEST);

		assertNotNull(ack);
		assertTrue(now.compareTo(ack.getUpdate()) < 0);
	}
}
