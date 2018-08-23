package net.unir.servicio.general.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;

import net.unir.servicio.general.dao.impl.GenericoDao;
import net.unir.servicio.general.modelo.Catalogo;
import unir.net.modelo.catalogo.CatalogoDto;

@RequestScoped
public class CatalogoDao extends GenericoDao<Catalogo> {
	public CatalogoDao() {
		super(Catalogo.class);
	}

	public CatalogoDto listarPorNemonico(final String nemonico) {
		Query query = entityManager().createNamedQuery(Catalogo.LISTAR_POR_NEMONICO);
		query.setParameter("nemonico", nemonico);
		List<Catalogo> lista = query.getResultList();
		List<CatalogoDto> catalogos = procesarResultado(lista);
		return catalogos.isEmpty() ? null : catalogos.get(0);
	}

	public List<CatalogoDto> listarHijosPorNemonicoPadre(final String nemonico) {
		Query query = entityManager().createNamedQuery(Catalogo.LISTAR_HIJOS_POR_NEMONICO_PADRE);
		query.setParameter("nemonico", nemonico);
		List<Catalogo> lista = query.getResultList();
		List<CatalogoDto> catalogos = procesarResultado(lista);
		catalogos.forEach(c -> c.setNemonicoPadre(nemonico));
		return catalogos;

	}

	private List<CatalogoDto> procesarResultado(List<Catalogo> lista) {
		if (lista != null && !lista.isEmpty()) {
			List<CatalogoDto> catalogos = new ArrayList<>();
			lista.forEach(h -> catalogos.add(CatalogoDto.builder().descripcion(h.getDescripcion()).id(h.getId())
					.nemonico(h.getNemonico()).build()));
			return catalogos;
		}
		return new ArrayList<>();
	}
}
