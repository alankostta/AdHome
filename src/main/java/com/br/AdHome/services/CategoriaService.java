package com.br.AdHome.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.br.AdHome.models.Categoria;
import com.br.AdHome.repositories.CategoriaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriaService {
	
	final CategoriaRepository categoriaRepository;
	
	public CategoriaService(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;	
	}
	public Categoria saveCategoria(Categoria categorias) {
		return categoriaRepository.save(categorias);
	}
	public List<Categoria> findAll(){
		return categoriaRepository.findAll();
	}
	public Optional<Categoria> findById(Long Id) {
		return categoriaRepository.findById(Id);
	}
	public void deleteCategoria(Categoria categorias) {
		categoriaRepository.delete(categorias);
	}
}
