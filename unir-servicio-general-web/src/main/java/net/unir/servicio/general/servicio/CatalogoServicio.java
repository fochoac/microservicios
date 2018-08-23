package net.unir.servicio.general.servicio;

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
import net.unir.servicio.general.dao.CatalogoDao;
import unir.net.modelo.catalogo.CatalogoDto;
import unir.net.modelo.catalogo.CatalogoRespuesta;

@RequestScoped
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Path("catalogo")
public class CatalogoServicio {

	@Inject
	private CatalogoDao catalogoDao;
	@Inject
	Logger log;
	@Context
	UriInfo uriInfo;

	@GET
	@Path("listarHijos/nemonico/{nemonico}")
	public Response listarHijos(@PathParam("nemonico") String nemonico) {
		try {
			List<CatalogoDto> hijos = catalogoDao.listarHijosPorNemonicoPadre(nemonico);
			if (hijos.isEmpty()) {
				return Response.status(Response.Status.NO_CONTENT).build();
			}
			CatalogoRespuesta respuesta = CatalogoRespuesta.builder().catalogos(hijos).build();
			return Response.ok(GsonUtil.generarGson(respuesta), MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		}

	}

	@GET
	@Path("obtener/nemonico/{nemonico}")
	public Response obtenerCatalogo(@PathParam("nemonico") String nemonico) {
		try {
			CatalogoDto dto = catalogoDao.listarPorNemonico(nemonico);
			if (Objects.isNull(dto)) {
				return Response.status(Response.Status.NO_CONTENT).build();
			}
			CatalogoRespuesta respuesta = CatalogoRespuesta.builder().catalogo(dto).build();
			return Response.ok(GsonUtil.generarGson(respuesta), MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		}

	}

}
