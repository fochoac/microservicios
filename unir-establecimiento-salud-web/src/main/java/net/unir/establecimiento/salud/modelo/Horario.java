package net.unir.establecimiento.salud.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/**
 * The persistent class for the citas_medicas database table.
 * 
 */
@Data()

@Entity
@Table(name = "horarios")

public class Horario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "horario_pk")
	private Long id;

	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@Column(name = "hora_inicio")
	@Temporal(TemporalType.TIME)
	private Date horaInicio;
	@Column(name = "hora_fin")
	@Temporal(TemporalType.TIME)
	private Date horaFin;
	@Column(name = "catalogo_pk")
	private Long estadoCatalogo;

	private boolean estado;

}