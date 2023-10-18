package com.br.AdHome.services;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.br.AdHome.models.Contato;
import com.br.AdHome.repositories.ContatoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ContatoService {
	final ContatoRepository contatoRepository;
	
	public ContatoService(ContatoRepository contatoRepository) {
		this.contatoRepository = contatoRepository;
	}
	public Contato saveContato(Contato contato) {
		return contatoRepository.save(contato);
	}
	public Page<Contato> findAll(Pageable pageable){
		return contatoRepository.findAll(pageable);
	}
	public Optional<Contato> findById(Long id){
		return contatoRepository.findById(id);
	}
	public void deleteContato(Contato contato) {
		contatoRepository.delete(contato);
	}
}
