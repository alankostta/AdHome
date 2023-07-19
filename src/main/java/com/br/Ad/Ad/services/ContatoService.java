package com.br.Ad.Ad.services;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.br.Ad.Ad.models.Contato;
import com.br.Ad.Ad.repositories.ContatoRepository;
import jakarta.transaction.Transactional;
/* Conhecida como camada Beans
 * Service: realiza opeações de negócio.
 * Um método da camada Service deve ter um SIGNIFICADO relacionado ao negócio,
 * executar várias operações.Ex; requisitar[verificar estoque, salvar pedido, baixar estque,
 * enviar e-mail]
 */
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
