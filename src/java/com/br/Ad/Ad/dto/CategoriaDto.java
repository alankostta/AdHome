package com.br.Ad.Ad.dto;

import java.util.Date;
import com.br.Ad.Ad.models.Categoria;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CategoriaDto {
	
	@NotNull
	@Size(max=50)
	private String nomeCategoria;
	
	public CategoriaDto() {
	}

	public CategoriaDto(String nomeCategoria, Date dataCasdastro) {
		
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
