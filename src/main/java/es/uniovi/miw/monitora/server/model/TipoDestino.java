package es.uniovi.miw.monitora.server.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.HashSet;
import java.util.Set;

/**
 * The persistent class for the TIPO_DESTINO database table.
 * 
 */
@Entity
@Table(name = "TIPO_DESTINO")
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idTipoDestino", scope = TipoDestino.class)
public class TipoDestino implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TIPO_DEST_ID_TI_DEST_SEQ", sequenceName = "TIPO_DEST_ID_TI_DEST_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPO_DEST_ID_TI_DEST_SEQ")
	@Column(name = "ID_TIPO_DESTINO")
	private Integer idTipoDestino;

	private String descripcion;

	@Column(name = "NOMBRE_CORTO")
	private String nombreCorto;

	// bi-directional many-to-many association to Consulta
	@ManyToMany(mappedBy = "tipoDestinos", fetch = FetchType.EAGER)
	private Set<Consulta> consultas = new HashSet<Consulta>();

	// bi-directional many-to-one association to InformeTipoDestino
	@OneToMany(mappedBy = "tipoDestino", fetch = FetchType.EAGER)
	private Set<InformeTipoDestino> informeTipoDestinos = new HashSet<InformeTipoDestino>();

	public TipoDestino() {
	}

	public Integer getIdTipoDestino() {
		return this.idTipoDestino;
	}

	protected void setIdTipoDestino(Integer idTipoDestino) {
		this.idTipoDestino = idTipoDestino;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombreCorto() {
		return this.nombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	public Set<Consulta> getConsultas() {
		return this.consultas;
	}

	protected void setConsultas(Set<Consulta> consultas) {
		this.consultas = consultas;
	}

	protected Consulta addConsulta(Consulta consulta) {
		getConsultas().add(consulta);

		consulta.addTipoDestino(this);
		return consulta;
	}

	public Consulta removeConsulta(Consulta consulta) {
		getConsultas().remove(consulta);

		return consulta;
	}

	public Set<InformeTipoDestino> getInformeTipoDestinos() {
		return this.informeTipoDestinos;
	}

	protected void setInformeTipoDestinos(
			Set<InformeTipoDestino> informeTipoDestinos) {
		this.informeTipoDestinos = informeTipoDestinos;
	}

	protected InformeTipoDestino addInformeTipoDestino(
			InformeTipoDestino informeTipoDestino) {
		getInformeTipoDestinos().add(informeTipoDestino);

		return informeTipoDestino;
	}

	public InformeTipoDestino removeInformeTipoDestino(
			InformeTipoDestino informeTipoDestino) {
		getInformeTipoDestinos().remove(informeTipoDestino);
		informeTipoDestino.setTipoDestino(null);

		return informeTipoDestino;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idTipoDestino == null) ? 0 : idTipoDestino.hashCode());
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
		TipoDestino other = (TipoDestino) obj;
		if (idTipoDestino == null) {
			if (other.idTipoDestino != null)
				return false;
		} else if (!idTipoDestino.equals(other.idTipoDestino))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TipoDestino [idTipoDestino=").append(idTipoDestino)
				.append(", descripcion=").append(descripcion)
				.append(", nombreCorto=").append(nombreCorto).append("]");
		return builder.toString();
	}

}