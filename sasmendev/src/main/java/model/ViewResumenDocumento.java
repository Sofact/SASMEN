package model;

import java.util.Date;

public class ViewResumenDocumento {
	
	private int convId;
	private int tidoId;
	private String  docuNombre;
	private int enlaId;
	private String convNumero;
	private int convYear;
	private String convApodo;
	private Date convFechaInicio;
	private String cotdPeriodicidad;
	private Date docuFechaInicio;
	
	
	public Date getDocuFechaInicio() {
		return docuFechaInicio;
	}
	public void setDocuFechaInicio(Date docuFechaInicio) {
		this.docuFechaInicio = docuFechaInicio;
	}
	public int getConvId() {
		return convId;
	}
	public void setConvId(int convId) {
		this.convId = convId;
	}
	public int getTidoId() {
		return tidoId;
	}
	public void setTidoId(int tidoId) {
		this.tidoId = tidoId;
	}
	public String getDocuNombre() {
		return docuNombre;
	}
	public void setDocuNombre(String docuNombre) {
		this.docuNombre = docuNombre;
	}
	public int getEnlaId() {
		return enlaId;
	}
	public void setEnlaId(int enlaId) {
		this.enlaId = enlaId;
	}
	public String getConvNumero() {
		return convNumero;
	}
	public void setConvNumero(String convNumero) {
		this.convNumero = convNumero;
	}
	public int getConvYear() {
		return convYear;
	}
	public void setConvYear(int convYear) {
		this.convYear = convYear;
	}
	public String getConvApodo() {
		return convApodo;
	}
	public void setConvApodo(String convApodo) {
		this.convApodo = convApodo;
	}
	public Date getConvFechaInicio() {
		return convFechaInicio;
	}
	public void setConvFechaInicio(Date convFechaInicio) {
		this.convFechaInicio = convFechaInicio;
	}
	public String getCotdPeriodicidad() {
		return cotdPeriodicidad;
	}
	public void setCotdPeriodicidad(String cotdPeriodicidad) {
		this.cotdPeriodicidad = cotdPeriodicidad;
	}
	
}
