package net.unir.servicio.seguridad.configuracion;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.kumuluz.ee.discovery.annotations.RegisterService;

@RegisterService("seguridad")
@ApplicationPath("api")
public class SeguridadConfiguracion extends Application {

}
