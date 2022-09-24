package com.br.AdHome.AdHome.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.br.AdHome.AdHome.dto.FornecedorDto;
import com.br.AdHome.AdHome.models.Fornecedor;
/*
 * Repository: realiza operções "individuais" de acesso ao banco de dados
 */
@Repository
//@EnableJpaRepositories
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{
	//Ao criar uma Query usando JPA os parametros de mapeamento são: 
	//No FROM= nome da classe
	//No WHERE= atributo da classe
	//Dessa forma o JPA consegue identificar os paramentros passados sem gerar o erro
	// na compilação do código.
	//@Query(nativeQuery = true,value ="SELECT f.fornecedor_id, f.nome_fornecedor, f.contato_enum, f.endereco_enum,"
		//	+"f.data_cadastro,f.data_altera,f.empresa_fornecedor FROM tb_fornecedor f WHERE f.empresa_fornecedor ILIKE %:name%")
	@Query("select f.fornecedorId,f.nome from Fornecedor f  where f.nomeEmpresa like %?1%")
	List<FornecedorDto> findByName(String name);
	
	//@Query("SELECT new com.br.AdHome.AdHome.dto.FornecedorDto(f.fornecedorId, f.nome) Fornecedor f WHERE f.nomeEmpresa LIKE CONCAT('%',:name,'%')")
	//List<FornecedorDto> findByName(@Param("name") String name);
}
