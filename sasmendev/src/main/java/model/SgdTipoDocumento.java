package model;
// Generated 26/03/2022 08:12:38 AM by Hibernate Tools 5.3.0.Beta2

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SgdTipoDocumento generated by hbm2java
 */
@Entity
@Table(name = "sgd_tipo_documento", schema = "dbo")
public class SgdTipoDocumento implements java.io.Serializable {

	private Integer tidoId;
	private String tidoNombre;
	private String tidoEstado;
	private Set<SgdConvenioTipoDocumento> convenioTipoDocumento = new HashSet<SgdConvenioTipoDocumento>(0);

	public Set<SgdConvenioTipoDocumento> getConvenioTipoDocumento() {
		return convenioTipoDocumento;
	}

	public void setConvenioTipoDocumento(Set<SgdConvenioTipoDocumento> convenioTipoDocumento) {
		this.convenioTipoDocumento = convenioTipoDocumento;
	}

	public SgdTipoDocumento() {
	}

	public SgdTipoDocumento(String tidoNombre, String tidoEstado) {
		this.tidoNombre = tidoNombre;
		this.tidoEstado = tidoEstado;
	}

	@Id
	@Column(name = "tido_id", unique = true, nullable = false)
	@OneToMany(cascade = {CascadeType.ALL})
	public Integer getTidoId() {
		return this.tidoId;
	}

	public void setTidoId(Integer tidoId) {
		this.tidoId = tidoId;
	}

	public Serializable getTidoNombre() {
		return this.tidoNombre;
	}

	public void setTidoNombre(String tidoNombre) {
		this.tidoNombre = tidoNombre;
	}

	public String getTidoEstado() {
		return this.tidoEstado;
	}

	public void setTidoEstado(String tidoEstado) {
		this.tidoEstado = tidoEstado;
	}

}
