package net.unir.gestion.citas.medicas.dao;

import javax.enterprise.context.RequestScoped;

import net.unir.gestion.citas.medicas.dao.impl.GenericoDao;
import net.unir.gestion.citas.medicas.modelo.CitaMedica;
import net.unir.servicio.EstadoAgendamientoEnum;
import unir.net.modelo.gestion.agendas.CitaMedicaEntrada;

@RequestScoped
public class CitaMedicaDao extends GenericoDao<CitaMedica> {
	public CitaMedicaDao() {
		super(CitaMedica.class);
	}

	public CitaMedica guardarCitaMedica(CitaMedicaEntrada cita) {
		System.out.println("ingresa al metodo");
		CitaMedica citaMedica = new CitaMedica();
		citaMedica.setConsultorioId(cita.getConsultorioId());
		citaMedica.setEspecialidadId(cita.getEspecialidadId());
		citaMedica.setEstablecimientoSaludId(cita.getEstablecimientoSaludId());
		citaMedica.setEstado(true);
		citaMedica.setMedicoId(cita.getMedicoId());
		citaMedica.setEstadoCita(cita.getEstadoCita());
		citaMedica.setHorarioId(cita.getHorarioId());
		citaMedica.setCedulaPaciente(cita.getCedulaPaciente());
		return save(citaMedica);
	}

	public boolean cancelarCita(CitaMedicaEntrada cita) {
		CitaMedica citaMedica = findById(cita.getIdCitaCancelar());
		citaMedica.setEstadoCita(EstadoAgendamientoEnum.CANCELADO.getId().toString());
		update(citaMedica);
		return true;
	}
}
