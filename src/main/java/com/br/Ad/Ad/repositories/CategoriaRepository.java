package com.br.Ad.Ad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.Ad.Ad.models.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
