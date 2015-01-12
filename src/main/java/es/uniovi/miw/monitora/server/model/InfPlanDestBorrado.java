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

}