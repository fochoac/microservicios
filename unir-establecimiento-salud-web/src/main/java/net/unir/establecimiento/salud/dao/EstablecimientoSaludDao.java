package net.unir.establecimiento.salud.dao;

import javax.enterprise.context.RequestScoped;

import net.unir.establecimiento.salud.dao.impl.GenericoDao;
import net.unir.establecimiento.salud.modelo.EstablecimientoSalud;
import unir.net.modelo.establecimiento.salud.EstablecimientoSaludDto;

@RequestScoped
public class EstablecimientoSaludDao extends GenericoDao<EstablecimientoSalud> {
	public EstablecimientoSaludDao() {
		super(EstablecimientoSalud.class);
	}

	public EstablecimientoSaludDto obtenerEstablecimientoId(final Long id) {
		EstablecimientoSalud establecimiento = findById(id);
		if (establecimiento == null) {
			return null;
		}
		return EstablecimientoSaludDto.builder().correo(establecimiento.getCorreo()).id(establecimiento.getId())
				.nombre(establecimiento.getNombre()).representanteLegal(establecimiento.getRepresentanteLegal())
				.ruc(establecimiento.getRuc()).telefono(establecimiento.getTelefono()).build();
	}
}
