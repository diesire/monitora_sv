package es.uniovi.miw.monitora.server;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uniovi.miw.monitora.core.model.Ack;
import es.uniovi.miw.monitora.core.model.Task;

public class SetCallHandler {
	static Logger logger = LoggerFactory.getLogger(SetCallHandler.class);

	public Ack ping(String clientId) {
		UpdateManager updater = new UpdateManager(clientId);
		Ack ack = new Ack();
		
		ack.setUpdate(updater.getLastUpdate());
		logger.trace("Ack to {}", clientId);
		return ack;
	}

	public List<Task> tasks(String clientId) {
		logger.trace("tasks for {}", clientId);
		return new TaskManager(clientId).getTasks();
	}
}
