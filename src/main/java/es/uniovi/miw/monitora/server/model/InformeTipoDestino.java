package es.uniovi.miw.monitora.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import es.uniovi.miw.monitora.server.model.keys.InformeTipoDestinoPK;

/**
 * The persistent class for the INFORME_TIPO_DESTINO database table.
 * 
 */
@Entity
@Table(name = "INFORME_TIPO_DESTINO")
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = InformeTipoDestino.class)
public class InformeTipoDestino implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private InformeTipoDestinoPK id = new InformeTipoDestinoPK();

	@Column(name = "POR_DEFECTO")
	private String porDefecto;

	// bi-directional many-to-one association to Informe
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_INFORME", insertable = false, updatable = false)
	private Informe informe;

	// bi-directional many-to-one association to TipoDestino
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_TIPO_DESTINO", insertable = false, updatable = false)
	private TipoDestino tipoDestino;

	public InformeTipoDestino() {
	}

	public InformeTipoDestino(Informe info, TipoDestino tDes) {
		this();
		setInforme(info);
		setTipoDestino(tDes);
	}

	public InformeTipoDestinoPK getId() {
		return this.id;
	}

	protected void setId(InformeTipoDestinoPK id) {
		this.id = id;
	}

	public String getPorDefecto() {
		return this.porDefecto;
	}

	public void setPorDefecto(String porDefecto) {
		this.porDefecto = porDefecto;
	}

	public Informe getInforme() {
		return this.informe;
	}

	protected void setInforme(Informe informe) {
		this.informe = informe;
		id.setIdInforme(informe.getInfoId());
		informe.addInformeTipoDestino(this);
	}

	public TipoDestino getTipoDestino() {
		return this.tipoDestino;
	}

	protected void setTipoDestino(TipoDestino tipoDestino) {
		this.tipoDestino = tipoDestino;
		id.setIdTipoDestino(tipoDestino.getIdTipoDestino());
		tipoDestino.addInformeTipoDestino(this);
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
		InformeTipoDestino other = (InformeTipoDestino) obj;
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
		builder.append("InformeTipoDestino [id=").append(id)
				.append(", porDefecto=").append(porDefecto).append("]");
		return builder.toString();
	}

}