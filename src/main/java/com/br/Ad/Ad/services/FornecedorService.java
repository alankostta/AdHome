package com.br.Ad.Ad.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.Ad.Ad.models.Fornecedor;
import com.br.Ad.Ad.repositories.FornecedorRepository;
import jakarta.transaction.Transactional;

/*
* Conhecida como camada Beans
* Service: realiza opeações de negócio.
* Um método da camada Service deve ter um SIGNIFICADO relacionado ao negócio,
* executar várias operações.Ex; requisitar[verificar estoque, salvar pedido, baixar estque,
* enviar e-mail]
*/

@Service
@Transactional
public class FornecedorService {
	@Autowired
	FornecedorRepository fornecedorRepository;
	
	public FornecedorService(FornecedorRepository fornecedorRepository) {
		this.fornecedorRepository = fornecedorRepository;
	}
	public Fornecedor saveFornecedor(Fornecedor fornecedor) {
		return fornecedorRepository.save(fornecedor);
	}
	public List<Fornecedor> findAll(){
		return fornecedorRepository.findAll();
	}
	public List<Fornecedor> projecaoFornecedor(){
		return fornecedorRepository.projecaoFornecedor();
	}
	public Optional<Fornecedor> findById(Long id){
		return fornecedorRepository.findById(id);
	}
	public List<Fornecedor> findByName(String name){
		return fornecedorRepository.findBynomeEmpresaContaining(name);
	}
	public void deleteFornecedor(Fornecedor fornecedor) {
		fornecedorRepository.delete(fornecedor);
	}
}
