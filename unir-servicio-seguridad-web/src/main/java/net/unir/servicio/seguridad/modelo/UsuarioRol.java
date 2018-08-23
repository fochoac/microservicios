package net.unir.servicio.seguridad.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * The persistent class for the citas_medicas database table.
 * 
 */
@Data()

@Entity
@Table(name = "usuario_roles")

public class UsuarioRol implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4174846758149698834L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuario_rol_pk")
	private Long id;

	@Column(name = "usuario_pk")
	private Long idUsuario;
	@Column(name = "rol_pk")
	private Long idRol;

	private boolean estado;

}