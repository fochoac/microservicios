package net.unir.establecimiento.salud.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * The persistent class for the citas_medicas database table.
 * 
 */
@Data()

@Entity
@Table(name = "establecimientos_salud")

public class EstablecimientoSalud implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4174846758149698834L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "establecimiento_salud_pk")
	private Long id;

	@Column(name = "nombre_establecimiento")
	private String nombre;
	@Column(name = "ruc")
	private String ruc;
	@Column(name = "representante_legal")
	private String representanteLegal;
	@Column(name = "telefono_representante_legal")
	private String telefono;
	@Column(name = "correo_electronico")
	private String correo;

	private boolean estado;

}