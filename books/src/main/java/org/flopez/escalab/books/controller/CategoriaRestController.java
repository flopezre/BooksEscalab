package org.flopez.escalab.books.controller;

import org.flopez.escalab.books.model.Categoria;
import org.flopez.escalab.books.response.CategoriaResponseRest;
import org.flopez.escalab.books.service.ICategoriaService;

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

@RestController
@RequestMapping("/categorias")
public class CategoriaRestController {
	
	@Autowired
	private ICategoriaService service;
	
	@GetMapping
	public ResponseEntity<CategoriaResponseRest> findCategoria() {
		
		ResponseEntity<CategoriaResponseRest> response = service.buscarCategoria();
		return response;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaResponseRest> consultaPorId(@PathVariable("id") Integer id) {
		
		ResponseEntity<CategoriaResponseRest> response = service.buscarCatagoriaPorId(id);
		return response;
	}
	
	@PostMapping
	public ResponseEntity<CategoriaResponseRest> crear(@RequestBody Categoria request) {
		
		ResponseEntity<CategoriaResponseRest> response = service.crearCategoria(request);
		return response;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoriaResponseRest> actualizar(@RequestBody Categoria request, @PathVariable Integer id) {
		
		ResponseEntity<CategoriaResponseRest> response = service.actualizarCatagoria(request, id);
		return response;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CategoriaResponseRest> eliminar(@PathVariable Integer id) {
		
		ResponseEntity<CategoriaResponseRest> response = service.eliminarCategoria(id);
		return response;
	}
	
}