package net.unir.servicio.seguridad.dao;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;

import net.unir.servicio.seguridad.dao.impl.GenericoDao;
import net.unir.servicio.seguridad.modelo.Rol;
import unir.net.modelo.seguridad.rol.RolDto;

@RequestScoped
public class RolDao extends GenericoDao<Rol> {

	public RolDto obtenerRolPOrUserName(final String usuario) {
		StringBuilder builder = new StringBuilder().append("select r.* from roles r, usuarios u, usuarios_roles ur\n"
				+ "where ur.usuario_pk=u.usuario_pk\n" + "and ur.rol_pk=r.rol_pk\n" + "and u.usuario=?");
		Query query = entityManager().createNativeQuery(builder.toString(), Rol.class);
		query.setParameter(1, usuario);
		List<Rol> roles = query.getResultList();
		if (Objects.isNull(roles) || roles.isEmpty()) {
			return null;
		}
		return RolDto.builder().nombre(roles.get(0).getNombre()).build();
	}
}
