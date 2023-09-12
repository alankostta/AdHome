package com.br.Ad.Ad.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.Ad.Ad.models.Cliente;

/*
 * Repository: realiza operções "individuais" de acesso ao banco de dados
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNomeContaining(String name);

	@Query(value = "SELECT c FROM Cliente c JOIN FETCH c.endereco e WHERE c.id = :id")
	Optional<Cliente> findClienteEnderecoById(Long id);


	 @Query("SELECT "
	 			+ "cl, co, en "
	            + "FROM Cliente cl "
	            + "JOIN cl.contato co "
	            + "JOIN cl.endereco en")
	    List<Cliente> projecaoCliente();
	
}
