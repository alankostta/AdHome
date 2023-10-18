package com.br.AdHome.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.br.AdHome.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNomeContaining(String name);

	@Query("SELECT c FROM Cliente c "
		       + "JOIN c.contato co "
		       + "JOIN c.endereco e "
		       + "WHERE c.id = :id")
		Optional<Cliente> findClienteEnderecoById(@Param("id") Long id);



	 @Query("SELECT "
	 			+ "cl, co, en "
	            + "FROM Cliente cl "
	            + "JOIN cl.contato co "
	            + "JOIN cl.endereco en")
	    List<Cliente> projecaoCliente();
	
}
