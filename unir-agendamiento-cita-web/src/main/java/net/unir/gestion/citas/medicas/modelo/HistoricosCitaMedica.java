package net.unir.gestion.citas.medicas.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the historicos_citas_medicas database table.
 * 
 */
@Entity
@Table(name="historicos_citas_medicas")
@NamedQuery(name="HistoricosCitaMedica.findAll", query="SELECT h FROM HistoricosCitaMedica h")
public class HistoricosCitaMedica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="historico_cita_medica_pk")
	private Long id;

	@Column(name="estado_cita")
	private String estadoCita;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="fecha_finalizacion")
	private Timestamp fechaFinalizacion;

	@Column(name="ip_modificacion")
	private String ipModificacion;

	@Column(name="usuario_modificacion_pk")
	private Long usuarioModificacionId;

	//bi-directional many-to-one association to CitaMedica
	@ManyToOne
	@JoinColumn(name="cita_medica_pk")
	private CitaMedica citasMedica;

	public HistoricosCitaMedica() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstadoCita() {
		return this.estadoCita;
	}

	public void setEstadoCita(String estadoCita) {
		this.estadoCita = estadoCita;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Timestamp getFechaFinalizacion() {
		return this.fechaFinalizacion;
	}

	public void setFechaFinalizacion(Timestamp fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public String getIpModificacion() {
		return this.ipModificacion;
	}

	public void setIpModificacion(String ipModificacion) {
		this.ipModificacion = ipModificacion;
	}

	public Long getUsuarioModificacionId() {
		return this.usuarioModificacionId;
	}

	public void setUsuarioModificacionId(Long usuarioModificacionId) {
		this.usuarioModificacionId = usuarioModificacionId;
	}

	public CitaMedica getCitasMedica() {
		return this.citasMedica;
	}

	public void setCitasMedica(CitaMedica citasMedica) {
		this.citasMedica = citasMedica;
	}

}