package org.flopez.escalab.books.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.flopez.escalab.books.model.Libro;
import org.flopez.escalab.books.model.dao.ILibroDao;
import org.flopez.escalab.books.response.LibroResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServiceImpl implements ILibroService{

	private static final Logger Log = LoggerFactory.getLogger(LibroServiceImpl.class);
	
	@Autowired
	private ILibroDao libroDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<LibroResponseRest> buscarLibros() {
	
		Log.info("Inicio metodo buscar libros");
		LibroResponseRest response = new LibroResponseRest();
		
		try {
			
			List<Libro> libros = (List<Libro>) libroDao.findAll();
			response.getLibroResponse().setLibro(libros);
			response.setMetada("OK", "0", "Respuesta exitosa");
		} catch (Exception e) {
			response.setMetada("Error", "-1", "Error al consultar libros");
			Log.error("Error al consultar libros: " , e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<LibroResponseRest> buscarLibrosPorId(Integer id) {
		
		Log.info("Inicio metodo buscar libro por id");
		LibroResponseRest response = new LibroResponseRest();
		List<Libro> list = new ArrayList<>();
		
		try {
			Optional<Libro> libro = libroDao.findById(id);
			if(libro.isPresent()) {
				list.add(libro.get());
				response.getLibroResponse().setLibro(list);
			} else {
				Log.error("Error al consultar libro por id");
				response.setMetada("Error", "-1", "Libro no encontrado");
				return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			Log.error("Error al consultar libro por id");
			response.setMetada("Error", "-1", "Error al consultar libro");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetada("OK", "0", "Respuesta exitosa");
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<LibroResponseRest> crearLibro(Libro libro) {
		
		Log.info("Inicio mentodo crear libro");
		LibroResponseRest response = new LibroResponseRest();
		List<Libro> list = new ArrayList<>();
		
		try {
			
			Libro libroGuardado = libroDao.save(libro);
			if(libroGuardado != null) {
				list.add(libroGuardado);
				response.getLibroResponse().setLibro(list);
			} else {
				Log.error("Error al guardar libro");
				response.setMetada("Error", "-1", "Libro no guardado");
				return new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			Log.error("Error al guardar libro");
			response.setMetada("Error", "-1", "Error al guardar libro");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetada("OK", "0", "Libro creado");
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<LibroResponseRest> actualizarLibro(Libro libro, Integer id) {
		
		Log.info("Inicio metodo actualizar");
		LibroResponseRest response = new LibroResponseRest();
		List<Libro> list = new ArrayList<>();
		
		try {
			
			Optional<Libro> libroBuscado = libroDao.findById(id);
			if(libroBuscado.isPresent()) {
				libroBuscado.get().setNombre(libro.getNombre());
				libroBuscado.get().setDescripcion(libro.getDescripcion());
				
				Libro libroActualizar = libroDao.save(libroBuscado.get());
				if(libroActualizar != null) {
					response.setMetada("OK", "0", "Libro actualizado");
					list.add(libroActualizar);
					response.getLibroResponse().setLibro(list);
				} else {
					Log.error("Error al actualizar libro");
					response.setMetada("Error", "-1", "Libro no actualizado");
					return new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
			} else {
				Log.error("Error al actualizar libro");
				response.setMetada("Error", "-1", "Libro no actualizado");
				return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			Log.error("Error al actualizar libro", e.getMessage());
			e.getStackTrace();
			response.setMetada("Error", "-1", "Libro no actualizado");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<LibroResponseRest> eliminarLibro(Integer id) {

		Log.info("Inicio metodo eliminar libro");
		LibroResponseRest response = new LibroResponseRest();

		try {

			libroDao.deleteById(id);
			response.setMetada("OK", "0", "Libro eliminado");

		} catch (Exception e) {
			Log.error("Error al eliminar libro", e.getMessage());
			e.getStackTrace();
			response.setMetada("Error", "-1", "Libro no eliminado");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
	}

}
