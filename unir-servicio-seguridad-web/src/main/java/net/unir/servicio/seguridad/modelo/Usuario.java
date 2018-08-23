package net.unir.servicio.seguridad.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "usuarios")
@NamedQueries({
		@NamedQuery(name = Usuario.OBTENER_CREDENCIALES_USUARIO, query = "Select u from Usuario u where u.estado=true and u.usuario=:usuario and u.password=:pass") })
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4174846758149698834L;
	public static final String OBTENER_CREDENCIALES_USUARIO = "Usuario.obtenerCredenciales";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuario_pk")
	private Long id;

	@Column(name = "persona_pk")
	private String idPersona;
	@Column(name = "usuario")
	private Long usuario;
	@Column(name = "password")
	private String password;
	@Column(name = "fechacreacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	private boolean estado;

}