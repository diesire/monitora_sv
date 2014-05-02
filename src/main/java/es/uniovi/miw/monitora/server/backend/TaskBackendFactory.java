package es.uniovi.miw.monitora.server.backend;


public class TaskBackendFactory {

	public static TaskBackend create() {
		// TODO Config in properties file
		return new SimpleTaskBackend();
	}

}
