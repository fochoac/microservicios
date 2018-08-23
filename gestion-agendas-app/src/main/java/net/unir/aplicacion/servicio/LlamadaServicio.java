package net.unir.aplicacion.servicio;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.unir.cliente.ClienteRestUtil;
import net.unir.gson.util.GsonUtil;
import unir.net.modelo.gestion.agendas.AgendamientoEntrada;
import unir.net.modelo.gestion.agendas.CitaMedicaEntrada;
import unir.net.modelo.gestion.agendas.CitaMedicaRespuesta;
import unir.net.modelo.gestion.agendas.ParametrosAgendamientoRespuesta;
import unir.net.modelo.seguridad.usuario.UsuarioEntrada;
import unir.net.modelo.seguridad.usuario.UsuarioRespuesta;

public class LlamadaServicio {
	private static final Logger LOG = Logger.getLogger(LlamadaServicio.class.getName());

	public static final UsuarioRespuesta autenticar(final String usuario, final String clave) {
		final String url = "http://173.249.51.238:8182/api/usuario/validarCredenciales";
		UsuarioEntrada entrada = new UsuarioEntrada(clave, usuario);

		try {
			return ClienteRestUtil.hacerPost(url, GsonUtil.generarGson(entrada), UsuarioRespuesta.class);

		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error al autenticas", e);
			return null;
		}

	}

	public static final ParametrosAgendamientoRespuesta obtenerParametros() {
		final String url = "http://173.249.51.238:8082/api/cita/obtenerParametrosAgendas";
		try {
			return ClienteRestUtil.hacerGet(url, ParametrosAgendamientoRespuesta.class);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error al caragr lso parametros", e);
			return null;
		}
	}

	public static final ParametrosAgendamientoRespuesta listarMedicosPorEspecialidad(final Long idEspecialidad,
			Long idConsultorio) {
		final String url = "http://173.249.51.238:8082/api/cita/obtenerMedicos";
		try {
			AgendamientoEntrada entrada = new AgendamientoEntrada();
			entrada.setIdEspecialidad(idEspecialidad);
			entrada.setIdConsultorio(idConsultorio);
			return ClienteRestUtil.hacerPost(url, GsonUtil.generarGson(entrada), ParametrosAgendamientoRespuesta.class);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error al caragr lso parametros", e);
			return null;
		}
	}

	public static final ParametrosAgendamientoRespuesta listarHorarios(final Long idMedico) {
		final String url = "http://173.249.51.238:8082/api/cita/obtenerHorarios";
		try {
			AgendamientoEntrada entrada = new AgendamientoEntrada();
			entrada.setIdMedico(idMedico);
			return ClienteRestUtil.hacerPost(url, GsonUtil.generarGson(entrada), ParametrosAgendamientoRespuesta.class);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error al caragr lso parametros", e);
			return null;
		}
	}

	public static final ParametrosAgendamientoRespuesta guardar(final CitaMedicaEntrada entrada) {
		final String url = "http://173.249.51.238:8082/api/cita/guardarCita";
		try {

			return ClienteRestUtil.hacerPost(url, GsonUtil.generarGson(entrada), ParametrosAgendamientoRespuesta.class);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error al caragr lso parametros", e);
			return null;
		}
	}
	public static final ParametrosAgendamientoRespuesta cancelarCita(final CitaMedicaEntrada entrada) {
		final String url = "http://173.249.51.238:8082/api/cita/cancelar";
		try {

			return ClienteRestUtil.hacerPost(url, GsonUtil.generarGson(entrada), ParametrosAgendamientoRespuesta.class);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error al caragr lso parametros", e);
			return null;
		}
	}
	public static final CitaMedicaRespuesta listarAgendasPaciente(final String cedula) {
		final String url = "http://173.249.51.238:8082/api/cita/consultar/cedula";
		try {
			AgendamientoEntrada entrada = new AgendamientoEntrada();
			entrada.setCedula(cedula);
			return ClienteRestUtil.hacerPost(url, GsonUtil.generarGson(entrada), CitaMedicaRespuesta.class);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error al caragr lso parametros", e);
			return null;
		}
	}
}
