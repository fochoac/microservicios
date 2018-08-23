package net.unir.establecimiento.salud.configuracion;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.kumuluz.ee.discovery.annotations.RegisterService;

import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

@RegisterService("servicio-establecimiento-salud")
@ApplicationPath("api")
@SwaggerDefinition(info = @Info(title = "EstablecimientoSaludAPI", version = "v1.0.0"), host = "173.249.51.238:8081")
public class EstablecimientoConfiguracion extends Application {

}
