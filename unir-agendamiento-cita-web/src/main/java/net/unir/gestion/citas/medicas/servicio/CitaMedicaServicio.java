package net.unir.gestion.citas.medicas.servicio;

import java.util.logging.Level;
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

import net.unir.gestion.citas.medicas.dao.CitaMedicaDao;
import net.unir.gestion.citas.medicas.modelo.CitaMedica;
import net.unir.gson.util.GsonUtil;
import unir.net.modelo.gestion.agendas.CitaMedicaEntrada;
import unir.net.modelo.gestion.agendas.ParametrosAgendamientoRespuesta;

@RequestScoped
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Path("cita")
public class CitaMedicaServicio {

	@Inject
	private CitaMedicaDao citaMedicaDao;
	@Inject
	Logger log;
	@Context
	UriInfo uriInfo;

	@POST
	@Path("guardar")
	public Response guardar(CitaMedicaEntrada cita) {
		try {
			System.out.println("INgreso al metodo de guardar cita");
			CitaMedica citaMedica = citaMedicaDao.guardarCitaMedica(cita);
			ParametrosAgendamientoRespuesta respuesta = new ParametrosAgendamientoRespuesta();
			respuesta.setIdAgenda(citaMedica.getId());
			return Response.ok(GsonUtil.generarGson(respuesta)).build();

		} catch (RuntimeException e) {
			log.log(Level.SEVERE, "Error al guardar la agenda", e);
			return Response.serverError().build();
		} catch (Exception e) {
			log.log(Level.SEVERE, "Error al guardar la agenda", e);
			return Response.serverError().build();
		}

	}

	@POST
	@Path("cancelar")
	public Response cancelar(CitaMedicaEntrada cita) {
		try {

			if (citaMedicaDao.cancelarCita(cita)) {
				ParametrosAgendamientoRespuesta respuesta = new ParametrosAgendamientoRespuesta();
				respuesta.setCancelacionExitosa(true);
				return Response.ok(GsonUtil.generarGson(respuesta)).build();
			}
			return Response.serverError().build();

		} catch (RuntimeException e) {
			log.log(Level.SEVERE, "Error al guardar la agenda", e);
			return Response.serverError().build();
		} catch (Exception e) {
			log.log(Level.SEVERE, "Error al guardar la agenda", e);
			return Response.serverError().build();
		}

	}
}
