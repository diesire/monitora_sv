package es.uniovi.miw.monitora.server;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateManager {
	static Logger logger = LoggerFactory.getLogger(SetCallHandler.class);

	private String clientId;
	private Calendar lastUpdate;

	public UpdateManager(String clientId) {
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
