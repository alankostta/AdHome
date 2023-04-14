package com.br.AdHome.AdHome.services;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.br.AdHome.AdHome.models.Categoria;
import com.br.AdHome.AdHome.repositories.CategoriaRepository;

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
