package com.br.AdHome.AdHome.dto;

import com.br.AdHome.AdHome.models.AdUser;

public class UserDto {
	
	private String nomeUser;
	
	private String emailUser;

	private String password;

	private String nome;
	
	
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
	public AdUser toAdUser() {
		AdUser user = new AdUser();
		user.setNome(this.nome);
		user.setNomeUser(this.nomeUser);
		user.setEmailUser(this.emailUser);
		user.setPassword(this.password);
		return user;
	}
	public AdUser toAdUser(AdUser user) {
		user.setNome(this.nome);
		user.setNomeUser(this.nomeUser);
		user.setEmailUser(this.emailUser);
		user.setPassword(this.password);
		return user;
	}
	public void fromAdUser(AdUser user) {
		this.nome = user.getNome();
		this.nomeUser = user.getNomeUser();
		this.emailUser = user.getEmailUser();
		this.password = user.getPassword();
	}
}
