package com.br.AdHome.AdHome.services;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.AdHome.AdHome.models.Pagamento;
import com.br.AdHome.AdHome.repositories.PagamentoRepository;

/*
* Conhecida como camada Beans
* Service: realiza opeações de negócio.
* Um método da camada Service deve ter um SIGNIFICADO relacionado ao negócio,
* executar várias operações.Ex; requisitar[verificar estoque, salvar pedido, baixar estque,
* enviar e-mail]
*/

@Service
@Transactional
public class PagamentoService {
	
	final PagamentoRepository pagamentoRepository;
	
	public PagamentoService(PagamentoRepository pagamentoRepository) {
		this.pagamentoRepository = pagamentoRepository;
	}
	public Pagamento salvePagamanto(Pagamento pagamento) {
		return pagamentoRepository.save(pagamento);
	}
	public Page<Pagamento> findAll(Pageable pageable){
		return pagamentoRepository.findAll(pageable);
	}
	public Optional<Pagamento> findById(Long id){
		return pagamentoRepository.findById(id);
	}
	public void deletePagamento(Pagamento pagamento) {
		pagamentoRepository.delete(pagamento);
	}
}
