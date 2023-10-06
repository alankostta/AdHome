package com.br.Ad.Ad.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.Ad.Ad.models.AdUser;
@Repository
public interface AdUserRepository extends JpaRepository<AdUser, Long>{
	Optional<AdUser> findByNomeUser(String nomeUser);
}
