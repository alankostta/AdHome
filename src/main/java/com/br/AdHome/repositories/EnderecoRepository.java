package com.br.AdHome.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.AdHome.models.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

}
