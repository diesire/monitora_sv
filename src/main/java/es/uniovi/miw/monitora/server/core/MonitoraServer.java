package es.uniovi.miw.monitora.server.core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uniovi.miw.monitora.core.api.Ack;
import es.uniovi.miw.monitora.core.api.Task;
import es.uniovi.miw.monitora.server.core.task.TaskServer;
import es.uniovi.miw.monitora.server.core.update.UpdateServer;

public class MonitoraServer {
	static Logger logger = LoggerFactory.getLogger(MonitoraServer.class);

	public Ack ping(String clientId) {
		UpdateServer updater = new UpdateServer(clientId);
		Ack ack = new Ack();
		
		ack.setUpdate(updater.getLastUpdate());
		logger.trace("Ack to {}", clientId);
		return ack;
	}

	public List<Task> tasks(String clientId) {
		logger.trace("tasks for {}", clientId);
		return new TaskServer(clientId).getTasks();
	}
}
