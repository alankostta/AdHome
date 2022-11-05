package com.br.AdHome.AdHome.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.br.AdHome.AdHome.models.Endereco;
/*
 * Repository: realiza operções "individuais" de acesso ao banco de dados
 */
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

}
