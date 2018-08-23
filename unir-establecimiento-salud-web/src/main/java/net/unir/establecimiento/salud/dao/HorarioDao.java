package net.unir.establecimiento.salud.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;

import net.unir.establecimiento.salud.dao.impl.GenericoDao;
import net.unir.establecimiento.salud.modelo.Horario;
import net.unir.parser.FechasUtil;
import unir.net.modelo.establecimiento.salud.horario.HorarioDto;

@RequestScoped
public class HorarioDao extends GenericoDao<Horario> {
	public HorarioDao() {
		super(Horario.class);
	}

	public List<HorarioDto> listarPorMedico(final Long idMedico) {
		final StringBuilder builder = new StringBuilder()
				.append("select h.* from medicos m,\n" + "consultorio_medico_especialidad_horario d,\n" + "horarios h\n"
						+ "where d.estado=true \n" + "and d.estado=m.estado\n" + "and h.estado=d.estado\n"
						+ "and h.horario_pk = d.horario_pk\n" + "and m.medico_pk=d.medico_pk\n" + "and m.medico_pk=? and fecha between now() and now() + interval '5 day' order by fecha asc");
		Query query = entityManager().createNativeQuery(builder.toString(), Horario.class);
		query.setParameter(1, idMedico);
		List<Horario> lista = query.getResultList();
		List<HorarioDto> horarios = new ArrayList<>();
		lista.forEach(h -> {
			HorarioDto dto = HorarioDto.builder().estadoCatalogo(h.getEstadoCatalogo())
					.fecha(FechasUtil.obtenerLocalDate(h.getFecha()))
					.horaFin(FechasUtil.obtenerLocalTime(h.getHoraFin()))
					.horaInicio(FechasUtil.obtenerLocalTime(h.getHoraInicio())).id(h.getId()).build();
			horarios.add(dto);
		});
		return horarios;
	}

	public HorarioDto obtenerPorId(Long id) {
		Horario h = findById(id);

		return HorarioDto.builder().estadoCatalogo(h.getEstadoCatalogo())
				.fecha(FechasUtil.obtenerLocalDate(h.getFecha())).horaFin(FechasUtil.obtenerLocalTime(h.getHoraFin()))
				.horaInicio(FechasUtil.obtenerLocalTime(h.getHoraInicio())).id(h.getId()).build();
	}
}
