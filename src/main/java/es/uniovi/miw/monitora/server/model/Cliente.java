package es.uniovi.miw.monitora.server.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.HashSet;
import java.util.Set;

/**
 * The persistent class for the CLIENTE database table.
 * 
 */
@Entity
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@UUID")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CLI_ID_CLIENTE_SEQ", sequenceName = "CLI_ID_CLIENTE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLI_ID_CLIENTE_SEQ")
	@Column(name = "ID_CLIENTE")
	private Integer idCliente;

	@Column(name = "LOGO")
	private String logo;

	@Column(name = "NOMBRE")
	private String nombre;

	// bi-directional many-to-one association to Agente
	@OneToMany(cascade = { CascadeType.MERGE }, mappedBy = "cliente", orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Agente> agentes = new HashSet<Agente>();

	// bi-directional many-to-one association to Destino
	@OneToMany(cascade = { CascadeType.MERGE }, mappedBy = "cliente", orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Destino> destinos = new HashSet<Destino>();

	public Cliente() {
	}

	public Integer getIdCliente() {
		return this.idCliente;
	}

	protected void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Agente> getAgentes() {
		return this.agentes;
	}

	protected void setAgentes(Set<Agente> agentes) {
		this.agentes = agentes;
	}

	public void addAgente(Agente agente) {
		agente.setCliente(this);
		agentes.add(agente);
	}

	public void removeAgente(Agente agente) {
		agentes.remove(agente);
		agente.setCliente(null);
	}

	public Set<Destino> getDestinos() {
		return this.destinos;
	}

	protected void setDestinos(Set<Destino> destinos) {
		this.destinos = destinos;
	}

	public void addDestino(Destino destino) {
		destino.setCliente(this);
		destinos.add(destino);
	}

	public void removeDestino(Destino destino) {
		destinos.remove(destino);
		destino.setCliente(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idCliente == null) ? 0 : idCliente.hashCode());
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
		Cliente other = (Cliente) obj;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cliente [idCliente=").append(idCliente)
				.append(", logo=").append(logo).append(", nombre=")
				.append(nombre).append("]");
		return builder.toString();
	}

}