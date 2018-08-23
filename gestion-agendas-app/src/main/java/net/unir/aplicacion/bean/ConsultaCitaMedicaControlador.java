package net.unir.aplicacion.bean;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;
import lombok.Setter;
import net.unir.aplicacion.seguridad.LogonMB;
import net.unir.aplicacion.servicio.LlamadaServicio;
import net.unir.aplicacion.util.Utils;
import unir.net.modelo.gestion.agendas.CitaMedicaDto;
import unir.net.modelo.gestion.agendas.CitaMedicaEntrada;
import unir.net.modelo.gestion.agendas.CitaMedicaRespuesta;
import unir.net.modelo.gestion.agendas.ParametrosAgendamientoRespuesta;
import unir.net.modelo.seguridad.persona.PersonaDto;

@ViewScoped
@Named
@SuppressWarnings("static-access")
public class ConsultaCitaMedicaControlador implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -770901744848992754L;
	private static final Logger LOG = Logger.getLogger(ConsultaCitaMedicaControlador.class.getName());

	@Inject
	LogonMB login;
	@Inject
	Utils utilitario;
	@Getter
	@Setter
	private List<CitaMedicaDto> citas;

	@PostConstruct
	public void init() {

		try {
			cargarParametros();

		} catch (Exception e) {
			System.out.println("hola2");
			LOG.log(Level.SEVERE, "Error al cargar los parametros", e);
		}
	}

	public void cargarParametros() throws Exception {
		CitaMedicaRespuesta respuesta = LlamadaServicio.listarAgendasPaciente(obtenerDatosPersona().getCedula());
		this.citas = respuesta.getCitas();
	}

	public void cancelarCita(Long idCita) {
		CitaMedicaEntrada entrada = new CitaMedicaEntrada();
		entrada.setIdCitaCancelar(idCita);
		ParametrosAgendamientoRespuesta respuesta = LlamadaServicio.cancelarCita(entrada);
		if (respuesta.isCancelacionExitosa()) {
			try {
				cargarParametros();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			utilitario.addDetailMessage("Cita medica cancelada con exito", FacesMessage.SEVERITY_INFO);
		}
		
	}

	public PersonaDto obtenerDatosPersona() {
		return login.getPersona();
	}
}
