package net.unir.aplicacion.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import lombok.Getter;
import lombok.Setter;
import net.unir.aplicacion.dto.CitaDto;
import net.unir.aplicacion.seguridad.LogonMB;
import net.unir.aplicacion.servicio.LlamadaServicio;
import net.unir.aplicacion.util.CitaMedica;
import net.unir.aplicacion.util.Utils;
import unir.net.modelo.establecimiento.salud.EstablecimientoSaludDto;
import unir.net.modelo.establecimiento.salud.consultorio.ConsultorioDto;
import unir.net.modelo.establecimiento.salud.especialidad.EspecialidadDto;
import unir.net.modelo.establecimiento.salud.horario.HorarioDto;
import unir.net.modelo.establecimiento.salud.medico.MedicoDto;
import unir.net.modelo.gestion.agendas.CitaMedicaEntrada;
import unir.net.modelo.gestion.agendas.ParametrosAgendamientoRespuesta;
import unir.net.modelo.seguridad.persona.PersonaDto;

@ViewScoped
@Named
@SuppressWarnings("static-access")
public class AgendamientoCitaMedicaControlador implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -770901744848992754L;
	private static final Logger LOG = Logger.getLogger(AgendamientoCitaMedicaControlador.class.getName());
	@Getter
	@Setter
	private List<CitaMedica> citas;
	@Inject
	LogonMB login;
	@Inject
	Utils utilitario;
	@Getter
	@Setter
	private Long idEspecialidad;
	@Getter
	@Setter
	private Long idConsultorio;
	@Getter
	@Setter
	private Long idMedico;
	@Getter
	@Setter
	private List<EspecialidadDto> especialidades;
	@Getter
	@Setter
	private List<ConsultorioDto> consultorios;
	@Getter
	@Setter
	private EstablecimientoSaludDto establecimientoSalud;
	@Getter
	@Setter
	private List<MedicoDto> medicos;
	@Getter
	@Setter
	private List<HorarioDto> horarios;
	@Getter
	@Setter
	private Long idHorario;
	@Getter
	@Setter
	private CitaDto citaAgendada;
	@Getter
	@Setter
	private boolean pantallaAgendamiento = true;
	@Getter
	@Setter
	private boolean detalleAgendamiento = false;

	@PostConstruct
	public void init() {

		try {
			cargarParametros();
			pantallaAgendamiento = true;
			detalleAgendamiento = false;
		} catch (Exception e) {
			System.out.println("hola2");
			LOG.log(Level.SEVERE, "Error al cargar los parametros", e);
		}
	}

	public void cargarParametros() throws Exception {
		ParametrosAgendamientoRespuesta respuesta = LlamadaServicio.obtenerParametros();
		if (Objects.isNull(respuesta)) {
			throw new Exception("No se pudo cargar los parametros");
		}
		System.out.println("hola");
		especialidades = respuesta.getEspecialidades();
		consultorios = respuesta.getConsultorios();
		establecimientoSalud = respuesta.getDatosEstablecimiento();
		LOG.info(especialidades.toString());
		LOG.info(consultorios.toString());
		LOG.info(establecimientoSalud.toString());

	}

	public void cargarMedicos() {
		System.out.println("idcondultorio:" + idConsultorio);
		System.out.println("idespecialidad:" + idEspecialidad);
		ParametrosAgendamientoRespuesta respuesta = LlamadaServicio.listarMedicosPorEspecialidad(idEspecialidad,
				idConsultorio);
		if (Objects.isNull(respuesta)) {
			utilitario.addDetailMessage("No existen resultados para las opciones seleccionadas");
			return;
		}
		medicos = respuesta.getMedicos();
	}

	public void cargarHorarios(Long idMedico) {
		this.idMedico = idMedico;
		System.out.println("idMedico:" + idMedico);
		ParametrosAgendamientoRespuesta respuesta = LlamadaServicio.listarHorarios(idMedico);
		if (Objects.isNull(respuesta)) {
			utilitario.addDetailMessage("No existen horarios disponibles");
			return;
		}
		this.horarios = respuesta.getHorarios();
		PrimeFaces.current().executeScript("PF('dlg2').show();");

		System.out.println("tamanio: " + horarios.size());
	}

	public void agendarCita(Long idHorario) {
		this.idHorario = idHorario;
		System.out.println(idHorario);
		CitaMedicaEntrada citaMedicaEntrada = CitaMedicaEntrada.builder().consultorioId(idConsultorio)
				.especialidadId(idEspecialidad).establecimientoSaludId(getEstablecimientoSalud().getId())
				.horarioId(idHorario).medicoId(idMedico).cedulaPaciente(obtenerDatosPersona().getCedula()).build();
		LOG.info("Objeto: " + citaMedicaEntrada);
		ParametrosAgendamientoRespuesta respuesta = LlamadaServicio.guardar(citaMedicaEntrada);
		if (Objects.isNull(respuesta)) {
			utilitario.addDetailMessage("Ocurrio un error al guardar la cita medica");
			return;
		}
		if (Objects.isNull(respuesta.getIdAgenda())) {
			utilitario.addDetailMessage("Ocurrio un error al guardar la cita medica");
			return;
		}
		utilitario.addDetailMessage("Se a agendado la cita medica exitosamente", FacesMessage.SEVERITY_INFO);

		pantallaAgendamiento = false;
		detalleAgendamiento = true;
		PrimeFaces.current().executeScript("PF('dlg2').hide();");
		PrimeFaces.current().executeScript("myFunction();");

		llenarResumenCita(citaMedicaEntrada);

	}

	private void llenarResumenCita(CitaMedicaEntrada citaMedicaEntrada) {
		citaAgendada = new CitaDto();
		ConsultorioDto consultorio = consultorios.stream()
				.filter(c -> c.getId() == citaMedicaEntrada.getConsultorioId()).findFirst().get();
		citaAgendada.setConsultorio(consultorio.getNumeroConsultorio());
		EspecialidadDto especialidad = especialidades.stream()
				.filter(c -> c.getId() == citaMedicaEntrada.getEspecialidadId()).findFirst().get();
		citaAgendada.setEspecialidad(especialidad.getNombre());
		HorarioDto horario = horarios.stream().filter(c -> c.getId() == citaMedicaEntrada.getHorarioId()).findFirst()
				.get();
		citaAgendada.setFechaCita(horario.getFecha());
		citaAgendada.setFechaInicio(horario.getHoraInicio());
		MedicoDto medicoDto = medicos.stream().filter(c -> c.getId() == citaMedicaEntrada.getMedicoId()).findFirst()
				.get();
		citaAgendada.setNombreMedico(medicoDto.getNombreMedico());
	}

	public PersonaDto obtenerDatosPersona() {
		return login.getPersona();
	}
}
