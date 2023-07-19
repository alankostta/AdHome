package com.br.Ad.Ad.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.br.Ad.Ad.models.Cliente;


/*
 * Repository: realiza operções "individuais" de acesso ao banco de dados
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	// Pesquisa JPQL que equivale ao like usar Containing
	// List<Cliente> findByNomeContaining(String name);

	List<Cliente> findByNomeContaining(String name);

	@Query(value = "SELECT c.id, c.nome, " + "e.bairro, e.uf, e.localidade, e.id,"
			+ " e.complemento, e.numero, e.cep, e.logradouro " + "FROM Cliente c JOIN c.endereco e "
			+ "WHERE c.id = :id")
	List<Object[]> findClienteEnderecoById(Long id);

	 @Query("SELECT "
	 			+ "cl, co, en "
	            + "FROM Cliente cl "
	            + "JOIN cl.contato co "
	            + "JOIN cl.endereco en")
	    List<Cliente> projecaoCliente();
	
}
