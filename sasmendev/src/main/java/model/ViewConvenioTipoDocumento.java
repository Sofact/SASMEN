package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ViewConvenioTipoDocumento {
	
	private int tidoId;
	private String tidoNombre;
	private String tidoEstado;
	@Id
	@GeneratedValue
	private int cotdId;
	private int convId;
	private int cotdFechaFin;
	
	

	public int getCotdFechaFin() {
		return cotdFechaFin;
	}
	public void setCotdFechaFin(int cotdFechaFin) {
		this.cotdFechaFin = cotdFechaFin;
	}
	public int getTidoId() {
		return tidoId;
	}
	public void setTidoId(int tidoId) {
		this.tidoId = tidoId;
	}
	public String getTidoNombre() {
		return tidoNombre;
	}
	public void setTidoNombre(String tidoNombre) {
		this.tidoNombre = tidoNombre;
	}
	public String getTidoEstado() {
		return tidoEstado;
	}
	public void setTidoEstado(String tidoEstado) {
		this.tidoEstado = tidoEstado;
	}
	public int getCotdId() {
		return cotdId;
	}
	public void setCotdId(int cotdId) {
		this.cotdId = cotdId;
	}
	public int getConvId() {
		return convId;
	}
	public void setConvId(int convId) {
		this.convId = convId;
	}

}
