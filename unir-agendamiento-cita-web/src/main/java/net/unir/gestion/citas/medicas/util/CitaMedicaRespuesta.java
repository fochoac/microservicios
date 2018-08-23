package net.unir.gestion.citas.medicas.util;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.unir.gestion.citas.medicas.modelo.CitaMedica;

@Builder
public class CitaMedicaRespuesta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4860865076317254685L;
	@Getter
	@Setter
	private CitaMedica citaMedica;
	@Getter
	@Setter
	private List<CitaMedica> citas;

}
