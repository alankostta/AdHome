package com.br.AdHome.AdHome.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.AdHome.AdHome.dto.FornecedorDto;
import com.br.AdHome.AdHome.models.Fornecedor;
import com.br.AdHome.AdHome.repositories.FornecedorRepository;

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
	public Optional<Fornecedor> findById(Long id){
		return fornecedorRepository.findById(id);
	}
	public List<FornecedorDto> findByName(String name){
		return fornecedorRepository.findByName(name);
	}
	public void deleteFornecedor(Fornecedor fornecedor) {
		fornecedorRepository.delete(fornecedor);
	}
}
