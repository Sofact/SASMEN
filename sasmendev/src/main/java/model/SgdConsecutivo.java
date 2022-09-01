package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SgdConsecutivo {
	
	@Id
	@GeneratedValue
	private int consId;
	private String consNumero;
	private int tidoId;
	private String estado;
	
	
	public int getConsId() {
		return consId;
	}
	public void setConsId(int consId) {
		this.consId = consId;
	}
	public String getConsNumero() {
		return consNumero;
	}
	public void setConsNumero(String consNumero) {
		this.consNumero = consNumero;
	}
	public int getTidoId() {
		return tidoId;
	}
	public void setTidoId(int tidoId) {
		this.tidoId = tidoId;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
