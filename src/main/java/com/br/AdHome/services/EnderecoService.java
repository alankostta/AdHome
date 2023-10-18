package com.br.AdHome.services;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.br.AdHome.models.Endereco;
import com.br.AdHome.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EnderecoService {
	final EnderecoRepository enderecoRepository;
	
	public EnderecoService(EnderecoRepository enderecoRepository) {
		this.enderecoRepository = enderecoRepository;
	}
	public Endereco saveEndereco(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
	public Page<Endereco> findAllEndereco(Pageable pageable){
		return enderecoRepository.findAll(pageable);
	}
	public Optional<Endereco> findByIdEndereco(Long id){
		return enderecoRepository.findById(id);
	}
	public void deleteEndereco(Endereco endereco) {
		enderecoRepository.delete(endereco);
	}
}
