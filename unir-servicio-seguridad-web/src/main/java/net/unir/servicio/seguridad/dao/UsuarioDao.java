package net.unir.servicio.seguridad.dao;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;

import net.unir.servicio.seguridad.dao.impl.GenericoDao;
import net.unir.servicio.seguridad.modelo.Persona;
import net.unir.servicio.seguridad.modelo.Usuario;

@RequestScoped
public class UsuarioDao extends GenericoDao<Usuario> {
	public UsuarioDao() {
		super(Usuario.class);
	}

	public Persona validarCredenciales(final String usuario, final String clave) {
		StringBuilder builder = new StringBuilder().append("select p.* from personas p, usuarios u\n"
				+ "where u.persona_pk=p.persona_pk\n" + "and u.usuario = ?1\n" + "and u.password = ?2");
		Query query = entityManager().createNativeQuery(builder.toString(), Persona.class);
		query.setParameter("1", usuario);
		query.setParameter("2", clave);
		List<Persona> personas = query.getResultList();
		System.out.println("Tamanio de la lista: "+personas.size());
		if (Objects.isNull(personas) || personas.isEmpty()) {
			return null;
		}
		return personas.get(0);
	}

}
