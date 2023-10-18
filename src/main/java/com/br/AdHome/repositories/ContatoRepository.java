package com.br.AdHome.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.AdHome.models.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{

}
