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
import net.unir.establecimiento.salud.dao.EspecialidadDao;
import net.unir.gson.util.GsonUtil;
import unir.net.modelo.establecimiento.salud.consultorio.ConsultorioDto;
import unir.net.modelo.establecimiento.salud.consultorio.ConsultorioRespuesta;
import unir.net.modelo.establecimiento.salud.especialidad.EspecialidadDto;
import unir.net.modelo.establecimiento.salud.especialidad.EspecialidadRespuesta;

@RequestScoped
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Path("establecimiento/especialidad")
@Api
public class EspecialidadServicio {

	@Inject
	private EspecialidadDao dao;
	@Inject
	Logger log;
	@Context
	UriInfo uriInfo;

	@GET
	@Path("listarTodos")
	@ApiOperation(value = "Lista las especialidades", tags = {
			"Especialidades" }, notes = "Retorna el listado de especialdades del establecimiento de salud.")
	@ApiResponses(value = {
			@ApiResponse(message = "Lista de consultorios", code = 200, response = EspecialidadRespuesta.class) })
	public Response obtener() {
		try {
			List<EspecialidadDto> lista = dao.listarTodasEspecialidades();
			if (lista.isEmpty()) {
				return Response.status(Response.Status.NO_CONTENT).build();
			}
			EspecialidadRespuesta respuesta = EspecialidadRespuesta.builder().especialidades(lista).build();
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
	@ApiOperation(value = "Obtener la especialidad por id", tags = {
			"Consultorio" }, notes = "Retorna especialidad por id.")
	@ApiResponses(value = {
			@ApiResponse(message = "Lista de consultorios", code = 200, response = ConsultorioRespuesta.class) })
	public Response obtenerPorId(@PathParam("id") Long id) {
		try {
			EspecialidadDto dto = dao.obtenerEspecialidadPorId(id);
			if (Objects.isNull(dto)) {
				return Response.status(Response.Status.NO_CONTENT).build();
			}
			EspecialidadRespuesta respuesta = EspecialidadRespuesta.builder().especialidad(dto).build();
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
