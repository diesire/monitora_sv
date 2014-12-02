package es.uniovi.miw.monitora.server.core.task;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uniovi.miw.monitora.core.api.Task;
import es.uniovi.miw.monitora.server.backend.task.TaskBackend;
import es.uniovi.miw.monitora.server.backend.task.TaskBackendFactory;

public class TaskServer {
	static private Logger logger = LoggerFactory.getLogger(TaskServer.class);

	private String clientId;
	private TaskBackend taskBackend;

	public TaskServer(String clientId) {
		this.setClientId(clientId);
		this.taskBackend = TaskBackendFactory.create();
	}

	public List<Task> getTasks() {
		ArrayList<Task> tasks = taskBackend.getTasks(clientId);
		logger.debug("tasks {}", tasks.toString());
		return tasks;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
