package es.uniovi.miw.monitora.server.core.update;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uniovi.miw.monitora.server.core.MonitoraServer;

public class UpdateServer {
	static Logger logger = LoggerFactory.getLogger(MonitoraServer.class);

	private String clientId;
	private Calendar lastUpdate;

	public UpdateServer(String clientId) {
		this.setClientId(clientId);
		//PersistenceLayer.getLastUpdate(); //TODO get from persistence layer
		this.setLastUpdate(Calendar.getInstance());
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Calendar getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Calendar lastUpdate) {
		logger.debug("set last update on {}", lastUpdate.getTime().toString());
		this.lastUpdate = lastUpdate;
	}
}
