package com.br.AdHome.AdHome.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.AdHome.AdHome.models.Produto;
import com.br.AdHome.AdHome.repositories.ProdutoRepository;

/*
* Conhecida como camada Beans
* Service: realiza opeações de negócio.
* Um método da camada Service deve ter um SIGNIFICADO relacionado ao negócio,
* executar várias operações.Ex; requisitar[verificar estoque, salvar pedido, baixar estque,
* enviar e-mail]
*/

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
