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
 * The persistent class for the CONSULTA database table.
 * 
 */
@Entity
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@UUID")
public class Consulta implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String TIPO_BASE_DATOS = "B";
	public static final String TIPO_SISTEMA_OPERATIVO = "S";

	@Id
	@SequenceGenerator(name = "CONS_CONS_ID_SEQ", sequenceName = "CONS_CONS_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONS_CONS_ID_SEQ")
	@Column(name = "CONS_ID")
	private Integer consId;

	@Column(name = "COMANDO_SO")
	private String comandoSo;

	@Column(name = "DESC_CORTA")
	private String descCorta;

	@Column(name = "DESC_LARGA")
	private String descLarga;

	@Temporal(TemporalType.DATE)
	@Column(name = "F_ULTIMA_MODIFICACION")
	private Date fUltimaModificacion;

	@Column(name = "SQL_CREATE")
	private String sqlCreate;

	@Column(name = "SQL_SELECT")
	private String sqlSelect;

	@Column(name = "SQL_INSERT")
	private String sqlInsert;

	@Column(name = "SQL_DELETE")
	private String sqlDelete;

	@Column(name = "TABLA")
	private String tabla;

	@Column(name = "TIPO")
	private String tipo;

	// bi-directional many-to-many association to TipoDestino
	@ManyToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "CONSULTA_TIPO_DESTINO", joinColumns = { @JoinColumn(name = "ID_CONSULTA") }, inverseJoinColumns = { @JoinColumn(name = "ID_TIPO_DESTINO") })
	private Set<TipoDestino> tipoDestinos = new HashSet<TipoDestino>();

	// bi-directional many-to-one association to InformeConsulta
	@OneToMany(cascade = { CascadeType.MERGE }, mappedBy = "consulta", fetch = FetchType.EAGER)
	private Set<InformeConsulta> informeConsultas = new HashSet<InformeConsulta>();

	// bi-directional many-to-one association to Tcon1
	@OneToMany(mappedBy = "consulta", fetch = FetchType.EAGER)
	private Set<Tcon1> tcon1s;

	public Consulta() {
	}

	public Integer getConsId() {
		return this.consId;
	}

	protected void setConsId(Integer consId) {
		this.consId = consId;
	}

	public String getComandoSo() {
		return this.comandoSo;
	}

	public void setComandoSo(String comandoSo) {
		this.comandoSo = comandoSo;
	}

	public String getDescCorta() {
		return this.descCorta;
	}

	public void setDescCorta(String descCorta) {
		this.descCorta = descCorta;
	}

	public String getDescLarga() {
		return this.descLarga;
	}

	public void setDescLarga(String descLarga) {
		this.descLarga = descLarga;
	}

	public Date getFUltimaModificacion() {
		return this.fUltimaModificacion;
	}

	public void setFUltimaModificacion(Date fUltimaModificacion) {
		this.fUltimaModificacion = fUltimaModificacion;
	}

	public String getSqlCreate() {
		return this.sqlCreate;
	}

	public void setSqlCreate(String sqlCreate) {
		this.sqlCreate = sqlCreate;
	}

	public String getSqlSelect() {
		return this.sqlSelect;
	}

	public void setSqlSelect(String sqlSelect) {
		this.sqlSelect = sqlSelect;
	}

	public String getSqlInsert() {
		return sqlInsert;
	}

	public void setSqlInsert(String sqlInsert) {
		this.sqlInsert = sqlInsert;
	}

	public String getSqlDelete() {
		return sqlDelete;
	}

	public void setSqlDelete(String sqlDelete) {
		this.sqlDelete = sqlDelete;
	}

	public String getTabla() {
		return this.tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		if (tipo.compareToIgnoreCase(TIPO_BASE_DATOS) != 0
				&& tipo.compareToIgnoreCase(TIPO_SISTEMA_OPERATIVO) != 0) {
			throw new IllegalArgumentException(
					"Valid tipo: Data [B]ase or Operative [S]ystem");
		}
		this.tipo = tipo.toUpperCase();
	}

	public Set<TipoDestino> getTipoDestinos() {
		return this.tipoDestinos;
	}

	protected void setTipoDestinos(Set<TipoDestino> tipoDestinos) {
		this.tipoDestinos = tipoDestinos;
	}

	public Set<InformeConsulta> getInformeConsultas() {
		return this.informeConsultas;
	}

	protected void setInformeConsultas(Set<InformeConsulta> informeConsultas) {
		this.informeConsultas = informeConsultas;
	}

	public Set<Tcon1> getTcon1s() {
		return this.tcon1s;
	}

	protected void setTcon1s(Set<Tcon1> tcon1s) {
		this.tcon1s = tcon1s;
	}

	public void addTcon1(Tcon1 tcon1) {
		tcon1.setConsulta(this);
		this.tcon1s.add(tcon1);
	}

	public void removeTcon1(Tcon1 tcon1) {
		this.tcon1s.remove(tcon1);
		tcon1.setConsulta(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((consId == null) ? 0 : consId.hashCode());
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
		Consulta other = (Consulta) obj;
		if (consId == null) {
			if (other.consId != null)
				return false;
		} else if (!consId.equals(other.consId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Consulta [consId=").append(consId)
				.append(", comandoSo=").append(comandoSo)
				.append(", descCorta=").append(descCorta)
				.append(", descLarga=").append(descLarga)
				.append(", fUltimaModificacion=").append(fUltimaModificacion)
				.append(", sqlCreate=").append(sqlCreate)
				.append(", sqlSelect=").append(sqlSelect)
				.append(", sqlDelete=").append(sqlDelete)
				.append(", sqlInsert=").append(sqlInsert).append(", tabla=")
				.append(tabla).append(", tipo=").append(tipo).append("]");
		return builder.toString();
	}
}