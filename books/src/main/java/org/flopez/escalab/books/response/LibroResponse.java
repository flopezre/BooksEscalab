package org.flopez.escalab.books.response;

import java.util.List;

import org.flopez.escalab.books.model.Libro;

public class LibroResponse {

	private List<Libro> libros;
	
	public List<Libro> getLibro() {
		return libros;
	}
	
	public void setLibro(List<Libro> libros) {
		this.libros = libros;
	}
}
