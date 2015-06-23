package es.uniovi.miw.monitora.server.core.impl;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uniovi.miw.monitora.core.api.Ack;
import es.uniovi.miw.monitora.core.utils.ZipUtils;
import es.uniovi.miw.monitora.server.conf.Conf;
import es.uniovi.miw.monitora.server.conf.PersistenceFactory;
import es.uniovi.miw.monitora.server.conf.ServicesFactory;
import es.uniovi.miw.monitora.server.core.ClienteService;
import es.uniovi.miw.monitora.server.core.MonitoraServerService;
import es.uniovi.miw.monitora.server.core.impl.agente.FindAgenteById;
import es.uniovi.miw.monitora.server.model.Agente;
import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.Snapshot;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;
import es.uniovi.miw.monitora.server.persistence.util.PersistenceService;

public class MonitoraServer implements MonitoraServerService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CommandExecutor executor = new CommandExecutor();
	private PersistenceService persistence;

	public MonitoraServer() throws BusinessException {
		persistence = PersistenceFactory.getPersistenceService();
	}

	@Override
	public Ack ping(int agenteId) throws BusinessException {
		logger.trace("Ping from", agenteId);
		return createAck(agenteId);
	}

	@Override
	public Agente getAgente(int agenteId) throws BusinessException {
		logger.trace("Agent from", agenteId);
		return (Agente) executor.execute(new FindAgenteById(agenteId));
		// FIXME call service layer
	}

	@Override
	public List<Agente> getAgentes() throws BusinessException {
		logger.trace("Agentes");
		return ServicesFactory.getAgenteService().findAll();
	}

	@Override
	public void setSnapshot(int agenteId, Snapshot snapshot)
			throws BusinessException {
		// Check Scp inbox
		Path zFile = Paths.get(Conf.get("server.snapshot.path")).resolve(
				snapshot.getSnapshotDirName() + ".zip");
		Path outFolder;
		try {
			outFolder = Files
					.createTempDirectory(snapshot.getSnapshotDirName());
			ZipUtils.unZipIt(zFile.toString(), outFolder.toString());
			DirectoryStream<Path> stream = Files.newDirectoryStream(outFolder);
			Iterator<Path> iter = stream.iterator();
			while (iter.hasNext()) {
				Path outFile = iter.next();
				try {
					persistence.fromXML(outFile);
				} catch (Exception e) {
					// try next
				}
			}
		} catch (IOException e) {
			throw new BusinessException(e);
		}

		// Update snapshot

		System.err.println("--------------- TODO ----------------");
	}

	private Ack createAck(int agenteId) {
		Ack ack = new Ack();
		// TODO check if agente exists
		ack.setUpdate(Calendar.getInstance());
		return ack;
	}

	@Override
	public void start() throws BusinessException {
		logger.trace("Start server");
		persistence.start();
	}

	@Override
	public void stop() throws BusinessException {
		logger.trace("Stop server");
		persistence.stop();
	}
}
