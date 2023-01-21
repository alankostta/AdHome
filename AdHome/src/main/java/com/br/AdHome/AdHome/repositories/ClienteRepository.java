package com.br.AdHome.AdHome.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.br.AdHome.AdHome.models.Cliente;

/*
 * Repository: realiza operções "individuais" de acesso ao banco de dados
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
		//Pesquisa JPQL que equivale ao like usar Containing
		List<Cliente> findByNomeContaining(String name);
}
