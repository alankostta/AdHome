package com.br.Ad.Ad.dto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.br.Ad.Ad.models.AdUser;
import com.br.Ad.Ad.models.RoleModel;

public class AduserDto {
	
	private BigInteger id;
	
	private String nomeUser;
	
	private String emailUser;

	private String password;

	private String nome;
	
	private List<RoleModel> roles;
	
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeUser() {
		return nomeUser;
	}

	public void setNomeUser(String nomeUser) {
		this.nomeUser = nomeUser;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<RoleModel> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleModel> roles) {
		this.roles = roles;
	}

	public AdUser toAdUser() {
		AdUser user = new AdUser();
		
		user.setNome(this.nome);
		user.setNomeUser(this.nomeUser);
		user.setEmailUser(this.emailUser);
		user.setPassword(this.password);
		user.setRoles(this.roles);
		return user;
	}
	public AdUser toAdUser(AdUser user) {
		user.setId(this.id.longValue());
		user.setNome(this.nome);
		user.setNomeUser(this.nomeUser);
		user.setEmailUser(this.emailUser);
		user.setPassword(this.password);
		user.setRoles(this.roles);
		return user;
	}
	public void fromAdUser(AdUser user) {
		this.id = BigInteger.valueOf(user.getId());
		this.nome = user.getNome();
		this.nomeUser = user.getNomeUser();
		this.emailUser = user.getEmailUser();
		this.password = user.getPassword();
		this.roles = user.getRoles();
	}
	public List<AduserDto> listUser(List<AdUser> user){
		List<AduserDto> adUserDtoList = new ArrayList<>();
	    for (AdUser adUser : user) {
	        AduserDto dto = new AduserDto();
	        dto.fromAdUser(adUser);
	        adUserDtoList.add(dto);
	    }
	    return adUserDtoList;
	}
}
