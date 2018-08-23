package net.unir.servicio.seguridad.util;

import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@Dependent
public class Resources {

	@Produces
	public Logger getLogger(InjectionPoint p) {
		return Logger.getLogger(p.getMember().getDeclaringClass().getName());
	}

}
