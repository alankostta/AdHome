package com.br.AdHome.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.AdHome.models.Item;
import com.br.AdHome.models.Pedido;
import com.br.AdHome.repositories.ItemRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	public List<Item> findByPedido(Pedido pedido) {
		return itemRepository.findByPedido(pedido);
	}
	public void deleteItens(Pedido pedido) {
		itemRepository.deleteByPedido(pedido);
	}
}
