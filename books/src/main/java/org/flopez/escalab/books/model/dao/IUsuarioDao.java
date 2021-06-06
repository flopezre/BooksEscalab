package org.flopez.escalab.books.model.dao;

import org.flopez.escalab.books.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDao extends CrudRepository<Usuario, Integer> {
	
	public Usuario findByNombreUsuario(String nombreUsuario);
	
	@Query("select u from Usuario u where u.nombreUsuario=?1")
	public Usuario findByIdNombreUsuariov2(String nombreUsuario);
}
