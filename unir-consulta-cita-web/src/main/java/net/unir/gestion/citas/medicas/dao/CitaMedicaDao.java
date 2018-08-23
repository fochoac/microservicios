package net.unir.gestion.citas.medicas.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;

import net.unir.gestion.citas.medicas.dao.impl.GenericoDao;
import net.unir.gestion.citas.medicas.modelo.CitaMedica;
import unir.net.modelo.gestion.agendas.CitaMedicaEntrada;

@RequestScoped
public class CitaMedicaDao extends GenericoDao<CitaMedica> {
	public CitaMedicaDao() {
		super(CitaMedica.class);
	}

	@SuppressWarnings("unchecked")
	public List<CitaMedicaEntrada> listarCitasMedicas(final String cedula) {
		Query query = entityManager().createNamedQuery(CitaMedica.LISTAR_CITAS_MEDICAS);
		query.setParameter("cedula", cedula);
		List<CitaMedica> citas = query.getResultList();
		if (Objects.isNull(citas) || citas.isEmpty()) {
			return null;
		}
		List<CitaMedicaEntrada> lista = new ArrayList<>();
		citas.forEach(c -> {
			CitaMedicaEntrada dto = CitaMedicaEntrada.builder().cedulaPaciente(c.getCedulaPaciente())
					.consultorioId(c.getConsultorioId()).especialidadId(c.getEspecialidadId())
					.establecimientoSaludId(c.getEstablecimientoSaludId()).horarioId(c.getHorarioId())
					.medicoId(c.getMedicoId()).idCitaCancelar(c.getId()).build();
			lista.add(dto);
		});
		return lista;

	}
}
