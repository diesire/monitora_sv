package es.uniovi.miw.monitora.server.model.keys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * The primary key class for the DESTINO database table.
 * 
 */
@Embeddable
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = DestinoPK.class)
public class DestinoPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "ID_CLIENTE", insertable = false, updatable = false)
	private Integer idCliente;

	@Column(name = "ID_DESTINO")
	private Integer idDestino;

	public DestinoPK() {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DestinoPK [idCliente=").append(idCliente)
				.append(", idDestino=").append(idDestino).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idCliente == null) ? 0 : idCliente.hashCode());
		result = prime * result
				+ ((idDestino == null) ? 0 : idDestino.hashCode());
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
		DestinoPK other = (DestinoPK) obj;
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
		return true;
	}

	

}