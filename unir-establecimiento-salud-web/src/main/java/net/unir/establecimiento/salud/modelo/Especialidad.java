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
@Table(name = "especialidades")
public class Especialidad implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "especialidad_pk")
	private Long id;

	private String nombre;

	private boolean estado;

}