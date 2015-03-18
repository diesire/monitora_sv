package es.uniovi.miw.monitora.server.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import es.uniovi.miw.monitora.server.model.keys.InfPlanDestBorradoPK;

/**
 * The persistent class for the INF_PLAN_DEST_BORRADO database table.
 * 
 */
@Entity
@Table(name = "INF_PLAN_DEST_BORRADO")
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = InfPlanDestBorrado.class)
public class InfPlanDestBorrado implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private InfPlanDestBorradoPK id;

	public InfPlanDestBorrado() {
	}

	public InfPlanDestBorradoPK getId() {
		return this.id;
	}

	public void setId(InfPlanDestBorradoPK id) {
		this.id = id;
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
		InfPlanDestBorrado other = (InfPlanDestBorrado) obj;
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
		builder.append("InfPlanDestBorrado [id=").append(id).append("]");
		return builder.toString();
	}

}