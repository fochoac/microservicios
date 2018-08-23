package net.unir.servicio.general.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.unir.servicio.general.util.AbstractRepository;

public class GenericoDao<T> extends AbstractRepository<T> {
	public static final String GENERAL_PU = "unir-citas-medicas-PU";
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
