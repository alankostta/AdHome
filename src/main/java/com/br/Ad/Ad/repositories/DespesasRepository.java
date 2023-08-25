package com.br.Ad.Ad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.Ad.Ad.models.Despesas;

@Repository
public interface DespesasRepository extends JpaRepository<Despesas, Long> {

}
