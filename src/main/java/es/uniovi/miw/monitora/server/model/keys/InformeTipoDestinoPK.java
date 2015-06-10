package es.uniovi.miw.monitora.server.model.keys;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * The primary key class for the INFORME_TIPO_DESTINO database table.
 * 
 */
@Embeddable
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@UUID")
public class InformeTipoDestinoPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "ID_INFORME", insertable = false, updatable = false)
	private Integer idInforme;

	@Column(name = "ID_TIPO_DESTINO", insertable = false, updatable = false)
	private Integer idTipoDestino;

	public InformeTipoDestinoPK() {
	}

	public Integer getIdInforme() {
		return this.idInforme;
	}

	public void setIdInforme(Integer idInforme) {
		this.idInforme = idInforme;
	}

	public Integer getIdTipoDestino() {
		return this.idTipoDestino;
	}

	public void setIdTipoDestino(Integer idTipoDestino) {
		this.idTipoDestino = idTipoDestino;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InformeTipoDestinoPK [idInforme=").append(idInforme)
				.append(", idTipoDestino=").append(idTipoDestino).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idInforme == null) ? 0 : idInforme.hashCode());
		result = prime * result
				+ ((idTipoDestino == null) ? 0 : idTipoDestino.hashCode());
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
		InformeTipoDestinoPK other = (InformeTipoDestinoPK) obj;
		if (idInforme == null) {
			if (other.idInforme != null)
				return false;
		} else if (!idInforme.equals(other.idInforme))
			return false;
		if (idTipoDestino == null) {
			if (other.idTipoDestino != null)
				return false;
		} else if (!idTipoDestino.equals(other.idTipoDestino))
			return false;
		return true;
	}

}