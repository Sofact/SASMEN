package model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ViewResumen {
	
	private int convId;
	
	private String nombre;
	private Date convFechaInicio;
	private Date convFechaFin;
	private double convValor;
	private String docuNombre;
	private int consecutivo;
	private int convYear;
	private int convMes;
	@Id
	@GeneratedValue
	private String periodicidad;
	
	public int getConvId() {
		return convId;
	}
	public void setConvId(int convId) {
		this.convId = convId;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getConvFechaInicio() {
		return convFechaInicio;
	}
	public void setConvFechaInicio(Date convFechaInicio) {
		this.convFechaInicio = convFechaInicio;
	}
	public Date getConvFechaFin() {
		return convFechaFin;
	}
	public void setConvFechaFin(Date convFechaFin) {
		this.convFechaFin = convFechaFin;
	}
	public double getConvValor() {
		return convValor;
	}
	public void setConvValor(double convValor) {
		this.convValor = convValor;
	}
	public String getDocuNombre() {
		return docuNombre;
	}
	public void setDocuNombre(String docuNombre) {
		this.docuNombre = docuNombre;
	}
	public int getConsecutivo() {
		return consecutivo;
	}
	public void setConsecutivo(int consecutivo) {
		this.consecutivo = consecutivo;
	}
	public int getConvYear() {
		return convYear;
	}
	public void setConvYear(int convYear) {
		this.convYear = convYear;
	}
	public int getConvMes() {
		return convMes;
	}
	public void setConvMes(int convMes) {
		this.convMes = convMes;
	}
	public String getPeriodicidad() {
		return periodicidad;
	}
	public void setPeriodicidad(String periodicidad) {
		this.periodicidad = periodicidad;
	}
	

}
