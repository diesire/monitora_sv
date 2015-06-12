package es.uniovi.miw.monitora.server.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * The persistent class for the PLANIFICACION database table.
 * 
 */
@Entity
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idPlan", scope = Planificacion.class)
public class Planificacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PLAN_ID_PLAN_SEQ", sequenceName = "PLAN_ID_PLAN_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAN_ID_PLAN_SEQ")
	@Column(name = "ID_PLAN")
	private Integer idPlan;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name = "F_ULTIMA_MODIFICACION")
	private Date fUltimaModificacion;

	// bi-directional many-to-one association to InfPlanDest
	@OneToMany(mappedBy = "planificacion", orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<InfPlanDest> infPlanDests = new HashSet<InfPlanDest>();

	// bi-directional many-to-one association to LineaCron
	@OneToMany(mappedBy = "planificacion", orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<LineaCron> lineaCrons = new HashSet<LineaCron>();

	public Planificacion() {
	}

	public Integer getIdPlan() {
		return this.idPlan;
	}

	protected void setIdPlan(Integer idPlan) {
		this.idPlan = idPlan;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFUltimaModificacion() {
		return this.fUltimaModificacion;
	}

	public void setFUltimaModificacion(Date fUltimaModificacion) {
		this.fUltimaModificacion = fUltimaModificacion;
	}

	public Set<InfPlanDest> getInfPlanDests() {
		return this.infPlanDests;
	}

	protected void setInfPlanDests(Set<InfPlanDest> infPlanDests) {
		this.infPlanDests = infPlanDests;
	}

	public void addInfPlanDest(InfPlanDest infPlanDest) {
		infPlanDest.setPlanificacion(this);
		infPlanDests.add(infPlanDest);
	}

	public InfPlanDest removeInfPlanDest(InfPlanDest infPlanDest) {
		infPlanDests.remove(infPlanDest);
		infPlanDest.setPlanificacion(null);

		return infPlanDest;
	}

	public Set<LineaCron> getLineaCrons() {
		return this.lineaCrons;
	}

	protected void setLineaCrons(Set<LineaCron> lineaCrons) {
		this.lineaCrons = lineaCrons;
	}

	public void addLineaCron(LineaCron lineaCron) {
		lineaCron.setPlanificacion(this);
		lineaCrons.add(lineaCron);
	}

	public void removeLineaCron(LineaCron lineaCron) {
		lineaCrons.remove(lineaCron);
		lineaCron.setPlanificacion(null);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Planificacion [idPlan=").append(idPlan)
				.append(", descripcion=").append(descripcion)
				.append(", fUltimaModificacion=").append(fUltimaModificacion)
				.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPlan == null) ? 0 : idPlan.hashCode());
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
		Planificacion other = (Planificacion) obj;
		if (idPlan == null) {
			if (other.idPlan != null)
				return false;
		} else if (!idPlan.equals(other.idPlan))
			return false;
		return true;
	}

}