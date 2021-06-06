package org.flopez.escalab.books.service;

import java.util.*;

import org.flopez.escalab.books.model.Categoria;
import org.flopez.escalab.books.model.dao.ICategoriaDao;
import org.flopez.escalab.books.response.CategoriaResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaServiceImpl implements ICategoriaService{

	private static final Logger Log = LoggerFactory.getLogger(CategoriaServiceImpl.class);
	
	@Autowired
	private ICategoriaDao categoriaDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> buscarCategoria() {
		
		Log.info("Inicio metodo buscar categorias");
		CategoriaResponseRest response = new CategoriaResponseRest();
		
		try {
			
			List<Categoria> categoria = (List<Categoria>) categoriaDao.findAll();
			response.getCategoriaResponse().setCategoria(categoria);
			response.setMetada("OK", "0", "Respuesta exitosa");
		} catch(Exception e) {
			response.setMetada("Error", "-1", "Error al consultar categorias");
			Log.error("Error al consultar categoria: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK); // Devuelve 200
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> buscarCatagoriaPorId(Integer id) {
		
		Log.info("Inicio metodo buscar categorias por id");
		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<>();
		
		try {
			
			Optional<Categoria> categoria = categoriaDao.findById(id);
			// Si encuentra la categoria
			if(categoria.isPresent()) {
				list.add(categoria.get());
				response.getCategoriaResponse().setCategoria(list);
			} else {
				Log.error("Error al consultar categoria por id");
				response.setMetada("Error", "-1", "Categoria no encontrada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			Log.error("Error al consultar categoria");
			response.setMetada("Error", "-1", "Error al consultar categoria");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetada("OK", "0", "Respuesta exitosa");
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> crearCategoria(Categoria categoria) {
		
		Log.info("Inicio metodo crear categoria");
		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<>();
		
		try {
			
			Categoria categoriaGuardada = categoriaDao.save(categoria);
			if (categoriaGuardada != null) {
				list.add(categoriaGuardada);
				response.getCategoriaResponse().setCategoria(list);
			} else {
				Log.error("Error al guardar categoria");
				response.setMetada("Error", "-1", "Categoria no guardada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			Log.error("Error al guardar categoria");
			response.setMetada("Error", "-1", "Error al guardar categoria");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetada("OK", "0", "Categoria creada");
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> actualizarCatagoria(Categoria categoria, Integer id) {
		
		Log.info("Inicio metodo actualizar");
		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<>();
		
		try {
			
			Optional<Categoria> categoriaBuscada = categoriaDao.findById(id);
			if (categoriaBuscada.isPresent()) {
				categoriaBuscada.get().setNombre(categoria.getNombre());
				categoriaBuscada.get().setDescripcion(categoria.getDescripcion());
				
				// Actualizando
				Categoria categoriaActualizar = categoriaDao.save(categoriaBuscada.get());
				if (categoriaActualizar != null) {
					response.setMetada("OK", "0", "Categoria actualizada");
					list.add(categoriaActualizar);
					response.getCategoriaResponse().setCategoria(list);
				} else {
					Log.error("Error al actualizar categoria");
					response.setMetada("Error", "-1", "Categoria no actualizada");
					return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
			} else {
				Log.error("Error al actualizar categoria");
				response.setMetada("Error", "-1", "Categoria no actualizada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			Log.error("Error al actualizar categoria", e.getMessage());
			e.getStackTrace();
			response.setMetada("Error", "-1", "Categoria no actualizada");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> eliminarCategoria(Integer id) {
		
		Log.info("Inicio metodo eliminar categoria");
		CategoriaResponseRest response = new CategoriaResponseRest();
		
		try {
			
			categoriaDao.deleteById(id);
			response.setMetada("OK", "0", "Categoria eliminada");
			
		} catch (Exception e) {
			Log.error("Error al eliminar categoria", e.getMessage());
			e.getStackTrace();
			response.setMetada("Error", "-1", "Categoria no eliminada");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}
	
}
