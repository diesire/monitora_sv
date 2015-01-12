package es.uniovi.miw.monitora.server.model.keys;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * The primary key class for the INFORME_CONSULTA database table.
 * 
 */
@Embeddable
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = InformeConsultaPK.class)
public class InformeConsultaPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "ID_CONSULTA", insertable = false, updatable = false)
	private int idConsulta;

	@Column(name = "ID_INFORME", insertable = false, updatable = false)
	private int idInforme;

	public InformeConsultaPK() {
	}

	public int getIdConsulta() {
		return this.idConsulta;
	}

	public void setIdConsulta(int idConsulta) {
		this.idConsulta = idConsulta;
	}

	public int getIdInforme() {
		return this.idInforme;
	}

	public void setIdInforme(int idInforme) {
		this.idInforme = idInforme;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof InformeConsultaPK)) {
			return false;
		}
		InformeConsultaPK castOther = (InformeConsultaPK) other;
		return (this.idConsulta == castOther.idConsulta)
				&& (this.idInforme == castOther.idInforme);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idConsulta;
		hash = hash * prime + this.idInforme;

		return hash;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InformeConsultaPK [idConsulta=").append(idConsulta)
				.append(", idInforme=").append(idInforme).append("]");
		return builder.toString();
	}

}