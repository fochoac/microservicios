package net.unir.gestion.agendas.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;

import net.unir.cliente.ClienteRestUtil;
import net.unir.gson.util.GsonUtil;
import net.unir.servicio.AgendamientoEnum;
import net.unir.servicio.CatalogoEnum;
import net.unir.servicio.EstablecimientoEnum;
import net.unir.servicio.PersonaEnum;
import unir.net.modelo.catalogo.CatalogoRespuesta;
import unir.net.modelo.establecimiento.salud.EstablecimientoSaludRespuesta;
import unir.net.modelo.establecimiento.salud.consultorio.ConsultorioRespuesta;
import unir.net.modelo.establecimiento.salud.especialidad.EspecialidadRespuesta;
import unir.net.modelo.establecimiento.salud.horario.HorarioRespuesta;
import unir.net.modelo.establecimiento.salud.medico.MedicoRespuesta;
import unir.net.modelo.gestion.agendas.CitaMedicaEntrada;
import unir.net.modelo.gestion.agendas.CitaMedicaRespuesta;
import unir.net.modelo.gestion.agendas.ParametrosAgendamientoRespuesta;
import unir.net.modelo.seguridad.persona.PersonaRespuesta;

public final class ServicioUtil {
	private static final Logger LOG = Logger.getLogger(ServicioUtil.class.getName());

	private ServicioUtil() {

	}

	private static String obtenerUrlServicio(final String propiedad) {
		return ConfigurationUtil.getInstance().get(propiedad).get();

	}

	public static final PersonaRespuesta obtenerPersona(final String cedula) {
		final String url = obtenerUrlServicio(PersonaEnum.OBTENER_POR_CEDULA.getCodigo());
		String urlFinal = url.replace(PersonaEnum.OBTENER_POR_CEDULA.getParametros()[0], cedula);
		try {
			return ClienteRestUtil.hacerGet(urlFinal, PersonaRespuesta.class);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error al consumir el servicio persona", e);

		}
		return null;

	}

	public static final PersonaRespuesta obtenerPersonaPorId(final String id) {
		final String url = obtenerUrlServicio(PersonaEnum.OBTENER_POR_ID.getCodigo());
		String urlFinal = url.replace(PersonaEnum.OBTENER_POR_ID.getParametros()[0], id);
		System.out.println(urlFinal);
		try {
			return ClienteRestUtil.hacerGet(urlFinal, PersonaRespuesta.class);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error al consumir el servicio persona", e);

		}
		return null;

	}

	public static final CatalogoRespuesta obtenerCatalogo(final String nemonico) {
		final String url = obtenerUrlServicio(CatalogoEnum.OBTENER_POR_NEMONICO.getCodigo());
		String urlFinal = url.replace(CatalogoEnum.OBTENER_POR_NEMONICO.getParametros()[0], nemonico);
		try {
			return ClienteRestUtil.hacerGet(urlFinal, CatalogoRespuesta.class);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error al consumir el servicio persona", e);

		}
		return null;
	}

	public static final CatalogoRespuesta listarCatalogoHijos(final String nemonico) {
		final String url = obtenerUrlServicio(CatalogoEnum.LISTAR_HIJOS.getCodigo());
		String urlFinal = url.replace(CatalogoEnum.LISTAR_HIJOS.getParametros()[0], nemonico);
		try {
			return ClienteRestUtil.hacerGet(urlFinal, CatalogoRespuesta.class);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error al consumir el servicio persona", e);

		}
		return null;
	}

	public static final EstablecimientoSaludRespuesta obtenerDatosEstablecimientoPorId(final String id) {
		final String url = obtenerUrlServicio(EstablecimientoEnum.OBTENER_ID.getCodigo());
		String urlFinal = url.replace(EstablecimientoEnum.OBTENER_ID.getParametros()[0], id);
		try {
			return ClienteRestUtil.hacerGet(urlFinal, EstablecimientoSaludRespuesta.class);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error al consumir el servicio persona", e);

		}
		return null;
	}

	public static final ConsultorioRespuesta listarTodosConsultoriosEstablecimiento() {
		final String url = obtenerUrlServicio(EstablecimientoEnum.LISTAR_CONSULTORIO_ESTABLECIMIENTO.getCodigo());
		// String urlFinal = url.replace(CatalogoEnum.LISTAR_HIJOS.getParametros()[0],
		// id);
		try {
			return ClienteRestUtil.hacerGet(url, ConsultorioRespuesta.class);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error al consumir el servicio persona", e);

		}
		return null;
	}

	public static final EspecialidadRespuesta listarTodasEspecialidades() {
		final String url = obtenerUrlServicio(EstablecimientoEnum.LISTAR_ESPECIALDIADS_ESTABLECIMIENTO.getCodigo());
		// String urlFinal = url.replace(CatalogoEnum.LISTAR_HIJOS.getParametros()[0],
		// id);
		try {
			return ClienteRestUtil.hacerGet(url, EspecialidadRespuesta.class);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error al consumir el servicio persona", e);

		}
		return null;
	}

