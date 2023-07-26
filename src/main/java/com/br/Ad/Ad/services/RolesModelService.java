package com.br.Ad.Ad.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.Ad.Ad.models.RoleModel;
import com.br.Ad.Ad.repositories.RolesModelRepository;

@Service
public class RolesModelService {

	@Autowired
	RolesModelRepository rolesRepository;
	
	public RoleModel saveRole(RoleModel role) {
		return rolesRepository.save(role);
	}
	public List<RoleModel> findAll() {
		return rolesRepository.findAll();
	}
	public Optional<RoleModel> findById(Long id) {
		return rolesRepository.findById(id);
	}
	public void deleteCliente(RoleModel role) {
		rolesRepository.delete(role);
	}
}
