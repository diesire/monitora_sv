package es.uniovi.miw.monitora.server.backend.task;

import java.util.ArrayList;

import es.uniovi.miw.monitora.core.api.Task;

public interface TaskBackend {

	ArrayList<Task> getTasks(String clientId);

}
