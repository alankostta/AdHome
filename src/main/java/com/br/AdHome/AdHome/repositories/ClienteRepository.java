package com.br.AdHome.AdHome.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.AdHome.AdHome.models.Cliente;
import com.br.AdHome.AdHome.models.Fornecedor;

/*
 * Repository: realiza operções "individuais" de acesso ao banco de dados
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
		@Query(value="SELECT n FROM Cliente n WHERE n.nome LIKE %?1%")
		List<Fornecedor> findByNome(String name);
}
