package net.unir.gestion.agendas.servicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import net.unir.gestion.agendas.util.ServicioUtil;
import net.unir.gson.util.GsonUtil;
import unir.net.modelo.establecimiento.salud.EstablecimientoSaludRespuesta;
import unir.net.modelo.establecimiento.salud.consultorio.ConsultorioRespuesta;
import unir.net.modelo.establecimiento.salud.especialidad.EspecialidadRespuesta;
import unir.net.modelo.establecimiento.salud.horario.HorarioRespuesta;
import unir.net.modelo.establecimiento.salud.medico.MedicoDto;
import unir.net.modelo.establecimiento.salud.medico.MedicoRespuesta;
import unir.net.modelo.gestion.agendas.AgendamientoEntrada;
import unir.net.modelo.gestion.agendas.CitaMedicaEntrada;
import unir.net.modelo.gestion.agendas.ParametrosAgendamientoRespuesta;
import unir.net.modelo.seguridad.persona.PersonaRespuesta;

@RequestScoped
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Path("cita")
public class AgendamientoServicio {

	@Inject
	Logger log;
	@Context
	UriInfo uriInfo;

	@GET
	@Path("obtenerParametrosAgendas")
	public Response listarParametros() {
		EstablecimientoSaludRespuesta establecimiento = ServicioUtil.obtenerDatosEstablecimientoPorId("1");
		EspecialidadRespuesta especialidadRespuesta = ServicioUtil.listarTodasEspecialidades();
		ConsultorioRespuesta consultorioRespuesta = ServicioUtil.listarTodosConsultoriosEstablecimiento();

		if (Objects.isNull(establecimiento) || Objects.isNull(especialidadRespuesta)
				|| Objects.isNull(consultorioRespuesta)) {
			return Response.serverError().build();
		}
		ParametrosAgendamientoRespuesta respuesta = ParametrosAgendamientoRespuesta.builder()
				.consultorios(consultorioRespuesta.getConsultorios())
				.datosEstablecimiento(establecimiento.getEstablecimiento())
				.especialidades(especialidadRespuesta.getEspecialidades()).build();
		return Response.ok(GsonUtil.generarGson(respuesta), MediaType.APPLICATION_JSON).build();

	}

	@POST
	@Path("obtenerMedicos")
	public Response listarMedicos(AgendamientoEntrada entrada) {
		System.out.println(entrada.getIdConsultorio() + ", " + entrada.getIdEspecialidad());
		MedicoRespuesta medicoRespuesta = ServicioUtil.listarMedicosPorEspecialidadYConsultorio(
				entrada.getIdEspecialidad().toString(), entrada.getIdConsultorio().toString());
		if (Objects.isNull(medicoRespuesta)) {
			return Response.noContent().build();
		}
		Set<Long> idPersonas = new HashSet<>();
		medicoRespuesta.getMedicos().forEach(m -> {
			if (m.getId() != null) {
				idPersonas.add(m.getIdPersona());
			}
		});
		Map<Long, PersonaRespuesta> catalogo = new HashMap<>();
		idPersonas.forEach(i -> {
			catalogo.put(i, ServicioUtil.obtenerPersonaPorId(i.toString()));
		});
		medicoRespuesta.getMedicos().forEach(m -> {

			PersonaRespuesta res = catalogo.get(m.getIdPersona());
			m.setNombreMedico(res.getPersona().getNombres() + " " + res.getPersona().getApellidos());

		});
	List<MedicoDto> medicos=	medicoRespuesta.getMedicos().stream().distinct().collect(Collectors.toList());
		ParametrosAgendamientoRespuesta resultado = ParametrosAgendamientoRespuesta.builder()
				.medicos(medicos).build();
		return Response.ok(GsonUtil.generarGson(resultado), MediaType.APPLICATION_JSON).build();
	}

	@POST
	@Path("obtenerHorarios")
	public Response listarHorarios(AgendamientoEntrada entrada) {
		System.out.println(entrada.getIdMedico());
		HorarioRespuesta horarioRespuesta = ServicioUtil.listarHorariosPorMedico(entrada.getIdMedico().toString());
		if (Objects.isNull(horarioRespuesta)) {
			return Response.noContent().build();
		}
		ParametrosAgendamientoRespuesta resultado = ParametrosAgendamientoRespuesta.builder()
				.horarios(horarioRespuesta.getHorarios()).build();
		return Response.ok(GsonUtil.generarGson(resultado), MediaType.APPLICATION_JSON).build();
	}

	@POST
	@Path("guardarCita")
	public Response listarHorarios(CitaMedicaEntrada entrada) {
		log.info("Ingresa al metodo: " + entrada);
		entrada.setEstadoCita("16");
		ParametrosAgendamientoRespuesta respuesta = ServicioUtil.guardarCita(entrada);
		if (Objects.isNull(respuesta)) {
			return Response.noContent().build();
		}

		return Response.ok(GsonUtil.generarGson(respuesta), MediaType.APPLICATION_JSON).build();
	}

}
