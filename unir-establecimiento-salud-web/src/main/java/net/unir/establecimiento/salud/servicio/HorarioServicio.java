package net.unir.establecimiento.salud.servicio;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.unir.establecimiento.salud.dao.HorarioDao;
import net.unir.gson.util.GsonUtil;
import unir.net.modelo.establecimiento.salud.horario.HorarioDto;
import unir.net.modelo.establecimiento.salud.horario.HorarioRespuesta;

@RequestScoped
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Path("establecimiento/horario")
@Api
public class HorarioServicio {

	@Inject
	private HorarioDao dao;
	@Inject
	Logger log;
	@Context
	UriInfo uriInfo;

	@GET
	@Path("listar/medico/{id}")
	@ApiOperation(value = "Lista los horarios por medico", tags = {
			"Horario de Agendas" }, notes = "Retorna el listado horarios por medico.")
	@ApiResponses(value = {
			@ApiResponse(message = "Lista los horarios por id del medico", code = 200, response = HorarioRespuesta.class) })
	public Response listarHorariosPorMedico(@PathParam("id") Long id) {
		try {
			List<HorarioDto> lista = dao.listarPorMedico(id);
			if (lista.isEmpty()) {
				return Response.status(Response.Status.NO_CONTENT).build();
			}
			HorarioRespuesta respuesta = HorarioRespuesta.builder().horarios(lista).build();
			return Response.ok(GsonUtil.generarGson(respuesta), MediaType.APPLICATION_JSON).build();
		} catch (RuntimeException e2) {
			log.log(Level.SEVERE, "Error consultando el servicio", e2);
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		} catch (Exception e) {
			log.log(Level.SEVERE, "Error consultando el servicio", e);
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		}

	}

	@GET
	@Path("obtener/id/{id}")
	@ApiOperation(value = "Lista los horarios por medico", tags = {
			"Horario de Agendas" }, notes = "Retorna el listado horarios por medico.")
	@ApiResponses(value = {
			@ApiResponse(message = "Lista los horarios por id del medico", code = 200, response = HorarioRespuesta.class) })
	public Response obtenerHorarioId(@PathParam("id") Long id) {
		try {
			HorarioDto dto = dao.obtenerPorId(id);
			if (Objects.isNull(dto)) {
				return Response.status(Response.Status.NO_CONTENT).build();
			}
			HorarioRespuesta respuesta = HorarioRespuesta.builder().horario(dto).build();
			return Response.ok(GsonUtil.generarGson(respuesta), MediaType.APPLICATION_JSON).build();
		} catch (RuntimeException e2) {
			log.log(Level.SEVERE, "Error consultando el servicio", e2);
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		} catch (Exception e) {
			log.log(Level.SEVERE, "Error consultando el servicio", e);
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		}

	}

}
