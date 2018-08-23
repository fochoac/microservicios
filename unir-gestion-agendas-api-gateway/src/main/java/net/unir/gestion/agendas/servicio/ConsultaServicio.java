package net.unir.gestion.agendas.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import net.unir.gestion.agendas.configuracion.GestionConfiguracionesConsul;
import net.unir.gestion.agendas.util.ServicioUtil;
import net.unir.gson.util.GsonUtil;
import unir.net.modelo.establecimiento.salud.EstablecimientoSaludRespuesta;
import unir.net.modelo.establecimiento.salud.consultorio.ConsultorioRespuesta;
import unir.net.modelo.establecimiento.salud.especialidad.EspecialidadRespuesta;
import unir.net.modelo.establecimiento.salud.horario.HorarioRespuesta;
import unir.net.modelo.establecimiento.salud.medico.MedicoRespuesta;
import unir.net.modelo.gestion.agendas.AgendamientoEntrada;
import unir.net.modelo.gestion.agendas.CitaMedicaDto;
import unir.net.modelo.gestion.agendas.CitaMedicaRespuesta;
import unir.net.modelo.seguridad.persona.PersonaRespuesta;

@RequestScoped
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Path("cita")
public class ConsultaServicio {
	@Inject
	private GestionConfiguracionesConsul configuracion;
	@Inject
	Logger log;
	@Context
	UriInfo uriInfo;
	@Inject
	private AgendamientoServicio agendamientoServicio;

	@POST
	@Path("consultar/cedula/")
	public Response listarCitasAgendadasPorCedula(AgendamientoEntrada entrada) {
		CitaMedicaRespuesta citaMedicaRespuesta = ServicioUtil.listarCitasPorCedula(entrada.getCedula());
		if (Objects.isNull(citaMedicaRespuesta)) {
			return Response.noContent().build();
		}
		List<CitaMedicaDto> citasDto = new ArrayList<>();
		citaMedicaRespuesta.getCitasMedicas().forEach(c -> {
			ConsultorioRespuesta resuesta = ServicioUtil.obtenerConsultorioPorId(c.getConsultorioId().toString());
			EspecialidadRespuesta especialidadRespuesta = ServicioUtil
					.obtenerEspecialidadPorId(c.getEspecialidadId().toString());
			HorarioRespuesta horarioRespuesta = ServicioUtil.obtenerHOrarioId(c.getHorarioId().toString());
			MedicoRespuesta medicoRespuesta = ServicioUtil.obtenerMedicoPorId(c.getMedicoId().toString());
			PersonaRespuesta medicoCompleto = ServicioUtil
					.obtenerPersonaPorId(medicoRespuesta.getMedico().getIdPersona().toString());
			medicoRespuesta.getMedico().setNombreMedico(
					medicoCompleto.getPersona().getNombres() + " " + medicoCompleto.getPersona().getApellidos());
			EstablecimientoSaludRespuesta establecimientoSaludRespuesta = ServicioUtil
					.obtenerDatosEstablecimientoPorId(c.getEstablecimientoSaludId().toString());
			CitaMedicaDto dto = CitaMedicaDto.builder().especialidadDto(especialidadRespuesta.getEspecialidad())
					.establecimientoSaludDto(establecimientoSaludRespuesta.getEstablecimiento())
					.horarioDto(horarioRespuesta.getHorario()).medicoDto(medicoRespuesta.getMedico())
					.consultorioDto(resuesta.getConsultorio()).idCita(c.getIdCitaCancelar()).build();
			citasDto.add(dto);
		});
		citaMedicaRespuesta.setCitas(citasDto);

		return Response.ok().entity(GsonUtil.generarGson(citaMedicaRespuesta)).build();

	}
}
