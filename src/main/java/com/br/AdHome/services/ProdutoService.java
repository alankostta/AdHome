package com.br.AdHome.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.AdHome.models.Produto;
import com.br.AdHome.repositories.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProdutoService {
	@Autowired
	ProdutoRepository produtoRepository;
	
	public Produto salvarProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	public List<Produto> findAll(){
		return produtoRepository.findAll();
	}
	public Optional<Produto> findById(Long id){
		return produtoRepository.findById(id);
	}
	public void deleteProduto(Produto produto) {
		produtoRepository.delete(produto);
	}
	public List<Produto> findBydescricao(String descricao){
		return produtoRepository.findBydescricaoContaining(descricao);
	}
}
