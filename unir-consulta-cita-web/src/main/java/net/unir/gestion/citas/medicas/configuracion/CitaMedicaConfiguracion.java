package net.unir.gestion.citas.medicas.configuracion;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.kumuluz.ee.discovery.annotations.RegisterService;

@RegisterService("gestion-consulta-cita-medica-servicio")
@ApplicationPath("api")
public class CitaMedicaConfiguracion extends Application {

}
