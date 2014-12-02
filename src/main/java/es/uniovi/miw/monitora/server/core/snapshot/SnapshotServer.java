package es.uniovi.miw.monitora.server.core.snapshot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uniovi.miw.monitora.core.snapshot.Snapshot;

public class SnapshotServer {

	static Logger logger = LoggerFactory.getLogger(SnapshotServer.class);
	
	private String clientId;

	public SnapshotServer(String clientId) {
		this.clientId = clientId;
	}

	public void addSnapshot(Snapshot snapshot) {
		// TODO Auto-generated method stub
		logger.info("snapshot received");
		logger.debug("{}", snapshot);
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

}
