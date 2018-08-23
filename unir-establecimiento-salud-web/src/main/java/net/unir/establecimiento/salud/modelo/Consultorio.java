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
@Table(name = "consultorios")

public class Consultorio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4174846758149698834L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "consultorio_pk")
	private Long id;

	@Column(name = "numero_consultorio")
	private String numeroConsultorio;
	@Column(name = "largo")
	private double largo;
	@Column(name = "ancho")
	private double ancho;
	private int piso;

	private boolean estado;

}