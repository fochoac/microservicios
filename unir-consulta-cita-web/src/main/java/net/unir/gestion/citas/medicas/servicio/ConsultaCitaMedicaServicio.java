package net.unir.gestion.citas.medicas.servicio;

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

import net.unir.gestion.citas.medicas.dao.CitaMedicaDao;
import net.unir.gson.util.GsonUtil;
import unir.net.modelo.gestion.agendas.CitaMedicaEntrada;
import unir.net.modelo.gestion.agendas.CitaMedicaRespuesta;

@RequestScoped
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Path("cita")
public class ConsultaCitaMedicaServicio {

	@Inject
	private CitaMedicaDao citaMedicaDao;
	@Inject
	Logger log;
	@Context
	UriInfo uriInfo;

	@GET
	@Path("listar/cedula/{cedula}")
	public Response guardar(@PathParam("cedula") String cedula) {
		try {

			List<CitaMedicaEntrada> citas = citaMedicaDao.listarCitasMedicas(cedula);
			CitaMedicaRespuesta respuesta = CitaMedicaRespuesta.builder().citasMedicas(citas).build();

			if (Objects.isNull(citas)) {
				return Response.noContent().build();
			}
			return Response.ok(GsonUtil.generarGson(respuesta)).build();

		} catch (RuntimeException e) {
			log.log(Level.SEVERE, "Error al guardar la agenda", e);
			return Response.serverError().build();
		} catch (Exception e) {
			log.log(Level.SEVERE, "Error al guardar la agenda", e);
			return Response.serverError().build();
		}

	}
}
