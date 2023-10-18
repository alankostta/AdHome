package com.br.AdHome.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.AdHome.models.Pedido;
import com.br.AdHome.repositories.PedidoRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	
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
