package model;
// Generated 26/03/2022 08:12:38 AM by Hibernate Tools 5.3.0.Beta2

import java.io.Serializable;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SgdDocumento generated by hbm2java
 */
@Entity
@Table(name = "sgd_documento", schema = "dbo")
public class SgdDocumento implements java.io.Serializable {

	private Integer docuId;
	private String docuNombre;
	private String docuPath;
	private String docuEstado;
	private int tidoId;
	private int convId;
	private LocalDate docuFechaIni;
	private LocalDate docuFechaFin;
	private double docuValor;
	private String docuReducccion;
	private String docuComentario;
	private int enlaId;
	private int consecutivo;

	public int getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(int consecutivo) {
		this.consecutivo = consecutivo;
	}

	public SgdDocumento() {
	}

	public SgdDocumento(String docuNombre, String docuPath, String docuEstado, int tidoId, int convId, LocalDate docuFechaIni, LocalDate docuFechaFin, double docuValor, String docuReducccion, String docuComentario, int enlaId) {
		this.docuNombre = docuNombre;
		this.docuPath = docuPath;
		this.docuEstado = docuEstado;
		this.tidoId = tidoId;
		this.convId = convId;
		this.docuFechaIni = docuFechaIni;
		this.docuFechaFin = docuFechaFin;
		this.docuValor = docuValor;
		this.docuReducccion = docuReducccion;
		this.docuComentario = docuComentario;
		this.enlaId = enlaId;
	}

	public Integer getDocuId() {
		return this.docuId;
	}

	public void setDocuId(Integer docuId) {
		this.docuId = docuId;
	}

	public Serializable getDocuNombre() {
		return this.docuNombre;
	}

	public void setDocuNombre(String docuNombre) {
		this.docuNombre = docuNombre;
	}

	public Serializable getDocuPath() {
		return this.docuPath;
	}

	public void setDocuPath(String destinoPath) {
		this.docuPath = destinoPath;
	}

	public String getDocuEstado() {
		return this.docuEstado;
	}

	public void setDocuEstado(String docuEstado) {
		this.docuEstado = docuEstado;
	}

	public int getTidoId() {
		return this.tidoId;
	}

	public void setTidoId(int tidoId) {
		this.tidoId = tidoId;
	}

	public int getConvId() {
		return this.convId;
	}

	public void setConvId(int convId) {
		this.convId = convId;
	}

	public LocalDate getDocuFechaIni() {
		return docuFechaIni;
	}

	public void setDocuFechaIni(LocalDate fechajs) {
		this.docuFechaIni = fechajs;
	}

	public LocalDate getDocuFechaFin() {
		return docuFechaFin;
	}

	public void setDocuFechaFin(LocalDate docuFechaFin) {
		this.docuFechaFin = docuFechaFin;
	}

	public double getDocuValor() {
		return docuValor;
	}

	public void setDocuValor(double docuValor) {
		this.docuValor = docuValor;
	}

	public String getDocuComentario() {
		return docuComentario;
	}

	public void setDocuComentario(String docuComentario) {
		this.docuComentario = docuComentario;
	}

	public String getDocuReducccion() {
		return docuReducccion;
	}

	public void setDocuReducccion(String docuReducccion) {
		this.docuReducccion = docuReducccion;
	}

	public int getEnlaId() {
		return enlaId;
	}

	public void setEnlaId(int enlaId) {
		this.enlaId = enlaId;
	}

}