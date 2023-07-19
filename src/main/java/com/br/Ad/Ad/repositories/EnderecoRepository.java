package com.br.Ad.Ad.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.br.Ad.Ad.models.Endereco;
/*
 * Repository: realiza operções "individuais" de acesso ao banco de dados
 */
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

}
