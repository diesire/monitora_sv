package es.uniovi.miw.monitora.server.backend;

import java.util.ArrayList;

import es.uniovi.miw.monitora.core.api.Task;

public interface TaskBackend {

	ArrayList<Task> getTasks(String clientId);

}
