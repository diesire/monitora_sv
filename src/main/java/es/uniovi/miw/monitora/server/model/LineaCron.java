package es.uniovi.miw.monitora.server.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Date;

/**
 * The persistent class for the LINEA_CRON database table.
 * 
 */
@Entity
@Table(name = "LINEA_CRON")
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@UUID")
public class LineaCron implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "LINEA_CRON_ID_LINEA_CRON_SEQ", sequenceName = "LINEA_CRON_ID_LINEA_CRON_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LINEA_CRON_ID_LINEA_CRON_SEQ")
	@Column(name = "ID_LINEA_CRON")
	private Integer idLineaCron;

	private String descripcion;

	private String expresion;

	@Temporal(TemporalType.DATE)
	@Column(name = "F_ULTIMA_MODIFICACION")
	private Date fUltimaModificacion;

	// bi-directional many-to-one association to Planificacion
	@ManyToOne(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PLAN")
	private Planificacion planificacion;

	public LineaCron() {
	}

	public Integer getIdLineaCron() {
		return this.idLineaCron;
	}

	public void setIdLineaCron(Integer idLineaCron) {
		this.idLineaCron = idLineaCron;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getExpresion() {
		return this.expresion;
	}

	public void setExpresion(String expresion) {
		this.expresion = expresion;
	}

	public Date getFUltimaModificacion() {
		return this.fUltimaModificacion;
	}

	public void setFUltimaModificacion(Date fUltimaModificacion) {
		this.fUltimaModificacion = fUltimaModificacion;
	}

	public Planificacion getPlanificacion() {
		return this.planificacion;
	}

	protected void setPlanificacion(Planificacion planificacion) {
		this.planificacion = planificacion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LineaCron [idLineaCron=").append(idLineaCron)
				.append(", descripcion=").append(descripcion)
				.append(", expresion=").append(expresion)
				.append(", fUltimaModificacion=").append(fUltimaModificacion)
				.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idLineaCron == null) ? 0 : idLineaCron.hashCode());
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
		LineaCron other = (LineaCron) obj;
		if (idLineaCron == null) {
			if (other.idLineaCron != null)
				return false;
		} else if (!idLineaCron.equals(other.idLineaCron))
			return false;
		return true;
	}

}