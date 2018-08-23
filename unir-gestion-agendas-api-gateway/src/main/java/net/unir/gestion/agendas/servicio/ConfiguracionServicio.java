package net.unir.gestion.agendas.servicio;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;

import net.unir.gestion.agendas.configuracion.GestionConfiguracionesConsul;

//@RequestScoped
//@Path("/")
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
public class ConfiguracionServicio {

	@Inject
	private GestionConfiguracionesConsul configuracion;

	@GET
	@Path("/config")
	public Response test() {
		String response = "{\"servicioEstablecimientoSalud\": \"%s\" }";

		response = String.format(response,
				ConfigurationUtil.getInstance().get("url-servicios.servicio-establecimiento-salud ").get());

		return Response.ok(response).build();
	}
}
