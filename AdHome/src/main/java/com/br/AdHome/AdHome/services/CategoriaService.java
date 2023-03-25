package com.br.AdHome.AdHome.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.br.AdHome.AdHome.repositories.CategoriaRepository;

@Service
@Transactional
public class CategoriaService {
	
	final CategoriaRepository categoriaRepository;
	
	public CategoriaService(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;	
	}
}
