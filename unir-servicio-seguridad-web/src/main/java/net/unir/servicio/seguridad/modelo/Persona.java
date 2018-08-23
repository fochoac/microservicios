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
@Table(name = "personas")
@NamedQueries({
		@NamedQuery(name = Persona.LISTAR_PERSONAS_POR_CEDULA, query = "Select p from Persona p where p.estado = true and p.cedula=:cedula"),
		@NamedQuery(name = Persona.LISTAR_PERSONAS_POR_ID, query = "Select p from Persona p where p.estado = true and p.id =:id") })
public class Persona implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4174846758149698834L;
	public static final String LISTAR_PERSONAS_POR_CEDULA = "Persona.listarPOrCedula";
	public static final String LISTAR_PERSONAS_POR_ID = "Persona.listarPOrId";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "persona_pk")
	private Long id;

	@Column(name = "cedula")
	private String cedula;
	@Column(name = "nombres")
	private String nombres;
	@Column(name = "apellidos")
	private String apellidos;
	@Column(name = "fechanacimiento")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	private String genero;
	@Column(name = "fechacreacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	private boolean estado;

}