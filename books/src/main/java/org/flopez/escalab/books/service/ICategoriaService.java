package org.flopez.escalab.books.service;

import org.flopez.escalab.books.model.Categoria;
import org.flopez.escalab.books.response.CategoriaResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoriaService {
	
	public ResponseEntity<CategoriaResponseRest> buscarCategoria();	
	public ResponseEntity<CategoriaResponseRest> buscarCatagoriaPorId(Integer id);
	public ResponseEntity<CategoriaResponseRest> crearCategoria(Categoria categoria);
	public ResponseEntity<CategoriaResponseRest> actualizarCatagoria(Categoria categoria, Integer id);
	public ResponseEntity<CategoriaResponseRest> eliminarCategoria(Integer id);
}
