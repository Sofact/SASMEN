package model;
// Generated 26/03/2022 08:12:38 AM by Hibernate Tools 5.3.0.Beta2

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SgdConvenioMarco generated by hbm2java
 */
@Entity
@Table(name = "sgd_convenio_marco", schema = "dbo")
public class SgdConvenioMarco implements java.io.Serializable {

	private Integer comaId;
	private String comaNombre;
	private String comaDescripcion;
	private Date comaFechaInicio;
	private Date comaFechaFin;
	private String comaEstado;

	public SgdConvenioMarco() {
	}

	public SgdConvenioMarco(String comaNombre, Date comaFechaInicio) {
		this.comaNombre = comaNombre;
		this.comaFechaInicio = comaFechaInicio;
	}

	public SgdConvenioMarco(String comaNombre, String comaDescripcion, Date comaFechaInicio,
			Date comaFechaFin, String comaEstado) {
		this.comaNombre = comaNombre;
		this.comaDescripcion = comaDescripcion;
		this.comaFechaInicio = comaFechaInicio;
		this.comaFechaFin = comaFechaFin;
		this.comaEstado = comaEstado;
	}

	public Integer getComaId() {
		return this.comaId;
	}

	public void setComaId(Integer comaId) {
		this.comaId = comaId;
	}

	public Serializable getComaNombre() {
		return this.comaNombre;
	}

	public void setComaNombre(String comaNombre) {
		this.comaNombre = comaNombre;
	}

	public Serializable getComaDescripcion() {
		return this.comaDescripcion;
	}

	public void setComaDescripcion(String comaDescripcion) {
		this.comaDescripcion = comaDescripcion;
	}

	public Date getComaFechaInicio() {
		return this.comaFechaInicio;
	}

	public void setComaFechaInicio(Date comaFechaInicio) {
		this.comaFechaInicio = comaFechaInicio;
	}

	public Date getComaFechaFin() {
		return this.comaFechaFin;
	}

	public void setComaFechaFin(Date comaFechaFin) {
		this.comaFechaFin = comaFechaFin;
	}

	public String getComaEstado() {
		return this.comaEstado;
	}

	public void setComaEstado(String comaEstado) {
		this.comaEstado = comaEstado;
	}

}
