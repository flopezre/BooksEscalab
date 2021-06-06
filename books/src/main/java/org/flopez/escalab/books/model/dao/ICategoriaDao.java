package org.flopez.escalab.books.model.dao;

import org.flopez.escalab.books.model.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface ICategoriaDao extends CrudRepository<Categoria, Integer>{
	
}
