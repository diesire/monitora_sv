package es.uniovi.miw.monitora.server.backend.task;

import java.util.ArrayList;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uniovi.miw.monitora.core.api.Task;
import es.uniovi.miw.monitora.core.snapshot.TaskResult;
import es.uniovi.miw.monitora.core.task.Command;
import es.uniovi.miw.monitora.core.task.CommandType;
import es.uniovi.miw.monitora.core.task.SchedulerType;

public class SimpleTaskBackend implements TaskBackend {
	static private Logger logger = LoggerFactory
			.getLogger(SimpleTaskBackend.class);

	public ArrayList<Task> getTasks(String clientId) {
		ArrayList<Task> tasks = new ArrayList<Task>();

		tasks.add(createTask(clientId, new Command(CommandType.SHELL, "ls",
				"-la"), TaskResult.STDOUT, SchedulerType.CRON, "1 * * * * ?"));
		tasks.add(createTask(clientId, new Command(CommandType.SHELL, "touch",
				"deleteme"), TaskResult.STDOUT, SchedulerType.CRON,
				"1 * * * * ?"));
		tasks.add(createTask(clientId, new Command(CommandType.QUERY,
				"select * from system"), TaskResult.STDOUT, SchedulerType.CRON,
				"1 * * * * ?"));
		return tasks;
	}

	private Task createTask(String clientId, Command command,
			String resultType, String scheduler, String schedulerArgs) {
		Task task = new Task();
		task.setCommand(command);
		task.setScheduler(scheduler);
		task.setSchedulerArgs(schedulerArgs);
		task.setId(clientId + System.nanoTime());
		task.setCreationDate(Calendar.getInstance());
		task.setResultType(resultType);
		logger.debug("created new {}", task);
		return task;
	}

}
