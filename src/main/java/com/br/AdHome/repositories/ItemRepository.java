package com.br.AdHome.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.AdHome.models.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
