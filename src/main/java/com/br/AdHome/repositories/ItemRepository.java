package com.br.AdHome.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.AdHome.models.Item;
import com.br.AdHome.models.Pedido;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	List<Item> findByPedido(Pedido pedido);
	
	void deleteByPedido(Pedido pedido);
}
