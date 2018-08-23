package net.unir.gestion.citas.medicas.modelo;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.List;

/**
 * The persistent class for the citas_medicas database table.
 * 
 */
@Data()

@Entity
@Table(name = "citas_medicas")
@NamedQuery(name = "CitaMedica.findAll", query = "SELECT c FROM CitaMedica c")
public class CitaMedica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4174846758149698834L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cita_medica_pk")
	private Long id;

	@Column(name = "consultorio_pk")
	private Long consultorioId;

	@Column(name = "especialidad_pk")
	private Long especialidadId;

	@Column(name = "establecimiento_salud_pk")
	private Long establecimientoSaludId;

	private boolean estado;

	@Column(name = "estado_cita")
	private String estadoCita;
	@Column(name = "cedula_persona")
	private String cedulaPaciente;

	@Column(name = "horario_pk")
	private Long horarioId;

	@Column(name = "medico_pk")
	private Long medicoId;

	@OneToMany(mappedBy = "citasMedica", fetch = FetchType.EAGER)
	private List<HistoricosCitaMedica> historicosCitasMedicas;

}