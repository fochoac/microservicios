package net.unir.gestion.citas.medicas.dao;

import javax.enterprise.context.RequestScoped;

import net.unir.gestion.citas.medicas.dao.impl.GenericoDao;
import net.unir.gestion.citas.medicas.modelo.HistoricosCitaMedica;

@RequestScoped
public class HistoricoCitaMedicaDao extends GenericoDao<HistoricosCitaMedica> {

	public HistoricoCitaMedicaDao() {
		super(HistoricosCitaMedica.class);
	}
}
