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

import com.kumuluz.ee.cors.annotations.CrossOrigin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.unir.establecimiento.salud.dao.ConsultorioDao;
import net.unir.gson.util.GsonUtil;
import unir.net.modelo.establecimiento.salud.consultorio.ConsultorioDto;
import unir.net.modelo.establecimiento.salud.consultorio.ConsultorioRespuesta;

@RequestScoped
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Path("establecimiento/consultorio")
@Api
@CrossOrigin(allowOrigin = "*")
public class ConsultorioServicio {

	@Inject
	private ConsultorioDao consultorioDao;
	@Inject
	Logger log;
	@Context
	UriInfo uriInfo;

	@GET
	@Path("listarTodos")
	@ApiOperation(value = "Obtener todos los consultorio", tags = {
			"Consultorio" }, notes = "Retorna el listado de consultorio.")
	@ApiResponses(value = {
			@ApiResponse(message = "Lista de consultorios", code = 200, response = ConsultorioRespuesta.class) })
	public Response listarTodos() {
		try {
			List<ConsultorioDto> lista = consultorioDao.listarConsultorio();
			if (lista.isEmpty()) {
				return Response.status(Response.Status.NO_CONTENT).build();
			}
			ConsultorioRespuesta respuesta = ConsultorioRespuesta.builder().consultorios(lista).build();
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
	@ApiOperation(value = "Obtener todos los consultorio", tags = {
			"Consultorio" }, notes = "Retorna consultorioPorId.")
	@ApiResponses(value = {
			@ApiResponse(message = "Lista de consultorios", code = 200, response = ConsultorioRespuesta.class) })
	public Response obtenerPorId(@PathParam("id") Long id) {
		try {
			ConsultorioDto dto = consultorioDao.listarConsultorio(id);
			if (Objects.isNull(dto)) {
				return Response.status(Response.Status.NO_CONTENT).build();
			}
			ConsultorioRespuesta respuesta = ConsultorioRespuesta.builder().consultorio(dto).build();
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
