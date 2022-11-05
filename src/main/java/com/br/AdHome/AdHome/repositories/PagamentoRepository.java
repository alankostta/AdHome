package com.br.AdHome.AdHome.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.AdHome.AdHome.models.Pagamento;
/*
 * Repository: realiza operções "individuais" de acesso ao banco de dados
 */
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{

}
