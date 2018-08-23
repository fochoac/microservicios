package net.unir.aplicacion.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class CitaDto implements Serializable {

	private String nombreMedico;
	private LocalDate fechaCita;
	private LocalTime fechaInicio;
	private String especialidad;
	private String consultorio;
}
