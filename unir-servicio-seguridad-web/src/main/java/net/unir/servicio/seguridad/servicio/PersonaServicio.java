package net.unir.servicio.seguridad.servicio;

import java.util.List;
import java.util.Objects;
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

import net.unir.gson.util.GsonUtil;
import net.unir.servicio.seguridad.dao.PersonaDao;
import unir.net.modelo.seguridad.persona.PersonaDto;
import unir.net.modelo.seguridad.persona.PersonaRespuesta;

@RequestScoped
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Path("persona")
public class PersonaServicio {

	@Inject
	private PersonaDao dao;
	@Inject
	Logger log;
	@Context
	UriInfo uriInfo;

	@GET
	@Path("obtener/cedula/{cedula}")
	public Response obtenerCatalogo(@PathParam("cedula") String cedula) {
		try {
			PersonaDto dto = dao.obtenerPorCedula(cedula);
			if (Objects.isNull(dto)) {
				return Response.status(Response.Status.NO_CONTENT).build();
			}
			PersonaRespuesta respuesta = PersonaRespuesta.builder().persona(dto).build();
			return Response.ok(GsonUtil.generarGson(respuesta), MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		}

	}

	@GET
	@Path("obtener/id/{id}")
	public Response obtenerCatalogo(@PathParam("id") Long id) {
		try {
			PersonaDto dto = dao.obtenerPorId(id);
			if (Objects.isNull(dto)) {
				return Response.status(Response.Status.NO_CONTENT).build();
			}
			PersonaRespuesta respuesta = PersonaRespuesta.builder().persona(dto).build();
			return Response.ok(GsonUtil.generarGson(respuesta), MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		}

	}

}
