package es.uniovi.miw.monitora.server.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * The persistent class for the AGENTE database table.
 * 
 */
@Entity
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "agenteId", scope = Agente.class)
public class Agente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "AGENTE_AGENTE_ID_SEQ", sequenceName = "AGENTE_AGENTE_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AGENTE_AGENTE_ID_SEQ")
	@Column(name = "AGENTE_ID")
	private Integer agenteId;

	@Column(name = "COMENTARIOS")
	private String comentarios;

	@Column(name = "IP_AGENTE")
	private String ipAgente;

	// bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente cliente;

	// bi-directional many-to-many association to Destino
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "AGENTE_DESTINO", joinColumns = { @JoinColumn(name = "ID_AGENTE", referencedColumnName = "AGENTE_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE"),
			@JoinColumn(name = "ID_DESTINO", referencedColumnName = "ID_DESTINO") })
	private Set<Destino> destinos = new HashSet<Destino>();

	public Agente() {
	}

	public Integer getAgenteId() {
		return this.agenteId;
	}

	protected void setAgenteId(Integer agenteId) {
		this.agenteId = agenteId;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getIpAgente() {
		return this.ipAgente;
	}

	public void setIpAgente(String ipAgente) {
		this.ipAgente = ipAgente;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	protected void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<Destino> getDestinos() {
		return this.destinos;
	}

	protected void setDestinos(Set<Destino> destinos) {
		this.destinos = destinos;
	}

	public void addDestino(Destino destino) {
		destino.getAgentes().add(this);
		destinos.add(destino);
	}

	public void removeDestino(Destino destino) {
		destinos.remove(destino);
		destino.getAgentes().remove(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((agenteId == null) ? 0 : agenteId.hashCode());
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
		Agente other = (Agente) obj;
		if (agenteId == null) {
			if (other.agenteId != null)
				return false;
		} else if (!agenteId.equals(other.agenteId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Agente [agenteId=").append(agenteId)
				.append(", comentarios=").append(comentarios)
				.append(", ipAgente=").append(ipAgente).append("]");
		return builder.toString();
	}
}