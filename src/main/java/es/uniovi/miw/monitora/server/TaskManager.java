package es.uniovi.miw.monitora.server;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uniovi.miw.monitora.core.model.CommandType;
import es.uniovi.miw.monitora.core.model.SchedulerType;
import es.uniovi.miw.monitora.core.model.Task;

public class TaskManager {
	static private Logger logger = LoggerFactory.getLogger(TaskManager.class);

	private String clientId;

	public TaskManager(String clientId) {
		this.setClientId(clientId);
	}

	private Task createTask(String command, String commandArgs,
			String scheduler, String schedulerArgs) {
		Task task = new Task();
		task.setCommand(command);
		task.setCommandArgs(commandArgs);
		task.setScheduler(scheduler);
		task.setSchedulerArgs(schedulerArgs);
		logger.debug("created new {}", task);
		return task;
	}

	public List<Task> getTasks() {
		// TODO Get from persistence Layer
		ArrayList<Task> tasks = new ArrayList<Task>();

		tasks.add(createTask(CommandType.SHELL, "ls", SchedulerType.CRON,
				"0 0 5 0"));
		tasks.add(createTask(CommandType.SHELL, "ls", SchedulerType.CRON,
				"0 0 6 0"));
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
