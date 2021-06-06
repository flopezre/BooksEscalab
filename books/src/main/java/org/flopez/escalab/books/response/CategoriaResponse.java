package org.flopez.escalab.books.response;

import java.util.List;

import org.flopez.escalab.books.model.Categoria;

public class CategoriaResponse {
	
	private List<Categoria> categoria;

	public List<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<Categoria> categoria) {
		this.categoria = categoria;
	}	
}
