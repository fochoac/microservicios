/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unir.gestion.citas.medicas.util;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author hantsy
 * @param <T>
 * @param <ID>
 */
public abstract class AbstractRepository<T> {

	protected abstract EntityManager entityManager();

	@Getter
	@Setter
	private Class<T> entityClass;
//	@SuppressWarnings("unchecked")
//	private Class<T> entityClass() {
//		
//		ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
//		return (Class<T>) parameterizedType.getActualTypeArguments()[0];
//	}

	public List<T> findAll() {

		CriteriaBuilder cb = this.entityManager().getCriteriaBuilder();

		CriteriaQuery<T> q = cb.createQuery(getEntityClass());
		Root<T> c = q.from(getEntityClass());

		return entityManager().createQuery(q).getResultList();
	}

	@Transactional
	public T save(T entity) {
		EntityTransaction tx = entityManager().getTransaction();
		try {

			tx.begin();
			
			entityManager().persist(entity);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
		return entity;
	}
	@Transactional
	public T update(T entity) {
		EntityTransaction tx = entityManager().getTransaction();
		try {

			tx.begin();
			
			entityManager().merge(entity);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}
		return entity;
	}

	public T findById(Long id) {
		return entityManager().find(getEntityClass(), id);
	}

	@Transactional
	public boolean delete(T entity) {

		T _entity = entityManager().merge(entity);
		entityManager().remove(_entity);
		return true;
	}

	public void deleteById(Long id) {
		T _entity = findById(id);
		entityManager().remove(_entity);
	}

	public Stream<T> stream() {
		CriteriaBuilder cb = this.entityManager().getCriteriaBuilder();
		CriteriaQuery<T> q = cb.createQuery(getEntityClass());
		Root<T> c = q.from(getEntityClass());

		return entityManager().createQuery(q).getResultList().stream();
	}

	public Optional<T> findOptionalById(Long id) {
		return Optional.ofNullable(findById(id));
	}

}
