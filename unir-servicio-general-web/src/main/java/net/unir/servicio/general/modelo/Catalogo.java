package net.unir.servicio.general.modelo;

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
@Table(name = "catalogo")
@NamedQueries({
		@NamedQuery(name = Catalogo.LISTAR_POR_NEMONICO, query = "SELECT c FROM Catalogo c where c.estado=true and c.nemonico = :nemonico"),
		@NamedQuery(name = Catalogo.LISTAR_HIJOS_POR_NEMONICO_PADRE, query = "SELECT c FROM Catalogo c where c.estado=true and c.idCatalogoPadre in (Select o.id from Catalogo o where o.estado=true and o.nemonico=:nemonico)") })
public class Catalogo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4174846758149698834L;
	public static final String LISTAR_POR_NEMONICO = "Catalogo.listarPorNemonico";
	public static final String LISTAR_HIJOS_POR_NEMONICO_PADRE = "Catalogo.listarHijosPorNemonicoPadre";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "catalogo_pk")
	private Long id;

	@Column(name = "nemonico")
	private String nemonico;
	@Column(name = "catalogo_padre_pk")
	private Long idCatalogoPadre;
	@Column(name = "descripcion")
	private String descripcion;

	private boolean estado;

}