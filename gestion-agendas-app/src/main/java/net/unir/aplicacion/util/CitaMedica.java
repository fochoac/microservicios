package net.unir.aplicacion.util;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CitaMedica {

	private int id;

	private int consultorioId;

	private int especialidadId;

	private int establecimientoSaludId;

	private boolean estado;

	private String estadoCita;

	private int horarioId;

	private int medicoId;
	public CitaMedica() {
		
	}

}
