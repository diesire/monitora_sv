package es.uniovi.miw.monitora.server.core;

import java.util.Date;

import es.uniovi.miw.monitora.server.model.Cliente;
import es.uniovi.miw.monitora.server.model.Destino;
import es.uniovi.miw.monitora.server.model.Informe;
import es.uniovi.miw.monitora.server.model.Snapshot;
import es.uniovi.miw.monitora.server.model.exceptions.BusinessException;

public interface SnapshotService {
	Snapshot createSnapshot(Destino des, Informe info, Date fecha)
			throws BusinessException;

	void addSnapshot(Snapshot snap) throws BusinessException;

	void updateSnapshot(Snapshot snap) throws BusinessException;

	Snapshot findSnapshotById(Integer idSnapshot, Integer idDestino,
			Integer idCliente) throws BusinessException;

	void deleteSnapshot(Integer idSnapshot, Integer idDestino, Integer idCliente)
			throws BusinessException;
}
