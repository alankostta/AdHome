package com.br.AdHome.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.AdHome.models.AdUser;

@Repository
public interface AdUserRepository extends JpaRepository<AdUser, Long>{
	Optional<AdUser> findByNomeUser(String nomeUser);
}
