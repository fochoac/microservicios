package net.unir.servicio.seguridad.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

/**
 * The persistent class for the citas_medicas database table.
 * 
 */
@Data()

@Entity
@Table(name = "menu")

public class Menu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4174846758149698834L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "menu_pk")
	private Long id;

	@Column(name = "rol_pk")
	private Long rolId;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "url")
	private String url;
	@Column(name = "menu_padre_pk")
	private Long idMenuPadre;
	private boolean estado;

}