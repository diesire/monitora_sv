package es.uniovi.miw.monitora.server.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * The persistent class for the TCON_1 database table.
 * 
 */
@Entity
@Table(name = "TCON_1")
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "dato1", scope=Tcon1.class)
public class Tcon1 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TCON_1_ID_CLIENTE",sequenceName = "TCON_1_ID_CLIENTE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TCON_1_ID_CLIENTE")
	private Integer dato1;

	private int dato2;

	// bi-directional many-to-one association to Consulta
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CONS_ID")
	private Consulta consulta;

	// bi-directional many-to-one association to Snapshot
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE"),
			@JoinColumn(name = "ID_DESTINO", referencedColumnName = "ID_DESTINO"),
//			@JoinColumn(name = "ID_INFORME", referencedColumnName = "ID_INFORME"),
			@JoinColumn(name = "ID_SNAPSHOT", referencedColumnName = "ID_SNAPSHOT") })
	private Snapshot snapshot;

	public Tcon1() {
	}

	public Integer getDato1() {
		return this.dato1;
	}

	public void setDato1(Integer dato1) {
		this.dato1 = dato1;
	}

	public int getDato2() {
		return this.dato2;
	}

	public void setDato2(int dato2) {
		this.dato2 = dato2;
	}

	public Consulta getConsulta() {
		return this.consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Snapshot getSnapshot() {
		return this.snapshot;
	}

	public void setSnapshot(Snapshot snapshot) {
		this.snapshot = snapshot;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dato1 == null) ? 0 : dato1.hashCode());
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
		Tcon1 other = (Tcon1) obj;
		if (dato1 == null) {
			if (other.dato1 != null)
				return false;
		} else if (!dato1.equals(other.dato1))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tcon1 [dato1=").append(dato1).append(", dato2=")
				.append(dato2).append("]");
		return builder.toString();
	}

}