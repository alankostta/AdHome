package com.br.AdHome.AdHome.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.AdHome.AdHome.models.Produto;
/*
 * Repository: realiza operções "individuais" de acesso ao banco de dados
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	List<Produto> findBydescricaoContaining(String descricao);
}
