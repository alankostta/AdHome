package com.br.AdHome.AdHome.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.br.AdHome.AdHome.models.Categoria;

public class CategoriaDto {
	
	@NotNull
	@Size(max=50)
	private String nomeCategoria;
	
	@NotNull
	@Size(max=150)
	private String descricaoCategoria;
	
	public CategoriaDto() {
	}

	public CategoriaDto(String nomeCategoria, String descricaoCategoria, Date dataCasdastro) {
		
		this.nomeCategoria = nomeCategoria;
		this.descricaoCategoria = descricaoCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	public Categoria toCategoria() {
		
		Categoria categorias = new Categoria();
		categorias.setNomeCategoria(this.nomeCategoria);
		categorias.setDescricaoCategoria(this.descricaoCategoria);
		
		return categorias;
	}
	public Categoria toCategoria(Categoria categorias) {
			
			categorias.setNomeCategoria(this.nomeCategoria);
			categorias.setDescricaoCategoria(this.descricaoCategoria);
			
			return categorias;
	}
	public void fromCategoria(Categoria categorias) {
		this.descricaoCategoria = categorias.getDescricaoCategoria();
		this.nomeCategoria = categorias.getNomeCategoria();
		
	}
}
