package es.uniovi.miw.monitora.server.model.keys;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * The primary key class for the SNAPSHOT database table.
 * 
 */
@Embeddable
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@UUID")
public class SnapshotPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "ID_SNAPSHOT")
	private Integer idSnapshot;

	@Column(name = "ID_CLIENTE", insertable = false, updatable = false)
	private Integer idCliente;

	@Column(name = "ID_DESTINO", insertable = false, updatable = false)
	private Integer idDestino;

	public SnapshotPK() {
	}

	public Integer getIdSnapshot() {
		return this.idSnapshot;
	}

	public void setIdSnapshot(Integer idSnapshot) {
		this.idSnapshot = idSnapshot;
	}

	public Integer getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdDestino() {
		return this.idDestino;
	}

	public void setIdDestino(Integer idDestino) {
		this.idDestino = idDestino;
	}

//	public Integer getIdInforme() {
//		return this.idInforme;
//	}
//
//	public void setIdInforme(Integer idInforme) {
//		this.idInforme = idInforme;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idCliente == null) ? 0 : idCliente.hashCode());
		result = prime * result
				+ ((idDestino == null) ? 0 : idDestino.hashCode());
//		result = prime * result
//				+ ((idInforme == null) ? 0 : idInforme.hashCode());
		result = prime * result
				+ ((idSnapshot == null) ? 0 : idSnapshot.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SnapshotPK other = (SnapshotPK) obj;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		if (idDestino == null) {
			if (other.idDestino != null)
				return false;
		} else if (!idDestino.equals(other.idDestino))
			return false;
//		if (idInforme == null) {
//			if (other.idInforme != null)
//				return false;
//		} else if (!idInforme.equals(other.idInforme))
//			return false;
		if (idSnapshot == null) {
			if (other.idSnapshot != null)
				return false;
		} else if (!idSnapshot.equals(other.idSnapshot))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SnapshotPK [idSnapshot=").append(idSnapshot)
				.append(", idCliente=").append(idCliente)
				.append(", idDestino=").append(idDestino)
//				.append(", idInforme=").append(idInforme)
				.append("]");
		return builder.toString();
	}
}