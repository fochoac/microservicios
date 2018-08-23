package net.unir.establecimiento.salud.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;

import net.unir.establecimiento.salud.dao.impl.GenericoDao;
import net.unir.establecimiento.salud.modelo.Medico;
import unir.net.modelo.establecimiento.salud.medico.MedicoDto;

@RequestScoped
public class MedicoDao extends GenericoDao<Medico> {
	public MedicoDao() {
		super(Medico.class);
	}

	public List<MedicoDto> listarPorConsultiroEspecialidad(final Long idConsultorio, final Long idEspecialidad) {
		final StringBuilder builder = new StringBuilder()
				.append("select distinct m.* from medicos m, consultorio_medico_especialidad_horario d\n"
						+ "where d.estado=true and d.estado=m.estado\n" + "and d.medico_pk = m.medico_pk\n"
						+ "and d.consultorio_pk=?\n" + "and d.especialidad_pk=?");
		Query query = entityManager().createNativeQuery(builder.toString(), Medico.class);
		query.setParameter(1, idConsultorio);
		query.setParameter(2, idEspecialidad);
		List<Medico> lista = query.getResultList();
		return procesarResultado(lista);
	}

	public List<MedicoDto> listarTodosActivos() {
		Query query = entityManager().createNamedQuery(Medico.LISTAR_MEDICOS_ACTIVOS);

		List<Medico> lista = query.getResultList();
		return procesarResultado(lista);

	}

	public MedicoDto obtenerPorId(final Long id) {
		Medico m = findOptionalById(id).get();
		return MedicoDto.builder().codigo(m.getCodigo()).id(m.getId()).idPersona(m.getIdPersona()).build();
	}

	private List<MedicoDto> procesarResultado(List<Medico> lista) {
		List<MedicoDto> medicos = new ArrayList<>();
		if (Objects.isNull(lista) || lista.isEmpty()) {
			return medicos;
		}
		lista.forEach(m -> {
			MedicoDto dto = MedicoDto.builder().codigo(m.getCodigo()).id(m.getId()).idPersona(m.getIdPersona()).build();
			medicos.add(dto);
		});
		return medicos;
	}
}
