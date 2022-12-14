package model;
// Generated 26/03/2022 08:12:38 AM by Hibernate Tools 5.3.0.Beta2

import java.io.Serializable;
import java.util.Date;

/**
 * SgdConvenioEntidad generated by hbm2java
 */

public class SgdConvenioEntidad implements java.io.Serializable {

	private Integer coenId;
	private int convId;
	private int entId;
	private String coenCalidad;
	private String coenEstado;
	private Date coenFechaInicio;
	private Date coenFechaFin;

	public SgdConvenioEntidad() {
	}

	public SgdConvenioEntidad(int convId, int entId, String coenCalidad, String coenEstado,
			Date coenFechaInicio) {
		this.convId = convId;
		this.entId = entId;
		this.coenCalidad = coenCalidad;
		this.coenEstado = coenEstado;
		this.coenFechaInicio = coenFechaInicio;
	}

	public SgdConvenioEntidad(int convId, int entId, String coenCalidad, String coenEstado, Date coenFechaInicio,
			Date coenFechaFin) {
		this.convId = convId;
		this.entId = entId;
		this.coenCalidad = coenCalidad;
		this.coenEstado = coenEstado;
		this.coenFechaInicio = coenFechaInicio;
		this.coenFechaFin = coenFechaFin;
	}

	public Integer getCoenId() {
		return this.coenId;
	}

	public void setCoenId(Integer coenId) {
		this.coenId = coenId;
	}

	public int getConvId() {
		return this.convId;
	}

	public void setConvId(int convId) {
		this.convId = convId;
	}

	public int getEntId() {
		return this.entId;
	}

	public void setEntId(int entId) {
		this.entId = entId;
	}

	public Serializable getCoenCalidad() {
		return this.coenCalidad;
	}

	public void setCoenCalidad(String coenCalidad) {
		this.coenCalidad = coenCalidad;
	}

	public String getCoenEstado() {
		return this.coenEstado;
	}

	public void setCoenEstado(String coenEstado) {
		this.coenEstado = coenEstado;
	}

	public Date getCoenFechaInicio() {
		return this.coenFechaInicio;
	}

	public void setCoenFechaInicio(Date coenFechaInicio) {
		this.coenFechaInicio = coenFechaInicio;
	}

	public Date getCoenFechaFin() {
		return this.coenFechaFin;
	}

	public void setCoenFechaFin(Date coenFechaFin) {
		this.coenFechaFin = coenFechaFin;
	}

}
