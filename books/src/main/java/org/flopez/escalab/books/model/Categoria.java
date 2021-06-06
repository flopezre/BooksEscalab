package org.flopez.escalab.books.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "categorias")
public class Categoria implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria")
	private Integer idCategoria;
	
	
	@ApiModelProperty(notes = "El nombre de la categoria")
	@Size(min = 5, max = 30, message = "El campo nombre debe contener mínimo 5 caracteres")
	@Column(name = "nombre", nullable = true, length = 30)
	private String nombre;
	
	@ApiModelProperty(notes = "La descripcion de la categoria")
	@Size(min = 5, max = 30, message = "El campo descripcion debe contener mínimo 5 caracteres")
	@Column(name = "descripcion", nullable = true, length = 30)
	private String descripcion;

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
