package net.unir.gestion.agendas.servicio;

import java.util.Objects;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import net.unir.gestion.agendas.util.ServicioUtil;
import net.unir.gson.util.GsonUtil;
import unir.net.modelo.gestion.agendas.CitaMedicaEntrada;
import unir.net.modelo.gestion.agendas.ParametrosAgendamientoRespuesta;

@RequestScoped
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Path("cita")
public class CancelacionServicio {

	@Inject
	Logger log;
	@Context
	UriInfo uriInfo;

	@POST
	@Path("cancelar")
	public Response listarCitasAgendadasPorCedula(CitaMedicaEntrada entrada) {
		ParametrosAgendamientoRespuesta respuesta = ServicioUtil.cancelarCita(entrada);
		if (Objects.isNull(respuesta)) {
			return Response.serverError().build();
		}
		return Response.ok(GsonUtil.generarGson(respuesta), MediaType.APPLICATION_JSON).build();

	}
}
