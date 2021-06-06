package org.flopez.escalab.books.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Rol implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol")
	private Integer idRol;
	
	@Column(name = "nombre", unique = true, length = 30)
	private String nombre;

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
