package model;
// Generated 26/03/2022 08:12:38 AM by Hibernate Tools 5.3.0.Beta2

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * SgdPersonaProgramaConvenio generated by hbm2java
 */
public class SgdPersonaProgramaConvenio implements java.io.Serializable {

	private Integer pecoId;
	private int persId;
	private int convId;
	private Serializable pecoCalidad;
	private Serializable pecoEstado;
	private Serializable pecoIdSolicitud;
	private int progId;
	private Serializable pecoSemestre;
	private int pecoYear;
	private BigDecimal pecoValorMatricula;
	private Serializable pecoRangoMatricula;
	private Serializable pegoElegible;

	public SgdPersonaProgramaConvenio() {
	}

	public SgdPersonaProgramaConvenio(int persId, int convId, Serializable pecoCalidad, Serializable pecoEstado,
			Serializable pecoIdSolicitud, int progId, Serializable pecoSemestre, int pecoYear,
			BigDecimal pecoValorMatricula, Serializable pecoRangoMatricula, Serializable pegoElegible) {
		this.persId = persId;
		this.convId = convId;
		this.pecoCalidad = pecoCalidad;
		this.pecoEstado = pecoEstado;
		this.pecoIdSolicitud = pecoIdSolicitud;
		this.progId = progId;
		this.pecoSemestre = pecoSemestre;
		this.pecoYear = pecoYear;
		this.pecoValorMatricula = pecoValorMatricula;
		this.pecoRangoMatricula = pecoRangoMatricula;
		this.pegoElegible = pegoElegible;
	}

	public Integer getPecoId() {
		return this.pecoId;
	}

	public void setPecoId(Integer pecoId) {
		this.pecoId = pecoId;
	}

	public int getPersId() {
		return this.persId;
	}

	public void setPersId(int persId) {
		this.persId = persId;
	}

	public int getConvId() {
		return this.convId;
	}

	public void setConvId(int convId) {
		this.convId = convId;
	}

	public Serializable getPecoCalidad() {
		return this.pecoCalidad;
	}

	public void setPecoCalidad(Serializable pecoCalidad) {
		this.pecoCalidad = pecoCalidad;
	}

	public Serializable getPecoEstado() {
		return this.pecoEstado;
	}

	public void setPecoEstado(Serializable pecoEstado) {
		this.pecoEstado = pecoEstado;
	}

	public Serializable getPecoIdSolicitud() {
		return this.pecoIdSolicitud;
	}

	public void setPecoIdSolicitud(Serializable pecoIdSolicitud) {
		this.pecoIdSolicitud = pecoIdSolicitud;
	}

	public int getProgId() {
		return this.progId;
	}

	public void setProgId(int progId) {
		this.progId = progId;
	}

	public Serializable getPecoSemestre() {
		return this.pecoSemestre;
	}

	public void setPecoSemestre(Serializable pecoSemestre) {
		this.pecoSemestre = pecoSemestre;
	}

	public int getPecoYear() {
		return this.pecoYear;
	}

	public void setPecoYear(int pecoYear) {
		this.pecoYear = pecoYear;
	}

	public BigDecimal getPecoValorMatricula() {
		return this.pecoValorMatricula;
	}

	public void setPecoValorMatricula(BigDecimal pecoValorMatricula) {
		this.pecoValorMatricula = pecoValorMatricula;
	}

	public Serializable getPecoRangoMatricula() {
		return this.pecoRangoMatricula;
	}

	public void setPecoRangoMatricula(Serializable pecoRangoMatricula) {
		this.pecoRangoMatricula = pecoRangoMatricula;
	}

	public Serializable getPegoElegible() {
		return this.pegoElegible;
	}

	public void setPegoElegible(Serializable pegoElegible) {
		this.pegoElegible = pegoElegible;
	}

}
