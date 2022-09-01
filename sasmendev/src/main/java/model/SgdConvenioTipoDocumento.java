package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sgd_convenio_tipo_documento", schema = "dbo")
public class SgdConvenioTipoDocumento implements Serializable{
	
	
	private int cotdId;
	private int convId;
	private int tidoId;
	private String cotdFechaReq;
	private String cotdValorReq;
	private String cotReducionReq;
	private String cotdComentarioReq;
	private String participantesReq;
	private String cotdEnlaceReq;
	private String cotdPeriodicidad;
	private int cotdFechaFin;
	

	
	public int getCotdFechaFin() {
		return cotdFechaFin;
	}

	public void setCotdFechaFin(int cotdFechaFin) {
		this.cotdFechaFin = cotdFechaFin;
	}

	public SgdConvenioTipoDocumento()
	{
		
	}
	
	public SgdConvenioTipoDocumento(int cotdId, int convId, int tidoId, String cotdFechaReq, String cotdValorReq,
			String cotReducionReq, String cotdComentarioReq, String participantesReq, String cotdEnlaceReq, String cotdPeriodicidad) {
		super();
		this.cotdId = cotdId;
		this.convId = convId;
		this.tidoId = tidoId;
		this.cotdFechaReq = cotdFechaReq;
		this.cotdValorReq = cotdValorReq;
		this.cotReducionReq = cotReducionReq;
		this.cotdComentarioReq = cotdComentarioReq;
		this.participantesReq = participantesReq;
		this.cotdEnlaceReq = cotdEnlaceReq;
		this.cotdPeriodicidad = cotdPeriodicidad;
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
	
	
	@JoinColumn(name = "tido_id", nullable = false)
	public int getTidoId() {
		return tidoId;
	}
	public void setTidoId(int tidoId) {
		this.tidoId = tidoId;
	}
	public String getCotdFechaReq() {
		return cotdFechaReq;
	}
	public void setCotdFechaReq(String cotdFechaReq) {
		this.cotdFechaReq = cotdFechaReq;
	}
	public String getCotdValorReq() {
		return cotdValorReq;
	}
	public void setCotdValorReq(String cotdValorReq) {
		this.cotdValorReq = cotdValorReq;
	}
	public String getCotReducionReq() {
		return cotReducionReq;
	}
	public void setCotReducionReq(String cotReducionReq) {
		this.cotReducionReq = cotReducionReq;
	}
	public String getCotdComentarioReq() {
		return cotdComentarioReq;
	}
	public void setCotdComentarioReq(String cotdComentarioReq) {
		this.cotdComentarioReq = cotdComentarioReq;
	}
	public String getParticipantesReq() {
		return participantesReq;
	}
	public void setParticipantesReq(String participantesReq) {
		this.participantesReq = participantesReq;
	}
	public String getCotdEnlaceReq() {
		return cotdEnlaceReq;
	}
	public void setCotdEnlaceReq(String cotdEnlaceReq) {
		this.cotdEnlaceReq = cotdEnlaceReq;
	}

	public String getCotdPeriodicidad() {
		return cotdPeriodicidad;
	}

	public void setCotdPeriodicidad(String cotdPeriodicidad) {
		this.cotdPeriodicidad = cotdPeriodicidad;
	}



}
