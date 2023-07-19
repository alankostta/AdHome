package com.br.Ad.Ad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.Ad.Ad.models.Pedido;
/*
 * Repository: realiza operções "individuais" de acesso ao banco de dados
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
