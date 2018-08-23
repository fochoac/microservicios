package net.unir.establecimiento.salud.servicio;

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
import net.unir.establecimiento.salud.dao.EstablecimientoSaludDao;
import net.unir.gson.util.GsonUtil;
import unir.net.modelo.establecimiento.salud.EstablecimientoSaludDto;
import unir.net.modelo.establecimiento.salud.EstablecimientoSaludRespuesta;

@RequestScoped
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Path("establecimiento")
@Api
public class EstablecimientoSaludServicio {

	@Inject
	private EstablecimientoSaludDao establecimientoSaludDao;
	@Inject
	Logger log;
	@Context
	UriInfo uriInfo;

	@GET
	@Path("buscar/id/{id}")
	@ApiOperation(value = "Obtiene los datos del establecimiento de salud", tags = {
			"Establecimiento de Salud" }, notes = "Retorna el establecimiento de salud por el id.")
	@ApiResponses(value = {
			@ApiResponse(message = "Obtener establecimiento de salud por id", code = 200, response = EstablecimientoSaludRespuesta.class) })
	public Response obtener(@PathParam("id") Long id) {
		try {
			EstablecimientoSaludDto dto = establecimientoSaludDao.obtenerEstablecimientoId(id);
			if (dto == null) {
				return Response.status(Response.Status.NO_CONTENT).build();
			}
			EstablecimientoSaludRespuesta respuesta = EstablecimientoSaludRespuesta.builder().establecimiento(dto)
					.build();
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
