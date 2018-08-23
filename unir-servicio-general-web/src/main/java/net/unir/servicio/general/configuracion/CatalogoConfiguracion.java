package net.unir.servicio.general.configuracion;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.kumuluz.ee.discovery.annotations.RegisterService;

@RegisterService("servicio-general")
@ApplicationPath("api")
public class CatalogoConfiguracion extends Application {

}
