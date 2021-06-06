package org.flopez.escalab.books.model.dao;

import org.flopez.escalab.books.model.Libro;
import org.springframework.data.repository.CrudRepository;

public interface ILibroDao extends CrudRepository<Libro, Integer>{

}
