package net.unir.establecimiento.salud.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

/**
 * The persistent class for the citas_medicas database table.
 * 
 */
@Data()

@Entity
@Table(name = "medicos")
@NamedQueries({
		@NamedQuery(name = Medico.LISTAR_MEDICOS_ACTIVOS, query = "SELECT m FROM Medico m where  m.estado = true") })
public class Medico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4174846758149698834L;
	public static final String LISTAR_MEDICOS_ACTIVOS = "Medico.listarTodosMedicosActivos";
	public static final String LISTAR_POS_CONSULTORIO_ESPECIALIDAD = "Medico.listarPorConsultorioEspecialidad";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "medico_pk")
	private Long id;

	@Column(name = "persona_pk")
	private Long idPersona;

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "estado")
	private boolean estado;

}