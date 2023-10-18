package com.br.AdHome.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.AdHome.models.Despesa;

@Repository
public interface DespesasRepository extends JpaRepository<Despesa, Long> {

}
