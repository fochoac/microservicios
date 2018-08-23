package net.unir.servicio.seguridad.modelo;

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
@Table(name = "roles")

public class Rol implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4174846758149698834L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rol_pk")
	private Long id;

	@Column(name = "nombre")
	private String nombre;
	@Column(name = "fechacreacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;

	private boolean estado;

}