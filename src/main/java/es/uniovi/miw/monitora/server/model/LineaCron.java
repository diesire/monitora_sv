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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idLineaCron", scope=LineaCron.class)
public class LineaCron implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_LINEA_CRON")
	private int idLineaCron;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name = "F_ULTIMA_MODIFICACION")
	private Date fUltimaModificacion;

	// bi-directional many-to-one association to Planificacion
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PLAN")
	private Planificacion planificacion;

	public LineaCron() {
	}

	public int getIdLineaCron() {
		return this.idLineaCron;
	}

	public void setIdLineaCron(int idLineaCron) {
		this.idLineaCron = idLineaCron;
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

	public Planificacion getPlanificacion() {
		return this.planificacion;
	}

	public void setPlanificacion(Planificacion planificacion) {
		this.planificacion = planificacion;
	}

}