package model;
// Generated 26/03/2022 08:12:38 AM by Hibernate Tools 5.3.0.Beta2

import java.io.Serializable;

/**
 * SgdMunicipio generated by hbm2java
 */
public class SgdMunicipio implements java.io.Serializable {

	private Integer muniId;
	private Serializable muniNombre;
	private Serializable muniCodDane;
	private int depaId;

	public SgdMunicipio() {
	}

	public SgdMunicipio(Serializable muniNombre, Serializable muniCodDane, int depaId) {
		this.muniNombre = muniNombre;
		this.muniCodDane = muniCodDane;
		this.depaId = depaId;
	}

	public Integer getMuniId() {
		return this.muniId;
	}

	public void setMuniId(Integer muniId) {
		this.muniId = muniId;
	}

	public Serializable getMuniNombre() {
		return this.muniNombre;
	}

	public void setMuniNombre(Serializable muniNombre) {
		this.muniNombre = muniNombre;
	}

	public Serializable getMuniCodDane() {
		return this.muniCodDane;
	}

	public void setMuniCodDane(Serializable muniCodDane) {
		this.muniCodDane = muniCodDane;
	}

	public int getDepaId() {
		return this.depaId;
	}

	public void setDepaId(int depaId) {
		this.depaId = depaId;
	}

}