package org.flopez.escalab.books.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Model Usuario")
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer idUsuario;
	
	@ApiModelProperty(notes = "El nombre usuario")
	@Size(min = 5, max = 30, message = "El campo nombre usuario debe contener mínimo 5 caracteres")
	@Column(name = "nombre_usuario", unique = true, length = 30)
	private String nombreUsuario;
	
	@ApiModelProperty(notes = "La clave")
	@Size(min = 5, max = 30, message = "El campo nombre usuario debe contener mínimo 6 caracteres")
	@Column(name = "clave", length = 65)
	private String password;
	
	@Column(name = "habilitado")
	private Boolean habilitado;
	
	// Si se elimina un usuario, tambien se eliminan los roles asociados
	// al usuario
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "usuarios_roles",
		joinColumns = @JoinColumn(name = "usuario_id"),
		inverseJoinColumns = @JoinColumn(name = "rol_id"),
		uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "rol_id"})
	)
	@Column(name = "roles")
	private List<Rol> roles;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
	
	
}
