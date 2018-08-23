package net.unir.gestion.agendas.configuracion;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.kumuluz.ee.discovery.annotations.RegisterService;

@RegisterService("gestion-agendas-api-gateway")
@ApplicationPath("api")
public class GestionAgendasConfiguracion extends Application {

}
