package net.unir.servicio.seguridad.servicio;

import java.util.Objects;
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

import net.unir.gson.util.GsonUtil;
import net.unir.servicio.seguridad.dao.PersonaDao;
import net.unir.servicio.seguridad.dao.RolDao;
import net.unir.servicio.seguridad.dao.UsuarioDao;
import net.unir.servicio.seguridad.modelo.Persona;
import unir.net.modelo.seguridad.persona.PersonaDto;
import unir.net.modelo.seguridad.rol.RolDto;
import unir.net.modelo.seguridad.usuario.UsuarioEntrada;
import unir.net.modelo.seguridad.usuario.UsuarioRespuesta;

@RequestScoped
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Path("usuario")
public class UsuarioServicio {
	@Inject
	private UsuarioDao dao;
	@Inject
	private PersonaDao personaDao;
	@Inject
	private RolDao rolDao;

	@Inject
	Logger log;
	@Context
	UriInfo uriInfo;

	@POST
	@Path("validarCredenciales")
	public Response obtenerCatalogo(UsuarioEntrada entrada) {
		try {
			log.info(entrada.toString());
			Persona persona = dao.validarCredenciales(entrada.getUsuario(), entrada.getClave());
			UsuarioRespuesta respuesta = UsuarioRespuesta.builder().build();
			if (Objects.isNull(persona)) {
				respuesta.setValido(false);
				return Response.ok(GsonUtil.generarGson(respuesta), MediaType.APPLICATION_JSON).build();
			}

			PersonaDto personaDto = personaDao.obtenerPorCedula(persona.getCedula());
			RolDto rol = rolDao.obtenerRolPOrUserName(entrada.getUsuario());
			respuesta.setValido(true);
			respuesta.setPersona(personaDto);
			respuesta.setRol(rol);

			return Response.ok(GsonUtil.generarGson(respuesta), MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			log.log(Level.SEVERE, "error al consultar el servicio", e);
			return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
		}

	}

}
