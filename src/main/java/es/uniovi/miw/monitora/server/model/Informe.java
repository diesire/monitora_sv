package es.uniovi.miw.monitora.server.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "infoId", scope = Informe.class)
public class Informe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "INFO_INFO_ID_SEQ", sequenceName = "INFO_INFO_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INFO_INFO_ID_SEQ")
	@Column(name = "INFO_ID")
	private Integer infoId;

	@Column(name = "DESC_LARGA")
	private String descLarga;

	@Temporal(TemporalType.DATE)
	@Column(name = "F_ULTIMA_MODIFICACION")
	private Date fUltimaModificacion;

	// @Column(name = "ID_PLAN")
	// private int idPlan;

	private String nombre;

	// bi-directional many-to-one association to InformeConsulta
	@OneToMany(cascade={CascadeType.MERGE}, mappedBy = "informe", fetch = FetchType.EAGER)
	private Set<InformeConsulta> informeConsultas = new HashSet<InformeConsulta>();

	// bi-directional many-to-many association to Informe
	@ManyToMany(cascade={CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name = "INFORME_INFORME", joinColumns = { @JoinColumn(name = "ID_INFORME_CONTENIDO") }, inverseJoinColumns = { @JoinColumn(name = "ID_INFORME_CONTIENE") })
	private Set<Informe> contenidos = new HashSet<Informe>();

	// bi-directional many-to-many association to Informe
	@ManyToMany(cascade={CascadeType.MERGE}, mappedBy = "contenidos", fetch = FetchType.EAGER)
	private Set<Informe> contenedores = new HashSet<Informe>();

	// bi-directional many-to-one association to InformeTipoDestino
	@OneToMany(cascade={CascadeType.MERGE}, mappedBy = "informe", fetch = FetchType.EAGER)
	private Set<InformeTipoDestino> informeTipoDestinos = new HashSet<InformeTipoDestino>();

	// bi-directional many-to-one association to InfPlanDest
	@OneToMany(cascade={CascadeType.MERGE}, mappedBy = "informe", orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<InfPlanDest> infPlanDests = new HashSet<InfPlanDest>();

	// bi-directional many-to-one association to Snapshot
	@OneToMany(cascade={CascadeType.MERGE}, mappedBy = "informe", orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Snapshot> snapshots = new HashSet<Snapshot>();

	public Integer getInfoId() {
		return this.infoId;
	}

	protected void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

	public String getDescLarga() {
		return this.descLarga;
	}

	public void setDescLarga(String descLarga) {
		this.descLarga = descLarga;
	}

	public Date getFUltimaModificacion() {
		return this.fUltimaModificacion;
	}

	public void setFUltimaModificacion(Date fUltimaModificacion) {
		this.fUltimaModificacion = fUltimaModificacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<InformeConsulta> getInformeConsultas() {
		return this.informeConsultas;
	}

	protected void setInformeConsultas(Set<InformeConsulta> informeConsultas) {
		this.informeConsultas = informeConsultas;
	}

	public Set<Informe> getContenidos() {
		return this.contenidos;
	}

	protected void setContenidos(Set<Informe> contenidos) {
		this.contenidos = contenidos;
	}

	public Set<Informe> getContenedores() {
		return this.contenedores;
	}

	protected void setContenedores(Set<Informe> contenedores) {
		this.contenedores = contenedores;
	}

	public void addContenido(Informe informe) {
		informe.getContenedores().add(this);
		contenidos.add(informe);
	}

	public void removeContenido(Informe informe) {
		contenidos.remove(informe);
		informe.getContenedores().remove(this);
	}

	public Set<InformeTipoDestino> getInformeTipoDestinos() {
		return this.informeTipoDestinos;
	}

	protected void setInformeTipoDestinos(
			Set<InformeTipoDestino> informeTipoDestinos) {
		this.informeTipoDestinos = informeTipoDestinos;
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
		snapshot.setInforme(this);
		this.snapshots.add(snapshot);
	}

	public void removeSnapshot(Snapshot snapshot) {
		this.snapshots.remove(snapshot);
		snapshot.setInforme(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((infoId == null) ? 0 : infoId.hashCode());
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
		Informe other = (Informe) obj;
		if (infoId == null) {
			if (other.infoId != null)
				return false;
		} else if (!infoId.equals(other.infoId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Informe [infoId=").append(infoId)
				.append(", descLarga=").append(descLarga)
				.append(", fUltimaModificacion=").append(fUltimaModificacion)
				.append(", nombre=").append(nombre).append("]");
		return builder.toString();
	}

}