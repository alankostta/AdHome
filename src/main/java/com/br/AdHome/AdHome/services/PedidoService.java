package com.br.AdHome.AdHome.services;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.br.AdHome.AdHome.models.Pedido;
import com.br.AdHome.AdHome.repositories.PedidoRepository;

/*
* Conhecida como camada Beans
* Service: realiza opeações de negócio.
* Um método da camada Service deve ter um SIGNIFICADO relacionado ao negócio,
* executar várias operações.Ex; requisitar[verificar estoque, salvar pedido, baixar estque,
* enviar e-mail]
*/
@Service
@Transactional
public class PedidoService {
	final PedidoRepository pedidoRepository;
	public PedidoService(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}
	public Pedido savePedido(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	public List<Pedido> findAll(){
		return pedidoRepository.findAll();
	}
	public Optional<Pedido> findById(Long id){
		return pedidoRepository.findById(id);
	}
	public void deletePedido(Pedido pedido) {
		pedidoRepository.delete(pedido);
	}
}
