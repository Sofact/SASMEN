package model;
// Generated 26/03/2022 08:12:38 AM by Hibernate Tools 5.3.0.Beta2

import java.io.Serializable;

/**
 * SgdPrograma generated by hbm2java
 */
public class SgdPrograma implements java.io.Serializable {

	private Integer progId;
	private Serializable progNombre;
	private Serializable progCodigo;
	private int entiId;

	public SgdPrograma() {
	}

	public SgdPrograma(Serializable progNombre, Serializable progCodigo, int entiId) {
		this.progNombre = progNombre;
		this.progCodigo = progCodigo;
		this.entiId = entiId;
	}

	public Integer getProgId() {
		return this.progId;
	}

	public void setProgId(Integer progId) {
		this.progId = progId;
	}

	public Serializable getProgNombre() {
		return this.progNombre;
	}

	public void setProgNombre(Serializable progNombre) {
		this.progNombre = progNombre;
	}

	public Serializable getProgCodigo() {
		return this.progCodigo;
	}

	public void setProgCodigo(Serializable progCodigo) {
		this.progCodigo = progCodigo;
	}

	public int getEntiId() {
		return this.entiId;
	}

	public void setEntiId(int entiId) {
		this.entiId = entiId;
	}

}