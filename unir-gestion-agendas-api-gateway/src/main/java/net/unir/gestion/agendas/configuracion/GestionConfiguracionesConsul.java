package net.unir.gestion.agendas.configuracion;

import lombok.Getter;
import lombok.Setter;

//@ApplicationScoped
//@ConfigBundle(value = "url-servicios", watch = true)
public class GestionConfiguracionesConsul {
	@Getter
	@Setter
	private String servicioGeneral;
	@Getter
	@Setter
	private String servicioSeguridad;
	@Getter
	@Setter
	private String servicioEstablecimientoSalud;

}
