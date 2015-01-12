package es.uniovi.miw.monitora.server.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Date;

/**
 * The persistent class for the LOG_ERROR database table.
 * 
 */
@Entity
@Table(name = "LOG_ERROR")
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "codigo", scope=LogError.class)
public class LogError implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codigo;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private String modulo;

	public LogError() {
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getModulo() {
		return this.modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

}