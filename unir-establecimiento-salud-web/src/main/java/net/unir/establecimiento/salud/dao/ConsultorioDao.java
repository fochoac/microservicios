package net.unir.establecimiento.salud.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import net.unir.establecimiento.salud.dao.impl.GenericoDao;
import net.unir.establecimiento.salud.modelo.Consultorio;
import unir.net.modelo.establecimiento.salud.consultorio.ConsultorioDto;

@RequestScoped
public class ConsultorioDao extends GenericoDao<Consultorio> {
	public ConsultorioDao() {
		super(Consultorio.class);
	}

	public List<ConsultorioDto> listarConsultorio() {
		List<Consultorio> consultorios = findAll();
		List<ConsultorioDto> lista = new ArrayList<>();
		consultorios.forEach(e -> {
			lista.add(ConsultorioDto.builder().id(e.getId()).ancho(e.getAncho()).largo(e.getLargo())
					.numeroConsultorio(e.getNumeroConsultorio()).piso(e.getPiso()).build());
		});
		return lista;
	}

	public ConsultorioDto listarConsultorio(final Long id) {
		Consultorio consultorio = findById(id);
		return ConsultorioDto.builder().numeroConsultorio(consultorio.getNumeroConsultorio()).build();

	}
}
