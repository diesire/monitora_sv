package es.uniovi.miw.monitora.server.backend;

import java.util.ArrayList;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uniovi.miw.monitora.core.api.Task;
import es.uniovi.miw.monitora.core.snapshot.TaskResult;
import es.uniovi.miw.monitora.core.task.CommandType;
import es.uniovi.miw.monitora.core.task.SchedulerType;
import es.uniovi.miw.monitora.server.core.task.TaskServer;

public class SimpleTaskBackend implements TaskBackend{
	static private Logger logger = LoggerFactory.getLogger(SimpleTaskBackend.class);

	public ArrayList<Task> getTasks(String clientId) {
		// TODO Get from persistence Layer
		ArrayList<Task> tasks = new ArrayList<Task>();
	
		tasks.add(createTask(clientId, CommandType.SHELL, "ls", TaskResult.STDOUT, SchedulerType.CRON,
				"0/10 * * * * ?"));
		tasks.add(createTask(clientId, CommandType.QUERY, "select * from system", TaskResult.STDOUT, SchedulerType.CRON,
				"0/10 * * * * ?"));
		return tasks;
	}
	
	private Task createTask(String clientId, String command, String commandArgs, String resultType,
			String scheduler, String schedulerArgs) {
		Task task = new Task();
		task.setCommand(command);
		task.setCommandArgs(commandArgs);
		task.setScheduler(scheduler);
		task.setSchedulerArgs(schedulerArgs);
		task.setId(clientId + System.nanoTime());
		task.setCreationDate(Calendar.getInstance());
		task.setResultType(resultType);
		logger.debug("created new {}", task);
		return task;
	}

}
