package org.flopez.escalab.books.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Model Autor")
@Entity
@Table(name = "autores")
public class Autor implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_autor")
	private Integer idAutor;
	
	@ApiModelProperty(notes = "El nombre del autor")
	@Size(min = 5, max = 30, message = "El campo nombre debe contener mínimo 5 caracteres")
	@Column(name = "nombre", nullable = true, length = 30)
	private String nombre;
	
	@ApiModelProperty(notes = "El apellido del autor")
	@Size(min = 5, max = 30, message = "El campo apellido debe contener mínimo 5 caracteres")
	@Column(name = "apellido", nullable = true, length = 30)
	private String apellido;
	
	@ApiModelProperty(notes = "La nacionalidad del autor")
	@Size(min = 10, max = 30, message = "El campo nacionalidad debe contener mínimo 10 caracteres")
	@Column(name = "nacionalidad", nullable = true, length = 30)
	private String nacionalidad;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

}
