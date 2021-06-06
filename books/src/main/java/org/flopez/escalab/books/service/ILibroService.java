package org.flopez.escalab.books.service;

import org.flopez.escalab.books.model.Libro;
import org.flopez.escalab.books.response.LibroResponseRest;
import org.springframework.http.ResponseEntity;

public interface ILibroService {

	public ResponseEntity<LibroResponseRest> buscarLibros();
	public ResponseEntity<LibroResponseRest> buscarLibrosPorId(Integer id);
	public ResponseEntity<LibroResponseRest> crearLibro(Libro libro);
	public ResponseEntity<LibroResponseRest> actualizarLibro(Libro libro, Integer id);
	public ResponseEntity<LibroResponseRest> eliminarLibro(Integer id);
	
}
