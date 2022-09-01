package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class VConvenioTipoDoc {
	
	private int cotdId;
	@Id
	@GeneratedValue
	private int convId;
	private int tidoId;
	private String tidoNombre;
	
	public String getTidoNombre() {
		return tidoNombre;
	}
	public void setTidoNombre(String tidoNombre) {
		this.tidoNombre = tidoNombre;
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
	public int getTidoId() {
		return tidoId;
	}
	public void setTidoId(int tidoId) {
		this.tidoId = tidoId;
	}

}
