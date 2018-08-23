package net.unir.gestion.citas.medicas.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.unir.gestion.citas.medicas.util.AbstractRepository;

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
