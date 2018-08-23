package net.unir.servicio.general.util;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.unir.servicio.general.modelo.Catalogo;

@Builder
public class CitaMedicaRespuesta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4860865076317254685L;
	@Getter
	@Setter
	private Catalogo citaMedica;
	@Getter
	@Setter
	private List<Catalogo> citas;

}
