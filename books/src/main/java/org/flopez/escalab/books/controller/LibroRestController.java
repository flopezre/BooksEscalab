package org.flopez.escalab.books.controller;

import java.util.List;

import org.flopez.escalab.books.model.Libro;
import org.flopez.escalab.books.response.LibroResponseRest;
import org.flopez.escalab.books.service.ILibroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/libros")
public class LibroRestController {

	@Autowired
	private ILibroService service;
	
	@ApiOperation(value = "Obtiene todos los libros",
		      notes = "No requiere nada como parametro",
		      response = List.class,
		      responseContainer = "Libros")
	@GetMapping
	public ResponseEntity<LibroResponseRest> findLibros() {
		
		ResponseEntity<LibroResponseRest> response = service.buscarLibros();
		return response;
	}
	
	@ApiOperation(value = "Obtiene el libro por id",
		      notes = "Requiere un id de un libro como parametr",
		      response = List.class,
		      responseContainer = "Libros")
	@GetMapping("/{id}")
	public ResponseEntity<LibroResponseRest> consultaPorId(@PathVariable("id") Integer id) {
		
		ResponseEntity<LibroResponseRest> response = service.buscarLibrosPorId(id);
		return response;
	}
	
	@ApiOperation(value = "Crea un nuevo libro",
		      notes = "No requiere nada como parametro",
		      response = List.class,
		      responseContainer = "Libros")
	@PostMapping
	public ResponseEntity<LibroResponseRest> crear(@RequestBody Libro request) {
		ResponseEntity<LibroResponseRest> response = service.crearLibro(request);
		return response;
	}
	
	@ApiOperation(value = "Actualiza un libro",
		      notes = "Requiere un id de un libro como parametr",
		      response = List.class,
		      responseContainer = "Libros")
	@PutMapping("/{id}")
	public ResponseEntity<LibroResponseRest> actualizar(@RequestBody Libro request, @PathVariable Integer id) {
		
		ResponseEntity<LibroResponseRest> response = service.actualizarLibro(request, id);
		return response;
	}
	
	@ApiOperation(value = "Elimina un libro",
		      notes = "Requiere un id de un libro como parametro",
		      response = List.class,
		      responseContainer = "Libros")
	@DeleteMapping("/{id}")
	public ResponseEntity<LibroResponseRest> eliminar(@PathVariable Integer id) {
		
		ResponseEntity<LibroResponseRest> response = service.eliminarLibro(id);
		return response;
	}
}
