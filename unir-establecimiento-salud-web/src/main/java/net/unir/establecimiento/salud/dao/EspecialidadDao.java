package net.unir.establecimiento.salud.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import net.unir.establecimiento.salud.dao.impl.GenericoDao;
import net.unir.establecimiento.salud.modelo.Especialidad;
import unir.net.modelo.establecimiento.salud.especialidad.EspecialidadDto;

@RequestScoped
public class EspecialidadDao extends GenericoDao<Especialidad> {
	public EspecialidadDao() {
		super(Especialidad.class);
	}

	public List<EspecialidadDto> listarTodasEspecialidades() {
		List<Especialidad> especialidades = findAll();
		List<EspecialidadDto> lista = new ArrayList<>();
		especialidades.forEach(e -> {
			lista.add(EspecialidadDto.builder().id(e.getId()).nombre(e.getNombre()).build());
		});
		return lista;
	}

	public EspecialidadDto obtenerEspecialidadPorId(Long id) {
		Especialidad especialidad = findById(id);

		return EspecialidadDto.builder().nombre(especialidad.getNombre()).build();
	}
}
