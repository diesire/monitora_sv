package es.uniovi.miw.monitora.server.ui.util;

import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.impl.HsqldbService;

public class FillDB {

	public static void main(String[] args) throws BusinessException {
		new FillDB().run();
		System.exit(0);
	}

	private void run() throws BusinessException {
		HsqldbService db = new HsqldbService();
		TestUtils tUtils = new TestUtils();
		db.start();
		Agente ag = tUtils.createHierarchy();
		tUtils.testHierarchy(ag);
		db.stop();
	}
}
