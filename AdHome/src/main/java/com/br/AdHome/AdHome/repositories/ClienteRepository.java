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
	// Pesquisa JPQL que equivale ao like usar Containing
	// List<Cliente> findByNomeContaining(String name);

	List<Cliente> findByNomeContaining(String name);

	@Query(value = 
			"SELECT c.clienteId, c.nome, " 
			+ "e.bairro, e.uf, e.localidade, e.enderecoId,"
			+ " e.complemento, e.numero, e.cep, e.logradouro " 
			+ "FROM Cliente c JOIN c.endereco e "
			+ "WHERE c.clienteId = :id")
	List<Object[]> findClienteEnderecoById(Long id);
	
    @Query("SELECT cl FROM Cliente cl"
    		+ " JOIN FETCH cl.contato co "
    		+ "JOIN FETCH cl.endereco en")
    List<Cliente> projecaoCliente();
}
