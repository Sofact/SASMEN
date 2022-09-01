package model;
// Generated 26/03/2022 08:12:38 AM by Hibernate Tools 5.3.0.Beta2

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * SgdRubro generated by hbm2java
 */
public class SgdRubro implements java.io.Serializable {

	private Integer rubrId;
	private Serializable rubrNumResolucion;
	private Serializable rubrIdSolicitud;
	private BigDecimal rubrValor;
	private Date rubrFechaInicio;
	private Serializable rubrPeriodo;
	private int tiruId;

	public SgdRubro() {
	}

	public SgdRubro(Serializable rubrNumResolucion, Serializable rubrIdSolicitud, BigDecimal rubrValor,
			Date rubrFechaInicio, Serializable rubrPeriodo, int tiruId) {
		this.rubrNumResolucion = rubrNumResolucion;
		this.rubrIdSolicitud = rubrIdSolicitud;
		this.rubrValor = rubrValor;
		this.rubrFechaInicio = rubrFechaInicio;
		this.rubrPeriodo = rubrPeriodo;
		this.tiruId = tiruId;
	}

	public Integer getRubrId() {
		return this.rubrId;
	}

	public void setRubrId(Integer rubrId) {
		this.rubrId = rubrId;
	}

	public Serializable getRubrNumResolucion() {
		return this.rubrNumResolucion;
	}

	public void setRubrNumResolucion(Serializable rubrNumResolucion) {
		this.rubrNumResolucion = rubrNumResolucion;
	}

	public Serializable getRubrIdSolicitud() {
		return this.rubrIdSolicitud;
	}

	public void setRubrIdSolicitud(Serializable rubrIdSolicitud) {
		this.rubrIdSolicitud = rubrIdSolicitud;
	}

	public BigDecimal getRubrValor() {
		return this.rubrValor;
	}

	public void setRubrValor(BigDecimal rubrValor) {
		this.rubrValor = rubrValor;
	}

	public Date getRubrFechaInicio() {
		return this.rubrFechaInicio;
	}

	public void setRubrFechaInicio(Date rubrFechaInicio) {
		this.rubrFechaInicio = rubrFechaInicio;
	}

	public Serializable getRubrPeriodo() {
		return this.rubrPeriodo;
	}

	public void setRubrPeriodo(Serializable rubrPeriodo) {
		this.rubrPeriodo = rubrPeriodo;
	}

	public int getTiruId() {
		return this.tiruId;
	}

	public void setTiruId(int tiruId) {
		this.tiruId = tiruId;
	}

}
