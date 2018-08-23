package net.unir.aplicacion.seguridad;

import static net.unir.aplicacion.util.Utils.addDetailMessage;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Specializes;
import javax.faces.application.FacesMessage.Severity;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.codec.digest.DigestUtils;
import org.omnifaces.util.Faces;

import com.github.adminfaces.template.config.AdminConfig;
import com.github.adminfaces.template.session.AdminSession;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.unir.aplicacion.servicio.LlamadaServicio;
import unir.net.modelo.seguridad.persona.PersonaDto;
import unir.net.modelo.seguridad.rol.RolDto;
import unir.net.modelo.seguridad.usuario.UsuarioRespuesta;

/**
 * Created by rmpestano on 12/20/14.
 *
 * This is just a login example.
 *
 * AdminSession uses isLoggedIn to determine if user must be redirect to login
 * page or not. By default AdminSession isLoggedIn always resolves to true so it
 * will not try to redirect user.
 *
 * If you already have your authorization mechanism which controls when user
 * must be redirect to initial page or logon you can skip this class.
 */
@Named
@SessionScoped
@Specializes
@Data
@EqualsAndHashCode(callSuper = false)
public class LogonMB extends AdminSession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6051678597131282546L;
	private String currentUser;
	private String email;
	private String password;
	private boolean remember;
	private PersonaDto persona;
	private RolDto rol;
	@Inject
	private AdminConfig adminConfig;

	public void login() {
		String md5Hex = DigestUtils.md5Hex(password).toLowerCase();
		UsuarioRespuesta respuesta = LlamadaServicio.autenticar(email, md5Hex);
		if (Objects.isNull(respuesta) || !respuesta.isValido()) {
			addDetailMessage("Credenciales invalidas");
			return;
		}
		if (Objects.isNull(respuesta.getRol())) {
			addDetailMessage("No tiene permiso para ingresar a esta aplicacion.");
			return;
		}
		rol = respuesta.getRol();
		persona = respuesta.getPersona();
		currentUser = String.format("%s", persona.getNombres());
		addDetailMessage("Acceso concedido as <b>" + email + "</b>");
		Faces.getExternalContext().getFlash().setKeepMessages(true);

		try {
			Faces.redirect(adminConfig.getIndexPage());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public boolean isLoggedIn() {

		return currentUser != null;
	}

}
