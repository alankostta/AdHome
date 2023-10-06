package com.br.Ad.Ad.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.br.Ad.Ad.models.Fornecedor;
/*
 * Repository: realiza operções "individuais" de acesso ao banco de dados
 */
@Repository
//@EnableJpaRepositories
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{

	 @Query("SELECT "
	 			+ "fr, co, en "
	            + "FROM Fornecedor fr "
	            + "JOIN fr.contato co "
	            + "JOIN fr.endereco en")
	List<Fornecedor> projecaoFornecedor();
	 
	List<Fornecedor> findBynomeEmpresaContaining(String name);
	
	//@Query("SELECT new com.br.AdHome.AdHome.dto.FornecedorDto(f.fornecedorId, f.nome) Fornecedor f WHERE f.nomeEmpresa LIKE CONCAT('%',:name,'%')")
	//List<FornecedorDto> findByName(@Param("name") String name)
	//List<Fornecedor> findByNameLike(String Name);
}
