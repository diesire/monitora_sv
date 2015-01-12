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
	private int dato1;

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
			@JoinColumn(name = "ID_INFORME", referencedColumnName = "ID_INFORME"),
			@JoinColumn(name = "ID_SNAPSHOT", referencedColumnName = "ID_SNAPSHOT") })
	private Snapshot snapshot;

	public Tcon1() {
	}

	public int getDato1() {
		return this.dato1;
	}

	public void setDato1(int dato1) {
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

}