package com.br.AdHome.AdHome.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.br.AdHome.AdHome.models.Categoria;

public class CategoriaDto {
	
	@NotNull
	@Size(max=50)
	private String nomeCategoria;
	
	public CategoriaDto() {
	}

	public CategoriaDto(String nomeCategoria) {	
		this.nomeCategoria = nomeCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public Categoria toCategoria() {	
		Categoria categorias = new Categoria();
		categorias.setNomeCategoria(this.nomeCategoria);
		return categorias;
	}
	public Categoria toCategoria(Categoria categorias) {
			categorias.setNomeCategoria(this.nomeCategoria);	
			return categorias;
	}
	public void fromCategoria(Categoria categorias) {
		this.nomeCategoria = categorias.getNomeCategoria();
		
	}
}
