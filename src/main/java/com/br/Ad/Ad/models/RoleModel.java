package com.br.Ad.Ad.models;

import java.io.Serializable;
import org.springframework.security.core.GrantedAuthority;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_role")
public class RoleModel implements GrantedAuthority, Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	@Column(name = "role_name", nullable = false, unique = true)
	private RoleName roleName;
	
	@Override
	public String getAuthority() {
		// por se tratar de um Enum presica ser convertido para string
		return this.roleName.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleName getRoleName() {
		return roleName;
	}
	public void setRoleName(RoleName roleName) {
		this.roleName = roleName;
	}
}
