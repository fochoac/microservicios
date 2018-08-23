package net.unir.establecimiento.salud.servicio;

import java.util.List;
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
import net.unir.establecimiento.salud.dao.MedicoDao;
import net.unir.gson.util.GsonUtil;
import unir.net.modelo.establecimiento.salud.especialidad.EspecialidadRespuesta;
import unir.net.modelo.establecimiento.salud.medico.MedicoDto;
import unir.net.modelo.establecimiento.salud.medico.MedicoRespuesta;

@RequestScoped
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Path("establecimiento/medico")
@Api
public class MedicoServicio {

	@Inject
	private MedicoDao dao;
	@Inject
	Logger log;
	@Context
	UriInfo uriInfo;

	@GET
	@Path("id/{id}")
	@ApiOperation(value = "Obtener medico por id", tags = {
			"Datos Medico" }, notes = "Retorna el medico por el id.")
	@ApiResponses(value = {
			@ApiResponse(message = "Lista de consultorios", code = 200, response = MedicoRespuesta.class) })
	public Response obtener(@PathParam("id") Long id) {
		try {
			MedicoDto dto = dao.obtenerPorId(id);
			if (dto == null) {
				return Response.status(Response.Status.NO_CONTENT).build();
			}
			MedicoRespuesta respuesta = MedicoRespuesta.builder().medico(dto).build();
			return Response.ok(GsonUtil.generarGson(respuesta), MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		}

	}

	@GET
	@Path("especialidad/{idEspecialidad}/consultorio/{idConsultorio}")
	@ApiOperation(value = "Lista los medicos por especialdiad y consultorio", tags = {
			"Datos Medico" }, notes = "Retorna el listado de medicos por especialidad y consultorio.")
	@ApiResponses(value = {
			@ApiResponse(message = "Lista de medicos por consultorio y especialidad", code = 200, response = MedicoRespuesta.class) })
	public Response obtener(@PathParam("idEspecialidad") Long idEspecialidad,
			@PathParam("idConsultorio") Long idConsultorio) {
		try {
			List<MedicoDto> medicoDtos = dao.listarPorConsultiroEspecialidad(idConsultorio, idEspecialidad);
			if (medicoDtos.isEmpty()) {
				return Response.status(Response.Status.NO_CONTENT).build();
			}
			MedicoRespuesta respuesta = MedicoRespuesta.builder().medicos(medicoDtos).build();
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
