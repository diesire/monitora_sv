package es.uniovi.miw.monitora.server.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import es.uniovi.miw.monitora.server.model.keys.DestinoPK;

/**
 * The persistent class for the DESTINO database table.
 * 
 */
@Entity
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@UUID")
public class Destino implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DestinoPK id = new DestinoPK();

	@Column(name = "ID_TIPO_DESTINO")
	private Integer idTipoDestino;

	// bi-directional many-to-many association to Agente
	@ManyToMany(cascade = { CascadeType.MERGE }, mappedBy = "destinos", fetch = FetchType.EAGER)
	private Set<Agente> agentes = new HashSet<Agente>();

	// bi-directional many-to-one association to Cliente
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "ID_CLIENTE", insertable = false, updatable = false)
	private Cliente cliente;

	// bi-directional many-to-one association to InfPlanDest
	@OneToMany(cascade = { CascadeType.MERGE }, mappedBy = "destino", orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<InfPlanDest> infPlanDests = new HashSet<InfPlanDest>();

	// bi-directional many-to-one association to Snapshot
	@OneToMany(cascade = { CascadeType.MERGE }, mappedBy = "destino", orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Snapshot> snapshots = new HashSet<Snapshot>();

	public Destino() {
	}

	public DestinoPK getId() {
		return this.id;
	}

	protected void setId(DestinoPK id) {
		this.id = id;
	}

	public Integer getIdTipoDestino() {
		return this.idTipoDestino;
	}

	public void setIdTipoDestino(Integer idTipoDestino) {
		this.idTipoDestino = idTipoDestino;
	}

	public Set<Agente> getAgentes() {
		return this.agentes;
	}

	protected void setAgentes(Set<Agente> agentes) {
		this.agentes = agentes;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	protected void setCliente(Cliente cliente) {
		this.cliente = cliente;
		getId().setIdCliente(cliente.getIdCliente());
	}

	public Set<InfPlanDest> getInfPlanDests() {
		return this.infPlanDests;
	}

	protected void setInfPlanDests(Set<InfPlanDest> infPlanDests) {
		this.infPlanDests = infPlanDests;
	}

	public Set<Snapshot> getSnapshots() {
		return this.snapshots;
	}

	protected void setSnapshots(Set<Snapshot> snapshots) {
		this.snapshots = snapshots;
	}

	public void addSnapshot(Snapshot snapshot) {
		snapshot.setDestino(this);
		snapshots.add(snapshot);
	}

	public void removeSnapshot(Snapshot snapshot) {
		snapshots.remove(snapshot);
		snapshot.setDestino(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Destino other = (Destino) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Destino [id=").append(id).append(", idTipoDestino=")
				.append(idTipoDestino).append("]");
		return builder.toString();
	}
}