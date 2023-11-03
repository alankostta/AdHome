package com.br.AdHome.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.AdHome.models.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
}
