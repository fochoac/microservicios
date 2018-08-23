package net.unir.servicio.seguridad.dao;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;

import net.unir.servicio.seguridad.dao.impl.GenericoDao;
import net.unir.servicio.seguridad.modelo.Persona;
import unir.net.modelo.seguridad.persona.PersonaDto;

@RequestScoped
public class PersonaDao extends GenericoDao<Persona> {
	
	public PersonaDao() {
		super(Persona.class);
	}

	public PersonaDto obtenerPorCedula(final String cedula) {
		Query query = entityManager().createNamedQuery(Persona.LISTAR_PERSONAS_POR_CEDULA);
		query.setParameter("cedula", cedula);
		List<Persona> lista = query.getResultList();
		if (Objects.isNull(lista) || lista.isEmpty()) {
			return null;
		}
		Persona persona = lista.get(0);
		PersonaDto dto = PersonaDto.builder().apellidos(persona.getApellidos()).cedula(persona.getCedula())
				.fechaNacimiento(persona.getFechaNacimiento()).genero(persona.getGenero()).id(persona.getId())
				.nombres(persona.getNombres()).build();
		return dto;
	}
	
	public PersonaDto obtenerPorId(final Long id) {
		Query query = entityManager().createNamedQuery(Persona.LISTAR_PERSONAS_POR_ID);
		query.setParameter("id", id);
		List<Persona> lista = query.getResultList();
		if (Objects.isNull(lista) || lista.isEmpty()) {
			return null;
		}
		Persona persona = lista.get(0);
		PersonaDto dto = PersonaDto.builder().apellidos(persona.getApellidos()).cedula(persona.getCedula())
				.fechaNacimiento(persona.getFechaNacimiento()).genero(persona.getGenero()).id(persona.getId())
				.nombres(persona.getNombres()).build();
		return dto;
	}

}