	public static final HorarioRespuesta listarHorariosPorMedico(final String id) {
		final String url = obtenerUrlServicio(EstablecimientoEnum.LISTAR_HORARIOS_MEDICO_CONSULTORIO.getCodigo());
		String urlFinal = url.replace(EstablecimientoEnum.LISTAR_HORARIOS_MEDICO_CONSULTORIO.getParametros()[0], id);
		try {
			return ClienteRestUtil.hacerGet(urlFinal, HorarioRespuesta.class);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error al consumir el servicio persona", e);

		}
		return null;
	}

	public static final MedicoRespuesta listarMedicosPorEspecialidadYConsultorio(final String idEspecialidad,
			final String idConsultorio) {
		final String url = obtenerUrlServicio(EstablecimientoEnum.OBTENER_MEDICO_ESPECIALDIAD_CONSULTORIO.getCodigo());
		String urlFinal = url
				.replace(EstablecimientoEnum.OBTENER_MEDICO_ESPECIALDIAD_CONSULTORIO.getParametros()[0], idEspecialidad)
				.replace(EstablecimientoEnum.OBTENER_MEDICO_ESPECIALDIAD_CONSULTORIO.getParametros()[1], idConsultorio);
		try {
			return ClienteRestUtil.hacerGet(urlFinal, MedicoRespuesta.class);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error al consumir el servicio persona", e);

		}
		return null;
	}

	public static final MedicoRespuesta obtenerMedicoPorId(final String id) {
		final String url = obtenerUrlServicio(EstablecimientoEnum.OBTENER_MEDICO_ESTABLECIMIENTO_ID.getCodigo());
		String urlFinal = url.replace(EstablecimientoEnum.OBTENER_MEDICO_ESTABLECIMIENTO_ID.getParametros()[0], id);
		try {
			return ClienteRestUtil.hacerGet(urlFinal, MedicoRespuesta.class);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error al consumir el servicio persona", e);

		}
		return null;
	}

	public static final ParametrosAgendamientoRespuesta guardarCita(final CitaMedicaEntrada entrada) {
		final String url = obtenerUrlServicio(AgendamientoEnum.GUARDAR_CITA.getCodigo());
		LOG.info("cita_url:" + url);
		try {
			return ClienteRestUtil.hacerPost(url, GsonUtil.generarGson(entrada), ParametrosAgendamientoRespuesta.class);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error al consumir el servicio persona", e);

		}
		return null;
	}
	public static final ParametrosAgendamientoRespuesta cancelarCita(final CitaMedicaEntrada entrada) {
		final String url = obtenerUrlServicio(AgendamientoEnum.CANCELAR_CITA.getCodigo());
		LOG.info("cita_url:" + url);
		try {
			return ClienteRestUtil.hacerPost(url, GsonUtil.generarGson(entrada), ParametrosAgendamientoRespuesta.class);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error al consumir el servicio persona", e);

		}
		return null;
	}

	public static final ConsultorioRespuesta obtenerConsultorioPorId(final String id) {
		final String url = obtenerUrlServicio(EstablecimientoEnum.OBTENER_CONSULTORIO_POR_ID.getCodigo());
		String urlFinal = url.replace(EstablecimientoEnum.OBTENER_CONSULTORIO_POR_ID.getParametros()[0], id);
		System.out.println(urlFinal);
		try {
			return ClienteRestUtil.hacerGet(urlFinal, ConsultorioRespuesta.class);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error al consumir el servicio persona", e);

		}
		return null;

	}

	public static final EspecialidadRespuesta obtenerEspecialidadPorId(final String id) {
		final String url = obtenerUrlServicio(EstablecimientoEnum.OBTENER_ESPECIALIDAD_POR_ID.getCodigo());
		String urlFinal = url.replace(EstablecimientoEnum.OBTENER_ESPECIALIDAD_POR_ID.getParametros()[0], id);
		System.out.println(urlFinal);
		try {
			return ClienteRestUtil.hacerGet(urlFinal, EspecialidadRespuesta.class);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error al consumir el servicio persona", e);

		}
		return null;

	}

	public static final HorarioRespuesta obtenerHOrarioId(final String id) {
		final String url = obtenerUrlServicio(EstablecimientoEnum.OBTENER_HORARIO_POR_ID.getCodigo());
		String urlFinal = url.replace(EstablecimientoEnum.OBTENER_HORARIO_POR_ID.getParametros()[0], id);
		System.out.println(urlFinal);
		try {
			return ClienteRestUtil.hacerGet(urlFinal, HorarioRespuesta.class);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error al consumir el servicio persona", e);

		}
		return null;

	}

	public static final CitaMedicaRespuesta listarCitasPorCedula(final String cedula) {
		final String url = obtenerUrlServicio(AgendamientoEnum.CONSULTAR_CITA.getCodigo());
		String urlFinal = url.replace(AgendamientoEnum.CONSULTAR_CITA.getParametros()[0], cedula);
		System.out.println(urlFinal);
		try {
			return ClienteRestUtil.hacerGet(urlFinal, CitaMedicaRespuesta.class);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error al consumir el servicio persona", e);

		}
		return null;
	}
}
