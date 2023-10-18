package com.br.AdHome.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.AdHome.models.RoleModel;

@Repository
public interface RolesModelRepository extends JpaRepository<RoleModel, Long> {

}
