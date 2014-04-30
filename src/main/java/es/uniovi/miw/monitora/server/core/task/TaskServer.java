package es.uniovi.miw.monitora.server.core.task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uniovi.miw.monitora.core.api.Task;
import es.uniovi.miw.monitora.core.task.CommandType;
import es.uniovi.miw.monitora.core.task.SchedulerType;

public class TaskServer {
	static private Logger logger = LoggerFactory.getLogger(TaskServer.class);

	private String clientId;

	public TaskServer(String clientId) {
		this.setClientId(clientId);
	}

	private Task createTask(String command, String commandArgs,
			String scheduler, String schedulerArgs) {
		Task task = new Task();
		task.setCommand(command);
		task.setCommandArgs(commandArgs);
		task.setScheduler(scheduler);
		task.setSchedulerArgs(schedulerArgs);
		task.setId(clientId + System.nanoTime());
		task.setCreation(Calendar.getInstance());
		logger.debug("created new {}", task);
		return task;
	}

	public List<Task> getTasks() {
		// TODO Get from persistence Layer
		ArrayList<Task> tasks = new ArrayList<Task>();

		tasks.add(createTask(CommandType.SHELL, "ls", SchedulerType.CRON,
				"0/10 * * * * ?"));
		tasks.add(createTask(CommandType.QUERY, "select * from system", SchedulerType.CRON,
				"0/10 * * * * ?"));
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
