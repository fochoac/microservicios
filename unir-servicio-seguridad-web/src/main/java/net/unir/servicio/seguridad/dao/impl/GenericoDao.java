package net.unir.servicio.seguridad.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.unir.servicio.seguridad.util.AbstractRepository;

public class GenericoDao<T> extends AbstractRepository<T> {
	
	@PersistenceContext
	private EntityManager em;

	public GenericoDao() {

	}

	public GenericoDao(Class<T> clazz) {
		setEntityClass(clazz);
	}

	@Override
	protected EntityManager entityManager() {

		return em;
	}
	
	
}
